package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.24
 * @description : 기둥/빈 공간 정보 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "기둥/빈 공간 정보 조회 결과 DTO")
public class MsBlankLocResDto {
	@Schema(description = "데이터번호 (PK)")
	private String serialKey;
	
	@Schema(description = "센터 코드", example = "2600")
    private String dcCode;

    @Schema(description = "로케이션", example = "R01A01B01")
    private String loc;

    @Schema(description = "소속 존", example = "H07")
    private String zone;

    @Schema(description = "로케이션명")
    private String description;

    @Schema(description = "로케이션 유형")
    private String locType;

    @Schema(description = "랙 라인")
    private String rackLine;

    @Schema(description = "랙 컬럼")
    private String rackColumn;
    
    @Schema(description = "삭제여부")
    private String delYn;

    @Schema(description = "최초 등록자")
    private String addWho;
    
    @Schema(description = "최초 등록일시")
    private String addDate;

    @Schema(description = "최종 변경자")
    private String editWho;
    
    @Schema(description = "최종 변경일시")
    private String editDate;
    
    @Schema(description = "최종 변경자")
    private String editWhoNm;
    
    @Schema(description = "최초 등록자")
    private String addWhoNm;
}
