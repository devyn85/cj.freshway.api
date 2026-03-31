package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmMainCodeResDto;
import cjfw.wms.cm.dto.CmMenuReqDto;
import cjfw.wms.cm.service.CmMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.18 
 * @description : 메뉴 조회 및 설정
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "메뉴 조회 및 설정", description = "메뉴 조회 및 설정")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/menu")
public class CmMenuController {

	private final CmMenuService cmMenuService;

	/**
	 * @description : 즐겨찾기 메뉴 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "즐겨찾기 메뉴 목록 조회", description = "즐겨찾기 메뉴 목록 조회")
	@GetMapping(value = "/v1.0/getFavoriteMenuList")
	public ApiResult<List<CmMainCodeResDto>> getFavoriteMenuList(@Valid CmMenuReqDto cmMenuReqDto) {
		return ApiResult.createResult(cmMenuService.getFavoriteMenuList(cmMenuReqDto));
	}
	
	/**
	 * @description : 즐겨찾기 메뉴 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.18 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "즐겨찾기 메뉴 저장", description = "즐겨찾기 메뉴 저장")
	@PostMapping(value = "/v1.0/insertFavoriteMenu")
	public ApiResult<String> insertFavoriteMenu(@RequestBody @Valid CmMenuReqDto cmMenuReqDto) {
		return ApiResult.createResult(cmMenuService.insertFavoriteMenu(cmMenuReqDto));
	}
	
	/**
	 * @description : 즐겨찾기 메뉴 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "즐겨찾기 메뉴 삭제", description = "즐겨찾기 메뉴 저장")
	@PostMapping(value = "/v1.0/deleteFavoriteMenu")
	public ApiResult<String> deleteFavoriteMenu(@RequestBody @Valid CmMenuReqDto cmMenuReqDto) {
		return ApiResult.createResult(cmMenuService.deleteFavoriteMenu(cmMenuReqDto));
	}

}