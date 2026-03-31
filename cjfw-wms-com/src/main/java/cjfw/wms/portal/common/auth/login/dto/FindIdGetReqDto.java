package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 아이디찾기 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindIdGetReqDto {

    @Schema(description = "사용자이름", nullable = false, example = "개발자관리자")
    @NotEmpty
    private String name;

    @Schema(description = "이메일", nullable = false, example = "test@cj.net")
    @NotEmpty
    private String email;
}
