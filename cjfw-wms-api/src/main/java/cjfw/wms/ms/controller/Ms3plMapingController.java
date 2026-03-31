package cjfw.wms.ms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.Ms3plMapingReqDto;
import cjfw.wms.ms.service.Ms3plMapingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.18
 * @description : 3PL전산기준목록 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "Ms3plMapingController API", description = "Ms3plMapingController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/3plMaping")
public class Ms3plMapingController {
    private final Ms3plMapingService ms3plMapingService;

    /**
     * @description : 3PL전산기준목록 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "3PL전산기준목록 조회 List", description = "3PL전산기준목록 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody Ms3plMapingReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(ms3plMapingService.getMasterList(reqDto));
    }
}
