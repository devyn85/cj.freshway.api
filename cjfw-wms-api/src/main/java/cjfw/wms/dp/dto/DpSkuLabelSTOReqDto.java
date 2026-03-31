package cjfw.wms.dp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.07
 * @description : 입고라벨출력(광역) Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고라벨출력(광역) Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpSkuLabelSTOReqDto extends CommonDto {

	/** 광역출고일자(From) */
	@Schema(description = "광역출고일자(From)")
	private String fromSlipdt;

	/** 광역출고일자(To) */
	@Schema(description = "광역출고일자(To)")
	private String toSlipdt;

	/** 출고문서번호(다중) */
	@Schema(description = "출고문서번호(다중)")
	private String docno;

	/** 상품코드(다중) */
	@Schema(description = "상품코드(다중)")
	private String sku;

	/** 상품코드(다중) - 상세조회용 */
	@Schema(description = "상품코드(다중) - 상세조회용")
	private String multiSku;

	/** 출고문서일자(From) */
	@Schema(description = "출고문서일자(From)")
	private String fromDocdt;

	/** 출고문서일자(To) */
	@Schema(description = "출고문서일자(To)")
	private String toDocdt;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 배송일자(From) */
	@Schema(description = "배송일자(From)")
	private String fromDeliverydt;

	/** 배송일자(To) */
	@Schema(description = "배송일자(To)")
	private String toDeliverydt;

	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 출발거래처코드 */
	@Schema(description = "출발거래처코드")
	private String fromCustkey;

	/** 도착거래처코드 */
	@Schema(description = "도착거래처코드")
	private String toCustkey;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String gMultiCourier;
}
