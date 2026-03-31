package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import cjfw.wms.tm.service.TmDispatchOptionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.09.10
 * @description : 차량 배차 옵션 관리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 System Generated      생성
 */
@Tag(name = "TM > 배차관리", description = "차량 배차 옵션 관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tm/dispatchOptions")
public class TmDispatchOptionsController {

    private final TmDispatchOptionsService tmDispatchOptionsService;

    /**
     * @description : 차량 배차 옵션 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 System Generated      생성
     */
    @PostMapping(value = "/v1.0/setOptions")
    public ApiResult<String> saveDispatchOptions(@RequestBody TmDispatchOptionsReqDto reqDto) {
        return ApiResult.createResult(tmDispatchOptionsService.saveDispatchOptions(reqDto));
    }

  
    /**
     * @description : 배차옵션 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.26 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @GetMapping(value = "/v1.0/getOptions")
    public ApiResult<TmDispatchOptionsReqDto> getDispatchOptions(@Valid TmDispatchOptionsReqDto reqDto) {
        TmDispatchOptionsReqDto result = tmDispatchOptionsService.getDispatchOptions(reqDto);
        return ApiResult.createResult(result);
    }
}
