package cjfw.wms.rt.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.23
 * @description : 반품판정처리 Request DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품판정처리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtQCConfirmResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** AREA */
	@Schema(description = "AREA")
	private String area;

	/** DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/** DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/** DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/** DOCTYPE */
	@Schema(description = "DOCTYPE")
	private String doctype;

	/** SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** SLIPLINE */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/** SLIPTYPE */
	@Schema(description = "SLIPTYPE")
	private String sliptype;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/** INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private BigDecimal inspectqty;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private BigDecimal confirmqty;

	/** TOLOC */
	@Schema(description = "TOLOC")
	private String toloc;

	/** VAL */
	@Schema(description = "VAL")
	private String val;

	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

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
	private BigDecimal bunja;

	/** BUNMO */
	@Schema(description = "BUNMO")
	private BigDecimal bunmo;

	/** OPENQTY_EA */
	@Schema(description = "OPENQTY_EA")
	private BigDecimal openqtyEa;

	/** OPENQTY_BOX */
	@Schema(description = "OPENQTY_BOX")
	private BigDecimal openqtyBox;

	/** PLTQTY */
	@Schema(description = "PLTQTY")
	private BigDecimal pltqty;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private BigDecimal orderqty;

	/** ORDERWEIGHT */
	@Schema(description = "ORDERWEIGHT")
	private BigDecimal orderweight;

	/** SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private BigDecimal shortageqty;

	/** SHORTAGETRANQTY */
	@Schema(description = "SHORTAGETRANQTY")
	private BigDecimal shortagetranqty;

	/** TRANQTY */
	@Schema(description = "TRANQTY")
	private String tranqty;

	/** LOC */
	@Schema(description = "LOC")
	private String loc;

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
	private BigDecimal qtyperbox;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** DURATION_RATE */
	@Schema(description = "DURATION_RATE")
	private BigDecimal durationRate;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/** LASTLOTTABLE01 */
	@Schema(description = "LASTLOTTABLE01")
	private String lastlottable01;

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

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** STORAGETYPENAME */
	@Schema(description = "STORAGETYPENAME")
	private String storagetypename;

	/** INSPECTQTY_WD */
	@Schema(description = "INSPECTQTY_WD")
	private String inspectqtyWd;

	/** CONFIRMQTY_WD */
	@Schema(description = "CONFIRMQTY_WD")
	private String confirmqtyWd;

	/** STO_FLAG */
	@Schema(description = "STO_FLAG")
	private String stoFlag;

	/** BOXPERPLT */
	@Schema(description = "BOXPERPLT")
	private String boxperplt;

	/** * 반품예외여부 */
	@Schema(description = "* 반품예외여부")
	private String excptYn;
	private String excptYnOri;

	/** * 기안번호 */
	@Schema(description = "* 기안번호")
	private String draftNo;

	/** * 최종변경자 */
	@Schema(description = "* 최종변경자")
	private String editwho;

	/** * 최종변경자 이름 */
	@Schema(description = "* 최종변경자 이름")
	private String editwhoName;

	/** * 제조 */
	@Schema(description = "* 제조")
	private String lotManufacture;

	/** * 유통 */
	@Schema(description = "* 유통")
	private String lotExpire;

	/** * 방수 */
	@Schema(description = "* 방수")
	private String boxperlayer;

	/** * 단수 */
	@Schema(description = "* 단수")
	private String layerperplt;

	/** * 협력사코드 */
	@Schema(description = "* 협력사코드")
	private String custkey;

}
