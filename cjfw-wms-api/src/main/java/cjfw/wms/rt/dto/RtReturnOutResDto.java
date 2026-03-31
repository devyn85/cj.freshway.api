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
 * @date : 2025.10.13
 * @description : 협력사반품지시 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "협력사반품지시 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReturnOutResDto {
	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** DCNAME */
	@Schema(description = "DCNAME")
	private String dcname;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** AREA */
	@Schema(description = "AREA")
	private String area;

	/** CUSTKEY */
	@Schema(description = "CUSTKEY")
	private String custkey;

	/** CUSTNAME */
	@Schema(description = "CUSTNAME")
	private String custname;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** PALNT */
	@Schema(description = "PALNT")
	private String palnt;

	/** PLANT_DESCR */
	@Schema(description = "PLANT_DESCR")
	private String plantDescr;

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** SERIALYN */
	@Schema(description = "SERIALYN")
	private String serialyn;

	/** SERIALYNNAME */
	@Schema(description = "SERIALYNNAME")
	private String serialynname;

	/** WD_QTY */
	@Schema(description = "WD_QTY")
	private BigDecimal wdQty;

	/** QTY */
	@Schema(description = "QTY")
	private BigDecimal qty;

	/** UOM */
	@Schema(description = "UOM")
	private String uom;

	/** LOC */
	@Schema(description = "LOC")
	private String loc;

	/** LOT */
	@Schema(description = "LOT")
	private String lot;

	/** STOCKTYPE */
	@Schema(description = "STOCKTYPE")
	private String stocktype;

	/** STOCKTYPENAME */
	@Schema(description = "STOCKTYPENAME")
	private String stocktypename;

	/** STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/** STOCKGRADENAME */
	@Schema(description = "STOCKGRADENAME")
	private String stockgradename;

	/** REASONCODE */
	@Schema(description = "REASONCODE")
	private String reasoncode;

	/** OTHER03 */
	@Schema(description = "OTHER03")
	private String other03;

	/** OTHER04 */
	@Schema(description = "OTHER04")
	private String other04;

	/** OTHER01 */
	@Schema(description = "OTHER01")
	private String other01;

	/** OTHER05 */
	@Schema(description = "OTHER05")
	private String other05;

	/** BLNGDEPTCD */
	@Schema(description = "BLNGDEPTCD")
	private String blngdeptcd;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** * 제조 */
	@Schema(description = "* 제조")
	private String lotManufacture;

	/** * 유통 */
	@Schema(description = "* 유통")
	private String lotExpire;

	/** DURATION_TERM */
	@Schema(description = "DURATION_TERM")
	private String durationTerm;

	/** STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** BARCODE */
	@Schema(description = "BARCODE")
	private String barcode;

	/** SERIALNO */
	@Schema(description = "SERIALNO")
	private String serialno;

	/** CONVSERIALNO */
	@Schema(description = "CONVSERIALNO")
	private String convserialno;

	/** SERIALLEVEL */
	@Schema(description = "SERIALLEVEL")
	private String seriallevel;

	/** SERIALTYPE */
	@Schema(description = "SERIALTYPE")
	private String serialtype;

	/** FACTORYNAME */
	@Schema(description = "FACTORYNAME")
	private String factoryname;

	/** COLORDESCR */
	@Schema(description = "COLORDESCR")
	private String colordescr;

	/** PLACEOFORIGIN */
	@Schema(description = "PLACEOFORIGIN")
	private String placeoforigin;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

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

	/** STORAGELOC */
	@Schema(description = "STORAGELOC")
	private String storageloc;

	/** ETCQTY1 */
	@Schema(description = "ETCQTY1")
	private String etcqty1;

	/** CHECK_FLAG */
	@Schema(description = "CHECK_FLAG")
	private String checkFlag;

	/** 평균중량 */
	@Schema(description = "평균중량")
	private String avgweight;

	/** 환산박스 */
	@Schema(description = "환산박스")
	private String calbox;

	/** 실박스예정 */
	@Schema(description = "실박스예정")
	private String realorderbox;

	/** 실박스확정 */
	@Schema(description = "실박스확정")
	private String realcfmbox;

	/** 작업박스 */
	@Schema(description = "작업박스")
	private String tranbox;

	/** 박스처리유무 */
	@Schema(description = "박스처리유무")
	private String boxflag;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
