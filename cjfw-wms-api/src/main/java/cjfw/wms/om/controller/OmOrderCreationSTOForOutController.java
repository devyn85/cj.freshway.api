package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutResultResDto;
import cjfw.wms.om.service.OmOrderCreationSTOForOutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.08 
 * @description : 외부센터 보충발주 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "주문 > 주문등록 > 외부센터 보충발주", description = "외부센터 보충발주 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/ordercreationstoforout")
public class OmOrderCreationSTOForOutController {

	private final OmOrderCreationSTOForOutService omOrderCreationSTOForOutService;

	/**
	 * @description : 외부센터 보충발주 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부센터 보충발주 목록 조회", description = "외부센터 보충발주 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmOrderCreationSTOForOutResDto>> getMasterList(@Valid OmOrderCreationSTOForOutReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOForOutService.getMasterList(dto));
	}
	
	/**
     * @description : 외부센터 보충발주 처리결과 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부센터 보충발주 처리결과 조회", description = "외부센터 보충발주 처리결과 조회")
    @PostMapping(value = "/v1.0/getResultList")
    public ApiResult<List<OmOrderCreationSTOForOutResDto>> getResultList(@Valid OmOrderCreationSTOForOutReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOForOutService.getResultList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부센터 보충발주 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부센터 보충발주 저장", description = "외부센터 보충발주 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List<OmOrderCreationSTOForOutResultResDto>> saveMasterList(@RequestBody OmOrderCreationSTOForOutReqDto dto) throws Exception {
        return ApiResult.createResult(omOrderCreationSTOForOutService.saveMasterList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부센터 보충발주 엑셀업로드 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부센터 보충발주 엑셀업로드 저장", description = "외부센터 보충발주 엑셀업로드 저장")
    @PostMapping(value = "/v1.0/saveExcelList")
    public ApiResult<List<OmOrderCreationSTOForOutResDto>> saveExcelList(@RequestBody OmOrderCreationSTOForOutReqDto dto) throws Exception {
        return ApiResult.createResult(omOrderCreationSTOForOutService.saveExcelList(dto));
    }
    
    /**
     * @description : 외부센터 보충발주 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부센터 보충발주 엑셀업로드 데이터 검증", description = "외부센터 보충발주 엑셀업로드 데이터 검증")
    @PostMapping(value = "/v1.0/validateExcel")
    public ApiResult<List<OmOrderCreationSTOForOutResDto>> validateExcel(@RequestBody @Valid OmOrderCreationSTOForOutReqDto reqDto) {
        return ApiResult.createResult(omOrderCreationSTOForOutService.validateExcel(reqDto));
    }
}