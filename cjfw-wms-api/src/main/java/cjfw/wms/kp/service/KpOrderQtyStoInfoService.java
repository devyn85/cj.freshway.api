package cjfw.wms.kp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoDetailResDto;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoReqDto;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.20
 * @description : 이체및출고현황 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KpOrderQtyStoInfoService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "kpOrderQtyStoInfoService.";
	
	private final CommonDao commonDao;

	/**
	 * @description : 이체및출고현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<KpOrderQtyStoInfoResDto> getMasterList(KpOrderQtyStoInfoReqDto dto) {
		
		List<KpOrderQtyStoInfoResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	
	/**
	 * @description : 이체및출고현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<KpOrderQtyStoInfoDetailResDto> getDetailList(KpOrderQtyStoInfoReqDto dto) {
		
		List<KpOrderQtyStoInfoDetailResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		return result;
	}
	
}
