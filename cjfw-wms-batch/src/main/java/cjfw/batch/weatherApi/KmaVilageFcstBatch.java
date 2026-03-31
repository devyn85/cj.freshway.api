package cjfw.batch.weatherApi;

import cjfw.batch.common.BatchJobListener;
import cjfw.batch.common.SetParamService;
import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.weatherApi.dto.CmAdminAreaGridEntity;
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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KmaVilageFcstBatch {

    private final CommonDao commonDao;
    private final SetParamService setParamService;
    private static final String BATCH_JOB_NAME = "weatherApiJob";

    private final KmaVilageFcstService kmaVilageFcstService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private BatchJobListener batchJobListener;


    @Bean
    public Job weatherApiJob() {
        return new JobBuilder("weatherApiJob", jobRepository)
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
                    .lineNo(new Throwable().getStackTrace()[0].getLineNumber() + "")
                    .resultCode("0")
                    .resultMsg("")
                    .build();


            // 배치 파라미터 조회.
            Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
            BatchParamsUtil batchParam = setParamService.getObjectBatchParam(jobParams, batchLogDto.getJobExecutionId());

            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);

                /* service log 적재 필요한 log param obj */
                BatchLogParamsDto batchLogServiceDto = new BatchLogParamsDto();
                batchLogServiceDto.setJobExecutionId(Long.toString(chunkContext.getStepContext().getStepExecution().getJobExecution().getId()));
                batchLogServiceDto.setJobName(BATCH_JOB_NAME);
                batchLogServiceDto.setJobDiv("JAVA SERVICE");
                batchLogServiceDto.setNodeLevel(1);
                batchLogServiceDto.setJobStatus("START");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultCode("0");
                batchLogServiceDto.setResultMsg("JAVA SERVICE START");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);

                List<CmAdminAreaGridEntity> areaList = commonDao.selectList("kmaVilageFcstService.selectAreaGrid");
                batchLogServiceDto.setJobStatus("INFO");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultMsg("기상청제공 위경도 좌표 COUNT: " + areaList.size() + "건");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);

                List<CmAdminAreaGridEntity> areaCtpGroupList = commonDao.selectList("kmaVilageFcstService.selectAreaCtpGroupList");
                String areaCtpGroupListStr = "기상청제공 위경도 좌표 상세 COUNT\n========================\n[";
                String areaCtpGroupStr = "";
                if(areaCtpGroupList.isEmpty()) {
                    areaCtpGroupStr = "조회된 데이터가 없습니다.";
                    batchLogServiceDto.setResultCode("-1");
                }
                for(CmAdminAreaGridEntity areaCtpGroup : areaCtpGroupList){
                    areaCtpGroupStr = areaCtpGroupStr + areaCtpGroup.getCtpKorNm() + ": " + areaCtpGroup.getCtpKorNmCnt() + ", ";
                }
                areaCtpGroupListStr = areaCtpGroupListStr + areaCtpGroupStr.replaceAll(", $", "") + "]";
                batchLogServiceDto.setJobStatus("INFO");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultMsg(areaCtpGroupListStr);
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);

                /* 기상청 날씨예보 발표주기.(3시간단위)
                 * BASE_TIME이 발표주기랑 다르면 NO_DATA를 반환.
                 * 현시점과 가장 가까운 과거시간을 추출하여 BASE_TIME으로 사용
                 */
                String[] times = {"02", "05", "08", "11", "14", "17", "20", "23"};
                int nowHour = LocalTime.now().getHour();
                int[] intTimes = Arrays.stream(times)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int pastHour = Arrays.stream(intTimes)
                        .filter(t -> t <= nowHour)
                        .max()
                        .orElseGet(() -> Arrays.stream(intTimes).max().getAsInt());


                DateTimeFormatter dayFormatter  = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
                LocalDateTime now = LocalDateTime.now();
                String baseDate  = now.format(dayFormatter);
                String baseTime = String.format("%02d00", pastHour);

                String dayString  = now.format(dayFormatter);
                String timeString = now.format(timeFormatter);
                String stdDt = dayString.concat(timeString);

                // 현 시간 기준으로 전체 데이터 예상 카운트.
                int start = Integer.parseInt(baseTime);
                int end = 2300;
                LocalTime baseStartTime = LocalTime.ofSecondOfDay(0);
                LocalTime startTime = LocalTime.of(start / 100, start % 100);
                LocalTime endTime = LocalTime.of(end / 100, 0);
                long baseHourCnt = ChronoUnit.HOURS.between(baseStartTime, endTime) +1;
                long hourCnt = ChronoUnit.HOURS.between(startTime, endTime);
                int categoryCnt = commonDao.selectList("kmaVilageFcstService.selectUseCategory").size();
                int areaGridCnt = areaList.size();
                int kmShortForecastTotalCnt = Math.toIntExact((hourCnt + baseHourCnt) * categoryCnt * areaGridCnt);
                // 로그 쌓기.
                batchLogServiceDto.setJobStatus("INFO");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultMsg("BASETIME 기준으로 예상 생성 건수:[" + startTime + "] " + String.format("%,d", kmShortForecastTotalCnt) + "건");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);

                // API 조회 시작
                Map<String, Object> paramMap = new java.util.HashMap<>();
                paramMap.put("stdDt", stdDt);       // API 조회 일시
                paramMap.put("baseDate", baseDate); // API UPDATE 기준일자
                paramMap.put("baseTime", baseTime); // API UPDATE 기준시간
                //1차 처리 수행.
                String resultString = callKmaVilageFcstService(areaList, batchParam, batchLogDto, paramMap);

                int totalCnt1 = 0; // 1차 처리 건수
                if("OK".equals(resultString)){
                    // 1차 처리 건수
                    totalCnt1 = (int) commonDao.selectOne("kmaVilageFcstService.selectKmShortForecastCnt", paramMap);
                    batchLogServiceDto.setJobStatus("INFO");
                    batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                    batchLogServiceDto.setResultMsg("BASETIME 기준으로 1회차 처리 건수:[" + startTime + "] " + String.format("%,d", totalCnt1) + "건");
                    commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);
                }

                try {
                    // 처리 건수가 있을 경우에만 이전 데이터 삭제.
                    if(totalCnt1 > 0){
                        commonDao.delete("kmaVilageFcstService.deleteKmShortForecast", paramMap);
                    }
                } catch (Exception e) {
                    batchLogServiceDto.setJobStatus("ERR");
                    batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                    batchLogServiceDto.setResultMsg(e.getMessage());
                    commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
                }

                // 2차 처리 수행 - 미처리건 재처리
                paramMap.put("searchGubun", "S");
                resultString = "";
                List<CmAdminAreaGridEntity> areaListUnprocessed = commonDao.selectList("kmaVilageFcstService.selectAreaGrid", (Object) paramMap);
                if(!areaListUnprocessed.isEmpty()) {
                    resultString = callKmaVilageFcstService(areaListUnprocessed, batchParam, batchLogDto, paramMap);
                }

                int totalCnt2 = 0; // 2차 처리 건수
                if("OK".equals(resultString)){
                    // 2차 처리 건수
                    totalCnt2 = (int) commonDao.selectOne("kmaVilageFcstService.selectKmShortForecastCnt", paramMap);
                    batchLogServiceDto.setJobStatus("INFO");
                    batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                    batchLogServiceDto.setResultMsg("BASETIME 기준으로 2회차 처리 건수:[" + startTime + "] " + String.format("%,d", totalCnt2) + "건");
                    commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);
                }

                // 최종 처리 건수
                int totalCnt = (int) commonDao.selectOne("kmaVilageFcstService.selectKmShortForecastCnt", paramMap);
                batchLogServiceDto.setJobStatus("INFO");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultMsg("BASETIME 기준으로 최종 처리 건수:[" + startTime + "] " + String.format("%,d", totalCnt) + "건");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);

                batchLogServiceDto.setJobStatus("END");
                batchLogServiceDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogServiceDto.setResultCode("0");
                batchLogServiceDto.setResultMsg("JAVA SERVICE END");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogServiceDto);
            } catch (Exception e) {
                batchLogDto.setJobStatus("ERR");
                batchLogDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                batchLogDto.setResultCode("-1");
                batchLogDto.setResultMsg(e.getMessage());
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            } finally {
                batchLogDto.setJobStatus("END");
                batchLogDto.setLineNo(new Throwable().getStackTrace()[0].getLineNumber() + "");
                commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
            }

            return RepeatStatus.FINISHED;
        };
    }

    private String callKmaVilageFcstService(List<CmAdminAreaGridEntity> areaList, BatchParamsUtil batchParam, BatchLogParamsDto batchLogDto, Map<String, Object> paramMap) {
        int consecutiveErr = 0;
        String resultString = "";
        for(CmAdminAreaGridEntity area : areaList) {
            Integer pageNo = 1;
            Integer numOfRows = 1000;	// +5일 데이터 조회를 원치 않을 경우 수정하면 됨.
            String dataType = "JSON";	// JSON, XML 지원.


            Map<String, Object> paramVilageForecastMap = new java.util.HashMap<>();
            paramVilageForecastMap.put("pageNo", pageNo);
            paramVilageForecastMap.put("numOfRows", numOfRows);
            paramVilageForecastMap.put("dataType", dataType);
            paramVilageForecastMap.put("stdDt", paramMap.get("stdDt"));
            paramVilageForecastMap.put("baseDate", paramMap.get("baseDate"));
            paramVilageForecastMap.put("baseTime", paramMap.get("baseTime"));
            paramVilageForecastMap.put("nx", area.getNx());
            paramVilageForecastMap.put("ny", area.getNy());
            paramVilageForecastMap.put("batchParam", batchParam);
            paramVilageForecastMap.put("batchLogDto", batchLogDto);
            resultString = kmaVilageFcstService.getVilageForecast(paramVilageForecastMap);

            if("-1".equals(resultString)){
                break;
            }

            if ("ERR".equals(resultString)) {
                consecutiveErr++;

                // 연속 오류가 쌓이면 배치가 더 천천히 진행(서버 보호)
                if (consecutiveErr >= 5) {
                    try { Thread.sleep(3000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                }
                // 필요하면 여기서 아예 중단 정책도 가능(예: 20회 연속이면 stop)
                if (consecutiveErr >= 20) {
                    break;
                }
            } else {
                consecutiveErr = 0; // 성공하면 리셋
            }
        }

        return resultString;
    }
}