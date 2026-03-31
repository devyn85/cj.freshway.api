package cjfw.wms.sysmgt.func.commoncodeI18N.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * i18공통코드 조회 Request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class I18nCodeDtlGetReqDto {
	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE", nullable = false)
    @NotEmpty(message = "공통그룹코드는 필수 값입니다.")
    private String comGrpCd;
}
