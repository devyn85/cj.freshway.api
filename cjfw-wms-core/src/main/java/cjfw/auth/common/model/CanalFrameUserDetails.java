/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.auth.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CanalFrameUserDetails implements UserDetails {
 
    private static final long serialVersionUID = -4450269958885980297L;
    
    private String username;
    private String password;
    private List<Map<String,String>> authenticatedMenu = new ArrayList<Map<String,String>>();
    private List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
     
    public CanalFrameUserDetails(String userName, String password)
    {
        this.username = userName;
        this.password = password;
        
    }
     
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
  
    @Override
    public String getPassword() {
        return password;
    }
  
    @Override
    public String getUsername() {
        return username;
    }
  
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
  
    @Override
    public boolean isEnabled() {
        return true;
    }

	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
	}

	public List<Map<String,String>> getAuthenticatedMenu() {
		return authenticatedMenu;
	}

	public void setAuthenticatedMenu(List<Map<String,String>> authenticatedMenu) {
		this.authenticatedMenu = authenticatedMenu;
	}
 }
