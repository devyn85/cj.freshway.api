package cjfw.wms.dp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpTaskReqDto;
import cjfw.wms.dp.dto.DpTaskResDetailDto;
import cjfw.wms.dp.dto.DpTaskResDto;
import cjfw.wms.dp.service.DpTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.31
 * @description : 입고검수지정 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpTaskController API", description = "DpTaskController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/task")
public class DpTaskController {
	private final DpTaskService dpTaskService;

	/**
	 * @description : 입고검수지정 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고검수지정 조회 List", description = "입고검수지정 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Map> getMasterList(@RequestBody DpTaskReqDto reqDto) {
		List<DpTaskResDto> masterList = dpTaskService.getMasterList(reqDto);
		List<DpTaskResDetailDto> detailList = dpTaskService.getDetailList(reqDto);
		return ApiResult.createResult(new HashMap<>(){{
			put("masterList", masterList);
			put("detailList", detailList);
		}});
	}

	/**
	 * @description : 입고검수지정 상세 조회 List Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
//	@Operation(summary = "입고검수지정 상세 조회 List", description = "입고검수지정 상세 조회 List")
//	@PostMapping(value = "/v1.0/getDetailList")
//	public ApiResult<List<DpTaskResDetailDto>> getDetailList(@RequestBody DpTaskReqDto reqDto) {
//		return ApiResult.createResult(dpTaskService.getDetailList(reqDto));
//	}

	/**
	 * @description : 입고검수지정 조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "입고검수지정2 조회 List", description = "입고검수지정2 조회 List")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<Map> getMasterList2(@RequestBody DpTaskReqDto reqDto) {
		List<DpTaskResDto> masterList = dpTaskService.getMasterList2(reqDto);
		List<DpTaskResDetailDto> detailList = dpTaskService.getDetailList2(reqDto);
		return ApiResult.createResult(new HashMap<>(){{
			put("masterList", masterList);
			put("detailList", detailList);
		}});
	}

	/**
	 * @description : 입고검수지정 상세 조회 List Method
	 * @issues :
	 *
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
//	@Operation(summary = "입고검수지정 상세2 조회 List", description = "입고검수지정 상세2 조회 List")
//	@PostMapping(value = "/v1.0/getDetailList2")
//	public ApiResult<List<DpTaskResDetailDto>> getDetailList2(@RequestBody DpTaskReqDto reqDto) {
//		return ApiResult.createResult(dpTaskService.getDetailList2(reqDto));
//	}

	/**
	 * @description : 입고검수지정 지정삭제
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 KimDongHyeon (tirran123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지정삭제", description = "지정삭제")
	@PostMapping(value = "/v1.0/saveDpTask")
	public ApiResult<String> saveDpTask(@RequestBody DpTaskReqDto dto) throws Exception {
		return ApiResult.createResult(dpTaskService.saveDpTask(dto));
	}
}
