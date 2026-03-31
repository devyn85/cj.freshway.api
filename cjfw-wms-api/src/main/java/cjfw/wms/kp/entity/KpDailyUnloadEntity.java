package cjfw.wms.kp.entity;

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
 * @date : 2026.01.19
 * @description : 데일리 생산성 하역 지표 관리 Entity
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.19 JiHoPark  생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "데일리 생산성 하역 지표 관리 Entity")
public class KpDailyUnloadEntity extends CommonProcedureDto {
	/** serialkey */
	@Schema(description = "serialkey")
	private BigDecimal serialkey;

	/** 원시리얼번호 */
	@Schema(description = "원시리얼번호")
	private BigDecimal orgSerialkey;

	/** 회사코드 */
	@Schema(description = "회사코드")
	private String storerkey;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 대구분 */
	@Schema(description = "대구분")
	private String gubun1;

	/** 소구분 */
	@Schema(description = "소구분")
	private String gubun2;

	/** 인원(T/O) */
	@Schema(description = "인원(T/O)")
	private String gubun3;

	/** FROM */
	@Schema(description = "FROM")
	private String fromHour;

	/** TO */
	@Schema(description = "TO")
	private String toHour;

	/** 삭제여부 */
	@Schema(description = "삭제여부")
	private String delYn;

	/** 물류센터 */
	@Schema(description = "등록자")
	private String addwho;

	/** 물류센터 */
	@Schema(description = "수정자")
	private String editwho;

	/** 물류센터 */
	@Schema(description = "등록일자")
	private String adddate;

	/** 물류센터 */
	@Schema(description = "수정일자")
	private String editdate;

	/** 물류센터 */
	@Schema(description = "예외여부")
	private String excptYn;

	/** 물류센터 */
	@Schema(description = "계약시작일자")
	private String contractFromdate;

	/** 물류센터 */
	@Schema(description = "계약종료일자")
	private String contractTodate;

	/** 일자 */
	@Schema(description = "일자")
	private String deliverydt;

	/** 작업시간 */
	@Schema(description = "작업시간")
	private BigDecimal worktime;

	/** 투입인원 */
	@Schema(description = "투입인원")
	private String workperson;

	/** 관리처코드 */
	@Schema(description = "관리처코드")
	private String custkey;

	/** 비고 */
	@Schema(description = "비고")
	private String bigo;

	/** 예외시작일자 */
	@Schema(description = "예외시작일자")
	private String excptFromdate;

	/** 예외종료일자 */
	@Schema(description = "예외종료일자")
	private String excptTodate;
}
