package cjfw.wms.sysmgt.func.roles.dto;

import lombok.Data;

/**
 * 권한 조회 Response DTO
 */
@Data
public class RolesGetResDto {
    private String authority; // 권한코드
    private String roleNm; // 권한명
    private String description; // 설명
    private String regId;
    private String regDt;
}

/** API 샘플 예시
 "data": [
     {
         "authority": "BBS_ADMIN",
         "roleNm": "공지사항 관리자",
         "description": "공지사항 관리 권한",
         "regId": "admin01",
         "regDt": "2022-05-23"
     },
 ...
 */


/** MPA 참조
 AUTHORITY: "BBS_ADMIN"
 DESCRIPTION: "공지사항 관리 권한"
 REG_DT: "2022-05-23"
 REG_ID: "admin01"
 ROLE_NM: "공지사항 관리자"
 */
