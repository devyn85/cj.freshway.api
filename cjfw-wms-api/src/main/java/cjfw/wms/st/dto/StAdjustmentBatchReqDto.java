package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.24
 * @description : 일괄재고조정 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.24 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부센터매각출고처리 request dto")
public class StAdjustmentBatchReqDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String fixdccode;

	/** 창고 */
	@Schema(description = "창고")
	private String organize;

	/** 조정일자 */
	@Schema(description = "조정일자")
	private String docdt;

	/**상품코드*/
	@Schema(description = "상품코드")
	private String sku;

	/**저장조건*/
	@Schema(description = "저장조건")
	private String storagetype;

	/**유통기한여부*/
	@Schema(description = "유통기한여부")
	private String lottable01yn;

	/**재고위치*/
	@Schema(description = "재고위치")
	private String stocktype;

	/**재고속성*/
	@Schema(description = "재고속성")
	private String stockgrade;

	/**이력번호*/
	@Schema(description = "이력번호")
	private String serialno;

	/**피킹존*/
	@Schema(description = "피킹존")
	private String zone;

	/**FROM 로케이션*/
	@Schema(description = "FROM 로케이션")
	private String fromloc;

	/**TO 로케이션*/
	@Schema(description = "TO 로케이션")
	private String toloc;

	/**B/L번호*/
	@Schema(description = "B/L번호")
	private String convserialno;

	/**물류귀책배부*/
	@Schema(description = "물류귀책배부")
	private String processmain;

	/**발생사유*/
	@Schema(description = "발생사유")
	private String reasoncode;

	/**처리수량*/
	@Schema(description = "처리수량")
	private BigDecimal tranqty;

	/**귀책*/
	@Schema(description = "귀책")
	private String imputetype;

	/**귀속부서*/
	@Schema(description = "귀속부서")
	private String costcd;

	/**귀속부서명*/
	@Schema(description = "귀속부서명")
	private String costcdname;

	/**거래처*/
	@Schema(description = "거래처")
	private String custkey;

	/**처래처명*/
	@Schema(description = "처래처명")
	private String custname;

	/** 재고조정 저장 */
	@Schema(description = "재고조정 저장")
	private List<StAdjustmentBatchResDto> saveMasterList1;
}
