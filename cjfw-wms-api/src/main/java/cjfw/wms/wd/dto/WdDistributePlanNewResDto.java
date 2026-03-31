package cjfw.wms.wd.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.11.07 
 * @description : 미출예정확인(New) - 미출예정 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.07 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "미출예정확인(New) - 미출예정 응답")
public class WdDistributePlanNewResDto {
	@Schema(description = "출고일자")
    private String slipdt;
	
	@Schema(description = "주문번호")
    private String docno;
	
	@Schema(description = "관리처코드")
    private String toCustkey;
    
    @Schema(description = "관리처명")
	private String toCustname;
    
    @Schema(description = "상품코드")
    private String sku;
    
    @Schema(description = "상품명칭")
    private String skuname;
    
    @Schema(description = "플랜트")
    private String plantDescr;
    
    @Schema(description = "상품유형-1")
    private String skutype;
    
    @Schema(description = "외식전용구분")
    private String reference15;
    
    @Schema(description = "현재고수량")
    private String qty;
    
    @Schema(description = "이천")
    private String qty2600;
    
    @Schema(description = "수원")
    private String qty2620;
    
    @Schema(description = "수원2")
    private String qty2630;
    
    @Schema(description = "동탄")
    private String qty2650;
    
    @Schema(description = "통탄2")
    private String qty2660;
    
    @Schema(description = "양산")
    private String qty2260;
    
    @Schema(description = "장성")
    private String qty2230;
    
    @Schema(description = "이동중재고")
    private String stoSt;
    
    @Schema(description = "통합이동중재고")
    private String totalStoSt;
    
    @Schema(description = "가용재고수량")
    private String stOpenqty;
    
    @Schema(description = "부족수량")
    private String shortageqty2;
    
    @Schema(description = "주문량")
    private String orderqty;
    
    @Schema(description = "통합주문량")
    private String totalOrderqty;
    
    @Schema(description = "입고예정량")
    private String dpQty;
    
    @Schema(description = "통합입고예정량")
    private String totalDpQty;
    
    @Schema(description = "PO수량")
    private String poDpQty;
    
    @Schema(description = "재고할당수량")
    private String qtyallocated;
    
    @Schema(description = "피킹재고")
    private String qtypicked;
    
    @Schema(description = "주문단위")
    private String uom;
    
    @Schema(description = "C/D타입")
    private String crossdocktype;
    
    @Schema(description = "수급담당")
    private String buyername;
    
    @Schema(description = "주문조정등재")
    private String procpossYn;
}
