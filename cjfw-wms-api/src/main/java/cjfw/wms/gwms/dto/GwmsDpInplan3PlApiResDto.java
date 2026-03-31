package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.05
 * @description : 입고진행 현황 조회 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 
 * </pre>
 */
@Data
@Schema(description = "입고진행 현황 조회 API") 
public class GwmsDpInplan3PlApiResDto {

	/* DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/* DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/* SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/* FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY")
	private String fromCustkey;

	/* FROM_CUSTNAME */
	@Schema(description = "FROM_CUSTNAME")
	private String fromCustname;

	/* SKU */
	@Schema(description = "SKU")
	private String sku;

	/* SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/* CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/* CHANNEL_NAME */
	@Schema(description = "CHANNEL_NAME")
	private String channelName;

	/* STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/* STORAGETYPENAME */
	@Schema(description = "STORAGETYPENAME")
	private String storagetypename;

	/* ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/* SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private String shortageqty;

	/* ORDERADJUSTQTY */
	@Schema(description = "ORDERADJUSTQTY")
	private String orderadjustqty;

	/* OPENQTY */
	@Schema(description = "OPENQTY")
	private String openqty;

	/* INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private String inspectqty;

	/* CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/* UOM */
	@Schema(description = "UOM")
	private String uom;

	/* CONFIRMWEIGHT */
	@Schema(description = "CONFIRMWEIGHT")
	private String confirmweight;

	/* QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/* DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

	/* PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/* PLANT_DESCR */
	@Schema(description = "PLANT_DESCR")
	private String plantDescr;

	/* PURCHASE_QTY */
	@Schema(description = "PURCHASE_QTY")
	private String purchaseQty;

	/* STATUS */
	@Schema(description = "STATUS")
	private String status;


}
