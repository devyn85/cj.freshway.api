package cjfw.batch.noneParamJob;


import java.util.List;
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
import cjfw.batch.dto.IbTirdPartyMastResDto;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.15
 * @description : 3PL물류대행수수료정산 협력사관리_탭 협력사 관리 배치
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.15 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class IbTirdPartyMastBatch {

	private final CommonDao commonDao;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job ibTirdPartyMastJob() {
		return new JobBuilder("ibTirdPartyMastJob", jobRepository)
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
					.jobName("ibTirdPartyMastJob")
					.jobDiv("JAVA")
					.nodeLevel(0)
					.jobStatus("START")
					.command("")
					.lineNo("90") // try 안에 있는 첫번째 line
					.resultCode("0")
					.resultMsg("")
					.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
				batchLogDto.setLineNo("97"); // try 안에 있는 제일 먼저 실행되는 쿼리 line
                Map<String, Object> paramMap = new java.util.HashMap<>();
                paramMap.put("AVC_IFID", "ID237");
                paramMap.put("AVC_IFRESULT", "");
				paramMap.put("avc_WORKER", "batch");
				
                List<IbTirdPartyMastResDto> list = commonDao.selectList("IbTirdPartyMastService.selectMasterList");
                
				for(IbTirdPartyMastResDto dto : list) {
					
					List<IbTirdPartyMastResDto> dupCheckList = commonDao.selectList("IbTirdPartyMastService.selectCount", dto);
					
					if(dupCheckList.get(0).getCnt().equals("0")) {
						commonDao.insertQuartz("IbTirdPartyMastService.insertMaster", dto);
					}
					
				}

			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());

			} finally {
				batchLogDto.setJobStatus("END");
				batchLogDto.setLineNo("116"); // finally 안에 있는 첫 번째 line
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}