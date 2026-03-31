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
 * @date : 2025.11.05
 * @description : 수송배차조정 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수송배차조정 request dto")
public class TmTransportControlReqDto extends CommonProcedureDto {

	/** 수송 SERIALKEY */
	@Schema(description = "수송 SERIALKEY")
	private BigDecimal serialkey;
	
	/** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

	/** 출발물류센터 */
	@Schema(description = "출발물류센터")
	private String fromDcCode;

	/** 조회일자 */
	@Schema(description = "조회일자")
	private String deliverydt;

	/** 도착물류센터 */
	@Schema(description = "도착물류센터")
	private String toDcCode;

	/** 기준일자 from */
	@Schema(description = "기준일자 from")
	private String fromDeliverydt;

	/** 기준일자 to */
	@Schema(description = "기준일자 to")
	private String toDeliverydt;

	/** 운송사 */
	@Schema(description = "운송사")
	private String courier;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 저장조건 */
	@Schema(description = "저장조건")
	private String storagetype;

	/** 톤급 */
	@Schema(description = "톤급")
	private String carcapacity;

	/** 경유여부 */
	@Schema(description = "경유여부")
	private String routeYn;
	
	/** 노선 */
    @Schema(description = "노선")
    private String carrierRouteId;

	/** 목록 정보 조회 */
	@Schema(description = "목록 정보 조회")
	private TmTransportControlResDto masterInfo;

	/** 수송배차 조정 insert */
	@Schema(description = "수송배차 조정 insert")
	private List<TmTransportControlResDto> insertMasterList;

	/** 수송배차 조정 update */
	@Schema(description = "수송배차 조정 insert")
	private List<TmTransportControlResDto> updateMasterList;
}
