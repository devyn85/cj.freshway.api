package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.11.05
 * @description : KIT처리 조회결과 DTO [이체대상TAB]
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05   고혜미(laksjd0606@cj.net)  생성
 */
@Data
@Schema(description = "KIT처리 결과 DTO")
public class StMKitResT1Dto extends CommonProcedureDto {
    
    /** 순번 */
    @Schema(description = "순번")
    private Long rownum;

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    
    /** ZONE */
    @Schema(description = "ZONE")
    private String zone;
    
    /** 계획일자 */
    @Schema(description = "계획일자")
    private String planDt;
    
    /** KIT상품코드 */
    @Schema(description = "KIT상품코드")
    private String kitSku;
    
    /** KIT상품명 */
    @Schema(description = "KIT상품명")
    private String kitNm;
    
    /** KIT 소비일자 */
    @Schema(description = "KIT 소비일자")
    private String minExpiredt;
    
    /** KIT 계획수량 */
    @Schema(description = "KIT 계획수량")
    private BigDecimal openqty;
    
    /** KIT 생산수량 */
    @Schema(description = "KIT 생산수량")
    private BigDecimal confirmqty;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String skuNm;
    
    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgradeNm;
    
    /** 재고속성 */
    @Schema(description = "재고속성")
    private String stockgrade;
    
    /** 재고구분ID */
    @Schema(description = "재고구분ID")
    private String stockid;
    
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;
    
    /** 시리얼넘버 */
    @Schema(description = "시리얼넘버")
    private String serialno;
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;
    
    /** 현재재고수량 */
    @Schema(description = "현재재고수량")
    private BigDecimal stockqtyTotal;
    
    /** 가용재고수량 */
    @Schema(description = "가용재고수량")
    private BigDecimal stockqty;

    /** 요청량 */
    @Schema(description = "요청량")
    private BigDecimal reqQty;
    
    /** KIT구성수량 */
    @Schema(description = "KIT구성수량")
    private BigDecimal qty;
    
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
    
    /** KITCALLQTY */
    @Schema(description = "KITCALLQTY")
    private String kitcallqty;
    
    /** MIN_DURATION */
    @Schema(description = "MIN_DURATION")
    private String minDuration;
    
    /** AVC_COMMAND */
    @Schema(description = "AVC_COMMAND")
    private String avcCommand;
    
    /** AVC_REQUESTMSG */
    @Schema(description = "AVC_REQUESTMSG")
    private String avcRequestmsg;
    
    /** COSTCD */
    @Schema(description = "COSTCD")
    private String costcd;
    
    /** COSTNAME */
    @Schema(description = "COSTNAME")
    private String costcdname;
    
    /** CUSTKEY */
    @Schema(description = "CUSTKEY")
    private String custkey;
    
    /** CUSTNAME */
    @Schema(description = "CUSTNAME")
    private String custname;
    
    /** 세로 병합 */
    @Schema(description = "세로 병합")
    private String rowDist;
    
    /** MIXBOXKEY */
    @Schema(description = "MIXBOXKEY")
    private String mixboxkey;
    
    /** 단위 */
    @Schema(description = "단위")
    private String uom;
    
    /** 단위 */
    @Schema(description = "단위")
    private String skuUom;
    
    /** 단위 */
    @Schema(description = "단위")
    private String kitUom;
    
    /** LOT */
    @Schema(description = "LOT")
    private String lot;
    
    /** STOCKTYPE */
    @Schema(description = "STOCKTYPE")
    private String stocktype;
    
    /** QTYALLOCATED */
    @Schema(description = "QTYALLOCATED")
    private BigDecimal qtyallocated;
    
    /** QTYPICKED */
    @Schema(description = "QTYPICKED")
    private BigDecimal qtypicked;
   
    /** CALLQTY */
    @Schema(description = "CALLQTY")
    private BigDecimal callqty;
    
    /** 소비기한 */
    @Schema(description = "소비기한")
    private String duration;
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;
    
    /** DISUSETYPE */
    @Schema(description = "DISUSETYPE")
    private String disusetype;
    
    /** IMPUTETYPE */
    @Schema(description = "IMPUTETYPE")
    private String imputetype;
    
