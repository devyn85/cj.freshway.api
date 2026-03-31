package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14 
 * @description : 저장품발주현황 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmPurchaseMonitoringReqDto extends CommonProcedureDto {
	
	/** 조회 시작일 */
    @Schema(description = "조회 시작일", example = "")
    private String fromDate;

    /** 조회 종료일 */
    @Schema(description = "조회 종료일", example = "")
    private String toDate;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드", example = "")
    private String dccode;
    
    /** 물류센터코드 */
	@MultiSearch
	@Schema(description = "물류센터코드-다중OR검색", example = "")
	private List<String> dccodeMulti;

    /** 수급담당 */
    @Schema(description = "수급담당", example = "")
    private String buyerkey;
    
    /** 수급담당 */
	@MultiSearch
	@Schema(description = "수급담당-다중OR검색", example = "")
	private List<String> buyerkeyMulti;
    
    /** 협력사코드 */
    @Schema(description = "협력사코드", example = "")
    private String custkey;
    
    /** 협력사코드 */
	@MultiSearch
	@Schema(description = "협력사코드-다중OR검색", example = "")
	private List<String> custkeyMulti;

    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String storagetype;
    
    /** 저장조건 */
	@MultiSearch
	@Schema(description = "저장조건-다중OR검색", example = "")
	private List<String> storagetypeMulti;
    
    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;
    
    /** 상품코드 */
	@MultiSearch
	@Schema(description = "상품코드-다중OR검색", example = "")
	private List<String> skuMulti;
    
    /** 발주구분 */
    @Schema(description = "발주구분", example = "")
    private String purchasetype;
    
    /** 발주구분리스트 */
    @Schema(description = "발주구분리스트", example = "")
    private List<String> typeList;
    
}
