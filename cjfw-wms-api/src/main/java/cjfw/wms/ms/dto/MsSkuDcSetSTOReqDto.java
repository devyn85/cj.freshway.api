package cjfw.wms.ms.dto;

import java.util.List;

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
 * @date        : 2025.07.04
 * @description : 센터상품속성(광역일배) 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터상품속성(광역일배) 조회 조건 DTO")
public class MsSkuDcSetSTOReqDto extends CommonDto {
	
	@Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "멀티 센터 코드", example = "DC001")
    private String multiDcCode[];
    
    @Schema(description = "멀티 센터 코드", example = "DC001")
    private String multiToDcCode[];
    
    @Schema(description = "센터 코드", example = "DC001")
    private String dcCode;

    @Schema(description = "대상 센터 코드")
    private String toDcCode;

    @Schema(description = "고객사", example = "CUST001")
    private String storerKey;

    @Schema(description = "상품 코드", example = "SKU001")
    private String sku;

    @Schema(description = "상품 설명")
    private String skuDescr;

    @Schema(description = "삭제 여부", example = "N")
    private String delYn;

    @Schema(description = "SMS 처리 대상 여부")
    private String smsYn;
    
    @Schema(description = "상품 코드", example = "SKU001")
    private String skuCode;
    
    /** 저장 목록 */
	@Schema(description = "저장 목록", example = "")
	private List<MsSkuDcSetSTOReqDto> skuDcSetSTOList;
}
