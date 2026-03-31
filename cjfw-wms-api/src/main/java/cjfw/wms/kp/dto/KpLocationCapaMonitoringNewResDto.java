package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.11.17
 * @description : 센터CAPA현황(NEW) 응답 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Data
@Schema(description = "센터CAPA현황(NEW) 응답 DTO")
public class KpLocationCapaMonitoringNewResDto {
    @Schema(description = "물류센터 코드")
    private String dccode;

    @Schema(description = "물류센터 명")
    private String dcname;

    @Schema(description = "로케이션")
    private String loc;

    @Schema(description = "랙")
    private String rack;

    @Schema(description = "랙라인")
    private String rackline;

    @Schema(description = "랙컬럼")
    private String rackcolumn;

    @Schema(description = "수량")
    private BigDecimal qty;

    @Schema(description = "상태")
    private String status;

    
    //
    private String PK_RT_P_TOT_CNT;
    private String PK_RT_S_TOT_CNT;
    private String PK_CD_P_TOT_CNT;
    private String PK_CD_S_TOT_CNT;
    private String PK_FZ_P_TOT_CNT;
    private String PK_FZ_S_TOT_CNT;
    private String KP_RT_P_TOT_CNT;
    private String KP_RT_S_TOT_CNT;
    private String KP_CD_P_TOT_CNT;
    private String KP_CD_S_TOT_CNT;
    private String KP_FZ_P_TOT_CNT;
    private String KP_FZ_S_TOT_CNT;
    private String PK_RT_P_STOCK_CNT;
    private String PK_RT_S_STOCK_CNT;
    private String PK_CD_P_STOCK_CNT;
    private String PK_CD_S_STOCK_CNT;
    private String PK_FZ_P_STOCK_CNT;
    private String PK_FZ_S_STOCK_CNT;
    private String KP_RT_P_STOCK_CNT;
    private String KP_RT_S_STOCK_CNT;
    private String KP_CD_P_STOCK_CNT;
    private String KP_CD_S_STOCK_CNT;
    private String KP_FZ_P_STOCK_CNT;
    private String KP_FZ_S_STOCK_CNT;
    private String PK_RT_P_FREE_CNT;
    private String PK_RT_S_FREE_CNT;
    private String PK_CD_P_FREE_CNT;
    private String PK_CD_S_FREE_CNT;
    private String PK_FZ_P_FREE_CNT;
    private String PK_FZ_S_FREE_CNT;
    private String KP_RT_P_FREE_CNT;
    private String KP_RT_S_FREE_CNT;
    private String KP_CD_P_FREE_CNT;
    private String KP_CD_S_FREE_CNT;
    private String KP_FZ_P_FREE_CNT;
    private String KP_FZ_S_FREE_CNT;
    
//    Tab2
    @Schema(description = "총 LOC 수")
    private String totLoc;
    
    @Schema(description = "빈 LOC 수")
    private String empLoc;
    
   
    private String storerkey;
    private String organize;
    private String area;
    private String areaname;
    private String sku;
    private String description;
    private String stylecode;
    private String colorcode;
    private String sizecode;
    private String skugroup;
    private String barcode1;
    private String barcode2;
    private String barcode3;
    private String uom;
    private String lot;
    private String lottable01;
    private String lottable02;
    private String lottable03;
    private String lottable04;
    private String lottable05;
    private String stockid;
    private String stockgrade;
    private BigDecimal qtyallocated;
    private BigDecimal qtypicked;
    private BigDecimal openqty;
}
