/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.auth.common.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;

@Service
@RequiredArgsConstructor
public class CJSecurityRulesService {

	private final CommonDao commonDao;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUser(Map<String, String> userMap){
		Map<String, Object> user = (Map<String, Object>)commonDao.selectOne("cJSecurityRulesService.getUser", userMap);
		return user;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserById(Map<String, String> userMap){
		Map<String, Object> user = (Map<String, Object>)commonDao.selectOne("cJSecurityRulesService.getUserById", userMap);
		return user;
	}

	public List<Object> getUserAllowedIpList(Map<String, String> userMap){
		List<Object> user = commonDao.selectList("cJSecurityRulesService.getUserAllowedIpList", userMap);
		return user;
	}

	public List<Object> getUserRolesList(Map<String, String> userMap){
		List<Object> user = commonDao.selectList("cJSecurityRulesService.getUserRolesList", userMap);
		return user;
	}

	public void insertUsersLog(Map<String, String> inputData){
		commonDao.insert("cJSecurityRulesService.insertUsersLog", inputData);
	}

	public void insertLoginNewTx(Map<String, String> loginMap){
		commonDao.insert("cJSecurityRulesService.insertLogin", loginMap);
	}

	public void insertLogoutNewTx(Map<String, String> logoutMap){
		commonDao.insert("cJSecurityRulesService.insertLogOut", logoutMap);
	}

	public void updateSecurityRules(Map<String, String> inParams){
		commonDao.update("cJSecurityRulesService.updateSecurityRules", inParams);
	}

	@Cacheable(value = "authenticatedMenu", key = "#paramMap")
	public Map<String, String> getAuthenticatedMenuByUri(Map<String, Object> paramMap){
		Map<String, String> menu = (Map<String, String>)commonDao.selectOne("cJSecurityRulesService.getAuthenticatedMenuList", paramMap);
		return menu;
	}
	
	@Cacheable(value = "authenticatedMenu", key = "#paramMap")
	public Map<String, String> getAuthenticatedMenu(Map<String, Object> paramMap){
		Map<String, String> menu = (Map<String, String>)commonDao.selectOne("cJSecurityRulesService.getAuthenticatedMenu", paramMap);
		return menu;
	}

	public void updateUserRefreshToken(Map<String, String> inParams){
		commonDao.update("cJSecurityRulesService.updateUserRefreshToken", inParams);
	}
	
	public void updateLockUserId(Map<String, String> inParams){
		commonDao.update("cJSecurityRulesService.updateLockUserId", inParams);
	}

	public void updateUnlockUserId(Map<String, String> inParams){
		commonDao.update("cJSecurityRulesService.updateUnlockUserId", inParams);
	}

	public Map<String, String> getUserRefreshToken(Map<String, String> inParams){
		Map<String, String> user = (Map<String, String>)commonDao.selectOne("cJSecurityRulesService.getUserRefreshToken", inParams);
		return user;
	}
	
	public Map<String, String> getSpid(Map<String, String> inParams) {
		return (Map<String, String>)commonDao.selectOne("cJSecurityRulesService.getSpid", inParams);
	}

}
