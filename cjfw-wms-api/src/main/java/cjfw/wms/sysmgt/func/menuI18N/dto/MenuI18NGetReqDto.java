package cjfw.wms.sysmgt.func.menuI18N.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 메뉴다국어 조회 Request DTO
 */
@Data
public class MenuI18NGetReqDto {
	@Schema(description = "다국어 메뉴코드", example = "BIZ_SAMPLE", nullable = true)
    private String menuId;
	
	@Schema(description = "다국어 메뉴명", example = " ", nullable = true)
    private String menuNm;
}

/**
 * [MPA 참조]
 * MENU_ID: ""
 * MENU_NM: ""
 */