package cjfw.wms.wd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInplanOutOrgSoReqDto;
import cjfw.wms.wd.dto.WdInplanOutOrgStoReqDto;
import cjfw.wms.wd.service.WdInplanOutOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.11.27 
 * @description : 외부창고운송비현황 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.27 ParkJinWoo 생성
 */
@Tag(name = "WdInplanOutOrg", description = "외부창고운송비현황(정산 > 운송비정산 > 외부창고운송비현황)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/innplanOutOrg")
public class WdInplanOutOrgController {
	private final WdInplanOutOrgService wdInplanOutOrgService;

	/**
	 * @description : 외부창고운송비현황 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고운송비현황 SO조회", description = "외부창고운송비현황 SO조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Object> getMasterList(@Valid @RequestBody WdInplanOutOrgSoReqDto dto) {
		return ApiResult.createResult(wdInplanOutOrgService.getMasterList(dto));
	}

	/**
	 * @description : 외부창고운송비현황 STO조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 ParkJinWoo 생성
	 */
	@Operation(summary = "외부창고운송비현황 STO조회", description = "외부창고운송비현황 STO조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<Object> getMasterList2(@Valid @RequestBody WdInplanOutOrgStoReqDto dto) {
		return ApiResult.createResult(wdInplanOutOrgService.getMasterList2(dto));
	}

}
