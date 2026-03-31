package cjfw.batch.noneParamJob;


import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ExdcSendNoticeBatch {
    
	private static final String BATCH_JOB_NAME = "exdcSendNoticeJob";
	private final CommonDao commonDao;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job exdcSendNoticeJob() {
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
                        					.lineNo("84")
                        					.resultCode("0")
                        					.resultMsg("")
                        					.build();
			
			try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
                batchLogDto.setLineNo("89");
                Map<String, Object> paramMap = new java.util.HashMap<>();
                paramMap.put("AVC_IFID", "");
                paramMap.put("AVC_IFRESULT", "");  
                commonDao.selectOne("exdcSendNoticeService.sendNotice", paramMap);

			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");
				batchLogDto.setLineNo("97");
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
	

}