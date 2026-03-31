package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.02
 * @description : 재고 > 재고조사 > 재고조사등록 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조사 > 재고조사등록 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInquiryReqDto extends CommonProcedureDto {
	
	/* 창고 */
	@Schema(description = "창고")
	private String organize;
	
	
	/* 창고 */
	@Schema(description = "창고")
	private String dccode;	
	
	/* 창고-멀티 */
	@MultiSearch
    @Schema(description = "창고-멀티")
    private List<String> organizeMulti;  
	
	/* 창고명 */
	@Schema(description = "창고명")
	private String organizename;
	
	/* 조사일자 From */
	@Schema(description = "조사일자 From")
	private String fromInquirydt;

	/* 조사일자 To */
	@Schema(description = "조사일자 To")
	private String toInquirydt;

	/* 재고조사 별칭 */
	@Schema(description = "재고조사 별칭")
	private String inquiryAlias;
	
	/* 재고조사 번호 */
	@Schema(description = "재고조사 번호")
	private String inquiryno;
	
	/* 진행상태 */
	@Schema(description = "진행상태")
	private String status;
	
	/* 실사구분 */
	@Schema(description = "실사구분")
	private String lottype;
	
	/* 창고구분 */
	@Schema(description = "창고구분")
	private String wharea;

	/* 피킹존 From */
	@Schema(description = "피킹존 From")
	private String fromZone;

	/* 피킹존 To */
	@Schema(description = "피킹존 To")
	private String toZone;

	/* inquirytype */
	@Schema(description = "inquirytype")
	private String inquirytype;

	/* 로케이션 From */
	@Schema(description = "로케이션 From")
	private String fromLoc;

	/* 로케이션 To */
	@Schema(description = "로케이션 To")
	private String toLoc;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;  
	
	/* 조사일자 */
	@Schema(description = "조사일자")
	private String inquirydt;
	
	
	/* 회차 */
	@Schema(description = "회차")
	private String priority;

	/* saveDataList */
	@Schema(description = "savDataeList")
	private List<StInquiryResDto> saveDataList;
	
	/* saveDetailList */
	@Schema(description = "saveDetailList")
	private List<StInquiryResDetailDto> saveDetailList;
	
    /** 조사명 */
    @Schema(description = "조사명")
    private String inquiryName;
}
