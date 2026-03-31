package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdDeliveryLabelDelReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDelResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.23 
 * @description : 배송라벨삭제현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelDelService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdDeliveryLabelDelService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 배송라벨삭제현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelDelResDto> getMasterList(WdDeliveryLabelDelReqDto wdDeliveryLabelDelReqDto, Page page) {
		
		log.info("###### parameter = "+wdDeliveryLabelDelReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", wdDeliveryLabelDelReqDto);
	}
	

}
