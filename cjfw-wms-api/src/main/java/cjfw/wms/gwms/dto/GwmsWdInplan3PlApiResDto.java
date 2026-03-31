package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.12.05
 * @description : 입출고진행 현황 조회 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.05 KimDongHan (liop0123@cj.net) 생성 
 * </pre>
 */
@Data
@Schema(description = "출고진행 현황 조회 API") 
public class GwmsWdInplan3PlApiResDto {

	/* DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/* STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/* ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

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

	/* DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/* DOCTYPE */
	@Schema(description = "DOCTYPE")
	private String doctype;

	/* DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/* ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/* SOTYPE */
	@Schema(description = "SOTYPE")
	private String sotype;

	/* TO_CUSTKEY */
	@Schema(description = "TO_CUSTKEY")
	private String toCustkey;

	/* TO_CUSTNAME */
	@Schema(description = "TO_CUSTNAME")
	private String toCustname;

	/* MNGPLC_ID */
	@Schema(description = "MNGPLC_ID")
	private String mngplcId;

	/* MNGPLC_NAME */
	@Schema(description = "MNGPLC_NAME")
	private String mngplcName;

	/* STATUS */
	@Schema(description = "STATUS")
	private String status;

	/* SKU */
	@Schema(description = "SKU")
	private String sku;

	/* SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/* CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/* STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/* SERIALNO */
	@Schema(description = "SERIALNO")
	private String serialno;

	/* ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/* ORDERWEIGHT */
	@Schema(description = "ORDERWEIGHT")
	private String orderweight;

	/* CANCELQTY */
	@Schema(description = "CANCELQTY")
	private String cancelqty;

	/* CONFIRMWEIGHT */
	@Schema(description = "CONFIRMWEIGHT")
	private String confirmweight;

	/* UOM */
	@Schema(description = "UOM")
	private String uom;

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

	/* CLOSEYN */
	@Schema(description = "CLOSEYN")
	private String closeyn;

	/* DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

	/* ADDDATE */
	@Schema(description = "ADDDATE")
	private String adddate;

	/* QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/* TO_VATADDRESS1 */
	@Schema(description = "TO_VATADDRESS1")
	private String toVataddress1;

	/* TO_ZIPCODE */
	@Schema(description = "TO_ZIPCODE")
	private String toZipcode;

}
