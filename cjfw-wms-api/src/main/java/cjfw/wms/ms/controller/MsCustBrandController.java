package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCustBrandExcelResDto;
import cjfw.wms.ms.dto.MsCustBrandReqDto;
import cjfw.wms.ms.dto.MsCustBrandResDto;
import cjfw.wms.ms.service.MsCustBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.10 
 * @description : 본점별 브랜드마스터 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 본점별브랜드마스터")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/custBrand")
public class MsCustBrandController {
	private final MsCustBrandService msCustBrandService;
	
	/**
	 * @description : 본점별브랜드마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "본점별 브랜드 목록 조회", description = "본점별 브랜드 목록을 조회합니다.")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCustBrandResDto>> getMasterList(MsCustBrandReqDto dto) {
		return ApiResult.createResult(msCustBrandService.getMasterList(dto));
	}
	
	/**
	 * @description : 본점별브랜드마스터 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14        YeSeungCheol (pw6375@cj.net)       생성
	 */
	@Operation(summary = "본점별 브랜드 목록 병합", description = "본점별 브랜드 목록을 병합합니다. (엑셀/저장)")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<Object> saveMasterList(@RequestBody List<MsCustBrandReqDto> saveList) {
        return ApiResult.createResult(msCustBrandService.saveMasterList(saveList));
    }

    @Operation(summary = "본점별브랜드 엑셀 유효성검사")
    @PostMapping(value = "/v1.0/validateExcelList")
    public ApiResult<List<MsCustBrandExcelResDto>> validateExcelList(@RequestBody List<MsCustBrandReqDto> dto) {
        return ApiResult.createResult(msCustBrandService.validateExcelList(dto));
    }

    @Operation(summary = "본점별브랜드 엑셀 저장")
    @PostMapping(value = "/v1.0/saveExcelList")
    public ApiResult<String> saveExcelList(@RequestBody List<MsCustBrandReqDto> dto) {
        return ApiResult.createResult(msCustBrandService.saveExcelList(dto));
    }
}
