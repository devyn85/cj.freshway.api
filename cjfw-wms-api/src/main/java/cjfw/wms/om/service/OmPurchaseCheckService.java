package cjfw.wms.om.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.om.dto.OmPurchaseCheckReqDto;
import cjfw.wms.om.dto.OmPurchaseCheckResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.11
 * @description : 월간주간발주량체크PO 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmPurchaseCheckService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omPurchaseCheckService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 월간주간발주량체크PO 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseCheckResDto> getMasterListPO(OmPurchaseCheckReqDto omPurchaseCheckReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterListPO", omPurchaseCheckReqDto);
	}

	/**
	 * @description : 월간주간발주량체크STO 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmPurchaseCheckResDto> getMasterListSTO(OmPurchaseCheckReqDto omPurchaseCheckReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterListSTO", omPurchaseCheckReqDto);
	}
}
