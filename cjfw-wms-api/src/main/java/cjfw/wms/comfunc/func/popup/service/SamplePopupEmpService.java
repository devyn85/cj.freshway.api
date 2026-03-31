/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.popup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.comfunc.func.popup.dto.SamplePopupEmpGetReqDto;
import cjfw.wms.comfunc.func.popup.dto.SamplePopupEmpGetResDto;
import lombok.RequiredArgsConstructor;


/**
 * 공통팝업 사원 조회<br>
 */
@Service
@RequiredArgsConstructor
public class SamplePopupEmpService {
	
	private final CommonDao commonDao;
	
	/**
	 * 사원를 조회한다.
	 */
	public List<SamplePopupEmpGetResDto> getEmpList(SamplePopupEmpGetReqDto samplePopupEmpGetReqDto) {
		return commonDao.selectList("samplePopupEmp.getEmpList", samplePopupEmpGetReqDto);
	}

}
