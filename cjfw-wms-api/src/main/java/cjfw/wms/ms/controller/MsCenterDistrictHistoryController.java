package cjfw.wms.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsCenterDistrictHistoryReqDto;
import cjfw.wms.ms.dto.MsCenterDistrictHistoryResDto;
import cjfw.wms.ms.service.MsCenterDistrictHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @description :  기준정보 > 센터기준정보 > 센터 권역 이력
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 센터기준정보 > 센터 권역 이력", description = "센터 권역 변경이력 조회 및 저장")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/ms/centerDistrictHistory")
public class MsCenterDistrictHistoryController {
	
	private final MsCenterDistrictHistoryService msCenterDistrictHistoryService;
	
	@Operation(summary = "센터 권역 이력 조회", description = "센터 권역 이력 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsCenterDistrictHistoryResDto>> getMasterList(MsCenterDistrictHistoryReqDto dto, Page<?> page) {
		return ApiResult.createResult(msCenterDistrictHistoryService.getMasterList(dto, page));
	}
	
}
