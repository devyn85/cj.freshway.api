package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KP 주문동기화 모니터링 상세 - AJ_INPLAN 탭 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP 주문동기화 모니터링 AJ_INPLAN 응답 DTO")
public class KpSyncOrdDtlMonitoringAJResDto {

	private String dccode;
	private String slipdt;
	private String slipno;
	private String slipline;
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
	private String confirmqty;
	private String chkConfirmqty;
	private String cancelqty;
	private String chkCancelqty;
	private String loc;
	private String chkLoc;
	private String lot;
	private String chkLot;
	private String stockid;
	private String chkStockid;
	private String stockgrade;
	private String chkStockgrade;
	private String chkSum;
}
