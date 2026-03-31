package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmIndividualDispatchConfirmReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchPopReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchPopResDto;
import cjfw.wms.tm.dto.TmIndividualDispatchReqDto;
import cjfw.wms.tm.dto.TmIndividualDispatchResDto;
import cjfw.wms.tm.service.TmIndividualDispatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : devyn
 * @date : 2026.03.04
 * @description : 개별배차 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.03.04 devyn                 생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/individualDispatch")
@RequiredArgsConstructor
@Tag(name = "배차현황 > 개별배차", description = "개별배차 API")
public class TmIndividualDispatchController {

    private final TmIndividualDispatchService tmIndividualDispatchService;

    /**
     * @description : 개별배차 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    @PostMapping("/v1.0/getIndividualDispatchList")
    @Operation(summary = "개별배차 목록 조회", description = "개별배차 목록을 조회합니다.")
    public ApiResult<List<TmIndividualDispatchResDto>> getIndividualDispatchList(@RequestBody TmIndividualDispatchReqDto reqDto) {
        List<TmIndividualDispatchResDto> result = tmIndividualDispatchService.getIndividualDispatchList(reqDto);
        return ApiResult.createResult(result);
    }

    /**
     * @description : 개별배차 팝업 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    @GetMapping("/v1.0/getIndividualDispatchPopList")
    @Operation(summary = "개별배차 팝업 목록 조회", description = "개별배차 차량 팝업 목록을 조회합니다.")
    public ApiResult<Page<TmIndividualDispatchPopResDto>> getIndividualDispatchPopList(TmIndividualDispatchPopReqDto reqDto, Page page) {
        return ApiResult.createResult(tmIndividualDispatchService.getIndividualDispatchPopList(reqDto, page));
    }

    /**
     * @description : 개별배차 배차확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.03.04 devyn                 생성 </pre>
     */
    @PostMapping("/v1.0/updateConfirmDispatch")
    @Operation(summary = "개별배차 배차확정", description = "개별배차 건을 확정 처리합니다.")
    public ApiResult<String> updateConfirmDispatch(@RequestBody List<TmIndividualDispatchConfirmReqDto> reqDtoList) {
        return ApiResult.createResult(tmIndividualDispatchService.updateConfirmDispatch(reqDtoList));
    }

}
