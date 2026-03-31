package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDistrictPolygonReqDto;
import cjfw.wms.ms.dto.MsDistrictPolygonResDto;
import cjfw.wms.ms.service.MsDistrictPolygonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.08.19 
 * @description : 권역 폴리곤 조회 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */

@Tag(name = "권역 폴리곤", description = "권역 폴리곤 목록 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/districtPolygon")
public class MsDistrictPolygonController {
	
	private final MsDistrictPolygonService msDistrictPolygonService;
	
	/**
	 * @description : 권역 유형별 폴리곤 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "권역 유형별 폴리곤 목록 조회", description = "권역 타입별 폴리곤 목록 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsDistrictPolygonResDto>> getMasterList(@Valid MsDistrictPolygonReqDto dto) {
		return ApiResult.createResult(msDistrictPolygonService.getMasterList(dto));
	}

    /**
     * @description : 행정동 공간데이터 목록 조회(스트리밍)
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.14 LeeHyunsung (zoot0134@cj.net) 생성 </pre>
     */
	@Deprecated
    @Operation(summary = "행정동 공간데이터 목록 조회", description = "행정동 공간데이터 목록 조회")
    @PostMapping(value = "/v1.0/getStreamMasterList")
    public void getStreamMasterList(HttpServletResponse response, @RequestBody @Valid MsDistrictPolygonReqDto dto) {
        msDistrictPolygonService.getStreamMasterList(response, dto);
    }
    
    
    /**
     * @throws Exception 
     * @description : 권역 유형별 폴리곤 목록 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.19 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
	@Operation(summary = "권역 유형별 폴리곤 목록 조회", description = "권역 타입별 폴리곤 목록 조회 (스트리밍)")
	@PostMapping(value = "/v1.0/getMasterListNew")
	public void getMasterList(@RequestBody MsDistrictPolygonReqDto dto, HttpServletResponse response) {
		msDistrictPolygonService.getMasterList(dto, response);
	}

	@Operation(summary = "권역 유형별 폴리곤 파일 저장", description = "권역 타입별 폴리곤 목록 파일 저장")
	@GetMapping(value = "/v1.0/saveMasterListFile")
	public ApiResult<String> saveMasterListFile() {
		return ApiResult.createResult(msDistrictPolygonService.saveMasterListFile());
	}

	@Operation(summary = "권역 유형별 폴리곤 목록 파일에서 조회", description = "권역 타입별 폴리곤 목록 파일에서 조회")
    @PostMapping(value = "/v1.0/getMasterListOfFile")
    public void getMasterListOfFile(@RequestBody MsDistrictPolygonReqDto dto, HttpServletResponse response) {
        if (StringUtils.hasText(dto.getSearchCtp())) {
            msDistrictPolygonService.getHjdongMasterListOfFile(dto.getSearchCtp(), response);
        }
        else {
		    msDistrictPolygonService.getMasterListOfFile(dto.getDistrictType(), response);
        }
	}

}
