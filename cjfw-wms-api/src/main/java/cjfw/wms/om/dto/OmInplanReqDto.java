package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문이력현황 Req DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "주문이력현황 조회")
public class OmInplanReqDto extends CommonDto {
	@Schema(description = "물류센터 코드")
    private String dcCode[];	

    @Schema(description = "시작출고일자", example = "20250709")
    private String fromDocdt;
    
    @Schema(description = "종료출고일자", example = "20250709")
    private String toDocdt;
    
    @Schema(description = "시작배송일자", example = "20250709")
    private String fromDeliveryDate;
    
    @Schema(description = "종료배송일자", example = "20250709")
    private String toDeliveryDate;
    
    @Schema(description = "시작변경일자", example = "20250709")
    private String fromModifyDate;
    
    @Schema(description = "종료변경일자", example = "20250709")
    private String toModifyDate;    
    
    @Schema(description = "영업조직")
    private String deliveryGroup;
    
    @Schema(description = "진행상태")
    private String status;
    
    @Schema(description = "저장유무 헤더")
    private String channelH;
    
    @Schema(description = "저장유무 디테일")
    private String channelD;
    
    @Schema(description = "문서유형")
    private String docType;
    
    @Schema(description = "문서번호")
    private String docNo;
    
    @Schema(description = "문서일자")
    private String docDt;
    
    @Schema(description = "문서라인")
    private String docLine;
    
    @Schema(description = "선택유형")
    private String selectType;
    
    @Schema(description = "관리처코드")
    private String custKey;
    
    @Schema(description = "협력사코드")
    private String vendorCd;
    
    @Schema(description = "상품코드")
    private String skuCd;
    
    @Schema(description = "삭제여부")
    private String delYn;
    
    @Schema(description = "회사코드")
    private String storerKey;
    
    @Schema(description = "배송일자", example = "20250709")
    private String deliveryDate;
}
