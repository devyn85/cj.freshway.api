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
 * @date : 2025.09.11
 * @description : 재고 > 재고조정 > 상품이력번호변경 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고 > 재고조정 > 상품이력번호변경 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StConvertSNReqDto extends CommonProcedureDto {
	
	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 재고유무 */
	@Schema(description = "재고유무")
	private String stockyn ;
	
	/* 상품코드 */
	@Schema(description = "상품코드")
	private String sku;
	
    /* 상품코드-멀티 */
	@MultiSearch
    @Schema(description = "상품코드-멀티")
    private List<List<String>> skuMulti;

	/* 상품분류 */
	@Schema(description = "상품분류")
	private String skugroup;
	
    /* 상품분류-멀티 */
	@MultiSearch
    @Schema(description = "상품분류-멀티")
    private List<List<String>> skugroupMulti;
	
	/* B/L번호 */
	@Schema(description = "B/L번호")
	private String blno;	

	/* 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;

	/* 계약업체 */
	@Schema(description = "계약업체")
	private String contractcompany;
	
    /* 계약업체-멀티 */
	@MultiSearch
    @Schema(description = "계약업체-멀티")
    private List<List<String>> contractcompanyMulti;

	/* fromzone */
	@Schema(description = "fromzone")
	private String fromzone;

	/* tozone */
	@Schema(description = "tozone")
	private String tozone;

	/* storerkey */
	@Schema(description = "storerkey")
	private String storerkey;

	/* fromloc */
	@Schema(description = "fromloc")
	private String fromloc;

	/* toloc */
	@Schema(description = "toloc")
	private String toloc;

	/* slipdtFrom */
	@Schema(description = "slipdtFrom")
	private String slipdtFrom;

	/* slipdtTo */
	@Schema(description = "slipdtTo")
	private String slipdtTo;
	
	/* 메인그리드 저장 리스트 */
	@Schema(description = "메인그리드 저장 리스트")
	List<StConvertSNResDto> saveDataList;
	
}
