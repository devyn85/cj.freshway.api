package cjfw.wms.sysmgt.func.commoncodeI18N.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * i18그룹코드 조회 Response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class I18nCodeGrpGetReqDto {
	@Schema(description = "다국어 공통그룹코드", example = "TPL_TIMEZONE", nullable = true)
    private String comGrpCd;
	@Schema(description = "다국어 그룹코드명", example = " ", nullable = true)
    private String grpCdNm;
	@Schema(description = "사용여부", example = " ", nullable = true)
    private String useYn;
}
