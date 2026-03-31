package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * KP 주문동기화 모니터링 Header 조회 응답 DTO
 */
@Data
@Builder
@Schema(description = "KP 주문동기화 모니터링 Header 응답 DTO")
public class KpSyncOrdMonitoringMasterResDto {

	@Schema(description = "센터코드")
	private String dccode;
	@Schema(description = "문서번호")
	private String docno;
	@Schema(description = "문서일자")
	private String docdt;
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
	@Schema(description = "오더유형")
	private String ordertype;
	@Schema(description = "체크오더유형")
	private String chkOrdertype;
	@Schema(description = "오더수량")
	private String orderqty;
	@Schema(description = "체크오더수량")
	private String chkOrderqty;
	@Schema(description = "미결수량")
	private String openqty;
	@Schema(description = "체크미결수량")
	private String chkOpenqty;
	@Schema(description = "처리수량")
	private String processqty;
	@Schema(description = "체크처리수량")
	private String chkProcessqty;
	@Schema(description = "작업수량")
	private String workqty;
	@Schema(description = "체크작업수량")
	private String chkWorkqty;
	@Schema(description = "확정수량")
	private String confirmqty;
	@Schema(description = "체크확정수량")
	private String chkConfirmqty;
	@Schema(description = "취소수량")
	private String cancelqty;
	@Schema(description = "체크취소수량")
	private String chkCancelqty;
	@Schema(description = "체크합계")
	private String chkSum;
}
