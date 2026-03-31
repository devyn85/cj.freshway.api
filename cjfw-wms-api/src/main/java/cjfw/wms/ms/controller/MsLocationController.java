package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsLocationDetailReqDto;
import cjfw.wms.ms.dto.MsLocationDetailResDto;
import cjfw.wms.ms.dto.MsLocationReqDto;
import cjfw.wms.ms.dto.MsLocationResDto;
import cjfw.wms.ms.service.MsLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.06.19 
 * @description : 로케이션정보 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */

@Tag(name="기준정보 > 센터기준정보 > 로케이션정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/location")
public class MsLocationController {
	private final MsLocationService msLocationService;
	
	/**
	 * @description : 로케이션정보 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "로케이션정보 목록 조회", description = "화면 필터 조건에 따른 로케이션 목록을 조회합니다.")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsLocationResDto>> getMasterList(MsLocationReqDto dto) {
		return ApiResult.createResult(msLocationService.getMasterList(dto));
	}
	
	@Operation(summary = "로케이션정보 단건 조회", description = "단일 로케이션의 상세 정보를 조회합니다.")
    @GetMapping("/v1.0/getMaster")
    public ApiResult<MsLocationDetailResDto> getMaster(MsLocationDetailReqDto dto) {
        return ApiResult.createResult(msLocationService.getMaster(dto));
    }
	
    @Operation(summary = "로케이션정보 목록 저장", description = "로케이션정보 목록 저장")
    @PostMapping("/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody List<MsLocationReqDto> dto) {
        return ApiResult.createResult(msLocationService.saveMasterList(dto));
    }
	
    @Operation(summary = "로케이션정보 상세 저장", description = "로케이션정보 상세 조회")
    @PostMapping("/v1.0/saveMaster")
    public ApiResult<String> saveMaster(@RequestBody List<MsLocationReqDto> dto) {
        return ApiResult.createResult(msLocationService.saveMaster(dto));
    }
    
    @Operation(summary = "로케이션 정보 업서트", description = "로케이션 정보 업서트(신규/수정 단일 API)")
    @PostMapping("/v1.0/upsertMaster")
    public ApiResult<String> upsertMaster(@RequestBody List<MsLocationReqDto> dto) {
        return ApiResult.createResult(msLocationService.upsertMaster(dto));
    }
    
}
