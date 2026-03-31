package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 비밀번호변경 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PwdChangeReqDto {

    @Schema(description = "사용자ID", nullable = false, example = "devadmin01")
    @NotEmpty
    private String userId;

    @Schema(description = "비밀번호", nullable = false, example = "1111")
    @NotEmpty
    private String pwd;

    @Schema(description = "비밀번호 확인", nullable = false, example = "1111")
    @NotEmpty
    private String pwdCheck;

    @Schema(description = "이메일", nullable = false, example = "test@cj.net")
    @NotEmpty
    private String email;

}
