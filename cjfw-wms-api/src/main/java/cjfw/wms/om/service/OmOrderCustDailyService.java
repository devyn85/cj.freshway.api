package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmOrderCustDailyDetailResDto;
import cjfw.wms.om.dto.OmOrderCustDailyPrintResDto;
import cjfw.wms.om.dto.OmOrderCustDailyReqDto;
import cjfw.wms.om.dto.OmOrderCustDailyResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.20 
 * @description : 일배협력사별주문현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCustDailyService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omOrderCustDailyService.";

	private final CommonDao commonDao;

	/**
	 * @description : 일배협력사별주문현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<OmOrderCustDailyResDto> getMasterList(OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
		
		log.info("### parameter = "+omOrderCustDailyReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", omOrderCustDailyReqDto);
	}
	
	/**
	 * @description : 일배협력사별주문현황 주문현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<OmOrderCustDailyDetailResDto> getDetailList(OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
		
		log.info("#### parameter = "+omOrderCustDailyReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", omOrderCustDailyReqDto);
	}

	/**
	 * @description : 일배협력사별주문현황 프린트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.20 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<OmOrderCustDailyPrintResDto> getDataPrintList(OmOrderCustDailyReqDto omOrderCustDailyReqDto) {
		
		
		log.info("###### parameter = "+omOrderCustDailyReqDto.toString());		
		
		return commonDao.selectList(SERVICEID_PREFIX + "getDataPrintList", omOrderCustDailyReqDto);
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	

}
