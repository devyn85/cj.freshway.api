/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.commoncodeI18N.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.CommonCodeI18NSaveReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeDtlGetReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeGrpGetReqDto;
import cjfw.wms.sysmgt.func.commoncodeI18N.dto.I18nCodeGrpGetResDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonCodeI18NService{
	
	private final CommonDao commonDao;

	private final UserContext userContext;

	private static final String LANG_KO_KR = "ko_kr";

	/**
	 * 공통 그룹 코드 데이터를 조회한다.<br>
	 */
	public List<I18nCodeGrpGetResDto>  getGroupCdList(I18nCodeGrpGetReqDto i18CodeGrpReqDto) {
		return commonDao.selectList("commonCodeI18NService.getGroupCdList", i18CodeGrpReqDto);
	}
	
	/**
	 * 공통 코드 데이터를 조회한다.<br>
	 */
	public List<Map> getCommonCdList(I18nCodeDtlGetReqDto i18CodeDtlReqDto) {
		List<String> langCodeList = commonDao.selectList("commonCodeService.getUseLangCdList");

		Map param = new HashMap();
		param.put("comGrpCd", i18CodeDtlReqDto.getComGrpCd());
		param.put("codeList", langCodeList);
		List<Map> list = commonDao.selectList("commonCodeI18NService.getCommonCdList", param);
		return list;
	}


	/**
	 * 다국어 공통코드 저장
	 */
	public String saveCommonCd(CommonCodeI18NSaveReqDto commonCodeI18NSaveReqDto) {

		List<String> langCodeList = commonDao.selectList("commonCodeService.getUseLangCdList");

		List<Map> i18nCodeDtls = commonCodeI18NSaveReqDto.getI18nCodeDtls();
		if(i18nCodeDtls != null) {
			for (Map i18nCodeDtl : i18nCodeDtls) {
				for(String langCode: langCodeList){
					i18nCodeDtl.put("languageCd", langCode);
					i18nCodeDtl.put("cdNm", i18nCodeDtl.get(langCode).toString());
					i18nCodeDtl.put("modId", userContext.getUserId());
					//다국어에서 한국어 바꿀 때 공통코드테이블도 바뀌게
					if (LANG_KO_KR.equals(langCode)) {
						commonDao.update("commonCodeI18NService.updateCode", i18nCodeDtl);
					}
					commonDao.update("commonCodeI18NService.updateCodeI18N", i18nCodeDtl);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
