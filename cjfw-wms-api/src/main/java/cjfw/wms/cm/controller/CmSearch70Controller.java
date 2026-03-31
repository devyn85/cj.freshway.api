/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCarAreaPopupReqDto;
import cjfw.wms.cm.dto.CmCarAreaPopupResDto;
import cjfw.wms.cm.dto.CmDcPopupReqDto;
import cjfw.wms.cm.dto.CmDcPopupResDto;
import cjfw.wms.cm.service.CmSearch70Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : YangChangHwan (iamai@cj.net) 
 * @date : 2025.05.12 
 * @description : OO 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.12 YangChangHwan (iamai@cj.net) 생성 </pre>
*/
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch70Controller {

	private final CmSearch70Service service;

	/**
	 * 차량+권역 조회
	 */
	/** @description : 차량+권역 조회 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.12 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@PostMapping(value="/v1.0/getCarAreaList")
	public ApiResult<Page<CmCarAreaPopupResDto>> getCarAreaList(@RequestBody CmCarAreaPopupReqDto reqDto, Page<?> page) {
		return ApiResult.createResult(service.getCarAreaList(reqDto, page));
	}
	
	
	/** @description : 센터 목록 조회 Method 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.29 YangChangHwan (iamai@cj.net) 생성 </pre>
	*/
	@PostMapping(value="/v1.0/getDcList")
	public ApiResult<Page<CmDcPopupResDto>> getDcAreaList(@RequestBody CmDcPopupReqDto reqDto, Page<?> page) {
		return ApiResult.createResult(service.getDcList(reqDto, page));
	}
	
	
}