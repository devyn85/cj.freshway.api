package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsVehicleExitGroupCfgPopupReqDto;
import cjfw.wms.ms.dto.MsVehicleExitGroupCfgPopupResDto;
import cjfw.wms.ms.service.MsVehicleExitGroupCfgPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.08.01 
 * @description : 출차정보 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name="차량정보 > 출차그룹설정 > 출차그룹설정팝업")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/vehicleExitGroupCfg")
public class MsVehicleExitGroupCfgPopupController {
	private final MsVehicleExitGroupCfgPopupService msVehicleExitGroupCfgPopupService;
	
	/**
	 * @description : 출차그룹 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출차그룹 목록 조회", description = "출차그룹 목록 조회")
	@GetMapping("/v1.0/getVehicleExitGroupCfgList")
	public ApiResult<Page<MsVehicleExitGroupCfgPopupResDto>> getMsVehicleExitGroupCfgList(MsVehicleExitGroupCfgPopupReqDto msVehicleExitGroupCfgPopupReqDto, Page<?> page) {
		return ApiResult.createResult(msVehicleExitGroupCfgPopupService.getVehicleExitGroupCfgList(msVehicleExitGroupCfgPopupReqDto, page));
	}
	
	/**
	 * @description : 출차그룹 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.01 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출차그룹 목록 저장", description = "출차그룹 목록 저장")
	@PostMapping("/v1.0/saveVehicleExitGroupCfgList")
	public ApiResult<String> saveVehicleExitGroupCfgList(@RequestBody List<MsVehicleExitGroupCfgPopupReqDto> dto) {
    	return ApiResult.createResult(msVehicleExitGroupCfgPopupService.saveVehicleExitGroupCfgList(dto));
    }
}
