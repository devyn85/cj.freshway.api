package cjfw.wms.om.dto;

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
 * @date : 2025.06.20 
 * @description : 일배협력사별주문현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일배협력사별주문현황 요청") 
public class OmOrderCustDailyReqDto extends CommonDto {
	
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;

	    /** 관리처코드(다중OR검색) */
    @MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromcustkeyMulti;
	
	/** tocustkey */
	@Schema(description = "tocustkey", nullable = false, example = "")
	private String tocustkey;

		/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> tocustkeyMulti;
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;

    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;

	/** status*/
	@Schema(description = "status", nullable = false, example = "")
	private String status;	

	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
	/** blno */
	@Schema(description = "blno", nullable = false, example = "")
	private String blno;

    /**
     * blno
     */
    @MultiSearch
    @Schema(description = "blno", nullable = false, example = "")
    private List<String> blnoMulti;
	
	/** serialno */
	@Schema(description = "serialno", nullable = false, example = "")
	private String serialno;

    /**
     * serialno
     */
    @MultiSearch
    @Schema(description = "serialno", nullable = false, example = "")
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
	
	/** delYn */
	@Schema(description = "delYn", nullable = false, example = "")
	private String delYn;
	
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
	
	
	
	
}
