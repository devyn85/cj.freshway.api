package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 DTO
 */
@Data
@Schema(description = "로그인 정보")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Schema(description = "사용자ID", nullable = false, example = "devadmin01")
    private String username;

    @Schema(description = "암호화된 비밀번호", nullable = false, example = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c")
    private String password;
}
