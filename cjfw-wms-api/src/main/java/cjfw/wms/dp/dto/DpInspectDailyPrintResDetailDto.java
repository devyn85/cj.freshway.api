package cjfw.wms.dp.dto;

import java.math.BigDecimal;

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
 * @description : 일배입고검수출력 Detail Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** 문서라인번호 */
	@Schema(description = "문서라인번호")
	private BigDecimal docline;

	/** 상품코드 */
	@Schema(description = "상품코드")
	private String sku;

	/** 상품명칭 */
	@Schema(description = "상품명칭")
	private String skuname;

	/** 채널 */
	@Schema(description = "채널")
	private String channel;

	/** 채널명 */
	@Schema(description = "채널명")
	private String channelName;

	/** 보관유형 */
	@Schema(description = "보관유형")
	private String storagetype;

	/** 보관유형명 */
	@Schema(description = "보관유형명")
	private String storagetypename;

	/** 주문수량 */
	@Schema(description = "주문수량")
	private BigDecimal orderqty;

	/** 부족수량 */
	@Schema(description = "부족수량")
	private BigDecimal shortageqty;

	/** 주문조정수량 */
	@Schema(description = "주문조정수량")
	private BigDecimal orderadjustqty;

	/** 미확정수량 */
	@Schema(description = "미확정수량")
	private BigDecimal openqty;

	/** 검수수량 */
	@Schema(description = "검수수량")
	private BigDecimal inspectqty;

	/** 확정수량 */
	@Schema(description = "확정수량")
	private BigDecimal confirmqty;

	/** 단위 */
	@Schema(description = "단위")
	private String uom;

	/** 확정중량 */
	@Schema(description = "확정중량")
	private BigDecimal confirmweight;

	/** 박스입수량 */
	@Schema(description = "박스입수량")
	private BigDecimal qtyperbox;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 감사파일여부 */
	@Schema(description = "감사파일여부")
	private String ifAuditFile;

	/** 전송파일여부 */
	@Schema(description = "전송파일여부")
	private String ifSendFile;

	/** 플랜트 */
	@Schema(description = "플랜트")
	private String plant;

	/** 플랜트설명 */
	@Schema(description = "플랜트설명")
	private String plantDescr;
}
