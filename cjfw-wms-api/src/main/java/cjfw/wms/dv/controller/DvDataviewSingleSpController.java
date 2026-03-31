package cjfw.wms.dv.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dv.dto.DvDataviewSingleSpReqDto;
import cjfw.wms.dv.dto.DvDataviewSingleSpResDto;
import cjfw.wms.dv.service.DvDataviewSingleSpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.09
 * @description : 자동창고처리현황 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Tag(name = "재고 > 재고현황 > 자동창고처리현황", description = "자동창고처리현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dv/dvDataviewSingleSp")
public class DvDataviewSingleSpController {
	private final DvDataviewSingleSpService dvDataviewSingleSpService;

	/** @description :자동창고처리현황 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.09 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "이력재고처리현황 목록 조회", description = "이력재고처리현황 목록 조회")
	@GetMapping(value="/v1.0/getDvDataviewSingleSpList")
	public ApiResult<List<DvDataviewSingleSpResDto>> getDvDataviewSingleSpList(@Valid DvDataviewSingleSpReqDto reqDto) {

		return ApiResult.createResult(dvDataviewSingleSpService.getDvDataviewSingleSpList(reqDto));
	}
}
