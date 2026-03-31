package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmMyMenuPopupReqDto;
import cjfw.wms.cm.dto.CmMyMenuPopupResDto;
import cjfw.wms.cm.service.CmMyMenuPopupService;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)  
 * @date : 2025.11.20 
 * @description : 기준정보 > 기타기준정보 > 메뉴 즐겨찾기마스터 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Tag(name = "공통 > 공통 > 메뉴 즐겨찾기", description = "메뉴 즐겨찾기 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/myMenu")
public class CmMyMenuPopupController {

	private final CmMyMenuPopupService cmMyMenuPopupService;

	/**
	 * @description : 메뉴 즐겨찾기 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "전체메뉴 목록 조회", description = "전체메뉴 목록 검색 조회")
	@GetMapping(value = "/v1.0/getMyMenuPopupList")
	public ApiResult<List<CmMyMenuPopupResDto>> getMyMenuPopupList(@Valid CommonDto reqDto) {
		return ApiResult.createResult(cmMyMenuPopupService.getMyMenuPopupList(reqDto));
	}
	
	/**
	 * @description : 메뉴 즐겨찾기 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "메뉴 즐겨찾기 목록 조회", description = "메뉴 즐겨찾기 목록 조회")
	@GetMapping(value = "/v1.0/getMyFavoriteMenuList")
	public ApiResult<List<CmMyMenuPopupResDto>> getMyFavoriteMenuList(@Valid CommonDto reqDto) {
		return ApiResult.createResult(cmMyMenuPopupService.getMyFavoriteMenuList(reqDto));
	}
	
	/**
	 * @description : 메뉴 즐겨찾기 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "메뉴 즐겨찾기 저장", description = "메뉴 즐겨찾기 저장")
	@PostMapping(value = "/v1.0/saveMyMenuPopupList")
	public ApiResult<String> saveMyMenuPopupList(@RequestBody @Valid List<CmMyMenuPopupReqDto> reqDto) {
		return ApiResult.createResult(cmMyMenuPopupService.saveMyMenuPopupList(reqDto));
	}

}