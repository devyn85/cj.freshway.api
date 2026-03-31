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
 * @description : SKU 스펙 조회 요청 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
@Schema(description = "SKU 스펙 조회 요청 DTO")
public class MsExDcRateGetSkuSpecResDto extends CommonDto {

	/**고객사 코드 */
    @Schema(description = "고객사 코드")
    private String storerKey;

    /**스펙 카테고리*/
    @Schema(description = "스펙 카테고리")
    private String specCategory;

    /**스펙 코드*/
    @Schema(description = "스펙 코드")
    private String specCode;

    /**스펙 설명*/
    @Schema(description = "스펙 설명")
    private String specDescr;

    /**스펙 클래스*/
    @Schema(description = "스펙 클래스")
    private String specClass;

    /**스펙 타입*/
    @Schema(description = "스펙 타입")
    private String specType;

    /**상품 그룹 */
    @Schema(description = "상품 그룹")
    private String skuGroup;
}
