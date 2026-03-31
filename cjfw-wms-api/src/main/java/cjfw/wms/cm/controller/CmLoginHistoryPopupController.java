package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmLoginHistoryPopupResDto;
import cjfw.wms.cm.service.CmLoginHistoryPopupService;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.10.24 
 * @description : 접속이력정보 팝업 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24  JiSooKim (jskim14@cj.net) 생성 </pre>
 */

@Tag(name = "기준정보 > 사용자및센터정보 > 접속이력정보 팝업 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/login")
public class CmLoginHistoryPopupController {
	private final CmLoginHistoryPopupService cmLoginHistoryPopupService;
	
	
	/** 
	 * @description : 접속이력정보 팝업 목록 조회(전체)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24  JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation (summary = "접속이력정보 팝업 조회", description = "접속이력정보 팝업 조회")
	@GetMapping(value = "/v1.0/getLoginHistoryList")
	public ApiResult<List<CmLoginHistoryPopupResDto>> getLoginHistoryList(CommonDto cmPopupReqDto) {
		return ApiResult.createResult(cmLoginHistoryPopupService.getLoginHistoryList(cmPopupReqDto));
	}
}
