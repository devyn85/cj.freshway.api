package cjfw.wms.ms.dto;

import java.math.BigDecimal;

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
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 센터서류 담당자 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터서류 담당자 조회 조건 DTO")
public class MsCenterDocUserReqDto extends CommonDto {
	@Schema(description = "멀티 센터 코드", example = "2650")
    private String multiDcCode[];
	
	@Schema(description = "데이터번호 (PK, DB Sequence에 의해 자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal serialKey;
	
    @Schema(description = "사용자 이름")
    private String userNm;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "센터코드", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 14)
    private String dcCode;
    
    @Schema(description = "이메일", maxLength = 50)
    private String email;
}
