package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmWorklogByCarDetailReqDto;
import cjfw.wms.tm.dto.TmWorklogByCarDetailResDto;
import cjfw.wms.tm.dto.TmWorklogByCarReqDto;
import cjfw.wms.tm.dto.TmWorklogByCarResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.24
 * @description : 배차작업로그(차량별) 서비스
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
public class TmWorklogByCarService {

	private transient static final String SERVICEID_PREFIX = "tmWorklogByCarService.";

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
	public List<TmWorklogByCarResDto> getWorklogByCarList(TmWorklogByCarReqDto tmWorklogByCarReqDto) {
		List<TmWorklogByCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getWorklogByCarList", tmWorklogByCarReqDto);
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
	public List<TmWorklogByCarDetailResDto> getWorklogByCarDetailList(TmWorklogByCarDetailReqDto tmWorklogByCarDetailReqDto) {
		List<TmWorklogByCarDetailResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getWorklogByCarDetailList", tmWorklogByCarDetailReqDto);
		return result;
	}

}
