package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.01.20 
 * @description : 출도착지표 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.20 ParkJinWoo 생성
 */
@Data
@Schema(description = "거래처별 메모사항 조회 response dto")
public class TmDeliveryIndicatorResDto extends CommonDto {
	/** 기준일자 */
	@Schema(description = "기준일자")
	private String deliveryDt;

	/** 구분 */
	@Schema(description = "구분")
	private String gubun;

	/** 총대수 */
	@Schema(description = "총대수")
	private Long totalCarCnt;

	/** 00%미만차량 */
	@Schema(description = "00%미만차량")
	private Long underCarCnt;

	/** 비율 */
	@Schema(description = "비율")
	private BigDecimal underCarRate;

	/** 착지수 */
	@Schema(description = "착지수")
	private Long arrivalCount;

	/** 보고 */
	@Schema(description = "보고")
	private Long reportCount;

	/** 보고율 */
	@Schema(description = "보고율")
	private BigDecimal reportRate;
}
