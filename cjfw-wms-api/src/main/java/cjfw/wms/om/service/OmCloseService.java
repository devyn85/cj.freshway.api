package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmCloseReqDto;
import cjfw.wms.om.dto.OmCloseResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.19
 * @description : 주문마감현황 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmCloseService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omCloseService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 주문마감현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmCloseResDto> getMasterList(OmCloseReqDto omCloseReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", omCloseReqDto);
	}
	
	/**
	 * @description : 주문마감현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmCloseResDto> getDetailList(OmCloseReqDto omCloseReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", omCloseReqDto);
	}
	
	/**
	 * @description : 주문마감현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmCloseResDto> getDetail2List(OmCloseReqDto omCloseReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetail2List", omCloseReqDto);
	}
}
