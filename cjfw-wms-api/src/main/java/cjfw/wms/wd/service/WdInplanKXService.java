package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.wd.dto.WdInplanKXReqDto;
import cjfw.wms.wd.dto.WdInplanKXResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.11.28 
 * @description : 출고 > 출고현황 > KX출고진행현황 정보를 조회 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.28 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInplanKXService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdInplanKXService.";
	
	private final CommonDao commonDao;
	
	/**
	 * @description : KX출고진행현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.28 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	public List<WdInplanKXResDto> getInplanKXList(WdInplanKXReqDto wdInplanKXReqDto) {
		List<WdInplanKXResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getInplanKXList", wdInplanKXReqDto);
		
		return list;
	}

}