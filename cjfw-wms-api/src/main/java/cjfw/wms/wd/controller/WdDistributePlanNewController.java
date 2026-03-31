package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDistributePlanNewReqDto;
import cjfw.wms.wd.dto.WdDistributePlanNewResDto;
import cjfw.wms.wd.service.WdDistributePlanNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2025.11.06
 * @description : 출고현황 - 미출예정확인(New) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "출고 > 출고현황 > 미출예정확인(New)")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/wd/distributePlanNew")
public class WdDistributePlanNewController {
    private final WdDistributePlanNewService wdDistributePlanNewService;

    /**
	 * @description : 미출예정확인(New) - 미출예정 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "미출예정확인(New) - 미출예정 조회", description = "미출예정확인(New) - 미출예정 조회")
    @PostMapping(value = "/v1.0/getMasterListTab1")
    public ApiResult<List<WdDistributePlanNewResDto>> getMasterListTab1(@RequestBody WdDistributePlanNewReqDto dto) {
        return ApiResult.createResult(wdDistributePlanNewService.getMasterListTab1(dto));
    }

    /**
	 * @description : 미출예정확인(New) - 상품별 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "미출예정확인(New) - 상품별 조회", description = "미출예정확인(New) - 상품별 조회")
    @PostMapping(value = "/v1.0/getMasterListTab2")
    public ApiResult<List<WdDistributePlanNewResDto>> getMasterListTab2(@RequestBody WdDistributePlanNewReqDto dto) {
        return ApiResult.createResult(wdDistributePlanNewService.getMasterListTab2(dto));
    }

    /**
	 * @description : 미출예정확인(New) - 미출예정 조회 (양산(2), 수도권(3), 장성(4), 제주(5))
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.06 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "미출예정확인(New) - 미출예정 조회", description = "미출예정확인(New) - 미출예정 조회")
    @PostMapping(value = "/v1.0/getMasterListTab1WithCondition")
    public ApiResult<List<WdDistributePlanNewResDto>> getMasterListTab1WithCondition(@RequestBody WdDistributePlanNewReqDto dto) {
        return ApiResult.createResult(wdDistributePlanNewService.getMasterListTab1WithCondition(dto));
    }
}
