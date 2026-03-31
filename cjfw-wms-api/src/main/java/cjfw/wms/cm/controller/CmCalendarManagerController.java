package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmCalendarManagerReqDto;
import cjfw.wms.cm.dto.CmCalendarManagerResDto;
import cjfw.wms.cm.service.CmCalendarManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net)
 * @date : 2025.09.11
 * @description : 발주용 휴일관리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.11 YeoSeungCheol (pw6375@cj.net) 생성
 * </pre>
 */
@Tag(name="주문 > 주문등록 > 발주용 휴일관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/cm/calendarManager")
public class CmCalendarManagerController {
    private final CmCalendarManagerService cmCalendarManagerService;

    /**
     * @description : 발주용 휴일관리 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.11 YeoSeungCheol (pw6375@cj.net) 생성
     * </pre>
     */
    @Operation(summary = "캘린더 목록 조회", description = "필터 조건에 따른 캘린더 목록을 조회합니다.")
    @GetMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<CmCalendarManagerResDto>> getMasterList(CmCalendarManagerReqDto dto) {
        return ApiResult.createResult(cmCalendarManagerService.getMasterList(dto));
    }

    /**
     * @description : 발주용 휴일관리 목록 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.11 YeoSeungCheol (pw6375@cj.net) 생성
     * </pre>
     */
    @Operation(summary = "캘린더 목록 저장", description = "캘린더 목록을 저장합니다.")
    @PostMapping("/v1.0/saveMasterList")
    public ApiResult<List<CmCalendarManagerReqDto>> saveMasterList(@RequestBody List<CmCalendarManagerReqDto> dto) {
        return ApiResult.createResult(cmCalendarManagerService.saveMasterList(dto));
    }
}
