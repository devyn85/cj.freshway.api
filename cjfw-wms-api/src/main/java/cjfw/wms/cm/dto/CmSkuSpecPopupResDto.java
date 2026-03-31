package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.12 
 * @description : 상품 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "상품 검색 응답")
public class CmSkuSpecPopupResDto {
	/** 회사코드 */
	@Schema(description = "회사코드", nullable = false, example = "")
	private String storerkey;
	
	/** 카테고리 */
	@Schema(description = "카테고리", nullable = false, example = "")
	private String speccategory;
	
	/** 상품분류코드 */
	@Schema(description = "상품분류코드", nullable = false, example = "")
	private String speccode;
	
	/** 설명 */
	@Schema(description = "설명", nullable = false, example = "")
	private String specdescr;
	
	/** 설명 */
	@Schema(description = "설명", nullable = false, example = "")
	private String specclass;
	
	/** 설명 */
	@Schema(description = "설명", nullable = false, example = "")
	private String spectype;
	
	/** 상품그룹 */
	@Schema(description = "상품그룹", nullable = false, example = "")
	private String skugroup;
	
	/** 상품분류코드와 이름 */
	@Schema(description = "상품분류코드와 이름", nullable = false, example = "")
	private String spec;
	
	/** Grid 표현시 rowId(프로그램코드) */
	@Schema(description = "Grid 표현시 rowId(코드)", nullable = false, example = "")
	private String rowId;
	
	/** Grid Tree 참조용 상위 프로그램코드 */
	@Schema(description = "Grid Tree 참조용 상위 코드", example = "")
	private String refUpperSpecCode;
}