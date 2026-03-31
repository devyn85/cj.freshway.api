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
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.12.26
 * @description : 입고검수현황 조회 요청 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "입고검수현황 조회 요청")
public class KpDpInspectMonitoringReqDto extends CommonProcedureDto {
    /** 저장리스트 */
    @Schema(description = "저장리스트")
    private List<KpDpInspectMonitoringResDto> saveList;
    
    /** 저장리스트 */
    @Schema(description = "저장리스트")
    private List<KpDpInspectMonitoringReqDto> reqList;
    
    /** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
	
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;

	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> fromcustkeyMulti;

    @Schema(description = "상품유형")
    private String storagetype;

    @Schema(description = "시작일자")
    private String fromSlipdt;

    @Schema(description = "종료일자")
    private String toSlipdt;
    
    @Schema(description = "저장유무")
    private String channel;

    @Schema(description = "결과구분(10:스캔중, 20:스캔완료)")
    private String result;

    @Schema(description = "미완료여부")
    private String undoneyn;
    
    @Schema(description = "일자")
    private String slipdt;
    
    @Schema(description = "")
    private String dccode; 
    
    @Schema(description = "PDP날짜")
    private String eventdt; 
}