package cjfw.wms.ib.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbKxStoragefeeMonthReqDto;
import cjfw.wms.ib.service.IbKxStoragefeeMonthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.11.05
 * @description : 일별 보관료 계산 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "정산 > 1000센터 정산 > 일별 보관료 계산", description = "일별 보관료 계산")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/kxStoragefeeMonth")
public class IbKxStoragefeeMonthController {
	private final IbKxStoragefeeMonthService ibKxStoragefeeMonthService;

	/**
	 * @description :  일별 보관료 계산 조회 기능을 구현한 Method 
	 * @issues : 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "일별 보관료 계산 조회 List", description = "일별 보관료 계산 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@RequestBody IbKxStoragefeeMonthReqDto dto) {
		log.info("IbKxStoragefeeMonthController.getMasterList", dto);
		return ApiResult.createResult(ibKxStoragefeeMonthService.getMasterList(dto));
	}

	/**
	 * @description : 일별 보관료 저장 기능을 구현한 Method 
	 * @issues : 
	  * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = " 일별 보관료 계산 저장", description = "일별 보관료 계산 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody IbKxStoragefeeMonthReqDto dto) {
		return ApiResult.createResult(ibKxStoragefeeMonthService.saveMasterList(dto.getSaveList()));
	}
	
	/**
	 * @description : 일별 보관료 계산 기능을 구현한 Method 
	 * @issues : 
	  * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = " 일별 보관료 계산", description = "일별 보관료 계산")
	@PostMapping(value = "/v1.0/saveClose")
	public ApiResult<Object> saveClose(@RequestBody IbKxStoragefeeMonthReqDto dto) {
		return ApiResult.createResult(ibKxStoragefeeMonthService.saveClose(dto));
	}
//
//	/**
//	 * @description :  보관료마감처리 마감/취소 기능을 구현한 Method 
//	 * @issues : 
//	 * ----------------------------------------------------------- 
//	 * DATE AUTHOR MAJOR_ISSUE 
//	 * ----------------------------------------------------------- 
//	 * 2025.08.29 ParkJinWoo 생성
//	 */
//	@Operation(summary = " 보관료마감처리 마감/취소 ", description = " 보관료마감처리 마감/취소 ")
//	@PostMapping(value = "/v1.0/saveKxStoragefeeMonth")
//	public ApiResult<Object> saveKxStoragefeeMonth(@Valid @RequestBody IbKxStoragefeeMonthReqDto dto) throws RemoteException {
//		return ApiResult.createResult(ibKxStoragefeeMonthService.saveKxStoragefeeMonth(dto));
//	}
}
