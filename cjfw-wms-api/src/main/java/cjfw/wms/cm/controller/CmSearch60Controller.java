package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarPopupReqDto;
import cjfw.wms.cm.dto.CmCarPopupResDto;
import cjfw.wms.cm.dto.CmCustBrandPopupReqDto;
import cjfw.wms.cm.dto.CmCustBrandPopupResDto;
import cjfw.wms.cm.service.CmSearch60Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.12 
 * @description : 공통 검색 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "공통 검색(60)", description = "공통 검색(60)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search60")
public class CmSearch60Controller {

	private final CmSearch60Service cmSearch60Service;

	/**
	 * @description : 차량 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "차량 목록 조회", description = "차량 목록 조회")
	@PostMapping(value = "/v1.0/getCarList")
	public ApiResult<Page<CmCarPopupResDto>> getCarList(@RequestBody CmCarPopupReqDto cmGetCarReqDto, Page<?> page) {
		return ApiResult.createResult(cmSearch60Service.getCarList(cmGetCarReqDto, page));
	}
	
	/**
	 * @description : OO 기능을 구현한 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.11 jun (kthis77@cj.net) 생성 </pre>
	 */
	@Operation(summary = "단일 차량 기사전화번호 조회", description = "단일 차량 기사전화번호 조회")
    @PostMapping(value = "/v1.0/getCarDriverPhoneInfo")
    public ApiResult<CmCarPopupResDto> getCarDriverPhoneInfo(@RequestBody CmCarPopupReqDto cmGetCarReqDto) {
        return ApiResult.createResult(cmSearch60Service.getCarDriverPhoneInfo(cmGetCarReqDto));
    }
	
	/**
	 * @description : 본점 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.13 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "본점 목록 조회", description = "본점 목록 조회")
	@PostMapping(value = "/v1.0/getCustBrandList")
	public ApiResult<Page<CmCustBrandPopupResDto>> getCustBrandList(@RequestBody CmCustBrandPopupReqDto cmGetCustBrandReqDto, Page<?> page) {		
		return ApiResult.createResult(cmSearch60Service.getCustBrandList(cmGetCustBrandReqDto, page));
	}
	
	/**
	 * @description : 저장품 자동발주 팝업 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "저장품 자동발주 팝업 조회 (목록)", description = "저장품 자동발주 팝업 조회 (목록)")
	@GetMapping(value = "/v1.0/getPopDataHeaderList")
	public ApiResult<Page<CmCustBrandPopupResDto>> getPopDataHeaderList(@Valid CmCustBrandPopupReqDto cmGetCustBrandReqDto, Page<?> page) {
		ApiResult<Page<CmCustBrandPopupResDto>> result = new ApiResult<>();	
		log.info("name  =  "+isNull(cmGetCustBrandReqDto.getName()));
		
		log.info("MultiSelect  =  "+ isNull(cmGetCustBrandReqDto.getMultiSelect()));
		
		if(!isNull(cmGetCustBrandReqDto.getName()) || !isNull(cmGetCustBrandReqDto.getMultiSelect()) ){			
			result = ApiResult.createResult(cmSearch60Service.getPopDataHeaderList(cmGetCustBrandReqDto, page));
		}
				
		return result;
	}
	
	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}

}