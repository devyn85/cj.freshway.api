package cjfw.wms.tm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.26
 * @description : 배송고객모니터링 Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.26 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송고객모니터링 Entity")
public class TmMonitoringCustomerEntity extends CommonProcedureDto {
	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 배송기사 */
	@Schema(description = "배송기사")
	private String driver;

	/** 휴대폰번호 */
	@Schema(description = "휴대폰번호")
	private String phone;

	/** 고객사코드 */
	@Schema(description = "고객사코드")
	private String storerkey;

	/** 거래처유형 */
	@Schema(description = "거래처유형")
	private String custtype;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String custkey;

	/** 관리처명 */
	@Schema(description = "관리처명")
	private String custname;

	/** 중량 */
	@Schema(description = "중량")
	private BigDecimal weight;

	/** 사진촬영 유/무 */
	@Schema(description = "사진촬영 유/무")
	private String photoYn;

	/** 적온적재 유/무 */
	@Schema(description = "적온적재 유/무")
	private String loadtypeYn;

	/** 배송이슈 등재 */
	@Schema(description = "배송이슈 등재")
	private String issueYn;

	/** MA코드 */
	@Schema(description = "담당MA")
	private String ma;

	/** 그룹코드 */
	@Schema(description = "MA코드")
	private String maname;

	/** 결품건수 */
	@Schema(description = "결품건수")
	private BigDecimal cancelqty;

	/** 배송요청시간 */
	@Schema(description = "배송요청시간")
	private String deliveryreqdt;

	/** 도착시간(예정) */
	@Schema(description = "도착시간(예정)")
	private String custarrivaldt;

	/** 특이사항 */
	@Schema(description = "특이사항")
	private String memo;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 그룹코드 */
	@Schema(description = "그룹코드")
	private String groupCd;

	/** 그룹명 */
	@Schema(description = "그룹명")
	private String groupName;

	/** 사용여부 */
	@Schema(description = "사용여부")
	private String useYn;

	/** 비고 */
	@Schema(description = "비고")
	private String rmk;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String mngplcId;

	/** 관리처명 */
	@Schema(description = "관리처명")
	private String mngplcname;
}
