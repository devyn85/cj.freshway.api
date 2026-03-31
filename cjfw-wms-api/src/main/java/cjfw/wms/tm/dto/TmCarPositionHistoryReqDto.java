package cjfw.wms.tm.dto;

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
 * @date : 2025.11.14
 * @description : 운행일지 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.14 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운행일지 request dto")
public class TmCarPositionHistoryReqDto extends CommonProcedureDto {
	/** 배송일자 from */
	@Schema(description = "배송일자 from")
	private String fromDocdt;

	/** 배송일자 to */
	@Schema(description = "배송일자 to")
	private String toDocdt;

	/** 거래처 */
	@Schema(description = "거래처")
	private String toCustkey;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 고객사 */
	@Schema(description = "고객사")
	private String storerkey;

	/** 배송일자 */
	@Schema(description = "배송일자")
	private String deliverydt;

	/** 물류센터 */
	@Schema(description = "물류센터")
	private String dccode;

	/** 회차 */
	@Schema(description = "회차")
	private String priority;

	/** 계약유형 */
	@Schema(description = "계약유형")
	private String contracttype;

	/** 차량운행 일지 조회 목록*/
	@Schema(description = "차량운행 일지 조회 목록")
	private List<TmCarPositionHistoryResDto> printMasterList;
}

