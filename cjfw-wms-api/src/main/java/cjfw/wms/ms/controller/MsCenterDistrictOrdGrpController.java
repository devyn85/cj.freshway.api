package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictOrdGrpResDto;
import cjfw.wms.ms.service.MsCenterDistrictOrdGrpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.28 
 * @description : 기준정보 > 센터기준정보 > 센터 권역  주문그룹 컨트롤러
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.28 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */

@Tag(name = "기준정보 > 센터기준정보 > 센터 권역 주문그룹", description = "센터 권역 주문 그룹 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDistrictOrdGrp")
public class MsCenterDistrictOrdGrpController {
	
	private final MsCenterDistrictOrdGrpService msCenterDistrictOrdGrpService;
	
	/**
	 * @description : 주문 그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "주문그룹 목록 조회", description = "주문그룹 목록 조회")
	@GetMapping(value = "/v1.0/getOrdGrpList")
	public ApiResult<List<MsCenterDistrictOrdGrpResDto>> getOrdGrpList() {
		return ApiResult.createResult(msCenterDistrictOrdGrpService.getOrdGrpList());
	}
	
	/**
	 * @description : 중복 행정동에 할당될 주문그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "센터 권역 주문그룹 목록 조회", description = "중복 행정동에 할당될 주문그룹 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDistrictOrdGrpResDto>> getMasterList(@RequestBody MsCenterDistrictOrdGrpReqDto dto) {
		return ApiResult.createResult(msCenterDistrictOrdGrpService.getMasterList(dto));
	}
	
	/**
	 * @description : 주문그룹 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "센터 권역 주문그룹 목록 저장", description = "주문그룹 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody List<MsCenterDistrictOrdGrpReqDto> dtoList) {
		return ApiResult.createResult(msCenterDistrictOrdGrpService.saveMasterList(dtoList));
	}

}
