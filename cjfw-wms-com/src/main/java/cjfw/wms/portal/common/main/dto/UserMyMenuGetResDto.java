package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 즐겨찾기 목록 응답 DTO
 */
@Data
public class UserMyMenuGetResDto {
	
	@Schema(description = "메뉴ID", nullable = false, example = "FO_MGT_STD_02")
    private String menuId;
	
	@Schema(description = "메뉴명", nullable = true, example = "메뉴관리")
    private String menuNm;
	
	@Schema(description = "메뉴url", nullable = true, example = "/sysmgt/func/menu/page")
    private String menuUrl;
}