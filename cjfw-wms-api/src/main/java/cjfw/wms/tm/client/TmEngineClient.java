package cjfw.wms.tm.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.wms.tm.dto.engine.TmEngineRequestDto;
import cjfw.wms.tm.dto.engine.TmEngineResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : Engine 통신용 HTTP Client
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 * 2025.11.19 엔진환경설정 정보 properties 구성 변경
 *            엔진 호출 재시도 제한 처리
 *            복잡도 조정           박은경 ekmona@cj.net
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TmEngineClient {
    private final TmEngineConfig tmEngineConfig;

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    private final List<Class<?>> nonRetriableIOExceptionClasses = Arrays.asList(
            InterruptedIOException.class, UnknownHostException.class, ConnectException.class,
            NoRouteToHostException.class, SSLException.class
    );

    @PostConstruct
    public void init() {
        log.info("[TmEngineConfig] url: {}, retry-count: {}, retry-delay: {} ms",
                tmEngineConfig.getBaseUrl(), tmEngineConfig.getRetryCount(), tmEngineConfig.getRetryDelay());
        // SimpleClientHttpRequestFactory로 타임아웃 설정 (Spring Boot 기본 제공)
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(tmEngineConfig.getConnectTimeout()); // 30초 연결 타임아웃
        requestFactory.setReadTimeout(tmEngineConfig.getReadTimeout()); // 120초 읽기 타임아웃 (CJ 전처리 서버 처리 시간 고려)
        
        this.restTemplate = new RestTemplate(requestFactory);

        // RestTemplate의 MessageConverter 설정
        this.restTemplate.getMessageConverters().forEach(converter -> {
            if (converter instanceof MappingJackson2HttpMessageConverter messageConverter) {
                this.objectMapper =  messageConverter.getObjectMapper();
                this.objectMapper.findAndRegisterModules();
                this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        });

        log.info("[TmEngineClient] completed initialization - 연결 타임아웃: {} ms, 읽기 타임아웃: {} ms"
                , tmEngineConfig.getConnectTimeout(), tmEngineConfig.getReadTimeout());
    }

    /**
     * Engine에 최적화 요청을 전송합니다.
     *
     * @param request Engine 요청 DTO
     * @return Engine 응답 DTO
     * @throws RuntimeException Engine 통신 실패 시
     */
    public TmEngineResponseDto callOptimization(TmEngineRequestDto request) {
        long startTime = System.currentTimeMillis();
        String requestId = "REQ_" + startTime;
        request.setRequest_id(requestId);

        logRequest(request, requestId);

        boolean isRetriable = true;
        HttpEntity<TmEngineRequestDto> entity = createRequestEntity(request);
        int attempt = 1;
        for (; attempt <= tmEngineConfig.getRetryCount(); attempt++) {
            try {
                log.info("🔄 [{}] Engine 호출 시도 {}/{}", requestId, attempt, tmEngineConfig.getRetryCount());
                TmEngineResponseDto responseBody = executeRequest(requestId, entity);
                validateAndLogResponse(requestId, responseBody);
                logSuccess(requestId, responseBody, startTime);
                return responseBody;
            } catch (Exception e) {
                log.error("🚨 [{}] Engine 호출 중 예상치 못한 오류 (시도 {}/{}) - 메시지: {}",
                        requestId, attempt, tmEngineConfig.getRetryCount(), e.getMessage(), e);

                if (e instanceof HttpClientErrorException httpClientErrorException) {
                    HttpStatusCode status = httpClientErrorException.getStatusCode();
                    isRetriable = status.is5xxServerError();
                } else if (e.getCause() != null && e.getCause() instanceof IOException ioException) {
                    isRetriable = isRetrialbeIOException(ioException);
                }
                // retry 필요한 경우만 시도
                // Connection, Socket 관련 nonRetriable
                // status 4xx nonRetriable
                if (!isRetriable) {
                    log.info("🚀 [{}] Engine 호출 실패: isRetriable={}", requestId, isRetriable);
                    throw new TmEngineClientException("Engine 호출 실패", e);
                }
            }

            if (attempt < tmEngineConfig.getRetryCount()) {
                waitBeforeRetry(requestId);
            }
        }

        log.info("🚀 [{}] Engine 호출 실패: attempt={}", requestId, attempt);
        throw new TmEngineClientException("Engine 호출 재시도 횟수 초과");
    }

    private boolean isRetrialbeIOException(IOException ioException) {
        for (Class<?> clz : nonRetriableIOExceptionClasses) {
            if (clz.isInstance(ioException)) {
                log.warn("None retriable IOException : {}", clz);
                return false;
            }
        }
        return true;
    }

    private void logRequest(TmEngineRequestDto request, String requestId) {
        log.info("🚀 [{}] Engine 최적화 요청 시작 - URL: {}", requestId, tmEngineConfig.getBaseUrl());
        log.info("🚀 [{}] 요청 데이터 - shipments: {}, vehicles: {}, timezone={}, request_id={}, description={}, jobs={}",
                requestId,
            request.getShipments() != null ? request.getShipments().size() : 0,
            request.getVehicles() != null ? request.getVehicles().size() : 0,
            request.getTimezone(), request.getRequest_id(), request.getDescription(),
                request.getJobs() != null ? request.getJobs().size() : 0);

        // objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String prettyJson = "";
        try {
            prettyJson = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.error("[{}] Engine 요청 JSON 변환 중 오류 발생: {}", requestId, e.getMessage());
        }
        log.info("[{}] JSON=\n{}", requestId, prettyJson);
        // request.getShipments().forEach(ship -> log.info("[{}] amount: {}, skills: {}"
        //         , requestId, ship.getAmount(), ship.getSkills()));
        // request.getVehicles().forEach(vehi -> log.info("[{}] capacity: {}"
        //         , requestId, vehi.getCapacity()));
    }


    private HttpEntity<TmEngineRequestDto> createRequestEntity(TmEngineRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(request, headers);
    }

    private TmEngineResponseDto executeRequest(String requestId, HttpEntity<TmEngineRequestDto> entity) {
        ResponseEntity<TmEngineResponseDto> response = restTemplate.exchange(
            tmEngineConfig.getBaseUrl(),
            HttpMethod.POST,
            entity,
            TmEngineResponseDto.class
        );
        
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            log.warn("[{}] Engine 응답 이상 - HTTP 상태: {}, Body: {}", requestId, 
                response.getStatusCode(), response.getBody());
            throw new TmEngineClientException("Engine 응답이 올바르지 않습니다.");
        }
        
        return response.getBody();
    }

    private void validateAndLogResponse(String requestId, TmEngineResponseDto responseBody) {
        if (responseBody.getRoutes() == null || responseBody.getRoutes().isEmpty()) {
            log.warn("[{}] Routes가 없거나 비어있습니다!", requestId);
        }
        
        log.info("[{}] Engine 최적화 요청 시작 - URL: {}", requestId, tmEngineConfig.getBaseUrl());
        if (responseBody.getRoutes() != null && !responseBody.getRoutes().isEmpty()) {
            for (int routeIdx = 0; routeIdx < responseBody.getRoutes().size(); routeIdx++) {
                var route = responseBody.getRoutes().get(routeIdx);
                log.info("[{}] Route[{}] - vehicle: {}, steps: {}", requestId, routeIdx, route.getVehicle(), route.getSteps().size());
                 route.getSteps().forEach(step ->
                     log.info("[{}] Step[{}] - type: {}, location: {}, duration: {}, distance: {}, {}",
                             requestId, step.getType(), step.getLocation(), step.getDuration(), step.getDistance(), step));
            }
        } else {
            log.warn("[{}] Routes가 없거나 비어있습니다!", requestId);
        }
        log.info("[{}] Engine 최적화 요청 시작 - URL: {}", requestId, tmEngineConfig.getBaseUrl());
        
        Integer code = responseBody.getCode();
        if (code != null && code != 0 && code != 200) {
            String errorMessage = "CJ 전처리 서버에서 최적화 실패 (코드: " + code + ")";
            log.error("[{}] CJ 전처리 서버 최적화 실패 - 코드: {}", requestId, code);
            throw new TmEngineClientException(errorMessage);
        }
    }

    private void logSuccess(String requestId, TmEngineResponseDto responseBody, long startTime) {
        log.info("✅ [{}] Engine 호출 성공 - 코드: {}, routes: {}, unassigned: {}",
            requestId, responseBody.getCode(),
            responseBody.getRoutes() != null ? responseBody.getRoutes().size() : 0,
            responseBody.getUnassigned() != null ? responseBody.getUnassigned().size() : 0);
        responseBody.getUnassigned().forEach(unassigned -> log.info("{}", unassigned));
        log.info("[{}] summary: {}", requestId, responseBody.getSummary());
        log.info("[{}] estimated: {} ms", requestId, (System.currentTimeMillis() - startTime));
    }

    private void waitBeforeRetry(String requestId) {
        try {
            log.info("⏳ [{}] {}초 후 재시도합니다...", requestId, tmEngineConfig.getRetryDelay());
            Thread.sleep(tmEngineConfig.getRetryDelay());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new TmEngineClientException("[" + requestId + "] Engine 호출 중단됨", ie);
        }
    }

}
