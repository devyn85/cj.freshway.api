package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.om.dto.OmInplanMonitoringChkFsIfResDto;
import cjfw.wms.om.dto.OmInplanMonitoringChkFsResDto;
import cjfw.wms.om.dto.OmInplanMonitoringChkRsltFsResDto;
import cjfw.wms.om.dto.OmInplanMonitoringCloseWdResDto;
import cjfw.wms.om.dto.OmInplanMonitoringCustCloseTypeResDto;
import cjfw.wms.om.dto.OmInplanMonitoringReqDto;
import cjfw.wms.om.dto.OmInplanMonitoringTotalResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.09
 * @description : 주문 > 주문현황 > 주문수신모니터링 조회 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmInplanMonitoringService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omInplanMonitoringService.";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 주문수신모니터링-WMS진핸현황 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringTotalResDto> getTotalMasterList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getTotalMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-경로별마감 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringCustCloseTypeResDto> getCustCloseTypeMasterList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCustCloseTypeMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-경로별마감 상세정보 조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringCustCloseTypeResDto> getCustCloseTypeDetailList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getCustCloseTypeDetailList", dto);
	}
	
	/**
	 * 
	 * @description : 경로별 강제마감 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveOrderCloseStatus(OmInplanMonitoringReqDto omInplanMonitoringReqDto) {
		commonDao.update(SERVICEID_PREFIX +"saveOrderCloseStatus", omInplanMonitoringReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-출고마감 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringCloseWdResDto> getCloseWdMasterList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDailyCloseList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업오더차이 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkFsResDto> getChkFsMasterList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getChkFsMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업오더차이 상세 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkFsResDto> getChkFsDetailList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getChkFsDetailList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업오더차이 I/F상세 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkFsIfResDto> getOrderListChkFSIF(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getOrderListChkFSIF", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업실적차이 정보 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkRsltFsResDto> getChkRsltFsMasterList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getChkRsltFsMasterList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업실적차이 상세 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.09        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkRsltFsResDto> getChkRsltFsDetailList(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getChkRsltFsDetailList", dto);
	}
	
	/**
	 * 
	 * @description : 주문수신모니터링-영업실적차이 I/F상세 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanMonitoringChkFsIfResDto> getOrderListChkRsltFSIF(OmInplanMonitoringReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getOrderListChkRsltFSIF", dto);
	}
}
