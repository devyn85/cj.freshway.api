package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmPopPopupReqDto;
import cjfw.wms.cm.dto.CmPopPopupResDto;
import cjfw.wms.cm.service.CmPopPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.13 
 * @description : POP 조회 팝업 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */

@Tag(name = "기준정보 > 권역기준정보 > POP조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmPopPopupController {
	private final CmPopPopupService cmPopPopupService;
	
	/**
	 * @description : POP 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation (summary = "POP 목록 조회", description = "POP 목록 조회")
	@PostMapping(value = "/v1.0/getPopList")
	public ApiResult<List<CmPopPopupResDto>> getPopList(@ModelAttribute CmPopPopupReqDto cmPopPopupReqDto) {
		return ApiResult.createResult(cmPopPopupService.getPopList(cmPopPopupReqDto));
	}

		
//	public ApiResult<Page<CmPopPopupResDto>> getPopList(@RequestBody CmPopPopupReqDto cmPopPopupReqDto, Page<?> page) {
//		return ApiResult.createResult(cmPopPopupService.getPopList(cmPopPopupReqDto, page));
//	}
}
