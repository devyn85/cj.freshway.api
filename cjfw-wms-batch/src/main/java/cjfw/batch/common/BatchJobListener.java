package cjfw.batch.common;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Log4j2
@Component
public class BatchJobListener implements JobExecutionListener {

	@Autowired
	private CommonDao commonDao;

    private final SetParamService setParamService;

    public BatchJobListener(SetParamService setParamService) {
        this.setParamService = setParamService;
    }

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("--beforeJob ");
		System.out.println("--beforeJob");

        //JOB수행상태 UDPATE
        String jobName = jobExecution.getJobParameters().getString("jobName");
        setParamService.saveBatchStatus(jobName,"STARTED");

		BatchParamsUtil param = new BatchParamsUtil();
		param.setPGMID(jobName);
		param.setSTAT("0");
		commonDao.insert("quartz.insertBatchStartLog", param);
		jobExecution.getExecutionContext().put("exeNo", param.getEXENO());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("--afterJob");
		System.out.println("--afterJob");
		String exeNo = jobExecution.getExecutionContext().getString("exeNo");
		String jobName = jobExecution.getJobInstance().getJobName();

		BatchParamsUtil param = new BatchParamsUtil();
		param.setPGMID(jobName);
		param.setSTAT("1");
		param.setEXENO(exeNo);
		param.setMSG(jobName + "Complete");

		if (jobExecution.getStatus() == BatchStatus.FAILED) {
			log.info("{} 잡이 실패했습니다~ ", jobName);
			System.out.println("{} 잡이 실패했습니다~ " +jobName);

			// 예외 메시지 추출
			String errorMsg = jobExecution.getAllFailureExceptions().stream()
					.map(Throwable::getMessage)
					.collect(Collectors.joining(" | "));

			// 파라미터 세팅
			param.setSTAT("0");
			param.setMSG(errorMsg); // ← 예외 메시지 설정
		}

        //JOB 종료상태 UPDATE.
        setParamService.saveBatchStatus(jobName, String.valueOf(jobExecution.getStatus()));

        // 쿼츠 로그 저장.
		commonDao.update("quartz.updateBatchEndLog", param);
	}
}