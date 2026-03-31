package cjfw.wms.dp.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.17 
 * @description : 이력상품입고현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력상품입고현황 요청") 
public class DpInplanSNReqDto extends CommonDto {
	
	
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;

	/** slipno */
	@Schema(description = "slipno", nullable = false, example = "")
	private String slipno;

	/** tpltype */
	@Schema(description = "tpltype", nullable = false, example = "")
	private String tpltype;
	
	/** 입고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 입고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** FROM */
	@Schema(description = "FROM", nullable = false, example = "")
	private String fromDocdt;
	
	/** TO */
	@Schema(description = "TO", nullable = false, example = "")
	private String toDocdt;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	
	/** channel */
	@Schema(description = "channel", nullable = false, example = "Y")
	private String channel;
	
	/** status*/
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
    /** 구매유형-멀티(2025.12.23 김동한 추가) */
	@MultiSearch   
    @Schema(description = "구매유형-멀티")
    private List<List<String>> ordertypeMulti;  
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
	/** blno */
	@Schema(description = "blno", nullable = false, example = "")
	private String blno;
	/** serialno */
	@Schema(description = "serialno", nullable = false, example = "")
	private String serialno;
	/** contractcompany */
	@Schema(description = "contractcompany", nullable = false, example = "")
	private String contractcompany;
	
	/** serialCheck */
	@Schema(description = "serialCheck", nullable = false, example = "")
	private String serialCheck;
	
	
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** doctype */
	@Schema(description = "doctype", nullable = false, example = "")
	private String doctype;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromcustkeyMulti;

    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti;

    /** blno */
	@MultiSearch
    @Schema(description = "blno")
    private List<String> blnoMulti;

    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;

    /** barcode */
	@MultiSearch
    @Schema(description = "barcode")
    private String barcode;

    /** barcode */
	@MultiSearch
    @Schema(description = "barcode")
    private List<String> barcodeMulti;

    /** serialno */
	@MultiSearch
    @Schema(description = "serialno")
    private List<String> serialnoMulti;
}
