package cjfw.batch.job;


import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KxStorageFeeBatch {

    private static final String BATCH_JOB_NAME = "kxStorageFeeJob";
	private final CommonDao commonDao;

	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job kxStorageFeeJob() {
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
			String rtnMsg = "...";
			// 배치수행로그
			BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
					.jobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()))
					.jobName(BATCH_JOB_NAME)
					.jobDiv("JAVA")
					.nodeLevel(0)
					.jobStatus("START")
					.command("")
					.lineNo("74")
					.resultCode("0")
					.resultMsg("")
					.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

				// 배치 파라미터 조회.
				Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
				BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

	            //트리거대체 TRKX_STORAGEFEE.TR_INSERT 내부의 수불마감일자 체크로직 선반영함.
				String magamYn = commonDao.selectOne("kxStorageFeeService.selectMagamYn", batchParam);
				if (magamYn.equals("N")) { // 마감전: 
					int cnt1 = commonDao.insert("kxStorageFeeService.insertKxStorageFee"  , batchParam);
					int cnt2 = commonDao.insert("kxStorageFeeService.insertIfKxStorageFee", batchParam);
					rtnMsg = "KX_STORAGEFEE INSERT COUNT: " + String.valueOf(cnt1) + " / IF_KX_STORAGEFEE INSERT COUNT: " + String.valueOf(cnt2); 
				} else { // 마감후:
					rtnMsg = "수불이 마감되어 확정이 불가합니다. STORERKEY:FW00, DCCODE:1000, 마감일자:"+magamYn; 
				}
				
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");	
				batchLogDto.setLineNo("100");	
				batchLogDto.setResultMsg(rtnMsg);
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}