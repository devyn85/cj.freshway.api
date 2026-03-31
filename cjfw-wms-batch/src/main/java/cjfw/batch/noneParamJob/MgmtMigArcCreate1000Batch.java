package cjfw.batch.noneParamJob;

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

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2026.03.17
 * @description : MIGARC 계정 아카이빙 데이터 생성 BATCH
 *              : JOGID(1000) : 미오픈 센터 데이터 유입 차단(아카이빙후 데이터 삭제)
 *              : 전체 센터 TOBE 오프후 해당 기능 필요없음.(프로그램 삭제해주세요)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.17 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MgmtMigArcCreate1000Batch {

	private static final String BATCH_JOB_NAME = "mgmtMigArcCreate1000Job";
	private final CommonDao commonDao;

	private static final String SERVICEID_PREFIX = "migArcCreateJob.";

	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job mgmtMigArcCreate1000Job() {
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
					.lineNo("86")
					.resultCode("0")
					.resultMsg("")
					.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

				// 배치 파라미터 조회.
				Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
				BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

				commonDao.selectOne(SERVICEID_PREFIX + BATCH_JOB_NAME, batchParam);
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");
				batchLogDto.setLineNo("103");
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}