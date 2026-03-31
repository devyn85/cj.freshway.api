package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDriverReqDto;
import cjfw.wms.ms.dto.MsDriverResDto;
import cjfw.wms.ms.service.MsDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : SangSuSung(kduimux@cj.com) 
 * @date : 2025.05.10 
 * @description : ADMIN > 시스템운영 > 기사정보 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.10 SangSuSung(kduimux@cj.cj.com) 생성 </pre>
 */
@Tag(name = "기준정보 > 차량기준정보 > 기사정보", description = "기사정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/driver")
public class MsDriverController {

	private final MsDriverService msDriverService;

	/**
	 * @description : 기사정보 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "기사정보 목록 조회", description = "기사정보 목록 조회")
	@GetMapping(value = "/v1.0/getDatalist")
	public ApiResult<List<MsDriverResDto>> getDatalist(MsDriverReqDto dto) {
		return ApiResult.createResult(msDriverService.getDatalist(dto));
	}

	/**
	 * @description : 기사정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "기사정보 목록 저장", description = "기사정보 목록 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<Object> saveProgram(@RequestBody List<MsDriverReqDto> dto) {
		return ApiResult.createResult(msDriverService.saveConfirm(dto));
	}
}