package cjfw.wms.wd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdPltDpReqDto;
import cjfw.wms.wd.dto.WdPltDpResDto;
import cjfw.wms.wd.service.WdPltDpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.22
 * @description : 재고 > 공용기 관리업 > PLT 수불 관리  Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 공용기 관리업 > PLT 수불 관리", description = "재고 > 공용기 관리업 > PLT 수불 관리를 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/pltDp")
public class WdPltDpController {
	private final WdPltDpService wdPltDpService;

	/**
	 * @description : 재고 > 공용기 관리업 > PLT 수불 관리 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리업 > PLT 수불 관리 관리 조회", description = "재고 > 공용기 관리업 > PLT 수불 관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Map<String, List<WdPltDpResDto>>> getMasterList(@RequestBody WdPltDpReqDto dto) {
		
		List<WdPltDpResDto> masterList = wdPltDpService.getMasterList(dto); 
		// 2025.11.05 추가: 재고조회
		List<WdPltDpResDto> stock = wdPltDpService.getStock(dto);
		
		return ApiResult.createResult(Map.of("masterList", masterList, "stock", stock));

	}

	/**
	 * @description : 재고 > 공용기 관리업 > PLT 수불 관리 관리 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.22 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 공용기 관리업 > PLT 수불 관리 관리 저장", description = "재고 > 공용기 관리업 > PLT 수불 관리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody WdPltDpReqDto dto) throws Exception {
	    return ApiResult.createResult(wdPltDpService.saveMasterList(dto));
	}
}
