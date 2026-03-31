package cjfw.batch.job.smsSendKko;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.common.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsSendKkoBatchService {

	private final CommonDao commonDao;

	@Transactional
	public void callSmsSendKkoJobService(BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto) throws JobExecutionException {
		//BATCH JOB LOG설정
		String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리 
		BatchLogParamsDto copyDto = new BatchLogParamsDto();
		if (batchLogDto != null) {
			BeanUtils.copyProperties(batchLogDto, copyDto);
			copyDto.setJobDiv("JAVA SERVICE");
			copyDto.setNodeLevel(batchLogDto.getNodeLevel()+1 );
			copyDto.setResultCode("0");
			logSystemOut(copyDto, "START", logOption[4], "44", "JAVA SERVICE START");
		}

        List<SmsSendKkoParamsDto> sendList = commonDao.selectList("smsSendKkoBatchService.selectSms");
        logSystemOut(copyDto, "INFO", logOption[4], "48", "SMS발송 대상 건수:"+sendList.size());
        int succCount = 0;
        int errCount = 0;
        if(!sendList.isEmpty()){
            for(SmsSendKkoParamsDto sendData : sendList){
                try {
                    //TO-BE 알림톡 에이전트로 SMS 발송(강제 실패건으로 우회)
                    int cnt = commonDao.insert("smsSendKkoBatchService.insertKkoMsg", sendData);

                    if(cnt > 0){
                        commonDao.update("smsSendKkoBatchService.updateSms", sendData);
                        succCount++;
                    }
                } catch (Exception e){
                    //BATCH JOB LOG설정
                    errCount++;
                    copyDto.setResultCode("-1");
                    logSystemOut(copyDto, "ERR", logOption[4], "65", e.toString());
                }
            }
            logSystemOut(copyDto, "INFO", logOption[4], "70", "성공건수:"+succCount + "건 실패건수:" + errCount + "건");
        }
		//BATCH JOB LOG설정
		logSystemOut(copyDto, "END", logOption[4], "72", "JAVA SERVICE END");
	}

    /*
    * @description : System.out.println ==> BATCH LOG기록으로 전환
	*/
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
    	if (!StringUtil.isEmpty(reqDto.getJobExecutionId())) {
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