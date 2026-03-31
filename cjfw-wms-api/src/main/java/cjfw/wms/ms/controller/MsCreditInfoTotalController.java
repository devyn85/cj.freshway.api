package cjfw.wms.ms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCreditInfoDto;
import cjfw.wms.ms.service.MsCreditInfoTotalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.10 
 * @description : 여신정보 IF Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.10 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Tag(name = "여신정보 현행화 IF", description = "여신정보 현행화 IF")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/creditInfoTotal")
public class MsCreditInfoTotalController {

	private final MsCreditInfoTotalService msCreditInfoTotalService;

	/**
	 * @description : 기사정보 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "여신정보 현행화 IF", description = "여신정보 현행화 IF")
	@PostMapping(value = "/v1.0/saveData")
	public ApiResult<String> saveData(@RequestBody MsCreditInfoDto dto) {
		return ApiResult.createResult(msCreditInfoTotalService.saveData(dto));
	}

}