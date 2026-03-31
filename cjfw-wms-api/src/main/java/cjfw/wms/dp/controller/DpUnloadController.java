package cjfw.wms.dp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpUnloadReqDto;
import cjfw.wms.dp.dto.DpUnloadResDto;
import cjfw.wms.dp.dto.DpUnloadResExcelDto;
import cjfw.wms.dp.service.DpUnloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.07.28
 * @description : 입고하차관리 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpUnloadController API", description = "DpUnloadController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/unload")
public class DpUnloadController {
    private final DpUnloadService dpUnloadService;

    /**
     * @description : 입고하차관리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고하차관리 조회 List", description = "입고하차관리 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<DpUnloadResDto>> getMasterList(@RequestBody DpUnloadReqDto reqDto) {
        return ApiResult.createResult(dpUnloadService.getMasterList(reqDto));
    }

    /**
     * @description : 입고하차관리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고하차관리 상세 조회 List", description = "입고하차관리 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<Map> getDetailList(@RequestBody DpUnloadReqDto reqDto) {
        return ApiResult.createResult(dpUnloadService.getDetailList(reqDto));
    }

    /**
     * @description : 입고하차관리 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고하차관리 엑셀 조회 List", description = "입고하차관리 엑셀 조회 List")
    @PostMapping(value = "/v1.0/getExcelList")
    public ApiResult<List<DpUnloadResExcelDto>> getExcelList(@RequestBody DpUnloadReqDto reqDto) {
        return ApiResult.createResult(dpUnloadService.getExcelList(reqDto));
    }

    /**
     * @description : 입고하차관리 하차등록
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.28 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "PO맵핑", description = "PO맵핑")
    @PostMapping(value = "/v1.0/saveCarLog")
    public ApiResult<String> saveCarLog(@RequestBody DpUnloadReqDto dto) throws Exception {
        return ApiResult.createResult(dpUnloadService.saveCarLog(dto));
    }
}
