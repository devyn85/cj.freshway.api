package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdInvoiceDetailResDto;
import cjfw.wms.wd.dto.WdInvoiceReqDto;
import cjfw.wms.wd.service.WdInvoiceDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net) 생성
 * @date : 2025.11.07
 * @description : 납품서출력(배송기사용) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.07 KimDongHyeon (tirran123@cj.net) 생성 </pre>
 */
@Tag(name = "WdInvoiceDriver", description = "납품서출력(배송기사용)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/invoiceDriver")
public class WdInvoiceDriverController {
    private final WdInvoiceDriverService wdInvoiceDriverService;

    /**
     * @description : 납품서출력(배송기사용) 상세목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.05.12 KimDongHyeon (tirran123@cj.net) 생성 </pre>
     */
    @Operation(summary = "납품서출력(배송기사용) 상세목록 조회", description = "납품서출력(배송기사용) 상세목록 조회")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List<WdInvoiceDetailResDto>> getDetailList(@RequestBody WdInvoiceReqDto dto) {
        return ApiResult.createResult(wdInvoiceDriverService.getDetailList(dto));
    }
}