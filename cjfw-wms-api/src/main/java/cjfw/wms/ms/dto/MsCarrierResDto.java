package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author		: YeoSeungCheol (pw6375@cj.net) 
 * @date		: 2025.07.17 
 * @description : 운송사정보(목록) 조회 응답 DTO 
 * @issues		:<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "운송사정보(목록) 조회 응답 DTO")
public class MsCarrierResDto {
	@Schema(description = "데이터번호")
	private String serialKey;
	
	@Schema(description = "고객사코드")
	private String storerKey;
	
	@Schema(description = "거래처코드")
	private String carrierKey;
	
	@Schema(description = "거래처 유형")
	private String carrierType;
	
	@Schema(description = "대금청구거래처")
	private String billtoCarrierKey;
	
	@Schema(description = "거래처명")
	private String description;
	
	@Schema(description = "거래처그룹")
	private String carrierGroup;
	
	@Schema(description = "우선순위")
	private String priority;
	
	@Schema(description = "사업주명")
	private String owner;
	
	@Schema(description = "국가코드")
	private String country;
	
	@Schema(description = "주,도")
	private String state;
	
	@Schema(description = "시,읍,면")
	private String city;
	
	@Schema(description = "우편번호")
	private String zipcode;
	
	@Schema(description = "기본주소")
	private String address1;
	
	@Schema(description = "상세주소")
	private String address2;
	
	@Schema(description = "전화번호1")
	private String phone1;
	
	@Schema(description = "전화번호2")
	private String phone2;
	
	@Schema(description = "팩스번호")
	private String fax;
	
	@Schema(description = "사업자 등록 등록번호")
	private String vatno;
	
	@Schema(description = "사업자 등록 사업주명")
	private String vatowner;
	
	@Schema(description = "사업자 등록 업태")
	private String vattype;
	
	@Schema(description = "사업자 등록 종목")
	private String vatcategory;
	
	@Schema(description = "사업자 등록 기본주소")
	private String vataddress1;
	
	@Schema(description = "사업자 등록 상세주소")
	private String vataddress2;
	
	@Schema(description = "사업자 등록 전화번호")
	private String vatphone;
	
	@Schema(description = "사업자 등록 팩스번호")
	private String vatfax;
	
	@Schema(description = "관리 사원명1")
	private String empname1;
	
	@Schema(description = "관리 사원명2")
	private String empname2;
	
	@Schema(description = "관리 사원 전화번호1")
	private String empphone1;
	
	@Schema(description = "관리 사원 전화번호2")
	private String empphone2;
	
	@Schema(description = "거래 시작 일자")
	private String startdate;
	
	@Schema(description = "거래 종료 일자")
	private String enddate;

	@Schema(description = "업무 시작 시간(24HR)")
	private String opentime;
	
	@Schema(description = "업무 종료 시간(24HR)")
	private String closingtime;
	
	@Schema(description = "기본표시 FLOATMASK")
	private String floatmask;
	
	@Schema(description = "기타사항")
	private String memo;
	
	@Schema(description = "인터페이스파일명")
	private String iffilename;
	
	@Schema(description = "상태")
	private String status;
	
	@Schema(description = "삭제여부")
	private String delYn;
	
	@Schema(description = "위도")
	private String latitude;
	
	@Schema(description = "경도")
	private String longitude;
	
	@Schema(description = "최초등록자")
	private String addWho;
	
	@Schema(description = "최초등록시간")
	private String addDate;
	
	@Schema(description = "최종변경자")
	private String editWho;
	
	@Schema(description = "최종변경시간")
	private String editDate;
	
	@Schema(description = "커스텀 엑스트라 체크박스")
	private String customRowCheckYn="N";
}
