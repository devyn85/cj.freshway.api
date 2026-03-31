package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 즐겨찾기 삭제 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMyMenuDeleteReqDto {
	
	@Schema(description = "메뉴ID", nullable = false, example = "FO_MGT_STD_02")
	@NotEmpty
    private String menuId;

}