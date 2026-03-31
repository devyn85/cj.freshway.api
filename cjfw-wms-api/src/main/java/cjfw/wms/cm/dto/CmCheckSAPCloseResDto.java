package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.02 
 * @description : SAP 마감 체크 조회 결과 DTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "SAP 마감상태 조회 응답")
public class CmCheckSAPCloseResDto {
	/** 응답 결과 코드 */
	@Schema(description = "응답 결과 코드", example = "")
	private String result;
	
	/** 응답 에러 메시지 */
	@Schema(description = "응답 에러 메시지", example = "")
	private String errorMsg;

}