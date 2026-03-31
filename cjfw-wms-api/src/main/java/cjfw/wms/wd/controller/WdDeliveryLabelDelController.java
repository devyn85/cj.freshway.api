package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.wd.dto.WdDeliveryLabelDelReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelDelResDto;
import cjfw.wms.wd.service.WdDeliveryLabelDelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.06.23
 * @description : 배송라벨삭제현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdDeliveryLabelDel", description = "배송라벨삭제현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabelDel")
public class WdDeliveryLabelDelController {

	private final WdDeliveryLabelDelService wdDeliveryLabelDelService;

	/**
	 * @description : 차량 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송라벨삭제현황 목록 조회", description = "배송라벨삭제현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdDeliveryLabelDelResDto>> getMasterList(@RequestBody WdDeliveryLabelDelReqDto wdDeliveryLabelDelReqDto, Page page) {


		return ApiResult.createResult(wdDeliveryLabelDelService.getMasterList(wdDeliveryLabelDelReqDto, page));
	}

}