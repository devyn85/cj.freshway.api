package cjfw.wms.sysmgt.func.rolesmappingmenu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한메뉴 조회 Request DTO
 */
@Data
public class RolesMappingMenuGetReqDto {
	@Schema(description = "권한코드", example = "ROLE_USER", nullable = false)
    @NotEmpty
    private String authority; // 권한
}

/**
   [MPA 참조]
 AUTHORITY: "11"
 */