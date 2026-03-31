package cjfw.wms.dv.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dv.dto.DvDataviewMultiSpReqDto;
import cjfw.wms.dv.dto.DvDataviewMultiSpResDto;
import cjfw.wms.dv.service.DvDataviewMultiSpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.14
 * @description : 일배입출차이현황 Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Tag(name = "출고 > 출고현황 > 일배입출차이현황", description = "일배입출차이현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dv/dataviewMultiSp")
public class DvDataviewMultiSpController {

	private final DvDataviewMultiSpService dvDataviewMultiSpService;

	/**
	 * @description : 일배입출차이현황 List
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일배입출차이현황 List", description = "일배입출차이현황 List")
	@PostMapping(value="/v1.0/getDvDataviewMultiSpList")
	public ApiResult<List<DvDataviewMultiSpResDto>> getDvDataviewMultiSpDetailList(@RequestBody DvDataviewMultiSpReqDto reqDto) {
		return ApiResult.createResult(dvDataviewMultiSpService.getDvDataviewMultiSpList(reqDto));
	}


	/** @description : 상세 내역 List
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.14 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "상세 내역 List", description = "상세 내역 List")
	@PostMapping(value="/v1.0/getDvDataviewMultiSpDetailList")
	public ApiResult<List<DvDataviewMultiSpResDto>> getDvDataviewMultiSpDetailSubList(@RequestBody DvDataviewMultiSpReqDto reqDto) {
		return ApiResult.createResult(dvDataviewMultiSpService.getDvDataviewMultiSpDetailList(reqDto));
	}
}
