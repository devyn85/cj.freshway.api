package cjfw.wms.tm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 배차 다운로드 DTO (Excel 다운로드 + 좌표 갱신 통합)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.27 han@wemeetmobility.com 생성
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TmDispatchListDownloadDto {

    // ==================== Excel 컬럼 필드 (30개) ====================

    /** 순서 (동적 생성) */
    private Integer no;

    /** 물류센터명 */
    private String dcname;

    private String deliveryDate;
    
    
    /** POP명 */
    private String popName;

    /** 권역그룹명 */
    private String areaGroupName;

    /** 권역명 */
    private String areaName;

    /** 행정동코드 */
    private String dongCode;

    /** 우편번호 */
    private String zipCode;

    /** 실착지코드 */
    private String truthCustKey;

    /** 전표번호 */
    private String slipNo;

    /** 전표유형 */
    private String docType;

    /** 실착지명 */
    private String custName;

    /** 관리처코드 */
    private String custKey;

    /** 관리처명 */
    private String fromCustName;

    
    private String deliveryPriority;
    /** 경로 */
    private String route;

    /** 주문마감경로 */
    private String closeRoute;

    /** 클레임 여부 */
    private String claimYn;

    /** 차량번호 */
    private String carno;

    /** 기사명 */
    private String driverName;

    /** 전화번호 */
    private String driverPhone;

    /** 계약유형 */
    private String contractType;

    /** 배송유형 */
    private String deliveryType;

    /** 회차 */
    private Integer turn;

    /** 중량(kg) */
    private Double totalWeight;

    /** 체적(m³) */
    private Double totalCube;

    /** 주소 */
    private String address;

    /** 대면검수여부 */
    private String faceCheckYn;

    /** OTD */
    private String otd;

    /** 특수조건 */
    private String specialConditionYn;

    /** 키 유무 */
    private String keyYn;

    /** 키 상세조건 */
    private String keyDetail;

    // ==================== 좌표 갱신용 추가 필드 ====================

    /** 물류센터코드 (좌표 갱신용) */
    private String dccode;

    /** 고객사코드 (좌표 갱신용) */
    private String storerkey;

    /** 거래처 유형 (좌표 갱신용) */
    private String custType;
}
