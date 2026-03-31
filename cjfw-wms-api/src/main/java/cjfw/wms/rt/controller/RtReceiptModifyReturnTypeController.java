package cjfw.wms.rt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtReceiptModifyReturnTypeReqDto;
import cjfw.wms.rt.service.RtReceiptModifyReturnTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.10
 * @description : 반품회수/미회수변경 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RtReceiptModifyReturnTypeController API", description = "RtReceiptModifyReturnTypeController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/receiptModifyReturnType")
public class RtReceiptModifyReturnTypeController {
    private final RtReceiptModifyReturnTypeService rtReceiptModifyReturnTypeService;

    /**
     * @description : 반품회수/미회수변경 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품회수/미회수변경 조회 List", description = "반품회수/미회수변경 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody RtReceiptModifyReturnTypeReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReceiptModifyReturnTypeService.getMasterList(reqDto));
    }

    /**
     * @description : 반품회수/미회수변경 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.10 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "저장", description = "저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody RtReceiptModifyReturnTypeReqDto dto) throws Exception {
        return ApiResult.createResult(rtReceiptModifyReturnTypeService.saveMasterList(dto));
    }
}
