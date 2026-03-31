package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 기준정보 > 사용자및센터정보 > 물류센터별창고관리 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "물류센터별창고관리 조회 요청")
public class CmDcXOrganizeManagerReqDto extends CommonDto {
	@Schema(description = "데이터 번호")
    private String serialKey;
	
	@Schema(description = "물류센터코드", example = "2600")
	private String dcCode;
	
	@Schema(description = "멀티 물류센터코드", example = "2600")
	private String multiDcCode[];
	
	@Schema(description = "창고", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    private String organize;
	
	@Schema(description = "창고명", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    private String description;

	@Builder.Default
    @Schema(description = "상태 (00~99)", defaultValue = "00", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status = "00";

	@Builder.Default
    @Schema(description = "삭제여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn = "N";
}
