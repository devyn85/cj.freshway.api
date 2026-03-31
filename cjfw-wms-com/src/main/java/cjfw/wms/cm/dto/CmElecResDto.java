package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.22 
 * @description : 전자결재 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "전자결재 응답 DTO")
public class CmElecResDto {
	/** 전자결재 html */
	@Schema(description = "전자결재 html", example = "")
	private String elecHtml;
	
	/** 제목 */
	@Schema(description = "제목", example = "")
	private String elecTitle;
	
	/** 결재 가능 여부 */
	@Schema(description = "결재 가능 여부", example = "")
	private String approvalAvailableYN;
	
	/** 에러메시지 */
	@Schema(description = "에러메시지", example = "")
	private String errorMessage;
}