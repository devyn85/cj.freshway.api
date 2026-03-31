package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDeliveryLabelReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResCenterPickGroupDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1DetailDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1PrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1ReportCrossDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1ReportDto;
import cjfw.wms.wd.service.WdDeliveryLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.11.15
 * @description : 배송라벨출력 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "deliveryLabel", description = "배송라벨출력")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabel")
public class WdDeliveryLabelController {

	private final WdDeliveryLabelService wdDeliveryLabelService;

	/**
	 * @description : 배송라벨출력-분류표출력 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송라벨출력-분류표출력 목록 조회", description = "배송라벨출력-분류표출력 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdDeliveryLabelResTab1Dto>> getTab1MasterList(@RequestBody WdDeliveryLabelReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 배송라벨출력-분류표출력 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송라벨출력-분류표출력 상세 조회", description = "배송라벨출력-분류표출력 상세 조회")
	@PostMapping(value = "/v1.0/getTab1DetailList")
	public ApiResult<List<WdDeliveryLabelResTab1DetailDto>> getTab1DetailList(@RequestBody WdDeliveryLabelReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelService.getTab1DetailList(dto));
	}
	
	
	/**
	 * @description : 배송분류표회수리스트 일반 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송분류표회수리스트 일반 조회", description = "배송분류표회수리스트 일반 조회")
	@PostMapping(value = "/v1.0/getTab1ReportList")
	public ApiResult<List<WdDeliveryLabelResTab1ReportDto>> getTab1ReportList(@RequestBody WdDeliveryLabelReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelService.getTab1ReportList(dto));
	}
	
	/**
	 * @description : 배송분류표회수리스트 Cross 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송분류표회수리스트 Cross 조회", description = "배송분류표회수리스트 Cross 조회")
	@PostMapping(value = "/v1.0/getTab1ReportCrossList")
	public ApiResult<List<WdDeliveryLabelResTab1ReportCrossDto>> getTab1ReportCrossList(@RequestBody WdDeliveryLabelReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelService.getTab1ReportCrossList(dto));
	}
	
	/**
	 * @description : 센터별 피킹그룹 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터별 피킹그룹 조회", description = "센터별 피킹그룹 조회")
	@PostMapping(value = "/v1.0/getCenterPickGroupList")
	public ApiResult<List<WdDeliveryLabelResCenterPickGroupDto>> getCenterPickGroupList(@RequestBody WdDeliveryLabelReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelService.getCenterPickGroupList(dto));
	}

	/**
	 * @throws Exception
	 * @description : 배송라벨출력 인쇄 생성(목록)
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송라벨출력 인쇄 생성(목록)", description = "배송라벨출력 인쇄 생성(목록)")
	@PostMapping(value = "/v1.0/savePrintHeaderList")
	public ApiResult<List<WdDeliveryLabelResTab1PrintDto>> savePrintHeaderList(@RequestBody WdDeliveryLabelReqDto dto) throws Exception {
		return ApiResult.createResult(wdDeliveryLabelService.savePrintHeaderList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 배송라벨출력 인쇄 생성(상세)
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송라벨출력 인쇄 생성(상세)", description = "배송라벨출력 인쇄 생성(상세)")
	@PostMapping(value = "/v1.0/savePrintDetailList")
	public ApiResult<List<WdDeliveryLabelResTab1PrintDto>> savePrintDetailList(@RequestBody WdDeliveryLabelReqDto dto) throws Exception {
		return ApiResult.createResult(wdDeliveryLabelService.savePrintDetailList(dto));
	}
}