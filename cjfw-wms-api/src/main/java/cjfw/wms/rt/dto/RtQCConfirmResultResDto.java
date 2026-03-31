package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.21
 * @description : 반품판정현황 Master Response DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품판정현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtQCConfirmResultResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** CHECKYN */
    @Schema(description = "CHECKYN")
    private String checkyn;

    /** DOCNO */
    @Schema(description = "DOCNO")
    private String docno;

    /** DOCLINE */
    @Schema(description = "DOCLINE")
    private String docline;

    /** DOCNO_WD */
    @Schema(description = "DOCNO_WD")
    private String docnoWd;

    /** SLIPDT */
    @Schema(description = "SLIPDT")
    private String slipdt;

    /** QCDT */
    @Schema(description = "QCDT")
    private String qcdt;

    /** FROM_CUSTKEY */
    @Schema(description = "FROM_CUSTKEY")
    private String fromCustkey;

    /** FROM_CUSTNAME */
    @Schema(description = "FROM_CUSTNAME")
    private String fromCustname;

    /** BILLTOCUSTKEY */
    @Schema(description = "BILLTOCUSTKEY")
    private String billtocustkey;

    /** BILLTOCJUSTNAME */
    @Schema(description = "BILLTOCJUSTNAME")
    private String billtocjustname;

    /** OTHER03 */
    @Schema(description = "OTHER03")
    private String other03;

    /** OTHER04 */
    @Schema(description = "OTHER04")
    private String other04;

    /** VENDORETURN */
    @Schema(description = "VENDORETURN")
    private String vendoreturn;

    /** REASONCODE */
    @Schema(description = "REASONCODE")
    private String reasoncode;

    /** REASONMSG */
    @Schema(description = "REASONMSG")
    private String reasonmsg;

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

    /** CUSTORDERQTY */
    @Schema(description = "CUSTORDERQTY")
    private String custorderqty;

    /** UNRETURNQTY */
    @Schema(description = "UNRETURNQTY")
    private String unreturnqty;

    /** CONFIRMQTY */
    @Schema(description = "CONFIRMQTY")
    private String confirmqty;

    /** RETURNOUTQTY */
    @Schema(description = "RETURNOUTQTY")
    private String returnoutqty;

    /** DISUSEQTY */
    @Schema(description = "DISUSEQTY")
    private String disuseqty;

    /** GOODQTY */
    @Schema(description = "GOODQTY")
    private String goodqty;

    /** RETURNOUTCFMQTY */
    @Schema(description = "RETURNOUTCFMQTY")
    private String returnoutcfmqty;

    /** DISUSECFMQTY */
    @Schema(description = "DISUSECFMQTY")
    private String disusecfmqty;

    /** GOODCFMQTY */
    @Schema(description = "GOODCFMQTY")
    private String goodcfmqty;

    /** UNCONFIRMQTY */
    @Schema(description = "UNCONFIRMQTY")
    private String unconfirmqty;

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

    /** STOCKID */
    @Schema(description = "STOCKID")
    private String stockid;

    /** SERIALORDERQTY */
    @Schema(description = "SERIALORDERQTY")
    private String serialorderqty;

    /** SERIALINSPECTQTY */
    @Schema(description = "SERIALINSPECTQTY")
    private String serialinspectqty;

    /** SERIALSCANWEIGHT */
    @Schema(description = "SERIALSCANWEIGHT")
    private String serialscanweight;

    /** COLORDESCR */
    @Schema(description = "COLORDESCR")
    private String colordescr;

    /** PLACEOFORIGIN */
    @Schema(description = "PLACEOFORIGIN")
    private String placeoforigin;

    /** STATUS */
    @Schema(description = "STATUS")
    private String status;

    /** QCDOCNO */
    @Schema(description = "QCDOCNO")
    private String qcdocno;

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

    /** 평균중량 */
    @Schema(description = "평균중량")
    private String avgweight;

    /** 환산주문박스 */
    @Schema(description = "환산주문박스")
    private String calorderbox;

    /** 환산확정박스 */
    @Schema(description = "환산확정박스")
    private String calconfirmbox;

    /** 실박스 */
    @Schema(description = "실박스")
    private String realbox;

    /** CONFIRMDATE */
    @Schema(description = "CONFIRMDATE")
    private String confirmdate;

    /** PACKINGMETHOD */
    @Schema(description = "PACKINGMETHOD")
    private String packingmethod;

    /**
     * 제조
     */
    @Schema(description = "* 제조")
    private String lotManufacture;

    /**
     * 유통
     */
    @Schema(description = "* 유통")
    private String lotExpire;

    /** CARNO */
	@Schema(description = "CARNO")
	private String carno;
}
