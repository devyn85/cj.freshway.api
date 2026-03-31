package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmDcXOrganizeManagerReqDto;
import cjfw.wms.cm.dto.CmDcXOrganizeManagerResDto;
import cjfw.wms.cm.service.CmDcXOrganizeManagerService;
import cjfw.wms.st.dto.StAdjustmentResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangJaeHyun (jhjang43@cj.net)
 * @date : 2025.07.17 
 * @description : 기준정보 > 사용자및센터정보 > 물류센터별창고관리
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 사용자및센터정보 > 물류센터별창고관리", description = "물류센터별창고관리 정보 관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/dcXOrganize")
public class CmDcXOrganizeManagerController {

	private final CmDcXOrganizeManagerService cmDcXOrganizeManagerService;
	
	/**
	 * @description : 물류센터별창고관리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "물류센터별창고관리 목록 조회", description = "물류센터별창고관리 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<CmDcXOrganizeManagerResDto>> getUserList(@Valid CmDcXOrganizeManagerReqDto cmDcXOrganizeManagerReqDto) {
		return ApiResult.createResult(cmDcXOrganizeManagerService.getMasterList(cmDcXOrganizeManagerReqDto));
	}

	/**
	 * @description : 물류센터관리 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "물류센터관리 목록 저장", description = "물류센터관리 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<CmDcXOrganizeManagerReqDto> dto) {
		return ApiResult.createResult(cmDcXOrganizeManagerService.saveMasterList(dto));
	}
	
	/**
	 * @description : 창고 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "창고 조회 List", description = "창고  조회 List")
	@GetMapping(value = "/v1.0/getOrganizeList")
	public ApiResult<List<StAdjustmentResDto>> getOrganizeList(CmDcXOrganizeManagerReqDto reqDto) {
		return ApiResult.createResult(cmDcXOrganizeManagerService.getOrganizeList(reqDto));
	}		
	
	
}