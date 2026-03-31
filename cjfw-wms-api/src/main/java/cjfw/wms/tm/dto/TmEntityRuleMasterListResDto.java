package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.11 
 * @description : 통합수당관리 조회Dto 기능을 구현한 Java Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.11 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송클레임정보 조회 Dto")
public class TmEntityRuleMasterListResDto extends CommonDto {

	 /** 데이터번호 */
    @Schema(description = "데이터번호")
    private String serialKey;

    /** 정산항목코드 */
    @Schema(description = "정산항목코드")
    private String sttlItemCd;
    
    /** 정산항목코드 */
    @Schema(description = "정산항목코드")
    private String sttlItemName;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;
    @Schema(description = "센터코드")
    private String code;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String area;

    /** 운송사코드 */
    @Schema(description = "운송사코드")
    private String courier;

    /** 톤수 */
    @Schema(description = "톤수")
    private String ton;

    /** 마감유형 */
    @Schema(description = "마감유형")
    private String closeType;

    /** 기준 */
    @Schema(description = "기준")
    private String base;

    /** 금액 */
    @Schema(description = "금액")
    private BigDecimal amount;

    /** 적용시작일자(YYYYMMDD) */
    @Schema(description = "적용시작일자")
    private String fromDate;

    /** 적용종료일자(YYYYMMDD) */
    @Schema(description = "적용종료일자")
    private String toDate;

    /** 비고 */
    @Schema(description = "비고")
    private String rmk;

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
    

    /** 최초등록자 */
    @Schema(description = "최초등록자")
    private String addWhoId;

    /** 최종변경자 */
    @Schema(description = "최종변경자")
    private String editWhoId;
    /** 행 상태 */
    @Schema(description = "행 상태")
    private String rowStatus;
    
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String areaNm;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String courierNm;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String errMsg;
    
    /** 계약유형 */
    @Schema(description = "계약유형")
    private String errYn;
    
    private String valFlag;
    
	private String customRowCheckYn = "N";

	private String chk1;
	
	private String staDt;
	
	private String endDt;
}
