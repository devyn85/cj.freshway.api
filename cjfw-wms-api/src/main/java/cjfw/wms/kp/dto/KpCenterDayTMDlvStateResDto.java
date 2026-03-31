package cjfw.wms.kp.dto;

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
 * @date : 2025.09.01
 * @description : 지표 > 센터 운영 > 배송조별 출자 평균시간 현황 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.01 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표 > 센터 운영 > 배송조별 출자 평균시간 현황 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpCenterDayTMDlvStateResDto extends CommonProcedureDto {
	
	/* 물류센터코드 */
	@Schema(description = "물류센터코드")
	private String dccode;

	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* 구분 */
	@Schema(description = "구분")
	private String cargroup;

	/* 지표 */
	@Schema(description = "지표")
	private String dlvtype;

	/* 단위 */
	@Schema(description = "단위")
	private String uom;

	/* 전월 일평균 */
	@Schema(description = "전월 일평균")
	private String avgPremonth;

	/* 당월 일평균 */
	@Schema(description = "당월 일평균")
	private String avgMonth;

	/* 증감량 */
	@Schema(description = "증감량")
	private String diffMonth;

	/* 전일 */
	@Schema(description = "전일")
	private String avgPreday;

	/* 당일 */
	@Schema(description = "당일")
	private String avgDay;

	/* 전일 증감량 */
	@Schema(description = "전일 증감량")
	private String diffDay;

	/* 전월 증감량 */
	@Schema(description = "전월 증감량")
	private String diffMonthday;
}
