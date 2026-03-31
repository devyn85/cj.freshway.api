package cjfw.wms.st.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StExdcTransIndicatorOListReqDto;
import cjfw.wms.st.dto.StExdcTransIndicatorOListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.10 
 * @description : 외부비축상품별수불현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StExdcTransIndicatorOService{
	private transient static final String SEERVICED_PREFIX = "stExdcTransIndicatorOService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축상품별수불현황 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 ParkJinWoo 생성
	 */
	public Map<String, Object> getMasterList(StExdcTransIndicatorOListReqDto reqDto){
//		return commonDao.selectList(SEERVICED_PREFIX + "getStDailyInoutExDcMasterList",reqDto);
		 Map<String, Object> result = new HashMap();
		 List<StExdcTransIndicatorOListResDto> list =  commonDao.selectList(SEERVICED_PREFIX + "getHeaderList", reqDto);
		 result.put("list", list);
		 
		return result;
		
	}

	
} 