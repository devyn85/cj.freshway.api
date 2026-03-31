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
 * @date : 2025.09.16
 * @description : 반품확정처리 Request DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품확정처리 Detail Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReceiptConfirmResDetailDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /**
     * CHECKYN
     */
    @Schema(description = "CHECKYN")
    private String checkyn;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY")
    private String storerkey;

    /**
     * DCCODE
     */
    @Schema(description = "DCCODE")
    private String dccode;

    /**
     * SOURCEKEY
     */
    @Schema(description = "SOURCEKEY")
    private String sourcekey;

    /**
     * ORGANIZE
     */
    @Schema(description = "ORGANIZE")
    private String organize;

    /**
     * AREA
     */
    @Schema(description = "AREA")
    private String area;

    /**
     * DOCNO
     */
    @Schema(description = "DOCNO")
    private String docno;

    /**
     * DOCDT
     */
    @Schema(description = "DOCDT")
    private String docdt;

    /**
     * DOCLINE
     */
    @Schema(description = "DOCLINE")
    private String docline;

    /**
     * SKU
     */
    @Schema(description = "SKU")
    private String sku;

    /**
     * SKUNAME
     */
    @Schema(description = "SKUNAME")
    private String skuname;

    /**
     * CHANNEL
     */
    @Schema(description = "CHANNEL")
    private String channel;

    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE")
    private String storagetype;

    /**
     * UOM
     */
    @Schema(description = "UOM")
    private String uom;

    /**
     * ORDERQTY
     */
    @Schema(description = "ORDERQTY")
    private String orderqty;

    /**
     * TRANQTY
     */
    @Schema(description = "TRANQTY")
    private String tranqty;

    /**
     * TOLOC
     */
    @Schema(description = "TOLOC")
    private String toloc;

    /**
     * STOCKGRADE
     */
    @Schema(description = "STOCKGRADE")
    private String stockgrade;

    /**
     * LOTTABLE01
     */
    @Schema(description = "LOTTABLE01")
    private String lottable01;

    /**
     * DURATION_TERM
     */
    @Schema(description = "DURATION_TERM")
    private String durationTerm;

    /**
     * SERIALNO
     */
    @Schema(description = "SERIALNO")
    private String serialno;

    /**
     * CONVSERIALNO
     */
    @Schema(description = "CONVSERIALNO")
    private String convserialno;

    /**
     * SERIALLEVEL
     */
    @Schema(description = "SERIALLEVEL")
    private String seriallevel;

    /**
     * SERIALTYPE
     */
    @Schema(description = "SERIALTYPE")
    private String serialtype;

    /**
     * FACTORYNAME
     */
    @Schema(description = "FACTORYNAME")
    private String factoryname;

    /**
     * PLACEOFORIGIN
     */
    @Schema(description = "PLACEOFORIGIN")
    private String placeoforigin;

    /**
     * COLORDESCR
     */
    @Schema(description = "COLORDESCR")
    private String colordescr;

    /**
     * SLIPDT
     */
    @Schema(description = "SLIPDT")
    private String slipdt;

    /**
     * SLIPNO
     */
    @Schema(description = "SLIPNO")
    private String slipno;

    /**
     * SLIPLINE
     */
    @Schema(description = "SLIPLINE")
    private String slipline;

    /**
     * PRINTEDQTY
     */
    @Schema(description = "PRINTEDQTY")
    private String printedqty;

    /**
     * PRINT_YN
     */
    @Schema(description = "PRINT_YN")
    private String printYn;

    /**
     * DOCTYPE
     */
    @Schema(description = "DOCTYPE")
    private String doctype;

    /**
     * BARCODE
     */
    @Schema(description = "BARCODE")
    private String barcode;

    /**
     * STATUS
     */
    @Schema(description = "STATUS")
    private String status;

    /**
     * STATUSNAME
     */
    @Schema(description = "STATUSNAME")
    private String statusname;

    /**
     * DURATION
     */
    @Schema(description = "DURATION")
    private String duration;

    /**
     * DURATIONTYPE
     */
    @Schema(description = "DURATIONTYPE")
    private String durationtype;

    /**
     * CONTRACTCOMPANY
     */
    @Schema(description = "CONTRACTCOMPANY")
    private String contractcompany;

    /**
     * CONTRACTCOMPANYNAME
     */
    @Schema(description = "CONTRACTCOMPANYNAME")
    private String contractcompanyname;

    /**
     * CONTRACTTYPE
     */
    @Schema(description = "CONTRACTTYPE")
    private String contracttype;

    /**
     * BUTCHERYDT
     */
    @Schema(description = "BUTCHERYDT")
    private String butcherydt;

    /**
     * FROMVALIDDT
     */
    @Schema(description = "FROMVALIDDT")
    private String fromvaliddt;

    /**
     * TOVALIDDT
     */
    @Schema(description = "TOVALIDDT")
    private String tovaliddt;

    /**
     * BARCODE_SN
     */
    @Schema(description = "BARCODE_SN")
    private String barcodeSn;

    /**
     * SERIALORDERQTY
     */
    @Schema(description = "SERIALORDERQTY")
    private String serialorderqty;

    /**
     * SERIALINSPECTQTY
     */
    @Schema(description = "SERIALINSPECTQTY")
    private String serialinspectqty;

    /**
     * SERIALSCANWEIGHT
     */
    @Schema(description = "SERIALSCANWEIGHT")
    private String serialscanweight;

    /**
     * PLANT_DESCR
     */
    @Schema(description = "PLANT_DESCR")
    private String plantDescr;
    /**
     * 클레임번호
     */
    @Schema(description = "클레임번호", example = "C20250605-001")
    private String sapclaimno;
    /**
     * 세부내역
     */
    @Schema(description = "세부내역", example = "고객 변심으로 인한 반품")
    private String memo;
    /**
     * 작성자
     */
    @Schema(description = "작성자", example = "홍길동")
    private String writer;
    /**
     * 작성일자
     */
    @Schema(description = "작성일자", example = "20250605")
    private String writedate;
    /**
     * 작성시간
     */
    @Schema(description = "작성시간", example = "103000")
    private String writetime;

    /** * 제조 */
    @Schema(description = " 제조")
    private String lotManufacture;

    /** * 유통 */
    @Schema(description = " 유통")
    private String lotExpire;
}
