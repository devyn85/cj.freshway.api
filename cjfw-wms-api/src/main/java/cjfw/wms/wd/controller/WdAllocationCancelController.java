package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdAllocationCancelDetailResDto;
import cjfw.wms.wd.dto.WdAllocationCancelDetailSubResDto;
import cjfw.wms.wd.dto.WdAllocationCancelReqDto;
import cjfw.wms.wd.dto.WdAllocationCancelResDto;
import cjfw.wms.wd.service.WdAllocationCancelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.24
 * @description : 출고분배취소 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdAllocationCancel", description = "출고분배취소")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/allocationCancel")
public class WdAllocationCancelController {

	private final WdAllocationCancelService wdAllocationCancelService;

	/**
	 * @description : 자동취소 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동취소 목록 조회", description = "자동취소 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdAllocationCancelResDto>> getMasterList(@RequestBody WdAllocationCancelReqDto dto) {


		return ApiResult.createResult(wdAllocationCancelService.getMasterList(dto));
	}
	
	/**
	 * @description : 자동취소 상세 및 지정취소 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동취소 상세 및 지정취소 목록 조회", description = "자동취소 상세 및 지정취소 목록 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<WdAllocationCancelDetailResDto>> getDetailList(@RequestBody WdAllocationCancelReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationCancelService.getDetailList(dto));
	}
	
	/**
	 * @description : 지정취소 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정취소 상세 조회", description = "지정취소 상세 조회")
	@PostMapping(value = "/v1.0/getDetailSubList")
	public ApiResult<List<WdAllocationCancelDetailSubResDto>> getDetailSubList(@RequestBody WdAllocationCancelReqDto dto) {
		
		
		return ApiResult.createResult(wdAllocationCancelService.getDetailSubList(dto));
	}

	/**
	 * @description : 차량별취소 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량별취소 목록 조회", description = "차량별취소 목록 조회")
	@PostMapping(value = "/v1.0/getMasterListCarno")
	public ApiResult<List<WdAllocationCancelResDto>> getMasterListCarno(@RequestBody WdAllocationCancelReqDto dto) {


		return ApiResult.createResult(wdAllocationCancelService.getMasterListCarno(dto));
	}
	
	

	/**
	 * @throws Exception
	 * @description : 자동취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동취소 저장", description = "자동취소 저장")
	@PostMapping(value = "/v1.0/saveAutoBatch")
	public ApiResult<String> saveAutoBatch(@RequestBody WdAllocationCancelReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationCancelService.saveAutoBatch(dto));
	}
	
	
	/**
	 * @throws Exception
	 * @description : 차량번호별취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량번호별취소 저장", description = "차량번호별취소 저장")
	@PostMapping(value = "/v1.0/saveCarnoBatch")
	public ApiResult<String> saveCarnoBatch(@RequestBody WdAllocationCancelReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationCancelService.saveCarnoBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 지정취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정취소 저장", description = "지정취소 저장")
	@PostMapping(value = "/v1.0/saveAllocatonBatch")
	public ApiResult<String> saveAllocatonBatch(@RequestBody WdAllocationCancelReqDto dto) throws Exception {
		return ApiResult.createResult(wdAllocationCancelService.saveAllocatonBatch(dto));
	}
	

}