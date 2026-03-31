package cjfw.wms.om.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmPurchaseCtlReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.18
 * @description : 주문 > 주문등록 > 저장품자동발주관제 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmPurchaseCtlService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omPurchaseCtlService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 저장품자동발주관제 탭1 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab1MasterList(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return result;
	}
	
	/**
	 * @description : 저장품자동발주관제 탭2 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab2MasterList(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return result;
	}
	
	/**
	 * @description : 저장품자동발주관제 탭2 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab2DetailList(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailList", dto);
		return result;
	}

	/**
	 * @description : 저장품자동발주관제 탭2 상세2 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab2Detail2List(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab2Detail2List", dto);
		return result;
	}
	
	/**
	 * @description : 저장품자동발주관제 탭3 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab3MasterList(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return result;
	}
	
	/**
	 * @description : 저장품자동발주관제 탭3 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab3DetailList(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab3DetailList", dto);
		return result;
	}

	/**
	 * @description : 저장품자동발주관제 탭3 상세2 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.18        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String, Object>> getTab3Detail2List(OmPurchaseCtlReqDto dto) {
		List<Map<String, Object>> result = commonDao.selectList(SERVICEID_PREFIX + "getTab2Detail2List", dto);
		return result;
	}
}
