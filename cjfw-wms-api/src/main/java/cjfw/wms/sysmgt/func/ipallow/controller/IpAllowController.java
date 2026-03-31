/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.sysmgt.func.ipallow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowGetReqDto;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowGetResDto;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowSaveReqDto;
import cjfw.wms.sysmgt.func.ipallow.service.IpAllowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "IP허용 예외관리", description = "사용자 IP허용 예외관리 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sysmgt/func/ipallow")
public class IpAllowController {

    private final IpAllowService ipAllowService;

    /**
     * IP허용예외 리스트
     */
    @Operation(summary = "IP허용예외 리스트", description = "IP허용예외 리스트 조회")
    @GetMapping(value = "/list")
    public ApiResult<Page<List<IpAllowGetResDto>>> list(IpAllowGetReqDto ipAllowGetReqDto, Page page) {
        return ApiResult.createResult(ipAllowService.getIpAllowList(ipAllowGetReqDto,page));
    }

    /**
     * IP허용예외 저장(CUD)
     */
    @Operation(summary = "IP허용예외 저장", description = "IP허용예외 저장(등록/수정/삭제)")
    @PostMapping(value = "/save")
    public ApiResult save(@Valid @RequestBody IpAllowSaveReqDto ipAllowSaveReqDto) {
        return ApiResult.createResult(ipAllowService.saveIpAllow(ipAllowSaveReqDto));
    }

}