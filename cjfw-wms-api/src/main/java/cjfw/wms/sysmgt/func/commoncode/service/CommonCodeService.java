/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.commoncode.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlAsyncTemplateGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlAsyncTemplateGetReqDto.CodeGrp;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetResDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeGrpGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeGrpGetResDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CommonCodeSaveReqDto;
import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeDtlEntity;
import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeDtlEntityMapper;
import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeGrpEntity;
import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeGrpEntityMapper;
import cjfw.wms.sysmgt.func.commoncodeI18N.entity.CommonCodeI18NEntity;
import cjfw.wms.sysmgt.func.commoncodeI18N.entity.CommonCodeI18NEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonCodeService{

	private final CommonDao commonDao;

	private final CommonCodeAsyncService commonCodeAsyncService;

	private final UserContext userContext;

	private static final String LANG_KO_KR = "ko_kr";

	/**
	 * 공통 그룹 코드 데이터를 조회한다.<br>
	 */
	public List<CodeGrpGetResDto> getGroupCdList(CodeGrpGetReqDto codeGrpReqDto) {
		List<CodeGrpGetResDto> list = commonDao.selectList("commonCodeService.getGroupCdList", codeGrpReqDto);
		return list;
	}
	
	/**
	 * 공통 코드 데이터를 조회한다.<br>
	 */
	public List<CodeDtlGetResDto> getCommonCdList(CodeDtlGetReqDto codeDtlReqDto) {
		List<CodeDtlGetResDto> list = commonDao.selectList("commonCodeService.getCommonCdList", codeDtlReqDto);
		return list;
	}
	
	/**
	 * 공통 코드 데이터의 변경 사항을 저장한다.<br>
	 */
	public String saveCommonCd(CommonCodeSaveReqDto codeSaveReqDto) {
		
		List<CommonCodeSaveReqDto.CodeGrp> codeGrps = codeSaveReqDto.getCodeGrps();
		List<CommonCodeSaveReqDto.CodeDtl> codeDtls = codeSaveReqDto.getCodeDtls();

		if(codeGrps != null){
			for(CommonCodeSaveReqDto.CodeGrp codeGrp: codeGrps){
				CommonCodeGrpEntity grpCdEntity = CommonCodeGrpEntityMapper.INSTANCE.saveCodeGrpDtoToEntity(codeGrp, userContext);
				log.info("{}", grpCdEntity);
				
				//그룹코드
				String grpCode = MessageUtil.getMessage("LABEL.COM.CL.CD");
				
				if((CanalFrameConstants.INSERT).equals(codeGrp.getRowStatus())) {
					CodeGrpGetReqDto codeGrpReqDto = new CodeGrpGetReqDto();
					codeGrpReqDto.setComGrpCd(codeGrp.getComGrpCd());
					int cnt = commonDao.getTotalCount("commonCodeService.getGroupCdList", codeGrpReqDto);
					if(cnt > 0) {
						//중복된 그룹코드(이)가 존재합니다.
						throw new UserHandleException("MSG.COM.VAL.067", new String[] {grpCode});
					}
					commonDao.insert("commonCodeService.insertGcode", grpCdEntity);
				} else if((CanalFrameConstants.UPDATE).equals(codeGrp.getRowStatus())) {
					commonDao.update("commonCodeService.updateGcode", grpCdEntity);
				} else if((CanalFrameConstants.DELETE).equals(codeGrp.getRowStatus())) {
					commonDao.delete("commonCodeService.deleteGcode", grpCdEntity);
					commonDao.delete("commonCodeService.deleteGCode2", grpCdEntity);
					CommonCodeI18NEntity i18Entity = CommonCodeI18NEntityMapper.INSTANCE.grpCdEntityToEntity(grpCdEntity);
					commonDao.delete("commonCodeI18NService.deleteI18NCode", i18Entity);
				}
			}
		}

		if(codeDtls != null){
			/******************언어코드 추출 시작***********************/
			CodeDtlGetReqDto tmpParam = new CodeDtlGetReqDto();
			tmpParam.setComGrpCd("LANG_CD");
			tmpParam.setUseYn("1");
			List<CodeDtlGetResDto> langCdCodelist = commonDao.selectList("commonCodeService.getCommonCdList", tmpParam);
			/******************언어공통코드 추출 끝***********************/

			for(CommonCodeSaveReqDto.CodeDtl codeDtl: codeDtls){
				CommonCodeDtlEntity dtlCdEntity = CommonCodeDtlEntityMapper.INSTANCE.saveCodeDtlDtoToEntity(codeDtl, userContext);
				log.info("{}", dtlCdEntity);
				if((CanalFrameConstants.INSERT).equals(codeDtl.getRowStatus())) {
					commonDao.insert("commonCodeService.insertCode", dtlCdEntity);

					CommonCodeI18NEntity i18Entity = CommonCodeI18NEntityMapper.INSTANCE.dtlCdEntityToEntity(dtlCdEntity);
					String oriCdNm = dtlCdEntity.getCdNm();
					for(int langIdx = 0 ; langIdx < langCdCodelist.size() ; langIdx++){
						String langCd = langCdCodelist.get(langIdx).getComCd();
						i18Entity.setLanguageCd(langCd);
						//한국어 외 메뉴명 = 언어코드+메뉴명으로 변경  ex)en시스템관리
						if(!LANG_KO_KR.equals(langCd)){
							i18Entity.setCdNm(langCd+oriCdNm);
						}
						commonDao.insert("commonCodeI18NService.insertCodeI18N", i18Entity);
					}

				} else if((CanalFrameConstants.UPDATE).equals(codeDtl.getRowStatus())) {
					commonDao.update("commonCodeService.updateCode", dtlCdEntity);
					CommonCodeI18NEntity i18Entity = CommonCodeI18NEntityMapper.INSTANCE.dtlCdEntityToEntity(dtlCdEntity);
					i18Entity.setLanguageCd(langCdCodelist.get(0).getComCd());
					commonDao.update("commonCodeI18NService.updateCodeI18N", i18Entity);
				} else if((CanalFrameConstants.DELETE).equals(codeDtl.getRowStatus())) {
					commonDao.delete("commonCodeService.deleteCode", dtlCdEntity);
					commonDao.delete("commonCodeI18NService.deleteI18NCode", dtlCdEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 공통 코드 데이터를 비동기 방식으로 조회하는 Template 서비스<br>
	 */
	public String getCommonCdAsyncTemplateList() {
		CodeDtlAsyncTemplateGetReqDto codeDtlAsyncTemplateGetReqDto = new CodeDtlAsyncTemplateGetReqDto();
		
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code01 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code01.setComGrpCd("USER_STATUS");
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code02 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code02.setComGrpCd("LANG_CD");
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code03 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code03.setComGrpCd("BBS_SCP");
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code04 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code04.setComGrpCd("BBS_SRCH_TP");
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code05 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code05.setComGrpCd("MESSAGE_TP");
		CodeDtlAsyncTemplateGetReqDto.CodeGrp code06 = new CodeDtlAsyncTemplateGetReqDto.CodeGrp();
		code06.setComGrpCd("TPL_TIMEZONE");
		
		List<CodeGrp> codeGrp = new ArrayList<CodeGrp>();
		codeGrp.add(code01);
		codeGrp.add(code02);
		codeGrp.add(code03);
		codeGrp.add(code04);
		codeGrp.add(code05);
		codeGrp.add(code06);
		
		codeDtlAsyncTemplateGetReqDto.setCodeGrps(codeGrp);
		
		Iterator<CodeGrp> it = codeDtlAsyncTemplateGetReqDto.getCodeGrps().iterator();
	    
		// 호출 순번
		int idx = 0;
	    
		// 세팅된 공통그룹코드를 순차 비동기 호출
	    while(it.hasNext()) {
	    	idx++;
	    	commonCodeAsyncService.getCommonCdAsync(idx, it.next());
	    }
	    
	    return "비동기 조회 완료";
	}
}
