package cjfw.wms.tm.dto;

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
 * @date : 2025.10.27
 * @description : 거래처별 메모사항 조회 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.27 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처별 메모사항 조회 request dto")
public class TmDeliveryMemoReqDto extends CommonProcedureDto {
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 배송일자 from */
	@Schema(description = "배송일자 from")
	private String fromDeliverydt;

	/** 배송일자 to */
	@Schema(description = "배송일자 to")
	private String toDeliverydt;

	/** 차량번호 */
	@Schema(description = "차량번호")
	private String carno;

	/** 거래처 */
	@Schema(description = "거래처")
	private String toCustkey;

	/** 입력건만 조회 */
	@Schema(description = "입력건만 조회")
	private String chkMemo;
}
