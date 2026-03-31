package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.05
 * @description : 입고 확정내역 조회 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 
 * </pre>
 */
@Data
@Schema(description = "입고 확정내역 조회 API") 
public class GwmsDp3PlApiResDto {

	/* SERIALKEY */
	@Schema(description = "SERIALKEY")
	private String serialkey;

	/* CUSTKEY */
	@Schema(description = "CUSTKEY")
	private String custkey;

	/* SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/* SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/* SLIPLINE */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/* DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/* DOCTYPE */
	@Schema(description = "DOCTYPE")
	private String doctype;

	/* DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/* DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/* POKEY */
	@Schema(description = "POKEY")
	private String pokey;

	/* POLINE */
	@Schema(description = "POLINE")
	private String poline;

	/* ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/* PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/* STORAGELOC */
	@Schema(description = "STORAGELOC")
	private String storageloc;

	/* IOTYPE */
	@Schema(description = "IOTYPE")
	private String iotype;

	/* SKU */
	@Schema(description = "SKU")
	private String sku;

	/* STORERORDERQTY */
	@Schema(description = "STORERORDERQTY")
	private String storerorderqty;

	/* STORERADJUSTQTY */
	@Schema(description = "STORERADJUSTQTY")
	private String storeradjustqty;

	/* STOREROPENQTY */
	@Schema(description = "STOREROPENQTY")
	private String storeropenqty;

	/* STORERCONFIRMQTY */
	@Schema(description = "STORERCONFIRMQTY")
	private String storerconfirmqty;

	/* STORERCANCELQTY */
	@Schema(description = "STORERCANCELQTY")
	private String storercancelqty;

	/* STORERUOM */
	@Schema(description = "STORERUOM")
	private String storeruom;

	/* BUNJA */
	@Schema(description = "BUNJA")
	private String bunja;

	/* BUNMO */
	@Schema(description = "BUNMO")
	private String bunmo;

	/* UOM */
	@Schema(description = "UOM")
	private String uom;

	/* ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/* ORDERADJUSTQTY */
	@Schema(description = "ORDERADJUSTQTY")
	private String orderadjustqty;

	/* OPENQTY */
	@Schema(description = "OPENQTY")
	private String openqty;

	/* OPENADJUSTQTY */
	@Schema(description = "OPENADJUSTQTY")
	private String openadjustqty;

	/* PROCESSQTY */
	@Schema(description = "PROCESSQTY")
	private String processqty;

	/* WORKQTY */
	@Schema(description = "WORKQTY")
	private String workqty;

	/* INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private String inspectqty;

	/* CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/* CONFIRMWEIGHT */
	@Schema(description = "CONFIRMWEIGHT")
	private String confirmweight;

	/* CANCELQTY */
	@Schema(description = "CANCELQTY")
	private String cancelqty;

	/* LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/* FACTORYPRICE */
	@Schema(description = "FACTORYPRICE")
	private String factoryprice;

	/* CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/* CONFIRMDATE */
	@Schema(description = "CONFIRMDATE")
	private String confirmdate;

	/* DELIVERYDATE */
	@Schema(description = "DELIVERYDATE")
	private String deliverydate;

	/* STATUS */
	@Schema(description = "STATUS")
	private String status;

	/* DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

	/* IF_ID */
	@Schema(description = "IF_ID")
	private String ifId;

	/* ADDDATE */
	@Schema(description = "ADDDATE")
	private String adddate;

	/* EDITDATE */
	@Schema(description = "EDITDATE")
	private String editdate;

	/* ADDWHO */
	@Schema(description = "ADDWHO")
	private String addwho;

	/* EDITWHO */
	@Schema(description = "EDITWHO")
	private String editwho;


}
