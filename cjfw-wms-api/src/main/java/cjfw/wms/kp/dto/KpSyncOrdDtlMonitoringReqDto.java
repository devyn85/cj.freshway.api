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
 * KP 주문동기화 모니터링 상세(탭별) 조회 요청 DTO
 * 프론트: docno(선택), docline(선택), dcCode, 전표일자 기간(slipdtFrom/slipdtTo)
 * 문서번호 또는 전표일자 기간 중 하나는 필수.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Schema(description = "KP 주문동기화 모니터링 상세(탭) 요청 DTO")
public class KpSyncOrdDtlMonitoringReqDto extends CommonDto {

	@Schema(description = "문서번호(선택)")
	private String docno;

	@Schema(description = "문서라인번호(선택)")
	private String docline;

	@Schema(description = "타스크유형(ST 탭 시)")
	private String tasktype;

	@Schema(description = "전표일자 시작(YYYYMMDD)")
	private String slipdtFrom;
	@Schema(description = "전표일자 종료(YYYYMMDD)")
	private String slipdtTo;

	/** 센터코드 - 프론트에서 "2410,2440,2400" 형태로 전달, 서비스에서 dccodeList로 파싱 */
	@Schema(description = "센터코드(콤마구분)", example = "2410,2440,2400")
	private String dcCode;

	/** MyBatis IN 절용 (서비스에서 dcCode 파싱 후 세팅) */
	@Schema(hidden = true)
	private List<String> dccodeList;
}
