package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPurchaseCustPopupReqDto;
import cjfw.wms.ms.dto.MsPurchaseCustPopupResDto;
import cjfw.wms.ms.service.MsPurchaseCustPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.30 
 * @description : 수발주정보 수정 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.30 YeoSeungCheol 		(pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 수발주정보 수정", description = "수발주정보 수정 팝업")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ms/purchaseCust")
public class MsPurchaseCustPopupController {
	private final MsPurchaseCustPopupService msPurchaseCustPopupService;
	
	/**
	 * @description : 수발주정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 YeoSeungCheol 		(pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수발주정보 조회", description = "수발주정보 조회")
	@GetMapping("/v1.0/getPurchaseCust")
	public ApiResult<List<MsPurchaseCustPopupResDto>> getPurchaseCust(MsPurchaseCustPopupReqDto dto) {
    	return ApiResult.createResult(msPurchaseCustPopupService.getPurchaseCust(dto));
    }
	
	
	/**
	 * @description : 수발주정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.30 YeoSeungCheol 		(pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수발주정보 저장", description = "수발주정보 저장")
	@PostMapping("/v1.0/savePurchaseCust")
	public ApiResult<String> savePurchaseCust(@RequestBody List<MsPurchaseCustPopupReqDto> dto) {
    	return ApiResult.createResult(msPurchaseCustPopupService.savePurchaseCust(dto));
    }
}
