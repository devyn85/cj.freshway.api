package cjfw.wms.ms.controller;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictDcOrdGrpResDto;
import cjfw.wms.ms.service.MsCenterDistrictDcOrdGrpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "기준정보 > 권역기준정보 > 센터 권역관리", description = "주문그룹 관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDistrictDcOrdGrp")
public class MsCenterDistrictDcOrdGrpController {
	
	private final MsCenterDistrictDcOrdGrpService msCenterDistrictDcOrdGrpService;
	
	
	/**
	 * @description : 센터 권역 주문그룹 우선순위 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "센터 권역 주문그룹 우선순위 조회", description = "주문그룹 우선순위 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDistrictDcOrdGrpResDto>> getMasterList(@RequestBody MsCenterDistrictDcOrdGrpReqDto dto) {
		return ApiResult.createResult(msCenterDistrictDcOrdGrpService.getOrdGrpMatrixList(dto));
	}

	/**
	 * @description : 센터 권역 주문그룹 우선순위 상세 조회
	 * @issues :<pre>
	 */
	@Operation(summary = "센터 권역 주문그룹 우선순위 상세 조회", description = "주문그룹 우선순위 상세 조회")
	@PostMapping(value = "/v1.0/getDcOrdGrpListByPrDccode")
	public ApiResult<List<MsCenterDistrictDcOrdGrpResDto>> getDcOrdGrpListByPrDccode(@RequestBody MsCenterDistrictDcOrdGrpReqDto dto) {
		return ApiResult.createResult(msCenterDistrictDcOrdGrpService.getDcOrdGrpListByPrDccode(dto));
	}

	/**
	 * @description : 센터 권역 주문그룹 우선순위 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Deprecated
	@Operation(summary = "센터 권역 주문그룹 우선순위 저장", description = "주문그룹 우선순위 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody List<MsCenterDistrictDcOrdGrpReqDto> dtoList) {
		return ApiResult.createResult(msCenterDistrictDcOrdGrpService.saveMasterList(dtoList));
	}

}
