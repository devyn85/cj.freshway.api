package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPopMngPOPResDto;
import cjfw.wms.ms.dto.MsPopMngReqDto;
import cjfw.wms.ms.dto.MsPopMngResDto;
import cjfw.wms.ms.service.MsPopMngService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.07.18 
 * @description : 기준정보 > 권역기준정보 > 거래처별POP관리 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 권역기준정보 > 거래처별POP관리", description = "거래처별POP관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/popMng")
public class MsPopMngController {

	private final MsPopMngService msPopMngService;

	/**
	 * @description : 거래처별POP관리 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처별POP관리 정보 조회", description = "거래처별POP관리 정보 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsPopMngResDto>> getMasterList(@RequestBody @Valid MsPopMngReqDto dto) {
		return ApiResult.createResult(msPopMngService.getMasterList(dto));
	}
	
	/**
	 * @description : rolltainer 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "rolltainer 조회", description = "rolltainer 조회")
	@GetMapping(value = "/v1.0/getRolltainerList")
	public ApiResult<List<MsPopMngResDto>> getRolltainerList(@Valid MsPopMngReqDto dto) {
		return ApiResult.createResult(msPopMngService.getRolltainerList(dto));
	}
	
	/**
	 * @description : 거래처별POP MERGE
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처별POP관리 정보 MERGE", description = "거래처별POP관리 정보 MERGE")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsPopMngReqDto> dto) {
		return ApiResult.createResult(msPopMngService.saveMasterList(dto));
	}

	/**
	 * @description : 거래처별POP 일괄 재전송 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처별POP관리 일괄 재전송", description = "거래처별POP관리 일괄 재전송")
	@PostMapping(value = "/v1.0/saveMasterResend")
	public ApiResult<String> saveMasterResend(@RequestBody @Valid MsPopMngReqDto dto) {
		return ApiResult.createResult(msPopMngService.saveMasterResend(dto));
	}
	
	/**
	 * @description : POP일괄업로드(excel) & 유효성검증 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "POP일괄업로드", description = "POP일괄업로드")
	@PostMapping(value = "/v1.0/saveExcelList")
	public ApiResult<List<MsPopMngResDto>> saveExcelList(@RequestBody @Valid MsPopMngReqDto dto) {
		return ApiResult.createResult(msPopMngService.saveExcelList(dto));
	}
	
	/**
	 * @description : 코드 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "코드 상세 목록 조회", description = "코드 상세 목록 조회")
	@GetMapping(value = "/v1.0/getDetailCodeList")
	public ApiResult<List<MsPopMngPOPResDto>> getCodeDetailList(@Valid MsPopMngReqDto dto) {
		return ApiResult.createResult(msPopMngService.getCodeDetailList(dto));
	}
	
	/**
	 * @description : 대표POP변경 codelist  & 대표POP변경 bulk update 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "대표POP변경 codelist 저장", description = "대표POP변경 codelist 저장")
	@PostMapping(value = "/v1.0/saveCodeList")
	public ApiResult<List<MsPopMngResDto>> saveCodeList(@RequestBody @Valid List<MsPopMngReqDto> dto) {
		return ApiResult.createResult(msPopMngService.saveCodeList(dto));
	}
		
}