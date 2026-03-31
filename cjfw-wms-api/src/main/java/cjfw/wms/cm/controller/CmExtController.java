package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.common.extend.CommonDto;
import cjfw.wms.webservice.utility.WebServiceUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.08.19 
 * @description : 외부 서비스 호출
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "외부 서비스 호출", description = "외부 서비스 호출")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/ext")
public class CmExtController {

	/**
	 * @description : IAM에 SSO처리를 위해  1회용 Ticket 요청
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping(value = "/v1.0/getSSOTicket")
	public ApiResult<String> getSSOTicket(CommonDto commonDto) {
		String ssoId = WebServiceUtil.getSSOTicket(commonDto.getGUserNo());
		return ApiResult.createResult(ssoId, "");
	}

}