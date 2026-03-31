package cjfw.wms.cm.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : Automated (GitHub Copilot)
 * @date        : 2025.11.20
 * @description : 즐겨찾기 메뉴 저장 요청 DTO
 */
@Data
@Schema(description = "즐겨찾기 메뉴 저장 요청")
public class CmMyMenuPopupReqDto extends CommonDto {

    /** 사용자ID (gUserId) */
    @Schema(description = "사용자ID", example = "user01")
    private String userId;

    /** 프로그램코드 (progCd) */
    @Schema(description = "프로그램코드", example = "PRG001")
    private String progCd;

    /** 메뉴구분 (menuType) - F:폴더, M:메뉴 */
    @Schema(description = "메뉴구분 (F=폴더, M=메뉴)", example = "M", allowableValues = {"F","M"})
    private String menuType;

    /** 메뉴 순번 (menuSeq) */
    @Schema(description = "메뉴 순번", example = "1")
    private Integer menuSeq;

    /** 상위폴더ID (uprFolderId) */
    @Schema(description = "상위폴더ID", example = "0")
    private String uprFolderId;
    
    /** 노드 키값 (key) */
    private String key;
    
    /** 노드 타이틀 (title) */
    private String title;
    
    /** 하위메뉴 리스트 */
    @Schema(description = "하위메뉴 리스트")
    private List<CmMyMenuPopupReqDto> children;
    

}