package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.09.25
 * @description : 공통처리 response dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 JiHoPark  생성 </pre>
 */
@Data
@Schema(description = "공통처리 response dto")
public class StAdjustmentCommCdResDto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/**물류센터*/
	@Schema(description = "물류센터")
	private String dccode;

	/**코드*/
	@Schema(description = "코드")
	private String comCd;

	/**코드명*/
	@Schema(description = "코드명")
	private String cdNm;
}
