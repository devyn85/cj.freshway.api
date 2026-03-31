package cjfw.wms.rt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtReceiptConfirmReqDto;
import cjfw.wms.rt.service.RtReceiptConfirmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.16
 * @description : 반품확정처리 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RtReceiptConfirmController API", description = "RtReceiptConfirmController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/receiptConfirm")
public class RtReceiptConfirmController {
    private final RtReceiptConfirmService rtReceiptConfirmService;

    /**
     * @description : 반품확정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품확정처리 조회 List", description = "반품확정처리 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody RtReceiptConfirmReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReceiptConfirmService.getMasterList(reqDto));
    }

    /**
     * @description : 반품확정처리 mamd 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품확정처리 mamd 조회 List", description = "반품확정처리 mamd 조회 List")
    @PostMapping(value = "/v1.0/getMaMdInfoList")
    public ApiResult<List> getMaMdInfoList(@RequestBody RtReceiptConfirmReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtReceiptConfirmService.getMaMdInfoList(reqDto));
    }

    /**
     * @description : 반품확정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품확정처리 상세 조회 List", description = "반품확정처리 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody RtReceiptConfirmReqDto reqDto) {
        return ApiResult.createResult(rtReceiptConfirmService.getDetailList(reqDto));
    }

    /**
     * @description : 반품확정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품확정처리2 조회 List", description = "반품확정처리2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody RtReceiptConfirmReqDto reqDto) {
        return ApiResult.createResult(rtReceiptConfirmService.getMasterList2(reqDto));
    }

    /**
     * @description : 반품확정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품확정처리 상세2 조회 List", description = "반품확정처리 상세2 조회 List")
    @PostMapping(value = "/v1.0/getDetailList2")
    public ApiResult<List> getDetailList2(@RequestBody RtReceiptConfirmReqDto reqDto) {
        return ApiResult.createResult(rtReceiptConfirmService.getDetailList2(reqDto));
    }

    /**
     * @description : 반품확정처리 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveMaster(@RequestBody RtReceiptConfirmReqDto dto) throws Exception {
		return ApiResult.createResult(rtReceiptConfirmService.saveMaster(dto));
	}

    /**
     * @description : 반품확정처리 임시저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "임시저장", description = "임시저장")
    @PostMapping(value = "/v1.0/tempSaveMaster")
    public ApiResult<String> tempSaveMaster(@RequestBody RtReceiptConfirmReqDto dto) throws Exception {
        return ApiResult.createResult(rtReceiptConfirmService.tempSaveMaster(dto));
    }

    /**
     * @description : 반품확정처리 이메일
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "이메일", description = "이메일")
    @PostMapping(value = "/v1.0/sendMaster")
    public ApiResult<String> sendMaster(@RequestBody RtReceiptConfirmReqDto dto) throws Exception {
        return ApiResult.createResult(rtReceiptConfirmService.sendMaster(dto));
    }

    /**
     * @description : 반품확정처리 취소
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.16 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "취소", description = "취소")
    @PostMapping(value = "/v1.0/cancelMaster")
    public ApiResult<String> cancelMaster(@RequestBody RtReceiptConfirmReqDto dto) throws Exception {
        return ApiResult.createResult(rtReceiptConfirmService.cancelMaster(dto));
    }
}
