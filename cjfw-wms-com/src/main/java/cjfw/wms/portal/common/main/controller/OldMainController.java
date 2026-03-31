/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.main.controller;

import cjfw.core.model.ApiResult;
import cjfw.wms.portal.common.main.dto.GrpCommCodeListGetResDto;
import cjfw.wms.portal.common.main.dto.LoginHistoryGetResDto;
import cjfw.wms.portal.common.main.dto.MainMenuGetResDto;
import cjfw.wms.portal.common.main.dto.MainUserGetResDto;
import cjfw.wms.portal.common.main.dto.SiteMapMenuGetResDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuDeleteReqDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuGetResDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuInsertReqDto;
import cjfw.wms.portal.common.main.service.OldMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main 컨트롤러
 *  - 인증(로그인)은 필요하나 인가는 불필요한 공통 API 정의
 */
@Tag(name = "Main", description = "메인 공통 및 메뉴즐겨찾기 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("common/main")
public class OldMainController {

	private final OldMainService oldMainService;

	/**
	 * 로그인 사용자정보 조회
	 */
	@Operation(summary = "로그인 사용자", description = "로그인 사용자정보 조회")
	@GetMapping(value = "/user")
	public ApiResult<MainUserGetResDto> user() {
		return ApiResult.createResult(oldMainService.getCurrentUser());
	}


	/**
	 * 권한에 따른 메뉴 리스트 조회
	 */
	@Operation(summary = "사용자 메뉴 리스트", description = "로그인 사용자 권한에 따른 메뉴 리스트 조회")
	@GetMapping(value = "/menulist")
	public ApiResult<List<MainMenuGetResDto>> menulist() {
		List<MainMenuGetResDto> menuList = oldMainService.getMenuRoleMainViewList();

		// SPA에서 불필요한 메뉴 필터링
		List<String> excludeSpaMenuUrlPrefixs = Arrays.asList(
				"/sysmgt/func/messages/page",
				"/sysmgt/func/svcapi/page",
				"/sysmgt/func/devenv/page");
		menuList = menuList.stream()
				.filter(item ->  (item.getMenuUrl()==null || !excludeSpaMenuUrlPrefixs.contains(item.getMenuUrl())))
				.collect(Collectors.toList());


		log.info("test");



		return ApiResult.createResult(menuList);
	}

	/**
	 * 사이트 맵 조회 (권한여부(use_yn), 즐겨찾기여부(favorite_yn)까지 포함)
	 */
	@Operation(summary = "사이트 맵 조회", description = "사이트 맵 조회 (권한여부(use_yn), 즐겨찾기여부(favorite_yn)까지 포함)")
	@GetMapping(value = "/siteMapMenulist")
	public ApiResult<List<SiteMapMenuGetResDto>> siteMapMenulist() {
		List<SiteMapMenuGetResDto> menuList = oldMainService.getSiteMapMenulist();

		// SPA에서 불필요한 메뉴 필터링
		List<String> excludeSpaMenuUrlPrefixs = Arrays.asList(
			"/sysmgt/func/messages/page",
			"/sysmgt/func/svcapi/page",
			"/sysmgt/func/devenv/page");
		menuList = menuList.stream()
			.filter(item ->  (item.getMenuUrl()==null || !excludeSpaMenuUrlPrefixs.contains(item.getMenuUrl())))
			.collect(Collectors.toList());

		return ApiResult.createResult(menuList);
	}
	
	/**
	 * 즐겨찾기를 조회한다.<br>
	 */
	@Operation(summary = "즐겨찾기 메뉴 조회", description = "로그인 사용자 권한에 따른 즐겨찾기 메뉴 리스트 조회")
	@GetMapping(value = "/searchUsersMyMenu")
	public ApiResult<List<UserMyMenuGetResDto>> getUsersMyMenuList() {
		return ApiResult.createResult(oldMainService.getUsersMyMenuList());
	}
	
	/**
	 * 즐겨찾기 메뉴 추가<br>
	 */
	@Operation(summary = "즐겨찾기 메뉴 추가", description = "로그인 사용자 권한에 따른 즐겨찾기 메뉴 리스트 추가")
	@PostMapping(value = "/insertUsersMyMenu")
	public ApiResult insertUsersMyMenu(@Valid @RequestBody UserMyMenuInsertReqDto userMyMenuInsertReqDto) {
		String resultMsgCode = oldMainService.insertUsersMyMenu(userMyMenuInsertReqDto);
		return ApiResult.createResult(resultMsgCode);
	}

	/**
	 * 즐겨찾기 메뉴 삭제<br>
	 */
	@Operation(summary = "즐겨찾기 메뉴 삭제", description = "로그인 사용자 권한에 따른 즐겨찾기 메뉴 리스트 삭제")
	@PostMapping(value = "/deleteUsersMyMenu")
	public ApiResult deleteUsersMyMenu(@Valid @RequestBody UserMyMenuDeleteReqDto userMyMenuDeleteReqDto) {
		String resultMsgCode = oldMainService.deleteUsersMyMenu(userMyMenuDeleteReqDto);
		return ApiResult.createResult(resultMsgCode);
	}

	/**
	 * 로그인 이력을 조회한다.<br>
	 */
	@Operation(summary = "로그인 이력 조회", description = "로그인 사용자 로그인 이력조회")
	@GetMapping(value = "/getLoginLogOutList")
	public ApiResult<List<LoginHistoryGetResDto>> getLoginLogOutList() {
		return ApiResult.createResult(oldMainService.getLoginLogOutList());
	}
	
	/**
	 * 최근 로그인이력을 조회한다.<br>
	 */
	@Operation(summary = "최근 로그인 이력 조회", description = "로그인 사용자 최근 로그인 이력조회")
	@GetMapping(value = "/getLatestLogin")
	public ApiResult<List<LoginHistoryGetResDto>> getLatestLogin() {
		return ApiResult.createResult(oldMainService.getLatestLogin());
	}

	/**
	 * 멀티 그룹을 파라메타로 받아서 코드그룹명 공통코드를 조회<br>
	 */
	@Operation(summary = "멀티(다중)그룹 공통코드 목록 조회", description = "멀티(다중) 그룹을 파라메타로 받아서 코드그룹 별 공통코드를 조회")
	@GetMapping(value = "/getGrpCommCodeList")
	public ApiResult<List<GrpCommCodeListGetResDto>> getGrpCommCodeList(@RequestParam List<String> grpCds) {
		return ApiResult.createResult(oldMainService.getGrpCommCodeList(grpCds));
	}
}
