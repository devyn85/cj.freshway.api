package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 광역일배탭 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일배분류서출력 광역일배탭 결과") 
public class WdDeliveryLabelDailyResTab2Dto extends CommonDto {
	/** 출고일자 */
	@Schema(description = "출고일자", example = "2026-02-19")
	private String slipdt;

	/** 주문번호 */
	@Schema(description = "주문번호", example = "ORD20260219-001")
	private String docno;

	/** 물류센터(공급) */
	@Schema(description = "물류센터(공급)", example = "WD01")
	private String wdDccode;

	/** 물류센터명(공급) */
	@Schema(description = "물류센터명(공급)", example = "광역공급센터")
	private String wdDcname;

	/** 물류센터(공급받는) */
	@Schema(description = "물류센터(공급받는)", example = "DP01")
	private String dpDccode;

	/** 물류센터명(공급받는) */
	@Schema(description = "물류센터명(공급받는)", example = "지역배송센터")
	private String dpDcname;

	/** 협력사코드 */
	@Schema(description = "협력사코드", example = "PARTNER01")
	private String cpCustkey;

	/** 협력사명 */
	@Schema(description = "협력사명", example = "(주)공급물류")
	private String cpCustname;

	/** 관리처코드 */
	@Schema(description = "관리처코드", example = "MNG001")
	private String ccCustkey;

	/** 관리처명 */
	@Schema(description = "관리처명", example = "서울관리소")
	private String ccCustname;

	/** 상품코드 */
	@Schema(description = "상품코드", example = "SKU-1004")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭", example = "프리미엄 우유")
	private String skuname;

	/** 저장조건 */
	@Schema(description = "저장조건", example = "냉장")
	private String storagetype;

	/** 단위 */
	@Schema(description = "단위", example = "BOX")
	private String uom;

	/** 차량번호 */
	@Schema(description = "차량번호", example = "서울12가3456")
	private String carno;

	/** POP */
	@Schema(description = "POP", example = "A-ZONE")
	private String deliverygroup;

	/** 주문수량 */
	@Schema(description = "주문수량", example = "500")
	private BigDecimal orderqty;

	/** 광역출고검수량 */
	@Schema(description = "광역출고검수량", example = "500")
	private BigDecimal wdInspectqty;

	/** 광역입고검수량 */
	@Schema(description = "광역입고검수량", example = "498")
	private BigDecimal dpInspectqty;

	/** 출고 */
	@Schema(description = "출고", example = "일치")
	private String statusInspectWd;

	/** 입고 */
	@Schema(description = "입고", example = "불일치")
	private String statusInspectDp;
		
}
