package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StStockLabelReqDto;
import cjfw.wms.st.dto.StStockLabelResDto;
import cjfw.wms.st.service.StStockLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고라벨출력 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StStockLabelController API", description = "StStockLabelController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stockLabel")
public class StStockLabelController {
	private final StStockLabelService stStockLabelService;

	/**
	 * @description : 재고라벨출력 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고라벨출력 조회 List", description = "재고라벨출력 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockLabelResDto>> getMasterList(@RequestBody StStockLabelReqDto reqDto) {
		return ApiResult.createResult(stStockLabelService.getMasterList(reqDto));
	}


}
