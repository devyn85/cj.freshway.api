package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 사용자 권한메뉴 조회 응답 DTO
 */
@Data
@Schema(description = "사용자 메뉴 조회 응답")
public class SiteMapMenuGetResDto {
    @Schema(description = "메뉴ID", nullable = false, example = "FO_MGT")
    private String menuId;

    @Schema(description = "메뉴명", nullable = false, example = "시스템관리")
    private String menuNm;

    @Schema(description = "메뉴URL", nullable = false, example = "/sysmgt")
    private String menuUrl;

    @Schema(description = "상위메뉴ID", example = "null")
    private String upperMenuId;

    @Schema(description = "사용여부", example = "1", allowableValues = {"0", "1"})
    private String useYn;

    @Schema(description = "즐겨찾기여부", example = "1", allowableValues = {"0", "1"})
    private String favoriteYn;

}
