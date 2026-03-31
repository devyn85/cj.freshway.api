package cjfw.wms.kp.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 박요셉 (dytpq362@cj.net)   
 * @date : 2025.12.18
 * @description : 공정별생산성 Raw 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.18 박요셉 (dytpq362@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "공정별생산성 Raw 결과")
public class KpProcessResultResTab3Dto {
	@Schema(description = "id")
    private String id;
	
	@Schema(description = "물류센터명")
    private String dccode;

    @Schema(description = "스캔대상")
    private String label;

    @Schema(description = "라벨값")
    private String barcode;
    
    @Schema(description = "출고물류센터명")
    private String wddccode;
    
    @Schema(description = "저장유무")
    private String channel;

    @Schema(description = "저장조건")
    private String storagetype;

    @Schema(description = "물량")
    private String qty;
    
    @Schema(description = "상태값")
    private String proc;
    
    
    @Schema(description = "생산성측정")
    private String status;

    @Schema(description = "상세정보")
    private String dtlinfo;
    
    @Schema(description = "스캔시간")
    private String scandate;

    @Schema(description = "주문삭제")
    private String delYn;

    @Schema(description = "출고일자")
    private String slipdt;
    


}
