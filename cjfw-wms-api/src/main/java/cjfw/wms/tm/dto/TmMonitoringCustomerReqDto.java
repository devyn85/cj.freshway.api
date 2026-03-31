package cjfw.wms.tm.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.24
 * @description : 배송고객모니터링 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송고객모니터링 request dto")
public class TmMonitoringCustomerReqDto extends CommonProcedureDto {

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 기준일자 */
	@Schema(description = "기준일자")
	private String deliverydt;

	/** 모니터링그룹 */
	@Schema(description = "모니터링그룹")
	private String groupCd;

	/** 관리처 */
	@Schema(description = "관리처")
	private String custkey;

	/** 등록일차이 */
	@Schema(description = "등록일차이")
	private BigDecimal diffAddDate;

	/** 모니터링그룹코드/명 */
	@Schema(description = "모니터링그룹코드/명")
	private String groupCdName;

	/** 사용여부 */
	@Schema(description = "사용여부")
	private String useYn;

	/** 배송고객모니터링 저장 */
	@Schema(description = "배송고객모니터링 저장")
	private List<TmMonitoringCustomerResDto> saveMasterList;

	/** 모니터링 그룹 insert */
	@Schema(description = "모니터링 그룹 insert")
	private List<TmMonitoringCustomerResDto> insertCustomerGroupMasterList;

	/**모니터링 그룹 update */
	@Schema(description = "모니터링 그룹 update")
	private List<TmMonitoringCustomerResDto> updateCustomerGroupMasterList;

	/** 모니터링 그룹 상세 insert */
	@Schema(description = "모니터링 그룹 상세 insert")
	private List<TmMonitoringCustomerResDto> insertCustomerGroupDetailList;

	/** 모니터링 그룹 상세 update */
	@Schema(description = "모니터링 그룹 상세 update")
	private List<TmMonitoringCustomerResDto> updateCustomerGroupDetailList;

	/** 배송고객모니터링 이메일 발송 */
	@Schema(description = "배송고객모니터링 이메일 발송")
	private List<TmMonitoringCustomerResDto> emailList;

}
