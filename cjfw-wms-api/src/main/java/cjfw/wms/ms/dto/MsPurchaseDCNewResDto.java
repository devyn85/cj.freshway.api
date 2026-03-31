package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : 수발주마스터 조회(목록) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsPurchaseDCNewResDto {
			
	/** 체크여부 */
    @Schema(description = "체크여부", example = "")
    private String checkyn; 

    /** 수발주IN */
    @Schema(description = "수발주IN", example = "")
    private String purchaseIn; 

    /** 수발주OUT */
    @Schema(description = "수발주OUT", example = "")
    private String purchaseOut; 

    /** 재고 IN */
    @Schema(description = "재고 IN", example = "")
    private String stockin; 
    
    /** 재고OUT */
    @Schema(description = "재고OUT", example = "")
    private String stockout; 

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;

    /** 저장조건명 */
    @Schema(description = "저장조건명", example = "")
    private String storagetypenm;

    /** 상품유형구분 */
    @Schema(description = "상품유형구분", example = "")
    private String skutypedescr;

    /** 상품범주(대) */
    @Schema(description = "상품범주(대)", example = "")
    private String skuLdesc;

    /** CJ상품코드 */
    @Schema(description = "CJ상품코드", example = "")
    private String custsku;

    /** 체인전용구분 */
    @Schema(description = "체인전용구분", example = "")
    private String reference15;

    /** 단위중량 */
    @Schema(description = "단위중량", example = "")
    private String netweight;

    /** 박스입수 */
    @Schema(description = "박스입수", example = "")
    private String qtyperbox; 

    /** PLT입수 */
    @Schema(description = "PLT입수", example = "")
    private String boxperplt; 

    /** 본점코드 */
    @Schema(description = "본점코드", example = "")
    private String parentcustkey;

    /** 본점 */
    @Schema(description = "본점", example = "")
    private String parentcustname;

    /** 브랜드마스터 */
    @Schema(description = "브랜드마스터", example = "")
    private String brandnameFc;

    /** 고객코드 */
    @Schema(description = "고객코드", example = "")
    private String custkey;

    /** 고객명 */
    @Schema(description = "고객명", example = "")
    private String custname;

    /** 2차 협력사 코드 */
    @Schema(description = "2차 협력사 코드", example = "")
    private String slaveCustkey;

    /** 2차 협력사 명 */
    @Schema(description = "2차 협력사 명", example = "")
    private String slaveCustname;

    /** 리드타임 */
    @Schema(description = "리드타임", example = "")
    private String leadtime; 

    /** MOQ(업체단위) */
    @Schema(description = "MOQ(업체단위)", example = "")
    private String moqVender; 

    /** MOQ(상품 단위) */
    @Schema(description = "MOQ(상품 단위)", example = "")
    private String moqSku; 

    /** 수급담당(최종) */
    @Schema(description = "수급담당(최종)", example = "")
    private String lastbuyernm;

    /** 공급SITE */
    @Schema(description = "공급SITE", example = "")
    private String supplycm;

    /** 재고배치 */
    @Schema(description = "재고배치", example = "")
    private String stockdc;

    /** 이원화유무 */
    @Schema(description = "이원화유무", example = "")
    private String dupyn; 

    /** 수급센터 2600 */
    @Schema(description = "수급센터 2600", example = "")
    private String poDc2600;

    /** 수급센터 2620 */
    @Schema(description = "수급센터 2620", example = "")
    private String poDc2620;

    /** 수급센터 2630 */
    @Schema(description = "수급센터 2630", example = "")
    private String poDc2630;

    /** 수급센터 2650 */
    @Schema(description = "수급센터 2650", example = "")
    private String poDc2650;

    /** 수급센터 2660 */
    @Schema(description = "수급센터 2660", example = "")
    private String poDc2660;

    /** 수급센터 2260 */
    @Schema(description = "수급센터 2260", example = "")
    private String poDc2260;

    /** 수급센터 2250 */
    @Schema(description = "수급센터 2250", example = "")
    private String poDc2250;

    /** 수급센터 2230 */
    @Schema(description = "수급센터 2230", example = "")
    private String poDc2230;

    /** 수급센터 2270 */
    @Schema(description = "수급센터 2270", example = "")
    private String poDc2270;

    /** 수급센터 1000 */
    @Schema(description = "수급센터 1000", example = "")
    private String poDc1000;

    /** 수급센터 2900 */
    @Schema(description = "수급센터 2900", example = "")
    private String poDc2900;

    /** 수발주유무 */
    @Schema(description = "수발주유무", example = "")
    private String purchaseYn; 

    /** 수급담당 2600 */
    @Schema(description = "수급담당 2600", example = "")
    private String buyerkeynm2600;

    /** 발주구분 2600 */
    @Schema(description = "발주구분 2600", example = "")
    private String purchasetype2600;

    /** 수급담당 2620 */
    @Schema(description = "수급담당 2620", example = "")
    private String buyerkeynm2620;

    /** 발주구분 2620 */
    @Schema(description = "발주구분 2620", example = "")
    private String purchasetype2620;

    /** 수급담당 2630 */
    @Schema(description = "수급담당 2630", example = "")
    private String buyerkeynm2630;

    /** 발주구분 2630 */
    @Schema(description = "발주구분 2630", example = "")
    private String purchasetype2630;

    /** 수급담당 2650 */
    @Schema(description = "수급담당 2650", example = "")
    private String buyerkeynm2650;

    /** 발주구분 2650 */
    @Schema(description = "발주구분 2650", example = "")
    private String purchasetype2650;

    /** 수급담당 2660 */
    @Schema(description = "수급담당 2660", example = "")
    private String buyerkeynm2660;

    /** 발주구분 2660 */
    @Schema(description = "발주구분 2660", example = "")
    private String purchasetype2660;

    /** 수급담당 2260 */
    @Schema(description = "수급담당 2260", example = "")
    private String buyerkeynm2260;

    /** 발주구분 2260 */
    @Schema(description = "발주구분 2260", example = "")
    private String purchasetype2260;

    /** 수급담당 2250 */
    @Schema(description = "수급담당 2250", example = "")
    private String buyerkeynm2250;

    /** 발주구분 2250 */
    @Schema(description = "발주구분 2250", example = "")
    private String purchasetype2250;

    /** 수급담당 2230 */
    @Schema(description = "수급담당 2230", example = "")
    private String buyerkeynm2230;

    /** 발주구분 2230 */
    @Schema(description = "발주구분 2230", example = "")
    private String purchasetype2230;

    /** 수급담당 2270 */
    @Schema(description = "수급담당 2270", example = "")
    private String buyerkeynm2270;

    /** 발주구분 2270 */
    @Schema(description = "발주구분 2270", example = "")
    private String purchasetype2270;

    /** 수급담당 2041 */
    @Schema(description = "수급담당 2041", example = "")
    private String buyerkeynm2041;

    /** 발주구분 2041 */
    @Schema(description = "발주구분 2041", example = "")
    private String purchasetype2041;

    /** 수급담당 1000 */
    @Schema(description = "수급담당 1000", example = "")
    private String buyerkeynm1000;

    /** 발주구분 1000 */
    @Schema(description = "발주구분 1000", example = "")
    private String purchasetype1000;

    /** 수급담당 2900 */
    @Schema(description = "수급담당 2900", example = "")
    private String buyerkeynm2900;

    /** 발주구분 2900 */
    @Schema(description = "발주구분 2900", example = "")
    private String purchasetype2900;

    /** 일평균 */
    @Schema(description = "일평균", example = "")
    private String stockavgday;

    /** 보유일 */
    @Schema(description = "보유일", example = "")
    private String stockday; 

    /** 재고단위 */
    @Schema(description = "재고단위", example = "")
    private String stockuom;
    
    /** 총 이체 입고량 */
    @Schema(description = "총 이체 입고량", example = "")
    private String stoInQty;
    
    /** 총 이체 출고량 */
    @Schema(description = "총 이체 출고량", example = "")
    private String stoOutQty;
    
    /** 재고 회전일 */
    @Schema(description = "재고 회전일", example = "")
    private String turnoverday;

    /** 전체 재고 합계 */
    @Schema(description = "전체 재고 합계", example = "")
    private String qtyTotal; 

    /** FW센터 재고 합계 */
    @Schema(description = "FW센터 재고 합계", example = "")
    private String qtyTotalFw; 

    /** 재고 2600 */
    @Schema(description = "재고 2600", example = "")
    private String qty2600; 

    /** 재고 2620 */
    @Schema(description = "재고 2620", example = "")
    private String qty2620; 

    /** 재고 2630 */
    @Schema(description = "재고 2630", example = "")
    private String qty2630; 

    /** 재고 2650 */
    @Schema(description = "재고 2650", example = "")
    private String qty2650; 

    /** 재고 2660 */
    @Schema(description = "재고 2660", example = "")
    private String qty2660; 

    /** 재고 2260 */
    @Schema(description = "재고 2260", example = "")
    private String qty2260; 

    /** 재고 2250 */
    @Schema(description = "재고 2250", example = "")
    private String qty2250; 

    /** 재고 2230 */
    @Schema(description = "재고 2230", example = "")
    private String qty2230; 

    /** 재고 2270 */
    @Schema(description = "재고 2270", example = "")
    private String qty2270; 

    /** 재고 2041 */
    @Schema(description = "재고 2041", example = "")
    private String qty2041; 

    /** 재고 1000 */
    @Schema(description = "재고 1000", example = "")
    private String qty1000; 

    /** 재고 2930 */
    @Schema(description = "재고 2930", example = "")
    private String qty2930; 

    /** 전체 PLT 합계 */
    @Schema(description = "전체 PLT 합계", example = "")
    private String boxperpltQtyTotal; 

    /** FW센터 PLT 합계 */
    @Schema(description = "FW센터 PLT 합계", example = "")
    private String boxperpltQtyTotalFw; 

    /** PLT 2600 */
    @Schema(description = "PLT 2600", example = "")
    private String plt2600; 

    /** PLT 2620 */
    @Schema(description = "PLT 2620", example = "")
    private String plt2620; 

    /** PLT 2630 */
    @Schema(description = "PLT 2630", example = "")
    private String plt2630; 

    /** PLT 2650 */
    @Schema(description = "PLT 2650", example = "")
    private String plt2650; 

    /** PLT 2660 */
    @Schema(description = "PLT 2660", example = "")
    private String plt2660; 

    /** PLT 2260 */
    @Schema(description = "PLT 2260", example = "")
    private String plt2260; 

    /** PLT 2250 */
    @Schema(description = "PLT 2250", example = "")
    private String plt2250; 

    /** PLT 2230 */
    @Schema(description = "PLT 2230", example = "")
    private String plt2230; 

    /** PLT 2270 */
    @Schema(description = "PLT 2270", example = "")
    private String plt2270; 

    /** PLT 2041 */
    @Schema(description = "PLT 2041", example = "")
    private String plt2041; 

    /** PLT 2930 */
    @Schema(description = "PLT 2930", example = "")
    private String plt2930; 

    /** PLT 1000 */
    @Schema(description = "PLT 1000", example = "")
    private String plt1000; 

    /** ROUTE 2600 */
    @Schema(description = "ROUTE 2600", example = "")
    private String route2600;

    /** ROUTE 2620 */
    @Schema(description = "ROUTE 2620", example = "")
    private String route2620;

    /** ROUTE 2630 */
    @Schema(description = "ROUTE 2630", example = "")
    private String route2630;

    /** ROUTE 2650 */
    @Schema(description = "ROUTE 2650", example = "")
    private String route2650;

    /** ROUTE 2660 */
    @Schema(description = "ROUTE 2660", example = "")
    private String route2660;

    /** ROUTE 2260 */
    @Schema(description = "ROUTE 2260", example = "")
    private String route2260;

    /** ROUTE 2230 */
    @Schema(description = "ROUTE 2230", example = "")
    private String route2230;

    /** ROUTE 2041 */
    @Schema(description = "ROUTE 2041", example = "")
    private String route2041;

    /** ROUTE 1000 */
    @Schema(description = "ROUTE 1000", example = "")
    private String route1000;

    /** D-1 전월출고중량 합계 */
    @Schema(description = "D-1 전월출고중량 합계", example = "")
    private String shipqty1wTotal;

    /** D-1 전월출고중량 2600 */
    @Schema(description = "D-1 전월출고중량 2600", example = "")
    private String shipqty1w2600;

    /** D-1 전월출고중량 2620 */
    @Schema(description = "D-1 전월출고중량 2620", example = "")
    private String shipqty1w2620;

    /** D-1 전월출고중량 2630 */
    @Schema(description = "D-1 전월출고중량 2630", example = "")
    private String shipqty1w2630;

    /** D-1 전월출고중량 2650 */
    @Schema(description = "D-1 전월출고중량 2650", example = "")
    private String shipqty1w2650;

    /** D-1 전월출고중량 2660 */
    @Schema(description = "D-1 전월출고중량 2660", example = "")
    private String shipqty1w2660;

    /** D-1 전월출고중량 2260 */
    @Schema(description = "D-1 전월출고중량 2260", example = "")
    private String shipqty1w2260;

    /** D-1 전월출고중량 2250 */
    @Schema(description = "D-1 전월출고중량 2250", example = "")
    private String shipqty1w2250;

    /** D-1 전월출고중량 2230 */
    @Schema(description = "D-1 전월출고중량 2230", example = "")
    private String shipqty1w2230;

    /** D-1 전월출고중량 2270 */
    @Schema(description = "D-1 전월출고중량 2270", example = "")
    private String shipqty1w2270;

    /** D-1 전월출고중량 2041 */
    @Schema(description = "D-1 전월출고중량 2041", example = "")
    private String shipqty1w2041;

    /** D-1 전월출고중량 1000 */
    @Schema(description = "D-1 전월출고중량 1000", example = "")
    private String shipqty1w1000;

    /** D-2 전월출고중량 합계 */
    @Schema(description = "D-2 전월출고중량 합계", example = "")
    private String shipqty2wTotal;

    /** D-2 전월출고중량 2600 */
    @Schema(description = "D-2 전월출고중량 2600", example = "")
    private String shipqty2w2600;

    /** D-2 전월출고중량 2620 */
    @Schema(description = "D-2 전월출고중량 2620", example = "")
    private String shipqty2w2620;

    /** D-2 전월출고중량 2630 */
    @Schema(description = "D-2 전월출고중량 2630", example = "")
    private String shipqty2w2630;

    /** D-2 전월출고중량 2650 */
    @Schema(description = "D-2 전월출고중량 2650", example = "")
    private String shipqty2w2650;

    /** D-2 전월출고중량 2660 */
    @Schema(description = "D-2 전월출고중량 2660", example = "")
    private String shipqty2w2660;

    /** D-2 전월출고중량 2260 */
    @Schema(description = "D-2 전월출고중량 2260", example = "")
    private String shipqty2w2260;

    /** D-2 전월출고중량 2250 */
    @Schema(description = "D-2 전월출고중량 2250", example = "")
    private String shipqty2w2250;

    /** D-2 전월출고중량 2230 */
    @Schema(description = "D-2 전월출고중량 2230", example = "")
    private String shipqty2w2230;

    /** D-2 전월출고중량 2270 */
    @Schema(description = "D-2 전월출고중량 2270", example = "")
    private String shipqty2w2270;

    /** D-2 전월출고중량 2041 */
    @Schema(description = "D-2 전월출고중량 2041", example = "")
    private String shipqty2w2041;

    /** D-2 전월출고중량 1000 */
    @Schema(description = "D-2 전월출고중량 1000", example = "")
    private String shipqty2w1000;

    /** D-3 전월출고중량 합계 */
    @Schema(description = "D-3 전월출고중량 합계", example = "")
    private String shipqty3wTotal;

    /** D-3 전월출고중량 2600 */
    @Schema(description = "D-3 전월출고중량 2600", example = "")
    private String shipqty3w2600;

    /** D-3 전월출고중량 2620 */
    @Schema(description = "D-3 전월출고중량 2620", example = "")
    private String shipqty3w2620;

    /** D-3 전월출고중량 2630 */
    @Schema(description = "D-3 전월출고중량 2630", example = "")
    private String shipqty3w2630;

    /** D-3 전월출고중량 2650 */
    @Schema(description = "D-3 전월출고중량 2650", example = "")
    private String shipqty3w2650;

    /** D-3 전월출고중량 2660 */
    @Schema(description = "D-3 전월출고중량 2660", example = "")
    private String shipqty3w2660; 

    /** D-3 전월출고중량 2260 */
    @Schema(description = "D-3 전월출고중량 2260", example = "")
    private String shipqty3w2260; 

    /** D-3 전월출고중량 2250 */
    @Schema(description = "D-3 전월출고중량 2250", example = "")
    private String shipqty3w2250; 

    /** D-3 전월출고중량 2230 */
    @Schema(description = "D-3 전월출고중량 2230", example = "")
    private String shipqty3w2230; 

    /** D-3 전월출고중량 2270 */
    @Schema(description = "D-3 전월출고중량 2270", example = "")
    private String shipqty3w2270; 

    /** D-3 전월출고중량 2041 */
    @Schema(description = "D-3 전월출고중량 2041", example = "")
    private String shipqty3w2041; 

    /** D-3 전월출고중량 1000 */
    @Schema(description = "D-3 전월출고중량 1000", example = "")
    private String shipqty3w1000; 
	
		
}