package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.10
 * @description : 일배입고검수출력 Print Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 Print Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintResPrintDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 출발거래처명 */
	@Schema(description = "출발거래처명")
	private String fromCustname;

	/** 센터명 */
	@Schema(description = "센터명")
	private String dcname;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 차량명 */
	@Schema(description = "차량명")
	private String carname;

	/** 도착거래처명 */
	@Schema(description = "도착거래처명")
	private String toCustname;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 수량 */
	@Schema(description = "수량")
	private String qty;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 보관유형명 */
	@Schema(description = "보관유형명")
	private String storagetypename;

	/** 원산지 */
	@Schema(description = "원산지")
	private String countryoforigin;

	/** 배송그룹(기준) */
	@Schema(description = "배송그룹(기준)")
	private String deliverygroupStd;

	/** 배송그룹(확정) */
	@Schema(description = "배송그룹(확정)")
	private String deliverygroupCnf;
}
