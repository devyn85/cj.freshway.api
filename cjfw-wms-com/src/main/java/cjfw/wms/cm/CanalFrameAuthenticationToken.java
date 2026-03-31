package cjfw.wms.cm;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class CanalFrameAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String verificationYn;
	
	private String ssoLoginYn;

    public CanalFrameAuthenticationToken(Object principal, Object credentials, String verificationYn, String ssoLoginYn) {
        super(principal, credentials);
        this.verificationYn = verificationYn;
        this.ssoLoginYn = ssoLoginYn;
    }

    public CanalFrameAuthenticationToken(Object principal, Object credentials, String verificationYn, String ssoLoginYn,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.verificationYn = verificationYn;
        this.ssoLoginYn = ssoLoginYn;
    }

    public String getVerificationYn() {
        return verificationYn;
    }
    
    public String getSsoLoginYn() {
        return ssoLoginYn;
    }

}
