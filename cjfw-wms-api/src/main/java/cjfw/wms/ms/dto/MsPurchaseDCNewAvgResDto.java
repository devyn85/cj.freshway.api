package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : 수발주마스터 월평균 조회(목록) 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class MsPurchaseDCNewAvgResDto {
			
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

    /** FW센터 재고 평균 */
    @Schema(description = "FW센터 재고 평균", example = "")
    private String qtyAvg;

    /** 재고 01 */
    @Schema(description = "재고 01", example = "")
    private String qty01;

    /** 재고 02 */
    @Schema(description = "재고 02", example = "")
    private String qty02;

    /** 재고 03 */
    @Schema(description = "재고 03", example = "")
    private String qty03;

    /** 재고 04 */
    @Schema(description = "재고 04", example = "")
    private String qty04;

    /** 재고 05 */
    @Schema(description = "재고 05", example = "")
    private String qty05;

    /** 재고 06 */
    @Schema(description = "재고 06", example = "")
    private String qty06;

    /** 재고 07 */
    @Schema(description = "재고 07", example = "")
    private String qty07;

    /** 재고 08 */
    @Schema(description = "재고 08", example = "")
    private String qty08;

    /** 재고 09 */
    @Schema(description = "재고 09", example = "")
    private String qty09;

    /** 재고 10 */
    @Schema(description = "재고 10", example = "")
    private String qty10;

    /** 재고 11 */
    @Schema(description = "재고 11", example = "")
    private String qty11;

    /** 재고 12 */
    @Schema(description = "재고 12", example = "")
    private String qty12;

    /** 재고 13 */
    @Schema(description = "재고 13", example = "")
    private String qty13;

    /** 재고 14 */
    @Schema(description = "재고 14", example = "")
    private String qty14;

    /** 재고 15 */
    @Schema(description = "재고 15", example = "")
    private String qty15;

    /** 재고 16 */
    @Schema(description = "재고 16", example = "")
    private String qty16;

    /** 재고 17 */
    @Schema(description = "재고 17", example = "")
    private String qty17;

    /** 재고 18 */
    @Schema(description = "재고 18", example = "")
    private String qty18;

    /** 재고 19 */
    @Schema(description = "재고 19", example = "")
    private String qty19;

    /** 재고 20 */
    @Schema(description = "재고 20", example = "")
    private String qty20;

    /** 재고 21 */
    @Schema(description = "재고 21", example = "")
    private String qty21;

    /** 재고 22 */
    @Schema(description = "재고 22", example = "")
    private String qty22;

    /** 재고 23 */
    @Schema(description = "재고 23", example = "")
    private String qty23;

    /** 재고 24 */
    @Schema(description = "재고 24", example = "")
    private String qty24;

    /** 재고 25 */
    @Schema(description = "재고 25", example = "")
    private String qty25;

    /** 재고 26 */
    @Schema(description = "재고 26", example = "")
    private String qty26;

    /** 재고 27 */
    @Schema(description = "재고 27", example = "")
    private String qty27;

    /** 재고 28 */
    @Schema(description = "재고 28", example = "")
    private String qty28;

    /** 재고 29 */
    @Schema(description = "재고 29", example = "")
    private String qty29;

    /** 재고 30 */
    @Schema(description = "재고 30", example = "")
    private String qty30;

    /** 재고 31 */
    @Schema(description = "재고 31", example = "")
    private String qty31;

    /** PLT 총합 */
    @Schema(description = "PLT 총합", example = "")
    private String pltTotal;

    /** PLT 평균 */
    @Schema(description = "PLT 평균", example = "")
    private String pltAvg;

    /** PLT 01 */
    @Schema(description = "PLT 01", example = "")
    private String plt01;

    /** PLT 02 */
    @Schema(description = "PLT 02", example = "")
    private String plt02;

    /** PLT 03 */
    @Schema(description = "PLT 03", example = "")
    private String plt03;

    /** PLT 04 */
    @Schema(description = "PLT 04", example = "")
    private String plt04;

    /** PLT 05 */
    @Schema(description = "PLT 05", example = "")
    private String plt05;

    /** PLT 06 */
    @Schema(description = "PLT 06", example = "")
    private String plt06;

    /** PLT 07 */
    @Schema(description = "PLT 07", example = "")
    private String plt07;

    /** PLT 08 */
    @Schema(description = "PLT 08", example = "")
    private String plt08;

    /** PLT 09 */
    @Schema(description = "PLT 09", example = "")
    private String plt09;

    /** PLT 10 */
    @Schema(description = "PLT 10", example = "")
    private String plt10;

    /** PLT 11 */
    @Schema(description = "PLT 11", example = "")
    private String plt11;

    /** PLT 12 */
    @Schema(description = "PLT 12", example = "")
    private String plt12;

    /** PLT 13 */
    @Schema(description = "PLT 13", example = "")
    private String plt13;

    /** PLT 14 */
    @Schema(description = "PLT 14", example = "")
    private String plt14;

    /** PLT 15 */
    @Schema(description = "PLT 15", example = "")
    private String plt15;

    /** PLT 16 */
    @Schema(description = "PLT 16", example = "")
    private String plt16;

    /** PLT 17 */
    @Schema(description = "PLT 17", example = "")
    private String plt17;

    /** PLT 18 */
    @Schema(description = "PLT 18", example = "")
    private String plt18;

    /** PLT 19 */
    @Schema(description = "PLT 19", example = "")
    private String plt19;

    /** PLT 20 */
    @Schema(description = "PLT 20", example = "")
    private String plt20;

    /** PLT 21 */
    @Schema(description = "PLT 21", example = "")
    private String plt21;

    /** PLT 22 */
    @Schema(description = "PLT 22", example = "")
    private String plt22;

    /** PLT 23 */
    @Schema(description = "PLT 23", example = "")
    private String plt23;

    /** PLT 24 */
    @Schema(description = "PLT 24", example = "")
    private String plt24;

    /** PLT 25 */
    @Schema(description = "PLT 25", example = "")
    private String plt25;

    /** PLT 26 */
    @Schema(description = "PLT 26", example = "")
    private String plt26;

    /** PLT 27 */
    @Schema(description = "PLT 27", example = "")
    private String plt27;

    /** PLT 28 */
    @Schema(description = "PLT 28", example = "")
    private String plt28;

    /** PLT 29 */
    @Schema(description = "PLT 29", example = "")
    private String plt29;

    /** PLT 30 */
    @Schema(description = "PLT 30", example = "")
    private String plt30;

    /** PLT 31 */
    @Schema(description = "PLT 31", example = "")
    private String plt31;
	
	/** 최초등록시간 */
	@Schema(description = "최초등록시간", example = "")
	private String adddate;

	/** 최종변경시간 */
	@Schema(description = "최종변경시간", example = "")
	private String editdate;

	/** 최초등록자 */
	@Schema(description = "최초등록자", example = "")
	private String addwho;

	/** 최종변경자 */
	@Schema(description = "최종변경자", example = "")
	private String editwho;
	
}