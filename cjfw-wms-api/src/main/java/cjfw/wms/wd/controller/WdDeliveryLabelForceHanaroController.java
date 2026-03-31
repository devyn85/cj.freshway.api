package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdDeliveryLabelForceCoupangResDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceHanaroReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceHanaroResDto;
import cjfw.wms.wd.service.WdDeliveryLabelForceHanaroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.14
 * @description : 배송라벨출력(하나로엑셀) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.14 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdDeliveryLabelForceHanaro", description = "배송라벨출력(하나로엑셀)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabelForceHanaro")
public class WdDeliveryLabelForceHanaroController {

	private final WdDeliveryLabelForceHanaroService wdDeliveryLabelForceHanaroService;

	/**
	 * @description : 하나로 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.14 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "하나로 목록 조회", description = "하나로 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<WdDeliveryLabelForceHanaroResDto>> getTab1MasterList(@RequestBody WdDeliveryLabelForceHanaroReqDto dto, Page page) {


		return ApiResult.createResult(wdDeliveryLabelForceHanaroService.getTab1MasterList(dto, page));
	}
	
	/**
	 * @description : 쿠팡 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "쿠팡 목록 조회", description = "쿠팡 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<WdDeliveryLabelForceCoupangResDto>> getTab2MasterList(@RequestBody WdDeliveryLabelForceHanaroReqDto dto, Page page) {
		
		
		return ApiResult.createResult(wdDeliveryLabelForceHanaroService.getTab2MasterList(dto, page));
	}
	


}