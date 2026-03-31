package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * KP 재고동기화 모니터링 조회 요청 DTO
 * 화면 탭: EXISTS_YN '1'(TOBE 신규), '2'(ASISTOBE 차이) 구분 조회 시 existsYn 사용
 * 품목 다중 선택: sku 에 "SKU1,SKU2,..." 콤마 구분 전달 → AOP에서 skuMulti(999건씩 OR조건)로 변환
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Schema(description = "KP 재고동기화 모니터링 요청 DTO")
public class KpSyncStockMonitoringReqDto extends CommonDto {

	@Schema(description = "센터코드", requiredMode = Schema.RequiredMode.REQUIRED, example = "2410")
	private String dccode;

	@Schema(description = "품목코드(콤마구분 다중선택, 1000건 초과 가능)", example = "SKU001,SKU002,SKU003")
	private String sku;

	/** 다중OR검색용 (AOP에서 sku 기준 999건씩 분할 세팅, Oracle IN 1000건 제한 회피) */
	@MultiSearch
	@Schema(hidden = true)
	private List<List<String>> skuMulti;

	/** 탭 구분: '1' TOBE(신규), '2' ASISTOBE(차이), 미입력 시 전체 */
	@Schema(description = "탭구분(1:TOBE 신규, 2:ASISTOBE 차이)")
	private String existsYn;
}
