package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.st.dto.StConvertCgExDcDetailListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderSubListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcSaveReqDto;
import cjfw.wms.st.service.StConvertCgExDcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.18 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.18 ParkJinWoo 생성
 */
@Tag(name = "재고 > 재고조정 > 외부비축재고속성변경")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertCgExDc")
public class StConvertCgExDcController {
	private final StConvertCgExDcService stConvertCgExDcService;
	
	/**
	 * @description : 외부비축재고속성변경 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고속성변경 헤더목록 조회", description = "외부비축재고속성변경 헤더목록 조회")
	@PostMapping(value = "/v1.0/getStConvertCgExDcHeaderHeaderList")
	public ApiResult<Object> getStConertCgExDcHeaderHeaderList(@Valid @RequestBody StConvertCgExDcHeaderListReqDto stConertCgExDcHeaderListReqDto) {
		return ApiResult.createResult(stConvertCgExDcService.getStConertCgExDcHeaderList(stConertCgExDcHeaderListReqDto));
	}
	/**
	 * @description : 외부비축재고속성변경 헤더서브목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고속성변경 헤더서브목록 조회", description = "외부비축재고속성변경 헤더목록 조회")
	@PostMapping(value = "/v1.0/getStConvertCgExDcHeaderSubList")
	public ApiResult<Object> getStConertCgExDcHeaderHeaderSubList(@Valid @RequestBody StConvertCgExDcHeaderSubListReqDto stConertCgExDcHeaderSubListReqDto) {
		return ApiResult.createResult(stConvertCgExDcService.getStConertCgExDcHeaderHeaderSubList(stConertCgExDcHeaderSubListReqDto));
	}
	/**
	 * @description : 외부비축재고속성변경 상세목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고속성변경 상세목록 조회", description = "외부비축재고속성변경 헤더목록 조회")
	@GetMapping(value = "/v1.0/getStConvertCgExDcDetailList")
	public ApiResult<Object> getStConertCgExDcDetailList(@Valid  StConvertCgExDcDetailListReqDto stConvertCgExDcDetailListReqDto,Page page) {
		return ApiResult.createResult(stConvertCgExDcService.getStConertCgExDcDetailList(stConvertCgExDcDetailListReqDto, page));
	}
	/**
	 * @description : 외부비축재고속성변경 상세목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	@Operation(summary = "외부비축재고속성변경 상세목록 조회", description = "외부비축재고속성변경 헤더목록 조회")
	@PostMapping(value = "/v1.0/saveStConvertCgExDc")
	public ApiResult<Object> saveStConertCgExDc(@RequestBody StConvertCgExDcSaveReqDto saveReqDto) {
		return ApiResult.createResult(stConvertCgExDcService.saveStConertCgExDc(saveReqDto));
	}
}