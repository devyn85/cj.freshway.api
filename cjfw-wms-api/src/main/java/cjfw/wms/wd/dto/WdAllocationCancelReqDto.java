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
 * @date : 2025.07.24 
 * @description : 출고분배취소 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고분배취소 목록 요청") 
public class WdAllocationCancelReqDto extends CommonProcedureDto {
		
	/** 자동취소 리스트 */
	List<WdAllocationCancelResDto> saveAutoBatchList;
	/** 지정취소 리스트 */
	List<WdAllocationCancelDetailSubResDto> saveList;
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
	

	/** fixdccode : 조회조건 */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** 차량번호 */
	@Schema(description = "차량번호", nullable = false, example = "")
	private String carno;
	
	@Schema(description = "fromdate", example = "2025-01-01")
    private String fromdate;
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "Y")
	private String storagetype;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** deliverydt */
	@Schema(description = "deliverydt", nullable = false, example = "")
	private String deliverydt;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** slipno */
	@Schema(description = "slipno", nullable = false, example = "")
	private String slipno;
	
	/** slipline */
	@Schema(description = "slipline", nullable = false, example = "")
	private String slipline;
	
	/** serialyn */
	@Schema(description = "serialyn", nullable = false, example = "")
	private String serialyn;
	
	
	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
	/** vendor(다중검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<String> vendorMulti; 
	
	/** sku */
	@Schema(description = "sku", nullable = false, example = "")
	private String sku;
	
	
	
}