    /** PROCESSMAIN */
    @Schema(description = "PROCESSMAIN")
    private String processmain;
    
    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docno;
    
    /** 상태 */
    @Schema(description = "상태")
    private String status;
    
    /** 상태 */
    @Schema(description = "상태")
    private String processflag;
    
    /** 상태 */
    @Schema(description = "상태")
    private String processmsg;
    
    /** 상태 */
    @Schema(description = "상태")
    private String processflagitem;
    
    /** 상태 */
    @Schema(description = "상태")
    private String processmsgitem;
    
    /** 결재문서번호 */
    @Schema(description = "결재문서번호")
    private String approvalreqno;
    
    /** 결재상태 */
    @Schema(description = "결재상태")
    private String approvalstatus;
    
    /** 결재상태 */
    @Schema(description = "결재상태")
    private String approvalstatusname;
    
    
    /**********************************************/
    /* UP_SKUNAME */
    @Schema(description = "UP_SKUNAME")
    private String upSkuname;

    /* UP_SKU */
    @Schema(description = "UP_SKU")
    private String upSku;

    /* UP_UOM */
    @Schema(description = "UP_UOM")
    private String upUom;

    /* DOWN_SKU */
    @Schema(description = "DOWN_SKU")
    private String downSku;

    /* DOWN_SKUNAME */
    @Schema(description = "DOWN_SKUNAME")
    private String downSkuname;

    /* QTYPERBOX */
    @Schema(description = "QTYPERBOX")
    private String qtyperbox;

    /* BOX_QTY */
    @Schema(description = "BOX_QTY")
    private String boxQty;

    /* BOX_UOM */
    @Schema(description = "BOX_UOM")
    private String boxUom;

    /* EA_QTY */
    @Schema(description = "EA_QTY")
    private String eaQty;

    /* EA_UOM */
    @Schema(description = "EA_UOM")
    private String eaUom;

    /* USEBYDATE */
    @Schema(description = "USEBYDATE")
    private String usebydate;
    
    /* FACTORY_DISASSEM_FLAG */
    @Schema(description = "FACTORY_DISASSEM_FLAG")
    private String factoryDisassemFlag;
    
    /** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * STORERKEY
     */
    @Schema(description = "STORERKEY", example = "CJ01")
    private String storerkey;
	/**
     * ORGANIZE
     */
    @Schema(description = "ORGANIZE", example = "ORG01")
    private String organize;
	/**
     * AREA
     */
    @Schema(description = "AREA", example = "AREA01")
    private String area;
	/**
     * DOCDT
     */
    @Schema(description = "DOCDT", example = "2025-01-01")
    private String docdt;
	/**
     * DOCLINE
     */
    @Schema(description = "DOCLINE", example = "00001")
    private String docline;
	/**
     * SLIPDT
     */
    @Schema(description = "SLIPDT", example = "2025-01-01")
    private String slipdt;
	/**
     * SLIPNO
     */
    @Schema(description = "SLIPNO", example = "SLIP001")
    private String slipno;
	/**
     * SLIPLINE
     */
    @Schema(description = "SLIPLINE", example = "00001")
    private String slipline;
	/**
     * SLIPTYPE
     */
    @Schema(description = "SLIPTYPE", example = "ST01")
    private String sliptype;
	/**
     * KIT_LOC
     */
    @Schema(description = "KIT_LOC", example = "K-01-01")
    private String kitLoc;
	/**
     * KIT_LOT
     */
    @Schema(description = "KIT_LOT", example = "KLOT01")
    private String kitLot;
	/**
     * KIT_STOCKGRADE
     */
    @Schema(description = "KIT_STOCKGRADE", example = "10")
    private String kitStockgrade;
	/**
     * KIT_STOCKID
     */
    @Schema(description = "KIT_STOCKID", example = "KSID01")
    private String kitStockid;
    /**
     * IOTYPE
     */
    @Schema(description = "IOTYPE", example = "")
    private String iotype;
    /**
     * ORDERTYPE
     */
    @Schema(description = "ORDERTYPE", example = "")
    private String ordertype;

    /** TRANQTY */
    @Schema(description = "TRANQTY")
    private BigDecimal tranqty;

}
