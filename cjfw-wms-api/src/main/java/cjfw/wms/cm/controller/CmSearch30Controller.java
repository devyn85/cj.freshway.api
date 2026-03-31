package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmOrganizePopupReqDto;
import cjfw.wms.cm.dto.CmOrganizePopupResDto;
import cjfw.wms.cm.service.CmSearch30Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.cj.com) 
 * @date : 2025.05.09 
 * @description : 창고 목록 조회 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Tag(name = "창고 검색", description = "창고 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch30Controller {

	private final CmSearch30Service service;

	/**
	 * @description : 창고 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
	 */
	@Operation(summary = "창고 목록 조회", description = "창고 목록 조회")
	@PostMapping(value = "/v1.0/getOrganizePopupList")
	public ApiResult<Page<CmOrganizePopupResDto>> getOrganizePopupList(@RequestBody CmOrganizePopupReqDto dto, Page<?> page) {
		return ApiResult.createResult(service.getOrganizePopupList(dto,page));
	}

}