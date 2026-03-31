package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCustHistPopupReqDto;
import cjfw.wms.ms.dto.MsCustHistPopupResDto;
import cjfw.wms.ms.service.MsCustHistPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.29 
 * @description : 이력정보 상세정보 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 이력정보 상세정보팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/custHist")
public class MsCustHistPopupController {
	private final MsCustHistPopupService msCustHistPopupService;
	
	/**
	 * @description : 이력정보 상세정보 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.29 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력정보 상세정보 목록 조회", description = "이력정보 상세정보 목록 조회")
	@GetMapping(value = "/v1.0/getCustHistList")
	public ApiResult<List<MsCustHistPopupResDto>> getCustHistList(MsCustHistPopupReqDto dto) {
		return ApiResult.createResult(msCustHistPopupService.getCustHistList(dto));
	}
}
