package cjfw.wms.tm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByPopReqDto;
import cjfw.wms.tm.dto.TmDispatchListByPopResDto;
import cjfw.wms.tm.service.TmDispatchListByPopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.15
 * @description : 배차목록(POP별) 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.15 System      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("/api/tm/dispatchListByPop")
@RequiredArgsConstructor
@Tag(name = "배차목록(POP별) 관리", description = "배차목록(POP별) 조회 API")
public class TmDispatchListByPopController {

    private final TmDispatchListByPopService tmDispatchListByPopService;

    @PostMapping("/v1.0/getDispatchListByPop")
    @Operation(summary = "배차목록(POP별) 조회", description = "POP별 배차목록을 조회합니다.")
    public ApiResult<Page<TmDispatchListByPopResDto>> getDispatchListByPop(@RequestBody TmDispatchListByPopReqDto reqDto) {
        Page<TmDispatchListByPopResDto> result = tmDispatchListByPopService.getDispatchListByPop(reqDto);
        return ApiResult.createResult(result);
    }
}
