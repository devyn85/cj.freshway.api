package cjfw.wms.ms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.12.08
 * @description : 마감 출고데이터 마스터 - 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "마감 출고데이터 마스터 요청 DTO")
public class MsCostCenterCtgyInfoReqDto extends CommonDto {
    /** 저장리스트 */
   @Schema(description = "저장리스트")
   List<MsCostCenterCtgyInfoResDto> saveList;
   
    @Schema(description = "고객코드")
    private String custkey;
    
    @MultiSearch
    @Schema(description = "고객코드 멀티")
    private List<String> custkeyMulti;
    
	@Schema(description = "적용월")
	private String applyYm;
	
	@Schema(description = "사업부")
	private String deptNm;
	
	@Schema(description = "대분류")
	private String lclNm;
	
	@Schema(description = "중분류")
	private String mclNm;
	
	@Schema(description = "소분류")
    private String sclNm;

    @Schema(description = "업무부코드")
    private String deptCd;

    @Schema(description = "경로/업종")
    private String hierachyNm;
    
    @Schema(description = "군납여부")
    private String armyYn;
    
    @Schema(description = "상품코드")
    private String sku;
    
    @MultiSearch
    @Schema(description = "상품코드 멀티")
    private List<String> skuMulti;
    
    
    @Schema(description = "미곡여부")
    private String riceYn;
}
