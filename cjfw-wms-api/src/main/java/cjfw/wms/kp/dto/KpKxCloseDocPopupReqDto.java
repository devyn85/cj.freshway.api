package cjfw.wms.kp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.12.11
 * @description : 문서정보 팝업 REQ DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "문서정보 팝업 REQ DTO")
public class KpKxCloseDocPopupReqDto {

    @Schema(description = "문서번호", example = "DOC20251211001")
    private String docno;
    
    @Schema(description = "납품일자")
    private String deliverydate;
    
    @Schema(description = "센터코드")
    private String dccode;
    
    @Schema(description = "문서구분")
    private String doctype;

    @Schema(description = "문서라인")
    private String docline;
    
    @Schema(description = "시리얼여부")
    private String serialyn;
    
    @Schema(description = "상품코드")
    private String sku;
    
    @MultiSearch
    @Schema(description = "상품코드")
    private List<List<String>> skuMulti;
    
    @Schema(description = "시작일자")
    private String trandateFrom;
    
    @Schema(description = "종료일자")
    private String trandateTo;
    
    @Schema(description = "처리타입")
    private String trantype;
    
    @Schema(description = "로케이션")
    private String loc;
    
    @Schema(description = "문서일자")
    private String docdt;    
    
    @Schema(description = "센터코드(KX)")
    private String kxDccode;

    @Schema(description = "소유권이전번호")
    private String transferkey;
    
}
