package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : GitHub Copilot
 * @date        : 2025.10.27
 * @description : 즐겨찾기 메뉴 팝업 조회 응답 DTO
 */
@Data
@Schema(description = "즐겨찾기 메뉴 팝업 조회 응답")
public class CmMyMenuPopupResDto extends CommonDto {

    @Schema(description = "프로그램 코드", example = "PRG001")
    private String progCd;

    @Schema(description = "프로그램 명", example = "주문조회")
    private String progNm;

    @Schema(description = "프로그램 내부 순번", example = "1")
    private String progNo;

    @Schema(description = "프로그램 URL", example = "/order/list")
    private String progUrl;

    // 추가된 필드: FRAMEONE_MYMENU 테이블 조회 결과에 대응
    @Schema(description = "메뉴 순번", example = "1")
    private Integer menuSeq;

    @Schema(description = "메뉴 타입", example = "M")
    private String menuType;

    @Schema(description = "폴더 명", example = "즐겨찾기")
    private String folderNm;

    @Schema(description = "상위 폴더 ID", example = "ROOT")
    private String uprFolderId;
    
//    private String progLvl;
//    private String topmenuYn;
//    private String menuYn;
}