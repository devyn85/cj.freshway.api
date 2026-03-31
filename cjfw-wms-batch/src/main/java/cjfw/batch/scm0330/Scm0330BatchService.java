package cjfw.batch.scm0330;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.scm0330.soap.DTSCM0330SCM;
import cjfw.batch.scm0330.soap.DTSCM0330SCMResponse;
import cjfw.batch.scm0330.soap.SISCM0330SCMSO;
import cjfw.batch.scm0330.soap.SISCM0330SCMSOService;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Scm0330BatchService {

	private static final String BATCH_IF_ID = "SCM0330";
	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리
	public void callScm0330JobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callScm0330JobService(batchParam, null);
	}
	
	@Transactional
	public void callScm0330JobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			copyDto.setResultCode("0");
			logSystemOut(copyDto, "START", logOption[4], "51", "JAVA SERVICE START");
		}

		int errorCode = 0;				//에러코드
		String errorMessage = null;		//에러메시지
		String ifStaus = null; 			//IF_MASTER 호출결과
		Map<String, String> locator = null;
		Scm0330ParamsDto defaultParams = new Scm0330ParamsDto();

		DTSCM0330SCM.IFDMSENDDATAH header = null;
		DTSCM0330SCM.IFDMSENDDATAD detail = null;
		DTSCM0330SCM.IFSTSTOCKSERIALINFOS serial = null;
		DTSCM0330SCM mmData = null;

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "69", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
			try {
				List<Scm0330ParamsDto> searchList =  commonDao.selectList("scm0330Service.selectScm0330Header");
				logSystemOut(copyDto, "INFO", logOption[4], "73", "[sqlId]selectScm0330Header Rows:"+String.valueOf(searchList.size()));

				if(searchList != null) {
					DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
					DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

					LocalDateTime now = LocalDateTime.now();
					String dayString  = now.format(dayFormatter);
					String timeString = now.format(timeFormatter);

					for(Scm0330ParamsDto searchData : searchList) {
						try {
							mmData = new DTSCM0330SCM();
							mmData.setXSYS("WMS");
							mmData.setXDATS(dayString);
							mmData.setXTIMS(timeString);
							mmData.setXROWS("1");

							try {
								header = new DTSCM0330SCM.IFDMSENDDATAH();

								header.setSLIPNO(searchData.getSLIPNO());
								header.setCLOSECD(searchData.getCLOSECD());
								header.setDOCNO(searchData.getDOCNO());
								header.setEFFECTIVEDATE(searchData.getEFFECTIVEDATE());
								header.setPOKEY(searchData.getPOKEY());
								header.setPOSTINGDATE(searchData.getPOSTINGDATE());

								mmData.getIFDMSENDDATAH().add(header);
							} catch (Exception e) {
								log.info(e.getMessage());
							}

							if(errorCode == 0) {
								// 처리대상 IF_FLAG 처리
								defaultParams.setSEARCH_IF_FLAG("N");
								defaultParams.setUPDATE_IF_FLAG("P");
								defaultParams.setUPDATE_IF_MEMO("");
								defaultParams.setEDITDATE(searchData.getEDITDATE());
								defaultParams.setIFTIMESTAMP(searchData.getIFTIMESTAMP());
								commonDao.update("scm0330Service.updateScm0330", defaultParams);
								commonDao.update("scm0330Service.updateSerailScm0330", defaultParams);

								// Detail 조회 조건 설정
								defaultParams.setDOCNO(searchData.getDOCNO());
								defaultParams.setSLIPNO(searchData.getSLIPNO());
								defaultParams.setPOKEY(searchData.getPOKEY());
								List<Scm0330ParamsDto> detailList = commonDao.selectList("scm0330Service.selectScm0330Detail", defaultParams);
								logSystemOut(copyDto, "INFO", logOption[4], "121", "[sqlId]selectScm0330Detail Rows:"+String.valueOf(detailList.size()));
								List<Scm0330ParamsDto> serialList = commonDao.selectList("scm0330Service.selectScm0330Serial", defaultParams);
								logSystemOut(copyDto, "INFO", logOption[4], "123", "[sqlId]selectScm0330Serial Rows:"+String.valueOf(serialList.size()));

								if(detailList != null && serialList != null) {
									if(detailList.isEmpty()) {
										errorCode = -20001;
										errorMessage = "주문실적 Detail 정보를 찾을 수 없습니다.";
									}

									if(serialList.isEmpty()) {
										errorCode = -20001;
										errorMessage = "유통이력 정보를 찾을 수 없습니다.";
									}

									if(errorCode == 0 ) {
										try {
											for(Scm0330ParamsDto detailData : detailList) {
												detail = new DTSCM0330SCM.IFDMSENDDATAD();

												detail.setCONFIRMWEIGHT(detailData.getCONFIRMWEIGHT());
												detail.setDOCLINE(detailData.getDOCLINE());
												detail.setDOCNO(detailData.getDOCNO());
												detail.setPLANT(detailData.getPLANT());
												detail.setPOKEY(detailData.getPOKEY());
												detail.setPOLINE(detailData.getPOLINE());
												detail.setSKU(detailData.getSKU());
												detail.setSLIPLINE(detailData.getSLIPLINE());
												detail.setSLIPNO(detailData.getSLIPNO());
												detail.setSTORAGELOC(detailData.getSTORAGELOC());
												detail.setSTORERCONFIRMQTY(String.valueOf(detailData.getSTORERCONFIRMQTY()));
												detail.setSTORERUOM(detailData.getSTORERUOM());
												detail.setSTOCKTRANSTYPE(detailData.getSTOCKTRANSTYPE());

												mmData.getIFDMSENDDATAD().add(detail);
											}

										} catch (Exception e) {
											errorCode = -20001;
											errorMessage = "실적정보 Detail 처리중 오류발생.";
										}
									}


									if(errorCode == 0) {
										serial = new DTSCM0330SCM.IFSTSTOCKSERIALINFOS();
										String serialType = null;
										try {
											for(Scm0330ParamsDto serialData : serialList) {
												defaultParams.setSKU(serialData.getSKU());
												serialType = commonDao.selectOne("scm0330Service.selectSku", defaultParams);

												if(!"3".equals(serialType)) {
													serial.setSERIALNO(serialData.getSERIALNO());
												}

												serial.setCONVSERIALNO(serialData.getCONVSERIALNO());
												serial.setCANCELQTY(String.valueOf(serialData.getCANCELQTY()));
												serial.setCONFIRMQTY(String.valueOf(serialData.getCONFIRMQTY()));
												serial.setDPDOCLINE(String.valueOf(serialData.getDPDOCLINE()));
												serial.setDPDOCNO(String.valueOf(serialData.getDPDOCNO()));
												serial.setDPSLIPLINE(serialData.getDPSLIPLINE());
												serial.setDPSLIPNO(serialData.getDPSLIPNO());
												serial.setPLANT(serialData.getPLANT());
												serial.setPLANT(serialData.getPLANT());
												serial.setPOKEY(serialData.getPOKEY());
												serial.setPOLINE(serialData.getPOLINE());
												serial.setSKU(serialData.getSKU());
												serial.setSTOCKID(serialData.getSTOCKID());
												serial.setSTORAGELOC(serialData.getSTORAGELOC());
												serial.setUOM(serialData.getUOM());
												serial.setETCQTY(String.valueOf(serialData.getETCQTY()));

												mmData.getIFSTSTOCKSERIALINFOS().add(serial);
											}
										} catch (Exception e) {
											errorCode = -20001;
											errorMessage = "실적정보 유통이력정보 처리중 오류발생.";
										}
									}

									if(errorCode == 0) {
										DTSCM0330SCMResponse response = null;
										try {
											locator = eaiLocaterService.getLocater(BATCH_IF_ID);

											// JAX-WS Service & Port 생성
											SISCM0330SCMSOService service = new SISCM0330SCMSOService();
											SISCM0330SCMSO port = service.getPort(SISCM0330SCMSO.class);

											Map<String, Object> context = ((BindingProvider) port).getRequestContext();
											context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
											context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
											context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

											response = port.siSCM0330SCMSO(mmData);
											errorMessage = response.getTRETURN().get(0).getIFMEMO();
										} catch (Exception e) {
											log.info(e.getMessage());
											errorCode = -20001;
											errorMessage = e.getMessage();
										}

										// 응답에 대한 결과 처리
										if(response != null && !response.getTRETURN().isEmpty()) {
											if("E".equals(response.getTRETURN().get(0).getIFFLAG())) {
												errorCode = -20001;
												errorMessage = response.getTRETURN().get(0).getIFMEMO();
											}
										} else {
											errorCode = -20001;
											errorMessage = "실적자료 전송중 오류발생";
										}
									}
								}
							}
						} catch (Exception e) {
							log.info(e.getMessage());
						} finally {
							defaultParams.setSEARCH_IF_FLAG("P");
							if(errorCode < 0) {
								defaultParams.setUPDATE_IF_FLAG("E");
								defaultParams.setUPDATE_IF_MEMO(errorMessage);
								commonDao.update("scm0330Service.updateScm0330", defaultParams);
								commonDao.update("scm0330Service.updateSerailScm0330", defaultParams);
							} else {
								defaultParams.setUPDATE_IF_FLAG("Y");
								defaultParams.setUPDATE_IF_MEMO(errorMessage);
								commonDao.update("scm0330Service.updateScm0330", defaultParams);
								commonDao.update("scm0330Service.updateSerailScm0330", defaultParams);
							}
						}
					}

				}
			} catch (Exception e) {
				log.info(e.getMessage());
				//BATCH JOB LOG설정
				copyDto.setResultCode("-1");
				logSystemOut(copyDto, "INFO", logOption[4], "260", e.getMessage());
			}
		}

		//IF_MASTER설정
		if ( ifStaus.equals("END") ) {
			batchParam.setAVC_COMMAND("END");
			batchParam.setAVC_IFID(BATCH_IF_ID);
			batchParam.setOUT_SUCCESS(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_ERR_CODE(Integer.valueOf(copyDto.getResultCode()));
			batchParam.setOUT_RETURN_MSG(errorMessage);
			commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		} else {
			copyDto.setResultCode("-20001");
		}

		//BATCH JOB LOG설정
		logSystemOut(copyDto, "END", logOption[4], "277", "JAVA SERVICE END");
	}

    /*
    * @description : System.out.println ==> BATCH LOG기록으로 전환
	*/
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
    	if (!StringUtil.isEmpty(reqDto.getJobExecutionId()) && isPrint.equals("Y")) {
    		reqDto.setJobStatus(jobStatus);
    		reqDto.setLineNo(lineNo);
   			reqDto.setResultMsg(logMsg);
    		try {
				commonDao.selectOne("batchCommonService.insertBatchLog", reqDto);
			} catch (Exception e) {
				log.info(e.getMessage());
    		}
    	}
    }
}