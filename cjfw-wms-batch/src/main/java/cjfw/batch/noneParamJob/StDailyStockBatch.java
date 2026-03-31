package cjfw.batch.noneParamJob;


import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
@Service
@RequiredArgsConstructor
public class StDailyStockBatch {

    private final CommonDao commonDao;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;

    @Bean
    public Job stDailyStockJob() {
        return new JobBuilder("stDailyStockJob", jobRepository)
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
                    .jobName("stDailyStockJob")
                    .jobDiv("JAVA")
                    .nodeLevel(0)
                    .jobStatus("START")
                    .command("")
                    .lineNo("80")
                    .resultCode("0")
                    .resultMsg("")
                    .build();
            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
                batchLogDto.setLineNo("86");
                Map<String, Object> paramMap = new java.util.HashMap<>();
                paramMap.put("AVC_IFID", "ID232");
                paramMap.put("AVC_IFRESULT", "");
                paramMap.put("avc_WORKER", "batch");
                commonDao.insertQuartz("stDailyStockService.saveStDailyStock", paramMap);
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
}