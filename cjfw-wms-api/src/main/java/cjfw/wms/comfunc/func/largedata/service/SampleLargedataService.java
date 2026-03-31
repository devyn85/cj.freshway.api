/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.largedata.service;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeDataUtils;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.wms.comfunc.func.largedata.dto.SampleLargedataGetReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleLargedataService{	

	private final CommonDao commonDao;

	/**
	 * 대량 데이터 조회
	 */
	public void getLargedataList(SampleLargedataGetReqDto dummyReqDto, LargeDataUtils largeDataUtils) {
		commonDao.selectLargeDataset("largedata.getExcLogList", dummyReqDto, largeDataUtils);
	}

	/**
	 * 대량데이터 엑셀 다운로드
	 */
	public void getLargedataExcelList(SampleLargedataGetReqDto dummyReqDto, LargeExcel largeExcel) {
		commonDao.selectLargeExcelDataset("largedata.getExcLogList", dummyReqDto, largeExcel);
	}
}