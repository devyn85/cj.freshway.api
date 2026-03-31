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
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFODcDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOResultDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOStoResDto;
import cjfw.wms.om.service.OmOrderCreationSTOForOutService;
import cjfw.wms.om.service.OmOrderCreationSTOOrdBaseFOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.03.10 
 * @description : 당일광역보충발주(FO) Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "주문 > 주문등록 > 당일광역보충발주(FO)", description = "당일광역보충발주(FO) 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/ordercreationstoordbasefo")
public class OmOrderCreationSTOOrdBaseFOController {

	private final OmOrderCreationSTOOrdBaseFOService omOrderCreationSTOOrdBaseFOService;

	/**
	 * @description : 당일광역보충발주(FO) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주(FO) 목록 조회", description = "당일광역보충발주(FO) 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmOrderCreationSTOOrdBaseFOResDto>> getMasterList(@Valid OmOrderCreationSTOOrdBaseFOReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseFOService.getMasterList(dto));
	}
	
	/**
     * @description : 당일광역보충발주(FO) 처리결과 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "당일광역보충발주(FO) 처리결과 조회", description = "당일광역보충발주(FO) 처리결과 조회")
    @PostMapping(value = "/v1.0/getResultList")
    public ApiResult<List<OmOrderCreationSTOOrdBaseFOResultDto>> getResultList(@Valid OmOrderCreationSTOOrdBaseFOReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOOrdBaseFOService.getResultList(dto));
    }
    
    /**
     * @description : 당일광역보충발주(FO) 이동대상 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "당일광역보충발주(FO) 이동대상 조회", description = "당일광역보충발주(FO) 이동대상 조회")
    @PostMapping(value = "/v1.0/getSTOList")
    public ApiResult<List<OmOrderCreationSTOOrdBaseFOStoResDto>> getSTOList(@Valid OmOrderCreationSTOOrdBaseFOReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOOrdBaseFOService.getDataHeaderStoList(dto));
    }    
    
    /**
     * @description : 당일광역보충발주(FO) 물류센터 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "당일광역보충발주(FO) 물류센터 조회", description = "당일광역보충발주(FO) 물류센터 조회")
    @PostMapping(value = "/v1.0/getDcname")
    public ApiResult<List<OmOrderCreationSTOOrdBaseFODcDto>> getDcname(@Valid OmOrderCreationSTOOrdBaseFOReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOOrdBaseFOService.getDcname(dto));
    }  
    
    /**
     * @throws Exception
     * @description : 당일광역보충발주(FO) 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "당일광역보충발주(FO) 저장", description = "당일광역보충발주(FO) 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List<OmOrderCreationSTOOrdBaseFOResultDto>> saveMasterList(@RequestBody OmOrderCreationSTOOrdBaseFOReqDto dto) throws Exception {
        List<OmOrderCreationSTOOrdBaseFOResultDto> resultList = omOrderCreationSTOOrdBaseFOService.saveMasterList(dto);
        
        //주문 STO인 경우 출고 재고 분배 STO로직을 태운다.
        omOrderCreationSTOOrdBaseFOService.saveSTOAllocationBatch(dto);
        
        return ApiResult.createResult(resultList);
    }
    
}