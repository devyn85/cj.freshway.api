package cjfw.wms.mg.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.mg.dto.MgModifyLogExDcDetailListReqDto;
import cjfw.wms.mg.dto.MgModifyLogExDcMasterListReqDto;
import cjfw.wms.mg.service.MgModifyLogExDcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.11 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11 ParkJinWoo 생성
 */
@Tag(name = "재고 > 출고현황 > 외부비축재고변경사유현황", description = "외부비축재고변경사유현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/mg/modifyLogExDc")
public class MgModifyLogExDcController {

	private final MgModifyLogExDcService mgModifyLogExDcService;

	/**
	 * @description : 외부비축재고변경사유현황 헤더 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고변경사유현황 List", description = "외부비축재고변경사유현황 List")
	@PostMapping(value="/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody MgModifyLogExDcMasterListReqDto mgModifyLogExDcMasterListReqDto) {
		return ApiResult.createResult(mgModifyLogExDcService.getMasterList(mgModifyLogExDcMasterListReqDto));
	}


	/**
	 * @description : 외부비축재고변경사유현황 상세 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 ParkJinWoo 생성
	 */
	@Operation(summary = "상세 내역 List", description = "상세 내역 List")
	@PostMapping(value="/v1.0/getDetailList")
	public ApiResult<Object> getDetailList( @RequestBody MgModifyLogExDcDetailListReqDto mgModifyLogExDcDetailListReqDto) {
		return ApiResult.createResult(mgModifyLogExDcService.getDetailList(mgModifyLogExDcDetailListReqDto));
	}
}
