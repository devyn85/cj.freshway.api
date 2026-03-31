package cjfw.wms.rt.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
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
 * @description : 반품판정처리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품판정처리 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtQCConfirmResDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/**STO_ DCCODE */
	@Schema(description = "STO_DCCODE")
	private String stoDccode;

	/** STO_DCNAME */
	@Schema(description = "STO_DCNAME")
	private String stoDcname;

	/** DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/** DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/** DOCNO_WD */
	@Schema(description = "DOCNO_WD")
	private String docnoWd;

	/** DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/** FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY")
	private String fromCustkey;

	/** FROM_CUSTNAME */
	@Schema(description = "FROM_CUSTNAME")
	private String fromCustname;

	/** BILLTOCUSTKEY */
	@Schema(description = "BILLTOCUSTKEY")
	private String billtocustkey;

	/** BILLTOCUSTNAME */
	@Schema(description = "BILLTOCUSTNAME")
	private String billtocustname;

	/** SALEORGANIZE */
	@Schema(description = "SALEORGANIZE")
	private String saleorganize;

	/** SALEGROUP */
	@Schema(description = "SALEGROUP")
	private String salegroup;

	/** SALEDEPARTMENT */
	@Schema(description = "SALEDEPARTMENT")
	private String saledepartment;

	/** OTHER03 */
	@Schema(description = "OTHER03")
	private String other03;

	/** OTHER04 */
	@Schema(description = "OTHER04")
	private String other04;

	/** REASONCODE */
	@Schema(description = "REASONCODE")
	private String reasoncode;

	/** OTHER01 */
	@Schema(description = "OTHER01")
	private String other01;

	/** BLNGDEPTNAME */
	@Schema(description = "BLNGDEPTNAME")
	private String blngdeptname;

	/** VENDOR */
	@Schema(description = "VENDOR")
	private String vendor;

	/** VENDORNAME */
	@Schema(description = "VENDORNAME")
	private String vendorname;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** CHANNELName */
	@Schema(description = "CHANNELName")
	private String channelName;

	/** ROTYPE */
	@Schema(description = "ROTYPE")
	private String rotype;

	/** QTY */
	@Schema(description = "QTY")
	private BigDecimal qty;

	/** CUSTORDERQTY */
	@Schema(description = "CUSTORDERQTY")
	private BigDecimal custorderqty;

	/** UNRETURNQTY */
	@Schema(description = "UNRETURNQTY")
	private BigDecimal unreturnqty;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private BigDecimal confirmqty;

	/** RETURNOUTQTY */
	@Schema(description = "RETURNOUTQTY")
	private BigDecimal returnoutqty;

	/** DISUSEQTY */
	@Schema(description = "DISUSEQTY")
	private BigDecimal disuseqty;

	/** GOODQTY */
	@Schema(description = "GOODQTY")
	private BigDecimal goodqty;

	/** UNCONFIRMQTY */
	@Schema(description = "UNCONFIRMQTY")
	private BigDecimal unconfirmqty;

	/** UOM */
	@Schema(description = "UOM")
	private String uom;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** DURATION_TERM */
	@Schema(description = "DURATION_TERM")
	private String durationTerm;

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

	/** STATUS */
	@Schema(description = "STATUS")
	private String status;

	/** REASONMSG */
	@Schema(description = "REASONMSG")
	private String reasonmsg;

	/** SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** SLIPLINE */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/** ORG_RETURNOUTQTY */
	@Schema(description = "ORG_RETURNOUTQTY")
	private BigDecimal orgReturnoutqty;

	/** ORG_DISUSEQTY */
	@Schema(description = "ORG_DISUSEQTY")
	private BigDecimal orgDisuseqty;

	/** ORG_GOODQTY */
	@Schema(description = "ORG_GOODQTY")
	private BigDecimal orgGoodqty;

	/** RETURNOUTCFMQTY */
	@Schema(description = "RETURNOUTCFMQTY")
	private BigDecimal returnoutcfmqty;

	/** DISUSECFMQTY */
	@Schema(description = "DISUSECFMQTY")
	private BigDecimal disusecfmqty;

	/** GOODCFMQTY */
	@Schema(description = "GOODCFMQTY")
	private BigDecimal goodcfmqty;

	/** LOC */
	@Schema(description = "LOC")
	private String loc;

	/** LOT */
	@Schema(description = "LOT")
	private String lot;

	/** STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/** STOCKTYPE */
	@Schema(description = "STOCKTYPE")
	private String stocktype;

	/** BUNMO */
	@Schema(description = "BUNMO")
	private BigDecimal bunmo;

	/** BUNJA */
	@Schema(description = "BUNJA")
	private BigDecimal bunja;

	/** DURATION */
	@Schema(description = "DURATION")
	private String duration;

	/** DURATIONTYPE */
	@Schema(description = "DURATIONTYPE")
	private String durationtype;

	/** PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/** PLANT_DESCR */
	@Schema(description = "PLANT_DESCR")
	private String plantDescr;

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

	/** BARCODE_SN */
	@Schema(description = "BARCODE_SN")
	private String barcodeSn;

	/** SERIALORDERQTY */
	@Schema(description = "SERIALORDERQTY")
	private BigDecimal serialorderqty;

	/** SERIALINSPECTQTY */
	@Schema(description = "SERIALINSPECTQTY")
	private BigDecimal serialinspectqty;

	/** SERIALSCANWEIGHT */
	@Schema(description = "SERIALSCANWEIGHT")
	private String serialscanweight;

	/** CHECK_FLAG */
	@Schema(description = "CHECK_FLAG")
	private String checkFlag;

	/** PROCESSTYPE */
	@Schema(description = "PROCESSTYPE")
	private String processtype;

	/** PROCESSTYPENAME */
	@Schema(description = "PROCESSTYPENAME")
	private String processtypename;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** AREA */
	@Schema(description = "AREA")
	private String area;

	/** CUSTKEY */
	@Schema(description = "CUSTKEY")
	private String custkey;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private BigDecimal orderqty;

	/** STORERUOM */
	@Schema(description = "STORERUOM")
	private String storeruom;

	/** BATCH_NO */
	@Schema(description = "BATCH_NO")
	private String batchNo;

	/** LIST_NO */
	@Schema(description = "LIST_NO")
	private String listNo;

	/** QCDT */
	@Schema(description = "QCDT")
	private String qcdt;

	/** WAVEKEY */
	@Schema(description = "WAVEKEY")
	private String wavekey;

	/** ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/** IOTYPE */
	@Schema(description = "IOTYPE")
	private String iotype;

	/** *입고확정후D+3내에반출되지않는상품에대한지연여부 */
	@Schema(description = "*입고확정후D+3내에반출되지않는상품에대한지연여부")
	private String vendoreturnyn;

	/** VENDORYN */
	@Schema(description = "VENDORYN")
	private String vendoryn;

	/** STORAGELOC */
	@Schema(description = "STORAGELOC")
	private String storageloc;

	/** * 제조 */
	@Schema(description = "* 제조")
	private String lotManufacture;

	/** * 유통 */
	@Schema(description = "* 유통")
	private String lotExpire;

	/** * logiRespYn */
	@Schema(description = "* logiRespYn")
	private String logiRespYn;

	/** * RespType */
	@Schema(description = "* RespType")
	private String RespType;

	/** * RespType */
	@Schema(description = "* RespType")
	private String RespTypeNm;

	/** * toLoc */
	@Schema(description = "* toLoc")
	private String toLoc;

	/** * toLocYn */
	@Schema(description = "* toLocYn")
	private String toLocYn;

	/** * fixdccode */
	@Schema(description = "* fixdccode")
	private String fixdccode;
    /**
     * 박스입수
     */
    @Schema(description = "박스입수", example = "10")
    private BigDecimal qtyperbox;
}
