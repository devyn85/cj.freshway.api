package cjfw.wms.dp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpReceiptBoxReqDto;
import cjfw.wms.dp.service.DpReceiptBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.08
 * @description : 입고확정처리(수원3층) Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpReceiptBoxController API", description = "DpReceiptBoxController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/receiptBox")
public class DpReceiptBoxController {
    private final DpReceiptBoxService dpReceiptBoxService;

    /**
     * @description : 입고확정처리(수원3층) 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리(수원3층) 조회 List", description = "입고확정처리(수원3층) 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody DpReceiptBoxReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpReceiptBoxService.getMasterList(reqDto));
    }

    /**
     * @description : 입고확정처리(수원3층) 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리(수원3층) 상세 조회 List", description = "입고확정처리(수원3층) 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody DpReceiptBoxReqDto reqDto) {
        return ApiResult.createResult(dpReceiptBoxService.getDetailList(reqDto));
    }

    /**
     * @description : 입고확정처리(수원3층) 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveMaster(@RequestBody DpReceiptBoxReqDto dto) throws Exception {
		return ApiResult.createResult(dpReceiptBoxService.saveMaster(dto));
	}

    /**
     * @description : 입고확정처리(수원3층) 대상확정
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "대상확정", description = "대상확정")
	@PostMapping(value = "/v1.0/saveDetail")
	public ApiResult<String> saveDetail(@RequestBody DpReceiptBoxReqDto dto) throws Exception {
		return ApiResult.createResult(dpReceiptBoxService.saveDetail(dto));
	}

    /**
     * @description : 입고확정처리(수원3층) 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리(수원3층) 조회 List", description = "입고확정처리(수원3층) 조회 List")
    @PostMapping(value = "/v1.0/getExcelList")
    public ApiResult<List> getExcelList(@RequestBody DpReceiptBoxReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpReceiptBoxService.getExcelList(reqDto));
    }


    /**
     * @description : 입고확정처리(수원3층) PLTID 조회 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.08 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리(수원3층) 상세 조회 List", description = "입고확정처리(수원3층) 상세 조회 List")
    @PostMapping(value = "/v1.0/getMaxStockId")
    public ApiResult<String> getMaxStockId(@RequestBody DpReceiptBoxReqDto reqDto) {
        ApiResult<String> result = ApiResult.createResult();
        result.setData(dpReceiptBoxService.getMaxStockId(reqDto));
        return result;
    }
}
