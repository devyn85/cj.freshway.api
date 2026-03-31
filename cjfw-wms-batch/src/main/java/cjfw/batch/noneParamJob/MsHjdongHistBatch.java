package cjfw.batch.noneParamJob;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.dto.MsBatchDto;
import cjfw.batch.dto.MsHjdongDto;
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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class MsHjdongHistBatch {
    private final CommonDao commonDao;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchJobListener batchJobListener;

    @Bean
    public Job msHjdongHistJob() {
        return new JobBuilder("msHjdongHistJob", jobRepository)
            .listener(batchJobListener)
            .flow(execStep())
            .end()
            .build();
    }

    @Bean
    @StepScope
    public Step execStep() {
        return new StepBuilder("msHjdongHistStep", jobRepository)
            .tasklet(execTasklet(), transactionManager)
            .build();
    }

    public Tasklet execTasklet() {
        return (contribution, chunkContext) -> {

            String jobExecutionId = Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId());

            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                .jobExecutionId(jobExecutionId)
                .jobName("msHjdongHistJob")
                .jobDiv("JAVA")
                .nodeLevel(0)
                .jobStatus("START")
                .command("")
                .lineNo("1")
                .resultCode("0")
                .resultMsg("")
                .build();

            commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            try {
                commonDao.insert("msHjdongPolygonService.saveIfMsDcHjdongHis", new MsHjdongDto());
            } catch (Exception e) {
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("2");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job msDcOrdgrpJob() {
        return new JobBuilder("msDcOrdgrpJob", jobRepository)
            .listener(batchJobListener)
            .flow(execMsDcOrdgrpStep())
            .end()
            .build();
    }

    @Bean
    @StepScope
    public Step execMsDcOrdgrpStep() {
        return new StepBuilder("msDcOrdgrpStep", jobRepository)
            .tasklet(execMsDcOrdgrpTasklet(), transactionManager)
            .build();
    }

    public Tasklet execMsDcOrdgrpTasklet() {
        return (contribution, chunkContext) -> {

            String jobExecutionId = Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId());

            BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                .jobExecutionId(jobExecutionId)
                .jobName("msDcOrdgrpJob")
                .jobDiv("JAVA")
                .nodeLevel(0)
                .jobStatus("START")
                .command("")
                .lineNo("1")
                .resultCode("0")
                .resultMsg("")
                .build();

            commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            try {
                String editDate = commonDao.selectOne("msHjdongPolygonService.getEditdate").toString();
                MsBatchDto batchDto = MsBatchDto.builder().editDate(editDate).build();
                commonDao.insert("msHjdongPolygonService.saveMsDcOrdgrp", batchDto);
                commonDao.insert("msHjdongPolygonService.saveMsDcDistrictOrdgrp", batchDto);

                // 신규 오더그룹: API CTE 기반 IF 적재 + MS_HJDONG_POP MERGE
                batchDto.setBatchDate(new java.util.Date());
                commonDao.insert("msHjdongPolygonService.batchInsertIfFromSample", batchDto);
                commonDao.update("msHjdongPolygonService.batchMergeMsHjdongPop", batchDto);
            } catch (Exception e) {
                log.error("msDcOrdgrpJob 실행 오류", e);
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.toString());
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo("2");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
        };
    }

}
