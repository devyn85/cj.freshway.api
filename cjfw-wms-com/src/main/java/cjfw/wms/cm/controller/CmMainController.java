package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmMainCodeListResDto;
import cjfw.wms.cm.dto.CmMainMenuRoleResDto;
import cjfw.wms.cm.dto.CmMainNoticeReqDto;
import cjfw.wms.cm.dto.CmMainUserAuthResDto;
import cjfw.wms.cm.dto.CmMainUserReqDto;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.cm.service.CmMainService;
import cjfw.wms.cm.service.CmUserService;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.04.28 
 * @description : 메인 공통 API Controller
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.04.28 JangGwangSeok (breaker3317@cj.net) 생성
 */
@Tag(name = "Main", description = "메인 공통 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/main")
public class CmMainController {

	private final CmMainService cmMainService;
	
	private final CmUserService cmUserService;

	/**
	 * @description : 로그인 사용자 권한에 따른 메뉴 리스트 조회
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.04.28 JangGwangSeok (breaker3317@cj.net) 생성
	 */
	@Operation(summary = "사용자 메뉴 리스트", description = "로그인 사용자 권한에 따른 메뉴 리스트 조회")
	@GetMapping(value = "/v1.0/getMenuRoleList")
	public ApiResult<List<CmMainMenuRoleResDto>> getMenuRoleList(@Valid CommonDto commonDto) {
		List<CmMainMenuRoleResDto> menuList = cmMainService.getMenuRoleList(commonDto);
		return ApiResult.createResult(menuList);
	}
	
	/**
	 * @description : 사용자별 공통 코드 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자별 공통 코드 정보 조회", description = "사용자별 공통 코드 정보 조회")
	@GetMapping(value = "/v1.0/getUserCodeList")
	public ApiResult<List<CmMainCodeListResDto>> getUserCodeList(@Valid CommonDto commonDto) {
		return ApiResult.createResult(cmMainService.getUserCodeList(commonDto));
	}
	
	/**
	 * @description : 로그인 후 사용자 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "로그인 후 사용자 정보 조회", description = "로그인 후 사용자 정보 조회")
	@GetMapping(value = "/v1.0/getCmUser")
	public ApiResult<CmMainUserResDto> getCmUser(@Valid CmMainUserReqDto cmMainUserReqDto) {
		return ApiResult.createResult(cmUserService.getCmUser(cmMainUserReqDto));
	}
	
	/**
	 * @description : 사용자 접속 권한 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 접속 권한 정보 조회", description = "사용자 접속 권한 정보 조회")
	@GetMapping(value = "/v1.0/getCmUserAuthority")
	public ApiResult<List<CmMainUserAuthResDto>> getCmUserAuthority(@Valid CmMainUserReqDto cmMainUserReqDto) {
		return ApiResult.createResult(cmUserService.getCmUserAuthority(cmMainUserReqDto));
	}
	
	/**
	 * @description : 검색유형별 사용자 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "검색유형별 사용자 정보 조회", description = "검색유형별 사용자 정보 조회")
	@GetMapping(value = "/v1.0/getUserBySelType")
	public ApiResult<CmMainUserResDto> getUserBySelType(@Valid CmMainUserReqDto cmMainUserReqDto) {
		return ApiResult.createResult(cmUserService.getUserBySelType(cmMainUserReqDto));
	}
	
	
	/**
	 * @description : 사용자 이메일 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 Baechan (c_bae@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 이메일 정보 조회", description = "사용자 이메일 정보 조회")
	@GetMapping(value = "/v1.0/getUserEmailByUserId")
	public ApiResult<CmMainUserResDto> getUserEmailByUserId(@Valid CmMainUserReqDto cmMainUserReqDto) {
		return ApiResult.createResult(cmUserService.getUserEmailByUserId(cmMainUserReqDto));
	}
	
	/**
	 * @description : 알림 읽음 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "알림 읽음 처리", description = "알림 읽음 처리")
	@PostMapping(value = "/v1.0/saveNoticeRead")
	public ApiResult<String> saveNoticeRead(@RequestBody CmMainNoticeReqDto cmMainNoticeReqDto) {
		return ApiResult.createResult(cmMainService.saveNoticeRead(cmMainNoticeReqDto));
	}
}
