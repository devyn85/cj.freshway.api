package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPickBatchGroupPlantResDto;
import cjfw.wms.ms.dto.MsPickBatchGroupReqDto;
import cjfw.wms.ms.dto.MsPickBatchGroupResDto;
import cjfw.wms.ms.service.MsPickBatchGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.23
 * @description : 기준정보 > 센터기준정보 > 피킹그룹정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 센터기준정보 > 피킹그룹정보", description = "피킹그룹정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/pickBatchGroup")
public class MsPickBatchGroupController {

	private final MsPickBatchGroupService msPickBatchGroupService;

	/**
	 * 
	 * @description : 피킹그룹정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "피킹그룹정보 목록 조회", description = "피킹그룹정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsPickBatchGroupResDto>> getMasterList(@RequestBody MsPickBatchGroupReqDto dto) {
		return ApiResult.createResult(msPickBatchGroupService.getMasterList(dto));
	}
		
	/**
	 * @description : 피킹그룹정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "피킹그룹정보 목록 저장", description = "피킹그룹정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsPickBatchGroupReqDto> dto) {
		return ApiResult.createResult(msPickBatchGroupService.saveMasterList(dto));
	}
	
	/**
	 * 
	 * @description : 플랜트 정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.30        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "플랜트 정보 목록 조회", description = "플랜트 정보 목록 조회")
	@GetMapping(value = "/v1.0/getDataPlant")
	public ApiResult<List<MsPickBatchGroupPlantResDto>> getDataPlant(MsPickBatchGroupReqDto dto) {
		return ApiResult.createResult(msPickBatchGroupService.getDataPlant(dto));
	}
	
}