package cjfw.wms.sysmgt.func.rolesmappingmenu.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 권한메뉴 저장 Request DTO
 */
@Data
public class RolesMappingMenuSaveReqDto {
	@Schema(description = "권한별 메뉴 리스트", example = "", nullable = false)
    @Valid
    @NotEmpty
    private List<RolesMappingMenuSaveReqDto.RoleMenu> roleMenus;

    @Data
    public static class RoleMenu{
    	@Schema(description = "권한코드", example = "ROLE_USER", nullable = false)
        @NotEmpty
        private String authority; // 권한
        @Schema(description = "메뉴ID", example = "FO_MGT_STD", nullable = false)
        @NotEmpty
        private String menuId; // 메뉴ID
        @Schema(description = "메뉴여부", example = "1", allowableValues = {"0", "1"})
        private String menuYn; // 메뉴여부
        @Schema(description = "사용여부", example = "1", allowableValues = {"0", "1"})
        private String useYn; // 사용여부
        @Schema(description = "조회버튼사용여부", example = "1", allowableValues = {"0", "1"})
        private String searchYn; // 조회
        @Schema(description = "신규버튼사용여부", example = "0", allowableValues = {"0", "1"})
        private String newYn; // 신규
        @Schema(description = "저장버튼사용여부", example = "1", allowableValues = {"0", "1"})
        private String saveYn; // 저장
        @Schema(description = "삭제버튼사용여부", example = "0", allowableValues = {"0", "1"})
        private String deleteYn; // 삭제
        @Schema(description = "인쇄버튼사용여부", example = "0", allowableValues = {"0", "1"})
        private String printYn; // 프린트
        @Schema(description = "엑셀버튼사용여부", example = "0", allowableValues = {"0", "1"})
        private String excelYn; // 엑셀다운로드
        @Schema(description = "사용자버튼1사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn1Yn;
        @Schema(description = "사용자버튼2사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn2Yn;
        @Schema(description = "사용자버튼3사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn3Yn;
        @Schema(description = "사용자버튼4사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn4Yn;
        @Schema(description = "사용자버튼5사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn5Yn;
        @Schema(description = "사용자버튼6사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn6Yn;
        @Schema(description = "사용자버튼7사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn7Yn;
        @Schema(description = "사용자버튼8사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn8Yn;
        @Schema(description = "사용자버튼9사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn9Yn;
        @Schema(description = "사용자버튼10사용여부", example = "0", allowableValues = {"0", "1"})
        private String btn10Yn;
        @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"U"})
        @NotEmpty
        private String rowStatus; // "U"(수정)
    }
}

/**
 * [API 샘플 예시]
 {
     "roleMenus":[
         // 수정
         {
         "authority": "BBS_ADMIN",
         "menuId": "BIZ_SAMPLE",
         "menuYn": "1",
         "useYn": "1",
         "searchYn": "1",
         "newYn": "1",
         "saveYn": "1",
         "deleteYn": "1",
         "printYn": "1",
         "excelYn": "1",
         "btn1Yn": "1",
         "btn2Yn": "1",
         "btn3Yn": "1",
         "btn4Yn": "1",
         "btn5Yn": "1",
         "btn6Yn": "1",
         "btn7Yn": "1",
         "btn8Yn": "1",
         "btn9Yn": "1",
         "btn10Yn": "1",
         "rowStatus": "U"
         }
     ]
 }
 */

/**
  [MPA 참조]
 [
     {
         AUTHORITY: "ROLE_USER"
         BTN1_YN: "0"
         BTN2_YN: "0"
         BTN3_YN: "0"
         BTN4_YN: "0"
         BTN5_YN: "0"
         BTN6_YN: "0"
         BTN7_YN: "0"
         BTN8_YN: "0"
         BTN9_YN: "0"
         BTN10_YN: "0"
         DELETE_YN: "0"
         EXCEL_YN: "0"
         MENU_ID: "BIZ_SAMPLE"
         MENU_NM: "샘플대메뉴"
         MENU_YN: "1"
         NEW_YN: "0"
         PRINT_YN: "0"
         REG_DT: 1653271841000
         REG_ID: "SYSTEM"
         SAVE_YN: "0"
         SEARCH_YN: "1"
         USE_YN: "1"
         __rowStatus: "U"
     }
 ]
 */





