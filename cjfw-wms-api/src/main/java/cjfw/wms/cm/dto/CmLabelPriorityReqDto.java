package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.18 
 * @description : 프로그램별 그리드 정열 및 출력물 정열 공통 기능 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 sss (kduimux@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로그램별 그리드 정열 및 출력물 정열 공통 기능 요청")
public class CmLabelPriorityReqDto extends CommonDto {
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;		
	
	/** 출력순서  */
	@Schema(description = "출력순서")
	private String printOrder;
	
	/** usePgm */
	@Schema(description = "usePgm")
	private String usePgm;
	
	/* 출력명칭 */
	@Schema(description = "출력명칭")
	private String prtNm;
	
    /**
     * prdOrd1
     */
    @Schema(description = "prdOrd1", example = "")
    private String prdOrd1;
    
    /**
     * prdOrd2
     */
    @Schema(description = "prdOrd2", example = "")
    private String prdOrd2;
    
    /**
     * prdOrd3
     */
    @Schema(description = "prdOrd3", example = "")
    private String prdOrd3;
    
    /**
     * prdOrd4
     */
    @Schema(description = "prdOrd4", example = "")
    private String prdOrd4;
    
    /**
     * prdOrd5
     */
    @Schema(description = "prdOrd5", example = "")
    private String prdOrd5;
    
    /**
     * prdOrd6
     */
    @Schema(description = "prdOrd6", example = "")
    private String prdOrd6;
    
    /**
     * prdOrd7
     */
    @Schema(description = "prdOrd7", example = "")
    private String prdOrd7;
    
    /**
     * prdOrd8
     */
    @Schema(description = "prdOrd8", example = "")
    private String prdOrd8;   	
	
}
