package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmCancelDispatchReqDto;
import cjfw.wms.tm.dto.TmChangeCarNoReqDto;
import cjfw.wms.tm.dto.TmDispatchConfirmedReqDto;
import cjfw.wms.tm.dto.TmDispatchPlanCarReqDto;
import cjfw.wms.tm.dto.TmDispatchPlanCarResDto;
import cjfw.wms.tm.dto.TmGetDispatchListReqDto;
import cjfw.wms.tm.dto.TmGetDispatchListResDto;
import cjfw.wms.tm.dto.TmPlanReqDto;
import cjfw.wms.tm.dto.TmSetDispatchOutGroupReqDto;
import cjfw.wms.tm.dto.TmSetDispatchReqDto;
import cjfw.wms.tm.dto.TmSetDispatchResDto;
import cjfw.wms.tm.service.TmSetDispatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : TM 배차 설정 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Slf4j
@RestController
@RequestMapping("/api/tm/dispatch")
@RequiredArgsConstructor
@Tag(name = "TM > 배차관리", description = "TM 배차 설정 및 최적화 API")
public class TmSetDispatchController {

    private final TmSetDispatchService tmSetDispatchService;
    private final UserContext userContext;

    /**
     * @description : 배차 최적화 엔진 요청 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/setDispatch")
    @Operation(summary = "가배차 엔진 요청", description = "배차 최적화 엔진 요청")
    public ApiResult<TmSetDispatchResDto> saveDispatchInit(@Valid @RequestBody TmSetDispatchReqDto request) {
        return ApiResult.createResult(tmSetDispatchService.saveDispatchInit(request));
    }

    /**
     * @description : 배차 최적화 결과 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.10 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/saveDispatch")
    @Operation(summary = "배차 저장", description = "배차 최적화 결과 저장")
    public ApiResult<String> saveDispatch(@Valid @RequestBody TmPlanReqDto request) {
        return ApiResult.createResult(tmSetDispatchService.saveDispatch(request));
    }  
    
    /**
     * @description : 주행 가능한 차량 목록 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/getPlanCar")
    @Operation(summary = "배차 주행 가능 차량 조회", description = "주행 가능 차량 조회")
    public ApiResult<List<TmDispatchPlanCarResDto>> getPlanCar(@RequestBody TmDispatchPlanCarReqDto request) {
        return ApiResult.createResult(tmSetDispatchService.getPlanCar(request));
    }
    
    /**
     * @description : 주행 가능한 차량 상태 저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/savePlanCar")
    @Operation(summary = "배차 주행 가능 차량 설정", description = "주행 가능 차량 설정")
    public ApiResult<String> savePlanCar(@RequestBody List<TmDispatchPlanCarReqDto> request) {
        return ApiResult.createResult(tmSetDispatchService.savePlanCar(request));
    }


    /**
     * @description : 배차 목록 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @Operation(summary = "배차 목록 조회", description = "배차 목록 리스트 조회")
    @GetMapping("/v1.0/getDispatchList")
    public ApiResult<TmGetDispatchListResDto> getDispatchList(TmGetDispatchListReqDto request) {
        return ApiResult.createResult(tmSetDispatchService.getDispatchList(request));
    }
    
    /**
     * @description : 가배차 확정 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
	@Operation(summary = "배차 확정", description = "가배차 상태 배차 데이터 확정")
	@PostMapping(value = "/v1.0/updateConfirmed")
	public ApiResult<String> updateConfirmed(@RequestBody List<TmDispatchConfirmedReqDto> dtoList) {
		return ApiResult.createResult(tmSetDispatchService.updateConfirmed(dtoList));
	}
	
	/**
	 * @description : 실비차 배차 요청
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.27 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "실비차 배차 엔진 요청", description = "실비차  배차")
	@PostMapping(value = "/v1.0/setDispatchTemporaryCar")
	public ApiResult<TmSetDispatchResDto> createOutGroupDispatch(@RequestBody TmSetDispatchOutGroupReqDto dto) {
		return ApiResult.createResult(tmSetDispatchService.createOutGroupDispatch(dto));
	}
	
	/**
	 * @description : 배차 취소 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 OhEunBeom          생성 </pre>
	 */
	@Operation(summary = "배차 취소", description = "배차 취소 처리")
	@PostMapping(value = "/v1.0/cancelDispatch")
	public ApiResult<String> cancelDispatch(@RequestBody TmCancelDispatchReqDto request) {

        request.setUserId(userContext.getUserId());

		return ApiResult.createResult(tmSetDispatchService.cancelDispatch(request));
	}
	
	/**
	 * @description : 차량번호 변경 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 OhEunBeom          생성 </pre>
	 */
	@Operation(summary = "차량번호 변경", description = "배차 차량번호 변경 처리")
	@PostMapping(value = "/v1.0/changeCarNo") //TODO 리스트 처리 후 삭제예정
	public ApiResult<String> changeCarNo(@Valid @RequestBody TmChangeCarNoReqDto request) {
		request.setUserId(userContext.getUserId());
		return ApiResult.createResult(tmSetDispatchService.updateCarNo(request));
	}

    /**
     * @description : 배차 확정내역 조회 화면 저장 이벤트
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.06 OhEunBeom          생성 </pre>
     */
    @Operation(summary = "배차 확정내역 변경", description = "배차 확정내역 변경 리스트 처리")
    @PostMapping(value = "/v1.0/saveDispatchList")
    public ApiResult<String> saveDispatchList(@Valid @RequestBody List<TmChangeCarNoReqDto> requestList) {
        return ApiResult.createResult(tmSetDispatchService.saveDispatchList(requestList));
    }
}
