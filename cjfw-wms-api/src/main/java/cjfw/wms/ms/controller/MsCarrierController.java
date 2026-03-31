package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCarrierDetailReqDto;
import cjfw.wms.ms.dto.MsCarrierDetailResDto;
import cjfw.wms.ms.dto.MsCarrierReqDto;
import cjfw.wms.ms.dto.MsCarrierResDto;
import cjfw.wms.ms.service.MsCarrierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.07.17 
 * @description : 운송사정보 조회 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 운송사정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/carrier")
public class MsCarrierController {
	private final MsCarrierService msCarrierService;
	
	/**
	 * @description : 운송사정보 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "운송사정보 목록 조회", description = "화면 필터 조건에 따른 운송사정보 목록을 조회합니다.")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCarrierResDto>> getMasterList(MsCarrierReqDto dto) {
		return ApiResult.createResult(msCarrierService.getMasterList(dto));
	}
	
	/**
	 * @description : 운송사정보 단건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "운송사정보 단건 조회", description = "운송사 단건 상세 정보를 조회합니다.")
    @GetMapping("/v1.0/getMaster")
    public ApiResult<MsCarrierDetailResDto> getMaster(MsCarrierDetailReqDto dto) {
        return ApiResult.createResult(msCarrierService.getMaster(dto));
    }
	
	/**
	 * @description : 운송사정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "운송사정보 목록 저장", description = "운송사정보 목록 저장")
    @PostMapping("/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody List<MsCarrierDetailReqDto> dto) {
        return ApiResult.createResult(msCarrierService.saveMasterList(dto));
    }
	
	/**
	 * @description : 운송사정보 단건 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.18 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
	@Operation(summary = "운송사정보 단건 수정", description = "운송사정보 단건 수정")
	@PostMapping("/v1.0/saveMaster")
    public ApiResult<String> saveMaster(@RequestBody List<MsCarrierDetailReqDto> dto) {
        return ApiResult.createResult(msCarrierService.saveMaster(dto));
    }
}
