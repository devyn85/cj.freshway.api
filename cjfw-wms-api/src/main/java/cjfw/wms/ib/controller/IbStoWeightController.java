package cjfw.wms.ib.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbStoWeightReqDto;
import cjfw.wms.ib.service.IbStoWeightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.24
 * @description : 센터별물동량 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "IbStoWeightController API", description = "IbStoWeightController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/ib/stoWeight", "ltx/ib/stoWeight"})
public class IbStoWeightController {
    private final IbStoWeightService ibStoWeightService;

    /**
     * @description : 센터별물동량 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 조회 List", description = "센터별물동량 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody IbStoWeightReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(ibStoWeightService.getMasterList(reqDto));
    }

    /**
     * @description : 센터별물동량 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량2 조회 List", description = "센터별물동량2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody IbStoWeightReqDto reqDto) {
        return ApiResult.createResult(ibStoWeightService.getMasterList2(reqDto));
    }

    /**
     * @description : 센터별물동량 전월복사 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 전월복사 List", description = "센터별물동량 전월복사 List")
    @PostMapping(value = "/v1.0/copyMasterList")
    public ApiResult<List> copyMasterList(@RequestBody IbStoWeightReqDto reqDto) throws Exception {
        return ApiResult.createResult(ibStoWeightService.copyMasterList(reqDto));
    }

    /**
     * @description : 센터별물동량 저장 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 저장 List", description = "센터별물동량 저장 List")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List> saveMasterList(@RequestBody IbStoWeightReqDto reqDto) throws Exception {
        return ApiResult.createResult(ibStoWeightService.saveMasterList(reqDto));
    }

    /**
     * @description : 센터별물동량 동기화 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 동기화 List", description = "센터별물동량 동기화 List")
    @PostMapping(value = "/v1.0/saveBatchMasterList")
    public ApiResult<List> saveBatchMasterList(@RequestBody IbStoWeightReqDto reqDto) throws Exception {
        return ApiResult.createResult(ibStoWeightService.saveBatchMasterList(reqDto));
    }

    /**
     * @description : 센터별물동량 유효성 Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.24 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "센터별물동량 유효성 List", description = "센터별물동량 유효성 List")
    @PostMapping(value = "/v1.0/getExcelValChk")
    public ApiResult<List> getExcelValChk(@RequestBody IbStoWeightReqDto reqDto) throws Exception {
        return ApiResult.createResult(ibStoWeightService.getExcelValChk(reqDto));
    }
}
