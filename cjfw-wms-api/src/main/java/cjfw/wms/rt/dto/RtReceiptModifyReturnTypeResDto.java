package cjfw.wms.rt.dto;

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
 * @date : 2025.09.10
 * @description : 반품회수/미회수변경 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품회수/미회수변경 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtReceiptModifyReturnTypeResDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	/** /** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/**
	 * 창고명
	 */
	@Schema(description = "창고명", example = "중앙물류창고")
	private String organizename;

	/** DCNAME */
	@Schema(description = "DCNAME")
	private String dcname;

	/** DOCNO_WD */
	@Schema(description = "DOCNO_WD")
	private String docnoWd;

	/** DOCDT_WD */
	@Schema(description = "DOCDT_WD")
	private String docdtWd;

	/** CONFIRMDATE */
	@Schema(description = "CONFIRMDATE")
	private String confirmdate;

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

	/** RETURNCARNO */
	@Schema(description = "RETURNCARNO")
	private String returncarno;

	/** SKU */
	@Schema(description = "SKU")
	private String sku;

	/** SKUNAME */
	@Schema(description = "SKUNAME")
	private String skuname;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** CHANNELNAME */
	@Schema(description = "CHANNELNAME")
	private String channelname;

	/** STORAGETYPE */
	@Schema(description = "STORAGETYPE")
	private String storagetype;

	/** STORAGETYPENAME */
	@Schema(description = "STORAGETYPENAME")
	private String storagetypename;

	/** UOM */
	@Schema(description = "UOM")
	private String uom;

	/** ORDERQTY */
	@Schema(description = "ORDERQTY")
	private String orderqty;

	/** CONFIRMQTY */
	@Schema(description = "CONFIRMQTY")
	private String confirmqty;

	/** SHORTAGEQTY */
	@Schema(description = "SHORTAGEQTY")
	private String shortageqty;

	/** PACKINGMETHOD */
	@Schema(description = "PACKINGMETHOD")
	private String packingmethod;

	/** RETURNTYPE */
	@Schema(description = "RETURNTYPE")
	private String returntype;

	/** RETURNTYPENAME */
	@Schema(description = "RETURNTYPENAME")
	private String returntypename;

	/** REASONCODE */
	@Schema(description = "REASONCODE")
	private String reasoncode;

	/** REASONCODENAME */
	@Schema(description = "REASONCODENAME")
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

	/** SOURCEKEY */
	@Schema(description = "SOURCEKEY")
	private String sourcekey;

	/** SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** SLIPLINE */
	@Schema(description = "SLIPLINE")
	private String slipline;

	/** DEL_YN */
	@Schema(description = "DEL_YN")
	private String delYn;

	/** IF_AUDIT_FILE */
	@Schema(description = "IF_AUDIT_FILE")
	private String ifAuditFile;

	/** IF_SEND_FILE */
	@Schema(description = "IF_SEND_FILE")
	private String ifSendFile;

	/** PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/** PLANT_DESCR */
	@Schema(description = "PLANT_DESCR")
	private String plantDescr;
}
