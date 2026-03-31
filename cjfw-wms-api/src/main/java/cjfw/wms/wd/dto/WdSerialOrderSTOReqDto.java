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
 * @description : 이력STO출고처리 요청 DTO
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
@Schema(description = "이력STO출고처리 요청") 
public class WdSerialOrderSTOReqDto extends CommonProcedureDto {
	/** STO생성 저장 리스트 */
	List<WdSerialOrderSTOResTab1Dto> saveCreationSTOList;
	/** STO출고확정 저장 리스트 */
	List<WdSerialOrderSTOResTab2Dto> saveBatchConfirmList;
	/** 출고대상확정 리스트 */
	List<WdSerialOrderSTOResTab2WdDto> saveBatchConfirmLine;
	/** 결품대상확정 리스트 */
	List<WdSerialOrderSTOResTab2ShortageDto> saveBatchCancelLine;
	/** SO&STO분리 리스트 */
	List<WdSerialOrderSTOResTab2WdDto> saveDistributeList;
	
	

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/** fromSlipdt */
	@Schema(description = "fromSlipdt", nullable = false, example = "")
	private String fromSlipdt;
	
	/** toSlipdt */
	@Schema(description = "toSlipdt", nullable = false, example = "")
	private String toSlipdt;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti; 
	
	/** ordertype */
	@Schema(description = "ordertype", nullable = false, example = "")
	private String ordertype;
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	/** fromCustkey */
	@Schema(description = "fromCustkey", nullable = false, example = "")
	private String fromCustkey;

	/** fromCustkey(다중OR검색) */
	@MultiSearch
    @Schema(description = "fromCustkey-다중OR검색")
    private List<List<String>> fromCustkeyMulti;	

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
	
	/** toDccode */
	@Schema(description = "toDccode", nullable = false, example = "")
	private String toDccode;
	
	/** fromDccode */
	@Schema(description = "fromDccode", nullable = false, example = "")
	private String fromDccode;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** docnoSTO */
	@Schema(description = "docnoSTO", nullable = false, example = "")
	private String docnoSTO;

	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** storerkey */
	@Schema(description = "storerkey", nullable = false, example = "")
	private String storerkey;
	
	/** slipdt */
	@Schema(description = "slipdt", nullable = false, example = "")
	private String slipdt;
	
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;	
	
	/** taskdt */
	@Schema(description = "taskdt", nullable = false, example = "")
	private String taskdt;	
	
	
	
		
}
