package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDeliveryLabelSNReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResPrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab1DetailDto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab2Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelSNResTab3Dto;
import cjfw.wms.wd.service.WdDeliveryLabelSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.10.15
 * @description : 이력배송라벨출력 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "deliveryLabelSN", description = "이력배송라벨출력")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabelSN")
public class WdDeliveryLabelSNController {

	private final WdDeliveryLabelSNService wdDeliveryLabelSNService;

	/**
	 * @description : 이력배송라벨출력-분류표생성 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력배송라벨출력-분류표생성 목록 조회", description = "이력배송라벨출력-분류표생성 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdDeliveryLabelSNResTab1Dto>> getTab1MasterList(@RequestBody WdDeliveryLabelSNReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelSNService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 이력배송라벨출력-분류표생성 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력배송라벨출력-분류표생성 상세 조회", description = "이력배송라벨출력-분류표생성 상세 조회")
	@PostMapping(value = "/v1.0/getTab1DetailList")
	public ApiResult<List<WdDeliveryLabelSNResTab1DetailDto>> getTab1DetailList(@RequestBody WdDeliveryLabelSNReqDto dto) {


		return ApiResult.createResult(wdDeliveryLabelSNService.getTab1DetailList(dto));
	}
	
	/**
	 * @description : 이력배송라벨출력-분류표출력 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력배송라벨출력-분류표출력 목록 조회", description = "이력배송라벨출력-분류표출력 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdDeliveryLabelSNResTab2Dto>> getTab2MasterList(@RequestBody WdDeliveryLabelSNReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelSNService.getTab2MasterList(dto));
	}
	
	
	/**
	 * @description : 이력배송라벨출력-회수리스트 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력배송라벨출력-회수리스트 목록 조회", description = "이력배송라벨출력-회수리스트 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<WdDeliveryLabelSNResTab3Dto>> getTab3MasterList(@RequestBody WdDeliveryLabelSNReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelSNService.getTab3MasterList(dto));
	}	
	
	/**
	 * @description : 피킹지출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹지출력 조회", description = "피킹지출력 조회")
	@PostMapping(value = "/v1.0/getPrintPickingList")
	public ApiResult<WdDeliveryLabelSNResPrintDto> getPrintPickingList(@RequestBody WdDeliveryLabelSNReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelSNService.getPrintPickingList(dto));
	}	
	
	/**
	 * @description : 회수리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "회수리스트 조회", description = "회수리스트 조회")
	@PostMapping(value = "/v1.0/getPrintReturnList")
	public ApiResult<WdDeliveryLabelSNResPrintDto> getPrintReturnList(@RequestBody WdDeliveryLabelSNReqDto dto) {
		
		
		return ApiResult.createResult(wdDeliveryLabelSNService.getPrintReturnList(dto));
	}	
	
	/**
	 * @throws Exception
	 * @description : 이력배송라벨출력 분류표 생성
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "이력배송라벨출력 분류표 생성", description = "이력배송라벨출력 분류표 생성")
	@PostMapping(value = "/v1.0/saveCreationSN")
	public ApiResult<String> saveCreationSN(@RequestBody WdDeliveryLabelSNReqDto dto) throws Exception {
		return ApiResult.createResult(wdDeliveryLabelSNService.saveCreationSN(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 마감주문반영
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "마감주문반영", description = "마감주문반영")
	@PostMapping(value = "/v1.0/saveOrderclose")
	public ApiResult<String> saveOrderclose(@RequestBody WdDeliveryLabelSNReqDto dto) throws Exception {
		return ApiResult.createResult(wdDeliveryLabelSNService.saveOrderclose(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : INVOICE번호 출력여부 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "INVOICE번호 출력여부 저장", description = "INVOICE번호 출력여부 저장")
	@PostMapping(value = "/v1.0/saveInvoiceNoPrtYn")
	public ApiResult<String> saveInvoiceNoPrtYn(@RequestBody WdDeliveryLabelSNReqDto dto) throws Exception {
		return ApiResult.createResult(wdDeliveryLabelSNService.saveInvoiceNoPrtYn(dto));
	}
	
}