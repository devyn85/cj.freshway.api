package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SSO 로그인 DTO
 */
@Data
@Schema(description = "SSO 로그인 정보")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SsoDto {

    @NotEmpty
    @Schema(description = "CJWorldId", nullable = false, example = "test1")
    private String cjWorldId;

    @Schema(description = "CJWorldId 암호화여부", nullable = true, example = "Y", allowableValues = {"Y", "N"})
    private String enc;
}
