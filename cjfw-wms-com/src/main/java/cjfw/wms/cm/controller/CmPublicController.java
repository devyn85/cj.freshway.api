package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmMainNoticeResDto;
import cjfw.wms.cm.dto.CmMainTranslationResDto;
import cjfw.wms.cm.service.CmMainService;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.11.03 
 * @description : 권한 필요없는 API
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "Public", description = "권한 필요없는 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/public")
public class CmPublicController {

	private final CmMainService cmMainService;
	
	/**
	 * @description : 헬스체크
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "헬스체크", description = "헬스체크용 api")
	@GetMapping(value = "/health")
	public String health() {
		return "OK";
	}
	
	/**
	 * @description : 다국어 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.26 breaker3317 생성 </pre>
	 */
	@Operation(summary = "다국어 목록 조회", description = "다국어 목록 조회")
	@GetMapping(value = "/v1.0/getTranslationList")
	public ApiResult<List<CmMainTranslationResDto>> getTranslationList(@Valid CommonDto commonDto) {
		List<CmMainTranslationResDto> menuList = cmMainService.getTranslationList(commonDto);
		return ApiResult.createResult(menuList);
	}
	
	/**
	 * @description : 알림 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "알림 목록 조회", description = "알림 목록 조회")
	@GetMapping(value = "/v1.0/getNoticeList")
	public ApiResult<List<CmMainNoticeResDto>> getNoticeList(@Valid CommonDto commonDto) {
		return ApiResult.createResult(cmMainService.getNoticeList(commonDto));
	}
	
	/**
	 * @description : 공지사항 팝업 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "공지사항 팝업 목록 조회", description = "공지사항 팝업 목록 조회")
	@GetMapping(value = "/v1.0/getNoticePopList")
	public ApiResult<List<CmMainNoticeResDto>> getNoticePopList(@Valid CommonDto commonDto) {
		return ApiResult.createResult(cmMainService.getNoticePopList(commonDto));
	}
	
}
