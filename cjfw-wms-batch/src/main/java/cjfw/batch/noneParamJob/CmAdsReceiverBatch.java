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
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CmAdsReceiverBatch {
	
	private static final String BATCH_JOB_NAME = "cmAdsReceiverJob";

	private final CommonDao commonDao;
	
	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job cmAdsReceiverJob() {
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
				
				getAddrInfo(batchParam);
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");	
				batchLogDto.setLineNo("95");
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
	
	/**
	 * @description : 행정안전부 변동된 주소 가져오기
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.25 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void getAddrInfo(BatchParamsUtil batchParam) {
		// IF_MASTER 상태 조회
		String resultStatus = commonDao.selectOne("cmAdsReceiverService.selectAdressStatus", batchParam);
		
		if("1".equals(resultStatus)){
			// IF_MASTER 상태값 "START"로 변경
			commonDao.insertQuartz("cmAdsReceiverService.updateAdressStatusStart", batchParam);
			
			// 행안부 주소 I/F 테이블 DATA 가져오기
			int cnt = commonDao.insertQuartz("cmAdsReceiverService.saveAdsReceiver", batchParam);	
			if (cnt > 0) {
				// 행안부 주소 연동 후 행정동코드 "신규(31)" INSERT
				commonDao.insertQuartz("cmAdsReceiverService.insertHjdongCdNew", batchParam);			
				// 행안부 주소 연동 후 행정동코드 "폐기(63)" UPDATE
				commonDao.insertQuartz("cmAdsReceiverService.updateHjdongCdRemove", batchParam);			
				// 행안부 주소 연동 후 법정동코드 "신규(31)" INSERT
				commonDao.insertQuartz("cmAdsReceiverService.insertBjdongCdNew", batchParam);			
				// 행안부 주소 연동 후 법정동코드 "폐기(63)" UPDATE
				commonDao.insertQuartz("cmAdsReceiverService.updateBjdongCdRemove", batchParam);
				
				// "IF_FLAG" 값 변경
				commonDao.insertQuartz("cmAdsReceiverService.updateIFAdress", batchParam);
			}
			
			// IF_MASTER 상태값 "END"로 변경
			commonDao.insertQuartz("cmAdsReceiverService.updateAdressStatusEnd", batchParam);
		}
	}
}