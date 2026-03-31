package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.wd.dto.WdDeliveryLabelForceCoupangResDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceHanaroReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceHanaroResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.14 
 * @description : 배송라벨출력(하나로엑셀) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelForceHanaroService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdDeliveryLabelForceHanaroService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 하나로 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelForceHanaroResDto> getTab1MasterList(WdDeliveryLabelForceHanaroReqDto dto, Page page) {
		log.info("### parameter = "+dto.toString());
		
		List<WdDeliveryLabelForceHanaroResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	
	/**
	 * @description : 쿠팡 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelForceCoupangResDto> getTab2MasterList(WdDeliveryLabelForceHanaroReqDto dto, Page page) {
		log.info("### parameter = "+dto.toString());
		
		List<WdDeliveryLabelForceCoupangResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	

}
