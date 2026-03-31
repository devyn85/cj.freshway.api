package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsExDCStatusDetailReqDto;
import cjfw.wms.ms.dto.MsExDCStatusDetailResDto;
import cjfw.wms.ms.dto.MsExDCStatusListReqDto;
import cjfw.wms.ms.dto.MsExDCStatusListResDto;
import cjfw.wms.ms.service.MsExDCStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.16
 * @description : OO 기능을 구현한 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.16 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 외부창고 현황 관리", description = "외부창고 현황 관리 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/exdcstatus")
public class MsExDCStatusController {

	private final MsExDCStatusService msExDCStatusService;

/**
 * @description : 저장위치정보 목록 조회
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
 */
	@Operation(summary = "저장위치정보 목록 조회", description = "저장위치정보 목록 조회")
	@PostMapping(value = "/v1.0/getExDCStatusList")
	public ApiResult<List<MsExDCStatusListResDto>> getExDCStatusList(@Valid @RequestBody MsExDCStatusListReqDto msExDCStatusReqDto) {
		return ApiResult.createResult(msExDCStatusService.getExDCStatusList(msExDCStatusReqDto));
	}

	/**
	 * @description : 저장위치정보 상세 조회
	 * @issues :
	 * -----------------------------------------------------------
	 * DATE AUTHOR MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.29 KwonJungYun (jungyun8667@cj.net) 생성
	 */
	@Operation(summary = "저장위치정보 상세 조회", description = "저장위치정보 상세 조회")
	@GetMapping(value = "/v1.0/getExDCStatusDtl")
	public ApiResult<MsExDCStatusDetailResDto> getExDCStatusDtl(@Valid MsExDCStatusDetailReqDto msExDCStatusReqDto) {
		return ApiResult.createResult(msExDCStatusService.getExDCStatusDtl(msExDCStatusReqDto));
	}

	/**
	 * @description : 플랜트 셀렉트박스 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "저장위치정보 상세 저장", description = "저장위치정보 상세 저장")
	@GetMapping(value = "/v1.0/getPlantList")
	public ApiResult<List<Map<String,String>>> getPlantList() {
		return ApiResult.createResult(msExDCStatusService.getPlantList());
	}
}