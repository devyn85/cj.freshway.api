package cjfw.wms.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsSkuDcSetReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetResDto;
import cjfw.wms.ms.service.MsSkuDcSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 센터상품속성 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "기준정보 > 상품기준정보 > 센터상품속성", description = "센터상품속성 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dev/pilot01")
public class DevPilot01Controller {

	private final MsSkuDcSetService msSkuDcSetService;

	/**
	 * @description : 센터상품속성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "센터상품속성 목록 조회", description = "센터상품속성 목록 페이징 조회")
	@GetMapping(value = "/v1.0/getSkuDcSetList")
	public ApiResult<Page<MsSkuDcSetResDto>> getSkuDcSetPagingList(@Valid MsSkuDcSetReqDto msSkuDcSetReqDto, Page page) {
		return ApiResult.createResult(msSkuDcSetService.getSkuDcSetPagingList(msSkuDcSetReqDto, page));
	}
	
	/**
	 * @description : 센터상품속성 단건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "센터상품속성 단건 조회", description = "센터상품속성 단건 조회")
	@GetMapping(value = "/v1.0/getSkuDcSetDtl")
	public ApiResult<MsSkuDcSetResDto> getSkuDcSetDtl(@Valid MsSkuDcSetReqDto msSkuDcSetReqDto) {
		return ApiResult.createResult(msSkuDcSetService.getSkuDcSetDtl(msSkuDcSetReqDto));
	}
	
	/**
	 * @description : 저장위치정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "센터상품속성 저장", description = "센터상품속성 저장")
    @PostMapping(value = "/v1.0/saveSkuDcSet")
    public ApiResult<String> saveSkuDcSet(@RequestBody @Valid MsSkuDcSetReqDto reqDto) {
        return ApiResult.createResult(msSkuDcSetService.saveSkuDcSet(reqDto));
    }
}