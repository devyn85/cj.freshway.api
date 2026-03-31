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
 * @date : 2025.07.28
 * @description : 입고하차관리 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고하차관리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpUnloadResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 문서번호 */
	@Schema(description = "문서번호")
	private String docno;

	/** 채널 */
	@Schema(description = "채널")
	private String channel;

	/** 채널명 */
	@Schema(description = "채널명")
	private String channelName;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 주문유형명 */
	@Schema(description = "주문유형명")
	private String ordertypeName;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 문서라인 */
	@Schema(description = "문서라인")
	private String docline;

	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 입고예정번호 */
	@Schema(description = "입고예정번호")
	private String slipno;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 상품명 */
	@Schema(description = "상품명")
	private String skuname;

	/** 보관유형 */
	@Schema(description = "보관유형")
	private String storagetype;

	/** 보관유형명 */
	@Schema(description = "보관유형명")
	private String storagetypeName;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private String orderqty;

	/** 확정수량 */
	@Schema(description = "확정수량")
	private String confirmqty;

	/** 공장 */
	@Schema(description = "공장")
	private String plant;

	/** 공장명 */
	@Schema(description = "공장명")
	private String plantDescr;
}
