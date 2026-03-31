package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoDetailResDto;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoReqDto;
import cjfw.wms.kp.dto.KpOrderQtyStoInfoResDto;
import cjfw.wms.kp.service.KpOrderQtyStoInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.20
 * @description : 이체및출고현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "KpOrderQtyStoInfo", description = "이체및출고현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/orderQtyStoInfo")
public class KpOrderQtyStoInfoController {

	private final KpOrderQtyStoInfoService kpOrderQtyStoInfoService;

	/**
	 * @description : 이체및출고현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이체및출고현황 목록 조회", description = "이체및출고현황 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpOrderQtyStoInfoResDto>> getMasterlist(@Valid KpOrderQtyStoInfoReqDto dto) {

		return ApiResult.createResult(kpOrderQtyStoInfoService.getMasterList(dto));
	}
	
	/**
	 * @description : 이체및출고현황 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이체및출고현황 상세 조회", description = "이체및출고현황 상세 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<KpOrderQtyStoInfoDetailResDto>> getDetailList(@Valid KpOrderQtyStoInfoReqDto dto) {

		return ApiResult.createResult(kpOrderQtyStoInfoService.getDetailList(dto));
	}


}