package cjfw.wms.ib.controller;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbCloseStockSkuBillPopupReqDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeReqDto;
import cjfw.wms.ib.dto.IbCloseStoragefeeStatusReqDto;
import cjfw.wms.ib.service.IbCloseStoragefeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.08.29 
 * @description : 보관료마감처리 기능을 구현한 Contoller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 ParkJinWoo 생성
 */
@Tag(name = "정산 > 보관료마감 > 보관료마감", description = "보관료마감")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/closeStoragefee")
public class IbCloseStoragefeeController {
	private final IbCloseStoragefeeService ibCloseStoragefeeService;

	
	 /**
	  * @description : 보관료마감처리 현황 조회 기능을 구현한 Method 
	  * @issues : 
	  * ----------------------------------------------------------- 
	  * DATE AUTHOR MAJOR_ISSUE 
	  * ----------------------------------------------------------- 
	  * 2025.08.29 ParkJinWoo 생성
	  */
	@Operation(summary = " 보관료마감처리 현황 조회", description = " 보관료마감처리 현황 조회")
	@PostMapping(value = "/v1.0/getDataStatusHeaderlist")
	public ApiResult<Object> getDataStatusHeaderlist(@RequestBody IbCloseStoragefeeStatusReqDto dto) {
		return ApiResult.createResult(ibCloseStoragefeeService.getDataStatusHeaderlist(dto));
	}	

	/**
	 * @description :  보관료마감처리 현황 마감 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	@Operation(summary = "마감상태 관리 조회 List", description = "마감상태관리 조회 List")
	@PostMapping(value = "/v1.0/getDataHeaderlist")
	public ApiResult<Object> getDataHeaderlist(@RequestBody IbCloseStoragefeeReqDto dto) {
		return ApiResult.createResult(ibCloseStoragefeeService.getDataHeaderlist(dto));
	}	

	/**
	 * @description : 보관료마감처리 마감 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	@Operation(summary = " 보관료마감처리 마감/취소 ", description = " 보관료마감처리 마감/취소 ")
	@PostMapping(value = "/v1.0/saveClose")
	public ApiResult<Object> saveClose(@Valid @RequestBody IbCloseStoragefeeReqDto dto) throws RemoteException {
		return ApiResult.createResult(ibCloseStoragefeeService.saveClose(dto));
	}	

	/**
	 * @description :  보관료마감처리 마감/취소 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	@Operation(summary = " 보관료마감처리 마감/취소 ", description = " 보관료마감처리 마감/취소 ")
	@PostMapping(value = "/v1.0/saveCloseStoragefee")
	public ApiResult<Object> saveCloseStoragefee(@Valid @RequestBody IbCloseStoragefeeReqDto dto) throws RemoteException {
		return ApiResult.createResult(ibCloseStoragefeeService.saveCloseStoragefee(dto));
	}	

	/**
	 * @description : 보관료마감처리 강제마감 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	@Operation(summary = " 보관료마감처리 강제마감 ", description = "보관료마감처리 강제마감")
	@PostMapping(value = "/v1.0/saveCloseStoragefeeWMS")
	public ApiResult<Object> saveCloseStoragefeeWMS(@Valid @RequestBody IbCloseStoragefeeReqDto dto) throws RemoteException {
		return ApiResult.createResult(ibCloseStoragefeeService.saveCloseStoragefeeWMS(dto));
	}	
	
	/**
	 * @description : 보관료마감처리 마감내역 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 ParkJinWoo 생성
	 */
	@Operation(summary = "보관료마감처리 마감내역 조회", description = "보관료마감처리 마감내역 조회")
	@PostMapping(value = "/v1.0/getDataRead")
	public ApiResult<Object> getDataRead(@Valid @RequestBody IbCloseStockSkuBillPopupReqDto dto) throws RemoteException {
		return ApiResult.createResult(ibCloseStoragefeeService.getDataRead(dto));
	}
}
