package cjfw.wms.sysmgt.func.menuI18N.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 사용자 Entity(테이블: TB_CF_MENU_I18N)
 */
@Data
public class MenuI18NEntity {
    private String menuId; // 메뉴ID
    private String menuNm; // 메뉴명
    private String languageCd; // 다국어 코드
    private String regId;
    private LocalDateTime regDt;
}
