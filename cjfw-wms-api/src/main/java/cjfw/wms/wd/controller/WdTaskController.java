package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdTaskReqDto;
import cjfw.wms.wd.dto.WdTaskResPrintDto;
import cjfw.wms.wd.dto.WdTaskResTab1DetailDto;
import cjfw.wms.wd.dto.WdTaskResTab1Dto;
import cjfw.wms.wd.dto.WdTaskResTab2DetailDto;
import cjfw.wms.wd.dto.WdTaskResTab2Dto;
import cjfw.wms.wd.dto.WdTaskResTab3Dto;
import cjfw.wms.wd.dto.WdTaskResTab4Dto;
import cjfw.wms.wd.service.WdTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.08.29
 * @description : 피킹작업지시 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "task", description = "피킹작업지시")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/task")
public class WdTaskController {

	private final WdTaskService wdTaskService;

	/**
	 * @description : 피킹작업지시-조회생성 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-조회생성 목록 조회", description = "피킹작업지시-조회생성 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdTaskResTab1Dto>> getTab1MasterList(@RequestBody WdTaskReqDto dto) {


		return ApiResult.createResult(wdTaskService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 피킹작업지시-조회생성 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-조회생성 상세 조회", description = "피킹작업지시-조회생성 상세 조회")
	@PostMapping(value = "/v1.0/getTab1DetailList")
	public ApiResult<List<WdTaskResTab1DetailDto>> getTab1DetailList(@RequestBody WdTaskReqDto dto) {


		return ApiResult.createResult(wdTaskService.getTab1DetailList(dto));
	}
	
	/**
	 * @description : 피킹작업지시-진행현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-진행현황 목록 조회", description = "피킹작업지시-진행현황 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdTaskResTab2Dto>> getTab2MasterList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getTab2MasterList(dto));
	}
	
	/**
	 * @description : 피킹작업지시-진행현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-진행현황 상세 조회", description = "피킹작업지시-진행현황 상세 조회")
	@PostMapping(value = "/v1.0/getTab2DetailList")
	public ApiResult<List<WdTaskResTab2DetailDto>> getTab2DetailList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getTab2DetailList(dto));
	}	
	
	/**
	 * @description : 피킹작업지시-피킹작업자현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-피킹작업자현황 목록 조회", description = "피킹작업지시-피킹작업자현황 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<WdTaskResTab3Dto>> getTab3MasterList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getTab3MasterList(dto));
	}	
	
	/**
	 * @description : 피킹작업지시-조회생성(차량) 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-조회생성(차량) 목록 조회", description = "피킹작업지시-조회생성(차량) 목록 조회")
	@PostMapping(value = "/v1.0/getTab4MasterList")
	public ApiResult<List<WdTaskResTab4Dto>> getTab4MasterList(@RequestBody WdTaskReqDto dto) {


		return ApiResult.createResult(wdTaskService.getTab4MasterList(dto));
	}
	
	/**
	 * @description : 피킹작업지시-피킹리스트 STD 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-피킹리스트 STD 출력 조회", description = "피킹작업지시-피킹리스트 STD 출력 조회")
	@PostMapping(value = "/v1.0/getPrintSTDList")
	public ApiResult<WdTaskResPrintDto> getPrintSTDList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getPrintSTDList(dto));
	}	
	
	/**
	 * @description : 피킹작업지시-피킹리스트 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-피킹리스트 출력 조회", description = "피킹작업지시-피킹리스트 출력 조회")
	@PostMapping(value = "/v1.0/getPrintList")
	public ApiResult<WdTaskResPrintDto> getPrintList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getPrintList(dto));
	}	
	
	/**
	 * @description : 피킹작업지시-피킹리스트 멀티 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-피킹리스트 출력 조회", description = "피킹작업지시-피킹리스트 출력 조회")
	@PostMapping(value = "/v1.0/getMultiPrintList")
	public ApiResult<WdTaskResPrintDto> getMultiPrintList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getMultiPrintList(dto));
	}	
	
	/**
	 * @description : 피킹작업지시-PLT바코드 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업지시-PLT바코드 출력 조회", description = "피킹작업지시-PLT바코드 출력 조회")
	@PostMapping(value = "/v1.0/getPrintBarcodeList")
	public ApiResult<WdTaskResPrintDto> getPrintBarcodeList(@RequestBody WdTaskReqDto dto) {
		
		
		return ApiResult.createResult(wdTaskService.getPrintBarcodeList(dto));
	}	
	

	/**
	 * @throws Exception
	 * @description : 피킹생성 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹생성 저장", description = "피킹생성 저장")
	@PostMapping(value = "/v1.0/savePickingBatch")
	public ApiResult<String> savePickingBatch(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.savePickingBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 피킹생성취소
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹생성취소", description = "피킹생성취소")
	@PostMapping(value = "/v1.0/savePickingBatchDelete")
	public ApiResult<String> savePickingBatchDelete(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.savePickingBatchDelete(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 수동피킹 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수동피킹 저장", description = "수동피킹 저장")
	@PostMapping(value = "/v1.0/saveManualPickingBatch")
	public ApiResult<String> saveManualPickingBatch(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.saveManualPickingBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 모바일피킹지시 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "모바일피킹지시 저장", description = "모바일피킹지시 저장")
	@PostMapping(value = "/v1.0/saveMobilePickingBatch")
	public ApiResult<String> saveMobilePickingBatch(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.saveMobilePickingBatch(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 피킹분리 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹분리 저장", description = "피킹분리 저장")
	@PostMapping(value = "/v1.0/saveDivisionTask")
	public ApiResult<String> saveDivisionTask(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.saveDivisionTask(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 피킹병합 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹병합 저장", description = "피킹병합 저장")
	@PostMapping(value = "/v1.0/saveMergeTask")
	public ApiResult<String> saveMergeTask(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.saveMergeTask(dto));
	}

	/**
	 * @throws Exception
	 * @description : 피킹작업자 삭제
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹작업자 삭제", description = "피킹작업자 삭제")
	@PostMapping(value = "/v1.0/deletePicker")
	public ApiResult<String> deletePicker(@RequestBody WdTaskReqDto dto) throws Exception {
		return ApiResult.createResult(wdTaskService.deletePicker(dto));
	}

}