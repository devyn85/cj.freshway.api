package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmMngplcIDUndoneReqDto;
import cjfw.wms.tm.dto.TmMngplcIDUndoneResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.20
 * @description : 분할 미적용 관리처 Service
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.20 JiHoPark 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmMngplcIDUndoneService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmMngplcIDUndoneService.";

	private final CommonDao commonDao;

	/**
	 * @description : 분할 미적용 관리처 - 분할 미적용 관리처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.20 JiHoPark  생성 </pre>
	 */
	public List<TmMngplcIDUndoneResDto> getMasterList(TmMngplcIDUndoneReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
	}
}
