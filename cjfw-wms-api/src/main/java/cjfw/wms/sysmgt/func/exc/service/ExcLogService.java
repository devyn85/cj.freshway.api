/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.exc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.exc.dto.ExcLogGetReqDto;
import cjfw.wms.sysmgt.func.exc.dto.ExcLogGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템 예외 이력을 보여준다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ExcLogService{
	

	private final CommonDao commonDao;

	/**
	 * 시스템 예외 정보를 조회한다.<br>
	 */
	public Page<List<ExcLogGetResDto>> getExcLogList(ExcLogGetReqDto excLogGetReqDto, Page page) {
		return commonDao.selectPageList("excLogService.getExcLogList", excLogGetReqDto,page);
	}
}
