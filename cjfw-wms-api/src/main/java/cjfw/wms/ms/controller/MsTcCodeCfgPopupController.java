package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsTcCodeCfgPopupReqDto;
import cjfw.wms.ms.dto.MsTcCodeCfgPopupResDto;
import cjfw.wms.ms.service.MsTcCodeCfgPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.18 
 * @description : 출발지TC센터설정 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name="차량정보 > TC센터설정 > 출발지TC센터설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/tcCodeCfg")
public class MsTcCodeCfgPopupController {
	private final MsTcCodeCfgPopupService msTcCodeCfgPopupService;
	
	/**
	 * @description : 출발지TC센터설정 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출발지TC센터설정 목록 조회", description = "출발지TC센터설정 목록 조회")
	@GetMapping("/v1.0/getTcCodeCfgList")
	public ApiResult<Page<MsTcCodeCfgPopupResDto>> getTcCodeCfgList(MsTcCodeCfgPopupReqDto msTcCodeCfgPopupReqDto, Page<?> page) {
		return ApiResult.createResult(msTcCodeCfgPopupService.getTcCodeCfgList(msTcCodeCfgPopupReqDto, page));
	}
	
	/**
	 * @description : 출차그룹 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출발지TC센터설정 목록 저장", description = "출발지TC센터설정 목록 저장")
	@PostMapping("/v1.0/saveTcCodeCfgList")
	public ApiResult<String> saveTcCodeCfgList(@RequestBody List<MsTcCodeCfgPopupReqDto> dto) {
    	return ApiResult.createResult(msTcCodeCfgPopupService.saveTcCodeCfgList(dto));
    }
}
