package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StDailyInoutExDcDetailReqDto;
import cjfw.wms.st.dto.StDailyInoutExDcDetailResDto;
import cjfw.wms.st.dto.StDailyInoutExDcMasterReqDto;
import cjfw.wms.st.dto.StDailyInoutExDcMasterResDto;
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
public class StDailyInoutExDcService{
	private transient static final String SEERVICED_PREFIX = "stDailyInoutExDcService.";
	
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
	public List<StDailyInoutExDcMasterResDto> getStDailyInoutExDcMasterList(StDailyInoutExDcMasterReqDto stDailyInoutExDcMasterReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getStDailyInoutExDcMasterList",stDailyInoutExDcMasterReqDto);
		
	}
	/**
	 * @description : 외부비축상품별수불현황 상세목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10 ParkJinWoo 생성
	 */
	public List<StDailyInoutExDcDetailResDto> getStDailyInoutExDcDetailList(StDailyInoutExDcDetailReqDto stDailyInoutExDcDetailReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getStDailyInoutExDcDetailList",stDailyInoutExDcDetailReqDto);
		
	}
	
} 