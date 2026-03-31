package cjfw.wms.portal.common.auth.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 아이디찾기 응답 DTO
 */
@Data
public class FindIdGetResDto {

    @Schema(description = "사용자ID", nullable = true, example = "devadmin01")
    private String userId;
}
