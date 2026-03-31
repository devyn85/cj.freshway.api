package cjfw.wms.sysmgt.func.menu.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 메뉴 저장 Request DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSaveReqDto {
	@Schema(description = "메뉴관리 리스트", example = "", nullable = false)
    @Valid
    @NotEmpty(message = "메뉴List는 필수 값입니다.")
    private List<MenuSaveReqDto.Menu> menus;
    @Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
    public static class Menu{
    	@Schema(description = "메뉴ID", example = "BIZ_SAMPLE_MENU_01", nullable = false)
    	@NotEmpty(message = "메뉴ID는 필수 값입니다.")
        private String menuId; // 메뉴ID
    	@Schema(description = "상위메뉴ID", example = "BIZ_SAMPLE_MENU", nullable = true)
    	private String upperMenuId; // 상위메뉴ID
    	@Schema(description = "메뉴명", example = "샘플하메뉴1", nullable = false)
    	@NotEmpty(message = "메뉴명은 필수 값입니다.")
    	private String menuNm; // 메뉴명
    	@Schema(description = "메뉴URL", example = "/sample/depth2_1/depth3_1/page", nullable = true)
    	private String menuUrl; // 메뉴URL
    	@Schema(description = "메뉴여부", example = "1", nullable = false)
    	private String menuYn; // 메뉴여부
    	@Schema(description = "사용여부", example = "1", nullable = false)
    	private String useYn; // 사용여부
    	@Schema(description = "메뉴설명", example = " ", nullable = true)
    	private String description; // 메뉴설명
    	@Schema(description = "정렬순서", example = "1110", nullable = false)
    	private Integer sortOrder; // 정렬순서
    	@Schema(description = "팝업여부", example = "0", nullable = false)
    	private String isPopup; // 팝업여부
    	@Schema(description = "참조메뉴ID", example = " ", nullable = true)
    	private String refMenuId; // 참조메뉴ID
    	@Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    	@NotEmpty(message = "GridRow 저장 구분은 필수 값입니다.")
        private String rowStatus; // "I"(등록), "U"(수정), "D"(삭제)
    }
}

/** API 샘플
 {
 "menus":[
 // 등록
 // {
 //     "menuId": "BIZ_SAMPLE_MENU_0103",
 //     "menuNm": "임시 테스트2",
 //     "menuUrl": "/sample/tmp2",
 //     "menuYn": "N",
 //     "sortOrder": "1113",
 //     "upperMenuId": "BIZ_SAMPLE_MENU_01",
 //     "useYn": "1",
 //     "rowStatus": "I"
 // }

 // 수정
 // {
 //     "menuId": "BIZ_SAMPLE_MENU_0103",
 //     "menuNm": "임시 테스트2_수정",
 //     "menuUrl": "/sample/tmp2_update",
 //     "menuYn": "Y",
 //     "sortOrder": "1113",
 //     "upperMenuId": "BIZ_SAMPLE_MENU_01",
 //     "useYn": "0",
 //     "rowStatus": "U"
 // }

 // 삭제
 // {
 //     "menuId": "BIZ_SAMPLE_MENU_0103",
 //     "rowStatus": "D"
 // }

 ]
 }
 */

/** [MPA 참조]
 * - MENU_ID: "BIZ_SAMPLE_MENU_0102"
 * - MENU_NM: "임시 테스트"
 * - MENU_URL: "/sample/tmp"
 * - MENU_YN: "1"
 * - SORT_ORDER: "1112"
 * - UPPER_MENU_ID: "BIZ_SAMPLE_MENU_01"
 * - USE_YN: "1"
 * __rowStatus: "I"
 *
 */

