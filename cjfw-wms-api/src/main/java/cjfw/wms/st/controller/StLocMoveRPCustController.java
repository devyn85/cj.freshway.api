package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveRPCustReqDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab1CustkeyDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab1SkuDto;
import cjfw.wms.st.dto.StLocMoveRPCustResTab2Dto;
import cjfw.wms.st.service.StLocMoveRPCustService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.18
 * @description : 거래처별보충(수원3층) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "StLocMoveRPCust", description = "거래처별보충(수원3층)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMoveRPCust")
public class StLocMoveRPCustController {

	private final StLocMoveRPCustService stLocMoveRPCustService;

	/**
	 * @description : 보충생성-거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "보충생성-거래처 목록 조회", description = "보충생성-거래처 목록 조회")
	@GetMapping(value = "/v1.0/getTab1CustkeyList")
	public ApiResult<List<StLocMoveRPCustResTab1CustkeyDto>> getTab1CustkeyList(@Valid StLocMoveRPCustReqDto dto) {


		return ApiResult.createResult(stLocMoveRPCustService.getTab1CustkeyList(dto));
	}
	
	/**
	 * @description : 보충생성-상품 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "보충생성-상품 목록 조회", description = "보충생성-상품 목록 조회")
	@GetMapping(value = "/v1.0/getTab1SkuList")
	public ApiResult<List<StLocMoveRPCustResTab1SkuDto>> getTab1SkuList(@Valid StLocMoveRPCustReqDto dto) {
		
		
		return ApiResult.createResult(stLocMoveRPCustService.getTab1SkuList(dto));
	}
	
	/**
	 * @description : ASRS 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "ASRS 목록 조회", description = "ASRS 목록 조회")
	@GetMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StLocMoveRPCustResTab2Dto>> getTab2MasterList(@Valid StLocMoveRPCustReqDto dto) {
		
		
		return ApiResult.createResult(stLocMoveRPCustService.getTab2MasterList(dto));
	}
	
	

}