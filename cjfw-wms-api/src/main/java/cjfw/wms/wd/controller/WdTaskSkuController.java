package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdTaskSkuReqDto;
import cjfw.wms.wd.dto.WdTaskSkuResDetailDto;
import cjfw.wms.wd.dto.WdTaskSkuResDto;
import cjfw.wms.wd.service.WdTaskSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.09.29
 * @description : 피킹작업지시(상품별) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "taskSku", description = "피킹작업지시(상품별)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/taskSku")
public class WdTaskSkuController {

	private final WdTaskSkuService wdTaskSkuService;

	/**
	 * @description : 피킹작업지시(상품별)-조회생성 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시(상품별)-조회생성 목록 조회", description = "피킹작업지시(상품별)-조회생성 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdTaskSkuResDto>> getMasterList(@RequestBody WdTaskSkuReqDto dto) {


		return ApiResult.createResult(wdTaskSkuService.getMasterList(dto));
	}
	
	/**
	 * @description : 피킹작업지시(상품별)-조회생성 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시(상품별)-조회생성 상세 조회", description = "피킹작업지시(상품별)-조회생성 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<WdTaskSkuResDetailDto>> getDetailList(@RequestBody WdTaskSkuReqDto dto) {


		return ApiResult.createResult(wdTaskSkuService.getDetailList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 피킹지시(대상확정) 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹지시(대상확정) 저장", description = "피킹지시(대상확정) 저장")
	@PostMapping(value = "/v1.0/savePickingBatch")
	public ApiResult<String> savePickingBatch(@RequestBody WdTaskSkuReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskSkuService.savePickingBatch(dto));
	}
	
}