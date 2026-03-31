/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.main.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.portal.common.main.dto.GrpCommCodeListGetResDto;
import cjfw.wms.portal.common.main.dto.LoginHistoryGetResDto;
import cjfw.wms.portal.common.main.dto.MainMenuGetResDto;
import cjfw.wms.portal.common.main.dto.MainUserGetResDto;
import cjfw.wms.portal.common.main.dto.SiteMapMenuGetResDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuDeleteReqDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuGetResDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuInsertReqDto;
import cjfw.wms.portal.common.main.entity.UserMyMenuEntity;
import cjfw.wms.portal.common.main.entity.UserMyMenuEntityMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OldMainService {
	
//	private static final String DS_USERSMYMENU = "ds_usersMyMenu";
	
	private static final String USER_ID = "userId";
	
	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * 로그인 사용자 정보 조회
	 */
	public MainUserGetResDto getCurrentUser() {
		return commonDao.selectOne("oldMainService.getCurrentUser", userContext);
	}

	/**
	 * 권한에 따른 메뉴 리스트 조회
	 */
	public List<MainMenuGetResDto> getMenuRoleMainViewList(){
		Map param = new HashMap();
		param.put("roles", userContext.getRoleList());
		param.put("locale", userContext.getLocaleString());
		List<MainMenuGetResDto> list = commonDao.selectList("oldMainService.getMenuRoleMainViewList", param);
		return list;
	}

	/**
	 * SiteMap 메뉴 리스트 조회
	 */
	public List<SiteMapMenuGetResDto> getSiteMapMenulist() {
		Map param = new HashMap();
		param.put("roles", userContext.getRoleList());
		param.put("locale", userContext.getLocaleString());
		param.put("userId", userContext.getUserId());

		List<SiteMapMenuGetResDto> list = commonDao.selectList("oldMainService.getSiteMapMenuList", param);
		return list;
	}
	/**
	 * 즐겨찾기 메뉴를 조회한다.<br>
	 */
	public List<UserMyMenuGetResDto> getUsersMyMenuList() {
		String userId = userContext.getUserId();
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(USER_ID, userId);
		
		return commonDao.selectList("oldMainService.getUsersMyMenuList", paramMap);
	}
	
	/**
	 * 즐겨찾기 메뉴를 추가한다.<br>
	 */
	public String insertUsersMyMenu(UserMyMenuInsertReqDto userMyMenuInsertReqDto) {
		UserMyMenuEntity userMyMenuEntity = UserMyMenuEntityMapper.INSTANCE.insertMyMenuDtoToEntity(userMyMenuInsertReqDto, userContext);

		int cnt = commonDao.selectOne("oldMainService.getUsersMyMenu", userMyMenuEntity);
		if(cnt > 0) {
			//이미 BookMark에 등록된 메뉴입니다.
			return "MSG.COM.ERR.047";
		}
		
		commonDao.insert("oldMainService.insertUsersMyMenu", userMyMenuEntity);
		//추가 되었습니다.
		return "MSG.COM.SUC.002";
		
	}
	
	/**
	 * 즐겨찾기 메뉴를 삭제한다.<br>
	 */
	public String deleteUsersMyMenu(UserMyMenuDeleteReqDto userMyMenuDeleteReqDto) {
		UserMyMenuEntity userMyMenuEntity = UserMyMenuEntityMapper.INSTANCE.deleteMyMenuDtoToEntity(userMyMenuDeleteReqDto, userContext);

		int cnt = commonDao.selectOne("oldMainService.getUsersMyMenu", userMyMenuEntity);
		if(cnt < 1) {
			//삭제할 대상이 없습니다.
			return "MSG.COM.VAL.043";
		}
		
		commonDao.insert("oldMainService.deleteUsersMyMenu", userMyMenuEntity);
		//삭제 되었습니다.
		return "MSG.COM.SUC.006";
	}
	
	/**
	 * 로그인이력을 조회한다.<br>
	 */
	public List<LoginHistoryGetResDto> getLoginLogOutList() {
		String userId = userContext.getUserId();
		
		Map<String, String> paramMap = new HashMap<>();
		
		paramMap.put(USER_ID, userId);
		
		return commonDao.selectList("oldMainService.getLoginLogOutList", paramMap);
	}
	
	/**
	 * 최근 로그인이력을 조회한다.<br>
	 */
	public List<LoginHistoryGetResDto> getLatestLogin() {
		String userId = userContext.getUserId();
		
		Map<String, String> paramMap = new HashMap<>();
		
		paramMap.put(USER_ID, userId);
		
		return commonDao.selectList("oldMainService.getLatestLoginHist", paramMap);
	}

	/**
	 * 멀티 그룹을 파라메타로 받아서 코드그룹명 공통코드를 조회
	 */
	public List<GrpCommCodeListGetResDto> getGrpCommCodeList(List<String> grpCdList){
		Map param = new HashMap();
		param.put("grpCdList", grpCdList);
		param.put("locale", userContext.getLocaleString());
		List<GrpCommCodeListGetResDto.CommCode> commCodeList = commonDao.selectList("oldMainService.getGrpCommCodeList", param);

		List<GrpCommCodeListGetResDto> result = new ArrayList<>();
		GrpCommCodeListGetResDto resDto = null;
		for(GrpCommCodeListGetResDto.CommCode commCode: commCodeList){
			if(resDto == null || !resDto.getComGrpCd().equals(commCode.getComGrpCd())){
				resDto = new GrpCommCodeListGetResDto();
				resDto.setComGrpCd(commCode.getComGrpCd());
				resDto.setCommCodes(new ArrayList<>());
				result.add(resDto);
			}
			resDto.getCommCodes().add(commCode);
		}

		return result;
	}

}
