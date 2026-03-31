package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.05
 * @description : 재고 현황 조회 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 
 * </pre>
 */
@Data
@Schema(description = "재고 현황 조회 API") 
public class GwmsSt3PlApiResDto {

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/* STOCKTYPE */
	@Schema(description = "STOCKTYPE")
	private String stocktype;

	/* STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/* ZONE */
	@Schema(description = "ZONE")
	private String zone;

	/* PLT_FLG */
	@Schema(description = "PLT_FLG")
	private String pltFlg;

	/* LOC */
	@Schema(description = "LOC")
	private String loc;

	/* SKU */
	@Schema(description = "SKU")
	private String sku;

	/* SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/* UOM */
	@Schema(description = "UOM")
	private String uom;

	/* QTY */
	@Schema(description = "QTY")
	private String qty;

	/* QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/* OPENQTY */
	@Schema(description = "OPENQTY")
	private String openqty;

	/* QTYALLOCATED */
	@Schema(description = "QTYALLOCATED")
	private String qtyallocated;

	/* QTYPICKED */
	@Schema(description = "QTYPICKED")
	private String qtypicked;

	/* NEARDURATIONYN */
	@Schema(description = "NEARDURATIONYN")
	private String neardurationyn;

	/* LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/* DURATION_TERM */
	@Schema(description = "DURATION_TERM")
	private String durationTerm;

	/* SERIALNO */
	@Schema(description = "SERIALNO")
	private String serialno;

	/* STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/* QTY_1 */
	@Schema(description = "QTY_1")
	private String qty_1;

	/* QTY_2 */
	@Schema(description = "QTY_2")
	private String qty_2;

	/* UOM_1 */
	@Schema(description = "UOM_1")
	private String uom_1;

	/* UOM_2 */
	@Schema(description = "UOM_2")
	private String uom_2;

	/* STOCK_QTY_WEIGHT */
	@Schema(description = "STOCK_QTY_WEIGHT")
	private String stockQtyWeight;

	/* STOCK_OPENQTY_WEIGHT */
	@Schema(description = "STOCK_OPENQTY_WEIGHT")
	private String stockOpenqtyWeight;



}
