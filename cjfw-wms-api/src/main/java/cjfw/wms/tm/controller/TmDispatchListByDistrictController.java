package cjfw.wms.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.tm.dto.TmDispatchListByDistrictReqDto;
import cjfw.wms.tm.dto.TmDispatchListByDistrictResDto;
import cjfw.wms.tm.service.TmDispatchListByDistrictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : OhEunbeom
 * @date : 2025.10.10
 * @description : 배차목록(권역별) 조회 컨트롤러
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.10 OhEunbeom      생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("api/tm/dispatchListByDistrict")
@Tag(name = "배차목록(권역별) 조회", description = "배차목록(권역별) 조회 API")
public class TmDispatchListByDistrictController {

    @Autowired
    private TmDispatchListByDistrictService tmDispatchListByDistrictService;

    /**
     * 배차목록(권역별) 조회
     * @param reqDto 요청 DTO
     * @return 배차목록(권역별) 조회 결과
     * @author System
     * @date 2025.01.15
     */
    @PostMapping("/v1.0/getDispatchListByDistrict")
    @Operation(summary = "배차목록(권역별) 조회", description = "배차목록을 권역별로 조회합니다.")
    public ApiResult<Page<TmDispatchListByDistrictResDto>> getDispatchListByDistrict(@RequestBody TmDispatchListByDistrictReqDto reqDto) {
        log.info("배차목록(권역별) 조회 요청: {}", reqDto);

        Page<TmDispatchListByDistrictResDto> result = tmDispatchListByDistrictService.getDispatchListByDistrict(reqDto);
        log.info("배차목록(권역별) 조회 결과: {} 건", result.getList().size());

        return ApiResult.createResult(result);
    }

}
