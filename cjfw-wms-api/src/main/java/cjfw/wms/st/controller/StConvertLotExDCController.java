package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertLotExDCReqDto;
import cjfw.wms.st.dto.StConvertLotExDCResDto;
import cjfw.wms.st.service.StConvertLotExDCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 외부비축소비기한변경 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.15    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "재고 > 재고조정 > 외부비축소비기한변경", description = "외부비축소비기한변경 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertlotexdc")
public class StConvertLotExDCController {

	private final StConvertLotExDCService stConvertLotExDCService;

	/**
	 * @description : 외부비축소비기한변경 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.15    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "외부비축소비기한변경 목록 조회", description = "외부비축소비기한변경 목록 조회")
	@GetMapping(value = "/v1.0/getDataHeaderList")
	public ApiResult<List<StConvertLotExDCResDto>> getDataHeaderList(@Valid StConvertLotExDCReqDto dto) {
		return ApiResult.createResult(stConvertLotExDCService.getDataHeaderList(dto));
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
	@Operation(summary = "외부비축소비기한변경 저장", description = "외부비축소비기한변경 저장")
	@PostMapping(value = "/v1.0/saveStockConvertLot")
	public ApiResult<String> saveStockConvertLot(@RequestBody StConvertLotExDCReqDto stConvertLotExDCReqDto) throws Exception {
		return ApiResult.createResult(stConvertLotExDCService.saveStockConvertLot(stConvertLotExDCReqDto));
	}

}