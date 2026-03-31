package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsZoneManagerReqDto;
import cjfw.wms.ms.dto.MsZoneManagerResDto;
import cjfw.wms.ms.entity.MsZoneManagerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.05.27 
 * @description : 기준정보 > 센터기준정보 > 존정보 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsZoneManagerService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msZoneManagerService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 존 정보 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsZoneManagerResDto> getMasterList(MsZoneManagerReqDto dto) {
		List<MsZoneManagerResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	public String saveMasterList(List<MsZoneManagerReqDto> dtoList) {
		if (null != dtoList) {
			for (MsZoneManagerReqDto dto : dtoList) {
				MsZoneManagerEntity entity = ModelMapperUtil.map(dto, userContext, MsZoneManagerEntity.class);
				if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
					//trigger
					//commonDao.selectOne(SERVICEID_PREFIX +"insertZoneInfo", msZoneManagerEntity);
					commonDao.insert(SERVICEID_PREFIX +"insertMasterList", entity);
				} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateMasterList", entity);
				} else if ((CanalFrameConstants.DELETE).equals(dto.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"deleteMasterList", entity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
}
