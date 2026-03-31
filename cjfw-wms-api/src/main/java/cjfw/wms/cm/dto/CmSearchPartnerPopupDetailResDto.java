package cjfw.wms.cm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.24 
 * @description : 협력사 단건 조회 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "협력사 단건 조회 응답 DTO")
public class CmSearchPartnerPopupDetailResDto {
	@Schema(description = "거래처 유형명")
    private String custTypeDescr;

    @Schema(description = "거래처 그룹명")
    private String custGroupDescr;

    @Schema(description = "데이터번호")
    private String serialKey;

    @Schema(description = "고객사코드")
    private String storerKey;

    @Schema(description = "거래처코드")
    private String custKey;

    @Schema(description = "거래처 유형")
    private String custType;

    @Schema(description = "거래처 등급")
    private String custGrade;

    @Schema(description = "실제 고객사 시스템 거래처 코드")
    private String storerCustKey;

    @Schema(description = "상위 거래처")
    private String parentCustKey;

    @Schema(description = "영업그룹")
    private String saleGroup;

    @Schema(description = "영업부서")
    private String saleDepartment;

    @Schema(description = "대금청구 거래처")
    private String billToCustKey;

    @Schema(description = "거래처명")
    private String description;

    @Schema(description = "거래처 그룹")
    private String custGroup;

    @Schema(description = "우선순위")
    private Integer priority;

    @Schema(description = "권역구분")
    private String districtType;

    @Schema(description = "권역코드")
    private String districtCode;

    @Schema(description = "경유지")
    private String route;

    @Schema(description = "기본배송택배사")
    private String courier;

    @Schema(description = "기본운송장유형")
    private String invoiceType;

    @Schema(description = "주출고처 코드")
    private String dlvDcCode;

    @Schema(description = "사업주명")
    private String owner;

    @Schema(description = "타임존")
    private String timezone;

    @Schema(description = "국가코드")
    private String country;

    @Schema(description = "주/도")
    private String state;

    @Schema(description = "시/읍/면")
    private String city;

    @Schema(description = "우편번호")
    private String zipcode;

    @Schema(description = "기본주소")
    private String address1;

    @Schema(description = "상세주소")
    private String address2;

    @Schema(description = "확장주소3")
    private String address3;

    @Schema(description = "확장주소4")
    private String address4;

    @Schema(description = "전화번호1")
    private String phone1;

    @Schema(description = "전화번호2")
    private String phone2;

    @Schema(description = "팩스번호")
    private String fax;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "사업자 등록번호")
    private String vatNo;

    @Schema(description = "사업자 등록 사업주명")
    private String vatOwner;

    @Schema(description = "사업자 업태")
    private String vatType;

    @Schema(description = "사업자 명칭")
    private String vatName;

    @Schema(description = "사업자 종목")
    private String vatCategory;

    @Schema(description = "사업자 기본주소")
    private String vatAddress1;

    @Schema(description = "사업자 상세주소")
    private String vatAddress2;

    @Schema(description = "사업자 확장주소3")
    private String vatAddress3;

    @Schema(description = "사업자 확장주소4")
    private String vatAddress4;

    @Schema(description = "사업장 전화번호")
    private String vatPhone;

    @Schema(description = "사업장 팩스번호")
    private String vatFax;

    @Schema(description = "관리 사원명1")
    private String empName1;

    @Schema(description = "관리 사원명2")
    private String empName2;

    @Schema(description = "관리 사원명3")
    private String empName3;

    @Schema(description = "관리 사원명4")
    private String empName4;

    @Schema(description = "관리 사원명5")
    private String empName5;

    @Schema(description = "관리 사원 전화번호1")
    private String empPhone1;

    @Schema(description = "관리 사원 전화번호2")
    private String empPhone2;

    @Schema(description = "관리 사원 전화번호3")
    private String empPhone3;

    @Schema(description = "관리 사원 전화번호4")
    private String empPhone4;

    @Schema(description = "관리 사원 전화번호5")
    private String empPhone5;

    @Schema(description = "거래 시작 일자 (YYYYMMDDHH24MISS)")
    private String startDate;

    @Schema(description = "거래 종료 일자 (YYYYMMDDHH24MISS)")
    private String endDate;

    @Schema(description = "업무 시작 시간 (HH24MI)")
    private String openTime;

    @Schema(description = "업무 종료 시간 (HH24MI)")
    private String closingTime;

    @Schema(description = "진입조건")
    private String entryCondition;

    @Schema(description = "진입 제한 차량")
    private String limitCar;

    @Schema(description = "상하역 분당 박스수")
    private BigDecimal loadBoxPerMin;

    @Schema(description = "상하역 도구 코드")
    private String loadToolType;

    @Schema(description = "위도")
    private BigDecimal latitude;

    @Schema(description = "경도")
    private BigDecimal longitude;

    @Schema(description = "구매 유형")
    private String poType;

    @Schema(description = "판매 유형")
    private String soType;

    @Schema(description = "입고 전략")
    private String inStrategy;

    @Schema(description = "출고 전략")
    private String outStrategy;

    @Schema(description = "검품 유형")
    private String qcType;

    @Schema(description = "검수 유형")
    private String inspectType;

    @Schema(description = "거래처 전략1")
    private String custStrategy1;

    @Schema(description = "거래처 전략2")
    private String custStrategy2;

    @Schema(description = "거래처 전략3")
    private String custStrategy3;

    @Schema(description = "거래처 전략4")
    private String custStrategy4;

    @Schema(description = "거래처 전략5")
    private String custStrategy5;

    @Schema(description = "기본 표시 Float Mask")
    private String floatMask;

    @Schema(description = "통화 코드")
    private String currency;

    @Schema(description = "기타 정보1")
    private String other01;

    @Schema(description = "기타 정보2")
    private String other02;

    @Schema(description = "기타 정보3")
    private String other03;

    @Schema(description = "기타 정보4")
    private String other04;

    @Schema(description = "기타 정보5")
    private String other05;

    @Schema(description = "메모 사항")
    private String memo;

    @Schema(description = "인터페이스 파일명")
    private String ifFileName;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "데이터 흐름 제어")
    private String trafficCop;

    @Schema(description = "아카이브 제어")
    private String archiveCop;

    @Schema(description = "최초 등록 시간 (YYYYMMDDHH24MISS)")
    private String addDate;

    @Schema(description = "최종 변경 시간 (YYYYMMDDHH24MISS)")
    private String editDate;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;
}
