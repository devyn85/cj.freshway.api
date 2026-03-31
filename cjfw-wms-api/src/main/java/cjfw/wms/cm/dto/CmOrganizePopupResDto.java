package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.22 
 * @description : 창고 정보 조회 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.22 SangSuSung(kduimux@cj.com) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량+권역 팝업 조회 Response DTO")
public class CmOrganizePopupResDto {
	/** 창고코드 */
	@Schema(description = "창고코드", nullable = false, example = "")
	private String code;
	
	/** 창고명 */
	@Schema(description = "창고명", nullable = false, example = "")
	private String name;
}
