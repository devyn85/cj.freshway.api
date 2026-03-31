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
 * @date : 2025.11.20
 * @description : 분할 미적용 관리처 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.20 JiHoPark  생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "분할 미적용 관리처 request dto")
public class TmMngplcIDUndoneReqDto extends CommonProcedureDto {
	/** 센터코드 */
	@Schema(description = "센터코드")
	private String dccode;

	/** 출고일자 - from */
	@Schema(description = "출고일자 - from")
	private String deliverydateFrom;

	/** 출고일자 - to */
	@Schema(description = "출고일자 - to")
	private String deliverydateTo;

	/** 주문번호 */
	@Schema(description = "주문번호")
	private String docno;

	/** 관리처 고객 */
	@Schema(description = "관리처 고객")
	private String toCustkey;
}
