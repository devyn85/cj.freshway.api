package cjfw.wms.sys.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.07.17 
 * @description : 프로시저실행로그 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "실행로그 마스터 조회 요청")
public class SysExecuteLogReqDto extends CommonDto {
	/** 고객사코드 */
	@Schema(description = "실행일자", nullable = false, example = "20250716")
	private String executeDt;
	
	/** 코드리스트 */
	@Schema(description = "오브젝트명", example = "")
	private String objectName;
	
	/** 기본코드값 */
	@Schema(description = "처리명령", example = "")
	private String command;
	
	/** 기본코드설명 */
	@Schema(description = "작업자", example = "")
	private String worker;
	
	/** 기본코드설명 */
	@Schema(description = "db spid", example = "")
	private String spid;
	
	/** 기본코드설명 */
	@Schema(description = "요청메세지", example = "")
	private String requestMsg;
	
	/** 에러 여부 */
	@Schema(description = "에러 여부", example = "1")
	private Integer error;

	
}