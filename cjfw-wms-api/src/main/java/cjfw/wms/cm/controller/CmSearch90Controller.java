package cjfw.wms.cm.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarPopPopupReqDto;
import cjfw.wms.cm.dto.CmCarPopPopupResDto;
import cjfw.wms.cm.dto.CmDriverPopupReqDto;
import cjfw.wms.cm.dto.CmDriverPopupResDto;
import cjfw.wms.cm.dto.CmSkuGroupListPopupReqDto;
import cjfw.wms.cm.dto.CmSkuGroupListPopupResDto;
import cjfw.wms.cm.service.CmSearch90Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo (jw.park@cj.net) 
 * @date : 2025.05.09 
 * @description : 파일럿 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
 */
@Tag(name = "TEST > 임시 > pilot90", description = "pilot90")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch90Controller {

    private final CmSearch90Service CmSearch90Service;
	


	
	/**
	 * 
	 * @description : 기사ID목록 조회  팝업 기능 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
	 */
	@Operation(summary = "기사ID목록 조회",description = "기사ID목록 조회")
	@PostMapping(value ="/v1.0/getDriverList" )
	public ApiResult<Page<CmDriverPopupResDto>> getDriverList(@RequestBody CmDriverPopupReqDto CmLocationPopupReqDto , Page<?> page){
		return ApiResult.createResult(CmSearch90Service.getDriverList(CmLocationPopupReqDto,page));
	}
	
	/**
	 * 
	 * @description : 차량 팝 리스트 조회팝업
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 ParkJinWoo (jw.park@cj.net) 생성
	 */
	@Operation(summary = "차량 팝 목록",description = "차량 팝 목록")
	@GetMapping(value ="/v1.0/getCarPopList" )
	public ApiResult<Page<CmCarPopPopupResDto>> getCarPOPList(@Valid CmCarPopPopupReqDto CmCarPopPopupReqDto , Page page){
		return ApiResult.createResult(CmSearch90Service.getCarPopList(CmCarPopPopupReqDto,page));
	}
	
	/**
	 * 
	 * @description :상품그룹2 목록조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.19 ParkJinWoo 생성
	 */
	@Operation(summary = "상품그룹2 목록조회",description = "상품그룹2 목록조회")
	@PostMapping(value ="/v1.0/getNewSkuGroup2List" )
	public ApiResult<Page<CmSkuGroupListPopupResDto>> getNewSkuGroup2List(@RequestBody CmSkuGroupListPopupReqDto CmSkuGroupListPopupReqDto , Page page){
		return ApiResult.createResult(CmSearch90Service.getNewSkuGroup2List(CmSkuGroupListPopupReqDto,page));
	}
	
}