package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 이력STO출고처리- 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력STO출고처리- 결과")
public class WdSerialOrderSTOResPickingDto extends CommonDto{
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * pickListTitle
     */
    @Schema(description = "pickListTitle", example = "2025년09월29일  CJ대한통운출고(    ) 피킹리스트")
    private String pickListTitle;
    /**
     * pickBatchNo
     */
    @Schema(description = "pickBatchNo", example = "PB001 P001")
    private String pickBatchNo;
    /**
     * slipdt
     */
    @Schema(description = "slipdt", example = "2025-09-29")
    private String slipdt;
    /**
     * courier
     */
    @Schema(description = "courier", example = "CJ대한통운")
    private String courier;
    /**
     * storagetype
     */
    @Schema(description = "storagetype", example = "STORA01")
    private String storagetype;
    /**
     * pickNo
     */
    @Schema(description = "pickNo", example = "P001")
    private String pickNo;
    /**
     * zone
     */
    @Schema(description = "zone", example = "Z01")
    private String zone;
    /**
     * loc
     */
    @Schema(description = "loc", example = "LOC01")
    private String loc;
    /**
     * sku
     */
    @Schema(description = "sku", example = "SKU001")
    private String sku;
    /**
     * skuname
     */
    @Schema(description = "skuname", example = "백설 하얀설탕/미국")
    private String skuname;
    /**
     * baseuom
     */
    @Schema(description = "baseuom", example = "BOX")
    private String baseuom;
    /**
     * lottable01
     */
    @Schema(description = "lottable01", example = "LOT01")
    private String lottable01;
    /**
     * processqty
     */
    @Schema(description = "processqty", example = "10")
    private BigDecimal processqty;
    /**
     * toteaqty
     */
    @Schema(description = "toteaqty", example = "100")
    private BigDecimal toteaqty;
    /**
     * qtyperbox
     */
    @Schema(description = "qtyperbox", example = "10")
    private BigDecimal qtyperbox;
    /**
     * kgqty
     */
    @Schema(description = "kgqty", example = "50.5")
    private BigDecimal kgqty;
    /**
     * boxqty
     */
    @Schema(description = "boxqty", example = "10")
    private BigDecimal boxqty;
    /**
     * eaqty
     */
    @Schema(description = "eaqty", example = "5")
    private BigDecimal eaqty;
    /**
     * serialno
     */
    @Schema(description = "serialno", example = "SN12345")
    private String serialno;
    /**
     * stockid
     */
    @Schema(description = "stockid", example = "STK001")
    private String stockid;
    /**
     * 출력일시
     */
    @Schema(description = "출력일시", example = "2025-09-29 13:29:17")
    private String printdt;
    /**
     * dccode
     */
    @Schema(description = "dccode", example = "DC01")
    private String dccode;
    /**
     * docno
     */
    @Schema(description = "docno", example = "DOC001")
    private String docno;
}
