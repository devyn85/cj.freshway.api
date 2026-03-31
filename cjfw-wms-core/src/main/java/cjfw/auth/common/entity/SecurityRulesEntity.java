package cjfw.auth.common.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * SecurityRules Entity(테이블: TB_CF_SECURITY_RULES)
 */
@Data
public class SecurityRulesEntity {

    private String userId;
    private LocalDateTime finalInDtm;
    private LocalDateTime finalOutDtm;
    private String pwdChgDt;
    private String finalIpAddr;
    private Integer failInCnt;
    private Integer failTotCnt;
    private LocalDateTime finalFailInDtm;
    private String tmpPwdYn;
    private String allowAllIpYn; // "1":허용, "0":허용안함
}
