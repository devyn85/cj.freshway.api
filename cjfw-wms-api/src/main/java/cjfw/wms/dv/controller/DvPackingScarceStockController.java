package cjfw.wms.dv.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dv.dto.DvPackingScarceStockReqDto;
import cjfw.wms.dv.dto.DvPackingScarceStockResDto;
import cjfw.wms.dv.service.DvPackingScarceStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.10
 * @description : 부족분리스트 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "DvPackingScarceStockController API", description = "부족분리스트")
@RequestMapping("api/dv/dvPackingScarceStock")
public class DvPackingScarceStockController {
	private final DvPackingScarceStockService dvPackingScarceStockService;

	/** @description : 부족분리스트 목록 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "부족분리스트 목록 조회", description = "부족분리스트 목록 조회")
	@GetMapping(value="/v1.0/getDvPackingScarceStockList")
	public ApiResult<List<DvPackingScarceStockResDto>> getDvPackingScarceStockList(@Valid DvPackingScarceStockReqDto reqDto) {
		return ApiResult.createResult(dvPackingScarceStockService.getDvPackingScarceStockList(reqDto));
	}
}
