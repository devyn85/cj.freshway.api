package cjfw.wms.tm.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.14
 * @description : 운행일지 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.14 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "운행일지 response dto")
public class TmCarPositionHistoryResDto extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 데이터번호 */
	@Schema(description = "데이터번호")
	private BigDecimal serialkey;

	/** 고객사 */
	@Schema(description = "고객사")
	private String storerkey;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 회차 */
	@Schema(description = "회차")
	private String priority;

	/** 기사명 */
	@Schema(description = "기사명")
	private String drivername;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 계약유형명 */
	@Schema(description = "계약유형명")
	private String contracttypename;

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

	/** 운행시작(KM) */
	@Schema(description = "운행시작(KM)")
	private BigDecimal startmileage;

	/** 운행종료(KM) */
	@Schema(description = "운행종료(KM)")
	private BigDecimal endmileage;

	/** 당일주행(KM) */
	@Schema(description = "당일주행(KM)")
	private BigDecimal drivedistanceMst;

	/** 당일주유량 */
	@Schema(description = "당일주유량")
	private BigDecimal gasrefuel;

	/** 월주유누계 */
	@Schema(description = "월주유누계")
	private BigDecimal gasrefuelsum;

	/** gasrefuelsumYear */
	@Schema(description = "gasrefuelsumYear")
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

	/** 주차비 */
	@Schema(description = "주차비")
	private BigDecimal parkingfee;

	/** 실주행(KM) */
	@Schema(description = "실주행(KM)")
	private BigDecimal drivedistance;

	/** 월주행누계(KM) */
	@Schema(description = "월주행누계(KM)")
	private BigDecimal drivedistancesum;

	/** drivedistancesumYear */
	@Schema(description = "drivedistancesumYear")
	private BigDecimal drivedistancesumYear;

	/** 전표일자 */
	@Schema(description = "전표일자")
	private String slipdt;

	/** 전표번호 */
	@Schema(description = "전표번호")
	private String slipno;

	/** 전표유형 */
	@Schema(description = "전표유형")
	private String sliptype;

	/** 거래처유형 */
	@Schema(description = "거래처유형")
	private String custtype;

	/** 거래처코드 */
	@Schema(description = "거래처코드")
	private String custkey;

	/** 실착지코드 */
	@Schema(description = "실착지코드")
	private String truthcustkey;

	/** 배송 */
	@Schema(description = "배송숭위 실제")
	private String realDeliverypriority;

	/** 요청 */
	@Schema(description = "배송순위 계획")
	private String deliverypriority;

	/** 거래처명 */
	@Schema(description = "거래처명")
	private String custname;

	/** 실착지명 */
	@Schema(description = "실착지명")
	private String truthcustname;

	/** 도착시간 */
	@Schema(description = "도착시간")
	private String arrivedtime;

	/** 출발시간 */
	@Schema(description = "출발시간")
	private String departuretime;

	/** 이동 */
	@Schema(description = "이동")
	private String movingtime;

	/** 상하차 */
	@Schema(description = "상하차")
	private String workingtime;

	/** 기타 */
	@Schema(description = "기타")
	private String other01;

	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "0")
	private String chk = "0";

	/** 운행일지 출력용 헤더 목록 */
	@Schema(description = "운행일지 출력용 헤더 목록")
	private List<TmCarPositionHistoryReportDto> dsReportHeader;

	/** 운행일지 출력용 상세 목록 */
	@Schema(description = "운행일지 출력용 상세 목록")
	private List<TmCarPositionHistoryReportDto> dsReportDetail;
}
