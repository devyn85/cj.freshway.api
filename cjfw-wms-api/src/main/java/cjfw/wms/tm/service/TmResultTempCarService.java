package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmResultTempCarListReqDto;
import cjfw.wms.tm.dto.TmResultTempCarListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.06 
 * @description : 일별임시차현황 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.06 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmResultTempCarService {

	/**`
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmResultTempCarService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 일별임시차현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.06 ParkJinWoo 생성
	 */
	public List<TmResultTempCarListResDto> getMasterList(TmResultTempCarListReqDto tmResultTempCarListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist",tmResultTempCarListReqDto);
	}
	
	

}
