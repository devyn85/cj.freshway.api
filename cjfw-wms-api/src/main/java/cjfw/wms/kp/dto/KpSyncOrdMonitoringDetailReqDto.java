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
 * KP 주문동기화 모니터링 Detail 조회 요청 DTO (Header에서 선택한 행 기준)
 * 프론트: docno, dccode(단일값) 로 전달
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Schema(description = "KP 주문동기화 모니터링 Detail 요청 DTO")
public class KpSyncOrdMonitoringDetailReqDto extends CommonDto {

	@Schema(description = "문서번호", requiredMode = Schema.RequiredMode.REQUIRED)
	private String docno;

	/** 센터코드 - 프론트에서 마스터 선택 행의 DCCODE 단일값으로 전달 */
	@Schema(description = "센터코드", example = "2600")
	private String dccode;

	/** MyBatis IN 절용 리스트 (서비스에서 dccode 파싱 후 세팅) */
	@Schema(hidden = true)
	private List<String> dccodeList;
}
