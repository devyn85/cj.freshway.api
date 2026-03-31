package cjfw.wms.dp.service;

import java.util.List;

import cjfw.core.dataaccess.largedata.LargeExcel;
import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.dp.dto.DpInplanDetailResDto;
import cjfw.wms.dp.dto.DpInplanExcelResDto;
import cjfw.wms.dp.dto.DpInplanReqDto;
import cjfw.wms.dp.dto.DpInplanResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.16 
 * @description : 입고진행현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpInplanService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "dpInplanService.";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 입고진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanResDto> getMasterList(DpInplanReqDto dpInplanReqDto, Page page) {
		log.info("### parameter = "+dpInplanReqDto.toString());
		
		List<DpInplanResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dpInplanReqDto);
		return list;
	}
	/**
	 * @description : 입고진행현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanDetailResDto> getDetailList(DpInplanReqDto dpInplanReqDto) {
		
		log.info("#### parameter = "+dpInplanReqDto.toString());		
		
		List<DpInplanDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dpInplanReqDto);
		return list;
	}
	/**
	 * @description : 입고진행현황 엑셀 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public void getDataExcelList(DpInplanReqDto dpInplanReqDto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset(SERVICEID_PREFIX + "getDataExcelList", dpInplanReqDto, largeExcel);
	}
	

}
