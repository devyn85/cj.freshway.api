package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdShortageListResDto;
import cjfw.wms.wd.dto.WdShortageListReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.03.05 
 * @description : 출고결품현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.05 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdShortageListService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdShortageListService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 출고결품현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdShortageListResDto> getMasterList(WdShortageListReqDto dto, Page page) {
		log.info("### parameter = "+dto.toString());
		
		List<WdShortageListResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	

}
