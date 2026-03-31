package cjfw.wms.dp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.dp.dto.DpInplanSTODetailResDto;
import cjfw.wms.dp.dto.DpInplanSTOExcelResDto;
import cjfw.wms.dp.dto.DpInplanSTOReqDto;
import cjfw.wms.dp.dto.DpInplanSTOResDto;
import cjfw.wms.dp.dto.DpInplanSTOSerialInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.18 
 * @description : 광역입고현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DpInplanSTOService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "dpInplanSTOService.";

	private final CommonDao commonDao;

	/**
	 * @description : 광역입고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanSTOResDto> getMasterList(DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		log.info("### parameter = "+dpInplanSTOReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dpInplanSTOReqDto);
	}
	
	/**
	 * @description : 광역입고현황 주문현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanSTODetailResDto> getDetailList(DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		log.info("#### parameter = "+dpInplanSTOReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dpInplanSTOReqDto);
	}
	
	
	/**
	 * @description : 광역입고현황 이력현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanSTOSerialInfoResDto> getSerialInfoList(DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		log.info("##### parameter = "+dpInplanSTOReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getSerialInfoList", dpInplanSTOReqDto);
	}
	

	/**
	 * @description : 광역입고현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<DpInplanSTOExcelResDto> getDataExcelList(DpInplanSTOReqDto dpInplanSTOReqDto) {
		
		
		log.info("###### parameter = "+dpInplanSTOReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", dpInplanSTOReqDto);
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	

}
