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
 * @date : 2025.05.15 
 * @description : 그룹권한 조회 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "그룹권한 조회 요청 DTO")
public class SysAuthorityReqDto extends CommonDto {
	/** 시스템구분 */
	@Schema(description = "시스템구분", example = "LOGISONE")
	private String systemCl;
	
	/** 권한그룹코드 */
	@Schema(description = "권한그룹코드", example = "LOGISONE_00")
	private String authCd;
	
	/** 상위권한그룹코드 */
	@Schema(description = "상위권한그룹코드", example = "LOGISONE")
	private String upAuthGroupCd;
	
	/** 하위권한그룹여부 */
	@Schema(description = "하위권한그룹여부", example = "Y")
	private String lowAuthYn;
	
	/** 권한그룹 목록 */
	@Schema(description = "권한그룹 목록", example = "")
	private List<SysAuthorityGrpReqDto> authGrpList;
	
	/** 권한상세 목록 */
	@Schema(description = "권한상세 목록", example = "")
	private List<SysAuthorityDtlReqDto> authDtlList;
}
