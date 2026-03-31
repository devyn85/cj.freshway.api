package cjfw.wms.wd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.RoInvoiceReqDto;
import cjfw.wms.wd.dto.RoInvoiceResDetailDto;
import cjfw.wms.wd.dto.RoInvoiceResDto;
import cjfw.wms.wd.service.RoInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.16
 * @description : 반품명세서출력  Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RoInvoiceController API", description = "RoInvoiceController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/roInvoice")
public class RoInvoiceController {
    private final RoInvoiceService roInvoiceService;

    /**
     * @description : 반품명세서출력  조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력(광역) 조회 List", description = "반품명세서출력  조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<RoInvoiceResDto>> getMasterList(@RequestBody RoInvoiceReqDto reqDto) {
        return ApiResult.createResult(roInvoiceService.getMasterList(reqDto));
    }

    /**
     * @description : 반품명세서출력  상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품명세서출력  상세 조회 List", description = "반품명세서출력  상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List<RoInvoiceResDetailDto>> getDetailList(@RequestBody RoInvoiceReqDto reqDto) {
        return ApiResult.createResult(roInvoiceService.getDetailList(reqDto));
    }

    /**
     * @description : 반품명세서출력  상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품명세서출력  인쇄 조회 List", description = "반품명세서출력  인쇄 조회 List")
    @PostMapping(value = "/v1.0/getPrintMasterList")
    public ApiResult<Map<String, List>> getPrintMasterList(@RequestBody RoInvoiceReqDto reqDto) {
        return ApiResult.createResult(roInvoiceService.getPrintMasterList(reqDto));
    }
}
