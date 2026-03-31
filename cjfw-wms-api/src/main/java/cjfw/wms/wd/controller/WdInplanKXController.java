package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInplanKXReqDto;
import cjfw.wms.wd.dto.WdInplanKXResDto;
import cjfw.wms.wd.service.WdInplanKXService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.11.28 
 * @description : 출고 > 출고현황 > KX출고진행현황 조회 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.28 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "출고 > 출고현황 > KX출고진행현황", description = "KX출고진행현황 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/inplanKX") 
public class WdInplanKXController {

	private final WdInplanKXService wdInplanKXService;

	/**
	 * @description : KX출고진행현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.28 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX출고진행현황 조회", description = "KX출고진행현황 조회")
	@PostMapping(value = "/v1.0/getInplanKXList")
	public ApiResult<List<WdInplanKXResDto>> getInplanKXList(@RequestBody WdInplanKXReqDto wdInplanKXReqDto) {
		return ApiResult.createResult(wdInplanKXService.getInplanKXList(wdInplanKXReqDto));
	}

	
}