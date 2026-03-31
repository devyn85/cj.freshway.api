package cjfw.wms.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByCustomerReqDto;
import cjfw.wms.tm.dto.TmDispatchListByCustomerResDto;
import cjfw.wms.tm.service.TmDispatchListByCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.09.10
 * @description : 배차목록(거래처별) 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 OhEunbeom      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/dispatchListByCustomer")
@Tag(name = "배차목록(거래처별) 조회", description = "배차목록(거래처별) 조회 API")
public class TmDispatchListByCustomerController {

    @Autowired
    private TmDispatchListByCustomerService tmDispatchListByCustomerService;

    /**
     * 배차목록(거래처별) 조회
     * @param reqDto 요청 DTO
     * @return 배차목록(거래처별) 조회 결과
     * @author OhEunbeom
     * @date 2025.09.10
     */
    @PostMapping("/v1.0/getDispatchListByCustomer")
    @Operation(summary = "배차목록(거래처별) 조회", description = "배차목록을 거래처별로 조회합니다.")
    public ApiResult<Page<TmDispatchListByCustomerResDto>> getDispatchListByCustomer(@RequestBody TmDispatchListByCustomerReqDto reqDto) {
        log.info("배차목록(거래처별) 조회 요청: {}", reqDto);

        Page<TmDispatchListByCustomerResDto> result = tmDispatchListByCustomerService.getDispatchListByCustomer(reqDto);
        log.info("배차목록(거래처별) 조회 결과: {} 건", result.getList().size());

        return ApiResult.createResult(result);
    }

}
