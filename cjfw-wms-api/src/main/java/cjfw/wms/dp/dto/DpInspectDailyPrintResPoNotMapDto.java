package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.10
 * @description : 일배입고검수출력 PO 미맵핑 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 PO 미맵핑 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintResPoNotMapDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 화주코드 */
	@Schema(description = "화주코드")
	private String storerkey;

	/** 문서유형 */
	@Schema(description = "문서유형")
	private String doctype;

	/** 조직 */
	@Schema(description = "조직")
	private String organize;

	/** 주문유형 */
	@Schema(description = "주문유형")
	private String ordertype;

	/** 주문유형명 */
	@Schema(description = "주문유형명")
	private String ordertypeName;

	/** 문서일자 */
	@Schema(description = "문서일자")
	private String docdt;

	/** 도착거래처코드 */
	@Schema(description = "도착거래처코드")
	private String toCustkey;

	/** 도착거래처명 */
	@Schema(description = "도착거래처명")
	private String toCustname;

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 출고예정일 */
	@Schema(description = "출고예정일")
	private String slipdt;

	/** 출고예정번호 */
	@Schema(description = "출고예정번호")
	private String slipno;
}
