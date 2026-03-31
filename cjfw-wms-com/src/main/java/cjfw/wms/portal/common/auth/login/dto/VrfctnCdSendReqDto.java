package cjfw.wms.portal.common.auth.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인증코드전송 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VrfctnCdSendReqDto {
    @Schema(description = "사용자ID", nullable = false, example = "devadmin01")
    @NotEmpty
    private String userId;

    @Schema(description = "이메일", nullable = false, example = "test@cj.net")
    @NotEmpty
    private String email;

    @Schema(description = "인증코드", nullable = false, example = "175884")
    @JsonIgnore
    private String vrfctnCd;
}
