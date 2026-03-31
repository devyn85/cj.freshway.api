package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsExDCSimulationReqDto;
import cjfw.wms.ms.dto.MsExDCSimulationResDto;
import cjfw.wms.ms.service.MsExDCSimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.23 
 * @description : 외부창고정산 시뮬레이션 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "정산 > 외부창고정산 > 외부창고정산 시뮬레이션", description = "외부창고정산시뮬레이션")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/exdcsimulation")
public class MsExDCSimulationController {

	private final MsExDCSimulationService msExDCSimulationService;

	/**
	 * @description : 외부창고정산 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부창고정산 시뮬레이션 창고비교 목록 조회", description = "외부창고정산 시뮬레이션 창고비교 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsExDCSimulationResDto>> getMasterList(@Valid MsExDCSimulationReqDto dto) {
		return ApiResult.createResult(msExDCSimulationService.getMasterList(dto));
	}
	
	/**
     * @description : 외부창고정산 시뮬레이션 상품 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 시뮬레이션 상품 목록 조회", description = "외부창고정산 시뮬레이션 상품 목록 조회")
    @PostMapping(value = "/v1.0/getSkuList")
    public ApiResult<List<MsExDCSimulationResDto>> getSkuList(@Valid MsExDCSimulationReqDto dto) {
        return ApiResult.createResult(msExDCSimulationService.getSkuList(dto));
    }
	
    /**
     * @throws Exception
     * @description : 외부창고정산 시뮬레이션 창고비교
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 시뮬레이션 창고비교", description = "외부창고정산 시뮬레이션 창고비교")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List<MsExDCSimulationResDto>> saveMasterList(@RequestBody MsExDCSimulationReqDto dto) {
        return ApiResult.createResult(msExDCSimulationService.saveMasterList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 외부창고정산 시뮬레이션 상품
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부창고정산 시뮬레이션 상품", description = "외부창고정산 시뮬레이션 상품")
    @PostMapping(value = "/v1.0/saveSkuList")
    public ApiResult<List<MsExDCSimulationResDto>> saveSkuList(@RequestBody MsExDCSimulationReqDto dto) {
        return ApiResult.createResult(msExDCSimulationService.saveSkuList(dto));
    }

}