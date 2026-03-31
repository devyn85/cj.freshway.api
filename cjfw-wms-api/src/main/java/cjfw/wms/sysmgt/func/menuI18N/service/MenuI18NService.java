/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.menuI18N.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.menuI18N.dto.MenuI18NGetReqDto;
import cjfw.wms.sysmgt.func.menuI18N.dto.MenuI18NSaveReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuI18NService {
	
	private final CommonDao commonDao;

	private final UserContext userContext;

	private static final String LANG_KO_KR = "ko_kr";

	/**
	 * 메뉴 다국어 리스트 조회
	 */
	public List<Map> getMenuI18NList(MenuI18NGetReqDto menuI18NGetReqDto) {
		List<String> langCodeList = commonDao.selectList("commonCodeService.getUseLangCdList");

		Map param = new HashMap();
		param.put("menuId", menuI18NGetReqDto.getMenuId());
		param.put("menuNm", menuI18NGetReqDto.getMenuNm());
		param.put("codeList", langCodeList);
		return commonDao.selectList("menuI18NService.getMenuI18NList", param);
	}


	/**
	 * 메뉴 다국어 저장
	 */
	public String saveMenuI18N(MenuI18NSaveReqDto menuI18NSaveReqDto){

		List<String> langCodeList = commonDao.selectList("commonCodeService.getUseLangCdList");

		List<Map> menuI18Ns = menuI18NSaveReqDto.getMenuI18Ns();
		if(menuI18Ns != null) {
			for (Map menuI18N : menuI18Ns) {
				for(String langCode: langCodeList){
					menuI18N.put("languageCd", langCode);
					menuI18N.put("menuNm", menuI18N.get(langCode).toString());
					//다국어에서 한국어 바꿀 때 공통코드테이블도 바뀌게
					if (LANG_KO_KR.equals(langCode)) {
						commonDao.update("menuI18NService.updateMenu", menuI18N);
					}
					commonDao.update("menuI18NService.updateMenuI18N", menuI18N);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
