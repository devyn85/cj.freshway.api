package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.14
 * @description : 운행일지 request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.14 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "운행일지 report dto")
public class TmCarPositionHistoryReportDto {
	/** 고객사 */
	@Schema(description = "고객사")
	private String storerkey;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 배송차수 */
	@Schema(description = "배송차수")
	private String priority;

	/** 배송담당 */
	@Schema(description = "배송담당")
	private String drivername;

	/** 운행시작(KM) */
	@Schema(description = "운행시작(KM)")
	private BigDecimal startmileage;

	/** 운행종료(KM) */
	@Schema(description = "운행종료(KM)")
	private BigDecimal endmileage;

	/** 당일주유량 */
	@Schema(description = "당일주유량")
	private BigDecimal gasrefuel;

	/** 당일주유량 sum */
	@Schema(description = "당일주유량 sum")
	private BigDecimal gasrefuelsum;

	/** gasrefuelsumYear */
	@Schema(description = "gasrefuelsumYear ")
	private BigDecimal gasrefuelsumYear;

	/** 소독시간 */
	@Schema(description = "소독시간")
	private String disinfectiontime;

	/** 차량점검유무 */
	@Schema(description = "차량점검유무")
	private String carcheckyn;

	/** 통행료 */
	@Schema(description = "통행료")
	private BigDecimal toll;

	/** 통행료 sum */
	@Schema(description = "통행료 sum")
	private BigDecimal tollsum;

	/** 주차비 */
	@Schema(description = "주차비")
	private BigDecimal parkingfee;

	/** 주차비 sum */
	@Schema(description = "주차비 sum")
	private BigDecimal parkingfeesum;

	/** drivedistanceMst */
	@Schema(description = "drivedistanceMst")
	private BigDecimal drivedistanceMst;

	/** drivedistancesum */
	@Schema(description = "drivedistancesum")
	private BigDecimal drivedistancesum;

	/** drivedistancesumYear */
	@Schema(description = "drivedistancesumYear")
	private BigDecimal drivedistancesumYear;

	/** dispPageDiv */
	@Schema(description = "dispPageDiv")
	private String dispPageDiv;

	/** 당일 계획 주행거리 */
	@Schema(description = "당일 실 주행거리")
	private BigDecimal dailyMileage;

	/** 당일 계획 주행거리 */
	@Schema(description = "당일 계획 주행거리")
	private BigDecimal dailyExpDrivedistance;

	/** 당월 계획 주행거리 */
	@Schema(description = "당월 실 주행거리")
	private BigDecimal dailyMileagesum;

	/** 당월 계획 주행거리 */
	@Schema(description = "당월 계획 주행거리")
	private BigDecimal dailyExpDrivedistancesum;


	/** 배송순위 */
	@Schema(description = "배송순위")
	private BigDecimal deliverypriority;

	/** 실제 배송순위 */
	@Schema(description = "실제 배송순위")
	private BigDecimal realDeliverypriority;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 거래처명 */
	@Schema(description = "거래처명")
	private String custname;

	/** 도착시간 */
	@Schema(description = "도착시간")
	private String arrivedtime;

	/** 출발시간 */
	@Schema(description = "출발시간")
	private String departuretime;

	/** 주행거리(KM) */
	@Schema(description = "주행거리(KM)")
	private String drivedistance;

	/** 소요시간(이동) */
	@Schema(description = "소요시간(이동)")
	private String movingtime;

	/** 소요시간(상하차) */
	@Schema(description = "소요시간(상하차)")
	private String workingtime;
}
