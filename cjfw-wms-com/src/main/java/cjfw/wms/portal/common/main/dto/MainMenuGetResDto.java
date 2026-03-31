package cjfw.wms.portal.common.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 사용자 권한메뉴 조회 응답 DTO
 */
@Data
@Schema(description = "사용자 메뉴 조회 응답")
public class MainMenuGetResDto {
    @Schema(description = "메뉴ID", nullable = false, example = "FO_MGT")
    private String menuId;

    @Schema(description = "메뉴명", nullable = false, example = "시스템관리")
    private String menuNm;

    @Schema(description = "메뉴URL", nullable = false, example = "/sysmgt")
    private String menuUrl;

    @Schema(description = "상위메뉴ID", example = "null")
    private String upperMenuId;

    @Schema(description = "정렬순서", example = "9100")
    private Integer sortOrder;

    @Schema(description = "메뉴여부", example = "1", allowableValues = {"0", "1"})
    private String menuYn;

    @Schema(description = "사용여부", example = "1", allowableValues = {"0", "1"})
    private String useYn;

    @Schema(description = "조회 사용여부", example = "1", allowableValues = {"0", "1"})
    private String searchYn;

    @Schema(description = "신규 사용여부", example = "1", allowableValues = {"0", "1"})
    private String newYn;

    @Schema(description = "삭제 사용여부", example = "1", allowableValues = {"0", "1"})
    private String deleteYn;

    @Schema(description = "저장 사용여부", example = "1", allowableValues = {"0", "1"})
    private String saveYn;

    @Schema(description = "프린트 사용여부", example = "0", allowableValues = {"0", "1"})
    private String printYn;

    @Schema(description = "엑셀다운로드 사용여부", example = "0", allowableValues = {"0", "1"})
    private String excelYn;

    @Schema(description = "버튼1 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn1Yn;

    @Schema(description = "버튼2 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn2Yn;

    @Schema(description = "버튼3 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn3Yn;

    @Schema(description = "버튼4 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn4Yn;

    @Schema(description = "버튼5 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn5Yn;

    @Schema(description = "버튼6 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn6Yn;

    @Schema(description = "버튼7 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn7Yn;

    @Schema(description = "버튼8 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn8Yn;

    @Schema(description = "버튼9 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn9Yn;

    @Schema(description = "버튼10 사용여부", example = "0", allowableValues = {"0", "1"})
    private String btn10Yn;
}
