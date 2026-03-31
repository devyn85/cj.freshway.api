package cjfw.wms.sysmgt.func.users.dto;

import lombok.Data;

/**
 * 사용자 조회 응답 DTO
 */
@Data
public class UsersGetResDto {
    private String userId;
    private String userNm;
    private String userStatus;
    private String userEnable;
    private String regId;
    private String regDt;
    private String empNo;
    private String mailAddr;
}
