package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterPolicyMngDetailResDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngLocationResDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngReqDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngResDto;
import cjfw.wms.ms.service.MsCenterPolicyMngService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.20
 * @description : 기준정보 > 센터기준정보 > 센터정책관리 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터정책관리", description = "센터정책관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerPolicyMng")
public class MsCenterPolicyMngController {

	private final MsCenterPolicyMngService msCenterPolicyMngService;

	/**
	 * @description : 센터정책관리 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터정책관리 조회", description = "센터정책관리 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterPolicyMngResDto>> getMasterList(@Valid MsCenterPolicyMngReqDto dto) {
		return ApiResult.createResult(msCenterPolicyMngService.getMasterList(dto));
	}
	
	/**
	 * @description : 센터정책관리 상세조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터정책관리 조회", description = "센터정책관리 조회")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<MsCenterPolicyMngDetailResDto>> getDetailList(@Valid MsCenterPolicyMngReqDto dto) {
		return ApiResult.createResult(msCenterPolicyMngService.getDetailList(dto));
	}
	
	/**
	 * @description : 센터정책관리 로케이션 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터정책관리 로케이션 조회", description = "센터정책관리 로케이션 조회")
	@GetMapping(value = "/v1.0/getLocationList")
	public ApiResult<List<MsCenterPolicyMngLocationResDto>> getLocationList(@Valid MsCenterPolicyMngReqDto dto) {
		return ApiResult.createResult(msCenterPolicyMngService.getLocationList(dto));
	}
	
	/**
	 * @description : 센터정책관리 코드 유효성 체크
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "센터정책관리 코드 유효성 체크", description = "센터정책관리 코드 유효성 체크")
	@PostMapping(value = "/v1.0/getValidateCodeList")
	public ApiResult<String> getValidateCodeList(@RequestBody @Valid List<MsCenterPolicyMngReqDto> dto) {
		return ApiResult.createResult(msCenterPolicyMngService.getValidateCodeList(dto));
	}
	
	/**
	 * @description : 센터정책관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.23        생성
	 */
	@Operation(summary = "센터정책관리 항목 저장", description = "센터정책관리 항목 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsCenterPolicyMngReqDto> dto) {
		return ApiResult.createResult(msCenterPolicyMngService.saveMasterList(dto));
	}	
	
	/**
	 * @description : 센터정책관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.23        생성
	 */
	@Operation(summary = "센터정책관리 기타설정  저장", description = "센터정책관리 기타설정 저장")
	@PostMapping(value = "/v1.0/saveMasterList2")
	public ApiResult<String> saveMasterList2(@RequestBody @Valid MsCenterPolicyMngReqDto dto) {
		return ApiResult.createResult(msCenterPolicyMngService.saveMasterList2(dto));
	}	
	
	/**
	 * @description : 센터정책관리 세부항목 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.23        생성
	 */
	@Operation(summary = "센터정책관리 세부항목 저장", description = "센터정책관리 정책 저장")
	@PostMapping(value = "/v1.0/saveMasterList3")
	public ApiResult<String> saveMasterList3(@RequestBody @Valid MsCenterPolicyMngReqDto dto) {
		return ApiResult.createResult(msCenterPolicyMngService.saveMasterList3(dto));
	}	
	
}