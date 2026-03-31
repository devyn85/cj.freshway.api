package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterStoClosetypeExcelResDto;
import cjfw.wms.ms.dto.MsCenterStoClosetypeResDto;
import cjfw.wms.ms.dto.MsCenterStoPickResDto;
import cjfw.wms.ms.dto.MsCenterStoPriorityResDto;
import cjfw.wms.ms.dto.MsCenterStoReqDto;
import cjfw.wms.ms.dto.MsCenterStoResDto;
import cjfw.wms.ms.service.MsCenterStoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.08.04
 * @description : 기준정보 > 센터기준정보 > 센터이체마스터 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터이체마스터", description = "센터이체마스터")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerSto")
public class MsCenterStoController {

	private final MsCenterStoService msCenterStoService;

	/**
	 * @description : 센터이체마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터이체마스터 조회", description = "센터이체마스터 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterStoResDto>> getMasterList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getMasterList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "센터이체마스터 저장", description = "센터이체마스터 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.saveMasterList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 자동 설정 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹유형 자동 설정 조회", description = "피킹유형 자동 설정 조회")
	@GetMapping(value = "/v1.0/getPickList")
	public ApiResult<List<MsCenterStoPickResDto>> getPickList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getPickList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 자동 설정 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹유형 조회", description = "피킹유형 조회")
	@GetMapping(value = "/v1.0/getPickTypeList")
	public ApiResult<List<MsCenterStoPickResDto>> getPickTypeList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getPickTypeList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 피킹유형 자동 설정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "피킹유형 자동 설정 저장", description = "피킹유형 자동 설정 저장")
	@PostMapping(value = "/v1.0/savePickList")
	public ApiResult<String> savePickList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.savePickList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 센터 제외 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 제외 조회", description = "센터 제외 조회")
	@GetMapping(value = "/v1.0/getClosetypeDcList")
	public ApiResult<List<MsCenterStoClosetypeResDto>> getClosetypeDcList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getClosetypeDcList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 센터 제외 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "센터 제외 저장", description = "센터 제외 저장")
	@PostMapping(value = "/v1.0/saveClosetypeDcList")
	public ApiResult<String> saveClosetypeDcList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.saveClosetypeDcList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상품 제외 조회", description = "상품 제외 조회")
	@GetMapping(value = "/v1.0/getClosetypeSkuList")
	public ApiResult<List<MsCenterStoClosetypeResDto>> getClosetypeSkuList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getClosetypeSkuList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "센터이체마스터 상품 제외 유효성검사", description = "센터이체마스터 상품 제외 유효성검사")
	@PostMapping(value = "/v1.0/getClosetypeSkuListCheck")
	public ApiResult<String> getClosetypeSkuListCheck(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.getClosetypeSkuListCheck(dto));
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "상품 제외 저장", description = "상품 제외 저장")
	@PostMapping(value = "/v1.0/saveClosetypeSkuList")
	public ApiResult<String> saveClosetypeSkuList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.saveClosetypeSkuList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 EXCELUPLOAD 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "센터이체마스터 상품 제외 유효성검사", description = "센터이체마스터 상품 제외 유효성검사")
	@PostMapping(value = "/v1.0/validateClosetypeSkuExcelList")
	public ApiResult<List<MsCenterStoClosetypeExcelResDto>> validateClosetypeSkuExcelList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.validateClosetypeSkuExcelList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 상품 제외 EXCELUPLOAD 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "상품 제외 저장", description = "상품 제외 저장")
	@PostMapping(value = "/v1.0/saveClosetypeSkuExcelList")
	public ApiResult<String> saveClosetypeSkuExcelList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.saveClosetypeSkuExcelList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 우선 순위 설정 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "우선 순위 설정 조회", description = "우선 순위 설정 조회")
	@GetMapping(value = "/v1.0/getPriorityList")
	public ApiResult<List<MsCenterStoPriorityResDto>> getPriorityList(@Valid MsCenterStoReqDto dto) {
		return ApiResult.createResult(msCenterStoService.getPriorityList(dto));
	}
	
	/**
	 * @description : 센터이체마스터 우선 순위 설정 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.04    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "우선 순위 설정 저장", description = "우선 순위 설정 저장")
	@PostMapping(value = "/v1.0/savePriorityList")
	public ApiResult<String> savePriorityList(@RequestBody @Valid List<MsCenterStoReqDto> dto) {
		return ApiResult.createResult(msCenterStoService.savePriorityList(dto));
	}

}