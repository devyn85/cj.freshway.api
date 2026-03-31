package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 즐겨찾기 저장 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMyMenuInsertReqDto {
	
	@Schema(description = "메뉴ID", nullable = false, example = "FO_MGT_STD_02")
	@NotEmpty
    private String menuId;
    
	@Schema(description = "메뉴명", nullable = true, example = "메뉴관리")
	private String menuNm;
	
	@Schema(description = "메뉴url", nullable = true, example = "/sysmgt/func/menu/page")
    private String menuUrl;
	
}