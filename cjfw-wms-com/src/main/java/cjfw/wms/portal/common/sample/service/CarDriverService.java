/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.sample.service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.portal.common.sample.dto.CarDriverGetResDto;
import cjfw.wms.portal.common.sample.dto.CarDriverReqDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarDriverService {
	
	private final CommonDao commonDao;

	/**
	 * 
	 * @description : 샘플 목록조회 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.04.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<CarDriverGetResDto> getCarDriverList(CarDriverReqDto carDriverReqDto) {
		
		List<CarDriverGetResDto> list = commonDao.selectList("carDriverService.getCarDriverList", carDriverReqDto);
		
		return list;
	}


	/**
	 * 
	 * @description : 샘플 저장 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.04.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public String saveCarDriver(CarDriverReqDto carDriverReqDto){

		commonDao.update("carDriverService.updateCarDriver", carDriverReqDto);
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
}
