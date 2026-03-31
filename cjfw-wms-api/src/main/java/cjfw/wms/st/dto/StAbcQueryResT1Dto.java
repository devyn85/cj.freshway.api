package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.11.12
 * @description : 재고 > 재고운영 > ABC 분석 분석_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "재고 > 재고운영 > ABC 분석 분석_탭 Response DTO")
public class StAbcQueryResT1Dto extends CommonProcedureDto {
	
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 저장조건명 */
	@Schema(description = "저장조건명")
	private String storagetypeNm;

	/** 박스입수 */
	@Schema(description = "박스입수")
	private BigDecimal qtyperbox;

	/** 현재고:수량 */
	@Schema(description = "현재고:수량")
	private BigDecimal qty;

	/** 현재고:단위 */
	@Schema(description = "현재고:단위")
	private String uom;

	/** 현재고:박스수량 */
	@Schema(description = "현재고:박스수량")
	private BigDecimal boxQty;

	/** 현재고:EA수량 */
	@Schema(description = "현재고:EA수량")
	private BigDecimal eaQty;

	/** 입고:총수량 */
	@Schema(description = "입고:총수량")
	private BigDecimal dpQty;

	/** 입고:횟수 */
	@Schema(description = "입고:횟수")
	private BigDecimal dpCnt;

	/** 입고:평균 */
	@Schema(description = "입고:평균")
	private BigDecimal dpAvg;

	/** 출고:총수량 */
	@Schema(description = "출고:총수량")
	private BigDecimal wdQty;

	/** 출고:횟수 */
	@Schema(description = "출고:횟수")
	private BigDecimal wdCnt;

	/** 출고:평균 */
	@Schema(description = "출고:평균")
	private BigDecimal wdAvg;

	/** 재고:총수량 */
	@Schema(description = "재고:총수량")
	private BigDecimal stQty;

	/** 재고:횟수 */
	@Schema(description = "재고:횟수")
	private BigDecimal stCnt;

	/** 재고:평균 */
	@Schema(description = "재고:평균")
	private BigDecimal stAvg;

	/** ABC_QUERY */
	@Schema(description = "ABC_QUERY")
	private String abcQuery;

	/** SHELF_RACK_BOX */
	@Schema(description = "SHELF_RACK_BOX")
	private BigDecimal shelfRackBox;

	/** F1_RACK_BOX */
	@Schema(description = "F1_RACK_BOX")
	private BigDecimal f1RackBox;

	/** F2_RACK_BOX */
	@Schema(description = "F2_RACK_BOX")
	private BigDecimal f2RackBox;
	
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";

}
