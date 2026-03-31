package cjfw.wms.rt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.rt.dto.RtInplanSNExcelResDto;
import cjfw.wms.rt.dto.RtInplanTotalDetailResDto;
import cjfw.wms.rt.dto.RtInplanTotalReqDto;
import cjfw.wms.rt.dto.RtInplanTotalResDto;
import cjfw.wms.rt.dto.RtInplanTotalSerialInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.04 
 * @description : 반품진행현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RtInplanTotalService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "rtInplanTotalService.";

	private final CommonDao commonDao;

	/**
	 * @description : 반품진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanTotalResDto> getMasterList(RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		log.info("### parameter = "+rtInplanTotalReqDto.toString());		
		List<RtInplanTotalResDto> list = new ArrayList<RtInplanTotalResDto>();
		
		if(isNull(rtInplanTotalReqDto.getReturnInfoYn()) && rtInplanTotalReqDto.getReturnInfoYn().equals("1")) {
			list = commonDao.selectList(SERVICEID_PREFIX + "getMasterReturnList", rtInplanTotalReqDto);
		} else {
			list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", rtInplanTotalReqDto);
		}
		return list;
	}
	
	/**
	 * @description : 반품진행현황 클레임내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanTotalDetailResDto> getDetailList(RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		log.info("#### parameter = "+rtInplanTotalReqDto.toString());		
		
		List<RtInplanTotalDetailResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", rtInplanTotalReqDto);
		return list;
	}
	
	
	/**
	 * @description : 반품진행현황 이력정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.05 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanTotalSerialInfoResDto> getSerialInfoList(RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		log.info("##### parameter = "+rtInplanTotalReqDto.toString());		
		
		List<RtInplanTotalSerialInfoResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getSerialInfoList", rtInplanTotalReqDto);
		return list;
	}
	

	/**
	 * @description : 반품진행현황 엑셀자료 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<RtInplanSNExcelResDto> getDataExcelList(RtInplanTotalReqDto rtInplanTotalReqDto) {
		
		
		log.info("###### parameter = "+rtInplanTotalReqDto.toString());		
		
		List<RtInplanSNExcelResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDataExcelList", rtInplanTotalReqDto);
		//List<RtInplanSNExcelResDto> list = null;
		return list;
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	

}
