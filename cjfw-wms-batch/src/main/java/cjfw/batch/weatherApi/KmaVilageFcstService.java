package cjfw.batch.weatherApi;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.batch.common.dto.BatchParamsUtil;
import cjfw.batch.weatherApi.dto.KmaVilageFcstEntity;
import cjfw.core.dataaccess.CommonDao;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;
import cjfw.wms.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KmaVilageFcstService {

    private final CommonDao commonDao;
    private final WebClient kmaWebClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${cf.kma.base-url}")
    private String baseUrl;

    @Value("${cf.kma.base-path}")
    private String basePath;

    @Value("${cf.kma.service-key}")
    private String serviceKey;

    // (추가) 필드/프로퍼티 (클래스 필드에 추가)
    @Value("${cf.kma.min-interval-ms:250}")
    private long minIntervalMs;

    @Value("${cf.kma.max-retries:3}")
    private int maxRetries;

    @Value("${cf.kma.backoff-initial-ms:1000}")
    private long backoffInitialMs;

    @Value("${cf.kma.backoff-max-ms:8000}")
    private long backoffMaxMs;

    // 인스턴스 내 호출 속도 제한(멀티스레드 대비)
    private final Object throttleLock = new Object();
    private volatile long lastCallAtMs = 0L;

//    private void throttle() {
//        synchronized (throttleLock) {
//            long now = System.currentTimeMillis();
//            long wait = (lastCallAtMs + minIntervalMs) - now;
//            if (wait > 0) {
//                try { Thread.sleep(wait); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
//            }
//            lastCallAtMs = System.currentTimeMillis();
//        }
//    }

    /**
     * @description : API 호출 간 최소 간격 보장 (멀티스레드 환경에서도 안전)
     * - synchronized 블록 내에서 현재 시간과 마지막 호출 시간 비교
     * - 대기 필요 시 throttleLock 객체의 wait() 사용 (Thread.sleep() 대신)
     * - 인터럽트 발생 시 현재 스레드의 인터럽트 상태를 복구하여 상위 스택에 알림 (S2142 준수)
     */
    private void throttle() {
        synchronized (throttleLock) {
            //long now = System.currentTimeMillis();
            // 목표로 하는 해제 시간을 미리 계산합니다.
            long targetTimeMs = lastCallAtMs + minIntervalMs;

            // 허위 깨어남을 방지하기 위해 while 루프 내에서 조건을 확인합니다.
            while (System.currentTimeMillis() < targetTimeMs) {
                long wait = targetTimeMs - System.currentTimeMillis();
                if (wait > 0) {
                    try {
                        // 지정된 시간만큼 대기하며 락을 일시 해제합니다.
                        throttleLock.wait(wait);
                    } catch (InterruptedException ie) {
                        // 인터럽트 발생 시 상태를 복구하고 실행을 중단합니다.
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            // 대기가 확실히 끝난 후 현재 시간을 갱신합니다.
            lastCallAtMs = System.currentTimeMillis();
        }
    }

    /**
     * 기상청 단기예보 조회 (XML/JSON 모두 지원: default JSON)
     *
     * @param paramVilageForecastMap
     */
    public String getVilageForecast(Map<String, Object> paramVilageForecastMap) {
        //BATCH JOB LOG설정
        BatchParamsUtil batchParam = (BatchParamsUtil) paramVilageForecastMap.get("batchParam");
        BatchLogParamsDto batchLogDto = (BatchLogParamsDto) paramVilageForecastMap.get("batchLogDto");
        String[] logOption = batchParam.getAVC_LOG_PARAMS().split("\\^"); //AVC_LOG_PARAMS 문자열 분리
        BatchLogParamsDto copyDto = new BatchLogParamsDto();
        if (batchLogDto != null) {
            BeanUtils.copyProperties(batchLogDto, copyDto);
            copyDto.setJobDiv("JAVA SERVICE");
            copyDto.setNodeLevel(batchLogDto.getNodeLevel()+2 );
            copyDto.setCommand("");
        }

        String returnReasonMessage = "";

        try {
            String rel = UriComponentsBuilder.fromPath(basePath)
                    .query("serviceKey=" + serviceKey   // 인코딩된 키 그대로
                            + "&numOfRows=" + paramVilageForecastMap.get("numOfRows")
                            + "&pageNo=" + paramVilageForecastMap.get("pageNo")
                            + "&base_date=" + paramVilageForecastMap.get("baseDate")
                            + "&base_time=" + paramVilageForecastMap.get("baseTime")
                            + "&nx=" + paramVilageForecastMap.get("nx")
                            + "&ny=" + paramVilageForecastMap.get("ny")
                            + "&dataType=" + paramVilageForecastMap.get("dataType"))
                    .build(false)   // 추가 인코딩 방지
                    .toUriString();

            // (기존) WebClient 호출부를 아래로 교체 (getVilageForecast 내부)
            throttle(); // ✅ 호출 직전 최소 간격 보장

            String body = kmaWebClient.get()
                    .uri(rel)         // baseUrl + 상대 경로
                    .exchangeToMono(response -> {
                        HttpStatusCode status = response.statusCode();

                        // 바디는 일단 읽어두기(에러 메시지 확보)
                        Mono<String> bodyMono = response.bodyToMono(String.class).defaultIfEmpty("");

                        if (status.value() == 429) {
                            // Retry-After (초) 헤더가 있으면 읽기
                            String retryAfter = response.headers().asHttpHeaders().getFirst("Retry-After");
                            Long retryAfterSec;
                            if (retryAfter != null && retryAfter.matches("\\d+")) {
                                retryAfterSec = Long.parseLong(retryAfter);
                            } else {
                                retryAfterSec = null;
                            }

                            return bodyMono.flatMap(msg -> {
                                logSystemOut(copyDto, "ERR", logOption[4],
                                        new Throwable().getStackTrace()[0].getLineNumber() + "",
                                        "KMA API 429 Too Many Requests: " + msg
                                );

                                return Mono.error(
                                        new KmaRateLimitException(
                                                "KMA API 429 Too Many Requests: " + msg,
                                                retryAfterSec
                                        )
                                );
                            });
                        }

                        if (status.is5xxServerError() || status.is4xxClientError()) {
                            return bodyMono.flatMap(msg -> {
                                logSystemOut(copyDto, "ERR", logOption[4],
                                        new Throwable().getStackTrace()[0].getLineNumber() + "",
                                        "KMA API 4xx or 5xx: " + status.value() + " " + msg
                                );

                                return Mono.error(
                                        new RuntimeException("KMA API 4xx or 5xx: " + status.value() + " " + msg)
                                );
                            });
                        }

                        return bodyMono; // 정상
                    })
                    .retryWhen(
                            Retry.backoff(maxRetries, Duration.ofMillis(backoffInitialMs))
                                    .maxBackoff(Duration.ofMillis(backoffMaxMs))
                                    .jitter(0.2)
                                    .filter(ex ->
                                            ex instanceof KmaRateLimitException ||
                                                    ex instanceof KmaServerException ||
                                                    ex instanceof java.io.IOException ||
                                                    ex instanceof org.springframework.web.reactive.function.client.WebClientRequestException
                                    )
                                    .doBeforeRetry(sig -> {
                                        Throwable ex = sig.failure();

                                        // ✅ 429이고 Retry-After 있으면 그 시간만큼 추가로 대기(가장 효과 큼)
                                        if (ex instanceof KmaRateLimitException) {
                                            Long ra = ((KmaRateLimitException) ex).getRetryAfterSeconds();
                                            if (ra != null && ra > 0) {
                                                try { Thread.sleep(ra * 1000L); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                                            }
                                        }
                                        String nyString = "[nx:" + paramVilageForecastMap.get("nx") + ",ny:" + paramVilageForecastMap.get("ny") + "]";
                                        logSystemOut(copyDto, "ERR", logOption[4], new Throwable().getStackTrace()[0].getLineNumber() + "",
                                                "Retry " + (sig.totalRetries() + 1) + "/" + maxRetries +
                                                        " due to: " + ex.getClass().getSimpleName() + " - " + nyString + ex.getMessage());
                                    })
                    )
                    .block();

            JsonNode root = null;
            try {
                root = objectMapper.readTree(body);

            } catch (JsonProcessingException e) {
                // body가 JSON이 아닐 경우
                XmlMapper xmlMapper = new XmlMapper();
                root = xmlMapper.readTree(body);

                String returnReasonCode = root.path("cmmMsgHeader").path("returnReasonCode").asText();
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("returnReasonCode", returnReasonCode);
                returnReasonMessage = commonDao.selectOne("kmaVilageFcstService.selectReturnReasonMessage", paramMap);
                if(returnReasonMessage.isEmpty()) {
                    returnReasonMessage = e.getMessage();
                }
            }

            if(root != null) {
                JsonNode items = root.path("response").path("body").path("items").path("item");

                if(!items.isEmpty()) {
                    // 공통코드에 설정된 값으로 DB저장 여부 판단.(CD_CODE-> BASECODE, DATA1 = 'Y'값 추출)
                    List<KmaVilageFcstEntity> useCategory = commonDao.selectList("kmaVilageFcstService.selectUseCategory");
                    String[] useCategoryArray = useCategory.stream()
                            .map(KmaVilageFcstEntity::getCategory)
                            .toArray(String[]::new);

                    for (JsonNode item : items) {
                        if (Arrays.asList(useCategoryArray).contains(item.path("category").asText())) {

                            // 금일 기준 +1일 데이터만 적재.
                            String fcstDateStr = item.path("fcstDate").asText();
                            DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                            LocalDate fcstDate = LocalDate.parse(fcstDateStr, ymdFormatter);
                            LocalDate today = LocalDate.now();
                            // +1 이후가 들어오면 나가기...
                            if (fcstDate.isAfter(today.plusDays(1))) {
                                break;
                            }

                            try {
                                KmaVilageFcstEntity entityDto = new KmaVilageFcstEntity();
                                entityDto.setStdDt((String) paramVilageForecastMap.get("stdDt"));
                                entityDto.setBaseDate(item.path("baseDate").asText());
                                entityDto.setBaseTime(item.path("baseTime").asText());
                                entityDto.setFcstDate(item.path("fcstDate").asText());
                                entityDto.setFcstTime(item.path("fcstTime").asText());
                                entityDto.setCategory(item.path("category").asText());
                                entityDto.setFcstValue(item.path("fcstValue").asText());
                                entityDto.setNx(Integer.parseInt(item.path("nx").asText()));
                                entityDto.setNy(Integer.parseInt(item.path("ny").asText()));

                                commonDao.insert("kmaVilageFcstService.insertKmShortForecast", entityDto);
                            } catch (Exception e) {
                                returnReasonMessage = e.getMessage();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            if(e.getMessage().contains("API token quota exceeded")) {
                logSystemOut(copyDto, "ERR", logOption[4], new Throwable().getStackTrace()[0].getLineNumber() + "", "일일 트래픽 초과 오류!!!!==> 10000");
                return "-1";
            } else {
                String nx = paramVilageForecastMap.get("nx").toString();
                String ny = paramVilageForecastMap.get("ny").toString();
                returnReasonMessage = returnReasonMessage + e.getMessage();
                logSystemOut(copyDto, "ERR", logOption[4], new Throwable().getStackTrace()[0].getLineNumber() + "", "NX:"+nx + ", NY:" + ny + ", Error Message: " + returnReasonMessage);
                return "ERR";
            }
        }

        return "OK";
    }

    // (추가) 커스텀 예외 2개 (파일 내부 하단 또는 내부 static class로 추가)
    static class KmaRateLimitException extends RuntimeException {
        private final Long retryAfterSeconds; // null 가능
        KmaRateLimitException(String msg, Long retryAfterSeconds) {
            super(msg);
            this.retryAfterSeconds = retryAfterSeconds;
        }
        Long getRetryAfterSeconds() { return retryAfterSeconds; }
    }

    static class KmaServerException extends RuntimeException {
        KmaServerException(String msg) { super(msg); }
    }

    /*
     * @description : System.out.println ==> BATCH LOG기록으로 전환
     */
    public void logSystemOut(BatchLogParamsDto reqDto, String jobStatus, String isPrint, String lineNo, String logMsg) {
        if (!StringUtil.isEmpty(reqDto.getJobExecutionId())) {
            reqDto.setJobStatus(jobStatus);
            reqDto.setLineNo(lineNo);
            reqDto.setResultMsg(logMsg);
            try {
                commonDao.selectOne("batchCommonService.insertBatchLog", reqDto);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }
}