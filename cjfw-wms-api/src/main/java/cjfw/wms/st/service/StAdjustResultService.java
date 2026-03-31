package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.st.dto.StAdjustResultDetailResDto;
import cjfw.wms.st.dto.StAdjustResultReqDto;
import cjfw.wms.st.dto.StAdjustResultResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.23 
 * @description : 재고감모현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StAdjustResultService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stAdjustResultService.";

	private final CommonDao commonDao;

	/**
	 * @description : 재고감모현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StAdjustResultResDto> getMasterList(StAdjustResultReqDto stAdjustResultReqDto) {
		
		log.info("###### parameter = "+stAdjustResultReqDto.toString());		
		
		List<StAdjustResultResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", stAdjustResultReqDto);
		return list;
	}
	
	/**
	 * @description : 재고감모현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StAdjustResultDetailResDto> getDetailList(StAdjustResultReqDto stAdjustResultReqDto) {
		
		log.info("###### parameter = "+stAdjustResultReqDto.toString());		
		
		List<StAdjustResultDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", stAdjustResultReqDto);
		return list;
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	

}
