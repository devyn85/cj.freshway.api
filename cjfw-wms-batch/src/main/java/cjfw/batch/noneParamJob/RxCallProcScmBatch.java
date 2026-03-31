package cjfw.batch.noneParamJob;


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
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RxCallProcScmBatch {
	
	private final CommonDao commonDao;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job rxCallProcScmJob() {
		return new JobBuilder("rxCallProcScmJob", jobRepository)
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
			try {
				setUserInfo();
			} catch (Exception e) {
				log.error("Batch execution failed", e);
			}

			return RepeatStatus.FINISHED;
		};
	}
	
	/**
	 * @description : IAM I/F 테이블을 통해 사용자 정보 설정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public void setUserInfo() {
		// IAM 수정된 정보 실 사용 TABLE에 정제
		// RXCALLPROCEDURESCM
		// IF_USER
		// IF_ROLE
		// IF_ROLE_USER
		
		// "success", "errCode", "errMsg" 응답 파라미터를 위해 사용
//		CommonTriggerDto commonTriggerDto = new CommonTriggerDto();
		
		// IAM 수정된 사용자 정보 저장
		commonDao.insertQuartz("rxCallProcScmService.insertUserInfo", null);
		
		// IAM 수정된 권한 정보 저장
//		commonDao.insertQuartz("rxCallProcScmService.insertRoleInfo", commonTriggerDto);
		
		// IAM 수정된 권한 정보 저장
		commonDao.insertQuartz("rxCallProcScmService.insertRoleUserInfo", null);
	}
	
}