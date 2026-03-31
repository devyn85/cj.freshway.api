package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmCostCenterPopupReqDto;
import cjfw.wms.cm.dto.CmCostCenterPopupResDto;
import cjfw.wms.cm.service.CmSearch80Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.09 
 * @description : 귀속 부서 조회(매각출고처리) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "귀속 부서 조회", description = "귀속 부서 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearch80Controller {

	private final CmSearch80Service cmSearch80Service;

	/**
	 * @description : 귀속 부서 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "테스트 목록 조회", description = "테스트 목록 조회")
	@GetMapping(value = "/v1.0/getCostCenterList")
	public ApiResult<Page<CmCostCenterPopupResDto>> getCostCenterList(@Valid CmCostCenterPopupReqDto cmCostCenterPopupReqDto, Page page) {
		return ApiResult.createResult(cmSearch80Service.getCostCenterList(cmCostCenterPopupReqDto, page));
	}
	
}