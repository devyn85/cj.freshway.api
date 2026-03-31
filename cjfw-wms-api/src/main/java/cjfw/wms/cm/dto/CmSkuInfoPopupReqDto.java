package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 팝업 상품정보조회 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 정보 팝업조회 Request DTO")
public class CmSkuInfoPopupReqDto extends CommonDto {
	
	/** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;
}