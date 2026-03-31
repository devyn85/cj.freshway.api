package cjfw.wms.ms.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 외부창고 요율 SKU 단건조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
@Schema(description = "외부창고 요율 SKU 단건조회 요청 DTO")
public class MsExDcRateGetSkuDataSelectReqDto extends CommonDto {



    @Schema(description = "상품 코드 (PK)")
    private String sku;
    
    @Schema(description = "창고 ")
    private String organize;
}
