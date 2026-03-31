/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.rolesmappingusers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersGetReqDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersGetResDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.dto.RolesMappingUsersSaveReqDto;
import cjfw.wms.sysmgt.func.rolesmappingusers.entity.AuthChgLogEntity;
import cjfw.wms.sysmgt.func.rolesmappingusers.entity.AuthEntity;
import cjfw.wms.sysmgt.func.users.dto.UsersGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesMappingUsersService{

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * 권한 사용자 페이지 리스트
	 */
	public Page<RolesMappingUsersGetResDto> getRolesMappingUserList(RolesMappingUsersGetReqDto rolesMappingUsersGetReqDto, Page page) {
		Page<RolesMappingUsersGetResDto> result = commonDao.selectPageList("rolesMappingUsersService.getRolesMappingUserList", rolesMappingUsersGetReqDto, page);
		return result;
	}

	/**
	 * 권한 사용자 저장(등록/삭제)
	 */
	public String saveRolesMappingUsers(RolesMappingUsersSaveReqDto rolesMappingUsersSaveReqDto) {
		List<RolesMappingUsersSaveReqDto.RoleUser> roleUsers = rolesMappingUsersSaveReqDto.getRoleUsers();
		if(roleUsers != null){
			for(RolesMappingUsersSaveReqDto.RoleUser roleUser: roleUsers){
				AuthEntity authEntity = ModelMapperUtil.map(roleUser, userContext, AuthEntity.class);
				log.info("{}", authEntity);
				AuthChgLogEntity authChgLogEntity = ModelMapperUtil.map(roleUser, userContext, AuthChgLogEntity.class);
				if("1".equals(roleUser.getInclude())){  // 추가
					commonDao.insert("rolesMappingUsersService.insertRolesMappingUsers", authEntity);
					authChgLogEntity.setContent("ADD");
					commonDao.insert("rolesMappingUsersService.insertRolesMappingUsersLog", authChgLogEntity);
				} else{ // 삭제
					commonDao.delete("rolesMappingUsersService.deleteRolesMappingUsers", authEntity);
					authChgLogEntity.setContent("DELETE");
					commonDao.insert("rolesMappingUsersService.insertRolesMappingUsersLog", authChgLogEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * 사용자 리스트 조회
	 */
	public Page<UsersGetResDto> getUserList(RolesMappingUsersGetReqDto rolesMappingUsersGetReqDto) {
		Page page = new Page();
		page.setListCount(rolesMappingUsersGetReqDto.getListCount());
		page.setStartRow(rolesMappingUsersGetReqDto.getStartRow());
		rolesMappingUsersGetReqDto.setUserEnable("1");
		Page<UsersGetResDto> list = commonDao.selectPageList("usersService.getUserList", rolesMappingUsersGetReqDto, page);

		return list;
	}
}