package cjfw.batch.scm0060;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.scm0060.soap.DTSCM0060SCM;
import cjfw.batch.scm0060.soap.DTSCM0060SCMResponse;
import cjfw.batch.scm0060.soap.SISCM0060SCMSO;
import cjfw.batch.scm0060.soap.SISCM0060SCMSOService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Scm0060BatchService {

	private static final String BATCH_IF_ID = "SCM0060"; //SO입,출고 실적 송신 
	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리 (기존 호출 대응용도-메서드 오버로딩)
	public void callScm0060JobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callScm0060JobService(batchParam, null);
	}

	@Transactional
	public void callScm0060JobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			logSystemOut(copyDto, "START", logOption[4], "51", "JAVA SERVICE START");
		}

		Scm0060ParamsDto defaultParam = new Scm0060ParamsDto();
		Map<String, String> locator = null;

		int errorCode = 0;
		String errorMessage = null;
		String ifStaus = null; //IF_MASTER 호출결과

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "65", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
			try {
				DTSCM0060SCM mmData = null;
				List<DTSCM0060SCM.IFDMSENDDATA> dataSearchList =  commonDao.selectList("scm0060Service.selectScm0060");
				logSystemOut(copyDto, "INFO", logOption[4], "70", "[sqlId]selectScm0060 Rows:"+String.valueOf(dataSearchList.size()));

				if(dataSearchList != null && !dataSearchList.isEmpty()) {

					List<DTSCM0060SCM.IFDMSENDDATA> dataList = new ArrayList<DTSCM0060SCM.IFDMSENDDATA>();


					DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
					DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

					LocalDateTime now = LocalDateTime.now();
					String dayString  = now.format(dayFormatter);
					String timeString = now.format(timeFormatter);


					try {
						mmData = new DTSCM0060SCM();


						mmData.setXSYS("SCM");
						mmData.setXDATS(dayString);
						mmData.setXTIMS(timeString);
						mmData.setXROWS(String.valueOf(dataSearchList.size()));

						for(DTSCM0060SCM.IFDMSENDDATA selectData: dataSearchList) {
                            DTSCM0060SCM.IFDMSENDDATA data = new DTSCM0060SCM.IFDMSENDDATA();
							try {
								// 조회 조건 설정
								defaultParam.setDOCTYPE(selectData.getDOCTYPE());
								defaultParam.setDOCNO(selectData.getDOCNO());
								defaultParam.setDOCDT(selectData.getDOCDT());
								defaultParam.setDOCLINE(selectData.getDOCLINE());
								defaultParam.setSLIPNO(selectData.getSLIPNO());
								defaultParam.setSLIPDT(selectData.getSLIPDT());
								defaultParam.setSLIPLINE(selectData.getSLIPLINE());

								// 처리대상 IF_FLAG 처리.
								defaultParam.setSEARCH_IF_FLAG("N");
								defaultParam.setUPDATE_IF_FLAG("P");
								defaultParam.setSERIALKEY(selectData.getSERIALKEY());
								defaultParam.setALL_FLAG("N");
								defaultParam.setSERIAL_FLAG("N");

								this.updateSataStatus(defaultParam);

								data.setCHANNEL(selectData.getCHANNEL());
								data.setCLOSECD(selectData.getCLOSECD());
								data.setCONFIRMQTY(selectData.getCONFIRMQTY());
								data.setCONFIRMWEIGHT(selectData.getCONFIRMWEIGHT());
								data.setDELYN(selectData.getDELYN());
								data.setDOCDT(selectData.getDOCDT());
								data.setDOCLINE(selectData.getDOCLINE());
								data.setDOCNO(selectData.getDOCNO());
								data.setDOCTYPE(selectData.getDOCTYPE());
								data.setEDITDATE(selectData.getEDITDATE());
								data.setEDITWHO(selectData.getEDITWHO());
								data.setPLANT(selectData.getPLANT());
								if(selectData.getPOKEY() != null) {
									data.setPOKEY(selectData.getPOKEY());
								}
								if(selectData.getPOLINE() != null) {
									data.setPOLINE(selectData.getPOLINE());
								}
								if(selectData.getREASONCODE2() != null) {
									data.setREASONCODE2(selectData.getREASONCODE2());
								}
								if(selectData.getREASONMSG2() != null) {
									data.setREASONMSG2(selectData.getREASONMSG2());
								}
								data.setSERIALKEY(selectData.getSERIALKEY());
								data.setDCCODE(selectData.getDCCODE());
								data.setSKU(selectData.getSKU());
								data.setSLIPDT(selectData.getSLIPDT());
								data.setSLIPLINE(selectData.getSLIPLINE());
								data.setSLIPNO(selectData.getSLIPNO());
								data.setSTOCKGRADE(selectData.getSTOCKGRADE());
								data.setSTORAGELOC(selectData.getSTORAGELOC());
								data.setSTORERCANCELQTY(selectData.getSTORERCANCELQTY());
								data.setSTORERCONFIRMQTY(selectData.getSTORERCONFIRMQTY());
								data.setSTOREROPENQTY(selectData.getSTOREROPENQTY());
								data.setSTORERUOM(selectData.getSTORERUOM());
								data.setUOM(selectData.getUOM());

								dataList.add(data);
							} catch (Exception e) {
								//log.info(e.getMessage());
								errorCode = -20001;
								errorMessage = "실적처리중 오류발생";
							}
						}

						mmData.setIFDMSENDDATA(dataList);

						List<DTSCM0060SCM.IFSTSTOCKSERIALINFOS> serialSearchList = commonDao.selectList("scm0060Service.selectScm0060Serial");
						logSystemOut(copyDto, "INFO", logOption[4], "165", "[sqlId]selectScm0060Serial Rows:"+String.valueOf(serialSearchList.size()));

						if(!serialSearchList.isEmpty()) {
							List<DTSCM0060SCM.IFSTSTOCKSERIALINFOS> serialList = new ArrayList<DTSCM0060SCM.IFSTSTOCKSERIALINFOS>();

							try{
								for(DTSCM0060SCM.IFSTSTOCKSERIALINFOS serialSearchDta : serialSearchList ) {
                                    DTSCM0060SCM.IFSTSTOCKSERIALINFOS serial = new DTSCM0060SCM.IFSTSTOCKSERIALINFOS();

									defaultParam.setSERIAL_FLAG("Y");
									defaultParam.setSERIALKEY(serialSearchDta.getSERIALKEY());
									this.updateSataStatus(defaultParam);

									serial.setSERIALKEY(serialSearchDta.getSERIALKEY());
									serial.setWDDOCNO(serialSearchDta.getWDDOCNO());
									serial.setWDDOCLINE(serialSearchDta.getWDDOCLINE());
									serial.setWDDOCDT(serialSearchDta.getWDDOCDT());

									if("3".equals(serialSearchDta.getSERIALKEY())) {
										serial.setSERIALNO("");
									} else {
										serial.setSERIALNO(serialSearchDta.getSERIALNO());
									}
									serial.setPOKEY(serialSearchDta.getPOKEY());
									serial.setPOLINE(serialSearchDta.getPOLINE());
									serial.setCONVSERIALNO(serialSearchDta.getCONVSERIALNO());
									serial.setSHIPPEDQTY(serialSearchDta.getSHIPPEDQTY());
									serial.setEDITDATE(serialSearchDta.getEDITDATE());
									serial.setEDITWHO(serialSearchDta.getEDITWHO());
									serial.setETCQTY(serialSearchDta.getETCQTY());
									serial.setSTORAGEAMOUNT(serialSearchDta.getSTORAGEAMOUNT());
									serial.setETCAMOUNT(serialSearchDta.getETCAMOUNT());

									serialList.add(serial);
								}
								mmData.setIFSTSTOCKSERIALINFOS(serialList);
							} catch (Exception e) {
								errorCode = -20001;
								errorMessage = "실적정보 유통이력정보 처리중 오류발생.";
							} finally {
								//BATCH JOB LOG설정
								copyDto.setResultCode(String.valueOf(errorCode));
								logSystemOut(copyDto, "INFO", logOption[4], "206", "IFSTSTOCKSERIALINFOS RESULT:"+errorMessage);
							}
						}

						// 에러가 없을 경우
						if(errorCode == 0) {
							DTSCM0060SCMResponse response = null;
							try {
								locator = eaiLocaterService.getLocater(BATCH_IF_ID);

								// JAX-WS Service & Port 생성
								SISCM0060SCMSOService service = new SISCM0060SCMSOService();
								SISCM0060SCMSO port = service.getPort(SISCM0060SCMSO.class);

								Map<String, Object> context = ((BindingProvider) port).getRequestContext();
								context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
								context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
								context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

								response = port.siSCM0060SCMSO(mmData);
								errorMessage = response.getXMSGS();
							} catch (Exception e) {
								errorCode = -20001;
								errorMessage = e.getMessage();
							} finally {
								//BATCH JOB LOG설정
								copyDto.setResultCode(String.valueOf(errorCode));
								logSystemOut(copyDto, "INFO", logOption[4], "233", "SISCM0060SCMSOService RESULT:"+errorMessage);
							}

							// 응답에 대한 결과 처리
							if(response != null) {
								if("E".equals(response.getXSTAT())) {
									errorCode = -20001;
									errorMessage = response.getXMSGS();
								}
							} else {
								errorCode = -20001;
								errorMessage = "실적자료 전송중 오류발생";
							}
						}
					} catch (Exception e) {
                        errorCode = -20001;
                        errorMessage = e.getMessage();
					} finally {
						defaultParam.setSEARCH_IF_FLAG("P");
						defaultParam.setALL_FLAG("Y");
						if(errorCode < 0 ){
							defaultParam.setUPDATE_IF_FLAG("E");
							defaultParam.setUPDATE_IF_MEMO(errorMessage);
						} else {
							defaultParam.setUPDATE_IF_FLAG("Y");
							defaultParam.setUPDATE_IF_MEMO(errorMessage);
						}

						defaultParam.setSERIAL_FLAG("N");
						this.updateSataStatus(defaultParam);

						defaultParam.setSERIAL_FLAG("Y");
						this.updateSataStatus(defaultParam);
					}
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				errorMessage = e.getMessage();
				//BATCH JOB LOG설정
				copyDto.setResultCode("-1");
				logSystemOut(copyDto, "INFO", logOption[4], "273", e.getMessage());
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
		logSystemOut(copyDto, "END", logOption[4], "290", "JAVA SERVICE END");
	}

	public void updateSataStatus(Scm0060ParamsDto defaultParam) {
		String allFlag = defaultParam.getALL_FLAG();
		String serialFlag = defaultParam.getSERIAL_FLAG();

		if("Y".equals(allFlag)) {
			if("Y".equals(serialFlag)) {
				commonDao.update("scm0060Service.updateSerailScm0060All", defaultParam);
			} else {
				commonDao.update("scm0060Service.updateScm0060All", defaultParam);
			}
		} else {
			if("Y".equals(serialFlag)) {
				commonDao.update("scm0060Service.updateSerailScm0060", defaultParam);
			} else {
				commonDao.update("scm0060Service.updateScm0060", defaultParam);
			}
		}
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