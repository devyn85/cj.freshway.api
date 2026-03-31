package cjfw.wms.cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.cm.dto.CmSearchPartnerPopupDetailReqDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupDetailResDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupReqDto;
import cjfw.wms.cm.dto.CmSearchPartnerPopupResDto;
import cjfw.wms.cm.service.CmSearchPartnerPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.24 
 * @description : 협력사정보 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.24 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name="기준정보 > 거래처기준정보 > 협력사정보", description = "협력사정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/cm/search")
public class CmSearchPartnerPopupController {
	private final CmSearchPartnerPopupService cmSearchPartnerPopupService;
	/**
	 * @description : 협력사 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.13 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "협력사 목록 조회", description = "협력사 목록 조회")
	@PostMapping("/v1.0/getPartnerList")
	public ApiResult<Page<CmSearchPartnerPopupResDto>> getPartnerList(@RequestBody CmSearchPartnerPopupReqDto cmSearchPartnerPopupReqDto, Page<?> page) {
		return ApiResult.createResult(cmSearchPartnerPopupService.getPartnerList(cmSearchPartnerPopupReqDto, page));
	}
	
	/**
	 * @description : 협력사 단건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "협력사 단건 조회", description = "협력사 단건 조회")
    @GetMapping("/v1.0/getPartner")
    public ApiResult<CmSearchPartnerPopupDetailResDto> getPartner(CmSearchPartnerPopupDetailReqDto dto) {
        return ApiResult.createResult(cmSearchPartnerPopupService.getPartner(dto));
    }
	
}
