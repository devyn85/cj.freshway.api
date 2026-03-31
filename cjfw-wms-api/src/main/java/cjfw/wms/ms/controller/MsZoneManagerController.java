package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsZoneManagerReqDto;
import cjfw.wms.ms.dto.MsZoneManagerResDto;
import cjfw.wms.ms.service.MsZoneManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.05.27 
 * @description : 존정보 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 센터기준정보 > 존정보", description = "존정보")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/zone")
public class MsZoneManagerController {

	private final MsZoneManagerService msZoneManagerService;

	/**
	 * @description : 존 정보 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.27 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "존 정보 조회", description = "존 정보 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsZoneManagerResDto>> getMasterList(@Valid MsZoneManagerReqDto dto) {
		return ApiResult.createResult(msZoneManagerService.getMasterList(dto));
	}
	
	/**
	 * @description : 존 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.27    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "존 정보 저장", description = "존 정보 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsZoneManagerReqDto> dto) {
		return ApiResult.createResult(msZoneManagerService.saveMasterList(dto));
	}

}