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
 * @description : 기둥/빈 공간 정보 Zone 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "기둥/빈 공간 정보 Zone 조회 결과 DTO")
public class MsBlankLocZoneResDto {
	@Schema(description = "센터 코드", example = "2600")
    private String dcCode;
    
    @Schema(description = "Zone코드")
    private String baseCode;
    
    @Schema(description = "Zone명")
    private String baseDescr;
}
