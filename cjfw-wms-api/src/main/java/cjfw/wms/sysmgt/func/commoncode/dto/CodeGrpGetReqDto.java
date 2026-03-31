package cjfw.wms.sysmgt.func.commoncode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeGrpGetReqDto {
	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE", nullable = true)
    private String comGrpCd;
	@Schema(description = "공통그룹코드명", example = " ", nullable = true)
    private String grpCdNm;
	@Schema(description = "사용여부", example = " ", nullable = true)
    private String useYn;
}
