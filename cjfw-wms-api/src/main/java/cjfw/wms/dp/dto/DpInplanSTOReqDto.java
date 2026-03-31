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
 * @date : 2025.06.18 
 * @description : 광역입고현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역입고현황 요청") 
public class DpInplanSTOReqDto extends CommonDto {
	
	
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;
	
	/** tocustkey */
	@Schema(description = "tocustkey", nullable = false, example = "")
	private String tocustkey;

	/** 광역입고일자FROM */
	@Schema(description = "광역입고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 광역입고일자TO */
	@Schema(description = "광역입고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** FROM */
	@Schema(description = "FROM", nullable = false, example = "")
	private String fromDocdt;
	
	/** TO */
	@Schema(description = "TO", nullable = false, example = "")
	private String toDocdt;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;

    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;

	/** status*/
	@Schema(description = "status", nullable = false, example = "")
	private String status;	
	
	/** blno */
	@Schema(description = "blno", nullable = false, example = "")
	private String blno;

	    /**
     * blno
     */
    @MultiSearch
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> blnoMulti;
	
	/** serialno */
	@Schema(description = "serialno", nullable = false, example = "")
	private String serialno;

	    /**
     * serialno
     */
    @MultiSearch
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> serialnoMulti;
	
	/** contractcompany */
	@Schema(description = "contractcompany", nullable = false, example = "")
	private String contractcompany;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;

    /**
     * 문서번호
     */
    @MultiSearch
    @Schema(description = "문서번호", nullable = false, example = "")
    private List<String> docnoMulti;
	
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

	/** fromDccode */
	@Schema(description = "fromDccode", nullable = false, example = "")
	private String fromDccode;

	/** toDccode */
	@Schema(description = "toDccode", nullable = false, example = "")
	private String toDccode;
	
}
