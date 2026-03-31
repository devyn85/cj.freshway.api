package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentTRBLExDCReqDto;
import cjfw.wms.st.dto.StAdjustmentTRBLExDCResDto;
import cjfw.wms.st.service.StAdjustmentTRBLExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.04 
 * @description : 외부비축BL내재고이관 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "재고 > 재고작업 > 외부비축BL내재고이관", description = "외부비축BL내재고이관 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustmenttrblexdc")
public class StAdjustmentTRBLExDCController {

	private final StAdjustmentTRBLExDCService stAdjustmentTRBLExDCService;

	/**
	 * @description : 외부비축BL내재고이관 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.04    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축BL내재고이관 목록 조회", description = "외부비축BL내재고이관 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StAdjustmentTRBLExDCResDto>> getMasterList(@Valid StAdjustmentTRBLExDCReqDto dto) {
		return ApiResult.createResult(stAdjustmentTRBLExDCService.getMasterList(dto));
	}
	
	/**
     * @throws Exception
     * @description : 외부비축소비기한변경 저장
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.15    KimSunHo(sunhokim6229@cj.net)   생성
     */
    @Operation(summary = "외부비축BL내재고이관 저장", description = "외부비축BL내재고이관 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody StAdjustmentTRBLExDCReqDto dto) throws Exception {
        return ApiResult.createResult(stAdjustmentTRBLExDCService.saveMasterList(dto));
    }

}