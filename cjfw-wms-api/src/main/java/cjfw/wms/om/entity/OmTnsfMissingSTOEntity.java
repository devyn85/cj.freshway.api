package cjfw.wms.om.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.31 
 * @description : 당일광역보충발주 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmTnsfMissingSTOEntity extends CommonDto {

	/** 공급센터 */
    @Schema(description = "공급센터", example = "")
    private String dccode;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;

    /** 발주량(센터) */
    @Schema(description = "발주량(센터)", example = "")
    private String orderDccode;

    /** 발주량(발주량) */
    @Schema(description = "발주량(발주량)", example = "")
    private String supplyqty;

    /** 대응여부 */
    @Schema(description = "대응여부", example = "")
    private String respYn;
    
    /** 원_문서일자 */
    @Schema(description = "원_문서일자", example = "")
    private String orgDocdt;

    /** 원_문서번호 */
    @Schema(description = "원_문서번호", example = "")
    private String orgDocno;

    /** 원_문서라인 */
    @Schema(description = "원_문서라인", example = "")
    private String orgDocline;
	
    /** 회차 */
    @Schema(description = "회차", example = "")
    private String priority;
    
    /** 회차 */
    @Schema(description = "회차", example = "")
    private String fromDccode;
}