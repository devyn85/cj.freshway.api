package cjfw.wms.rt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.rt.dto.RtInplanSNDetailResDto;
import cjfw.wms.rt.dto.RtInplanSNExcelResDto;
import cjfw.wms.rt.dto.RtInplanSNReqDto;
import cjfw.wms.rt.dto.RtInplanSNResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.28 
 * @description : 이력상품반품현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtInplanSNService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "rtInplanSNService.";

	private final CommonDao commonDao;

	/**
	 * @description : 이력상품반품현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanSNResDto> getMasterList(RtInplanSNReqDto rtInplanSNReqDto) {
				
		log.info("### parameter = "+rtInplanSNReqDto.toString());		
		
		List<RtInplanSNResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", rtInplanSNReqDto);
		return list;
	}
	
	/**
	 * @description : 이력상품반품현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanSNDetailResDto> getDetailList(RtInplanSNReqDto rtInplanSNReqDto) {
		
		log.info("#### parameter = "+rtInplanSNReqDto.toString());		
		
		List<RtInplanSNDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", rtInplanSNReqDto);
		return list;
	}
	

	/**
	 * @description : 이력상품반품현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanSNExcelResDto> getDataExcelList(RtInplanSNReqDto rtInplanSNReqDto) {
		
		
		log.info("##### parameter = "+rtInplanSNReqDto.toString());		
		
		List<RtInplanSNExcelResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", rtInplanSNReqDto);
		//List<RtInplanSNExcelResDto> list = null;
		return list;
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	

}
