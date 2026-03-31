package cjfw.wms.kp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.20
 * @description : 이체및출고현황 상세 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이체및출고현황 상세 결과")
public class KpOrderQtyStoInfoDetailResDto {
	
	@Schema(description = "상품코드", example = "")
    private String sku;

    @Schema(description = "상품명", example = "")
    private String skuname;

    @Schema(description = "저장조건", example = "")
    private String storagetype;

    @Schema(description = "상품대분류", example = "")
    private String skuLdesc;

    @Schema(description = "전용(Y) / 범용(N)", example = "")
    private String reference15;

    @Schema(description = "총중량(Gross Weight)", example = "")
    private String grossweight; 

    @Schema(description = "박스입수", example = "")
    private String qtyperbox;

    @Schema(description = "PLT입수", example = "")
    private String boxperplt;

    @Schema(description = "수급담당 코드(Buyer Key)", example = "")
    private String buyerkey;

    @Schema(description = "수급담당자 이름", example = "")
    private String buyerName; 

    // 월 출고량 (자체 + 이체)
    // --------------------------------------------------
    @Schema(description = "월출고량 전체", example = "")
    private String confirmqty;

    @Schema(description = "월출고량 수도권", example = "")
    private String confirmqtyS;

    @Schema(description = "월출고량 이천", example = "")
    private String confirmqty2600;

    @Schema(description = "월출고량 수원단지", example = "")
    private String confirmqtySuwon;

    @Schema(description = "월출고량 동탄단지", example = "")
    private String confirmqtyDongtan;

    @Schema(description = "월출고량 제주", example = "")
    private String confirmqty2041;

    @Schema(description = "월출고량 양산", example = "")
    private String confirmqty2260;

    @Schema(description = "월출고량 장성", example = "")
    private String confirmqty2230;

    @Schema(description = "월출고량 KX센터", example = "")
    private String confirmqty1000;

    // 이체 출고량 (STO)
    // --------------------------------------------------
    @Schema(description = "이체출고량 전체", example = "")
    private String confirmqtySto;

    @Schema(description = "이체출고량 수도권", example = "")
    private String confirmqtySSto;

    @Schema(description = "이체출고량 이천", example = "")
    private String confirmqty2600Sto;

    @Schema(description = "이체출고량 수원단지", example = "")
    private String confirmqtySuwonSto;

    @Schema(description = "이체출고량 동탄단지", example = "")
    private String confirmqtyDongtanSto;

    @Schema(description = "이체출고량 제주", example = "")
    private String confirmqty2041Sto;

    @Schema(description = "이체출고량 양산", example = "")
    private String confirmqty2260Sto;

    @Schema(description = "이체출고량 장성", example = "")
    private String confirmqty2230Sto;

    @Schema(description = "이체출고량 KX센터", example = "")
    private String confirmqty1000Sto;

    // 비중 (Ratio)
    // --------------------------------------------------
    @Schema(description = "자체출고비중", example = "")
    private String ratioQty;

    @Schema(description = "이체출고비중", example = "")
    private String ratioQtySto;
}
