package cjfw.wms.dp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.dp.dto.DpSkuLabelInspectGrid1ResDto;
import cjfw.wms.dp.dto.DpSkuLabelInspectReqDto;
import cjfw.wms.dp.dto.DpSkuLabelInspectResDto;
import cjfw.wms.dp.service.DpSkuLabelInspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.24
 * @description : 입고라벨출력(검수) Controller Class
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@Tag(name = "DpSkuLabelInspectController API", description = "DpSkuLabelInspectController ���� API �Դϴ�.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dp/dpSkuLabelInspect")
public class DpSkuLabelInspectController {
	private final DpSkuLabelInspectService dpSkuLabelInspectService;

	/** @description : 입고라벨출력(검수) 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "입고라벨출력(검수) 조회 List", description = "입고라벨출력(검수) 조회 List")
	@PostMapping(value="/v1.0/getDpSkuLabelInspectList")
	public ApiResult<List<DpSkuLabelInspectResDto>> getDpSkuLabelInspectList(@RequestBody DpSkuLabelInspectReqDto reqDto) {
		return ApiResult.createResult(dpSkuLabelInspectService.getDpSkuLabelInspectList(reqDto));
	}

	/** @description : 입고라벨출력(검수) Grid1 조회 List Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.24 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@Operation(summary = "입고라벨출력(검수) Grid1 조회 List", description = "입고라벨출력(검수) Grid1 조회 List")
	@PostMapping(value="/v1.0/getDpSkuLabelInspectGrid1List")
	public ApiResult<List<DpSkuLabelInspectGrid1ResDto>> getDpSkuLabelInspectGrid1List(@RequestBody DpSkuLabelInspectReqDto reqDto) {
		return ApiResult.createResult(dpSkuLabelInspectService.getDpSkuLabelInspectGrid1List(reqDto));
	}
}
