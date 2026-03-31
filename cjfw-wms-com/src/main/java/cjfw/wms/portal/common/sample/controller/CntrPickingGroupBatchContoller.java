package cjfw.wms.portal.common.sample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchsaveReqDto;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchGetResDto;
import cjfw.wms.portal.common.sample.service.CntrPickingGroupBatchService;
import cjfw.wms.portal.common.sample.dto.CntrPickingGroupBatchGetReqDto;

import cjfw.core.model.ApiResult;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : 박진우 (jwpark1104@cj.net)
 * @date        : 2025.04.21
 * @description : 피킹그룹 기능을 구현한 Conroller Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.17        박진우 (jwpark1104@cj.net)       생성
 */
@Tag(name = "피킹그룹정보" , description = "피킹그룹정보 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sample/cntr/pickingBatchGroup")
public class CntrPickingGroupBatchContoller {
	private final CntrPickingGroupBatchService CntrPickingGroupBatchService;
	
	/**
	 * 피킹그룹 조회기능 
	 * @param CntrPickingGroupBatchGetReqDto
	 * @return
	 */
	@GetMapping(value = "/search")
	public ApiResult<List<CntrPickingGroupBatchGetResDto>> getPickingGroup(@Valid CntrPickingGroupBatchGetReqDto CntrPickingGroupBatchGetReqDto){
		
		return ApiResult.createResult(CntrPickingGroupBatchService.getPickingGroup(CntrPickingGroupBatchGetReqDto));
	}
	/**
	 * 피킹 그룹 상세조회
	 * @param CntrPickingGroupBatchGetReqDto
	 * @return
	 */
	@GetMapping(value = "/searchDetaile")
	public ApiResult<List<CntrPickingGroupBatchGetResDto>> searchDetaile(@Valid CntrPickingGroupBatchGetReqDto CntrPickingGroupBatchGetReqDto){
		
		return ApiResult.createResult(CntrPickingGroupBatchService.searchDetaile(CntrPickingGroupBatchGetReqDto));
	}
	
	/**
	 * 피킹그룹 저장(단일)
	 * @param CntrPickingGroupBatchsaveReqDto
	 * @return
	 */
	@PostMapping(value="/save")
	public ApiResult savePickingGroup(@RequestBody @Valid CntrPickingGroupBatchsaveReqDto CntrPickingGroupBatchsaveReqDto){
		
		return ApiResult.createResult(CntrPickingGroupBatchService.savePickingGroup(CntrPickingGroupBatchsaveReqDto));
	}
	/**
	 * 피킹그룹 저장(단일)
	 * @param CntrPickingGroupBatchsaveReqDto
	 * @return
	 */
	@GetMapping(value="/getDccode")
	public ApiResult getDccode(){
		
		return ApiResult.createResult(CntrPickingGroupBatchService.getDccode());
	}
}
