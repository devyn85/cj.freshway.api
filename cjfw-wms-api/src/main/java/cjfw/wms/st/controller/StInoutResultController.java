package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StInoutResultReqDto;
import cjfw.wms.st.dto.StInoutResultResDetailDto;
import cjfw.wms.st.dto.StInoutResultResDto;
import cjfw.wms.st.dto.StInoutResultResPrintDto;
import cjfw.wms.st.service.StInoutResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 수불현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StInoutResultController API", description = "StInoutResultController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/inoutResult")
public class StInoutResultController {
	private final StInoutResultService stInoutResultService;

	/**
	 * @description : 수불현황 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "수불현황 조회 List", description = "수불현황 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StInoutResultResDto>> getMasterList(@RequestBody StInoutResultReqDto reqDto) {
		return ApiResult.createResult(stInoutResultService.getMasterList(reqDto));
	}

	/**
	 * @description : 수불현황 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "수불현황 상세 조회 List", description = "수불현황 상세 조회 List")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<StInoutResultResDetailDto>> getDetailList(@Valid StInoutResultReqDto reqDto) {
		return ApiResult.createResult(stInoutResultService.getDetailList(reqDto));
	}
	
	/**
	 * @description : 수불현황 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "수불현황 출력 조회 List", description = "수불현황 출력 조회 List")
	@PostMapping(value = "/v1.0/printMasterList")
	public ApiResult<StInoutResultResPrintDto> printMasterList(@RequestBody StInoutResultReqDto reqDto) {
		return ApiResult.createResult(stInoutResultService.printMasterList(reqDto));
	}	
}
