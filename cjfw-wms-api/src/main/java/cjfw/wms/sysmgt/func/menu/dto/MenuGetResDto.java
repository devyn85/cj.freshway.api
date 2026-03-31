package cjfw.wms.sysmgt.func.menu.dto;

import lombok.Data;

/**
 * 메뉴 조회 응답 DTO
 */
@Data
public class MenuGetResDto {
    private String rowId;
    private String menuId; 			// 메뉴ID
    private String upperMenuId; 	// 상위메뉴ID
    private String menuNm; 			// 메뉴명
    private String menuUrl; 		// 메뉴URL
    private String menuYn; 			// 메뉴여부
    private String useYn; 			// 사용여부
    private String description; 	// 메뉴설명
    private Integer sortOrder; 		// 정렬순서
    private String isPopup; 		// 팝업여부
    private String refMenuId; 		// 참조메뉴ID
    private String regId;
    private String regDt;
    private String refUpperMenuId; 	// Grid Tree 참조용 상위메뉴ID
}

/**
 * [MPA 참조]
 - DESCRIPTION: null
 - IS_POPUP: "0"
 - MENU_ID: "BIZ_SAMPLE_MENU"
 - MENU_NM: "샘플중메뉴"
 - MENU_URL: null
 - MENU_YN: "1"
 - REF_MENU_ID: null
 - REF_UPPER_MENU_ID: "BIZ_SAMPLE"
 - REG_DT: "2022-05-23"
 - REG_ID: "sj_song"
 - ROW_ID: "r2"
 - SORT_ORDER: 1100
 - UPPER_MENU_ID: "BIZ_SAMPLE"
 - USE_YN: "1"


 [API 샘플]
 {
 "rowId": "r1",
 "menuId": "BIZ_SAMPLE",
 "upperMenuId": null,
 "menuNm": "샘플대메뉴",
 "menuUrl": null,
 "menuYn": "1",
 "useYn": "1",
 "description": null,
 "sortOrder": 1000,
 "isPopup": "0",
 "refMenuId": null,
 "regId": "sj_song",
 "regDt": "2022-05-23",
 "refUpperMenuId": null
 },
 */