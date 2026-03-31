package cjfw.wms.sysmgt.func.ipallow.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * IP허용예외 Entity(테이블: TB_CF_ALLOWED_IP)
 */
@Data
public class IpAllowEntity {
    private String userId;
    private String ipAddr;
    private String reason;
    private String regId;
    private LocalDateTime regDt;
}
