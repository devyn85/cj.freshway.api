package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarInfoPopupReqDto;
import cjfw.wms.cm.dto.CmCarInfoPopupResDto;
import cjfw.wms.cm.dto.CmCustInfoPopupReqDto;
import cjfw.wms.cm.dto.CmCustInfoPopupResDto;
import cjfw.wms.cm.dto.CmPartnerInfoPopupReqDto;
import cjfw.wms.cm.dto.CmPartnerInfoPopupResDto;
import cjfw.wms.cm.dto.CmPopupReqDto;
import cjfw.wms.cm.dto.CmPopupResDto;
import cjfw.wms.cm.dto.CmSearchCustReqDto;
import cjfw.wms.cm.dto.CmSearchCustResDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupReqDto;
import cjfw.wms.cm.dto.CmSkuInfoPopupResDto;
import cjfw.wms.cm.service.CmSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.04.30
 * @description : 공통 검색 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.30 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "공통 검색", description = "공통 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearchController {

	private final CmSearchService cmSearchService;

	/**
	 * @description : 거래처 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.08 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 목록 조회", description = "거래처 목록 조회")
	@PostMapping(value = "/v1.0/getCustList")
	public ApiResult<List<CmSearchCustResDto>> getCustList(@RequestBody CmSearchCustReqDto cmSearchCustReqDto) {
		return ApiResult.createResult(cmSearchService.getCustList(cmSearchCustReqDto));
	}

	/** @description : 상품정보팝업 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.25 YangChangHwan (iamai@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상품정보팝업 목록 조회", description = "상품정보팝업 목록 조회")
	@GetMapping(value="/v1.0/getSkuPopup")
	public ApiResult<CmSkuInfoPopupResDto> getSkuPopup(@Valid CmSkuInfoPopupReqDto reqDto) {

		return ApiResult.createResult(cmSearchService.getSkuInfoPopup(reqDto));
	}

	/**
	 * @description : 거래처 상세 팝업 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 상세 팝업 조회", description = "거래처 상세 팝업 조회")
	@GetMapping(value="/v1.0/getCustInfoPopup")
	public ApiResult<CmCustInfoPopupResDto> getCustInfoPopup(@Valid CmCustInfoPopupReqDto reqDto) {

		return ApiResult.createResult(cmSearchService.getCustInfoPopup(reqDto));
	}
	/**
	 * @description : 협력사 상세 팝업 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.23   생성 </pre>
	 */
	@Operation(summary = "협력사 상세 팝업 조회", description = "협력사 상세 팝업 조회")
	@GetMapping(value="/v1.0/getPartnerInfoPopup")
	public ApiResult<CmPartnerInfoPopupResDto> getPartnerInfoPopup(@Valid CmPartnerInfoPopupReqDto reqDto) {
		return ApiResult.createResult(cmSearchService.getPartnerInfoPopup(reqDto));
	}

	/** @description : 상품정보팝업 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.25 YangChangHwan (iamai@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량 상세 팝업 조회", description = "차량 상세 팝업 조회")
	@GetMapping(value="/v1.0/getCarInfoPopup")
	public ApiResult<CmCarInfoPopupResDto> getCarInfoPopup(@Valid CmCarInfoPopupReqDto reqDto) {

		return ApiResult.createResult(cmSearchService.getCarInfoPopup(reqDto));
	}
	
	/**
	 * @description : BOX 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 SSS (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "BOX 목록 조회", description = "BOX 목록 조회")
	@GetMapping(value = "/v1.0/getBoxList")
	public ApiResult<Page<CmPopupResDto>> getBoxList(@Valid CmPopupReqDto dto, Page<CmPopupResDto> page) {
		return ApiResult.createResult(cmSearchService.getBoxList(dto, page));
	}
	
	   /**
     * @description : 원거리유형 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.05.09 SSS (kduimux@cj.net) 생성 </pre>
     */
    @Operation(summary = "원거리유형 목록 조회", description = "원거리유형 목록 조회")
    @PostMapping(value = "/v1.0/getDistanceTypeList")
    public ApiResult<Page<CmPopupResDto>> getDistanceTypeList(@Valid CmPopupReqDto dto, Page<CmPopupResDto> page) {
        return ApiResult.createResult(cmSearchService.getDistanceTypeList(dto, page));
    }

}