package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsVehicleExitGroupCfgPopupReqDto;
import cjfw.wms.ms.dto.MsVehicleExitGroupCfgPopupResDto;
import cjfw.wms.ms.entity.MsVehicleExitGroupCfgPopupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.01 
 * @description : 출차정보 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MsVehicleExitGroupCfgPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msVehicleExitGroupCfgPopup.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 출차그룹 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public Page<MsVehicleExitGroupCfgPopupResDto> getVehicleExitGroupCfgList(MsVehicleExitGroupCfgPopupReqDto msVehicleExitGroupCfgPopupReqDto, Page<?> page) {
    	return commonDao.selectPageList(SERVICEID_PREFIX + "getVehicleExitGroupCfgList", msVehicleExitGroupCfgPopupReqDto, page);
    }
    
    /**
	 * @description : 출차그룹 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public String saveVehicleExitGroupCfgList(List<MsVehicleExitGroupCfgPopupReqDto> list) {
    	if (list != null) {
    		for (MsVehicleExitGroupCfgPopupReqDto dto : list) {
    			MsVehicleExitGroupCfgPopupEntity entity = ModelMapperUtil.map(dto, userContext, MsVehicleExitGroupCfgPopupEntity.class);
    			
    			commonDao.update(SERVICEID_PREFIX + "saveVehicleExitGroupCfgList", entity);    			
    		}
    	}
    	return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
}
