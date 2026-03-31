package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmMngplcIDUndoneReqDto;
import cjfw.wms.tm.dto.TmMngplcIDUndoneResDto;
import cjfw.wms.tm.service.TmMngplcIDUndoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.11.20
 * @description : 분할 미적용 관리처 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.20 JiHoPark  생성 </pre>
 */
@Tag(name = "TmMngplcIDUndone", description = "분할 미적용 관리처(배송 > 배차작업 > 분할 미적용 관리처)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/mngplcidundone")
public class TmMngplcIDUndoneController {
	private final TmMngplcIDUndoneService tmMngplcIDUndoneService;

	/**
	 * @description : 재고조정 요청/처리 - 재고조정 요청 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.20 JiHoPark  생성 </pre>
	 */
	@Operation(summary = "분할 미적용 관리처 - 분할 미적용 관리처 목록 조회", description = "분할 미적용 관리처 - 분할 미적용 관리처 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmMngplcIDUndoneResDto>> getMasterList(@Valid @RequestBody TmMngplcIDUndoneReqDto dto) {
		return ApiResult.createResult(tmMngplcIDUndoneService.getMasterList(dto));
	}
}
