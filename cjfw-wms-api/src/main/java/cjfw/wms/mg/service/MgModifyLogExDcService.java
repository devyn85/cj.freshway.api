package cjfw.wms.mg.service;



import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.mg.dto.MgModifyLogExDcDetailListReqDto;
import cjfw.wms.mg.dto.MgModifyLogExDcDetailListResDto;
import cjfw.wms.mg.dto.MgModifyLogExDcMasterListReqDto;
import cjfw.wms.mg.dto.MgModifyLogExDcMasterListResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description : 외부비축재고변경사유현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MgModifyLogExDcService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "mgModifyLogExDcService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * @description : 외부비축재고변경사유현황 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<MgModifyLogExDcMasterListResDto> getMasterList(MgModifyLogExDcMasterListReqDto mgModifyLogExDcMasterListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", mgModifyLogExDcMasterListReqDto);
	}
	/**
	 * @description : 외부비축재고변경사유현황 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	public List<MgModifyLogExDcDetailListResDto> getDetailList(MgModifyLogExDcDetailListReqDto mgModifyLogExDcDetailListReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", mgModifyLogExDcDetailListReqDto);
	}

}
