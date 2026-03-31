package cjfw.wms.ib.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbKxStoragefeeExpenseReqDto;
import cjfw.wms.ib.service.IbKxStoragefeeExpenseService;
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
 * @description : 보관료 마감 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "정산 > 1000센터 정산 > 보관료 마감", description = "보관료 마감")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/kxStoragefeeExpense")
public class IbKxStoragefeeExpenseController {
	private final IbKxStoragefeeExpenseService ibKxStoragefeeExpenseService;

	/**
	 * @description :  보관료 마감 계산 조회 기능을 구현한 Method 
	 * @issues : 
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "보관료 마감 조회 List", description = "보관료 마감 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@RequestBody IbKxStoragefeeExpenseReqDto dto) {
		log.info("IbKxStoragefeeExpenseController.getMasterList", dto);
		return ApiResult.createResult(ibKxStoragefeeExpenseService.getMasterList(dto));
	}

	/**
	 * @description : 보관료 계산 기능을 구현한 Method 
	 * @issues : 
	  * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "보관료 계산", description = "보관료 계산")
	@PostMapping(value = "/v1.0/saveKxStorageExpense")
	public ApiResult<Object> saveKxStorageExpense(@RequestBody IbKxStoragefeeExpenseReqDto dto) {
		return ApiResult.createResult(ibKxStoragefeeExpenseService.saveKxStorageExpense(dto.getSaveList()));
	}
	
	/**
	 * @description : 보관료 계산 취소 기능을 구현한 Method 
	 * @issues : 
	  * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "보관료 계산 취소", description = "보관료 계산 취소")
	@PostMapping(value = "/v1.0/cancelKxStorageExpense")
	public ApiResult<Object> saveClose(@RequestBody IbKxStoragefeeExpenseReqDto dto) {
		return ApiResult.createResult(ibKxStoragefeeExpenseService.cancelKxStorageExpense(dto.getSaveList()));
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
//	@PostMapping(value = "/v1.0/saveKxStoragefeeExpense")
//	public ApiResult<Object> saveKxStoragefeeExpense(@Valid @RequestBody IbKxStoragefeeExpenseReqDto dto) throws RemoteException {
//		return ApiResult.createResult(ibKxStoragefeeExpenseService.saveKxStoragefeeExpense(dto));
//	}
}
