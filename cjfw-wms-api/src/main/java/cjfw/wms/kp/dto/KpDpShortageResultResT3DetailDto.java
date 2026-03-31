package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.08
 * @description : 지표/모니터링 > 센터운영지표 > 입고결품실적 일배_탭 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 센터운영지표 > 입고결품실적 일배_탭 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDpShortageResultResT3DetailDto extends CommonDto {
	
	/* CUSTNAME */
	@Schema(description = "CUSTNAME")
	private String custname;

	/* DAY1 */
	@Schema(description = "DAY1")
	private BigDecimal day1;

	/* DAY2 */
	@Schema(description = "DAY2")
	private BigDecimal day2;

	/* DAY3 */
	@Schema(description = "DAY3")
	private BigDecimal day3;
}
