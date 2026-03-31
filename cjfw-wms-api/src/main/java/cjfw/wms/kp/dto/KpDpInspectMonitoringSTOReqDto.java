package cjfw.wms.kp.dto;

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
 * @date : 2025.11.29 
 * @description : 광역출고검수현황 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "광역출고검수현황 요청") 
public class KpDpInspectMonitoringSTOReqDto extends CommonProcedureDto {
	
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
    /**
     * 광역출고시작일자
     */
    @Schema(description = "광역출고시작일자", example = "C100")
    private String fromSlipdt;
    
    /**
     * 광역출고종료일자
     */
    @Schema(description = "광역출고종료일자", example = "C100")
    private String toSlipdt;
    
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String fromCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromCustkeyMulti;

	/** vendor */
	@Schema(description = "vendor", nullable = false, example = "")
	private String vendor;
	
	/** vendor(다중검색) */
	@MultiSearch
    @Schema(description = "vendor-다중OR검색")
    private List<List<String>> vendorMulti; 

	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** toDccode */
	@Schema(description = "toDccode", nullable = false, example = "")
	private String toDccode;
	
	/** fromDccode */
	@Schema(description = "fromDccode", nullable = false, example = "")
	private String fromDccode;

	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** fromDc */
	@Schema(description = "fromDc", nullable = false, example = "")
	private String fromDc;
	
	/** toDc */
	@Schema(description = "toDc", nullable = false, example = "")
	private String toDc;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** channel */
	@Schema(description = "channel", nullable = false, example = "")
	private String channel;
		
		
}
