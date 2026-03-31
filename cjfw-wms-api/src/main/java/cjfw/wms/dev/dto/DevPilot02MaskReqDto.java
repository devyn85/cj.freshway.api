package cjfw.wms.dev.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.06.14 
 * @description : 마스킹 처리를 하는 등답 dto 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.14 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@NoArgsConstructor
@Schema(description = "마스킹 목록 응답") 
public class DevPilot02MaskReqDto extends CommonDto { 

	/** 사원번호 */
	@Schema(description = "사원번호", nullable = false, example = "")
	private String empNo;
	
}