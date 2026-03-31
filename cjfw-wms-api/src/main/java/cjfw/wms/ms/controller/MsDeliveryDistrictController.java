package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDistrictNewHjdongResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictHjdongResDto;
import cjfw.wms.ms.dto.MsCenterHjdongIntersectionResDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictResDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonReqDto;
import cjfw.wms.ms.dto.MsDlvDistrictPolygonResDto;
import cjfw.wms.ms.service.MsDeliveryDistrictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "기준정보 > 배송 권역관리 > 권역", description = "권역 그룹 저장 및 POP 할당")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/deliveryDistrict")
public class MsDeliveryDistrictController {
	
	private final MsDeliveryDistrictService msDeliveryDistrictService;
	
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
		return ApiResult.createResult(msDeliveryDistrictService.getMasterList(dto));
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
	public ApiResult<String> saveMasterList(@RequestBody List<MsCenterDlvDistrictReqDto> dtoList) {
		return ApiResult.createResult(msDeliveryDistrictService.saveMasterList(dtoList));
	}
	
	/**
	 * @description : 센터 배송 권역 행정동 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송 권역 행정동 저장", description = "센터 배송 권역 행정동 목록 저장")
	@PostMapping(value = "/v1.0/saveHjdongList")
	public ApiResult<String> saveHjdongList(@RequestBody MsCenterDlvDistrictHjdongReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictService.saveHjdongList(dto));
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
	@Operation(summary = "배송 권역 저장 행정동 검증", description = "다른 권역에 겹치는 행정동 존재유무 검증(겹칠시 덮어쯰움)")
	@PostMapping(value = "/v1.0/validateSaveHjdongList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> validateSaveHjdongList(@RequestBody MsCenterDlvDistrictHjdongReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictService.getSaveHjdongListValidation(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 행정동 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송 권역 행정동 조회", description = "센터 배송 권역 행정동 목록 조회")
	@PostMapping(value = "/v1.0/getHjdongList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> getHjdongList(@RequestBody MsCenterDlvDistrictReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictService.getHjdongList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 / 그룹 폴리곤 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송 권역 / 그룹 폴리곤 조회", description = "센터 배송 권역 / 그룹 폴리곤 조회")
	@PostMapping(value = "/v1.0/getDlvDistrictPolygon")
	public ApiResult<List<MsDlvDistrictPolygonResDto>> getDlvDistrictPolygon(@RequestBody MsDlvDistrictPolygonReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictService.getDlvDistrictPolygon(dto));
	}
	
	
	/**
	 * @description : 센터 배송 권역 사용 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.15 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송 권역 사용 현황 조회", description = "센터 배송 권역 사용 현황 조회")
	@PostMapping(value = "/v1.0/getCenterDistrictUsageList")
	public ApiResult<List<MsCenterDlvDistrictHjdongResDto>> getCenterDistrictUsageList(@RequestBody MsCenterDlvDistrictGroupReqDto dto){
		return ApiResult.createResult(msDeliveryDistrictService.getCenterDistrictUsageList(dto));
	}
	
	/**
	 * @description : 신규 센터 권역 행정동 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "신규 센터권역 행정동 조회", description = "신규 센터권역 행정동 조회")
	@PostMapping(value = "/v1.0/getNewHjdongList")
	public ApiResult<List<MsCenterDistrictNewHjdongResDto>> getNewHjdongList(@RequestBody MsCenterDlvDistrictReqDto dto){
		return ApiResult.createResult(msDeliveryDistrictService.getNewHjdongList(dto));
	}

	@Operation(summary = "센터 행정동 교집합 날짜 조회", description = "배송 권역에 행정동 추가 시 센터-행정동과 권역의 FROM/TO 교집합 계산")
	@PostMapping(value = "/v1.0/getCenterHjdongIntersectionList")
	public ApiResult<List<MsCenterHjdongIntersectionResDto>> getCenterHjdongIntersectionList(@RequestBody MsCenterDlvDistrictReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictService.getCenterHjdongIntersectionList(dto));
	}

	@Operation(summary = "권역 TODATE 하위 영향도 검증", description = "권역 TODATE 단축 시 하위 행정동 영향 여부 확인")
	@PostMapping(value = "/v1.0/getTodateChildImpact")
	public ApiResult<Map<String, String>> getTodateChildImpact(@RequestBody List<MsCenterDlvDistrictReqDto> dtoList) {
		return ApiResult.createResult(msDeliveryDistrictService.getTodateChildImpact(dtoList));
	}

}
