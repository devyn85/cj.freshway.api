package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmPlanUndispatchManualSplitReqDto;
import cjfw.wms.tm.dto.TmPlanUndispatchManualSplitResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.07 
 * @description : 수동 분할 배차 서비스 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.07 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanUndispatchManualSplitService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmPlanUndispatchManualSplitService.";
	
	private final CommonDao commonDao;
	
	
	public List<TmPlanUndispatchManualSplitResDto> getMasterList (TmPlanUndispatchManualSplitReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}

}
