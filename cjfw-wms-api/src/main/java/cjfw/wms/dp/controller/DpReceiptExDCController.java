package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpReceiptExDCDetailResDto;
import cjfw.wms.dp.dto.DpReceiptExDCReqDto;
import cjfw.wms.dp.service.DpReceiptExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.14 
 * @description : 외부비축입고처리 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "입고 > 입고작업 > 외부비축입고처리", description = "외부비축입고처리 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/receiptexdc")
public class DpReceiptExDCController {

	private final DpReceiptExDCService dpReceiptExDCService;

	/**
	 * @description : 중계영업확정처리 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축입고처리 헤더 목록 조회", description = "외부비축입고처리 헤더 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<DpReceiptExDCDetailResDto>> getMasterList(@Valid DpReceiptExDCReqDto dto) {
		return ApiResult.createResult(dpReceiptExDCService.getMasterList(dto));
	}
	
	/**
     * @description : 중계영업확정처리 상세 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축입고처리 상세 목록 조회", description = "외부비축입고처리 상세 목록 조회")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List<DpReceiptExDCDetailResDto>> getDetailList(@Valid DpReceiptExDCReqDto dto) {
        return ApiResult.createResult(dpReceiptExDCService.getDetailList(dto));
    }

    /**
     * @throws Exception
     * @description : 중계영업확정처리 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.14    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축입고처리 저장", description = "외부비축입고처리 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody DpReceiptExDCReqDto dto) {
        return ApiResult.createResult(dpReceiptExDCService.saveMasterList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 중계영업확정처리 확정
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "중계영업확정처리 확정", description = "중계영업확정처리 확정")
    @PostMapping(value = "/v1.0/confirmMasterList")
    public ApiResult<String> confirmMasterList(@RequestBody DpReceiptExDCReqDto dto) {
        return ApiResult.createResult(dpReceiptExDCService.confirmMasterList(dto));
    }
    
    /**
     * @throws Exception
     * @description : 중계영업확정처리 반려
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "중계영업확정처리 반려", description = "중계영업확정처리 반려")
    @PostMapping(value = "/v1.0/rejectMasterList")
    public ApiResult<String> rejectMasterList(@RequestBody DpReceiptExDCReqDto dto) {
        return ApiResult.createResult(dpReceiptExDCService.rejectMasterList(dto));
    }

}