package cjfw.wms.st.dto;

import java.math.BigDecimal;
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
 * @date : 2025.10.28
 * @description : 재고 > 재고조사 > 재고 실사 지시 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조사 > 재고 실사 지시 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StInquiryInplanReqDto extends CommonProcedureDto {
	
	/* saveList */
	@Schema(description = "saveDataList")
	private List<StInquiryInplanResDto> saveDataList;	
	
    /** 고정센터코드 */
    @Schema(description = "고정센터코드")
    private String fixdccode;

	/* 창고 */
	@Schema(description = "창고")
	private String organize;
	
	/* 창고-멀티 */
	@MultiSearch
    @Schema(description = "창고-멀티")
    private List<String> organizeMulti;  
	
	/* 재고속성 */
	@Schema(description = "재고속성")
	private String stockgrade;
	
	/* 피킹존 From */
	@Schema(description = "피킹존 From")
	private String fromzone;

	/* 피킹존 To */
	@Schema(description = "피킹존 To")
	private String tozone;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
	/* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;  
	
	/* 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/* 로케이션 종류 */
	@Schema(description = "로케이션 종류")
	private String loccategory;
	
	/* 로케이션 From */
	@Schema(description = "로케이션 From")
	private String fromloc;

	/* 로케이션 To */
	@Schema(description = "로케이션 To")
	private String toloc;
	
	/* 제외존 */
	@Schema(description = "제외존")
	private String excludeZone;
	
	/* 제외존-멀티 */
	@MultiSearch
    @Schema(description = "제외존-멀티")
    private List<String> excludeZoneMulti;  

	/* 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;
	
	/* 실사구분 */
	@Schema(description = "실사구분")
	private String lottype;
	
	/* 실사구분명 */
	@Schema(description = "실사구분명")
	private String lottypeNm;
	
	/* 재고조사 별칭 */
	@Schema(description = "재고조사 별칭")
	private String inquiryAlias;
	
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율")
    private BigDecimal usebydatefreert;
    
    /* duration */
    @Schema(description = "duration")
    private String duration;    
}
