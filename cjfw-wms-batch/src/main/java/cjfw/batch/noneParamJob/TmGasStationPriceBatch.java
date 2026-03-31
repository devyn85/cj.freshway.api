package cjfw.batch.noneParamJob;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TmGasStationPriceBatch {

    private final CommonDao commonDao;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;

    @Bean
    public Job tmGasStationPriceJob() {
        return new JobBuilder("tmGasStationPriceJob", jobRepository)
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
                    .jobName("tmGasStationPriceJob")
                    .jobDiv("JAVA")
                    .nodeLevel(0)
                    .jobStatus("START")
                    .command("")
                    .lineNo("82")
                    .resultCode("0")
                    .resultMsg("")
                    .build();
            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
                callOpinet();
                batchLogDto.setLineNo("88");
                Map<String, Object> paramMap = new java.util.HashMap<>();
                paramMap.put("AVC_IFID", "ID227");
                paramMap.put("AVC_IFRESULT", "");
                commonDao.selectOne("tmGasStationPriceService.saveTmGasStationPrice", paramMap);

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
     * @description : 오피넷 API 호출 후 결과값 DB INSERT
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.25 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
     */
    public void callOpinet() {
        try {
            String apiUrl = ContextUtil.getProperty("cf.opinet.url") + "?out=json&code=" + ContextUtil.getProperty("cf.opinet.key");
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> result = objectMapper.readValue(inputStream, Map.class);

            log.info("TmGasStationPriceBatch > callOpinet() > 결과값 : {}", result.toString());

            // 결과 확인
            if (result.containsKey("RESULT")) {
                result = (Map<String, Object>) result.get("RESULT");

                // "OIL" 항목 DB INSERT
                List<Map<String, Object>> oilList = (List<Map<String, Object>>) result.get("OIL");
                if (oilList != null && oilList.size() > 0) {
                    for (Map<String, Object> oil : oilList) {
                        // TO-DO : 공통코드에서 가져오게 수정
                        if ("B027/D047/K015".contains(oil.get("PRODCD").toString())) {
                            commonDao.insertQuartz("tmGasStationPriceService.saveGasStationPrice", oil);
                        }
                    }

                }
            }
        } catch (Exception e) {
            log.info("TmGasStationPriceBatch > callOpinet() > Exception > {}", e.toString());
            throw new UserHandleException("MSG.COM.ERR.001");
        }
    }
}