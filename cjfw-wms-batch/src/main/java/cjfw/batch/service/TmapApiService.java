package cjfw.batch.service;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : SYSTEM
 * @date : 2025.01.17
 * @description : TMAP API 연동 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.17 SYSTEM                생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmapApiService {

    private final CommonDao commonDao;
    private final RestTemplate restTemplate;
    private final HjdongCodeService hjdongCodeService;
    private final ObjectMapper objectMapper;

    @Value("${cf.tmap.api.key}")
    private String tmapApiKey;

    @Value("${cf.tmap.api.url}")
    private String tmapApiUrl;

    /**
     * TMAP 공간검색 API 호출
     * @param hjdongCd 행정동코드
     * @param hjdongNm 행정동명
     * @param category 카테고리 (시도, 시군구, 행정동)
     * @param ctpKorNm 시도명
     * @param sigKorNm 시군구명
     * @return regionId 또는 null
     */
    public String searchSpatialRegion(String hjdongCd, String hjdongNm, String category, String ctpKorNm, String sigKorNm, String jobExecutionId) {
        try {
            log.info("TMAP API 공간검색 시작 - 행정동코드: {}, 행정동명: {}, 카테고리: {}, 시도: {}, 시군구: {}",
                    hjdongCd, hjdongNm, category, ctpKorNm, sigKorNm);

            // GET 요청을 위한 URL 쿼리 파라미터 구성
            StringBuilder urlBuilder = new StringBuilder(tmapApiUrl + "/tmap/geofencing/regions");
            urlBuilder.append("?version=1");
            urlBuilder.append("&format=json");
            urlBuilder.append("&callback=result");
            urlBuilder.append("&count=100");
            urlBuilder.append("&categories=").append(category);
            urlBuilder.append("&searchType=KEYWORD");

            switch (category) {
                case "city_do":
                    urlBuilder.append("&searchKeyword=").append(ctpKorNm);
                    break;
                case "gu_gun":
                    urlBuilder.append("&searchKeyword=").append(sigKorNm);
                    break;
                default:
                    urlBuilder.append("&searchKeyword=").append(hjdongNm);
                    break;
            }

            String url = urlBuilder.toString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("appKey", tmapApiKey);

            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode searchRegionsInfo = rootNode.get("searchRegionsInfo");

                if (searchRegionsInfo != null && searchRegionsInfo.isArray()) {
                    // 행정동명과 시군구명이 모두 일치하는 regionId 찾기
                    String bestMatchRegionId = findBestMatch(searchRegionsInfo, category, hjdongNm, ctpKorNm, sigKorNm);

                    if (bestMatchRegionId != null) {
                        log.info("최적 매칭 발견 - regionId: {}", bestMatchRegionId);
                        return bestMatchRegionId;
                    }
                }
            }

            log.warn("TMAP API에서 매칭되는 지역을 찾지 못함 - 행정동명: {}, 시군구명: {}", hjdongNm, sigKorNm);
            return null;

        } catch (Exception e) {
            log.error("TMAP API 호출 중 오류 발생 - 행정동코드: {}, 행정동명: {}", hjdongCd, hjdongNm, e);
            saveErrorLog(jobExecutionId, "3", e.toString());
            return null;
        }
    }

    /**
     * 여러 검색 결과 중에서 최적의 매칭을 찾는 메서드
     */
    private String findBestMatch(JsonNode searchRegionsInfo, String category, String hjdongNm, String ctpKorNm, String sigKorNm) {
        boolean isSejongCity = hjdongCodeService.isSejongCity(ctpKorNm);
        String expectedDescription = buildExpectedDescription(ctpKorNm, sigKorNm, hjdongNm);

        for (JsonNode regionInfo : searchRegionsInfo) {
            JsonNode region = regionInfo.get("regionInfo");
            if (region == null) continue;

            String regionId = region.get("regionId").asText();
            String description = region.get("description").asText();

            // 세종특별자치시 행정동은 시군구가 없으므로 별도 처리
            if ("adminDong".equals(category) && isSejongCity) {
                String regionName = region.get("regionName").asText();
                if (hjdongNm.equals(regionName) && description.contains(ctpKorNm)) {
                    log.info("세종특별자치시 매칭 발견 - regionId: {}, description: {}", regionId, description);
                    return regionId;
                }
                continue;
            }

            // description 일치 비교 (city_do, gu_gun, adminDong 공통)
            if (expectedDescription.equals(description)) {
                log.info("description 매칭 발견 - regionId: {}, expected: {}, actual: {}", regionId, expectedDescription, description);
                return regionId;
            }
        }

        return null;
    }

    /**
     * TMAP description 비교용 문자열 생성
     * @param ctpKorNm 시도명
     * @param sigKorNm 시군구명 (city_do일 경우 null)
     * @param hjdongNm 행정동명 (city_do, gu_gun일 경우 null)
     * @return "시도 시군구 행정동" 형식의 문자열 (null 필드는 제외)
     */
    private String buildExpectedDescription(String ctpKorNm, String sigKorNm, String hjdongNm) {
        StringBuilder sb = new StringBuilder();
        if (ctpKorNm != null && !ctpKorNm.isEmpty()) {
            sb.append(ctpKorNm);
        }
        if (sigKorNm != null && !sigKorNm.isEmpty()) {
            sb.append(" ").append(sigKorNm);
        }
        if (hjdongNm != null && !hjdongNm.isEmpty()) {
            sb.append(" ").append(hjdongNm);
        }
        return sb.toString();
    }

    /**
     * TMAP 폴리곤 정보 조회 API 호출
     * @param regionId 지역ID
     * @return 폴리곤 정보 (GeoJSON 형태)
     */
    public String getPolygonData(String regionId, String jobExecutionId) {
        try {
            log.info("TMAP 폴리곤 정보 조회 시작 - regionId: {}", regionId);

            // GET 요청을 위한 URL 쿼리 파라미터 구성
            StringBuilder urlBuilder = new StringBuilder(tmapApiUrl + "/tmap/geofencing/regions/"+regionId);
            urlBuilder.append("?version=1");
            urlBuilder.append("&format=json");
            urlBuilder.append("&callback=result");
            urlBuilder.append("&resCoordType=WGS84GEO");
            urlBuilder.append("&appKey=").append(tmapApiKey);

            String url = urlBuilder.toString();

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("폴리곤 정보 조회 성공 - regionId: {}", regionId);
                return response.getBody();
            } else {
                log.warn("폴리곤 정보 조회 실패 - regionId: {}, status: {}", regionId, response.getStatusCode());
                return null;
            }

        } catch (Exception e) {
            log.error("TMAP 폴리곤 정보 조회 중 오류 발생 - regionId: {}", regionId, e);
            saveErrorLog(jobExecutionId, "4", e.toString());
            return null;
        }
    }

    private void saveErrorLog(String jobExecutionId, String lineNo, String resultMsg){
        BatchLogParamsDto batchLogDto = BatchLogParamsDto.builder()
                .jobExecutionId(jobExecutionId)
                .jobName("msHjdongPolygonJob")
                .jobDiv("JAVA")
                .nodeLevel(0)
                .jobStatus("INFO")
                .command("")
                .lineNo(lineNo)
                .resultCode("-1")
                .resultMsg(resultMsg)
                .build();

        commonDao.selectOne("batchCommonService.insertBatchLog", batchLogDto);
    }
}
