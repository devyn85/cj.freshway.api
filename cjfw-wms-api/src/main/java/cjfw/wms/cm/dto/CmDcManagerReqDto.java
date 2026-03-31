package cjfw.wms.cm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Schema(description = "물류센터관리 조회 요청")
public class CmDcManagerReqDto extends CommonDto {
	@Schema(description = "멀티 물류센터코드", example = "2600")
	private String multiDcCode[];
	
	@Schema(description = "물류센터코드", example = "2600")
	private String dcCode;
	
	@Schema(description = "데이터번호 (DB Sequence에 의해 자동 생성)")
    private BigDecimal serialKey;
	
    @Schema(description = "센터명", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String dcName;

    @Schema(description = "접속서버", maxLength = 20)
    private String connServer;

    @Schema(description = "접속데이터베이스", maxLength = 20)
    private String connDb;

    @Schema(description = "접속유형", maxLength = 10)
    private String connType;

    @Schema(description = "거래처 유형", maxLength = 10)
    private String dcType;

    @Schema(description = "대금청구거래처 (실배송이 아닌 대금 청구 거래처)", maxLength = 30)
    private String billToCustKey;

    @Schema(description = "거래처명", maxLength = 200)
    private String description;

    @Schema(description = "거래처그룹", maxLength = 20)
    private String dcGroup;

    @Schema(description = "우선순위", maxLength = 10)
    private String priority;

    @Schema(description = "권역구분", maxLength = 10)
    private String districtType;

    @Schema(description = "권역코드", maxLength = 30)
    private String districtCode;

    @Schema(description = "경유지", maxLength = 10)
    private String route;

    @Schema(description = "기본배송택배사", maxLength = 10)
    private String courier;

    @Schema(description = "기본운송장유형", maxLength = 10)
    private String invoiceType;

    @Schema(description = "주출고처", maxLength = 10)
    private String dlvDcCode;

    @Schema(description = "사업주명", maxLength = 35)
    private String owner;

    @Schema(description = "국가코드", maxLength = 10)
    private String country;

    @Schema(description = "주,도", maxLength = 20)
    private String state;

    @Schema(description = "시,읍,면", maxLength = 50)
    private String city;

    @Schema(description = "우편번호", maxLength = 6)
    private String zipCode;

    @Schema(description = "기본주소", maxLength = 300)
    private String address1;

    @Schema(description = "상세주소", maxLength = 300)
    private String address2;

    @Schema(description = "확장주소3", maxLength = 300)
    private String address3;

    @Schema(description = "확장주소4", maxLength = 300)
    private String address4;

    @Schema(description = "전화번호1", maxLength = 20)
    private String phone1;

    @Schema(description = "전화번호2", maxLength = 20)
    private String phone2;

    @Schema(description = "팩스번호", maxLength = 20)
    private String fax;

    @Schema(description = "사업자 등록 등록번호", maxLength = 20)
    private String vatNo;

    @Schema(description = "사업자 등록 사업주명", maxLength = 50)
    private String vatOwner;

    @Schema(description = "사업자 등록 업태", maxLength = 60)
    private String vatType;

    @Schema(description = "사업자 등록 종목", maxLength = 50)
    private String vatCategory;

    @Schema(description = "사업자 등록 기본주소", maxLength = 300)
    private String vatAddress1;

    @Schema(description = "사업자 등록 상세주소", maxLength = 300)
    private String vatAddress2;

    @Schema(description = "사업자 등록 확장주소3", maxLength = 300)
    private String vatAddress3;

    @Schema(description = "사업자 등록 확장주소4", maxLength = 300)
    private String vatAddress4;

    @Schema(description = "사업자 등록 전화번호", maxLength = 20)
    private String vatPhone;

    @Schema(description = "사업자 등록 팩스번호", maxLength = 20)
    private String vatFax;

    @Schema(description = "관리 사원명1", maxLength = 50)
    private String empName1;

    @Schema(description = "관리 사원명2", maxLength = 50)
    private String empName2;

    @Schema(description = "관리 사원 전화번호1", maxLength = 20)
    private String empPhone1;

    @Schema(description = "관리 사원 전화번호2", maxLength = 20)
    private String empPhone2;

    @Schema(description = "거래 시작 일자", maxLength = 8)
    private String startDate;

    @Schema(description = "거래 종료 일자", maxLength = 8)
    private String endDate;

    @Schema(description = "업무 시작 시간(24hr 2300)", maxLength = 6)
    private String openTime;

    @Schema(description = "업무 종료 시간(24hr 2300)", maxLength = 6)
    private String closingTime;

    @Schema(description = "기본표시FLOATMASK", defaultValue = "#,##0", maxLength = 10)
    private String floatMask;

    @Schema(description = "기타사항", maxLength = 500)
    private String memo;

    @Schema(description = "인터페이스파일명", maxLength = 100)
    private String ifFileName;

    @Schema(description = "상태 (00~99)", defaultValue = "00", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "삭제여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn = "N";

    @Schema(description = "데이터흐름제어", maxLength = 10)
    private String trafficCop;

    @Schema(description = "아카이브제어", maxLength = 1)
    private String archiveCop;

    @Schema(description = "SMS 사용여부", defaultValue = "N", maxLength = 1)
    private String smsYn;

    @Schema(description = "정렬순서")
    private BigDecimal arrOrd;
    
    @Schema(description = "조달저장도착시간", example = "2400")
    private String procStorageArrivedtime;
    
    @Schema(description = "조달일배도착시간", example = "2400")
    private String procSamedayArrivedtime;
    
    @Schema(description = "조달저장입차시간", example = "2400")
    private String procStorageIntime;
    
    @Schema(description = "조달일배입차시간", example = "2400")
    private String procSamedayIntime;
    
    @Schema(description = "위도", example = "65.123456")
    private BigDecimal latitude;
    
    @Schema(description = "경도", example = "123.123456")
    private BigDecimal longitude;
    
    @Schema(description = "실센터여부", example = "Y")
    private String realDcYn;
    
    @Schema(description = "노출여부", example = "Y")
    private String displayYn;
    
    @Schema(description = "롤테이너 적재 중량", example = "100")
    private BigDecimal rolltainerWeight;
    
    @Schema(description = "롤테이너 적재 체적", example = "100")
    private BigDecimal rolltainerCube;
    
    /** 반경 */
    @Schema(description = "반경", example = "100")
    private BigDecimal radius;
    
    /** 체류시간 */
    @Schema(description = "체류시간", example = "100")
    private BigDecimal stytime;
}
