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
 * @date : 2025.12.02
 * @description : 지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.02 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 센터운영지표 > 출고결품실적 월요약_탭 Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdShortageResultResT1Dto extends CommonProcedureDto {
	
	/* GOAL */
	@Schema(description = "GOAL")
	private BigDecimal goal;

	/* DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;
	
	/* 물류센터명 */
	@Schema(description = "물류센터명")
	private String dcname;

	/* SKUPART */
	@Schema(description = "SKUPART")
	private String skupart;

	/* SKUPARTNAME */
	@Schema(description = "SKUPARTNAME")
	private String skupartname;

	/* SKUPART */
	@Schema(description = "reasoncode")
	private String reasoncode;

	/* SKUPARTNAME */
	@Schema(description = "reasoncodename")
	private String reasoncodename;
	
	/* TOTAL */
	@Schema(description = "TOTAL")
	private BigDecimal total;

	/* AVERAGE */
	@Schema(description = "AVERAGE")
	private BigDecimal average;
	
	/* daycnt */
	@Schema(description = "daycnt")
	private BigDecimal daycnt;

	/* day */
	@Schema(description = "day")
	private String day;
	
	/* colValue */
	@Schema(description = "colValue")
	private String colValue;
	
	/* colHeader */
	@Schema(description = "colHeader")
	private String colHeader;
	
	/* colField */
	@Schema(description = "colField")
	private String colField;
	
	/* DAY01 */
	@Schema(description = "DAY01")
	private BigDecimal day01;

	/* DAY02 */
	@Schema(description = "DAY02")
	private BigDecimal day02;

	/* DAY03 */
	@Schema(description = "DAY03")
	private BigDecimal day03;

	/* DAY04 */
	@Schema(description = "DAY04")
	private BigDecimal day04;

	/* DAY05 */
	@Schema(description = "DAY05")
	private BigDecimal day05;

	/* DAY06 */
	@Schema(description = "DAY06")
	private BigDecimal day06;

	/* DAY07 */
	@Schema(description = "DAY07")
	private BigDecimal day07;

	/* DAY08 */
	@Schema(description = "DAY08")
	private BigDecimal day08;

	/* DAY09 */
	@Schema(description = "DAY09")
	private BigDecimal day09;

	/* DAY10 */
	@Schema(description = "DAY10")
	private BigDecimal day10;

	/* DAY11 */
	@Schema(description = "DAY11")
	private BigDecimal day11;

	/* DAY12 */
	@Schema(description = "DAY12")
	private BigDecimal day12;

	/* DAY13 */
	@Schema(description = "DAY13")
	private BigDecimal day13;

	/* DAY14 */
	@Schema(description = "DAY14")
	private BigDecimal day14;

	/* DAY15 */
	@Schema(description = "DAY15")
	private BigDecimal day15;

	/* DAY16 */
	@Schema(description = "DAY16")
	private BigDecimal day16;

	/* DAY17 */
	@Schema(description = "DAY17")
	private BigDecimal day17;

	/* DAY18 */
	@Schema(description = "DAY18")
	private BigDecimal day18;

	/* DAY19 */
	@Schema(description = "DAY19")
	private BigDecimal day19;

	/* DAY20 */
	@Schema(description = "DAY20")
	private BigDecimal day20;

	/* DAY21 */
	@Schema(description = "DAY21")
	private BigDecimal day21;

	/* DAY22 */
	@Schema(description = "DAY22")
	private BigDecimal day22;

	/* DAY23 */
	@Schema(description = "DAY23")
	private BigDecimal day23;

	/* DAY24 */
	@Schema(description = "DAY24")
	private BigDecimal day24;

	/* DAY25 */
	@Schema(description = "DAY25")
	private BigDecimal day25;

	/* DAY26 */
	@Schema(description = "DAY26")
	private BigDecimal day26;

	/* DAY27 */
	@Schema(description = "DAY27")
	private BigDecimal day27;

	/* DAY28 */
	@Schema(description = "DAY28")
	private BigDecimal day28;

	/* DAY29 */
	@Schema(description = "DAY29")
	private BigDecimal day29;

	/* DAY30 */
	@Schema(description = "DAY30")
	private BigDecimal day30;

	/* DAY31 */
	@Schema(description = "DAY31")
	private BigDecimal day31;
}
