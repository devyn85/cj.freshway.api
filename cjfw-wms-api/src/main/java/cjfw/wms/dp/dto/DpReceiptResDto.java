package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.22
 * @description : 입고확정처리 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "입고확정처리 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DpReceiptResDto {
	/** CHECKYN */
	@Schema(description = "CHECKYN")
	private String checkyn;

	/** DCCODE */
	@Schema(description = "DCCODE")
	private String dccode;

	/** STORERKEY */
	@Schema(description = "STORERKEY")
	private String storerkey;

	/** DOCDT */
	@Schema(description = "DOCDT")
	private String docdt;

	/** DOCNO */
	@Schema(description = "DOCNO")
	private String docno;

	/** DOCTYPE */
	@Schema(description = "DOCTYPE")
	private String doctype;

	/** SLIPDT */
	@Schema(description = "SLIPDT")
	private String slipdt;

	/** SLIPNO */
	@Schema(description = "SLIPNO")
	private String slipno;

	/** POTYPE */
	@Schema(description = "POTYPE")
	private String potype;

	/** POTYPENAME */
	@Schema(description = "POTYPENAME")
	private String potypename;

	/** ORGANIZE */
	@Schema(description = "ORGANIZE")
	private String organize;

	/** ORGANIZENAME */
	@Schema(description = "ORGANIZENAME")
	private String organizename;

	/** ORDERTYPE */
	@Schema(description = "ORDERTYPE")
	private String ordertype;

	/** ORDERTYPE_NAME */
	@Schema(description = "ORDERTYPE_NAME")
	private String ordertypeName;

	/** STATUS */
	@Schema(description = "STATUS")
	private String status;

	/** STATUSNAME */
	@Schema(description = "STATUSNAME")
	private String statusname;

	/** FROM_CUSTKEY */
	@Schema(description = "FROM_CUSTKEY")
	private String fromCustkey;

	/** FROM_CUSTNAME */
	@Schema(description = "FROM_CUSTNAME")
	private String fromCustname;

	/** PLANT */
	@Schema(description = "PLANT")
	private String plant;

	/** WORKPROCESSCODE */
	@Schema(description = "WORKPROCESSCODE")
	private String workprocesscode;

	/** CHANNEL */
	@Schema(description = "CHANNEL")
	private String channel;

	/** STOTYPE */
	@Schema(description = "STOTYPE")
	private String stotype;

	/** VAL */
	@Schema(description = "VAL")
	private String val;

	/** route */
	@Schema(description = "route")
	private String route;

	/** stoDpYn */
	@Schema(description = "stoDpYn")
	private String stoDpYn;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
