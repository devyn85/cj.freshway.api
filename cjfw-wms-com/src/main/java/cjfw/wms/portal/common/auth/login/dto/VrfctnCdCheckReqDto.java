package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인증코드 체크 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VrfctnCdCheckReqDto {
    @Schema(description = "사용자ID", nullable = false, example = "devadmin01")
    @NotEmpty
    private String userId;

    @Schema(description = "이메일", nullable = false, example = "test@cj.net")
    @NotEmpty
    private String email;

    @Schema(description = "인증코드", nullable = false, example = "175884")
    @NotEmpty
    private String vrfctnCd;
}

/**

 * {"SVC_MSG_CD":"MSG.COM.ERR.073",
 * "ErrorCode":0,
 * "CHECK_YN":"0",
 * "SVC_MSG_TEXT":"인증코드를 다시 확인해주세요.",
 * "SVC_ERR_MSG_TEXT":""}
 *
 *
 *
 */