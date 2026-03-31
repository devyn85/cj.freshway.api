package cjfw.wms.kp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.kp.dto.KpProcessResultReqDto;
import cjfw.wms.kp.dto.KpProcessResultResTab1Dto;
import cjfw.wms.kp.dto.KpProcessResultResTab2Dto;
import cjfw.wms.kp.dto.KpProcessResultResTab3Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 박요셉 (dytpq362@cj.net) 
 * @date : 2025.12.18
 * @description : 공정별생산성 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.18 박요셉 (dytpq362@cj.net) 생성</pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpProcessResultService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpProcessResultService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 공정별생산성 센터별 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.18 박요셉 (dytpq362@cj.net) 생성 </pre>
	 */
	public List<KpProcessResultResTab1Dto> getTab1MasterList(KpProcessResultReqDto dto) {
		List<KpProcessResultResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 공정별생산성 작업자별 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.18 박요셉 (dytpq362@cj.net) </pre>
	 */
	public List<KpProcessResultResTab2Dto> getTab2MasterList(KpProcessResultReqDto dto) {
		List<KpProcessResultResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 공정별생산성 Raw 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.18 박요셉 (dytpq362@cj.net) </pre>
	 */
	public List<KpProcessResultResTab3Dto> getTab3MasterList(KpProcessResultReqDto dto) {
		List<KpProcessResultResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}
	

	
	
}
