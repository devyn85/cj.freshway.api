/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.comfunc.func.largedata.controller;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.dataaccess.largedata.LargeDataUtils;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.UserContext;
import cjfw.wms.comfunc.func.largedata.dto.SampleLargedataGetReqDto;
import cjfw.wms.comfunc.func.largedata.service.SampleLargedataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("comfunc/func/largedata")
public class SampleLargedataController {

	private final SampleLargedataService sampleLargedataService;

	private final UserContext userContext;

	@GetMapping(value = "/search")
	public void searchLargedataList(HttpServletResponse response, SampleLargedataGetReqDto dummyReqDto) {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		LargeDataUtils largeDataUtil = new LargeDataUtils(response);
		sampleLargedataService.getLargedataList(dummyReqDto, largeDataUtil);
		largeDataUtil.writeResponseToJson();

		stopWatch.stop();
		log.info("##### {} ms.", stopWatch.getTime());
	}

	@GetMapping(value = "/excel")
	public void excelLargedataList(HttpServletRequest request, HttpServletResponse response, SampleLargedataGetReqDto dummyReqDto) {

		String[] headerColumns = new String[]{"일련번호","예외내용","발생일자","클라이언트IP", "서버IP", "호출URL"};
		String[] dataColumns = new String[]{"exnNo", "excptCnts", "occrDy","clntAddr","svrAddr","callUri"}; // Data Mapping Colum List(컬럼 순서에 맞게 생성)

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		LargeExcel largeExcel = new LargeExcel();
		largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
		largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
		largeExcel.setExcelFileName("에러내역"); // 엑셀 파일명

		LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
		sampleLargedataService.getLargedataExcelList(dummyReqDto, largeExcel);
		largeExcel.setUserContext(userContext);
		excelUtil.makeExcelDownload(request, response, largeExcel);

		stopWatch.stop();
		log.info("##### {} ms.", stopWatch.getTime());
	}
}