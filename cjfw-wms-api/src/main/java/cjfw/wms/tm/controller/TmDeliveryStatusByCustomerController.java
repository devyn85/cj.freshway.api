package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerProcessArrivalReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByCustomerResDto;
import cjfw.wms.tm.service.TmDeliveryStatusByCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.11.13
 * @description : 배송현황(거래처별) 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.13 OhEunbeom      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/deliveryStatusByCustomer")
@RequiredArgsConstructor
@Tag(name = "배송현황(거래처별) 조회", description = "배송현황(거래처별) 조회 API")
public class TmDeliveryStatusByCustomerController {

    private final TmDeliveryStatusByCustomerService tmDeliveryStatusByCustomerService;
    private final UserContext userContext;

    /**
     * 배송현황(거래처별) 조회
     * @param reqDto 요청 DTO
     * @return 배송현황(거래처별) 조회 결과
     * @author OhEunbeom
     * @date 2025.11.13
     */
    @PostMapping("/v1.0/getDeliveryStatusByCustomer")
    @Operation(summary = "배송현황(거래처별) 조회", description = "배송현황을 거래처별로 조회합니다.")
    public ApiResult<Page<TmDeliveryStatusByCustomerResDto>> getDeliveryStatusByCustomer(@RequestBody TmDeliveryStatusByCustomerReqDto reqDto) {
        log.info("배송현황(거래처별) 조회 요청: {}", reqDto);

        Page<TmDeliveryStatusByCustomerResDto> result = tmDeliveryStatusByCustomerService.getDeliveryStatusByCustomer(reqDto);
        log.info("배송현황(거래처별) 조회 결과: {} 건", result.getList().size());

        return ApiResult.createResult(result);
    }

    /**
     * 배송현황(거래처별) 도착처리
     * @param reqDto 요청 DTO
     * @return 처리 결과
     * @author OhEunbeom
     * @date 2025.11.13
     */
    @PostMapping("/v1.0/processArrival")
    @Operation(summary = "배송현황(거래처별) 도착처리", description = "배송현황(거래처별) 도착처리를 수행합니다.")
    public ApiResult<Void> processArrival(@RequestBody TmDeliveryStatusByCustomerProcessArrivalReqDto reqDto) {
        reqDto.setUserId(userContext.getUserId());
        log.info("배송현황(거래처별) 도착처리 요청: {}", reqDto);
        tmDeliveryStatusByCustomerService.updateArrival(reqDto);
        log.info("배송현황(거래처별) 도착처리 완료");

        return ApiResult.createResult(CanalFrameConstants.MSG_COM_SUC_CODE);
    }

}

