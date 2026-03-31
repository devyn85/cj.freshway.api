package cjfw.wms.sysmgt.func.rolesmappingmenu.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 권한메뉴 Entity(테이블: TB_CF_MENU_ROLE)
 */
@Data
public class RolesMappingMenuEntity {
    private String authority; // 권한
    private String menuId; // 메뉴ID
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
    private LocalDateTime regDt;
}
