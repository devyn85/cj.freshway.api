package cjfw.wms.dev.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.08 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.08 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper=true) 
@Builder
public class DevPilot01ReqDto extends CommonDto {
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품코드 다중선택 */
	private String multiSku;
	
	/** 코드리스트 */
    private String[] codeList;
}
