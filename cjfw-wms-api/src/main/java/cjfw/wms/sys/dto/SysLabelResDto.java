package cjfw.wms.sys.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.08.21
 * @description : 라벨 정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "라벨 정보 조회 결과 DTO")
public class SysLabelResDto extends CommonDto {
    @Schema(description = "라벨코드")
    private String labelCd;

    @Schema(description = "시스템구분")
    private String systemCl;
    
    @Schema(description = "라벨유형")
    private String labelType;

    @Schema(description = "라벨명")
    private String labelNm;
    
    @Schema(description = "정렬순서")
    private String alignType;

    @Schema(description = "마이플렛폼 도메인 속성(TYPE)")
    private String attribute4;

    @Schema(description = "마이플렛폼 도메인 속성(LIMIT)")
    private String attribute5;

    @Schema(description = "마이플렛폼 도메인 속성(ENABLE)")
    private String attribute3;

    @Schema(description = "마이플렛폼 도메인 속성(DECIMAL)")
    private String attribute6;

    @Schema(description = "마이플렛폼 도메인 속성(FORMAT)")
    private String attribute1;

    @Schema(description = "마이플렛폼 도메인 속성(VISIBLE)")
    private String attribute2;

    @Schema(description = "사용여부")
    private String useYn;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
