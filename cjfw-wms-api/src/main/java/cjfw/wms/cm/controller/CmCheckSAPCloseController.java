package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.conn.jco.JCoException;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmCheckSAPCloseReqDto;
import cjfw.wms.cm.dto.CmCheckSAPCloseResDto;
import cjfw.wms.cm.service.CmCheckSAPCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.02 
 * @description : SAP 마감 체크 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "SAP 마감 체크 IF", description = "SAP 마감 체크 IF")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/checkSAPClose")
public class CmCheckSAPCloseController {

	private final CmCheckSAPCloseService cmCheckSAPCloseService;
	
	/**
	 * @throws JCoException 
	 * @description : SAP 마감 조회
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.02    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "SAP 마감 조회", description = "SAP 마감 조회")
	@GetMapping(value = "/v1.0/getStatus")
	public ApiResult<CmCheckSAPCloseResDto> getStatus(@Valid CmCheckSAPCloseReqDto cmCheckSAPCloseReqDto) throws JCoException {
		return ApiResult.createResult(cmCheckSAPCloseService.getStatus(cmCheckSAPCloseReqDto));
	}

}