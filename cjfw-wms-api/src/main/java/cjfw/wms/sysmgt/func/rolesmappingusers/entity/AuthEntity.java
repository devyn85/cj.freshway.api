package cjfw.wms.sysmgt.func.rolesmappingusers.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 권한 Entity(테이블: TO_FO_AUTH)
 */
@Data
public class AuthEntity {
    private String userId;
    private String authority;
    private String regId;
    private LocalDateTime regDt;
}
