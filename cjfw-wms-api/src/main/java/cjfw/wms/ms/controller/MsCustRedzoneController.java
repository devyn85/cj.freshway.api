package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCustPopupReqDto;
import cjfw.wms.cm.dto.CmCustPopupResDto;
import cjfw.wms.ms.dto.MsCustRedzoneReqDto;
import cjfw.wms.ms.dto.MsCustRedzoneResDto;
import cjfw.wms.ms.service.MsCustRedzoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.05.27 
 * @description : 특별관리고객현황 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 특별관리고객현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/custredzone")
public class MsCustRedzoneController {
	private final MsCustRedzoneService msCustRedzoneService;
	
	/**
	 * @description : 특별관리고객 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.27 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "특별관리고객 목록 조회", description = "특별관리고객 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCustRedzoneResDto>> getMasterList(@RequestBody MsCustRedzoneReqDto dto) {
		return ApiResult.createResult(msCustRedzoneService.getMasterList(dto));
	}
	
	/**
	 * 거래처 데이터 조회
	 */
	@PostMapping(value="/v1.0/getCustPopupList")
	public ApiResult<Page<CmCustPopupResDto>> getCustPopupPagingList(@RequestBody CmCustPopupReqDto CmCustPopupReqDto, Page page) {
		return ApiResult.createResult(msCustRedzoneService.getCustPopupPagingList(CmCustPopupReqDto, page));
	}
}
