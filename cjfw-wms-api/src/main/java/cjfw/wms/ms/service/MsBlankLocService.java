package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsBlankLocReqDto;
import cjfw.wms.ms.dto.MsBlankLocResDto;
import cjfw.wms.ms.dto.MsBlankLocZoneResDto;
import cjfw.wms.ms.entity.MsBlankLocEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 기준정보 > 센터기준정보 > 기둥/빈 공간 정보 목록 조회 및 저장 Service
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsBlankLocService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msBlankLocService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 기둥/빈 공간 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsBlankLocResDto> getMasterList(MsBlankLocReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 기둥/빈 공간 정보 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsBlankLocReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsBlankLocEntity.class);
				
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					entity.setDelYn("Y");
					commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : ZONE 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsBlankLocZoneResDto> getDataZone(MsBlankLocReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataZone", dto);
	}

}
