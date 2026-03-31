package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.02
 * @description : 지표 > 센터 운영 > 일일 물동량 조회 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 일일 물동량 조회 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpDailyQTYResDto extends CommonProcedureDto {

	/* 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;
	
	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 창고 */
	@Schema(description = "창고")
	private String organizename;

	/* 경로 */
	@Schema(description = "경로")
	private String custstrategy3;

	/* 물동량(주문량) */
	@Schema(description = "물동량(주문량)")
	private BigDecimal orderweight;

	/* 거래처수(주문량) */
	@Schema(description = "거래처수(주문량)")
	private BigDecimal ordercustcount;

	/* 물동량(출고량) */
	@Schema(description = "물동량(출고량)")
	private BigDecimal confirmweight;

	/* 거래처수(출고량) */
	@Schema(description = "거래처수(출고량)")
	private BigDecimal confirmcustcount;

}
