package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmPlanOrderNewPopupReqDto;
import cjfw.wms.tm.dto.TmSetDispatchUnassignedOrderResDto;
import cjfw.wms.tm.service.TmPlanOrderNewPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.31 
 * @description : 신규 주문 알림 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("/api/tm/planOrderNewPopup")
@RequiredArgsConstructor
@Tag(name = "배송 > 배차계획", description = "신규 주문 알림 Controller")
public class TmPlanOrderNewPopupController {
	
	private final TmPlanOrderNewPopupService tmPlanOrderNewPopupService;
	
    /**
     * @description : 신규 주문 갯수 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/getNewOrderCount")
    @Operation(summary = "신규 주문 갯수 조회", description = "신규 주문 갯수 조회")
    public ApiResult<Integer> getNewOrderCount(@RequestBody TmPlanOrderNewPopupReqDto dto) {
        return ApiResult.createResult(tmPlanOrderNewPopupService.getNewOrderCount(dto));
    }
    
    /**
     * @description : 신규 주문 추가 (미사용 - deprecated)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.31 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @PostMapping("/v1.0/saveNewOrder")
    @Operation(summary = "신규 주문 추가 (미사용)", description = "신규 주문 추가 (미사용 - addNewOrders 사용)")
    @Deprecated
    public ApiResult<List<TmSetDispatchUnassignedOrderResDto>> saveNewOrder(@RequestBody TmPlanOrderNewPopupReqDto dto) {
        return ApiResult.createResult(tmPlanOrderNewPopupService.saveNewOrder(dto));
    }

    /**
     * @description : 신규 주문 추가 (TM_INPLAN 적재 + 실착지 목록 반환)
     *                - SP 호출하여 TM_INPLAN에 주문 적재
     *                - 적재된 신규 주문의 실착지 목록을 setDispatch API와 동일한 형식으로 반환
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.29 Claude Code           생성 </pre>
     */
    @PostMapping("/v1.0/addNewOrders")
    @Operation(summary = "신규 주문 추가", description = "신규 주문을 TM_INPLAN에 적재하고 실착지 목록을 반환합니다.")
    public ApiResult<List<TmSetDispatchUnassignedOrderResDto>> addNewOrders(@RequestBody TmPlanOrderNewPopupReqDto dto) {
        return ApiResult.createResult(tmPlanOrderNewPopupService.addNewOrders(dto));
    }

}
