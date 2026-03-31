package cjfw.wms.ib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.24
 * @description : 센터별물동량 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터별물동량 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbStoWeightResExcelDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/** DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/** DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/** SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** SLIPLINE */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/** POTYPE */
	@Schema(description = "POTYPE")
	private String potype;

	/** POTYPENAME */
	@Schema(description = "POTYPENAME")
	private String potypename;

	/** ORGANIZENAME */
	@Schema(description = "ORGANIZENAME")
	private String organizename;

	/** ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/** ORDERTYPE_NAME */
	@Schema(description = "ORDERTYPE_NAME")
	private String ordertypeName;

	/** FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY")
	private String fromCustkey;

	/** FROM_CUSTNAME */
	@Schema(description = "FROM_CUSTNAME")
	private String fromCustname;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** COUNTRYOFORIGIN */
	@Schema(description = "COUNTRYOFORIGIN")
	private String countryoforigin;

	/** UOM */
	@Schema(description = "UOM")
	private String uom;

	/** BUNJA */
	@Schema(description = "BUNJA")
	private String bunja;

	/** BUNMO */
	@Schema(description = "BUNMO")
	private String bunmo;

	/** OPENQTY_EA */
	@Schema(description = "OPENQTY_EA")
	private String openqtyEa;

	/** OPENQTY_BOX */
	@Schema(description = "OPENQTY_BOX")
	private String openqtyBox;

	/** PLTQTY */
	@Schema(description = "PLTQTY")
	private String pltqty;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/** ORDERWEIGHT */
	@Schema(description = "ORDERWEIGHT")
	private String orderweight;

	/** INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private String inspectqty;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/** CONFIRMQTY_WD */
	@Schema(description = "CONFIRMQTY_WD")
	private String confirmqtyWd;

	/** STO_FLAG */
	@Schema(description = "STO_FLAG")
	private String stoFlag;

	/** SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private String shortageqty;

	/** SHORTAGETRANQTY */
	@Schema(description = "SHORTAGETRANQTY")
	private String shortagetranqty;

	/** TRANQTY */
	@Schema(description = "TRANQTY")
	private String tranqty;

	/** LOC */
	@Schema(description = "LOC")
	private String loc;

	/** TOLOC */
	@Schema(description = "TOLOC")
	private String toloc;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** DURATION_TERM */
	@Schema(description = "DURATION_TERM")
	private String durationTerm;

	/** COLORDESCR */
	@Schema(description = "COLORDESCR")
	private String colordescr;

	/** REFERENCE02 */
	@Schema(description = "REFERENCE02")
	private String reference02;

	/** REASONCODE */
	@Schema(description = "REASONCODE")
	private String reasoncode;

	/** REASONMSG */
	@Schema(description = "REASONMSG")
	private String reasonmsg;

	/** NEARDURATIONYN */
	@Schema(description = "NEARDURATIONYN")
	private String neardurationyn;

	/** STATUS */
	@Schema(description = "STATUS")
	private String status;

	/** STATUSNAME */
	@Schema(description = "STATUSNAME")
	private String statusname;

	/** QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/** LASTLOTTABLE01 */
	@Schema(description = "LASTLOTTABLE01")
	private String lastlottable01;

	/** STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/** CROSSLOC */
	@Schema(description = "CROSSLOC")
	private String crossloc;

	/** PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/** PLANT_DESCR */
	@Schema(description = "PLANT_DESCR")
	private String plantDescr;

	/** CHANNEL_NAME */
	@Schema(description = "CHANNEL_NAME")
	private String channelName;

	/** STORAGETYPENAME */
	@Schema(description = "STORAGETYPENAME")
	private String storagetypename;

	/** SERIALNO */
	@Schema(description = "SERIALNO")
	private String serialno;

	/** CONVSERIALNO */
	@Schema(description = "CONVSERIALNO")
	private String convserialno;

	/** FACTORYNAME */
	@Schema(description = "FACTORYNAME")
	private String factoryname;

	/** CONTRACTCOMPANY */
	@Schema(description = "CONTRACTCOMPANY")
	private String contractcompany;

	/** CONTRACTCOMPANYNAME */
	@Schema(description = "CONTRACTCOMPANYNAME")
	private String contractcompanyname;

	/** CONTRACTTYPE */
	@Schema(description = "CONTRACTTYPE")
	private String contracttype;

	/** BUTCHERYDT */
	@Schema(description = "BUTCHERYDT")
	private String butcherydt;

	/** FROMVALIDDT */
	@Schema(description = "FROMVALIDDT")
	private String fromvaliddt;

	/** TOVALIDDT */
	@Schema(description = "TOVALIDDT")
	private String tovaliddt;

	/** BARCODE */
	@Schema(description = "BARCODE")
	private String barcode;

	/** SERIALORDERQTY */
	@Schema(description = "SERIALORDERQTY")
	private String serialorderqty;

	/** SERIALINSPECTQTY */
	@Schema(description = "SERIALINSPECTQTY")
	private String serialinspectqty;

	/** SERIALSCANWEIGHT */
	@Schema(description = "SERIALSCANWEIGHT")
	private String serialscanweight;

	/** CHECKFLAG */
	@Schema(description = "CHECKFLAG")
	private String checkflag;

	/** LINE01 */
	@Schema(description = "LINE01")
	private String line01;

	/** SERIALYN */
	@Schema(description = "SERIALYN")
	private String serialyn;

	/** FONTCOLOR */
	@Schema(description = "FONTCOLOR")
	private String fontcolor;
}
