package cjfw.wms.st.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StExdcManualIndicatorReqDto;
import cjfw.wms.st.dto.StExdcManualIndicatorResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.03 
 * @description : 수기출고현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.03 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StExdcManualIndicatorService{
	private transient static final String SEERVICED_PREFIX = "stExdcManualIndicatorService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
		
	/**
	 * @description : 수기출고현황 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.03 ParkJinWoo 생성
	 */
	public Map<String, Object> getMasterList(StExdcManualIndicatorReqDto reqDto){
//		return commonDao.selectList(SEERVICED_PREFIX + "getStDailyInoutExDcMasterList",reqDto);
		 Map<String, Object> result = new HashMap();
		 List<StExdcManualIndicatorResDto> totalList =  commonDao.selectList(SEERVICED_PREFIX + "getHeaderList", reqDto);
		 List<StExdcManualIndicatorResDto> monthChartList =  commonDao.selectList(SEERVICED_PREFIX + "selectManualOutboundMonthlyTrend", reqDto);
		 List<StExdcManualIndicatorResDto> monthList =  commonDao.selectList(SEERVICED_PREFIX + "selectManualOutboundReasonByMonth", reqDto);
		 List<StExdcManualIndicatorResDto> docList =  commonDao.selectList(SEERVICED_PREFIX + "selectManualOutboundReasonByDepthr", reqDto);
		 result.put("totalList", totalList);
		 result.put("monthChartList", monthChartList);
		 result.put("monthList", monthList);
		 result.put("docList", docList);
		return result;
		
	}

	
} 