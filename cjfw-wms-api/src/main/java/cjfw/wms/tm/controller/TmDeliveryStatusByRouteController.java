package cjfw.wms.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDeliveryStatusByRouteReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByRouteResDto;
import cjfw.wms.tm.service.TmDeliveryStatusByRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 배송현황(경로별) 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/deliveryStatusByRoute")
@Tag(name = "배송현황(경로별) 조회", description = "배송현황(경로별) 조회 API")
public class TmDeliveryStatusByRouteController {

    @Autowired
    private TmDeliveryStatusByRouteService tmDeliveryStatusByRouteService;

    /**
     * 배송현황(경로별) 조회
     * @param reqDto 요청 DTO
     * @return 배송현황(경로별) 조회 결과
     * @author System
     * @date 2025.01.XX
     */
    @PostMapping("/v1.0/getDeliveryStatusByRoute")
    @Operation(summary = "배송현황(경로별) 조회", description = "배송현황을 경로별로 조회합니다.")
    public ApiResult<Page<TmDeliveryStatusByRouteResDto>> getDeliveryStatusByRoute(@RequestBody TmDeliveryStatusByRouteReqDto reqDto) {
        Page<TmDeliveryStatusByRouteResDto> result = tmDeliveryStatusByRouteService.getDeliveryStatusByRoute(reqDto);
        return ApiResult.createResult(result);
    }

}

