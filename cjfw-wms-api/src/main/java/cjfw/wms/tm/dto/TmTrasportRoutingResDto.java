package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.10.15  
 * @description : 수송경로관리 기능을 구현한 RES DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 ParkYoSep 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수송경로관리 조회 resDto")
public class TmTrasportRoutingResDto {
	
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
     /** 일련번호 */
    @Schema(description = "일련번호")
    private Long serialKey;

    /** 노선번호 */
    @Schema(description = "노선번호")
    private Long routeSerialKey;
    
    /** 운송사 */
    @Schema(description = "운송사")
    private String courier;
    
    /** 운송사 명 */
    @Schema(description = "운송사명")
    private String courierNm;

    /** 노선명 */
    @Schema(description = "노선명")
    private String routeNm;

    /** 출발센터 */
    @Schema(description = "출발센터")
    private String fromDcCode;

    /** 출발창고 */
    @Schema(description = "출발창고")
    private String fromOrganize;
    
    /** 출발창고명*/
    @Schema(description = "출발창고명")
    private String fromOrganizeName;

    /** 도착센터 */
    @Schema(description = "도착센터")
    private String toDcCode;

    /** 도착창고 */
    @Schema(description = "도착창고")
    private String toOrganize;

    /** 도착창고명 */
    @Schema(description = "도착창고명")
    private String toOrganizeName;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 저장구분 */
    @Schema(description = "저장구분")
    private String storageType;

    /** 경유여부 */
    @Schema(description = "경유여부")
    private String routeYn;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 단가 */
    @Schema(description = "단가")
    private BigDecimal price;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간")
    private String addDate;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWho;

    /** 등록자명 */
    @Schema(description = "등록자명")
    private String addWhoName;

    /** 수정자명 */
    @Schema(description = "수정자명")
    private String editWhoName;

    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
    
    /** 에러 메세지 */
    @Schema(description = "에러메세지")
    private String errMsg;
    
    /** 에러코드 */
    @Schema(description = "에러코드")
    private String errYn;
    
    /** TC센터코드 */
    @Schema(description = "TC센터코드")
    private String tcCode;
    
    /** TC센터명 */
    @Schema(description = "TC센터명")
    private String tcName;
    
    /** 중복여부 */
	@Schema(description = "중복여부")
    private String isDuplicate;
	
	/** 위도 */
    @Schema(description = "위도")
    private String latitude;
    
    /** 경도 */
    @Schema(description = "경도")
    private String longitude;
    
    /** 기본주소 */
    @Schema(description = "기본주소")
    private String address1;
    
    /** 상세주소 */
    @Schema(description = "상세주소")
    private String address2;
    
    /** 사용여부 */
    @Schema(description = "사용여부")
    private String useyn;
	
}