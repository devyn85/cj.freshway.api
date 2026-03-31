package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.11
 * @description : 월간주간발주량체크STO Res DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "월간주간발주량체크STO 조회")
public class OmPurchaseCheckReqDto extends CommonDto {
	@Schema(description = "물류센터 코드", example = "DC001")
    private String dcCode[];

    @Schema(description = "고객사 코드", example = "STORER001")
    private String storerKey;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "전표일자", example = "20250711")
    private String slipDt;
}
