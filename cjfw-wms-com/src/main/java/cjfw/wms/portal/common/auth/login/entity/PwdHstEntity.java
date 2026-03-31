package cjfw.wms.portal.common.auth.login.entity;

import java.util.Date;
import lombok.Data;

/**
 *  비밀번호 이력 Entity(테이블: TB_CF_PWD_HST)
 */
@Data
public class PwdHstEntity {
	private Date pwdChgDt;
    private String userId;
    private Integer userPwdSeq;
    private String userPwd;
    private String regId;
    private Date regDt;
    private String modId;
    private Date modDt;
}
