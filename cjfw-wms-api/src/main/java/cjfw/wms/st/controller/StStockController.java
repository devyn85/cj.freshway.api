package cjfw.wms.st.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StStockDataCodeListResDto;
import cjfw.wms.st.dto.StStockReqDto;
import cjfw.wms.st.dto.StStockResDto;
import cjfw.wms.st.service.StStockService;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCMIF_ST_STOCKAMT;
import cjfw.wms.webservice.elecAmt.DT_MM0090_SCM_responseIF_ST_STOCKAMT_RET;
import cjfw.wms.webservice.elecAmt.SI_MM0090_SCM_SOProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 성상수 (kduimux@cj.net) 생성
 * @date : 2025.05.16
 * @description : 재고조회 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.16 성상수 (kduimux@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고현황 > 재고조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stock")
public class StStockController {
	private final StStockService stStockService;
	private final UserContext userContext;

	/**
	 * @description : 재고조회 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 성상수 (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고조회 목록 조회", description = "재고조회 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockResDto>> getMasterList(@RequestBody StStockReqDto dto) {
		return ApiResult.createResult(stStockService.getMasterList(dto));
	}
	
	/**
	 * @description : 존 리스트 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "존 리스트 조회 ", description = "존 리스트 조회 ")
	@GetMapping(value = "/v1.0/getDataCodeList")
	public ApiResult<List<StStockDataCodeListResDto>> getDataCodeList(@Valid StStockReqDto dto) {
		return ApiResult.createResult(stStockService.getDataCodeList(dto));
	}	
}