package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * KP 주문동기화 모니터링 Header 조회 요청 DTO
 * 프론트: dcCode: "2410,2440,2400" (콤마 구분 문자열)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "KP 주문동기화 모니터링 Header 요청 DTO")
public class KpSyncOrdMonitoringMasterReqDto extends CommonDto {

	@Schema(description = "문서번호")
	private String docNo;
	@Schema(description = "문서일자")
	private String docDt;
	@Schema(description = "배송일자 시작(YYYYMMDD)")
	private String pvcDeliveryDtFrom;
	@Schema(description = "배송일자 종료(YYYYMMDD)")
	private String pvcDeliveryDtTo;
	@Schema(description = "문서유형(DOCTYPE)")
	private String docType;

	/** 주문완료여부: 1=주문완료만(STATUS 또는 CHK_STATUS = '90'), 0 또는 미전달=전체 */
	@Schema(description = "주문완료여부(1:주문완료만)", example = "0")
	private String orderCompleteYn;

	/** 작업프로세스코드 (A.WORKPROCESSCODE 조회 조건) */
	@Schema(description = "작업프로세스코드", example = "")
	private String workProcessCode;

	/** 센터코드 - 프론트에서 "2410,2440,2400" 형태로 전달, 서비스에서 dccodeList로 파싱 */
	@Schema(description = "센터코드(콤마구분)", example = "2410,2440,2400")
	private String dcCode;

	/** MyBatis IN 절용 리스트 (서비스에서 dcCode 파싱 후 세팅) */
	@Schema(hidden = true)
	private List<String> dccodeList;
}
