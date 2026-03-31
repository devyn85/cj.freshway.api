package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.10.15 
 * @description : 이력배송라벨출력 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력배송라벨출력 요청") 
public class WdDeliveryLabelSNReqDto extends CommonProcedureDto {
	/** 분류표 생성 리스트 */
	List<WdDeliveryLabelSNResTab1Dto> saveCreationSNList;
	
		
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** plant */
	@Schema(description = "plant", nullable = false, example = "")
	private String plant;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** skugroup */
	@Schema(description = "skugroup", nullable = false, example = "")
	private String skugroup;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;

	/** 주문번호 */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 

	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;

	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** doctype */
	@Schema(description = "doctype", nullable = false, example = "")
	private String doctype;
	
	
	
	/** printOrder */
	@Schema(description = "printOrder", nullable = false, example = "")
	private String printOrder;
	
	/** orderbyPick */
	@Schema(description = "orderbyPick", nullable = false, example = "")
	private String orderbyPick;
	
	/** printpickinglist */
	@Schema(description = "printpickinglist", nullable = false, example = "")
	private String printpickinglist;
	
	/** printmethod */
	@Schema(description = "printmethod", nullable = false, example = "")
	private String printmethod;
	
	/** crossDc */
	@Schema(description = "crossDc", nullable = false, example = "")
	private String crossDc;
	
	/** invoiceno */
	@Schema(description = "invoiceno", nullable = false, example = "")
	private String invoiceno;
	
	/** invoiceno(다중검색) */
	@MultiSearch
    @Schema(description = "invoiceno-다중OR검색")
    private List<String> invoicenoMulti; 
	
	/** invoiceNoPrint */
	@Schema(description = "invoiceNoPrint", nullable = false, example = "")
	private String invoiceNoPrint;
	
	/** invoiceNoPrint(다중검색) */
	@MultiSearch
	@Schema(description = "invoiceNoPrint-다중OR검색")
	private List<String> invoiceNoPrintMulti; 
	
	/** memo */
	@Schema(description = "memo", nullable = false, example = "")
	private String memo;
	
	/** custkey */
	@Schema(description = "custkey", nullable = false, example = "")
	private String custkey;
	
	/** custkey(다중검색) */
	@MultiSearch
	@Schema(description = "custkey-다중OR검색")
	private List<String> custkeyMulti; 
	
	/** zone */
	@Schema(description = "zone", nullable = false, example = "")
	private String zone;
	
	/** zone(다중검색) */
	@MultiSearch
	@Schema(description = "zone", nullable = false, example = "")
	private List<String> zoneMulti; 
	

	/** exceptLoc */
	@Schema(description = "exceptLoc", nullable = false, example = "")
	private String exceptLoc;
		
}
