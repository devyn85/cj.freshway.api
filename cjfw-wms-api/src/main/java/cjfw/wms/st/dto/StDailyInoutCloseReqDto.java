package cjfw.wms.st.dto;

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
 * @date        : 2025.07.22
 * @description : 수불마감정보 기능을 구현한 Req DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수불마감정보 조회 ")
public class StDailyInoutCloseReqDto extends CommonDto {
	
	@Schema(description = "멀티 센터 코드", example = "2650")
    private String multiDcCode[];
	
    @Schema(description = "센터 코드", example = "2650")
    private String dcCode;

    @Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "수불 일자", example = "20240731")
    private String inoutDt;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "상태")
    private String status;
    
    @Schema(description = "데이터 번호")
    private String serialKey;
}
