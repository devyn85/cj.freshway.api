package cjfw.batch.job.inspectSorter;

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

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InspectSorterBatch {

	private static final String BATCH_JOB_NAME = "inspectSorterJob"; 
	private final CommonDao commonDao;

	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job inspectSorterJob() {
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
					.lineNo("73")
					.resultCode("0")
					.resultMsg("")
					.build();
			try {
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

				// 배치 파라미터 조회.
				Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
				BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

				int cntRows = commonDao.insert("inspectSorterService.insertInspectSorter", batchParam);
				batchLogDto.setJobStatus("INFO");
				batchLogDto.setLineNo("86");
				batchLogDto.setCommand("INSERT DP_INSPECT_SORTER");
				batchLogDto.setResultMsg("AFFECTED ROWS: " + String.valueOf(cntRows));
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
				batchLogDto.setCommand(""); batchLogDto.setResultMsg("");

				List<InspectSorterDto> resList = commonDao.selectList("inspectSorterService.selectInspectSorter");
				for(InspectSorterDto res : resList) {
					batchParam.setAVC_SYSTEM(res.getSystem());
					batchParam.setAVC_EXECUTEMODE("");
					batchParam.setAVC_COMMAND(res.getCommand());
					batchParam.setAVC_DCCODE(res.getDccode());
					batchParam.setAVC_STORERKEY(res.getStorerkey());
					batchParam.setAVC_ORGANIZE(res.getOrganize());
					batchParam.setAVC_AREA(res.getArea());
					batchParam.setAVC_REQUESTCODE(res.getRequestcode());
					batchParam.setAVC_REQUESTMSG(res.getRequestmsg());
					batchParam.setAVC_WORKER(res.getWorker());
					batchParam.setAVC_SECURITYKEY("");
					batchParam.setAI_SPID(Integer.parseInt(res.getSpid()));
					batchParam.setI_ERR(0);
					batchParam.setVC_RESULTMSG("");

					commonDao.selectOne("inspectSorterService.callProcExecute", batchParam);

					if(batchParam.getI_ERR() == 0) {
						batchParam.setSERIALKEY(res.getSerialkey());
						commonDao.selectOne("inspectSorterService.updateInspectSorter", batchParam);
					}
				}
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");
				batchLogDto.setLineNo("123");
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}
}