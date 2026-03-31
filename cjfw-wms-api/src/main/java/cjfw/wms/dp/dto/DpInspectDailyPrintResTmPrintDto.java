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
 * @description : 일배입고검수출력 TM Print Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "일배입고검수출력 TM Print Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpInspectDailyPrintResTmPrintDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** 입고예정일 */
	@Schema(description = "입고예정일")
	private String slipdt;

	/** 출발거래처명 */
	@Schema(description = "출발거래처명")
	private String fromCustname;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 예정배송그룹 */
	@Schema(description = "예정배송그룹")
	private String predeliverygroup;

	/** 배송그룹 */
	@Schema(description = "배송그룹")
	private String deliverygroup;
}
