package cjfw.wms.tm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.24
 * @description : 배차작업로그(차량별) 요청 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배차작업로그(거래처별) 요청 DTO")
public class TmWorklogByCarReqDto extends CommonDto {
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "")
	private String carno;

	/** 배송일자 */
	@Schema(description = "배송일자", nullable = false, example = "")
	private String deliverydate;

	/** 거래처코드 */
	@Schema(description = "거래처코드", nullable = false, example = "")
	private String custCode;

	/** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

	/** 거래처코드 다중선택 */
	private String multiCust;
}
