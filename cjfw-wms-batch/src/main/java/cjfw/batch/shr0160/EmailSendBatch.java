package cjfw.batch.shr0160;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSendBatch {
    private static final String BATCH_JOB_NAME = "emailSendJob";
    private final CommonDao commonDao;
    private final SetParamService setParamService;
	private final EmailSendBatchService emailSendBatchService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private BatchJobListener batchJobListener;

	/**
	 * 배치 잡(Job)을 정의
	 * BATCH_H 등록 -> Quartz 테이블 등록 된 JOB_NAME => emailSendJob
     */
	@Bean
	public Job emailSendJob() {
		return new JobBuilder(BATCH_JOB_NAME, jobRepository)	// 잡 이름과 사용할 JobRepository 설정
				.listener(batchJobListener)							// 잡 실행 전/후 동작을 정의한 리스너 설정(공통 사용으로 class 파일 분리)
				.flow(execStep())									// 첫 번째 실행 스텝 설정 -> 하단의 execStep 메서드 실행.
				.end()												// 플로우 종료
				.build();											// 최종 Job 객체 생성
	}

	/**
	 * Step을 정의 및 Chunk 방식으로 구성
     */
	@Bean
	@StepScope
	public Step execStep() {
		return new StepBuilder("execStep", jobRepository)		// Step 이름과 JobRepository 설정
                .tasklet(execTasklet(), transactionManager)
				.build();											// Step 객체 생성
	}


	/**
	 * ROW 단위 이메일 전송 처리 후 결과를 DB에 업데이트
     */
	@Bean
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
                    .lineNo("74")
                    .resultCode("0")
                    .resultMsg("")
                    .build();

            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

                // 배치 파라미터 조회.
                Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
                BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

                // 상태 조회: 발송 가능한 상태인지 확인
                String resultStatus = commonDao.selectOne("eailSendBatchService.selectEmailStatus", batchParam);
                if ("1".equals(resultStatus)) {
                    // 상태 업데이트: 발송 시작 상태로 변경
                    commonDao.update("eailSendBatchService.updateStatusStart", batchParam);

                    // 이메일 리스트 조회
                    emailSendBatchService.sendEmail(batchParam, batchLogDto);
                }
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                // 상태 업데이트: 발송 종료 상태로 변경
                commonDao.update("eailSendBatchService.updateStatusEnd", batchLogDto);

                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("120");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
		};
	}

}