package cjfw.wms.rt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtReturnOutReqDto;
import cjfw.wms.rt.service.RtReturnOutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.10.13
 * @description : 협력사반품지시 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RtReturnOutController API", description = "RtReturnOutController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/returnOut")
public class RtReturnOutController {
    private final RtReturnOutService rtReturnOutService;

    /**
     * @description : 협력사반품지시 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시 조회 List", description = "협력사반품지시 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody RtReturnOutReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReturnOutService.getMasterList(reqDto));
    }

    /**
     * @description : 협력사반품지시 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시 상세 조회 List", description = "협력사반품지시 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody RtReturnOutReqDto reqDto) {
        return ApiResult.createResult(rtReturnOutService.getDetailList(reqDto));
    }

    /**
     * @description : 협력사반품지시 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시2 조회 List", description = "협력사반품지시2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody RtReturnOutReqDto reqDto) {
        return ApiResult.createResult(rtReturnOutService.getMasterList2(reqDto));
    }

    /**
     * @description : 협력사반품지시 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시 상세2 조회 List", description = "협력사반품지시 상세2 조회 List")
    @PostMapping(value = "/v1.0/getDetailList2")
    public ApiResult<List> getDetailList2(@RequestBody RtReturnOutReqDto reqDto) {
        return ApiResult.createResult(rtReturnOutService.getDetailList2(reqDto));
    }

    /**
     * @description : 협력사반품지시 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveMaster(@RequestBody RtReturnOutReqDto dto) throws Exception {
		return ApiResult.createResult(rtReturnOutService.saveMaster(dto));
	}

    /**
     * @description : 협력사반품지시 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시 조회 List", description = "협력사반품지시 조회 List")
    @PostMapping(value = "/v1.0/getExcelList")
    public ApiResult<List> getExcelList(@RequestBody RtReturnOutReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReturnOutService.getExcelList(reqDto));
    }

    /**
     * @description : 협력사반품지시 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.10.13 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "협력사반품지시 조회 List", description = "협력사반품지시 조회 List")
    @PostMapping(value = "/v1.0/getExcelList2")
    public ApiResult<List> getExcelList2(@RequestBody RtReturnOutReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReturnOutService.getExcelList2(reqDto));
    }
}
