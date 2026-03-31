package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KP 주문동기화 모니터링 상세 - ST_INPLAN 탭 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP 주문동기화 모니터링 ST_INPLAN 응답 DTO")
public class KpSyncOrdDtlMonitoringSTResDto {

	private String dccode;
	private String slipdt;
	private String slipno;
	private String slipline;
	private String sku;
	private String tasktype;
	private String ordertype;
	private String chkOrdertype;
	private String fromLot;
	private String chkFromLot;
	private String fromStockid;
	private String chkFromStockid;
	private String fromStockgrade;
	private String chkFromStockgrade;
	private String fromStocktype;
	private String chkFromStocktype;
	private String fromLoc;
	private String chkFromLoc;
	private String toLot;
	private String chkToLot;
	private String toStockid;
	private String chkToStockid;
	private String toStockgrade;
	private String chkToStockgrade;
	private String toStocktype;
	private String chkToStocktype;
	private String toLoc;
	private String chkToLoc;
	private String orderqty;
	private String chkOrderqty;
	private String openqty;
	private String chkOpenqty;
	private String confirmqty;
	private String chkConfirmqty;
	private String cancelqty;
	private String chkCancelqty;
	private String chkSum;
}
