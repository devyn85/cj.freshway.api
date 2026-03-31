package cjfw.wms.sys.dto;

import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.08 
 * @description : 권한그룹별 프로그램 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "권한그룹별 프로그램 요청 DTO")
public class SysAuthorityProgramReqDto extends CommonDto {
	/** 시스템구분 */
	@Schema(description = "시스템구분", example = "LOGISONE")
	private String systemCl;
	
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 프로그램 목록 */
	@Schema(description = "프로그램 목록", example = "")
	private List<SysAuthorityProgramResDto> authProgramList;
}
