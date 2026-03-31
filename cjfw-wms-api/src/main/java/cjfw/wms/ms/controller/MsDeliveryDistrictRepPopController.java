package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopResDto;
import cjfw.wms.ms.service.MsDeliveryDistrictRepPopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.01 
 * @description : 기준정보 > 배송 권역관리 > POP 조회 및 저장
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 배송 권역관리 > POP", description = "배송 그룹 POP 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/deliveryDistrictRepPop")
public class MsDeliveryDistrictRepPopController {
	
	private final MsDeliveryDistrictRepPopService msDeliveryDistrictRepPopService;

	
	/**
	 * @description : POP 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "POP 조회", description = "배송 권역 대표 POP 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsDistrictGroupPopResDto>> getMasterList(@RequestBody MsDistrictGroupPopReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictRepPopService.getMasterList(dto));
	}
	
	/**
	 * @description : POP 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 POP 저장", description = "센터 권역 POP 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> savePopList(@RequestBody List<MsDistrictGroupPopReqDto> dto) {
		return ApiResult.createResult(msDeliveryDistrictRepPopService.saveMasterList(dto));
	}

	@Operation(summary = "센터 대표POP 존재 여부 확인", description = "해당 센터에 유효한 대표POP가 존재하는지 확인")
	@PostMapping(value = "/v1.0/existsRepPop")
	public ApiResult<Boolean> existsRepPop(@RequestBody MsDistrictGroupPopReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictRepPopService.getRepPopExists(dto));
	}

	@Operation(summary = "대표POP TODATE 하위 영향도 검증", description = "대표POP TODATE 단축 시 하위 권역그룹POP 영향 여부 확인")
	@PostMapping(value = "/v1.0/getTodateChildImpact")
	public ApiResult<Map<String, String>> getTodateChildImpact(@RequestBody List<MsDistrictGroupPopReqDto> dtoList) {
		return ApiResult.createResult(msDeliveryDistrictRepPopService.getTodateChildImpact(dtoList));
	}
}
