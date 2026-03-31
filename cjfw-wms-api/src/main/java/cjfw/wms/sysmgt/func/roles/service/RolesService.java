/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.roles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sysmgt.func.roles.dto.RolesGetReqDto;
import cjfw.wms.sysmgt.func.roles.dto.RolesGetResDto;
import cjfw.wms.sysmgt.func.roles.dto.RolesSaveReqDto;
import cjfw.wms.sysmgt.func.roles.dto.UserRolesGetReqDto;
import cjfw.wms.sysmgt.func.roles.dto.UserRolesGetResDto;
import cjfw.wms.sysmgt.func.roles.entity.RolesEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesService {
	
	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * 권한 리스트 조회
	 */
	public List<RolesGetResDto> getRoleList(RolesGetReqDto rolesGetReqDto) {
		return commonDao.selectList("rolesService.getRoleList", rolesGetReqDto);
	}

	/**
	 * 권한 저장(CUD)
	 */
	public String saveRoles(RolesSaveReqDto rolesSaveReqDto) {
		List<RolesSaveReqDto.Role> roles = rolesSaveReqDto.getRoles();
		if(roles != null){
			for(RolesSaveReqDto.Role role: roles){
				RolesEntity roleEntity = ModelMapperUtil.map(role, userContext, RolesEntity.class);
				log.info("{}", roleEntity);
				if((CanalFrameConstants.INSERT).equals(role.getRowStatus())) {
					commonDao.insert("rolesService.insertRoles", roleEntity);
				} else if((CanalFrameConstants.UPDATE).equals(role.getRowStatus())) {
					commonDao.update("rolesService.updateRoles", roleEntity);
				} else if((CanalFrameConstants.DELETE).equals(role.getRowStatus())) {
					commonDao.delete("rolesService.deleteRoles", roleEntity);
					commonDao.delete("rolesMappingMenuService.deleteRolesMappingAllMenus", roleEntity);
					commonDao.delete("rolesMappingUsersService.deleteRolesMappingAllUsers", roleEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


	/**
	 * 사용자 권한 리스트 조회
	 */
	public List<UserRolesGetResDto> getUserRoleList(UserRolesGetReqDto userRolesGetReqDto) {
		return commonDao.selectList("rolesService.getUserRoleList", userRolesGetReqDto);
	}
}