package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmWorklogByCustomerDetailReqDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerDetailResDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerReqDto;
import cjfw.wms.tm.dto.TmWorklogByCustomerResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.24
 * @description : 배차작업로그(거래처별) 서비스
 *  Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmWorklogByCustomerService {

	private transient static final String SERVICEID_PREFIX = "tmWorklogByCustomerService.";

	private final CommonDao commonDao;

	private final UserContext userContext;

	/**
	 * @description : 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<TmWorklogByCustomerResDto> getWorklogByCustomerList(TmWorklogByCustomerReqDto tmWorklogByCustomerReqDto) {
		List<TmWorklogByCustomerResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getWorklogByCustomerList", tmWorklogByCustomerReqDto);
		return result;
	}

	/**
	 * @description : 상세 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	public List<TmWorklogByCustomerDetailResDto> getWorklogByCustomerDetailList(TmWorklogByCustomerDetailReqDto tmWorklogByCustomerDetailReqDto) {
		List<TmWorklogByCustomerDetailResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getWorklogByCustomerDetailList", tmWorklogByCustomerDetailReqDto);
		return result;
	}

}
