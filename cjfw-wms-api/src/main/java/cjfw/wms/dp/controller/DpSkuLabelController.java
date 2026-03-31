package cjfw.wms.dp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpSkuLabelReqDto;
import cjfw.wms.dp.service.DpSkuLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.07
 * @description : 입고라벨출력 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpSkuLabelController API", description = "DpSkuLabelController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/skuLabel")
public class DpSkuLabelController {
    private final DpSkuLabelService dpSkuLabelService;

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력 조회 List", description = "입고라벨출력 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody DpSkuLabelReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpSkuLabelService.getMasterList(reqDto));
    }

    /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력 상세 조회 List", description = "입고라벨출력 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody DpSkuLabelReqDto reqDto) {
        return ApiResult.createResult(dpSkuLabelService.getDetailList(reqDto));
    }

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력2 조회 List", description = "입고라벨출력2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody DpSkuLabelReqDto reqDto) {
        return ApiResult.createResult(dpSkuLabelService.getMasterList2(reqDto));
    }

    /**
     * @description : 입고라벨출력 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력 상세2 조회 List", description = "입고라벨출력 상세2 조회 List")
    @PostMapping(value = "/v1.0/getDetailList2")
    public ApiResult<List> getDetailList2(@RequestBody DpSkuLabelReqDto reqDto) {
        return ApiResult.createResult(dpSkuLabelService.getDetailList2(reqDto));
    }

    /**
     * @description : 입고라벨출력 예외저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "예외저장", description = "예외저장")
	@PostMapping(value = "/v1.0/saveDpSkuLabel")
	public ApiResult<String> saveDpSkuLabel(@RequestBody DpSkuLabelReqDto dto) throws Exception {
		return ApiResult.createResult(dpSkuLabelService.saveDpSkuLabel(dto));
	}

    /**
     * @description : 입고라벨출력 zone 조회
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "예외저장", description = "예외저장")
	@PostMapping(value = "/v1.0/getZone")
	public ApiResult<List> getZone(@RequestBody DpSkuLabelReqDto dto) throws Exception {
		return ApiResult.createResult(dpSkuLabelService.getZone(dto));
	}

    /**
     * @description : 입고라벨출력 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.07 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고라벨출력 인쇄 조회 List", description = "입고라벨출력 인쇄 조회 List")
    @PostMapping(value = "/v1.0/getPrintList")
    public ApiResult<List> getPrintList(@RequestBody DpSkuLabelReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpSkuLabelService.getPrintList(reqDto));
    }
}