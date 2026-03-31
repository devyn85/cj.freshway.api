/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.publics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.portal.common.publics.dto.PublicCodeDtlGetReqDto;
import cjfw.wms.portal.common.publics.dto.PublicCodeDtlGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicCommonCodeService {

	private final CommonDao commonDao;

	/**
	 * 공통 코드 데이터를 조회한다.<br>
	 */
	public List<PublicCodeDtlGetResDto> getCommonCdList(PublicCodeDtlGetReqDto codeDtlReqDto) {
		List<PublicCodeDtlGetResDto> list = commonDao.selectList("publicCommonCodeService.getCommonCdList", codeDtlReqDto);
		return list;
	}
}
