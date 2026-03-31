package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 박요셉 (dytpq362@cj.net)  
 * @date : 2025.12.18
 * @description : 공정별생산성 작업자별 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.18 박요셉 (dytpq362@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "공정별생산성 작업자별 결과")
public class KpProcessResultResTab2Dto {
	@Schema(description = "id")
    private String id;
	
	@Schema(description = "물류센터명")
    private String dccode;

    @Schema(description = "출고물류센터명")
    private String wddccode;

    @Schema(description = "공정")
    private String proc;

    @Schema(description = "저장유무")
    private String channel;

    @Schema(description = "저장조건")
    private String storagetype;

    @Schema(description = "인시생산성(라벨)")
    private String hcnt1;

    @Schema(description = "인시생산성(물량)")
    private String hqty;
    
    @Schema(description = "업무시간")
    private String difftm;

    @Schema(description = "이동횟수")
    private String cnt4;

    @Schema(description = "라벨건수")
    private String cnt1;
    
    @Schema(description = "물량")
    private String qty;
    
    @Schema(description = "출고일자")
    private String slipdt;

}
