package cjfw.batch.scm0320;

import cjfw.batch.common.EaiLocaterService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.common.dto.EaiProperties;
import cjfw.batch.scm0320.saop.DTSCM0320SCM;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Scm0320BatchService {

	private static final String BATCH_IF_ID = "SCM0320";
	private final CommonDao commonDao;

	//private final EaiProperties props;

	private final EaiLocaterService eaiLocaterService;

	// 서비스/다른 곳에서 직접 호출할 수 있도록 로직 분리 (기존 호출 대응용도-메서드 오버로딩)
	public void callScm0320JobService(BatchParamsUtil batchParam) throws JobExecutionException {
		callScm0320JobService(batchParam, null);
	}
	
	@Transactional
	public void callScm0320JobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setCommand(BATCH_IF_ID);
			copyDto.setResultCode("0");
			logSystemOut(copyDto, "START", logOption[4], "49", "JAVA SERVICE START");
		}
		
		Scm0320ParamsReqDto defaultParams = new Scm0320ParamsReqDto();
		Map<String, String> locater = null;
		String errorMessage = null;		//에러메시지
		String ifStaus = null; 			//IF_MASTER 호출결과
		int cntRows = 0;

		List<DTSCM0320SCM.ITEM> mmDataList = null;
		DTSCM0320SCM.ITEM mmData = null;

		batchParam.setAVC_COMMAND("START");	// CHECK(*1)후 START()방식 또는 직접 START(*END)방식 모두 가능함.
		batchParam.setAVC_IFID(BATCH_IF_ID);
		commonDao.selectOne("batchCommonService.procIFStatus", batchParam);
		ifStaus = batchParam.getOUT_RETURN_MSG();
		logSystemOut(copyDto, "INFO", logOption[4], "69", "IF_MASTER SET START RESULT:"+ (ifStaus.equals("END")?"OK":ifStaus));
		if( ifStaus.equals("END") ) {
			try {
				List<Scm0320ParamsDto> searchList =  commonDao.selectList("scm0320Service.selectScm0320");
				logSystemOut(copyDto, "INFO", logOption[4], "73", "[sqlId]selectScm0320 Rows:"+String.valueOf(searchList.size()));

				if(searchList != null) {
					locater = eaiLocaterService.getLocater(BATCH_IF_ID);
					int index = 0;
					for(Scm0320ParamsDto searchData : searchList) {
						errorMessage = "";

						// 처리대상 IF_FLAG 처리
						defaultParams.setSEARCH_IF_FLAG("N");
						defaultParams.setUPDATE_IF_FLAG("P");
						//defaultParams.setEDITDATE(searchData.getEDITDATE())
						defaultParams.setDOCNO(searchData.getDOCNO());
						defaultParams.setDOCLINE(searchData.getDOCLINE());
						defaultParams.setIFTIMESTAMP(searchData.getIFTIMESTAMP()); //(*20251209) 파라미터 누락항목 추가함
						cntRows = commonDao.update("scm0320Service.updateScm0320", defaultParams);
						logSystemOut(copyDto, "INFO", logOption[4], "94", "[sqlId]updateScm0320 Rows:"+String.valueOf(cntRows));

						// 실적데이터 설정
						mmData = new DTSCM0320SCM.ITEM();
						mmData.setDOCNO(searchData.getDOCNO());
						mmData.setDOCLINE(searchData.getDOCLINE());
						mmData.setPLANT(searchData.getPLANT());
						mmData.setSTOCKTRANSTYPE(searchData.getSTOCKTRANSTYPE());
						mmData.setEFFECTIVEDATE(searchData.getEFFECTIVEDATE());
						mmData.setPOSTINGDATE(searchData.getPOSTINGDATE());
						mmData.setSTORAGELOC(searchData.getSTORAGELOC());
						mmData.setSKU(searchData.getSKU());
						mmData.setCONFIRMQTY(String.valueOf(searchData.getCONFIRMQTY()));
						mmData.setUOM(searchData.getUOM());
						mmData.setREASONCODE2("");
						mmData.setREFERENCE05(searchData.getREFERENCE05());
						if(searchData.getPOKEY() != null) {
							mmData.setPOKEY(searchData.getPOKEY());
						} else {
							mmData.setPOKEY("");
						}

						if(searchData.getOTHER03() != null) {
							mmData.setOTHER03(searchData.getOTHER03());
						} else {
							mmData.setOTHER03("");
						}
						mmData.setCLOSECD(searchData.getCLOSECD());
						if(searchData.getREFERENCE07() != null) {
							mmData.setREFERENCE07(searchData.getREFERENCE07());
						} else {
							mmData.setREFERENCE07("");
						}
						if(searchData.getPOLINE() != null) {
							mmData.setPOLINE(searchData.getPOLINE());
						} else {
							mmData.setPOLINE("");
						}

						if(searchData.getREFERENCE08() != null){
							if(!"3".equals(searchData.getSERIALTYPE())) {
								mmData.setREFERENCE08(searchData.getREFERENCE08());
							} else {
								mmData.setREFERENCE08("");
							}
						} else {
							mmData.setREFERENCE08("");
						}
						if(searchData.getCUSTKEY() != null) {
							mmData.setCUSTKEY(searchData.getCUSTKEY());
						} else {
							mmData.setCUSTKEY("");
						}

						mmData.setSTOCKAMOUNT(searchData.getSTOCK_AMOUNT());
						mmData.setETCAMOUNT(searchData.getETC_AMOUNT());
						mmData.setSLIPNO(searchData.getSLIPNO());
						mmData.setSLIPLINE(searchData.getSLIPLINE());
						mmData.setIFTIMESTAMP(searchData.getIFTIMESTAMP());

						// 전송해야 하는 실적data가 500건이상일 경우 처리
						if(searchList.size() > 500) {
							if(mmDataList != null) {
								mmDataList.add(mmData);
								index++;

								// 500건 마다 전송
								if(index % 500 == 0) {
									ProcessScm0320Batch sendThread = new ProcessScm0320Batch(commonDao, mmDataList, locater);
									logSystemOut(copyDto, "INFO", logOption[4], "163", "BEFORE:ProcessScm0320Batch:sendThread.start()");
									sendThread.start();
									logSystemOut(copyDto, "INFO", logOption[4], "165", "AFTER:ProcessScm0320Batch:sendThread.start()");

									// 데이터 초기화.
									index = 0;
									mmDataList = null;
								}
							}
						} else {
							mmDataList = new ArrayList<>();
							mmDataList.add(mmData);
						}
					}

					// 마지막일때 처리
					int cnt = searchList.size();
					if(cnt > 0 && ((cnt > 500 && index >0) || cnt < 500)) {
						ProcessScm0320Batch sendThread = new ProcessScm0320Batch(commonDao, mmDataList, locater);
						logSystemOut(copyDto, "INFO", logOption[4], "182", "BEFORE:ProcessScm0320Batch:sendThread.run()");
						sendThread.run();
						logSystemOut(copyDto, "INFO", logOption[4], "184", "AFTER:ProcessScm0320Batch:sendThread.run()");

						// 1분까지 처리를 기다린다.
						long time = index * 500L;
						if(index > 60) {
							time = 3000L;
						}
						Thread.sleep(time);
					}
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				errorMessage = e.getMessage();
				//BATCH JOB LOG설정
				copyDto.setResultCode("-1");
				logSystemOut(copyDto, "INFO", logOption[4], "199", errorMessage);
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
		logSystemOut(copyDto, "END", logOption[4], "216", "JAVA SERVICE END");
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