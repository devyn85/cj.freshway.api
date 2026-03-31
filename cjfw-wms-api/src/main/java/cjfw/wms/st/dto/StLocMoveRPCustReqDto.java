package cjfw.wms.st.dto;

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
 * @date : 2025.07.18 
 * @description : 거래처별보충(수원3층) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처별보충(수원3층) 요청") 
public class StLocMoveRPCustReqDto extends CommonDto {
	
	/** 저장 리스트 */
	List<StLocMoveRPCustReqDto> saveCreationList;
	/** 저장 리스트 */
	List<StLocMoveRPCustResTab1SkuDto> saveList;
	
	/** 저장 리스트 */
	List<StLocMoveRPCustResTab1SkuDto> saveDataList;

	
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String docdt;
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;   
	
	/** fromzone */
	@Schema(description = "fromzone", nullable = false, example = "")
	private String fromzone;
	
	/** tozone */
	@Schema(description = "tozone", nullable = false, example = "")
	private String tozone;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** printyn */
	@Schema(description = "printyn", nullable = false, example = "")
	private String printyn;
	
	/** supplno */
	@Schema(description = "supplno", nullable = false, example = "")
	private String supplno;
	
	/** toCustkey */
	@Schema(description = "toCustkey", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** fixdccode : 조회조건 */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	

	/** distancetype */
	@Schema(description = "distancetype", nullable = false, example = "")
	private String distancetype; 
	
	/** distancetype(다중OR검색) */
	@MultiSearch
    @Schema(description = "distancetype-다중OR검색")
    private List<String> distancetypeMulti;    
	
}
