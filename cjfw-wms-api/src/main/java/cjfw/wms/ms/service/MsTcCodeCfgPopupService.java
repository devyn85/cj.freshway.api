package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsTcCodeCfgPopupReqDto;
import cjfw.wms.ms.dto.MsTcCodeCfgPopupResDto;
import cjfw.wms.ms.entity.MsTcCodeCfgPopupEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.18 
 * @description : 출발지TC센터설정 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MsTcCodeCfgPopupService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private static final String SERVICEID_PREFIX = "msTcCodeCfgPopup.";
	private final UserContext userContext;
	private final CommonDao commonDao;
	
	/**
	 * @description : 출발지TC센터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public Page<MsTcCodeCfgPopupResDto> getTcCodeCfgList(MsTcCodeCfgPopupReqDto msTcCodeCfgPopupReqDto, Page<?> page) {
    	return commonDao.selectPageList(SERVICEID_PREFIX + "getTcCodeCfgList", msTcCodeCfgPopupReqDto, page);
    }
    
    /**
	 * @description : 출차그룹 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    public String saveTcCodeCfgList(List<MsTcCodeCfgPopupReqDto> list) {
    	if (list != null) {
    		for (MsTcCodeCfgPopupReqDto dto : list) {
    			MsTcCodeCfgPopupEntity entity = ModelMapperUtil.map(dto, userContext, MsTcCodeCfgPopupEntity.class);
    			
    			commonDao.update(SERVICEID_PREFIX + "saveTcCodeCfgList", entity);    			
    		}
    	}
    	return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
}
