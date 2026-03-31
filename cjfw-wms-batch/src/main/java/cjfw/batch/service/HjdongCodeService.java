package cjfw.batch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : SYSTEM
 * @date : 2025.01.17
 * @description : 행정동 코드 관련 서비스
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.01.17 SYSTEM                생성 </pre>
 */
@Slf4j
@Service
public class HjdongCodeService {
    
    /**
     * 행정동 코드를 분석하여 TMAP API 카테고리 결정
     * @param hjdongCd 행정동코드 (10자리)
     * @return TMAP API 카테고리 (시도, 시군구, 행정동)
     */
    public String determineCategory(String hjdongCd) {
        if (hjdongCd == null || hjdongCd.length() < 2) {
            log.warn("유효하지 않은 행정동코드: {}", hjdongCd);
            return "adminDong";
        }
        
        // 10자리 행정동 코드 패턴 분석
        if (hjdongCd.length() == 10) {
            // 시도 단위 (xx00000000 형식)
            if (hjdongCd.endsWith("00000000")) {
                log.debug("시도 단위 코드: {}", hjdongCd);
                return "city_do";
            }
            
            // 시군구 단위 (xxxxx00000 형식)
            if (hjdongCd.endsWith("00000")) {
                log.debug("시군구 단위 코드: {}", hjdongCd);
                return "gu_gun";
            }
            
            // 행정동 단위 (xxxxxxxxxx 형식)
            log.debug("행정동 단위 코드: {}", hjdongCd);
            return "adminDong";
        }
        
        // 기타 길이의 코드는 행정동으로 처리
        log.debug("기타 길이 코드 ({}자리): {}", hjdongCd.length(), hjdongCd);
        return "adminDong";
    }
    
    /**
     * 행정동 코드 길이 검증
     * @param hjdongCd 행정동코드
     * @return 유효한 코드인지 여부
     */
    public boolean isValidHjdongCode(String hjdongCd) {
        if (hjdongCd == null || hjdongCd.trim().isEmpty()) {
            return false;
        }
        
        // 2자리 이상 10자리 이하 (10자리 행정동 코드 지원)
        return hjdongCd.length() >= 2 && hjdongCd.length() <= 10;
    }
    
    /**
     * 세종특별자치시인지 확인
     * @param ctpKorNm 시도명
     * @return 세종특별자치시 여부
     */
    public boolean isSejongCity(String ctpKorNm) {
        return "세종특별자치시".equals(ctpKorNm);
    }
    
    /**
     * 행정동명 정규화 (공백 제거, 특수문자 처리, 제숫자동 -> 숫자동 변환)
     * @param hjdongNm 행정동명
     * @return 정규화된 행정동명
     */
    public String normalizeHjdongName(String hjdongNm) {
        if (hjdongNm == null) {
            return "";
        }
        
        String normalized = hjdongNm.trim()
                .replaceAll("\\s+", " ")  // 여러 공백을 하나로
                .replaceAll("[()]", "");  // 괄호 제거

        // "제숫자동" 및 "제숫자.숫자동" 패턴을 "숫자동" 및 "숫자.숫자동"으로 변경
        // 예: 구로제1동 -> 구로1동, 우제2동 -> 우2동, 면목제3.8동 -> 면목3.8동
        normalized = normalized.replaceAll("제(\\d+(?:\\.\\d+)?)동", "$1동");
        
        log.debug("행정동명 정규화: {} -> {}", hjdongNm, normalized);

        //서울시 강동구 상일동들의 경우 TMAP API 결과값 이슈로 제1동 형식으로 조회해야함
        if("상일1동".equals(normalized)){
            normalized = "상일제1동";
        }
        if("상일2동".equals(normalized)){
            normalized = "상일제2동";
        }
        
        return normalized;
    }
}
