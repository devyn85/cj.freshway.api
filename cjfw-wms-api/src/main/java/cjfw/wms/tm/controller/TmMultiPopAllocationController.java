package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmMultiPopAllocationReqDto;
import cjfw.wms.tm.dto.TmMultiPopAllocationResDto;
import cjfw.wms.tm.service.TmMultiPopAllocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.23 
 * @description : 거래처별이중 POP 배차 현황 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "배송 > 배차현황 > 거래처별이중 POP 배차 현황", description = "거래처별이중 POP 배차 현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/multipopallocation")
public class TmMultiPopAllocationController {

	private final TmMultiPopAllocationService tmMultiPopAllocationService;

	/**
	 * @description : 거래처별이중 POP 배차 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "거래처별이중 POP 배차 현황 조회", description = "거래처별이중 POP 배차 현황 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<TmMultiPopAllocationResDto>> getMasterList(@Valid TmMultiPopAllocationReqDto tmMultiPopAllocationReqDto) {
		return ApiResult.createResult(tmMultiPopAllocationService.getMasterList(tmMultiPopAllocationReqDto));
	}
	
	/**
     * @throws Exception
     * @description : 배차조정 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
//    @Operation(summary = "배차조정 저장", description = "배차조정 저장")
//    @PostMapping(value = "/v1.0/saveTmInplan")
//    public ApiResult<String> saveTmInplan(@RequestBody TmMultiPopAllocationReqDto tmMultiPopAllocationReqDto) throws Exception {
//        return ApiResult.createResult(tmMultiPopAllocationService.saveTmInplan(tmMultiPopAllocationReqDto));
//    }
}