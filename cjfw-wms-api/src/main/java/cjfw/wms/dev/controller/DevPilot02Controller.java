package cjfw.wms.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.dev.dto.DevPilot02MaskResDto;
import cjfw.wms.dev.dto.DevPilot02ReqDto;
import cjfw.wms.dev.dto.DevPilot02ResDto;
import cjfw.wms.dev.service.DevPilot02Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 센터상품속성 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Tag(name = "기준정보 > 상품기준정보 > 센터상품속성", description = "센터상품속성 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/dev/pilot02")
public class DevPilot02Controller {

	private final DevPilot02Service devPilot02Service;

	/**
	 * @description : 센터상품속성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "센터상품속성 목록 조회", description = "센터상품속성 목록 페이징 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<DevPilot02ResDto>> getMasterList(DevPilot02ReqDto dto, Page page) {
		return ApiResult.createResult(devPilot02Service.getMasterList(dto, page));
	}
	
	/**
	 * @throws Exception
	 * @description : 출고진행현황 상품제외 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "상품제외 처리", description = "저장위치정보 저장")
	@PostMapping(value = "/v1.0/saveInquiry")
	public ApiResult<String> saveInquiry(@RequestBody DevPilot02ReqDto dto) throws Exception {
		return ApiResult.createResult(devPilot02Service.saveInquiry(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 출고진행현황 상품제외 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "상품제외 처리", description = "저장위치정보 저장")
	@PostMapping(value = "/v1.0/saveInquiryByTempTable")
	public ApiResult<String> saveInquiryByTempTable(@RequestBody DevPilot02ReqDto dto) throws Exception {
		return ApiResult.createResult(devPilot02Service.saveInquiryByTempTable(dto));
	}
		
	
	
	/**
	 * @description : 마스킹 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	@Operation(summary = "마스킹 조회", description = "마스킹 조회")
	@GetMapping(value = "/v1.0/getMaskingList")
	public ApiResult<List<DevPilot02MaskResDto>> getMaskingList(DevPilot02ReqDto dto) {
		return ApiResult.createResult(devPilot02Service.getMaskingList(dto));
	}	
}