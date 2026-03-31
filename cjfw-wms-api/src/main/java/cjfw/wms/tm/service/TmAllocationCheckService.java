package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmAllocationCheckDetailReqDto;
import cjfw.wms.tm.dto.TmAllocationCheckDetailResDto;
import cjfw.wms.tm.dto.TmAllocationCheckMasterReqDto;
import cjfw.wms.tm.dto.TmAllocationCheckMasterResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description :배차마스터체크결과 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmAllocationCheckService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmAllocationCheckService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 납품서 츨력로그 (관리자)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 ParkJinWoo 생성
	 */
	public List<TmAllocationCheckMasterResDto> getMasterList(TmAllocationCheckMasterReqDto tmAllocationCheckMasterReqDto) {
//		TmAllocationCheckReqDto.setDocType("WD");
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList",tmAllocationCheckMasterReqDto);
	}
	

	/**
	 * @description : 납품서 츨력로그 (관리자)조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.30 ParkJinWoo 생성
	 */
	public Page<TmAllocationCheckDetailResDto> getDetailList(TmAllocationCheckDetailReqDto tmAllocationCheckDetailReqDto,Page page) {
//		TmAllocationCheckReqDto.setDocType("WD");
		Page<TmAllocationCheckDetailResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getDetailList",tmAllocationCheckDetailReqDto,page);
		return result;
	}
	
	


}
