package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.05.19 
 * @description : 세분류팝업 기능을 구현한 resDto  
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 ParkJinWoo 생성
 */
@Data
@Schema(description = "차량+권역 팝업 조회 응답")
public class CmSkuGroupListPopupResDto {


    @Schema(description = "스펙 코드", nullable = false, example = "FW00")
    private String code;

    @Schema(description = "스펙명", nullable = false, example = "호밀빵")
    private String name;
}