package cjfw.wms.om.controller;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.model.ApiResult;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.om.dto.OmStockReocationCapaResDto;
import cjfw.wms.om.dto.OmStockReocationCompareResDto;
import cjfw.wms.om.dto.OmStockReocationPltResDto;
import cjfw.wms.om.dto.OmStockReocationReqDto;
import cjfw.wms.om.dto.OmStockReocationResDto;
import cjfw.wms.om.service.OmStockReocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.12.22
 * @description : 주문 > 주믄등록 > 재고재배치조회 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 재고재배치조회", description = "재고재배치조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/stockReocation")
public class OmStockReocationController {

	private final OmStockReocationService omStockReocationService;

	/**
	 * @description : 재고재배치조회 Data 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고재배치조회 Data 조회 (목록)", description = "재고재배치조회 plt 조회 (목록)")
	@GetMapping(value = "/v1.0/getDataList")
	public ApiResult<OmStockReocationResDto> getDataList() {
		return ApiResult.createResult(omStockReocationService.getDataList());
	}	
	
	/**
     * @description : AI팀 CSV 파일 저장 및 response
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/startOptimization")
	public boolean startOptimization(@RequestBody @Valid OmStockReocationReqDto dto) {
		return omStockReocationService.startOptimization(dto);
	}
    
    /**
     * @description : 상세정보 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
     */
    @GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<OmStockReocationResDto>> getDetailList() {
		return ApiResult.createResult(omStockReocationService.getDetailList());
	}
    
    /**
     * @description : 현재고 배차안 asis tobe 상품 비교
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
     */
    @GetMapping(value = "/v1.0/getSkuCompareList")
	public ApiResult<List<OmStockReocationCompareResDto>> getSkuCompareList(@Valid OmStockReocationReqDto dto) {
    	log.info("CompareList dto chekc controller===" + dto.toString());
    	return ApiResult.createResult(omStockReocationService.getSkuCompareList(dto));
	}
    
    /**
     * @description : AI팀 CSV 파일 저장 및 response 콜백 수신부
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.12.22 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
     */
    @PostMapping(value = "/v1.0/optimization")
	public void optimization(@RequestBody @Valid Map<String, Object> payload) {
		omStockReocationService.optimization(payload);
	}
	
}