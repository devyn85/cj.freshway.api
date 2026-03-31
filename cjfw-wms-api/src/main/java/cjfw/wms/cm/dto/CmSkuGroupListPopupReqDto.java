package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.05.19 
 * @description : 세분류팝업 기능을 구현한 reqDto  
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.19 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품그룹 2조회")
public class CmSkuGroupListPopupReqDto extends CommonDto{
	

//    @Schema(description = "고객사코드",  example = "FW00")
//    private String storerKey;

    @Schema(description = "스펙 클래스",  example = "MC")
    private String specClass;
    
	@Schema(description = "다중 선택", example = "")
	private String multiSelect;

	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;

    @Schema(description = "검색어", example = "10010101")
    private String searchVal;

    @Schema(description = "명칭", example = "")
    private String name;
}
	


