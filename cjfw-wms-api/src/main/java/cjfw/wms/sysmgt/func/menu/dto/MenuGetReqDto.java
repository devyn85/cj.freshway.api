package cjfw.wms.sysmgt.func.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 메뉴 조회 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuGetReqDto {
	@Schema(description = "메뉴코드", example = "BIZ_SAMPLE", nullable = true)
    private String menuId;
	@Schema(description = "메뉴명", example = " ", nullable = true)
    private String menuNm;
    @Schema(description = "메뉴여부", example = " ", nullable = true)
    private String menuYn;
}

/**
 * [MPA 참조]
 * MENU_ID: ""
 * MENU_NM: ""
 */
