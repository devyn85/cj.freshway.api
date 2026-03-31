package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 비밀번호변경 관련 공통 응답 DTO
 */
@Data
@Builder
public class PwdChangeComResDto {
    @Schema(description = "성공여부", nullable = false, example = "1", allowableValues = {"0", "1"})
    private String successYn;

    @Schema(description = "인증코드 기능 샘플용", nullable = true, example = "111111")
    private String verificationCd;
}
