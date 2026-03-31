/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.chart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.comfunc.func.chart.dto.SampleChartGetReqDto;
import cjfw.wms.comfunc.func.chart.dto.SampleChartGetResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class SampleChartService{

	private final CommonDao commonDao;
	
	/**
	 * 년도별 시스템 예외 이력.<br>
	 */
	public List<SampleChartGetResDto> getSystemExceptionCount(SampleChartGetReqDto sampleChartGetReqDto) {
		return commonDao.selectList("sampleChartService.getSystemExceptionCount", sampleChartGetReqDto);
	}
}