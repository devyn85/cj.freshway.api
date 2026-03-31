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
 * @date : 2025.09.29 
 * @description : 피킹작업지시(상품별) 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "피킹작업지시(상품별) 요청") 
public class WdTaskSkuReqDto extends CommonProcedureDto {
	/** 피킹지시(대상확정) 저장 리스트 */
	List<WdTaskSkuResDetailDto> savePickingBatchList;
	
	
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/** taskdt */
	@Schema(description = "taskdt", nullable = false, example = "")
	private String taskdt;
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 

	/** skugroup */
	@Schema(description = "skugroup", nullable = false, example = "")
	private String skugroup;

	/** chkYn */
	@Schema(description = "chkYn", nullable = false, example = "")
	private String chkYn;
	
	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** plant */
	@Schema(description = "plant", nullable = false, example = "")
	private String plant;
	
	/** distancetype */
	@Schema(description = "distancetype", nullable = false, example = "")
	private String distancetype;
	
	/** createkey */
	@Schema(description = "createkey", nullable = false, example = "")
	private String createkey;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
	/** pickBatchNo */
	@Schema(description = "pickBatchNo", nullable = false, example = "")
	private String pickBatchNo;
	
	/** pickNo */
	@Schema(description = "pickNo", nullable = false, example = "")
	private String pickNo;
	
	/** pickListNo */
	@Schema(description = "pickListNo", nullable = false, example = "")
	private String pickListNo;
	
	
	
	/** createtype */
	@Schema(description = "createtype", nullable = false, example = "")
	private String createtype;
	
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;		
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** wavekey */
	@Schema(description = "wavekey", nullable = false, example = "")
	private String wavekey;
	
		
}
