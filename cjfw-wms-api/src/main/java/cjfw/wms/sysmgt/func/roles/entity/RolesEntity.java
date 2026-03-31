package cjfw.wms.sysmgt.func.roles.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 권한 Entity(테이블: TB_CF_ROLES)
 */
@Data
public class RolesEntity {
    private String authority; // 권한코드
    private String roleNm; // 권한명
    private String description; // 설명
    private String regId;
    private LocalDateTime regDt;
}
