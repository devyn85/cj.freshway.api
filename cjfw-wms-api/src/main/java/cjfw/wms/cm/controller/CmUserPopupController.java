package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmUserPopupReqDto;
import cjfw.wms.cm.dto.CmUserPopupResDto;
import cjfw.wms.cm.service.CmUserPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.11 
 * @description : 사용자 팝업 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
 */

@Tag(name = "기준정보 > 사용자및센터정보 > 사용자 팝업 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmUserPopupController {
	private final CmUserPopupService cmUserPopupService;
	
	/**
	 * @description : 사용자 팝업 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation (summary = "사용자 팝업 조회", description = "유저 목록 조회")
	@PostMapping(value = "/v1.0/getUserList")
	public ApiResult<Page<CmUserPopupResDto>> getPopList(@RequestBody CmUserPopupReqDto cmPopupReqDto, Page<?> page) {
		return ApiResult.createResult(cmUserPopupService.getPopList(cmPopupReqDto, page));
	}
	
	/** 
	 * @description : 사용자 팝업 목록 조회(전체)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.30        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation (summary = "사용자 팝업 조회", description = "유저 목록 조회")
	@PostMapping(value = "/v1.0/getAllUserList")
	public ApiResult<List<CmUserPopupResDto>> getPopAllList(@RequestBody CmUserPopupReqDto cmPopupReqDto) {
		return ApiResult.createResult(cmUserPopupService.getPopAllList(cmPopupReqDto));
	}
}
