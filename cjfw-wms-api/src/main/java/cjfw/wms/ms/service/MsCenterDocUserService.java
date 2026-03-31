package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterDocUserReqDto;
import cjfw.wms.ms.dto.MsCenterDocUserResDto;
import cjfw.wms.ms.entity.MsCenterDocUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 센터서류 사용자관리 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterDocUserService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterDocUserService.";

	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 센터서류 사용자관리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<MsCenterDocUserResDto> getMasterList(MsCenterDocUserReqDto msCenterDocUserReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", msCenterDocUserReqDto);
	}
	
	/**
	 * 
	 * @description : 센터서류 사용자관리 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveMasterList(List<MsCenterDocUserReqDto> list) {
		if (null != list) {
			for (var dto : list) {
				var entity = ModelMapperUtil.map(dto, userContext, MsCenterDocUserEntity.class);				
				if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					entity.setDelYn("Y");					
				}
				commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
