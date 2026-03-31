package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고 요율 평균치 체크 응답 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
 @Schema(description = "외부창고 요율 평균치 체크 응답 DTO")
public class MsExDcRateCheckRateAvgResDto {

    @Schema(description = "평균치 내 포함 여부 (1 이상이면 통과)")
    private Integer checkCnt;
}
