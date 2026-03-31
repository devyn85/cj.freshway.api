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
public class StKitPlanResT2Dto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** KIT상품코드 */
    @Schema(description = "KIT상품코드")
    private String kitSku;
    
    /** KIT상품명 */
    @Schema(description = "KIT상품명")
    private String kitNm;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skuNm;
    
    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;
    
    /** 입고예정일 */
    @Schema(description = "입고예정일")
    private String receivingScheduledDate;
    
    /** 현재고 */
    @Schema(description = "현재고")
    private BigDecimal currentQty;
    
    /** 입고예정 */
    @Schema(description = "입고예정")
    private BigDecimal openqty;
    
    /** 제작필요수량 */
    @Schema(description = "제작필요수량")
    private BigDecimal productionRequiredQty;
    
    /** 생산수량차감현재고 */
    @Schema(description = "생산수량차감현재고")
    private BigDecimal stock;
    
    /** 계 */
    @Schema(description = "계")
    private BigDecimal totalConfirmqty;
    
    /** D+7 */
    @Schema(description = "D+7")
    private BigDecimal confirmqtySum7days;
    
    /** D+14 */
    @Schema(description = "D+14")
    private BigDecimal confirmqtySum14days;
    
    /** D+21 */
    @Schema(description = "D+21")
    private BigDecimal confirmqtySum21days;
    
    /** D-1월 */
    @Schema(description = "D-1월")
    private BigDecimal preMonthUsage1;
    
    /** D-2월 */
    @Schema(description = "D-2월")
    private BigDecimal preMonthUsage2;
    
    /** D-3월 */
    @Schema(description = "D-3월")
    private BigDecimal preMonthUsage3;
    
    


    
}
