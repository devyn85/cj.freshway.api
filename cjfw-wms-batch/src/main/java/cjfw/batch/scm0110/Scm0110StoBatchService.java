package cjfw.batch.scm0110;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.scm0110.soap.DTSCM0110SCM;
import cjfw.batch.scm0110.soap.DTSCM0110SCMResponse;
import cjfw.batch.scm0110.soap.SISCM0110SCMSO;
import cjfw.batch.scm0110.soap.SISCM0110SCMSOService;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Scm0110StoBatchService {

	private static final String BATCH_IF_ID = "SCM0110"; //STO 센터 입고 실적(저장품일배포함) 송신
	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리
	public void callScm0100StoJobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callScm0100StoJobService(batchParam, null);
	}
	
	@Transactional
	public void callScm0100StoJobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			logSystemOut(copyDto, "START", logOption[4], "52", "JAVA SERVICE START");
		}

		int errorCode = 0;				//에러코드
		String errorMessage = null;		//에러메시지
		String ifStaus = null; 			//IF_MASTER 호출결과
		Map<String, String> locator = null;
		Scm0110ParamsDto inputParam = new Scm0110ParamsDto();

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "65", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
			try {
				List<Scm0110ParamsDto> sendList =  commonDao.selectList("scm0110Service.selectScm0110StoHeader");
				logSystemOut(copyDto, "INFO", logOption[4], "70", "[sqlId]selectScm0110StoHeader Rows:"+String.valueOf(sendList.size()));

				DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

				LocalDateTime now = LocalDateTime.now();
				String dayString  = now.format(dayFormatter);
				String timeString = now.format(timeFormatter);

				for(Scm0110ParamsDto sendData : sendList) {
					errorCode = 0;
					errorMessage = "";

					try {
						DTSCM0110SCM mmData = new DTSCM0110SCM();
						mmData.setXSYS("SCM");
						mmData.setXDATS(dayString);
						mmData.setXTIMS(timeString);
						mmData.setXROWS("1");
						List<DTSCM0110SCM.IFDMSENDDATAH> headerList = new ArrayList<DTSCM0110SCM.IFDMSENDDATAH>();
						DTSCM0110SCM.IFDMSENDDATAH header = new DTSCM0110SCM.IFDMSENDDATAH();
						String plant = null;
						try {
							plant = sendData.getPLANT();
							header.setDOCNO(sendData.getDOCNO());
							header.setCLOSECD(sendData.getCLOSECD());
							header.setEDITDATE(sendData.getEDITDATE());
							header.setEDITWHO(sendData.getEDITWHO());
							header.setEFFECTIVEDATE(sendData.getEFFECTIVEDATE());
							header.setPLANT(plant);
							header.setPOKEY(sendData.getPOKEY());
							header.setPOSTINGDATE(sendData.getPOSTINGDATE());
							headerList.add(header);

							mmData.setIFDMSENDDATAH(headerList);
						} catch (Exception e) {
							log.info(e.getMessage());
							errorMessage = e.getMessage();
						}

						if(errorCode == 0) {
							inputParam.setSEARCH_IF_FLAG("N");
							inputParam.setUPDATE_IF_FLAG("P");
							inputParam.setEDITDATE(sendData.getEDITDATE());
							inputParam.setIFTIMESTAMP(sendData.getIFTIMESTAMP());
							//inputParam.setIFTIMESTAMP("20241101001632493839");
							commonDao.update("scm0110Service.updateScm0110", inputParam);
							commonDao.update("scm0110Service.updateSerailScm0110", inputParam);

							inputParam.setDOCNO(sendData.getDOCNO());
							inputParam.setSLIPNO(sendData.getSLIPNO());
							inputParam.setPOKEY(sendData.getPOKEY());
							inputParam.setPOLINE(sendData.getPOLINE());
							inputParam.setORDERTYPE(sendData.getORDERTYPE());
							List<Scm0110ParamsDto> detailSearchList = commonDao.selectList("scm0110Service.selectScm0110Detail", inputParam);
							logSystemOut(copyDto, "INFO", logOption[4], "124", "[sqlId]selectScm0110Detail Rows:"+String.valueOf(detailSearchList.size()));
							List<Scm0110ParamsDto> serialSearchList = commonDao.selectList("scm0110Service.selectScm0110Serial", inputParam);
							logSystemOut(copyDto, "INFO", logOption[4], "126", "[sqlId]selectScm0110Serial Rows:"+String.valueOf(serialSearchList.size()));

							if(detailSearchList.isEmpty()) {
								errorCode = -20001;
								errorMessage = "주문실적 Detail 정보를 찾을 수 없습니다.";
							}

							if(serialSearchList.isEmpty() && "2170".equals(plant)) {
								errorCode = -20001;
								errorMessage = "유통이력 정보를 찾을 수 없습니다.";
							}

							if(errorCode == 0) {
								try {
									List<DTSCM0110SCM.IFDMSENDDATAD> detailList = new ArrayList<DTSCM0110SCM.IFDMSENDDATAD>();
									DTSCM0110SCM.IFDMSENDDATAD detail = new DTSCM0110SCM.IFDMSENDDATAD();
									for (Scm0110ParamsDto detailDto : detailSearchList) {
										detail.setPOKEY(detailDto.getPOKEY());
										detail.setPOLINE(detailDto.getPOLINE());
										detail.setDOCNO(detailDto.getDOCNO());
										detail.setDOCLINE(detailDto.getDOCLINE());
										detail.setSKU(detailDto.getSKU());
										detail.setSTORAGELOC(detailDto.getSTORAGELOC());
										detail.setSTOREROPENQTY(String.valueOf(detailDto.getSTOREROPENQTY()));
										detail.setCONFIRMQTY(String.valueOf(detailDto.getCONFIRMQTY()));
										detail.setSTORERCONFIRMQTY(String.valueOf(detailDto.getSTORERCONFIRMQTY()));
										detail.setSTORERCANCELQTY(String.valueOf(detailDto.getSTORERCANCELQTY()));

										if (detailDto.getREASONCODE2() != null) {
											detail.setREASONCODE2(detailDto.getREASONCODE2());
										}

										detail.setCONFIRMWEIGHT(String.valueOf(detailDto.getCONFIRMWEIGHT()));
										detail.setUOM(detailDto.getUOM());
										detail.setSTORERUOM(detailDto.getSTORERUOM());
										detail.setSTATUS(detailDto.getSTATUS());
										detail.setSLIPLINE(detailDto.getSLIPLINE());
										detail.setSLIPNO(detailDto.getSLIPNO());

										detailList.add(detail);
									}
									mmData.setIFDMSENDDATAD(detailList);
								} catch (Exception e) {
									log.info(e.getMessage());
									errorCode = -20001;
									errorMessage = "실적정보 Detail 처리중 오류발생.";
								}

								if(errorCode == 0) {
									try {
										List<DTSCM0110SCM.IFSTSTOCKSERIALINFOS> serialList = new ArrayList<DTSCM0110SCM.IFSTSTOCKSERIALINFOS>();
										DTSCM0110SCM.IFSTSTOCKSERIALINFOS serial = new DTSCM0110SCM.IFSTSTOCKSERIALINFOS();
										for(Scm0110ParamsDto serialDto : serialSearchList) {
											serial.setPOKEY(serialDto.getPOKEY());
											serial.setPOLINE(serialDto.getPOLINE());
											serial.setDPDOCNO(serialDto.getDPDOCNO());
											serial.setDPDOCLINE(serialDto.getDPDOCLINE());
											serial.setSTOCKID(serialDto.getSTOCKID());
											serial.setSKU(serialDto.getSKU());
											serial.setPLANT(serialDto.getPLANT());
											serial.setSTORAGELOC(serialDto.getSTORAGELOC());
											serial.setCONVSERIALNO(serialDto.getCONVSERIALNO());
											serial.setETCQTY(String.valueOf(serialDto.getETCQTY()));

											if(!"3".equals(serialDto.getSERIALTYPE())) {
												serial.setSERIALNO(serialDto.getSERIALNO());
											}

											serial.setCONFIRMQTY(String.valueOf(serialDto.getCONFIRMQTY()));
											serial.setCANCELQTY(String.valueOf(serialDto.getCANCELQTY()));
											serial.setUOM(serialDto.getUOM());

											if("RP".equals(inputParam.getORDERTYPE())) {
												serial.setDPSLIPNO(serialDto.getDPPOKEY());
												serial.setDPSLIPLINE(serialDto.getDPPOLINE());
											} else {
												serial.setDPSLIPNO(serialDto.getDPSLIPNO());
												serial.setDPSLIPLINE(serialDto.getDPSLIPLINE());
											}
											serialList.add(serial);
										}
										mmData.setIFSTSTOCKSERIALINFOS(serialList);
									} catch (Exception e) {
										throw new RuntimeException(e);
									}
								}

								if(errorCode == 0) {
									DTSCM0110SCMResponse response = null;
									try {
										locator = eaiLocaterService.getLocater(BATCH_IF_ID);

										// JAX-WS Service & Port 생성
										SISCM0110SCMSOService service = new SISCM0110SCMSOService();
										SISCM0110SCMSO port = service.getPort(SISCM0110SCMSO.class);

										Map<String, Object> context = ((BindingProvider) port).getRequestContext();
										context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
										context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
										context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

										response = port.siSCM0110SCMSO(mmData);
									} catch (Exception e) {
										log.info(e.getMessage());
										errorMessage = e.getMessage();
									}

									// 응답에 대한 결과 처리.
									if(response != null && !CollectionUtils.isEmpty(response.getTRETURN())) {
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
						errorMessage = e.getMessage();
					} finally {
						inputParam.setSEARCH_IF_FLAG("P");
						if(errorCode < 0) {
							inputParam.setUPDATE_IF_FLAG("E");
							inputParam.setUPDATE_IF_MEMO(errorMessage);
							commonDao.update("scm0110Service.updateScm0110", inputParam);
							commonDao.update("scm0110Service.updateSerailScm0110", inputParam);
						} else {
							inputParam.setUPDATE_IF_FLAG("Y");
							inputParam.setUPDATE_IF_MEMO(errorMessage);
							commonDao.update("scm0110Service.updateScm0110", inputParam);
							commonDao.update("scm0110Service.updateSerailScm0110", inputParam);
						}
					}
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				errorMessage = e.getMessage();
				//BATCH JOB LOG설정
				copyDto.setResultCode("-1");
				logSystemOut(copyDto, "INFO", logOption[4], "269", e.getMessage());
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
		logSystemOut(copyDto, "END", logOption[4], "286", "JAVA SERVICE END");
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