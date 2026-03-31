package cjfw.wms.kp.dto;

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
 *
 * @author :ParkYosep (dytpq362@cj.net)
 * @date : 2025.12.12
 * @description : 지표/모니터링 > 검수지표 > 상차검수지표 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.12 ParkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "지표/모니터링 > 검수지표 > 상차검수지표 Request DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpWdLoadMonitoringReqDto extends CommonProcedureDto {
    /** 시작일자 */
    @Schema(description = "시작일자")
    private String fromDate;

    /** 종료일자 */
    @Schema(description = "종료일자")
    private String toDate;
	
    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carno;
	
    /** 차량번호 멀티*/
    @MultiSearch
    @Schema(description = "차량번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> carnoMulti;
    
    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String toCustkey;
	
    /** 관리처코드 멀티*/
    @MultiSearch
    @Schema(description = "관리처코드(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> toCustkeyMulti; 
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
	
    /** 상품코드 멀티*/
    @MultiSearch
    @Schema(description = "상품코드(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> skuMulti;    
    
//    /** 협력사코드 */
//    @Schema(description = "협력사코드")
//    private String custkey;
//	
//    /** 협력사코드 멀티*/
//    @MultiSearch
//    @Schema(description = "협력사코드(다중 – 콤마/개행 구분)", nullable = false, example = "")
//    private List<String> custkeyMulti;    
//    
    /** 검수진행상태 */
    @Schema(description = "검수진행상태")
    private String inspectstatus;
    
    
    /** 고객사코드 */
    @Schema(description = "고객사코드", example = "FW00")
    private String storerkey;
    
    /** 물류센터코드 */
    @Schema(description = "물류센터 코드")
    private String dccode;
    
    /** 출고일자 */
	@Schema(description = "출고일자", nullable = false)
	private String slipdt;
    
	/** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;
}	
