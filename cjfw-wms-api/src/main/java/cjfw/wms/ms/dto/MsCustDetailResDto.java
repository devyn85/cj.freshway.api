package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.09 
 * @description : 고객정보(New) 상세정보 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsCustDetailResDto {
			
	/** 데이터번호 */
	@Schema(description = "데이터번호", example = "")
	private String serialkey;

	/** 고객사코드 */
	@Schema(description = "고객사코드", example = "")
	private String storerkey;

	/** 거래처코드 */
	@Schema(description = "거래처코드", example = "")
	private String custkey;

	/** 거래처명 */
	@Schema(description = "거래처명", example = "")
	private String description;

	/** 고객사 시스템에 등록되어 있는 거래처 코드 */
	@Schema(description = "고객사 시스템에 등록되어 있는 거래처 코드", example = "")
	private String storercustkey;

	/** 대금청구거래처 실배송이 아닌 대금 청구 거래처 */
	@Schema(description = "대금청구거래처 실배송이 아닌 대금 청구 거래처", example = "")
	private String billtocustkey;

	/** 거래처 유형 */
	@Schema(description = "거래처 유형", example = "")
	private String custtype;

	/** 거래처 그룹 */
	@Schema(description = "거래처 그룹", example = "")
	private String custgroup;

	/** 우선순위 */
	@Schema(description = "우선순위", example = "")
	private String priority;

	/** 권역구분 */
	@Schema(description = "권역구분", example = "")
	private String districttype;

	/** 권역 */
	@Schema(description = "권역", example = "")
	private String districtcode;

	/** 경유지 */
	@Schema(description = "경유지", example = "")
	private String route;

	/** 기본배송택배사 */
	@Schema(description = "기본배송택배사", example = "")
	private String courier;

	/** 기본운송장유형 */
	@Schema(description = "기본운송장유형", example = "")
	private String invoicetype;

	/** 주출고처 */
	@Schema(description = "주출고처", example = "")
	private String dlvDccode;

	/** 사업주명 */
	@Schema(description = "사업주명", example = "")
	private String owner;

	/** 전화번호1 */
	@Schema(description = "전화번호1", example = "")
	private String phone1;

	/** 전화번호2 */
	@Schema(description = "전화번호2", example = "")
	private String phone2;

	/** 팩스번호 */
	@Schema(description = "팩스번호", example = "")
	private String fax;

	/** 우편번호 */
	@Schema(description = "우편번호", example = "")
	private String zipcode;

	/** 국가코드 */
	@Schema(description = "국가코드", example = "")
	private String country;

	/** 주,도 */
	@Schema(description = "주,도", example = "")
	private String state;

	/** 시,읍,면 */
	@Schema(description = "시,읍,면", example = "")
	private String city;

	/** 기본주소 */
	@Schema(description = "기본주소", example = "")
	private String address1;

	/** 상세주소 */
	@Schema(description = "상세주소", example = "")
	private String address2;

	/** 사업자 등록 등록번호 */
	@Schema(description = "사업자 등록 등록번호", example = "")
	private String vatno;

	/** 사업자 등록 사업주명 */
	@Schema(description = "사업자 등록 사업주명", example = "")
	private String vatowner;

	/** 사업자 등록 업태 */
	@Schema(description = "사업자 등록 업태", example = "")
	private String vattype;

	/** 사업자 등록 종목 */
	@Schema(description = "사업자 등록 종목", example = "")
	private String vatcategory;

	/** 사업자 등록 기본주소 */
	@Schema(description = "사업자 등록 기본주소", example = "")
	private String vataddress1;

	/** 사업자 등록 상세주소 */
	@Schema(description = "사업자 등록 상세주소", example = "")
	private String vataddress2;

	/** 사업자 등록 전화번호 */
	@Schema(description = "사업자 등록 전화번호", example = "")
	private String vatphone;

	/** 사업자 등록 팩스번호 */
	@Schema(description = "사업자 등록 팩스번호", example = "")
	private String vatfax;

	/** 관리 사원명1 */
	@Schema(description = "관리 사원명1", example = "")
	private String empname1;

	/** 관리 사원 전화번호1 */
	@Schema(description = "관리 사원 전화번호1", example = "")
	private String empphone1;

	/** 관리 사원명2 */
	@Schema(description = "관리 사원명2", example = "")
	private String empname2;

	/** 관리 사원 전화번호2 */
	@Schema(description = "관리 사원 전화번호2", example = "")
	private String empphone2;

	/** 관리 사원명3 */
	@Schema(description = "관리 사원명3", example = "")
	private String empname3;

	/** 관리 사원 전화번호3 */
	@Schema(description = "관리 사원 전화번호3", example = "")
	private String empphone3;

	/** MA */
	@Schema(description = "MA", example = "")
	private String ma;

	/** MA명 */
	@Schema(description = "MA명", example = "")
	private String maname;

	/** 거래 시작 일자 */
	@Schema(description = "거래 시작 일자", example = "")
	private String startdate;

	/** 거래 종료 일자 */
	@Schema(description = "거래 종료 일자", example = "")
	private String enddate;

	/** 업무 시작 시간(24hr  2300) */
	@Schema(description = "업무 시작 시간(24hr  2300)", example = "")
	private String opentime;

	/** 업무 종료 시간(24hr  2300) */
	@Schema(description = "업무 종료 시간(24hr  2300)", example = "")
	private String closingtime;

	/** 위도 */
	@Schema(description = "위도", example = "")
	private String latitude;

	/** 경도 */
	@Schema(description = "경도", example = "")
	private String longitude;

	/** 진입제한차량 */
	@Schema(description = "진입제한차량", example = "")
	private String limitcar;

	/** 상하역가능한분당박스수 */
	@Schema(description = "상하역가능한분당박스수", example = "")
	private String loadboxpermin;

	/** 상하역도구코드 */
	@Schema(description = "상하역도구코드", example = "")
	private String loadtooltype;

	/** 진입조건 */
	@Schema(description = "진입조건", example = "")
	private String entrycondition;

	/** 기타사항 */
	@Schema(description = "기타사항", example = "")
	private String memo;

	/** 인터페이스파일명 */
	@Schema(description = "인터페이스파일명", example = "")
	private String iffilename;

	/** 상태 */
	@Schema(description = "상태", example = "")
	private String status;

	/** 삭제여부 */
	@Schema(description = "삭제여부", example = "")
	private String delYn;

	/** 기본표시FLOATMASK */
	@Schema(description = "기본표시FLOATMASK", example = "")
	private String floatmask;

	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최초등록자명 */
	@Schema(description = "최초등록자명", example = "")
	private String addwhoname;

	/** 최종등록자명 */
	@Schema(description = "최종등록자명", example = "")
	private String editwhoname;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;

	/** 마감유형 */
	@Schema(description = "마감유형", example = "")
	private String custorderclosetype;

	/** 배송 비효율 거래처여부 */
	@Schema(description = "배송 비효율 거래처여부", example = "")
	private String inefficientYn;

	/** 격오지여부 */
	@Schema(description = "격오지여부", example = "")
	private String distantYn;

	/** 영업그룹 */
	@Schema(description = "영업그룹", example = "")
	private String salegroup;

	/** 사업장 */
	@Schema(description = "사업장", example = "")
	private String saledepartment;

	/** 영업조직  2019-05-17 이름변경 */
	@Schema(description = "영업조직  2019-05-17 이름변경", example = "")
	private String other04;

	/** 거래처전략5 */
	@Schema(description = "거래처전략5", example = "")
	private String custstrategy5;

	/** 2차점 라벨출력 옵션 */
	@Schema(description = "2차점 라벨출력 옵션", example = "")
	private String labelstrategy;

	/** 납품서 하단유형 */
	@Schema(description = "납품서 하단유형", example = "")
	private String invoicetypeBt;

	/** OTD(배송요구시간)1 */
	@Schema(description = "OTD(배송요구시간)1", example = "")
	private String reqdlvtime1From;

	/** OTD(배송요구시간)2 */
	@Schema(description = "OTD(배송요구시간)2", example = "")
	private String reqdlvtime1To;

	/** 도로명주소 */
	@Schema(description = "도로명주소", example = "")
	private String address3;

	/** 도로명주소2 */
	@Schema(description = "도로명주소2", example = "")
	private String address4;

	/** 검수유형 */
	@Schema(description = "검수유형", example = "")
	private String inspecttype;

}