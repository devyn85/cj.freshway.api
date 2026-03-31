package cjfw.wms.cm.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.dto.CmCostCenterPopupReqDto;
import cjfw.wms.cm.dto.CmCostCenterPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.05.09 
 * @description : ADMIN > 시스템운영 > 귀속 부서 조회(매각 출고 처리) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CmSearch80Service {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "cmSearch80Service.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 귀속 부서 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public Page<CmCostCenterPopupResDto> getCostCenterList(CmCostCenterPopupReqDto cmCostCenterPopupReqDto, Page page) {
		Page<CmCostCenterPopupResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getCostCenterList", cmCostCenterPopupReqDto, page);
		return result;
	}
	
}
