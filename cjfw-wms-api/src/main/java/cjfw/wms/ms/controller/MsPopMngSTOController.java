package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPopMngSTOReqDto;
import cjfw.wms.ms.dto.MsPopMngSTOResDto;
import cjfw.wms.ms.service.MsPopMngSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 기준정보 > 권역기준정보 > 거래처별POP관리(광역일배) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 권역기준정보 > 거래처별POP관리(광역일배)", description = "거래처별POP관리(광역일배)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/popMngSTO")
public class MsPopMngSTOController {

	private final MsPopMngSTOService msPopMngSTOService;

	/**
	 * @description : 거래처별POP관리(광역일배) 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처별POP관리(광역일배) 정보 조회", description = "거래처별POP관리(광역일배) 정보 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsPopMngSTOResDto>> getMasterList(@Valid MsPopMngSTOReqDto dto) {
		return ApiResult.createResult(msPopMngSTOService.getMasterList(dto));
	}
	
	/**
	 * @description : 거래처별POP(광역일배) 정보 MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처별POP관리(광역일배) 정보 MERGE", description = "거래처별POP관리(광역일배) 정보 MERGE")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsPopMngSTOReqDto> dto) {
		return ApiResult.createResult(msPopMngSTOService.saveMasterList(dto));
	}

}