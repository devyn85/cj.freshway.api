package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdSerialOrderSTOReqDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResPickingDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab1Dto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2Dto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2ShortageDto;
import cjfw.wms.wd.dto.WdSerialOrderSTOResTab2WdDto;
import cjfw.wms.wd.service.WdSerialOrderSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.09.29
 * @description : 이력STO출고처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "serialOrderSTO", description = "이력STO출고처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/serialOrderSTO")
public class WdSerialOrderSTOController {

	private final WdSerialOrderSTOService wdSerialOrderSTOService;

	/**
	 * @description : 이력STO출고처리-조회생성 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-STO생성 목록 조회", description = "이력STO출고처리-STO생성 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdSerialOrderSTOResTab1Dto>> getTab1MasterList(@RequestBody WdSerialOrderSTOReqDto dto) {


		return ApiResult.createResult(wdSerialOrderSTOService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 이력STO출고처리-STO출고확정 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-STO출고확정 조회", description = "이력STO출고처리-STO출고확정 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdSerialOrderSTOResTab2Dto>> getTab2MasterList(@RequestBody WdSerialOrderSTOReqDto dto) {


		return ApiResult.createResult(wdSerialOrderSTOService.getTab2MasterList(dto));
	}
	
	/**
	 * @description : 이력STO출고처리-출고대상 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-출고대상 상세 조회", description = "이력STO출고처리-출고대상 상세 조회")
	@PostMapping(value = "/v1.0/getTab2DetailWDList")
	public ApiResult<List<WdSerialOrderSTOResTab2WdDto>> getTab2DetailWDList(@RequestBody WdSerialOrderSTOReqDto dto) {
		
		
		return ApiResult.createResult(wdSerialOrderSTOService.getTab2DetailWDList(dto));
	}	
	
	/**
	 * @description : 이력STO출고처리-결품대상 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-결품대상 조회", description = "이력STO출고처리-결품대상 조회")
	@PostMapping(value = "/v1.0/getTab2DetailShortageList")
	public ApiResult<List<WdSerialOrderSTOResTab2ShortageDto>> getTab2DetailShortageList(@RequestBody WdSerialOrderSTOReqDto dto) {
		
		
		return ApiResult.createResult(wdSerialOrderSTOService.getTab2DetailShortageList(dto));
	}	
	
	/**
	 * @description : 이력STO출고처리-피킹 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-피킹 조회", description = "이력STO출고처리-피킹 조회")
	@PostMapping(value = "/v1.0/getPickingList")
	public ApiResult<List<WdSerialOrderSTOResPickingDto>> getPickingList(@RequestBody WdSerialOrderSTOReqDto dto) {
		
		
		return ApiResult.createResult(wdSerialOrderSTOService.getPickingList(dto));
	}	
	

	/**
	 * @throws Exception
	 * @description : 이력STO출고처리-STO생성 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-STO생성 저장", description = "이력STO출고처리-STO생성 저장")
	@PostMapping(value = "/v1.0/saveCreationSTOList")
	public ApiResult<String> saveCreationSTOList(@RequestBody WdSerialOrderSTOReqDto dto) throws Exception {
		return ApiResult.createResult(wdSerialOrderSTOService.saveCreationSTOList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 이력STO출고처리-STO출고확정 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-STO출고확정 저장", description = "이력STO출고처리-STO출고확정 저장")
	@PostMapping(value = "/v1.0/saveBatchConfirm")
	public ApiResult<String> saveBatchConfirm(@RequestBody WdSerialOrderSTOReqDto dto) throws Exception {
		return ApiResult.createResult(wdSerialOrderSTOService.saveBatchConfirm(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 이력STO출고처리-출고대상확정 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-출고대상확정 저장", description = "이력STO출고처리-출고대상확정 저장")
	@PostMapping(value = "/v1.0/saveBatchConfirmLine")
	public ApiResult<String> saveBatchConfirmLine(@RequestBody WdSerialOrderSTOReqDto dto) throws Exception {
		return ApiResult.createResult(wdSerialOrderSTOService.saveBatchConfirmLine(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 이력STO출고처리-결품대상확정 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-결품대상확정 저장", description = "이력STO출고처리-결품대상확정 저장")
	@PostMapping(value = "/v1.0/saveBatchCancelLine")
	public ApiResult<String> saveBatchCancelLine(@RequestBody WdSerialOrderSTOReqDto dto) throws Exception {
		return ApiResult.createResult(wdSerialOrderSTOService.saveBatchCancelLine(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 이력STO출고처리-SO&STO분리
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력STO출고처리-SO&STO분리", description = "이력STO출고처리-SO&STO분리")
	@PostMapping(value = "/v1.0/saveDistribute")
	public ApiResult<String> saveDistribute(@RequestBody WdSerialOrderSTOReqDto dto) throws Exception {
		return ApiResult.createResult(wdSerialOrderSTOService.saveDistribute(dto));
	}
	
}