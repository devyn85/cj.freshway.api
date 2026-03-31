package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.st.dto.StLocMoveRPCustReqDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab1CustkeyDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab1SkuDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab2Dto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.18 
 * @description : 거래처별보충(수원3층) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StLocMoveRPCustService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stLocMoveRPCustService.";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	/** 공통.UserContext */
	private final UserContext userContext;
	/** 공통.service */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 보충생성-거래처 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPCustResTab1CustkeyDto> getTab1CustkeyList(StLocMoveRPCustReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPCustResTab1CustkeyDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1CustkeyList", dto);
		return list;
	}
	
	/**
	 * @description : 보충생성-상품 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPCustResTab1SkuDto> getTab1SkuList(StLocMoveRPCustReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPCustResTab1SkuDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1SkuList", dto);
		return list;
	}
	
	/**
	 * @description : ASRS결과 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<StLocMoveRPCustResTab2Dto> getTab2MasterList(StLocMoveRPCustReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<StLocMoveRPCustResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
}
