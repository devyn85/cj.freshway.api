package cjfw.wms.rt.dto;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.04 
 * @description : 반품진행현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "반품진행현황 목록 요청") 
public class RtInplanTotalReqDto extends CommonDto {
	
	/*
	 * RtInplanTotalResDto 쿼리에 사용(메인쿼리)
	 */

	/**
     * sku
     */
    @Schema(description = "sku", example = "SKU001,SKU002")
    private String sku;

    /**
     * returntype
     */
    @Schema(description = "returntype", example = "NORMAL")
    private String returntype;

    /**
     * Channel
     */
    @Schema(description = "Channel", example = "ONLINE")
    private String channel;

    /**
     * Status
     */
    @Schema(description = "Status", example = "COMPLETED")
    private String status;

    /**
     * userId
     */
    @Schema(description = "userId", example = "user123")
    private String userId;

    /**
     * DC Code
     */
    @Schema(description = "DC Code", example = "DC001")
    private String dccode;

    /**
     * Storer Key
     */
    @Schema(description = "Storer Key", example = "STR001")
    private String storerkey;


    /**
     * fromSlipdt
     */
    @Schema(description = "fromSlipdt", example = "20230101")
    private String fromSlipdt;

    /**
     * toSlipdt
     */
    @Schema(description = "toSlipdt", example = "20230131")
    private String toSlipdt;
    
    /**
     * cbxReturnInfo
     */
    @Schema(description = "cbxReturnInfo", example = "20230131")
    private String returnInfoYn;

    /**
     * Storage Type
     */
    @Schema(description = "Storage Type", example = "NORMAL")
    private String storagetype;

    /**
     * fromConfirmdate
     */
    @Schema(description = "fromConfirmdate", example = "20230101")
    private String fromConfirmdate;

    /**
     * toConfirmdate
     */
    @Schema(description = "toConfirmdate", example = "20230131")
    private String toConfirmdate;

    /**
     * Document No
     */
    @Schema(description = "Document No", example = "DOC001")
    private String docno;

    /**
     * PO Type
     */
    @Schema(description = "PO Type", example = "RETURN")
    private String potype;
    /**
     * sotype
     */
    @Schema(description = "sotype", example = "RETURN")
    private String sotype;

    /**
     * Order Type
     */
    @Schema(description = "Order Type", example = "CUSTOMER_RETURN")
    private String ordertype;

    /**
     * Vendor Return
     */
    @Schema(description = "Vendor Return", example = "Y")
    private String vendoreturn;

    /**
     * Courier
     */
    @Schema(description = "Courier", example = "CJ_LOGISTICS")
    private String courier;

    /**
     * Serial Check
     */
    @Schema(description = "Serial Check", example = "Y")
    private String serialCheck;

    /**
     * BL No
     */
    @Schema(description = "BL No", example = "BL12345")
    private String blno;

    /**
     * Serial No
     */
    @Schema(description = "Serial No", example = "SN67890")
    private String serialno;

    /**
     * fromcustkey
     */
    @Schema(description = "fromcustkey", example = "CUST001")
    private String fromcustkey;
    /**
     * Cust Key
     */
    @Schema(description = "Cust Key", example = "CUST001")
    private String custkey;
    /**
     * 구분
     */
    @Schema(description = "구분", example = "CUST001")
    private String searchDateType;
		
	
	
	/*
	 * RtInplanSNDetailResDto 쿼리에 사용
	 */
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** docline */
	@Schema(description = "docline", nullable = false, example = "")
	private String docline;
	
	/** sourcekey */
	@Schema(description = "sourcekey", nullable = false, example = "")
	private String sourcekey;
	
	/** doctype */
	@Schema(description = "doctype", nullable = false, example = "")
	private String doctype;

	/** claimdtlids */
	@Schema(description = "claimdtlids", nullable = false, example = "")
	private String claimdtlids;

    /**
     * claimdtlids
     */
    @MultiSearch
    @Schema(description = "claimdtlids", nullable = false, example = "")
    private List<String> claimdtlidsMulti;
}
