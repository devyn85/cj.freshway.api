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
 * @description : 반품확정처리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품확정처리 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReceiptConfirmResDto {
	/** INPLAN */
	@Schema(description = "INPLAN")
	private String inplan;

	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** DCNAME */
	@Schema(description = "DCNAME")
	private String dcname;

	/** DOCNO_WD */
	@Schema(description = "DOCNO_WD")
	private String docnoWd;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** ORGANIZENAME */
	@Schema(description = "ORGANIZENAME")
	private String organizename;

	/** AREA */
	@Schema(description = "AREA")
	private String area;

	/** DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/** CONFIRMDATE */
	@Schema(description = "CONFIRMDATE")
	private String confirmdate;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/** POTYPE */
	@Schema(description = "POTYPE")
	private String potype;

	/** POTYPENAME */
	@Schema(description = "POTYPENAME")
	private String potypename;

	/** STATUS */
	@Schema(description = "STATUS")
	private String status;

	/** STATUSNAME */
	@Schema(description = "STATUSNAME")
	private String statusname;

	/** DOCLINE */
	@Schema(description = "DOCLINE")
	private String docline;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** QTYPERBOX */
	@Schema(description = "QTYPERBOX")
	private String qtyperbox;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** UOM */
	@Schema(description = "UOM")
	private String uom;

	/** BUNJA */
	@Schema(description = "BUNJA")
	private String bunja;

	/** BUNMO */
	@Schema(description = "BUNMO")
	private String bunmo;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/** INSPECTQTY */
	@Schema(description = "INSPECTQTY")
	private String inspectqty;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/** SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private String shortageqty;

	/** SHORTAGETRANQTY */
	@Schema(description = "SHORTAGETRANQTY")
	private String shortagetranqty;

	/** TRANQTY */
	@Schema(description = "TRANQTY")
	private String tranqty;

	/** TOLOC */
	@Schema(description = "TOLOC")
	private String toloc;

	/** LOTTABLE01 */
	@Schema(description = "LOTTABLE01")
	private String lottable01;

	/** DURATION_TERM */
	@Schema(description = "DURATION_TERM")
	private String durationTerm;

	/** STOCKGRADE */
	@Schema(description = "STOCKGRADE")
	private String stockgrade;

	/** STOCKID */
	@Schema(description = "STOCKID")
	private String stockid;

	/** PACKINGMETHOD */
	@Schema(description = "PACKINGMETHOD")
	private String packingmethod;

	/** RETURNTYPE */
	@Schema(description = "RETURNTYPE")
	private String returntype;

	/** RETURTYPENNAME */
	@Schema(description = "RETURTYPENNAME")
	private String returtypenname;

	/** REASONCODE */
	@Schema(description = "REASONCODE")
	private String reasoncode;

	/** reasoncodename */
	@Schema(description = "reasoncodename")
	private String reasoncodename;

	/** REASONMSG */
	@Schema(description = "REASONMSG")
	private String reasonmsg;

	/** OTHER01 */
	@Schema(description = "OTHER01")
	private String other01;

	/** BLNGDEPTNAME */
	@Schema(description = "BLNGDEPTNAME")
	private String blngdeptname;

	/** ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/** ORDERTYPENAME */
	@Schema(description = "ORDERTYPENAME")
	private String ordertypename;

	/** SALEORGANIZE */
	@Schema(description = "SALEORGANIZE")
	private String saleorganize;

	/** SALEGROUP */
	@Schema(description = "SALEGROUP")
	private String salegroup;

	/** SALEDEPARTMENT */
	@Schema(description = "SALEDEPARTMENT")
	private String saledepartment;

	/** CUSTGROUP */
	@Schema(description = "CUSTGROUP")
	private String custgroup;

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

	/** OTHER03 */
	@Schema(description = "OTHER03")
	private String other03;

	/** OTHER04 */
	@Schema(description = "OTHER04")
	private String other04;

	/** VENDORETURN */
	@Schema(description = "VENDORETURN")
	private String vendoreturn;

	/** CUSTNAME */
	@Schema(description = "CUSTNAME")
	private String custname;

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

	/** SERIALYN */
	@Schema(description = "SERIALYN")
	private String serialyn;

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

	/** INSPECTSERIALKEY */
	@Schema(description = "INSPECTSERIALKEY")
	private String inspectserialkey;

	/** FORCEINSPECT */
	@Schema(description = "FORCEINSPECT")
	private String forceinspect;

	/** LINE01 */
	@Schema(description = "LINE01")
	private String line01;

    /** * 제조 */
     @Schema(description = " 제조")
     private String lotManufacture;

     /** * 유통 */
     @Schema(description = " 유통")
     private String lotExpire;

	/** 변경요청귀책부서 */
	@Schema(description = "변경요청귀책부서")
	private String chgReqDeptCd;

	/** 변경요청귀책부서 */
	@Schema(description = "변경요청귀책부서")
	private String chgReqDeptNm;

	/** 이메일발송일시 */
	@Schema(description = "이메일발송일시")
	private String emailSendDate;

	/** REFERENCE15 */
	@Schema(description = "REFERENCE15")
	private String reference15;

	/** GUBUN */
	@Schema(description = "GUBUN")
	private String gubun;

	/** SOMDCODE */
	@Schema(description = "SOMDCODE")
	private String somdcode;

	/** EMP_NO */
	@Schema(description = "EMP_NO")
	private String empNo;

	/** SOMDNAME */
	@Schema(description = "SOMDNAME")
	private String somdname;

	/** MAIL_ID */
	@Schema(description = "MAIL_ID")
	private String mailId;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
