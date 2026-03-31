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
 * @date : 2025.06.16 
 * @description : 입고진행현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "입고진행현황 요청") 
public class DpInplanReqDto extends CommonDto {
	
	
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;

	/** slipno */
	@Schema(description = "slipno", nullable = false, example = "")
	private String slipno;

	/** tpltype */
	@Schema(description = "tpltype", nullable = false, example = "")
	private String tpltype;
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
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
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
	/** serialtype */
	@Schema(description = "serialtype", nullable = false, example = "")
	private String serialtype;
	
	
	
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

	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromcustkeyMulti;

    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti;

    /** ordertype */
	@MultiSearch
    @Schema(description = "ordertype")
    private List<String> ordertypeMulti;

    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;
}