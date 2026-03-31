package cjfw.wms.sysmgt.func.users.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 사용자 Entity(테이블: TB_CF_USERS)
 */
@Data
public class UsersEntity {
    private String userId;
    private String userNm;
    private String userPwd;
    private String userStatus; // "01"(사용), "02"(잠금상태), "99"(사용안함),
    private String userEnable;
    private String empNo;
    private String mailAddr;
    private String regId;
    private LocalDateTime regDt;
    private String modId;
    private LocalDateTime modDt;

    // 제거 검토 컬럼
    // private String comCd;
    // priavte String langCd;

}
