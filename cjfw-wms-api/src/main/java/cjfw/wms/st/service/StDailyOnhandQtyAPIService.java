package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIReqDto;
import cjfw.wms.st.dto.StDailyOnhandQtyAPIResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.04 
 * @description : 외부창고 API 재고현황 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StDailyOnhandQtyAPIService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stDailyOnhandQtyAPIService.";	
		
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 외부창고 API 재고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.04    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<StDailyOnhandQtyAPIResDto> getMasterList(StDailyOnhandQtyAPIReqDto reqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", reqDto);
	}
		
}
