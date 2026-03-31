package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonTriggerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : ParkYosep (dytpq362@cj.net)
 * @date        : 2026.01.14
 * @description : 출고 데이터 결산 마스터 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.14        ParkYosep (dytpq362@cj.net)       생성
 */
@Data
@Schema(description = " 출고 데이터 결산 마스터 Entity") 
public class MsCostCenterCtgyInfoEntity extends CommonTriggerDto {	
	@Schema(description = "고객코드")
	private String custkey;
	
    @Schema(description = "적용월")
    private String applyYm;

    @Schema(description = "사업장코드")
    private String deptCd;

    @Schema(description = "사업장명")
    private String deptNm;

    @Schema(description = "조직계층명")
    private String hierachyNm;

    @Schema(description = "조직대분류명")
    private String lclNm;

    @Schema(description = "조직중분류명")
    private String mclNm;

    @Schema(description = "조직소분류명")
    private String sclNm;

    @Schema(description = "최초등록일시")
    private String adddate;

    @Schema(description = "최종수정일시")
    private String editdate;

    @Schema(description = "최초등록자(ID)")
    private String addwho;

    @Schema(description = "최종수정자(ID)")
    private String editwho;


//    
    @Schema(description = "고객명")
    private String description;

    @Schema(description = "주출고처")
    private String dlvDccode;

    @Schema(description = "군납여부(Y/N)")
    private String armyYn;
    
    @Schema(description = "상품소분류코드")
    private String sclCd;


//    
    
    @Schema(description = "상품코드")
    private String sku;

    @Schema(description = "상품명")
    private String skuname;

    @Schema(description = "전용구분여부")
    private String dedicYn;
    
    @Schema(description = "에러메세지")
    private String errMsg;
    
    @Schema(description = "등록여부")
    private String existYn;
}
