/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.menu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetResDto;
import cjfw.wms.sysmgt.func.menu.dto.MenuGetReqDto;
import cjfw.wms.sysmgt.func.menu.dto.MenuGetResDto;
import cjfw.wms.sysmgt.func.menu.dto.MenuSaveReqDto;
import cjfw.wms.sysmgt.func.menu.entity.MenuEntity;
import cjfw.wms.sysmgt.func.menu.entity.MenuEntityMapper;
import cjfw.wms.sysmgt.func.menuI18N.entity.MenuI18NEntity;
import cjfw.wms.sysmgt.func.menuI18N.entity.MenuI18NEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuService{	

	private final CommonDao commonDao;

	private final UserContext userContext;

	private static final String LANG_KO_KR = "ko_kr";

	/**
	 * 메뉴 리스트 조회
	 */
	public List<MenuGetResDto> getMenuList(MenuGetReqDto menuGetReqDto) {
		List<MenuGetResDto> list = commonDao.selectList("menuService.getMenuList", menuGetReqDto);
		Set<String> menuIds = new HashSet<>();
		for(MenuGetResDto menuGetResDto: list){
			menuIds.add(menuGetResDto.getMenuId());
			String upperMenuId = menuGetResDto.getUpperMenuId();
			if(menuIds.contains(upperMenuId)) { // Tree참조용 컬럼 추가(검색결과 처리용)
				menuGetResDto.setRefUpperMenuId(upperMenuId);
			}
		}
		return list;
	}

	/**
	 * 메뉴 저장(CUD)
	 */
	@CacheEvict(value = "authenticatedMenu", allEntries=true)
	public String saveMenu(MenuSaveReqDto menuSaveReqDto) {

		/******************언어코드 추출 시작***********************/
		CodeDtlGetReqDto tmpParam = new CodeDtlGetReqDto();
		tmpParam.setComGrpCd("LANG_CD");
		tmpParam.setUseYn("1");
		List<CodeDtlGetResDto> langCdlist = commonDao.selectList("commonCodeService.getCommonCdList", tmpParam);
		/******************언어공통코드 추출 끝***********************/

		List<MenuSaveReqDto.Menu> menus = menuSaveReqDto.getMenus();
		if(null!=menus) {
			for(MenuSaveReqDto.Menu menu: menus) {
				if(menu.getUpperMenuId() != null && "".equals(menu.getUpperMenuId().trim())){ // 상위 메뉴ID가 empty면 null변환
					menu.setUpperMenuId(null);
				}

				MenuEntity menuEntity = MenuEntityMapper.INSTANCE.saveMenuDtoToEntity(menu, userContext);
				log.info("{}", menuEntity);
				MenuI18NEntity menui18nEntity = MenuI18NEntityMapper.INSTANCE.menuEntityToEntity(menuEntity);
				if((CanalFrameConstants.INSERT).equals(menu.getRowStatus())) {
					commonDao.insert("menuService.insertMenu", menuEntity);
					String oriMenuNm = menui18nEntity.getMenuNm();
					//메뉴관리에서 추가 시 명
					for(int langIdx = 0 ; langIdx < langCdlist.size() ; langIdx++) {
						String langCd = langCdlist.get(langIdx).getComCd();
						menui18nEntity.setLanguageCd(langCd);
						//한국어 외 메뉴명 = 언어코드+메뉴명으로 변경  ex)en_us시스템관리
						if(!LANG_KO_KR.equals(langCd)) {
							menui18nEntity.setMenuNm(langCd+oriMenuNm);
						}
						commonDao.insert("menuI18NService.insertMenuI18N", menui18nEntity);
					}
				} else if((CanalFrameConstants.UPDATE).equals(menu.getRowStatus())) {
					commonDao.update("menuService.updateMenu", menuEntity);
					//메뉴관리에서 명 변경 시 한국어도 변경
					menui18nEntity.setLanguageCd(LANG_KO_KR); // ko_kr 파라메타 세팅
					commonDao.update("menuI18NService.updateMenuI18N", menui18nEntity);
				} else if((CanalFrameConstants.DELETE).equals(menu.getRowStatus())) {
					commonDao.delete("menuI18NService.deleteMenuI18N", menui18nEntity);
					commonDao.delete("menuService.deleteMenu", menuEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
