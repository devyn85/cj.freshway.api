package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupPopResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictResDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsDistrictGroupPopResDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonReqDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonResDto;
import cjfw.wms.ms.service.MsCenterDlvDistrictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.29 
 * @description : 기준정보 > 센터기준정보 > 센터 권역 그룹 목록 조회 및 저장
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Deprecated
@Tag(name = "기준정보 > 센터기준정보 > 센터 배송 권역", description = "센터 배송 권역 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDlvDistrict")
public class MsCenterDlvDistrictController {
	
	private final MsCenterDlvDistrictService msCenterDlvDistrictService;
	
	
	/**
	 * @description : 센터 배송 권역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 조회", description = "조회 조건에 따른 센터 배송 권역 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDlvDistrictResDto>> getMasterList(@RequestBody MsCenterDlvDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getMasterList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 저장", description = "센터 배송 권역 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody MsCenterDlvDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.saveMasterList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 행정동 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 행정동 저장", description = "센터 배송 권역 행정동 목록 저장")
	@PostMapping(value = "/v1.0/saveHjdongList")
	public ApiResult<String> saveHjdongList(@RequestBody MsCenterDlvDistrictHjdongReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.saveHjdongList(dto));
	}

	/**
	 * @description : 저장할 배송 권역 검증 ( 중복 권역 체크 ) 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.16 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "센터 배송 권역 저장 행정동 검증", description = "다른 권역에 겹치는 행정동 존재유무 검증(겹칠시 덮어쯰움)")
	@PostMapping(value = "/v1.0/validateSaveHjdongList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> validateSaveHjdongList(@RequestBody MsCenterDlvDistrictHjdongReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getSaveHjdongListValidation(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 행정동 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 행정동 조회", description = "센터 배송 권역 행정동 목록 조회")
	@PostMapping(value = "/v1.0/getHjdongList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> getHjdongList(@RequestBody MsCenterDlvDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getHjdongList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 / 그룹 폴리곤 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 / 그룹 폴리곤 조회", description = "센터 배송 권역 / 그룹 폴리곤 조회")
	@PostMapping(value = "/v1.0/getDlvDistrictPolygon")
	public ApiResult<List<MsDlvDistrictPolygonResDto>> getDlvDistrictPolygon(@RequestBody MsDlvDistrictPolygonReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getDlvDistrictPolygon(dto));
	}
	
	
	/**
	 * @description : 센터 배송 권역 그룹 리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 리스트 조회", description = "센터 배송 권역 그룹 리스트 조회")
	@PostMapping(value = "/v1.0/getDistrictGroupList")
	public ApiResult<List<MsCenterDlvDistrictGroupResDto>> getDistrictGroupList(@RequestBody MsCenterDlvDistrictGroupReqDto dto){
		return ApiResult.createResult(msCenterDlvDistrictService.getDistrictGroupList(dto));
	}
	
	
	/**
	 * @description : 센터 배송 권역 사용 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 사용 현황 조회", description = "센터 배송 권역 사용 현황 조회")
	@PostMapping(value = "/v1.0/getCenterDistrictUsageList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> getCenterDistrictUsageList(@RequestBody MsCenterDlvDistrictGroupReqDto dto){
		return ApiResult.createResult(msCenterDlvDistrictService.getCenterDistrictUsageList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 그룹 X POP 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 X POP 저장", description = "배송 권역 그룹 X POP 리스트 저장")
	@PostMapping(value = "/v1.0/saveDistrictGroupxPop")
	public ApiResult<String> saveDistrictGroupXPop(@RequestBody MsCenterDlvDistrictGroupPopReqDto dto){
		return ApiResult.createResult(msCenterDlvDistrictService.saveDistrictGroupXPop(dto));
	}
	
	/**
	 * @description : 배송 권역 그룹 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 저장", description = "배송 권역 그룹 저장")
	@PostMapping(value = "/v1.0/saveDistrictGroup")
	public ApiResult<String> saveDistrictGroup(@RequestBody List<MsCenterDlvDistrictGroupReqDto> dto){
		return ApiResult.createResult(msCenterDlvDistrictService.saveDistrictGroup(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 그룹 X POP 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 X POP 조회", description = "센터 배송 권역 그룹 X POP 조회")
	@PostMapping(value = "/v1.0/getDistrictGroupXPop")
	public ApiResult<List<MsCenterDlvDistrictGroupPopResDto>> getDistrictGroupXPop(@RequestBody MsCenterDlvDistrictGroupPopReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getDistrictGroupXPop(dto));
	}
	
	/**
	 * @description : 권역 그룹 POP 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 POP 저장", description = "센터 권역 POP 저장")
	@PostMapping(value = "/v1.0/savePopList")
	public ApiResult<String> savePopList(@RequestBody List<MsDistrictGroupPopReqDto> dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.savePopList(dto));
	}
		
	/**
	 * @description : 센터 권역 그룹 POP 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 POP 조회", description = "센터 권역 POP 목록 조회")
	@PostMapping(value = "/v1.0/getPopList")
	public ApiResult<List<MsDistrictGroupPopResDto>> getPopList(@RequestBody MsDistrictGroupPopReqDto dto) {
		return ApiResult.createResult(msCenterDlvDistrictService.getPopList(dto));
	}
	
	
}
