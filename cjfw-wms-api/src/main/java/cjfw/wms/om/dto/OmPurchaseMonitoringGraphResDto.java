package cjfw.wms.om.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14 
 * @description : 저장품발주현황 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
public class OmPurchaseMonitoringGraphResDto {
			
	/** 발주구분 */
    @Schema(description = "발주구분", example = "")
    private String type;
    
    /** 일자 */
    @Schema(description = "일자", example = "")
    private String dt;
    
    /** 물류센터코드 */
    @Schema(description = "물류센터코드", example = "")
    private String dccode;
    
    /** 물류센터별 값 */
    @Schema(description = "물류센터별 값", example = "")
    private String weightValue;

    /** po 물류센터별 값 */
    @Schema(description = "po 물류센터별 값", example = "")
    private String poWeightValue;

    /** sto 물류센터별 값 */
    @Schema(description = "sto 물류센터별 값", example = "")
    private String stoWeightValue;

    /** 물류센터별 값 (동적 필드) */
    private Map<String, String> weights;

}