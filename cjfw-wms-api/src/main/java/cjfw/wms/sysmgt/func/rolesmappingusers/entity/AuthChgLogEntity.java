package cjfw.wms.sysmgt.func.rolesmappingusers.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 권한변경로그 Entity(테이블: TO_FO_AUTH_CHG_LOG)
 */
@Data
public class AuthChgLogEntity {
    private String userId;
    private String authority;
    private String content;
    private String regId;
    private LocalDateTime regDt;
}
