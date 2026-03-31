package cjfw.wms.rt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.rt.dto.RtQCConfirmReqDto;
import cjfw.wms.rt.service.RtQCConfirmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.09.23
 * @description : 반품판정처리 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "RtQCConfirmController API", description = "RtQCConfirmController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/rt/qcConfirm")
public class RtQCConfirmController {
    private final RtQCConfirmService rtQCConfirmService;

    /**
     * @description : 반품판정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품판정처리 조회 List", description = "반품판정처리 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody RtQCConfirmReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(rtQCConfirmService.getMasterList(reqDto));
    }

    /**
     * @description : 반품판정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품판정처리 상세 조회 List", description = "반품판정처리 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody RtQCConfirmReqDto reqDto) {
        return ApiResult.createResult(rtQCConfirmService.getDetailList(reqDto));
    }

    /**
     * @description : 반품판정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품판정처리2 조회 List", description = "반품판정처리2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody RtQCConfirmReqDto reqDto) {
        return ApiResult.createResult(rtQCConfirmService.getMasterList2(reqDto));
    }

    /**
     * @description : 반품판정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "반품판정처리 상세2 조회 List", description = "반품판정처리 상세2 조회 List")
    @PostMapping(value = "/v1.0/getDetailList2")
    public ApiResult<List> getDetailList2(@RequestBody RtQCConfirmReqDto reqDto) {
        return ApiResult.createResult(rtQCConfirmService.getDetailList2(reqDto));
    }

    /**
     * @description : 반품판정처리 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody RtQCConfirmReqDto dto) throws Exception {
		return ApiResult.createResult(rtQCConfirmService.saveMasterList(dto));
	}

    /**
     * @description : 반품판정처리 저장2
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/saveMasterList2")
	public ApiResult<Object> saveMasterList2(@RequestBody RtQCConfirmReqDto dto) throws Exception {
		return ApiResult.createResult(rtQCConfirmService.saveMasterList2(dto));
	}

    /**
     * @description : 반품판정처리 삭제
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.09.23 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
	@Operation(summary = "삭제", description = "삭제")
	@PostMapping(value = "/v1.0/deleteMasterList")
	public ApiResult<String> deleteMasterList(@RequestBody RtQCConfirmReqDto dto) throws Exception {
		return ApiResult.createResult(rtQCConfirmService.deleteMasterList(dto));
	}
}
