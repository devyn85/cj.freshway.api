package cjfw.wms.sys.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.SystemException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.sys.dto.SysLabelReqDto;
import cjfw.wms.sys.dto.SysLabelResDto;
import cjfw.wms.sys.entity.SysLabelEntity;
import cjfw.wms.sys.entity.SysLabelLangEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.08.21
 * @description : Admin > 시스템운영 > 라벨 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysLabelService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "sysLabelService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 라벨 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
	 */
	public Page<SysLabelResDto> getMasterList(SysLabelReqDto dto) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getLabelList", dto, dto);
	}
	
	/**
	 * 
	 * @description : 라벨 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21        JiSooKim (jskim14@cj.net)       생성
	 */
	@CacheEvict(cacheNames = "transCode", allEntries = true)
	public String saveMasterList(List<SysLabelReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, SysLabelEntity.class);
				var langEntity = ModelMapperUtil.map(dto, userContext, SysLabelLangEntity.class); 
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertLabel", entity);
					commonDao.insert(SERVICEID_PREFIX +"insertAutoLabelMultilingual", langEntity);
					if(langEntity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ langEntity.getErrCode() + ", 에러메세지 : " + langEntity.getErrMsg()));
					}
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateLabel", entity);
					if(entity.getErrCode() != 0){
						throw new SystemException(new UserHandleException(""+"에러코드 : "+ entity.getErrCode() + ", 에러메세지 : " + entity.getErrMsg()));
					}
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

}
