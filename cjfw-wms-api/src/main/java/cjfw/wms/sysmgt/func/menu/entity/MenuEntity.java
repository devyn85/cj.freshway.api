package cjfw.wms.sysmgt.func.menu.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 사용자 Entity(테이블: TB_CF_MENU)
 */
@Data
public class MenuEntity {
    private String menuId; // 메뉴ID
    private String upperMenuId; // 상위메뉴ID
    private String menuNm; // 메뉴명
    private String menuUrl; // 메뉴URL
    private String menuYn; // 메뉴여부(1 or 0)
    private String useYn; // 사용여부(1 or 0)
    private String description; // 메뉴설명
    private Integer sortOrder; // 정렬순서
    private String isPopup; // 팝업여부
    private String refMenuId; // 참조메뉴ID(필요시 활용)
    private String regId;
    private LocalDateTime regDt;
}