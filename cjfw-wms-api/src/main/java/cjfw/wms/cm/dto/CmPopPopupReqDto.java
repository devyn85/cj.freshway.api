package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.13 
 * @description : POP조회 팝업 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "POP조회 팝업 DTO")
public class CmPopPopupReqDto extends CommonDto {
	@Schema(description = "물류센터코드", nullable = false, example="")
	private String dcCode;
	
	@Schema(description = "다중 선택", example="")
	private String multiSelect;
	
	@Schema(description = "999개 청크 목록(OR IN 생성용)")
	private java.util.List<java.util.List<String>> codeGroups;
	
	@Schema(description = "POP 번호", example="")
	private String popCode;
	
	private String keywordEng;
    private String keywordRegexp;
    private String keywordEngRegexp;
}
