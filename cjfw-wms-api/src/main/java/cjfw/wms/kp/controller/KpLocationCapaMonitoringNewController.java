package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpLocationCapaMonitoringNewReqDto;
import cjfw.wms.kp.dto.KpLocationCapaMonitoringNewResDto;
import cjfw.wms.kp.service.KpLocationCapaMonitoringNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.11.17
 * @description : 센터CAPA현황(NEW) Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.17        YeoSeungCheol (pw6375@cj.net)       생성
 */
@Tag(name = "지표/모니터링 > 재고운영지표 > 센터Capa 현황(New)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/locationCapaMonitoringNew")
public class KpLocationCapaMonitoringNewController {

    private final KpLocationCapaMonitoringNewService kpLocationCapaMonitoringNewService;

    /**
	 * @description : 로케이션 CAPA 현황 요약 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "로케이션 CAPA 현황 요약 조회", description = "로케이션 CAPA 현황 요약 조회")
    @PostMapping(value = "/v1.0/getMasterListTab1")
    public ApiResult<List<KpLocationCapaMonitoringNewResDto>> getMasterListTab1(@RequestBody KpLocationCapaMonitoringNewReqDto dto) {
        return ApiResult.createResult(kpLocationCapaMonitoringNewService.getMasterListTab1(dto));
    }

    /**
	 * @description : 로케이션 CAPA 현황 랙별 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "로케이션 CAPA 현황 랙별 상세 조회", description = "로케이션 CAPA 현황 랙별 상세 조회")
    @PostMapping(value = "/v1.0/getMasterListTab2")
    public ApiResult<List<KpLocationCapaMonitoringNewResDto>> getMasterListTab2(@RequestBody KpLocationCapaMonitoringNewReqDto dto) {
        return ApiResult.createResult(kpLocationCapaMonitoringNewService.getMasterListTab2(dto));
    }

    /**
	 * @description : 로케이션 CAPA 현황 유통기한 상태 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "로케이션 CAPA 현황 유통기한 상태 조회", description = "로케이션 CAPA 현황 유통기한 상태 조회")
    @PostMapping(value = "/v1.0/getDataStatusCount")
    public ApiResult<List<KpLocationCapaMonitoringNewResDto>> getDataStatusCount(@RequestBody KpLocationCapaMonitoringNewReqDto dto) {
        return ApiResult.createResult(kpLocationCapaMonitoringNewService.getDataStatusCount(dto));
    }

    /**
	 * @description : 랙별 상세탭 셀 내 유통기한 조회(하단 그리드)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "랙별 상세탭 셀 내 유통기한 조회(하단 그리드)", description = "랙별 상세탭 셀 내 유통기한 조회(하단 그리드)")
    @PostMapping(value = "/v1.0/getDataTotalCount")
    public ApiResult<KpLocationCapaMonitoringNewResDto> getDataTotalCount(@RequestBody KpLocationCapaMonitoringNewReqDto dto) {
        return ApiResult.createResult(kpLocationCapaMonitoringNewService.getDataTotalCount(dto));
    }
    
    /**
	 * @description : 로케이션 CAPA 현황 - 렉별 상세탭 CAPA 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.28 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "렉별 상세탭 CAPA 상세 조회", description = "렉별 상세탭 CAPA 상세 조회")
    @PostMapping(value = "/v1.0/getDetailRead")
    public ApiResult<List<KpLocationCapaMonitoringNewResDto>> getDetailRead(@RequestBody KpLocationCapaMonitoringNewReqDto dto) {
        return ApiResult.createResult(kpLocationCapaMonitoringNewService.getDetailRead(dto));
    }
}
