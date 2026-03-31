package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmPlanEtaOptimizeAutoReqDto;
import cjfw.wms.tm.dto.TmSetDispatchResDto;
import cjfw.wms.tm.service.TmPlanEtaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.24 
 * @description : 배차계획 재계산 및 ETA 계산 컨트롤러 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("/api/tm/planEta")
@RequiredArgsConstructor
@Tag(name = "배송 > 배차계획", description = "배차계획 재계산 및 ETA 계산 컨트롤러")
public class TmPlanEtaController {

	private final TmPlanEtaService tmPlanEtaService;
	
    /**
     * @description : 드라이버 조정 엔진 재계산 요청 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/optimize/auto")
    @Operation(summary = "드라이버 조정 엔진 재계산 요청", description = "드라이버 조정 엔진 재계산 요청")
    public ApiResult<TmSetDispatchResDto> planEtaOptimizeAuto(@RequestBody TmPlanEtaOptimizeAutoReqDto dto) {
        return ApiResult.createResult(tmPlanEtaService.savePlanEtaOptimizeAuto(dto));
    }
    
    /**
     * @description : 경로 재계산 API 요청 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.28 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/optimize/manual")
    @Operation(summary = "드라이버 조정 수동 재계산 요청", description = "드라이버 조정 수동 재계산 요청")
    public ApiResult<TmSetDispatchResDto> planEtaOptimizeManual(@RequestBody TmPlanEtaOptimizeAutoReqDto dto) {
        return ApiResult.createResult(tmPlanEtaService.savePlanEtaOptimizeManual(dto));
    }
    
}
