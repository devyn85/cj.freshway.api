package cjfw.wms.sys.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.08.21
 * @description : 라벨 조회 조건 DTO
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
@Schema(description = "라벨 정보 조회 조건 DTO")
public class SysLabelReqDto extends CommonDto {
	@Schema(description = "시스템구분")
    private String systemCl;
	
	@Schema(description = "라벨유형")
    private String labelType;

    @Schema(description = "라벨코드")
    private String labelCd;

    @Schema(description = "라벨명")
    private String labelNm;

    @Schema(description = "사용여부")
    private String useYn;
    
    @Schema(description = "정렬순서")
    private String alignType;
    
    /** 속성1 */
    @Schema(description = "FORMAT")
    private String attribute1;

    /** 속성2 */
    @Schema(description = "VISIBLE")
    private String attribute2;

    /** 속성3 */
    @Schema(description = "ENABLE")
    private String attribute3;

    /** 속성4 */
    @Schema(description = "TYPE")
    private String attribute4;

    /** 속성5 */
    @Schema(description = "LIMIT")
    private String attribute5;

    /** 속성6 */
    @Schema(description = "DECIMAL")
    private String attribute6;
}
