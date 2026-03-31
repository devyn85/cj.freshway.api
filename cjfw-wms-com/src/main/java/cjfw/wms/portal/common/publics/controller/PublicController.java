/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.publics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.core.model.ApiResult;
import cjfw.wms.portal.common.publics.dto.PublicCodeDtlGetReqDto;
import cjfw.wms.portal.common.publics.dto.PublicCodeDtlGetResDto;
import cjfw.wms.portal.common.publics.service.PublicCommonCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통 Public 컨트롤러 - 인증 없이 호출되는(ex. 로그인화면 등) API 정의
 */
@Tag(name = "Public", description = "인증없이도 호출 가능한 공통 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("common/public")
public class PublicController {

	@Value("${cf.sso.redirectUrl}")
	private String ssoRedirectUrl;

	private final PublicCommonCodeService publicCommonCodeService;

	private final CJSecurityRulesService cJSecurityRulesService;

	/**
	 * 언어 코드를 조회한다.<br>
	 */
	@Operation(summary = "언어코드 리스트", description = "언어코드 리스트 조회")
	@GetMapping(value = "/getLangCdList")
	public ApiResult<List<PublicCodeDtlGetResDto>> getLangCdList() {
		PublicCodeDtlGetReqDto codeDtlReqDto = new PublicCodeDtlGetReqDto();
		codeDtlReqDto.setComGrpCd("LANG_CD");
		codeDtlReqDto.setUseYn("1");

		return ApiResult.createResult(publicCommonCodeService.getCommonCdList(codeDtlReqDto));
	}

	@Operation(summary = "헬스체크", description = "헬스체크용 api")
	@GetMapping(value = "/health")
	public String health() {
		return "OK";
	}

//	@Operation(summary = "CJWorld SSO", description = "CJWorld SSO 연동 api")
//	@PostMapping(value = "/ssoSignin")
//	public void ssoSignin(@RequestParam("cjworld_id") String cjWorldId, @RequestParam("language") String lang,
//			HttpServletResponse response) throws Exception {
//		// log.info("{}, {}", cjWorldId, lang);
//		String redirectUrl = ssoRedirectUrl + "/error?type=unauthorized";
//
//		SsoCryptoUtil td = new SsoCryptoUtil();
//		String decryptUserId = td.decrypt(cjWorldId);
//		Map<String, String> userIdMap = new HashMap<String, String>();
//		userIdMap.put("userId", decryptUserId);
//		Map<String, Object> user = cJSecurityRulesService.getUser(userIdMap); // 사용자 존재여부 체크
//		if (user != null) {
//			log.info("SSO cjWorldId: {}, user: {}", cjWorldId, user);
//			redirectUrl = ssoRedirectUrl + "/ssoLogin?cjWorldId=" + URLEncoder.encode(cjWorldId, "UTF-8");
//		}
//		response.sendRedirect(redirectUrl);
//	}

}
