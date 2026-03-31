package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.07.17
 * @description : 운송사정보 조회(목록) 요청 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "운송사정보 조회(목록) 요청 DTO")
public class MsCarrierReqDto extends CommonDto {
	@Schema(description = "데이터 번호")
    private String serialKey;
	
    @Schema(description = "고객사코드")
    private String storerKey;
    
    @Schema(description = "운송사유형")
    private String carrierType;
    
    @Schema(description = "진행상태")
    private String statusCode;

    @Schema(description = "운송사코드")
    private String carrierKey;

    @Schema(description = "운송사명")
    private String carrierName;
    
    @Schema(description = "삭제여부")
    private String delYn;
}
