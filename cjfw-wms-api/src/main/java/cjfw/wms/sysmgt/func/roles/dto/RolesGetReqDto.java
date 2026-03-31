package cjfw.wms.sysmgt.func.roles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 권한 조회 Request DTO
 */
@Data
public class RolesGetReqDto {
	@Schema(description = "권한코드", example = "ROLE_USER", nullable = true)
    private String authority; // 권한코드
	@Schema(description = "권한명", example = "사용자", nullable = true)
    private String roleNm; // 권한명
}
