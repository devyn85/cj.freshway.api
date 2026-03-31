package cjfw.wms.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDispatchHistoryPopupResDto;
import cjfw.wms.tm.service.TmPlanCustomerDispatchHistoryPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차이력 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/planCustomerDispatchHistoryPopup")
@Tag(name = "배차이력 조회", description = "배차이력 조회 API")
public class TmPlanCustomerDispatchHistoryPopupController {

    @Autowired
    private TmPlanCustomerDispatchHistoryPopupService tmPlanCustomerDispatchHistoryPopupService;

    /**
     * 배차이력 조회
     * @param reqDto 요청 DTO
     * @return 배차이력 조회 결과
     * @author OhEunbeom
     * @date 2025.10.10
     */
    @PostMapping("/v1.0/getPlanCustomerDispatchHistoryPopup")
    @Operation(summary = "배차이력 조회", description = "배차이력을 조회합니다.")
    public ApiResult<Page<TmPlanCustomerDispatchHistoryPopupResDto>> getPlanCustomerDispatchHistoryPopup(@RequestBody TmPlanCustomerDispatchHistoryPopupReqDto reqDto) {
        log.info("배차이력 조회 요청: {}", reqDto);

        Page<TmPlanCustomerDispatchHistoryPopupResDto> result = tmPlanCustomerDispatchHistoryPopupService.getPlanCustomerDispatchHistoryPopup(reqDto);
        log.info("배차이력 조회 결과: {} 건", result.getList().size());

        return ApiResult.createResult(result);
    }

}
