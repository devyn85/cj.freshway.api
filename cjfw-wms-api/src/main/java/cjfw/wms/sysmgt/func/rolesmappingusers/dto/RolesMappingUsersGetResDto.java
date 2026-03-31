package cjfw.wms.sysmgt.func.rolesmappingusers.dto;

import lombok.Data;

/**
 * 권한사용자 조회 Response DTO
 */
@Data
public class RolesMappingUsersGetResDto {
    private String authority; 	// 권한코드
    private String userId; 		// 사용자ID
    private String userNm; 		// 사용자명
    private String include;  	// 권한 포함 여부(1 or 0)
    private String regId;
    private String regDt;
}
