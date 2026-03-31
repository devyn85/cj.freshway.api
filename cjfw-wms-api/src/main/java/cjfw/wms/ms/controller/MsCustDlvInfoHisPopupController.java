package cjfw.wms.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsCustDlvInfoHisPopupReqDto;
import cjfw.wms.ms.dto.MsCustDlvInfoHisPopupResDto;
import cjfw.wms.ms.service.MsCustDlvInfoHisPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.28
 * @description : 고객배송조건 수신이력 조회 팝업 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name="기준정보 > 거래처기준정보 > 고객배송조건", description = "고객배송조건 수신이력 팝업")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/custdelivery")
public class MsCustDlvInfoHisPopupController {
	private final MsCustDlvInfoHisPopupService msCustDlvInfoHisPopupService;
	
	/**
	 * @description : 고객배송조건 수신이력 조회 팝업
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 * 
	 */
	@Operation(summary = "고객배송조건 수신이력 조회", description = "고객배송조건 수신이력 조회")
	@GetMapping("/v1.0/getCustDlvInfoHis")
	public ApiResult<Page<MsCustDlvInfoHisPopupResDto>> getCustDlvInfoHis(MsCustDlvInfoHisPopupReqDto dto, Page<?> page) {
		return ApiResult.createResult(msCustDlvInfoHisPopupService.getCustDlvInfoHis(dto, page));
	}
}
