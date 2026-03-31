package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDistrictNewHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDistrictReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictResDto;
import cjfw.wms.ms.dto.MsCenterDistrictValidationResDto;
import cjfw.wms.ms.service.MsCenterDistrictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.20 
 * @description : 기준정보 > 센터기준정보 > 센터 권역  센터 권역 목록 조회 및 저장 기능을 구현한 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터 권역", description = "센터 권역 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDistrict")
public class MsCenterDistrictController {
	
	private final MsCenterDistrictService msCenterDistrictService;
	
	
	/**
	 * @description :센터 권역 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 목록 조회", description = "조회 조건에 따른 센터 권역 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDistrictHjdongResDto>> getMasterList(@RequestBody MsCenterDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDistrictService.getMasterList(dto));
	}
	
	/**
	 * @description : 센터 권역 폴리곤 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 폴리곤 목록 조회", description = "조회 조건에 따른 센터 권역 폴리곤 목록 조회")
	@PostMapping(value = "/v1.0/getCenterDistrictPolygon")
	public ApiResult<List<MsCenterDistrictResDto>> getCenterDistrictPolyglon(@RequestBody MsCenterDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDistrictService.getCenterDistrictPolyglon(dto));
	}
	
	/**
	 * @description : 행정동 신규건 포함 검증 API 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.18 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "행정동 검증 API(신규건 포함)", description = "행정동 검증 API(신규건 포함)")
	@PostMapping(value = "/v1.0/getCenterDistrictHjdongValidation")
	public ApiResult<List<MsCenterDistrictValidationResDto>> getHjdongValidationList(@RequestBody List<MsCenterDistrictHjdongReqDto> dtoList) {
		return ApiResult.createResult(msCenterDistrictService.getHjdongValidationList(dtoList));
	}

	/**
	 * @description : 센터 권역 행정동 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 행정동 저장", description = "센터별 행정동 데이터 저장 및 센터 폴리곤 생성")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody List<MsCenterDistrictHjdongReqDto> dtoList) {
		String errorMessage = msCenterDistrictService.saveMasterList(dtoList);
		if (!CanalFrameConstants.MSG_COM_SUC_CODE.equals(errorMessage)) {
			return ApiResult.createResult(errorMessage, -1);
		}
		return ApiResult.createResult(CanalFrameConstants.MSG_COM_SUC_CODE);
	}

	/**
	 * @description : 센터 권역 신규 행정동 목록
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.17 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 신규 행정동 목록 조회", description = "센터 권역 신규 행정동 목록 조회")
	@PostMapping(value = "/v1.0/getNewHjdongList")
	public ApiResult<List<MsCenterDistrictNewHjdongResDto>> getNewHjdongList(@RequestBody MsCenterDistrictReqDto dto) {
		return ApiResult.createResult(msCenterDistrictService.getNewHjdongList(dto));
	}

	@Operation(summary = "신규 행정동 폴리곤 미반영 목록 조회", description = "행정구역 변동으로 신규 생성된 행정동 중 폴리곤이 미반영된 목록 조회")
	@GetMapping(value = "/v1.0/getNewCreatedHjdongWithoutPolygon")
	public ApiResult<List<MsCenterDistrictNewHjdongResDto>> getNewCreatedHjdongWithoutPolygon() {
		return ApiResult.createResult(msCenterDistrictService.getNewCreatedHjdongWithoutPolygon());
	}

	@Operation(summary = "센터권역 행정동 TODATE 하위 영향도 검증", description = "센터권역 행정동 TODATE 단축 시 하위 배송권역 행정동 영향 여부 확인")
	@PostMapping(value = "/v1.0/getTodateChildImpact")
	public ApiResult<Map<String, String>> getTodateChildImpact(@RequestBody List<MsCenterDistrictHjdongReqDto> dtoList) {
		return ApiResult.createResult(msCenterDistrictService.getTodateChildImpact(dtoList));
	}

}
