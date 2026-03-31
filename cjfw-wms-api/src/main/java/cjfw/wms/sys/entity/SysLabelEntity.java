package cjfw.wms.sys.entity;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.08.21
 * @description : 라벨 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "라벨 관리 Entity")
public class SysLabelEntity extends CommonTriggerDto {
    /** 시스템구분 */
    @Schema(description = "시스템구분", maxLength = 10)
    private String systemCl;

    /** 라벨코드 */
    @Schema(description = "라벨코드", maxLength = 256)
    private String labelCd;

    /** 라벨명 */
    @Schema(description = "라벨명", maxLength = 200)
    private String labelNm;

    /** 속성1 */
    @Schema(description = "FORMAT", maxLength = 30)
    private String attribute1;

    /** 속성2 */
    @Schema(description = "VISIBLE", maxLength = 20)
    private String attribute2;

    /** 속성3 */
    @Schema(description = "ENABLE", maxLength = 20)
    private String attribute3;

    /** 속성4 */
    @Schema(description = "TYPE", maxLength = 20)
    private String attribute4;

    /** 속성5 */
    @Schema(description = "LIMIT", maxLength = 20)
    private String attribute5;

    /** 속성6 */
    @Schema(description = "DECIMAL", maxLength = 20)
    private String attribute6;

    /** 사용여부 */
    @Schema(description = "사용여부", defaultValue = "0", maxLength = 1)
    private String useYn = "0";

    /** 등록자 */
    @Schema(description = "등록자", defaultValue = "SYSTEM", maxLength = 20)
    private String regId;

    /** 등록일자 */
    @Schema(description = "등록일자")
    private String regDate;

    /** 수정자 */
    @Schema(description = "수정자", maxLength = 20)
    private String modrId;

    /** 수정일자 */
    @Schema(description = "수정일자")
    private String modDate;

    /** 소스구분 */
    @Schema(description = "소스구분", maxLength = 10)
    private String sourceCl;

    /** 라벨임시명 */
    @Schema(description = "라벨임시명", maxLength = 200)
    private String labelTmpnm;

    /** 라벨타입 */
    @Schema(description = "라벨타입", maxLength = 3)
    private String labelType;
    
    @Schema(description = "정렬순서")
    private String alignType;
}