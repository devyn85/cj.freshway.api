package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSkuPopupReqDto;
import cjfw.wms.cm.dto.CmSkuPopupResDto;
import cjfw.wms.cm.service.CmSearch50Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 상품 코드 조회(상품정보) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "상품 코드 조회", description = "상품 코드 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch50Controller {

	private final CmSearch50Service CmSearch50Service;

	/**
	 * @description : 상품 코드 조회
	 * ->상품
	 * ->KIT상품
	 * ->화주상품
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상품 목록 조회", description = "상품 목록 조회")
	@PostMapping(value = "/v1.0/getSkuList")
	public ApiResult<Page<CmSkuPopupResDto>> getSkuList(@RequestBody CmSkuPopupReqDto cmSkuPopupReqDto, Page page) {
		return ApiResult.createResult(CmSearch50Service.getSkuList(cmSkuPopupReqDto, page));
	}

}