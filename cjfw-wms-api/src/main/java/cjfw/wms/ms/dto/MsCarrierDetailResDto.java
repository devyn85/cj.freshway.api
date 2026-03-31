package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author		: YeoSeungCheol (pw6375@cj.net) 
 * @date		: 2025.07.18 
 * @description : 운송사정보 조회(단건) 응답 DTO 
 * @issues		:<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@Schema(description = "운송사정보(단건) 조회 응답 DTO")
public class MsCarrierDetailResDto {
    @Schema(description = "데이터번호")
    private String serialKey;
    
    @Schema(description = "고객사코드")
    private String storerKey;
    
    @Schema(description = "거래처코드")
    private String carrierKey;
    
    @Schema(description = "거래처유형")
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
    
    @Schema(description = "사업자등록번호")
    private String vatno;
    
    @Schema(description = "사업자 대표자명")
    private String vatowner;
    
    @Schema(description = "업태")
    private String vattype;
    
    @Schema(description = "종목")
    private String vatcategory;
    
    @Schema(description = "사업장주소1")
    private String vataddress1;
    
    @Schema(description = "사업장주소2")
    private String vataddress2;
    
    @Schema(description = "사업장전화번호")
    private String vatphone;
    
    @Schema(description = "사업장팩스")
    private String vatfax;
    
    @Schema(description = "담당자명1")
    private String empname1;
    
    @Schema(description = "담당자명2")
    private String empname2;
    
    @Schema(description = "담당자전화번호1")
    private String empphone1;
    
    @Schema(description = "담당자전화번호2")
    private String empphone2;
    
    @Schema(description = "거래시작일자")
    private String startdate;
    
    @Schema(description = "거래종료일자")
    private String enddate;
    
    @Schema(description = "업무시작시간")
    private String opentime;
    
    @Schema(description = "업무종료시간")
    private String closingtime;
    
    @Schema(description = "표시포맷")
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
    
    @Schema(description = "등록자")
    private String addWho;
    
    @Schema(description = "등록일시")
    private String addDate;
    
    @Schema(description = "수정자")
    private String editWho;
    
    @Schema(description = "수정일시")
    private String editDate;
}
