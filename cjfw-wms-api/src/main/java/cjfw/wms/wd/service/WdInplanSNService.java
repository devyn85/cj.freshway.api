package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.wd.dto.WdInplanSNReqDto;
import cjfw.wms.wd.dto.WdInplanSNResDetailDto;
import cjfw.wms.wd.dto.WdInplanSNResDto;
import cjfw.wms.wd.dto.WdInplanSNResExcelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.10 
 * @description : 이력상품출고현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdInplanSNService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdInplanSNService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 이력상품출고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdInplanSNResDto> getMasterList(WdInplanSNReqDto wdInplanSNReqDto, Page page) {
		log.info("### parameter = "+wdInplanSNReqDto.toString());
		
		List<WdInplanSNResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", wdInplanSNReqDto);
		return list;
	}
	/**
	 * @description : 이력상품출고현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdInplanSNResDetailDto> getDetailList(WdInplanSNReqDto wdInplanSNReqDto, Page page) {
		
		log.info("#### parameter = "+wdInplanSNReqDto.toString());		
		
		List<WdInplanSNResDetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", wdInplanSNReqDto);
		return list;
	}
	/**
	 * @description : 이력상품출고현황 엑셀 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdInplanSNResExcelDto> getDataExcelList(WdInplanSNReqDto wdInplanSNReqDto, Page page) {
		
		log.info("##### parameter = "+wdInplanSNReqDto.toString());		
		
		List<WdInplanSNResExcelDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", wdInplanSNReqDto);
		return list;
	}
	

}
