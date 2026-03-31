package cjfw.wms.om.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.11.12 
 * @description : 누락분 STO 이체 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OmTnsfMissingSTOReqDto extends CommonDto {
	
	/** 이체일자 */
    @Schema(description = "이체일자", example = "")
    private String deliverydate;
    
    /** 수급센터 */
    @Schema(description = "수급센터", example = "")
    private String toDccode;
	
	/** 공급센터 */
    @Schema(description = "공급센터", example = "")
    private String dccode;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", example = "")
    private List<List<String>> skuMulti;
    
    /** 고객코드 */
    @Schema(description = "고객코드", example = "")
    private String custkey;
    
    /** 문서번호 */
    @Schema(description = "문서번호", example = "")
    private String docno;
    
    /** 상태코드 */
    @Schema(description = "상태코드", example = "")
    private String stoStatus;

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
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String multistorageType[];
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String storageType;
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String fromDccode;
    
    /** 저장조건 */
    @Schema(description = "저장조건", example = "")
    private String fromDcname;


}
