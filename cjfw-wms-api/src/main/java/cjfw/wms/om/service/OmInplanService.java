package cjfw.wms.om.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.om.dto.OmInplanDetailResDto;
import cjfw.wms.om.dto.OmInplanReqDto;
import cjfw.wms.om.dto.OmInplanResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.10
 * @description : 주문이력현황 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmInplanService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omInplanService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 주문이력현황 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public Page<OmInplanResDto> getMasterList(OmInplanReqDto omInplanReqDto) {
		return commonDao.selectPageList(SERVICEID_PREFIX + "getMasterList", omInplanReqDto, omInplanReqDto);
	}
	
	/**
	 * @description : 주문이력현황 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<OmInplanDetailResDto> getDetailList(OmInplanReqDto omInplanReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDetailList", omInplanReqDto);
	}
	
	/**
	 * @description : 진행상태 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.10        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<Map<String,String>> getPlantList(){
		return commonDao.selectList(SERVICEID_PREFIX + "getStatusList");
	}
}
