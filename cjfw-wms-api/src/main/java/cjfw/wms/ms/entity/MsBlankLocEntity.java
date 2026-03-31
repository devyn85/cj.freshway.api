package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 기둥/빈 공간 정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "기둥/빈 공간 정보 Entity") 
public class MsBlankLocEntity extends CommonDto {	
	/** 데이터번호 (PK) */
    @Schema(description = "데이터번호 (PK)")
    private String serialKey;

    /** 고객사코드 */
    @Schema(description = "고객사코드", defaultValue = "FW00", maxLength = 20)
    private String storerKey = "FW00";

    /** 센터코드 */
    @Schema(description = "센터코드", maxLength = 10)
    private String dcCode;

    /** 로케이션 */
    @Schema(description = "로케이션", maxLength = 30)
    private String loc;

    /** 로케이션명 */
    @Schema(description = "로케이션명", maxLength = 200)
    private String description;

    /** 피킹존 */
    @Schema(description = "피킹존", maxLength = 30)
    private String zone;

    /** 랙행번호 (숫자, 소수점 5자리 포함) */
    @Schema(description = "랙행번호 (숫자, 소수점 5자리 포함)")
    private String rackLine;

    /** 랙열번호 (숫자, 소수점 5자리 포함) */
    @Schema(description = "랙열번호 (숫자, 소수점 5자리 포함)")
    private String rackColumn;

    /** 로케이션유형 */
    @Schema(description = "로케이션유형", maxLength = 10)
    private String locType;

    /** 삭제여부 */
    @Schema(description = "삭제여부", defaultValue = "N")
    private String delYn = "N";
    
    /** 최초등록시간 (자동 생성) */
    @Schema(description = "최초등록시간 (자동 생성)")
    private String addDate;

    /** 최종변경시간 (자동 갱신) */
    @Schema(description = "최종변경시간 (자동 갱신)")
    private String editDate;

    /** 최초등록자 */
    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24)
    private String addWho;

    /** 최종변경자 */
    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24)
    private String editWho;
}
