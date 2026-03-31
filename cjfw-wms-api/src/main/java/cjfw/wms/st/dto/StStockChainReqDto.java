package cjfw.wms.st.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.23
 * @description : 상품별현재고(PLT)현황 요청 DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "상품별현재고(PLT)현황 요청 DTO")
public class StStockChainReqDto extends CommonDto {

	@Schema(description = "물류센터 코드")
    private String dcCode;
	
	@Schema(description = "멀티 물류센터 코드", example = "2600")
    private String multiDcCode[];

    @Schema(description = "상품 코드")
    private String multiSku;
  
    @Schema(description = "외식 여부")
    private String cmbYn;
    
    @Schema(description = "외식 여부")
    private String reference15;
    
    @Schema(description = "재고 유형")
    private String stockType;

    @Schema(description = "재고 등급")
    private String stockGrade;
}
