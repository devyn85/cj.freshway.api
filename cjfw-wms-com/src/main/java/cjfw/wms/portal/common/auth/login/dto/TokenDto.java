package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 토큰처리 요청/응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "토큰 정보")
public class TokenDto {
    @Schema(description = "AccessToken")
    private String accessToken;

    @Schema(description = "RefreshToken")
    private String refreshToken;
}
