package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCostCenterCtgyInfoExcelResDto;
import cjfw.wms.ms.dto.MsCostCenterCtgyInfoReqDto;
import cjfw.wms.ms.dto.MsCostCenterCtgyInfoResDto;
import cjfw.wms.ms.service.MsCostCenterCtgyInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.12.08
 * @description : 마감 출고데이터 마스터 - Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 마감 출고데이터 마스터")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/costCenterCtgyInfo")
public class MsCostCenterCtgyInfoController {

    private final MsCostCenterCtgyInfoService msCostCenterCtgyInfoService;

    /**
	 * @description : 고객마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "고객마스터 조회")
    @PostMapping("/v1.0/getMasterListTab1")
    public ApiResult<List<MsCostCenterCtgyInfoResDto>> getMasterListTab1(@RequestBody MsCostCenterCtgyInfoReqDto req) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.getMasterListTab1(req));
    }

    /**
	 * @description : 미곡 상품 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "미곡 상품 마스터 조회")
    @PostMapping("/v1.0/getMasterListTab2")
    public ApiResult<List<MsCostCenterCtgyInfoResDto>> getMasterListTab2(@RequestBody MsCostCenterCtgyInfoReqDto req) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.getMasterListTab2(req));
    }

    /**
	 * @description : 전용 상품 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "전용 상품 마스터")
    @PostMapping("/v1.0/getMasterListTab3")
    public ApiResult<List<MsCostCenterCtgyInfoResDto>> getMasterListTab3(@RequestBody MsCostCenterCtgyInfoReqDto req) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.getMasterListTab3(req));
    }
    
    //

    /**
	 * @description : 상품 미곡 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "업무부세조직분류 엑셀 유효성 검사")
    @PostMapping("/v1.0/validateExcelList")
    public ApiResult<List<MsCostCenterCtgyInfoExcelResDto>> validateExcelList(@RequestBody List<MsCostCenterCtgyInfoReqDto> reqList) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.validateExcelList(reqList));
    }

    /**
	 * @description : 엑셀 업로드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.08 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "업무부세조직분류 엑셀 저장")
    @PostMapping("/v1.0/saveExcelList")
    public ApiResult<String> saveExcelList(@RequestBody List<MsCostCenterCtgyInfoReqDto> reqList) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.saveExcelList(reqList));
    }
    
    /**
     * @description : 전용상품 엑셀정합성 검사 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.01.13 생성 </pre>
     */
    @Operation(summary = "전용상품 엑셀정합성 검사")
    @PostMapping("/v1.0/validateExcelListTab3")
    public ApiResult<List<MsCostCenterCtgyInfoResDto>> validateExcelListTab3(@RequestBody MsCostCenterCtgyInfoReqDto reqList) {
    	return ApiResult.createResult(msCostCenterCtgyInfoService.validateExcelListTab3(reqList));
    }
    /**
	 * @description : 전용상품 엑셀 업로드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.01.13 생성 </pre>
	 */
    @Operation(summary = "전용상품 엑셀 저장")
    @PostMapping("/v1.0/saveExcelListTab3")
    public ApiResult<String> saveExcelListTab3(@RequestBody MsCostCenterCtgyInfoReqDto reqList) {
        return ApiResult.createResult(msCostCenterCtgyInfoService.saveExcelListTab3(reqList));
    }
    
}
