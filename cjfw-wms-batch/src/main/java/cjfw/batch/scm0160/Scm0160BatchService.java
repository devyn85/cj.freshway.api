package cjfw.batch.scm0160;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.scm0160.soap.DTSCM0160SCM;
import cjfw.batch.scm0160.soap.DTSCM0160SCMResponse;
import cjfw.batch.scm0160.soap.SISCM0160SCMSO;
import cjfw.batch.scm0160.soap.SISCM0160SCMSOService;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import com.sun.xml.ws.client.ClientTransportException;
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
public class Scm0160BatchService {

	private static final String BATCH_IF_ID = "SCM0160";
	private final CommonDao commonDao;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리 (기존 호출 대응용도-메서드 오버로딩)
	public void callScm0160JobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callScm0160JobService(batchParam, null);
	}

	@Transactional
	public void callScm0160JobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			copyDto.setResultCode("0");
			logSystemOut(copyDto, "START", logOption[4], "54", "JAVA SERVICE START");
		}

		DTSCM0160SCM mmData = null;
		Map<String, String> locator = null;
		logSystemOut(copyDto, "INFO", logOption[4], "59", "dummy check.010");
		int errorCode = 0;
		String errorMessage = null;
		String ifStaus = null; //IF_MASTER 호출결과
		
		try {
			DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

			LocalDateTime now = LocalDateTime.now();
			String dayString  = now.format(dayFormatter);
			String timeString = now.format(timeFormatter);

			batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
			batchParam.setAVC_IFID(BATCH_IF_ID);
			commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
			ifStaus = batchParam.getOUT_RETURN_MSG();
			logSystemOut(copyDto, "INFO", logOption[4], "76", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
			if( ifStaus.equals("END") ) {
				try {
					List<DTSCM0160SCM.IFDMDOCUMENTH> headerList = commonDao.selectList("scm0160Service.selectScm0160Header", batchParam);
					for(DTSCM0160SCM.IFDMDOCUMENTH headerSendData : headerList) {
                        List<DTSCM0160SCM.IFDMDOCUMENTD> details = new ArrayList<>();
						try {
							Scm0160ParamsDto updateParam = new Scm0160ParamsDto();
							updateParam.setSEARCH_IF_FLAG("N");
							updateParam.setUPDATE_IF_FLAG("P");
							updateParam.setUPDATE_IF_MEMO("");
							updateParam.setDOCNO(headerSendData.getDOCNO());
							updateParam.setIF_ID(batchParam.getAVC_IFID());
							commonDao.update("scm0160Service.updateScm0160Header", updateParam);
							commonDao.update("scm0160Service.updateScm0160Detail", updateParam);

							mmData = new DTSCM0160SCM();
							mmData.setXSYS("WMS");
							mmData.setXDATS(dayString);
							mmData.setXTIMS(timeString);
							mmData.setXROWS("1");
							logSystemOut(copyDto, "INFO", logOption[4], "97", "dummy check.030");
							try {
                                DTSCM0160SCM.IFDMDOCUMENTH header = new DTSCM0160SCM.IFDMDOCUMENTH();
								header.setORDERTYPE(headerSendData.getORDERTYPE());
								header.setDCCODE(headerSendData.getDCCODE());
								header.setDOCNO(headerSendData.getDOCNO());
                                header.setFROMBILLTOKEY("FW00");
                                header.setSTORERKEY(headerSendData.getSTORERKEY());
								header.setSHOPPINGMALL("Z01");

								mmData.setIFDMDOCUMENTH(header);
							} catch (Exception e) {
								//log.info(e.toString())
								errorCode = -20001;
								errorMessage = "실적처리중 오류발생: ";
								logSystemOut(copyDto, "INFO", logOption[4], "112", errorMessage+e.toString());
							}

							if(errorCode == 0) {
								// Detail 조회 조건 설정
                                Scm0160ParamsDto detailParam = new Scm0160ParamsDto();
                                detailParam.setIF_ID(batchParam.getAVC_IFID());
                                detailParam.setDOCNO(headerSendData.getDOCNO());
                                detailParam.setSEARCH_IF_FLAG(updateParam.getSEARCH_IF_FLAG());
								List<DTSCM0160SCM.IFDMDOCUMENTD> detailList = commonDao.selectList("scm0160Service.selectScm0160Detail", detailParam);

								if(CollectionUtils.isEmpty(detailList)) {
									errorCode = -20001;
									errorMessage = "STO예정 문서 Detail 정보를 찾을 수 없습니다.";
								}
								logSystemOut(copyDto, "INFO", logOption[4], "127", "dummy check.040");
								if(errorCode == 0) {
									try {
										for(DTSCM0160SCM.IFDMDOCUMENTD detailSendData : detailList) {
                                            DTSCM0160SCM.IFDMDOCUMENTD detail = new DTSCM0160SCM.IFDMDOCUMENTD();
											detail.setDOCNO(detailSendData.getDOCNO());
											detail.setDOCLINE(detailSendData.getDOCLINE());
											detail.setDELYN(detailSendData.getDELYN());
											detail.setPLANT(detailSendData.getPLANT());
											detail.setSTORAGELOC(detailSendData.getSTORAGELOC());
											detail.setSKU(detailSendData.getSKU());
											detail.setSTORERORDERQTY(detailSendData.getSTORERORDERQTY());
											detail.setSTORERUOM(detailSendData.getSTORERUOM());
											detail.setDELIVERYDATE(detailSendData.getDELIVERYDATE());
											if (detailSendData.getOTHER01() != null && !detailSendData.getOTHER01().isEmpty()) {
												detail.setOTHER01(detailSendData.getOTHER01());
											} else {
												detail.setOTHER01("");
											}
											if (detailSendData.getOTHER02() != null && !detailSendData.getOTHER02().isEmpty()) {
												detail.setOTHER02(detailSendData.getOTHER02());
											} else {
												detail.setOTHER02("");
											}

											details.add(detail);
											mmData.setIFDMDOCUMENTD(details);
										}
									} catch (Exception e) {
										//log.info(e.toString())
										errorCode = -20001;
										errorMessage = "실적정보 Detail 처리중 오류발생. ";
										logSystemOut(copyDto, "INFO", logOption[4], "159", errorMessage+e.toString());
									}
									logSystemOut(copyDto, "INFO", logOption[4], "161", "dummy check.050");
									String xmsgs = "";
                                    String soapEndpoint = "";
									if(errorCode == 0) {
										DTSCM0160SCMResponse response = null;
										try {
											locator = eaiLocaterService.getLocater(batchParam.getAVC_IFID());

											// JAX-WS Service & Port 생성
											SISCM0160SCMSOService service = new SISCM0160SCMSOService();
											SISCM0160SCMSO port = service.getPort(SISCM0160SCMSO.class);

											Map<String, Object> context = ((BindingProvider) port).getRequestContext();
											context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
											context.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
											context.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

                                            BindingProvider bp = (BindingProvider) port;
                                            Map<String, Object> ctx = bp.getRequestContext();
                                            ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, locator.get("endpoint"));
                                            ctx.put(BindingProvider.USERNAME_PROPERTY, locator.get("username"));
                                            ctx.put(BindingProvider.PASSWORD_PROPERTY, locator.get("password"));

                                            // (권장) 타임아웃도 반드시 설정 (ms)
                                            ctx.put("jakarta.xml.ws.client.connectionTimeout", "10000");
                                            ctx.put("jakarta.xml.ws.client.receiveTimeout", "60000");

                                            BindingProvider bpp = (BindingProvider) port;
                                            soapEndpoint = (String) bpp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);

                                            response = port.siSCM0160SCMSO(mmData);
											errorMessage = xmsgs;
										} catch (jakarta.xml.ws.soap.SOAPFaultException sfe) {
                                            errorCode = -20001;
                                            errorMessage = sfe.getMessage();
                                            logSystemOut(copyDto, "INFO", logOption[4], "196", errorMessage );
                                        } catch (jakarta.xml.ws.WebServiceException wse) {
                                            // 연결/SSL/타임아웃/HTTP 프로토콜 이슈 대부분 여기로 떨어짐
                                            errorCode = -20001;
                                            errorMessage = wse.getMessage();
                                        } catch (Exception e) {
											//log.info(e.toString())
											errorCode = -20001;
											errorMessage = e.getMessage();
											logSystemOut(copyDto, "INFO", logOption[4], "205", errorMessage+e.toString());
										}
										// 응답에 대한 결과 처리
										if(response != null && !CollectionUtils.isEmpty(response.getTRETURN())) {
											if("E".equals(response.getTRETURN().get(0).getXSTAT())) {
												errorCode = -20001;
												errorMessage = response.getTRETURN().get(0).getXMSGS();
											}
                                        } else {
                                            errorCode = -20001;
                                            errorMessage = "실적자료 전송중 오류발생.";
                                        }
                                        logSystemOut(copyDto, "INFO", logOption[4], "217", errorMessage);
									}
								}
							}
						} catch (Exception e) {
							logSystemOut(copyDto, "ERR", logOption[4], "222", e.getMessage()+e.toString());
						} finally {
                            // 처리결과 업데이트
                            Scm0160ParamsDto updateParam = new Scm0160ParamsDto();
                            updateParam.setIF_ID("SCM0160");
                            updateParam.setDOCNO(headerSendData.getDOCNO());
                            updateParam.setSEARCH_IF_FLAG("P");

                            logSystemOut(copyDto, "INFO", logOption[4], "230", "dummy check.60");
                            if(errorCode < 0) {
                                updateParam.setUPDATE_IF_FLAG("E");
                                updateParam.setUPDATE_IF_MEMO(errorMessage);
                                commonDao.update("scm0160Service.updateScm0160Header", updateParam);
                                commonDao.update("scm0160Service.updateScm0160Detail", updateParam);
                            } else {
                                updateParam.setUPDATE_IF_FLAG("Y");
                                updateParam.setUPDATE_IF_MEMO(errorMessage);
                                commonDao.update("scm0160Service.updateScm0160Header", updateParam);
                                commonDao.update("scm0160Service.updateScm0160Detail", updateParam);
                            }
                        }
					}
				} catch (Exception e) {
					logSystemOut(copyDto, "INFO", logOption[4], "245", e.getMessage()+e.toString());
				}
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			//BATCH JOB LOG설정
			copyDto.setResultCode("-1");
			logSystemOut(copyDto, "INFO", logOption[4], "252", e.toString());
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
		logSystemOut(copyDto, "END", logOption[4], "268", "JAVA SERVICE END");
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