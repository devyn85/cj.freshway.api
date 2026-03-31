package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.10.21
 * @description : KIT상품 계획등록 결과 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21   고혜미(laksjd0606@cj.net)  생성
 */
@Data
@Schema(description = "KIT상품 계획등록 결과 DTO")
public class StKitPlanResT3Dto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skuNm;
    
    /** 입고예정일 */
    @Schema(description = "입고예정일")
    private String deliverydate;
    
    /** PO */
    @Schema(description = "PO")
    private BigDecimal orderqty;

    /** 업체확정 */
    @Schema(description = "업체확정")
    private BigDecimal vendorconfirmqty;

    /** 입고확정 */
    @Schema(description = "입고확정")
    private BigDecimal confirmqty;
    
}
