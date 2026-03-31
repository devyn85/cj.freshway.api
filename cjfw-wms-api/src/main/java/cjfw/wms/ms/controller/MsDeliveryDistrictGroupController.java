package cjfw.wms.ms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupPopReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupReqDto;
import cjfw.wms.ms.dto.MsCenterDlvDistrictGroupResDto;
import cjfw.wms.ms.dto.MsDeliveryDistrictGroupXPopReqDto;
import cjfw.wms.ms.dto.MsDeliveryDistrictGroupXPopResDto;
import cjfw.wms.ms.service.MsDeliveryDistrictGroupService;
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
@Tag(name = "기준정보 > 배송 권역관리 > 권역그룹", description = "권역 그룹 저장 및 POP 할당")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/deliveryDistrictGroup")
public class MsDeliveryDistrictGroupController {
	
	private final MsDeliveryDistrictGroupService msDeliveryDistrictGroupService;
	
	/**
	 * @description : 권역 리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배송 권역 리스트 조회", description = "배송 권역 리스트 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsCenterDlvDistrictGroupResDto>> getDistrictGroupList(@RequestBody MsCenterDlvDistrictGroupReqDto dto){
		return ApiResult.createResult(msDeliveryDistrictGroupService.getDistrictGroupList(dto));
	}
	
	
	/**
	 * @description : 권역 그룹 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 저장", description = "배송 권역 그룹 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody List<MsCenterDlvDistrictGroupReqDto> dto){
		return ApiResult.createResult(msDeliveryDistrictGroupService.saveDistrictGroup(dto));
	}
	
	/**
	 * @description : 권역 그룹 X POP 저장 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 권역 POP 저장", description = "센터 권역 POP 저장")
	@PostMapping(value = "/v1.0/saveGroupXPopList")
	public ApiResult<String> saveGroupXPopList(@RequestBody MsDeliveryDistrictGroupXPopReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.saveGroupXPopList(dto));
	}
	
	/**
	 * @description : 센터 배송 권역 그룹 X POP 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "센터 배송 권역 그룹 X POP 조회", description = "센터 배송 권역 그룹 X POP 조회")
	@PostMapping(value = "/v1.0/getDistrictGroupXPop")
	public ApiResult<List<MsDeliveryDistrictGroupXPopResDto>> getDistrictGroupXPop(@RequestBody MsCenterDlvDistrictGroupPopReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.getDistrictGroupXPop(dto));
	}

	@Operation(summary = "센터 배송 권역 그룹 POP 조회", description = "센터 배송 권역 그룹 X POP 조회")
	@PostMapping(value = "/v1.0/getDlvDistrictgroupPopList")
	public ApiResult<List<MsDeliveryDistrictGroupXPopResDto>> getDlvDistrictgroupPopList(@RequestBody MsCenterDlvDistrictGroupReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.getDlvDistrictgroupPopList(dto));
	}

	@Operation(summary = "권역그룹 TODATE 하위 영향도 검증", description = "권역그룹 TODATE 단축 시 하위 권역그룹POP/권역 영향 여부 확인")
	@PostMapping(value = "/v1.0/getTodateChildImpact")
	public ApiResult<Map<String, String>> getTodateChildImpact(@RequestBody List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.getTodateChildImpact(dtoList));
	}

	@Operation(summary = "권역그룹 삭제 시 대표POP 존재 여부 검증", description = "권역그룹 삭제 시 하위 대표POP가 설정되어 있으면 삭제 불가 안내")
	@PostMapping(value = "/v1.0/getDeleteGroupPopImpact")
	public ApiResult<Map<String, String>> getDeleteGroupPopImpact(@RequestBody List<MsCenterDlvDistrictGroupReqDto> dtoList) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.getDeleteGroupPopImpact(dtoList));
	}

	@Operation(summary = "배송POP 삭제 시 행정동 사용 여부 검증", description = "권역그룹 하위에 행정동이 설정되어 있는 배송POP는 삭제 불가")
	@PostMapping(value = "/v1.0/getDeleteGroupXPopHjdongImpact")
	public ApiResult<Map<String, Object>> getDeleteGroupXPopHjdongImpact(@RequestBody MsDeliveryDistrictGroupXPopReqDto dto) {
		return ApiResult.createResult(msDeliveryDistrictGroupService.getDeleteGroupXPopHjdongImpact(dto));
	}

}
