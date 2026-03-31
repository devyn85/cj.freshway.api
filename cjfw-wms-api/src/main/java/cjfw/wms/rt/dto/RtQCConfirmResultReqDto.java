package cjfw.wms.rt.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.21
 * @description : 반품판정현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.21 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "반품판정현황 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtQCConfirmResultReqDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


	/** other03 */
	@Schema(description = "other03")
	private String other03;

	/** channel */
	@Schema(description = "channel")
	private String channel;

	/** sku */
	@Schema(description = "sku")
	private String sku;

	/** storagetype */
	@Schema(description = "storagetype")
	private String storagetype;

	/** vendoreturnyn */
	@Schema(description = "vendoreturnyn")
	private String vendoreturnyn;

	/** vendoreturn */
	@Schema(description = "vendoreturn")
	private String vendoreturn;

	/** saledepartment */
	@Schema(description = "saledepartment")
	private String saledepartment;

	/** salegroup */
	@Schema(description = "salegroup")
	private String salegroup;

	/** docdtFrom */
	@Schema(description = "docdtFrom")
	private String docdtFrom;

	/** docdtTo */
	@Schema(description = "docdtTo")
	private String docdtTo;

	/** slipdtFrom */
	@Schema(description = "slipdtFrom")
	private String slipdtFrom;

	/** slipdtTo */
	@Schema(description = "slipdtTo")
	private String slipdtTo;

	/** qcdtFrom */
	@Schema(description = "qcdtFrom")
	private String qcdtFrom;

	/** qcdtTo */
	@Schema(description = "qcdtTo")
	private String qcdtTo;

	/** docno */
	@Schema(description = "docno")
	private String docno;

	/** docnoWd */
	@Schema(description = "docnoWd")
	private String docnoWd;

	/** statusRtqc */
	@Schema(description = "statusRtqc")
	private String statusRtqc;

	/** fromCustkey */
	@Schema(description = "fromCustkey")
	private String fromCustkey;

	/** vendor */
	@Schema(description = "vendor")
	private String vendor;

	/** 관리처코드(다중OR검색) */
	@MultiSearch
	@Schema(description = "관리처코드-다중OR검색")
	private List<List<String>> fromCustkeyMulti;

	/** 주문번호(다중검색) */
	@MultiSearch
	@Schema(description = "주문번호-다중OR검색")
	private List<String> docnoMulti;

	/** 주문번호(다중검색) */
	@MultiSearch
	@Schema(description = "주문번호-다중OR검색")
	private List<String> docnoWdMulti;

	/** 상품(다중OR검색) */
	@MultiSearch
	@Schema(description = "상품-다중OR검색")
	private List<List<String>> skuMulti;
}
