package cjfw.batch.exdcSkuAmount.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.01 
 * @description : 외부비축재고조회resDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.01 ParkJinWoo 생성
 */
@Data
@Schema(description = "외부비축재고조회 재고금액 저장")
public class ExdcSkuAmountEntity {
	/** 데이터번호 */
	@Schema(description = "데이터번호")
	private BigDecimal serialkey;

	/** 물류센터 코드 */
	@Schema(description = "물류센터 코드")
	private String dccode;

	/** 창고 코드 */
	@Schema(description = "창고(조직) 코드")
	private String organize;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 재고 속성 */
	@Schema(description = "재고 속성", nullable = true, example = "")
	private String stockgrade;

    /** 재고 수량 */
    @Schema(description = "재고 수량", nullable = true, example = "")
    private BigDecimal qty;
	
    /** 단가 */
    @Schema(description = "단가", nullable = true, example = "")
    private BigDecimal stockprice;
    
    /** 재고 금액 */
    @Schema(description = "재고 금액", nullable = true, example = "")
    private BigDecimal stockamt;
}
