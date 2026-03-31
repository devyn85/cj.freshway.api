package cjfw.wms.tm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmDeliveryIndicatorReqDto;
import cjfw.wms.tm.dto.TmDeliveryIndicatorResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.01.20 
 * @description : 출도착지표 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.01.20 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDeliveryIndicatorService {

	private transient static final String SERVICEID_PREFIX = "tmDeliveryIndicatorService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 출도착지표 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.05 ParkJinWoo 생성
	 */
	public Map<String, Object> getMasterList(TmDeliveryIndicatorReqDto reqDto) {
//		tmInvoicelogMgrReqDto.setDocType("WD");
//		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",reqDto);
	    List<TmDeliveryIndicatorResDto> dailyList =
	            commonDao.selectList(SERVICEID_PREFIX + "getHeaderListByDay", reqDto);

	    List<TmDeliveryIndicatorResDto> monthlyList =
	            commonDao.selectList(SERVICEID_PREFIX + "getHeaderListByMonth", reqDto);
	    Map<String, Object> result = new HashMap();
	    result.put("daily", dailyList);     // 일별
	    result.put("monthly", monthlyList); // 월별
	    return result;

	}
	
	


}
