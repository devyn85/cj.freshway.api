package cjfw.wms.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.dto.CmDcManagerReqDto;
import cjfw.wms.cm.dto.CmDcManagerResDto;
import cjfw.wms.cm.entity.CmDcManagerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 물류센터관리 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmDcManagerService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmDcManagerService.";

	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 물류센터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<CmDcManagerResDto> getMasterList(CmDcManagerReqDto cmDcManagerReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", cmDcManagerReqDto);
	}
	
	/**
	 * @description : 물류센터 상세 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public CmDcManagerResDto getDetail(CmDcManagerReqDto cmDcManagerReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getDetail", cmDcManagerReqDto);
	}	
	
	/**
	 * 
	 * @description : 물류센터 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<CmDcManagerReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, CmDcManagerEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
