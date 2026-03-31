package cjfw.batch.job;


import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultDataSendBatch {

    private static final String BATCH_JOB_NAME = "resultDataSendJob";
	private final CommonDao commonDao;

	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job resultDataSendJob() {
		return new JobBuilder(BATCH_JOB_NAME, jobRepository)
				.listener(batchJobListener)
				.flow(execStep())
				.end()
				.build();
	}

	@Bean
	@StepScope
	public Step execStep() {
		return new StepBuilder("execStep", jobRepository)
				.tasklet(execTasklet(), transactionManager)
				.build();
	}

	public Tasklet execTasklet() {
		return (contribution, chunkContext) -> {
			// 배치수행로그
			BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
					.jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
					.jobName(BATCH_JOB_NAME)
					.jobDiv("JAVA")
					.nodeLevel(0)
					.jobStatus("START")
					.command("")
					.lineNo("75")
					.resultCode("0")
					.resultMsg("")
					.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

				// 배치 파라미터 조회.
				Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
				BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

				// IF_MASTER 테이블에 전송상태 조회
				batchParam.setIfId("RSLT000");
				String ifSendFlg = commonDao.selectOne("batchCommonService.getIfSendFlag", batchParam);

				if("N".equals(ifSendFlg)) {
					// 다음 주기가 돌지 않도록 설정
					batchParam.setDelYn("Y");
					commonDao.update("batchCommonService.updateIfSendFlag", batchParam);

					// 실적 처리
					try {
						commonDao.selectOne("resultDataSendService.procIFSendResultMain", batchParam);
					} catch (Exception e) {
						// 에러코드 및 메시지 설정
						StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
						stepExecution.setExitStatus(new ExitStatus("FAILED", "에러 발생: " + e.getMessage()));
					} finally {
						// 다음 주기가 돌도록 설정
						batchParam.setDelYn("N");
						commonDao.update("batchCommonService.updateIfSendFlag", batchParam);
					}
				}
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");	
				batchLogDto.setLineNo("116");	
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}