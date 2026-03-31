package cjfw.wms.sysmgt.func.roles.dto;

import lombok.Data;

/**
 * 사용자 권한 조회 Response DTO
 */
@Data
public class UserRolesGetResDto {
    private String authority; 	// 권한코드
    private String roleNm; 		// 권한명
    private String description; // 설명
    private String userId; 		// 사용자 ID
    private String include; 	// 사용자권한 매핑(포함) 여부(1 or 0)
    private String regId;
    private String regDt;
}

/** API 샘플
 "data": [
     {
         "authority": "BBS_ADMIN",
         "roleNm": "공지사항 관리자",
         "description": "공지사항 관리 권한",
         "userId": "cxadmin",
         "include": "1",
         "regId": "jws",
         "regDt": "2022-05-23"
     },
 ...
 */
