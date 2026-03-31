package cjfw.wms.tm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM_INPLAN 벌크 업데이트용 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.28 han@wemeetmobility.com 생성
 * </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TmInplanUpdateDto {

    // ==================== WHERE 조건 (PRIMARY KEY) ====================

    /** 실착지코드 */
    private String truthCustKey;

    /** 전표번호 */
    private String slipNo;

    /** 전표유형 */
    private String docType;

    // ==================== UPDATE 대상 ====================

    /** POP (DELIVERYGROUP) */
    private String deliveryGroup;

    /** 권역그룹 (DISTRICTGROUP) */
    private String districtGroup;

    /** 권역 (DISTRICTCODE) */
    private String districtCode;

    /** 행정동코드 (HJDONG_CD) */
    private String hjdongCd;

    /** 우편번호 (ZIPCODE) */
    private String zipcode;

    /** 관리처코드 (CUSTKEY) */
    private String custKey;

    /** 경로 (ROUTE) */
    private String route;

    /** 차량번호 (CARNO) */
    private String carno;

    /** 계약유형 (CONTRACTTYPE) */
    private String contractType;

    /** 배송유형 (TM_DELIVERYTYPE) */
    private String deliveryType;

    /** 회차 (PRIORITY) */
    private Integer priority;

    /** 중량 (WEIGHT) */
    private Double weight;

    /** 체적 (CUBE) */
    private Double cube;

    /**센터 코드  (DCCODE)  **/
    private String dccdode;

    /**딜리버리 데이티(DELIVERY_DATE) **/
    private String devliveryDate;

    /** 조차 (OUT_GROOUP_CD ) */
    private String outGroupCd;

    /** 출발시간  (IN_TIME ) */
    private String inTime;

    /** 도착시간 (OUT_TIME ) */
    private String outTime;

    /** 배송우선순위 (DlvdDliveryPriority) */
    private String dlvDeliveryPriority;

    /** 배송순위 (DELIVERY_PRIORITY) */
    private String deliveryPriority;

    /** 센터-실착지 직선거리(km) */
    private Double linearDistanceKm;
}
