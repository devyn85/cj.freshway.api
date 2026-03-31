package cjfw.wms.dp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpInspectDailyPrintReqDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResDetailDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResPoNotMapDetailDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResPoNotMapDto;
import cjfw.wms.dp.dto.DpInspectDailyPrintResPrintDto;
import cjfw.wms.dp.service.DpInspectDailyPrintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.10
 * @description : 일배입고검수출력 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpInspectDailyPrintController API", description = "DpInspectDailyPrintController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/inspectDailyPrint")
public class DpInspectDailyPrintController {
	private final DpInspectDailyPrintService dpInspectDailyPrintService;

	/**
	 * @description : 일배입고검수출력 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고진행현황 조회 List", description = "입고진행현황 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Map<String, List>> getMasterList(@RequestBody DpInspectDailyPrintReqDto reqDto) {
		List<DpInspectDailyPrintResDto> masterList = dpInspectDailyPrintService.getMasterList(reqDto);
		List<DpInspectDailyPrintResPoNotMapDto> poNotMapList = dpInspectDailyPrintService.getPoNotMapList(reqDto);
		return ApiResult.createResult(new HashMap<>(){{
			put("masterList", masterList);
			put("poNotMapList", poNotMapList);
		}});
	}
	/**
	 * @description : 입고진행현황상세(구매현황) 조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고진행현황상세(구매현황) 조회 List", description = "입고진행현황상세(구매현황) 조회 List")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<DpInspectDailyPrintResDetailDto>> getDetailList(@RequestBody DpInspectDailyPrintReqDto reqDto) {
		return ApiResult.createResult(dpInspectDailyPrintService.getDetailList(reqDto));
	}

	/**
	 * @description : po미맵핑출고현황상세 조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "po미맵핑출고현황상세 조회 List", description = "po미맵핑출고현황상세 조회 List")
	@PostMapping(value = "/v1.0/getPoNotMapDetailList")
	public ApiResult<List<DpInspectDailyPrintResPoNotMapDetailDto>> getPoNotMapDetailList(@RequestBody DpInspectDailyPrintReqDto reqDto) {
		return ApiResult.createResult(dpInspectDailyPrintService.getPoNotMapDetailList(reqDto));
	}

	/**
	 * @description : 일배입고검수출력 데이터 (출력유형: 배차변경출력 외) 조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "일배입고검수지 출력 데이터 조회 List", description = "일배입고검수지 출력 데이터 조회 List")
	@PostMapping(value = "/v1.0/getPrintMasterList")
	public ApiResult<List<DpInspectDailyPrintResPrintDto>> getPrintMasterList(@RequestBody DpInspectDailyPrintReqDto reqDto) {
		return ApiResult.createResult(dpInspectDailyPrintService.getPrintMasterList(reqDto));
	}

	/**
	 * @description : 일배입고검수출력 PO맵핑
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.10 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "PO맵핑", description = "PO맵핑")
	@PostMapping(value = "/v1.0/savePoMapping")
	public ApiResult<String> savePoMapping(@RequestBody DpInspectDailyPrintReqDto dto) throws Exception {
		return ApiResult.createResult(dpInspectDailyPrintService.savePoMapping(dto));
	}
}
