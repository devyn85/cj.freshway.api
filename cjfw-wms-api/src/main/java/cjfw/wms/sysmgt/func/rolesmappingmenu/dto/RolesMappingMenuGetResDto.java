package cjfw.wms.sysmgt.func.rolesmappingmenu.dto;

import lombok.Data;

/**
 * 권한메뉴 조회 Response DTO
 */
@Data
public class RolesMappingMenuGetResDto {
    private String menuId; // 메뉴ID
    private String menuNm; // 메뉴명
    private String authority; // 권한
    private String menuYn; // 메뉴여부
    private String useYn; // 사용여부
    private String searchYn; // 조회
    private String newYn; // 신규
    private String saveYn; // 저장
    private String deleteYn; // 삭제
    private String printYn; // 프린트
    private String excelYn; // 엑셀다운로드
    private String btn1Yn;
    private String btn2Yn;
    private String btn3Yn;
    private String btn4Yn;
    private String btn5Yn;
    private String btn6Yn;
    private String btn7Yn;
    private String btn8Yn;
    private String btn9Yn;
    private String btn10Yn;
    private String regId;
    private String regDt;

}

/**
 [API 샘플 예시]
 "data": [
     {
         "menuId": "BIZ_SAMPLE",
         "menuNm": "샘플대메뉴",
         "authority": "BBS_ADMIN",
         "menuYn": "0",
         "useYn": "0",
         "searchYn": "0",
         "newYn": "0",
         "saveYn": "0",
         "deleteYn": "0",
         "printYn": "0",
         "excelYn": "0",
         "btn1Yn": "0",
         "btn2Yn": "0",
         "btn3Yn": "0",
         "btn4Yn": "0",
         "btn5Yn": "0",
         "btn6Yn": "0",
         "btn7Yn": "0",
         "btn8Yn": "0",
         "btn9Yn": "0",
         "btn10Yn": "0",
         "regId": null,
         "regDt": null
     },
     ...
 ]
 */

/**
  [MPA 참조]
 {
     "MENU_ID":"BIZ_SAMPLE",
     "MENU_NM":"샘플대메뉴",
     "AUTHORITY":"BBS_ADMIN",
     "MENU_YN":"0",
     "USE_YN":"0",
     "SEARCH_YN":"0",
     "NEW_YN":"0",
     "SAVE_YN":"0",
     "DELETE_YN":"0"
     "PRINT_YN":"0",
     "EXCEL_YN":"0",
     "BTN1_YN":"0",
     "BTN2_YN":"0",
     "BTN3_YN":"0",
     "BTN4_YN":"0",
     "BTN5_YN":"0",
     "BTN6_YN":"0",
     "BTN7_YN":"0",
     "BTN8_YN":"0",
     "BTN9_YN":"0",
     "BTN10_YN":"0",
     "REG_ID":null,
     "REG_DT":null,
 }

 */