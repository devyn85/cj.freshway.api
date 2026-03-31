package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.15 
 * @description : 사용자별 권합그롭코드 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.15 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "사용자별 권합그롭코드 응답 DTO")
public class CmUserRoleResDto {
	/** 권한그룹코드 */
    @Schema(description = "권한그룹코드", example = "LOGISONE_00")
    private String authCd;
}