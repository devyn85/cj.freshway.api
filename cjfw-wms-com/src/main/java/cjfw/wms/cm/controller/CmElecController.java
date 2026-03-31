package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.wms.cm.dto.CmElecReqDto;
import cjfw.wms.cm.dto.CmElecResDto;
import cjfw.wms.cm.service.CmElecService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.09.22 
 * @description : 전자결재 API Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "Elec", description = "전자결재 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/elec")
public class CmElecController {
	
	private final CmElecService cmElecService;

	/**
	 * @description : 전자결재 HTML 송신
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.22 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "전자결재 HTML 송신", description = "전자결재 HTML 송신")
	@GetMapping(value = "/v1.0/getElecHtml")
	public CmElecResDto getMenuRoleList(CmElecReqDto cmElecReqDto) {
		CmElecResDto result = cmElecService.getElecHtml(cmElecReqDto);
		return result;
	}
	
	/**
	 * @description : 전자결재 상태값 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "전자결재 상태값 수정", description = "전자결재 상태값 수정")
	@GetMapping(value = "/v1.0/setElecStatus")
	public CmElecResDto saveElecStatus(CmElecReqDto cmElecReqDto) {
		CmElecResDto result = cmElecService.saveElecStatus(cmElecReqDto);
		return result;
	}
}
