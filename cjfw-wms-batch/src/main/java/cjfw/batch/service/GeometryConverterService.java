package cjfw.batch.service;

import cjfw.batch.common.dto.BatchLogParamsDto;
import cjfw.core.dataaccess.CommonDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : SYSTEM
 * @date : 2025.01.17
 * @description : 지오메트리 변환 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.17 SYSTEM                생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeometryConverterService {

    private final CommonDao commonDao;
    private final ObjectMapper objectMapper;
    
    /**
     * GeoJSON을 WKT(Well-Known Text)로 변환
     * @param geoJson GeoJSON 문자열
     * @return WKT 문자열
     */
    public String convertGeoJsonToWkt(String geoJson, String jobExecutionId) {
        try {
            if (geoJson == null || geoJson.trim().isEmpty()) {
                log.warn("GeoJSON이 비어있음");
                return null;
            }
            
            //log.debug("GeoJSON → WKT 변환 시작 - 길이: {}", geoJson.length());
            JsonNode rootNode = objectMapper.readTree(geoJson);
            String type = rootNode.get("type").asText();
            //log.debug("GeoJSON 타입: {}", type);
            
            if ("FeatureCollection".equals(type)) {
                return convertFeatureCollectionToWkt(rootNode);
            } else {
                log.warn("지원하지 않는 GeoJSON 타입: {}", type);
                return null;
            }
            
        } catch (Exception e) {
            log.error("GeoJSON을 WKT로 변환 중 오류 발생", e);
            saveErrorLog(jobExecutionId,"5", e.toString());
            return null;
        }
    }
    
    /**
     * FeatureCollection을 WKT로 변환 (첫 번째 Feature의 geometry 사용)
     */
    private String convertFeatureCollectionToWkt(JsonNode featureCollectionNode) {
        try {
            JsonNode features = featureCollectionNode.get("features");
            if (features == null || !features.isArray() || features.size() == 0) {
                log.warn("FeatureCollection에 features가 비어있음");
                return null;
            }
            
            //log.debug("FeatureCollection features 개수: {}", features.size());
            
            // 첫 번째 Feature의 geometry 사용
            JsonNode firstFeature = features.get(0);
            if (firstFeature == null || !firstFeature.has("geometry")) {
                log.warn("첫 번째 Feature에 geometry가 없음");
                return null;
            }

            JsonNode geometry = firstFeature.get("geometry");
            String geometryType = geometry.get("type").asText();
            //log.debug("첫 번째 Feature의 geometry 타입: {}", geometryType);
            
            if ("Polygon".equals(geometryType)) {
                return convertPolygonToWkt(geometry);
            } else if ("MultiPolygon".equals(geometryType)) {
                return convertMultiPolygonToWkt(geometry);
            } else {
                log.warn("지원하지 않는 geometry 타입: {}", geometryType);
                return null;
            }
            
        } catch (Exception e) {
            log.error("FeatureCollection을 WKT로 변환 중 오류 발생", e);
            return null;
        }
    }
    
    /**
     * Polygon을 MULTIPOLYGON WKT로 변환 (SDO_GTYPE 2007)
     */
    private String convertPolygonToWkt(JsonNode polygonNode) {
        try {
            JsonNode coordinates = polygonNode.get("coordinates");
            if (coordinates == null || !coordinates.isArray() || coordinates.size() == 0) {
                log.warn("Polygon coordinates가 비어있음");
                return null;
            }
            
            // MULTIPOLYGON으로 변환 (하나의 POLYGON을 MULTIPOLYGON으로 래핑)
            StringBuilder wkt = new StringBuilder("MULTIPOLYGON(((");
            
            // 외곽 경계선 (첫 번째 배열)
            JsonNode exteriorRing = coordinates.get(0);
            appendCoordinatesToWkt(wkt, exteriorRing);
            wkt.append(")");
            
            // 내부 홀 (있는 경우)
            for (int i = 1; i < coordinates.size(); i++) {
                wkt.append(",(");
                appendCoordinatesToWkt(wkt, coordinates.get(i));
                wkt.append(")");
            }
            
            wkt.append("))");
            
            //log.debug("Polygon → MULTIPOLYGON WKT 생성 완료");
            return wkt.toString();
            
        } catch (Exception e) {
            log.error("Polygon을 MULTIPOLYGON WKT로 변환 중 오류 발생", e);
            return null;
        }
    }
    
    /**
     * MultiPolygon을 MULTIPOLYGON WKT로 변환 (첫 번째 Polygon만 사용, SDO_GTYPE 2007)
     */
    private String convertMultiPolygonToWkt(JsonNode multiPolygonNode) {
        try {
            JsonNode coordinates = multiPolygonNode.get("coordinates");
            if (coordinates == null || !coordinates.isArray() || coordinates.size() == 0) {
                log.warn("MultiPolygon coordinates가 비어있음");
                return null;
            }
            
            // 첫 번째 Polygon만 사용하여 MULTIPOLYGON WKT 생성
            JsonNode firstPolygon = coordinates.get(0);
            if (firstPolygon == null || !firstPolygon.isArray() || firstPolygon.size() == 0) {
                log.warn("MultiPolygon의 첫 번째 Polygon이 비어있음");
                return null;
            }
            
            StringBuilder wkt = new StringBuilder("MULTIPOLYGON(((");
            
            // 외곽 경계선
            JsonNode exteriorRing = firstPolygon.get(0);
            appendCoordinatesToWkt(wkt, exteriorRing);
            wkt.append("))");
            
            // 내부 홀 (있는 경우)
            for (int i = 1; i < coordinates.size(); i++) {
                wkt.append(",((");
                appendCoordinatesToWkt(wkt, coordinates.get(i).get(0));
                wkt.append("))");
            }
            
            wkt.append(")");
            
            //log.debug("MultiPolygon → MULTIPOLYGON WKT 생성 완료: {}", wkt.toString());
            return wkt.toString();
            
        } catch (Exception e) {
            log.error("MultiPolygon을 MULTIPOLYGON WKT로 변환 중 오류 발생", e);
            return null;
        }
    }
    
    /**
     * 좌표 배열을 WKT 형식으로 추가
     */
    private void appendCoordinatesToWkt(StringBuilder wkt, JsonNode coordinates) {
        if (coordinates.isArray()) {
            boolean first = true;
            for (JsonNode coord : coordinates) {
                if (coord.isArray() && coord.size() >= 2) {
                    if (!first) {
                        wkt.append(",");
                    }
                    double x = coord.get(0).asDouble();
                    double y = coord.get(1).asDouble();
                    wkt.append(x).append(" ").append(y);
                    first = false;
                }
            }
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
