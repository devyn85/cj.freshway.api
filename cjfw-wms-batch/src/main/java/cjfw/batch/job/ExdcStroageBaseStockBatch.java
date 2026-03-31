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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExdcStroageBaseStockBatch {

    private static final String BATCH_JOB_NAME = "exdcStroageBaseStockJob";
	private final CommonDao commonDao;

	private final SetParamService setParamService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	@Bean
	public Job exdcStroageBaseStockJob() {
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

				Calendar calendar = Calendar.getInstance();           // 현재 날짜와 시간으로 Calendar 객체 생성
				calendar.add(Calendar.MONTH, -1);                     // 현재 날짜에서 1개월 전으로 이동
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");  // "yyyyMM" 형식의 날짜 포맷 지정
				String result = dateFormat.format(calendar.getTime()); // 포맷에 맞게 날짜 문자열 변환

				if(batchParam.getAVC_REQUESTMSG() == null) {
					batchParam.setAVC_REQUESTMSG("<YYYYMM>" + result + "</YYYYMM>");
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					calendar.setTime(sdf.parse(batchParam.getAVC_REQUESTMSG()));
					result = dateFormat.format(calendar.getTime());
					batchParam.setAVC_REQUESTMSG("<YYYYMM>" + result + "</YYYYMM>");
				}

				commonDao.selectOne("exdcStroageBaseStockService.callProcExecute", batchParam);
			} catch (Exception e) {
				batchLogDto.setResultCode("-1");
				batchLogDto.setResultMsg(e.toString());
			} finally {
				batchLogDto.setJobStatus("END");	
				batchLogDto.setLineNo("110");	
				commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
			}

			return RepeatStatus.FINISHED;
		};
	}

}