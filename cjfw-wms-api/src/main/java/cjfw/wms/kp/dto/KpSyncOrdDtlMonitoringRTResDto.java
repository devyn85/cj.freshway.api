package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KP 주문동기화 모니터링 상세 - RT_INPLAN 탭 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP 주문동기화 모니터링 RT_INPLAN 응답 DTO")
public class KpSyncOrdDtlMonitoringRTResDto {

	private String dccode;
	private String slipdt;
	private String slipno;
	private String slipline;
	private String sliptype;
	private String ifSendType;
	private String sku;
	private String status;
	private String chkStatus;
	private String doctype;
	private String chkDoctype;
	private String iotype;
	private String chkIotype;
	private String ordertype;
	private String chkOrdertype;
	private String orderqty;
	private String chkOrderqty;
	private String openqty;
	private String chkOpenqty;
	private String taskqty;
	private String chkTaskqty;
	private String confirmqty;
	private String chkConfirmqty;
	private String cancelqty;
	private String chkCancelqty;
	private String confirmweight;
	private String chkConfirmweight;
	private String toloc;
	private String chkToloc;
	private String lot;
	private String chkLot;
	private String lottable01;
	private String chkLottable01;
	private String lottable02;
	private String chkLottable02;
	private String lottable03;
	private String chkLottable03;
	private String lottable04;
	private String chkLottable04;
	private String lottable05;
	private String chkLottable05;
	private String stockid;
	private String chkStockid;
	private String stockgrade;
	private String chkStockgrade;
	private String chkSum;
}
