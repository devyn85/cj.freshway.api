package cjfw.wms.portal.common.sample.controller;

import cjfw.core.model.ApiResult;
import cjfw.wms.portal.common.sample.dto.CarDriverGetResDto;
import cjfw.wms.portal.common.sample.dto.CarDriverReqDto;
import cjfw.wms.portal.common.sample.service.CarDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.04.17
 * @description : 샘플 기능을 구현한 Controller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "차량관리", description = "차량관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sample/car/driver")
public class CarDriverController {
	
	private final CarDriverService carDriverService;

	/**
	 * 차량정보 목록을 검색한다.<br>
	 */
	@Operation(summary = "차량정보 목록", description = "차량정보 목록 조회")
	@GetMapping(value = "/search")
	public ApiResult<List<CarDriverGetResDto>> getCarDriverList(@Valid CarDriverReqDto carDriverReqDto) {
		return ApiResult.createResult(carDriverService.getCarDriverList(carDriverReqDto));
	}

	/**
	 * 차량정보를 업데이트 한다.<br>
	 */
	@Operation(summary = "차량정보 저장", description = "차량정보 저장(등록/수정/삭제)")
	@PostMapping(value = "/save")
	public ApiResult saveCommonCd(@RequestBody @Valid CarDriverReqDto carDriverReqDto) {
		return ApiResult.createResult(carDriverService.saveCarDriver(carDriverReqDto));
	}

}

