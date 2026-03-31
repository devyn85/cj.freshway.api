package cjfw.wms.cm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 기준정보 > 사용자및센터정보 > 물류센터관리 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "물류센터관리 조회 응답")
public class CmDcManagerResDto {
	/** 데이터 번호 */
    @Schema(description = "데이터 번호")
    private String serialKey;

    /** 센터 코드 */
    @Schema(description = "센터 코드", example = "2650")
    private String dcCode;

    /** 센터명 */
    @Schema(description = "센터명")
    private String dcName;

    /** 접속 서버 */
    @Schema(description = "접속 서버")
    private String connServer;

    /** 접속 데이터베이스 */
    @Schema(description = "접속 데이터베이스")
    private String connDb;

    /** 접속 유형 */
    @Schema(description = "접속 유형")
    private String connType;

    /** 거래처 유형 */
    @Schema(description = "거래처 유형")
    private String dcType;

    /** 대금 청구 거래처 (실 배송이 아닌 대금 청구 거래처) */
    @Schema(description = "대금 청구 거래처 (실 배송이 아닌 대금 청구 거래처)")
    private String billToCustKey;

    /** 거래처명 */
    @Schema(description = "거래처명")
    private String description;

    /** 거래처 그룹 */
    @Schema(description = "거래처 그룹")
    private String dcGroup;

    /** 회차 */
    @Schema(description = "회차")
    private BigDecimal priority;

    /** 권역 구분 */
    @Schema(description = "권역 구분")
    private String districtType;

    /** 권역 코드 */
    @Schema(description = "권역 코드")
    private String districtCode;

    /** 경유지 */
    @Schema(description = "경유지")
    private String route;

    /** 기본 배송 택배사 */
    @Schema(description = "기본 배송 택배사")
    private String courier;

    /** 기본 운송장 유형 */
    @Schema(description = "기본 운송장 유형")
    private String invoiceType;

    /** 주 출고처 */
    @Schema(description = "주 출고처")
    private String dlvDcCode;

    /** 사업주명 */
    @Schema(description = "사업주명")
    private String owner;

    /** 국가 코드 */
    @Schema(description = "국가 코드")
    private String country;

    /** 주/도 */
    @Schema(description = "주/도")
    private String state;

    /** 시/읍/면 */
    @Schema(description = "시/읍/면")
    private String city;

    /** 우편번호 */
    @Schema(description = "우편번호")
    private String zipCode;

    /** 기본 주소 */
    @Schema(description = "기본 주소")
    private String address1;

    /** 상세 주소 */
    @Schema(description = "상세 주소")
    private String address2;

    /** 전화번호1 */
    @Schema(description = "전화번호1")
    private String phone1;

    /** 전화번호2 */
    @Schema(description = "전화번호2")
    private String phone2;

    /** 팩스번호 */
    @Schema(description = "팩스번호")
    private String fax;

    /** 사업자 등록 번호 */
    @Schema(description = "사업자 등록 번호")
    private String vatNo;

    /** 사업자 등록 사업주명 */
    @Schema(description = "사업자 등록 사업주명")
    private String vatOwner;

    /** 사업자 등록 업태 */
    @Schema(description = "사업자 등록 업태")
    private String vatType;

    /** 사업자 등록 종목 */
    @Schema(description = "사업자 등록 종목")
    private String vatCategory;

    /** 사업자 등록 기본 주소 */
    @Schema(description = "사업자 등록 기본 주소")
    private String vatAddress1;

    /** 사업자 등록 상세 주소 */
    @Schema(description = "사업자 등록 상세 주소")
    private String vatAddress2;

    /** 사업자 등록 전화번호 */
    @Schema(description = "사업자 등록 전화번호")
    private String vatPhone;

    /** 사업자 등록 팩스번호 */
    @Schema(description = "사업자 등록 팩스번호")
    private String vatFax;

    /** 관리 사원명1 */
    @Schema(description = "관리 사원명1")
    private String empName1;

    /** 관리 사원명2 */
    @Schema(description = "관리 사원명2")
    private String empName2;

    /** 관리 사원 전화번호1 */
    @Schema(description = "관리 사원 전화번호1")
    private String empPhone1;

    /** 관리 사원 전화번호2 */
    @Schema(description = "관리 사원 전화번호2")
    private String empPhone2;

    /** 거래 시작 일자 */
    @Schema(description = "거래 시작 일자", example = "YYYYMMDD")
    private String startDate;

    /** 거래 종료 일자 */
    @Schema(description = "거래 종료 일자", example = "YYYYMMDD")
    private String endDate;

    /** 업무 시작 시간 (24시간 형식, 예: 2300) */
    @Schema(description = "업무 시작 시간 (24시간 형식, 예: 2300)", example = "0900")
    private String openTime;

    /** 업무 종료 시간 (24시간 형식, 예: 2300) */
    @Schema(description = "업무 종료 시간 (24시간 형식, 예: 2300)", example = "1800")
    private String closingTime;

    /** 기본 표시 FLOAT MASK */
    @Schema(description = "기본 표시 FLOAT MASK")
    private String floatMask;

    /** 기타 사항 */
    @Schema(description = "기타 사항")
    private String memo;

    /** 인터페이스 파일명 */
    @Schema(description = "인터페이스 파일명")
    private String ifFileName;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제 여부 */
    @Schema(description = "삭제 여부")
    private String delYn;

    /** 최초 등록 시간 */
    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    /** 최종 변경 시간 */
    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    /** 최초 등록자 */
    @Schema(description = "최초 등록자")
    private String addWho;

    /** 최종 변경자 */
    @Schema(description = "최종 변경자")
    private String editWho;

    /** SMS 관리 여부 */
    @Schema(description = "SMS 관리 여부")
    private String smsYn;

    /** 정렬 순서 */
    @Schema(description = "정렬 순서", example = "1")
    private BigDecimal arrOrd;

    /** 조달저장도착시간 */
    @Schema(description = "조달저장도착시간", example = "2400")
    private String procStorageArrivedtime;

    /** 조달일배도착시간 */
    @Schema(description = "조달일배도착시간", example = "2400")
    private String procSamedayArrivedtime;

    /** 조달저장입차시간 */
    @Schema(description = "조달저장입차시간", example = "2400")
    private String procStorageIntime;

    /** 조달일배입차시간 */
    @Schema(description = "조달일배입차시간", example = "2400")
    private String procSamedayIntime;

    /** 위도 */
    @Schema(description = "위도", example = "65.123456")
    private BigDecimal latitude;

    /** 경도 */
    @Schema(description = "경도", example = "123.123456")
    private BigDecimal longitude;

    /** 최종 변경자 */
    @Schema(description = "최종 변경자")
    private String editWhoNm;

    /** 최초 등록자 */
    @Schema(description = "최초 등록자")
    private String addWhoNm;

    /** 실센터여부 */
    @Schema(description = "실센터여부", example = "Y")
    private String realDcYn;

    /** 노출여부 */
    @Schema(description = "노출여부", example = "Y")
    private String displayYn;

    /** 롤테이너 적재 중량 */
    @Schema(description = "롤테이너 적재 중량", example = "100")
    private BigDecimal rolltainerWeight;

    /** 롤테이너 적재 체적 */
    @Schema(description = "롤테이너 적재 체적", example = "100")
    private BigDecimal rolltainerCube;
    
    /** 반경 */
    @Schema(description = "반경", example = "100")
    private BigDecimal radius;
    
    /** 체류시간 */
    @Schema(description = "체류시간", example = "100")
    private BigDecimal stytime;
}