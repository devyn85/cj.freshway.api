package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmSendReqDto;
import cjfw.wms.cm.service.CmSendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.07.28 
 * @description : Email/SMS 전송
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "Email/SMS 전송", description = "Email/SMS 전송")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/send")
public class CmSendController {

	private final CmSendService cmSendService;
	
	/**
	 * @description : Email 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "Email 전송", description = "Email 전송")
	@PostMapping(value = "/v1.0/saveEmail")
	public ApiResult<String> saveEmail(@RequestBody CmSendReqDto cmSendReqDto) {
		return ApiResult.createResult(cmSendService.saveEmail(cmSendReqDto));
	}
	
	/**
	 * @description : SMS 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "SMS 전송", description = "SMS 전송")
	@PostMapping(value = "/v1.0/saveSms")
	public ApiResult<String> saveSms(@RequestBody CmSendReqDto cmSendReqDto) {
		return ApiResult.createResult(cmSendService.saveSms(cmSendReqDto));
	}
	
	/**
	 * @description : SMS 전송 (실시간)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@PostMapping(value = "/v1.0/saveSmsRealTime")
	public ApiResult<String> sendSmsRealTime(@RequestBody CmSendReqDto cmSendReqDto) {
		return ApiResult.createResult(cmSendService.saveSmsRealTime(cmSendReqDto));
	}

}