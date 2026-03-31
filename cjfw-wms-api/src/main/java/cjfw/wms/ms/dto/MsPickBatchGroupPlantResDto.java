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
 * @description : 피킹그룹정보 플랜트 조회 결과 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@Schema(description = "피킹그룹정보 플랜트 조회 결과 DTO")
public class MsPickBatchGroupPlantResDto {
	@Schema(description = "플랜트 코드")
    private String plantCode;
    
    @Schema(description = "플랜트 명")
    private String plantName;
    
    @Schema(description = "물류센터 코드")
    private String dcCode;
}
