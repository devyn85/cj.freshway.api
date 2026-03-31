package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsOutGroupSettingReqDto;
import cjfw.wms.ms.dto.MsOutGroupSettingResDto;
import cjfw.wms.ms.service.MsOutGroupSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="기준정보 > 배차계획 > 실비차 조건 설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/outGroupSetting")
public class MsOutGroupSettingController {
	
	private final MsOutGroupSettingService msOutGroupSettingService;
	
	/**
	 * @description : 실비차 목록 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "실비차 목록 조회", description = "실비차 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<MsOutGroupSettingResDto> getMasterList(MsOutGroupSettingReqDto dto) {
		return ApiResult.createResult(msOutGroupSettingService.getMasterList(dto));
	}
	
	/**
	 * @description : 실비차 설정 갱신
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "실비차 설정 저장", description = "실비차 설정 저장")
	@PostMapping(value = "/v1.0/updateMasterList")
	public ApiResult<String> updateMasterList(@RequestBody List<MsOutGroupSettingReqDto> dto) {
		return ApiResult.createResult(msOutGroupSettingService.updateMasterList(dto));
	}
	
}
