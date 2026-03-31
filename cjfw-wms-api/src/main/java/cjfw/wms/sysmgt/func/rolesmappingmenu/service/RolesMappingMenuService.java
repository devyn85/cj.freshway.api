/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.rolesmappingmenu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sysmgt.func.roles.entity.RolesEntity;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuCopyReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuGetReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuGetResDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.dto.RolesMappingMenuSaveReqDto;
import cjfw.wms.sysmgt.func.rolesmappingmenu.entity.RolesMappingMenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesMappingMenuService {
	
	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * 권한메뉴 리스트 조회
	 */
	public List<RolesMappingMenuGetResDto> getRolesMappingMenuList(RolesMappingMenuGetReqDto rolesMappingMenuGetReqDto) {
		return commonDao.selectList("rolesMappingMenuService.getRolesMappingMenuList", rolesMappingMenuGetReqDto);
	}

	/**
	 * 권한메뉴 저장(수정)
	 */
	@CacheEvict(value = "authenticatedMenu", allEntries=true)
	public String saveRolesMappingMenus(RolesMappingMenuSaveReqDto rolesMappingMenuSaveReqDto) {
		List<RolesMappingMenuSaveReqDto.RoleMenu> roleMenus = rolesMappingMenuSaveReqDto.getRoleMenus();
		if(roleMenus != null){
			for(RolesMappingMenuSaveReqDto.RoleMenu roleMenu: roleMenus){
				RolesMappingMenuEntity roleMenuEntity = ModelMapperUtil.map(roleMenu, userContext, RolesMappingMenuEntity.class);
				log.info("{}", roleMenuEntity);
				if((CanalFrameConstants.UPDATE).equals(roleMenu.getRowStatus())) {
					commonDao.delete("rolesMappingMenuService.deleteRolesMappingMenu", roleMenuEntity); // 삭제
					commonDao.insert("rolesMappingMenuService.insertRolesMappingMenu", roleMenuEntity); //추가
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

	/**
	 * 권한 메뉴 복사
	 */
	public String saveCopyRolesMappingMenus(RolesMappingMenuCopyReqDto rolesMappingMenuCopyReqDto) {

		// 복사
		RolesEntity roleEntity = ModelMapperUtil.map(rolesMappingMenuCopyReqDto, userContext, RolesEntity.class);
		roleEntity.setAuthority(rolesMappingMenuCopyReqDto.getAuthCd());
		roleEntity.setRoleNm(rolesMappingMenuCopyReqDto.getAuthNm());
		commonDao.insert("rolesService.copyRoles", roleEntity);

		Map copyParam = new HashMap();
		copyParam.put("authority", rolesMappingMenuCopyReqDto.getAuthority());
		copyParam.put("authCd", rolesMappingMenuCopyReqDto.getAuthCd());
		commonDao.insert("rolesMappingMenuService.copyRolesMappingMenu", copyParam);

		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}