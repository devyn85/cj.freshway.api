package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingAddress;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.annotation.MaskingTelno;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 오더리스트 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "퀵접수(VSR)및처리 오더리스트 DTO")
public class WdQuickResAPI02Dto extends CommonDto {

    /** 주문 번호 */
    @Schema(description = "주문 번호")
    private String serialNumber;

    /** 주문 상태 */
    @Schema(description = "주문 상태")
    private String orderState;

    /** 주문 일시 */
    @Schema(description = "주문 일시")
    private String orderDate;

    /** 접수자 이름ㆍ상호 */
    @Schema(description = "접수자 이름ㆍ상호")
    private String customerName;

    /** 접수자 부서 */
    @Schema(description = "접수자 부서")
    private String customerDepartment;

    /** 차량 타입 */
    @Schema(description = "차량 타입")
    private String carType;

    /** 배송 타입 */
    @Schema(description = "배송 타입")
    private String deliveryType;

    /** 출발지 부서 */
    @Schema(description = "출발지 부서")
    private String departureDepartment;

    /** 출발지 담당자 */
    @Schema(description = "출발지 담당자")
    private String departureStaff;

    /** 출발지 고객명 */
    @Schema(description = "출발지 고객명")
    private String departureCustomer;

    /** 출발지 동명 */
    @Schema(description = "출발지 동명")
    private String departureDongName;

    /** 출발지 주소 */
    @MaskingAddress
    @Schema(description = "출발지 주소")
    private String departureAddress;

    /** 도착지 고객명 */
    @Schema(description = "도착지 고객명")
    private String destinationCustomer;

    /** 도착지 동명 */
    @Schema(description = "도착지 동명")
    private String destinationDongName;

    /** 도착지 주소 */
    @MaskingAddress
    @Schema(description = "도착지 주소")
    private String destinationAddress;

    /** 총 비용 */
    @Schema(description = "총 비용")
    private BigDecimal totalCost;

    /** 주문 요약 */
    @Schema(description = "주문 요약")
    private String summary;

    /** 기본 요금 */
    @Schema(description = "기본 요금")
    private BigDecimal basicCost;

    /** 추가 요금 */
    @Schema(description = "추가 요금")
    private BigDecimal additionCost;

    /** 할인 금액 */
    @Schema(description = "할인 금액")
    private BigDecimal discountCost;

    /** 배송 비용 */
    @Schema(description = "배송 비용")
    private BigDecimal deliveryCost;

    /** 해피콜 여부 */
    @Schema(description = "해피콜 여부")
    private String happyCall;

    /** 고객 코드 */
    @Schema(description = "고객 코드")
    private String customerCode;

    /** 라이더 코드 */
    @Schema(description = "라이더 코드")
    private String riderCode;

    /** 라이더 ID */
    @Schema(description = "라이더 ID")
    private String riderId;

    /** 라이더 이름 */
    @MaskingName
    @Schema(description = "라이더 이름")
    private String riderName;

    /** 라이더 연락처 */
    @MaskingTelno
    @Schema(description = "라이더 연락처")
    private String riderMobile;

    /** 라이더 경도 */
    @Schema(description = "라이더 경도")
    private String riderLon;

    /** 라이더 위도 */
    @Schema(description = "라이더 위도")
    private String riderLat;

    /** 이동 거리 */
    @Schema(description = "이동 거리")
    private String distince;

    /** 주문 등록 타입 */
    @Schema(description = "주문 등록 타입")
    private String orderRegistType;

    /** 완료 시간 */
    @Schema(description = "완료 시간")
    private String completeTime;

    /** 기타 필드 1 */
    @Schema(description = "기타 필드 1")
    private String oEtc1;

    /** 기타 필드 2 */
    @Schema(description = "기타 필드 2")
    private String oEtc2;

    /** 기타 필드 3 */
    @Schema(description = "기타 필드 3")
    private String oEtc3;

    /** 기타 필드 4 */
    @Schema(description = "기타 필드 4")
    private String oEtc4;

    /** 기타 필드 5 */
    @Schema(description = "기타 필드 5")
    private String oEtc5;

    /** 기타 필드 6 */
    @Schema(description = "기타 필드 6")
    private String oEtc6;

    /** 기타 필드 7 */
    @Schema(description = "기타 필드 7")
    private String oEtc7;

    /** 기타 필드 8 */
    @Schema(description = "기타 필드 8")
    private String oEtc8;

    /** 기타 필드 9 */
    @Schema(description = "기타 필드 9")
    private String oEtc9;

    /** 기타 필드 10 */
    @Schema(description = "기타 필드 10")
    private String oEtc10;
    
    /** 2.물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 3.정산월 */
    @Schema(description = "정산월")
    private String sttlYm;

    /** 4.퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;    
    
}
