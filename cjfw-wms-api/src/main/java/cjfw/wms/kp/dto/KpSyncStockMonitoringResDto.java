package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KP 재고동기화 모니터링 조회 응답 DTO
 * EXISTS_YN: '1' TOBE(신규), '2' ASISTOBE(차이)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "KP 재고동기화 모니터링 응답 DTO")
public class KpSyncStockMonitoringResDto {

	@Schema(description = "구분(1:TOBE 신규, 2:ASISTOBE 차이)")
	private String existsYn;
	@Schema(description = "센터코드")
	private String dccode;
	@Schema(description = "품목코드")
	private String sku;
	@Schema(description = "로케이션")
	private String loc;
	@Schema(description = "LOT")
	private String lot;
	@Schema(description = "재고ID")
	private String stockid;
	@Schema(description = "재고등급")
	private String stockgrade;
	@Schema(description = "재고유형")
	private String stocktype;
	@Schema(description = "수량")
	private String qty;
	@Schema(description = "체크수량")
	private String chkQty;
	@Schema(description = "미결수량")
	private String openqty;
	@Schema(description = "체크미결수량")
	private String chkOpenqty;
	@Schema(description = "입고예정수량")
	private String qtyexpected;
	@Schema(description = "체크입고예정수량")
	private String chkQtyexpected;
	@Schema(description = "예약수량")
	private String qtyreserve;
	@Schema(description = "체크예약수량")
	private String chkQtyreserve;
	@Schema(description = "할당수량")
	private String qtyallocated;
	@Schema(description = "체크할당수량")
	private String chkQtyallocated;
	@Schema(description = "피킹수량")
	private String qtypicked;
	@Schema(description = "체크피킹수량")
	private String chkQtypicked;
	@Schema(description = "홀드수량")
	private String qtyhold;
	@Schema(description = "체크홀드수량")
	private String chkQtyhold;
	@Schema(description = "체크합계")
	private String chkSum;
}
