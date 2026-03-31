package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KP 주문동기화 모니터링 Detail 조회 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP 주문동기화 모니터링 Detail 응답 DTO")
public class KpSyncOrdMonitoringDetailResDto {

	@Schema(description = "센터코드")
	private String dccode;
	@Schema(description = "문서일자")
	private String docdt;
	@Schema(description = "문서번호")
	private String docno;
	@Schema(description = "문서라인")
	private String docline;
	@Schema(description = "품목코드")
	private String sku;
	@Schema(description = "상태")
	private String status;
	@Schema(description = "체크상태")
	private String chkStatus;
	@Schema(description = "문서유형")
	private String doctype;
	@Schema(description = "체크문서유형")
	private String chkDoctype;
	@Schema(description = "작업공정코드")
	private String workprocesscode;
	@Schema(description = "체크작업공정코드")
	private String chkWorkprocesscode;
	@Schema(description = "입출고유형")
	private String iotype;
	@Schema(description = "체크입출고유형")
	private String chkIotype;
	@Schema(description = "오더유형")
	private String ordertype;
	@Schema(description = "체크오더유형")
	private String chkOrdertype;
	@Schema(description = "오더수량")
	private String orderqty;
	@Schema(description = "체크오더수량")
	private String chkOrderqty;
	@Schema(description = "오더조정수량")
	private String orderadjustqty;
	@Schema(description = "체크오더조정수량")
	private String chkOrderadjustqty;
	@Schema(description = "미결수량")
	private String openqty;
	@Schema(description = "체크미결수량")
	private String chkOpenqty;
	@Schema(description = "미결조정수량")
	private String openadjustqty;
	@Schema(description = "체크미결조정수량")
	private String chkOpenadjustqty;
	@Schema(description = "처리수량")
	private String processqty;
	@Schema(description = "체크처리수량")
	private String chkProcessqty;
	@Schema(description = "확정수량")
	private String confirmqty;
	@Schema(description = "체크확정수량")
	private String chkConfirmqty;
	@Schema(description = "확정중량")
	private String confirmweight;
	@Schema(description = "체크확정중량")
	private String chkConfirmweight;
	@Schema(description = "취소수량")
	private String cancelqty;
	@Schema(description = "체크취소수량")
	private String chkCancelqty;
	@Schema(description = "LOT")
	private String lot;
	@Schema(description = "체크LOT")
	private String chkLot;
	@Schema(description = "LOTTABLE01")
	private String lottable01;
	@Schema(description = "체크LOTTABLE01")
	private String chkLottable01;
	@Schema(description = "LOTTABLE02")
	private String lottable02;
	@Schema(description = "체크LOTTABLE02")
	private String chkLottable02;
	@Schema(description = "LOTTABLE03")
	private String lottable03;
	@Schema(description = "체크LOTTABLE03")
	private String chkLottable03;
	@Schema(description = "LOTTABLE04")
	private String lottable04;
	@Schema(description = "체크LOTTABLE04")
	private String chkLottable04;
	@Schema(description = "LOTTABLE05")
	private String lottable05;
	@Schema(description = "체크LOTTABLE05")
	private String chkLottable05;
	@Schema(description = "재고등급")
	private String stockgrade;
	@Schema(description = "체크재고등급")
	private String chkStockgrade;
	@Schema(description = "체크합계")
	private String chkSum;
}
