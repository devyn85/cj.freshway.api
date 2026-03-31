package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmTrasportRoutingReqDto;
import cjfw.wms.tm.dto.TmTrasportRoutingResDto;
import cjfw.wms.tm.service.TmTrasportRoutingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkYoSep 
 * @date : 2025.10.15  
 * @description : 수송경로관리 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.15 ParkYoSep 생성
 */
@Tag(name = "정산 > 운송비정산 > 수송경로관리", description = "수송경로관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/trasportRouting")
public class TmTrasportRoutingController {

    
    private final TmTrasportRoutingService tmTrasportRoutingService;


    /**
     * @description : 수송경로관리 노선 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.15 ParkYoSep 생성
     */
    @Operation(summary = "수송경로조회", description = "수송경로조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<TmTrasportRoutingResDto>> getMasterList(@Valid @RequestBody TmTrasportRoutingReqDto reqDto) {
        return ApiResult.createResult(tmTrasportRoutingService.getMasterList(reqDto));
    }
    
    /**
     * @description : 수송경로관리 운영단가 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.31 ParkYoSep 생성
     */
    @Operation(summary = "수송운영단가조회", description = "수송운영단가조회")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List<TmTrasportRoutingResDto>> getDetailList(@Valid @RequestBody TmTrasportRoutingReqDto reqDto) {
        return ApiResult.createResult(tmTrasportRoutingService.getDetailList(reqDto));
    }
    
    /**
     * @description : 수송경로관리 도착센터조회 조회 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.29 ParkYoSep 생성
     */
    @Operation(summary = "도착센터조회", description = "도착센터조회")
    @PostMapping(value = "/v1.0/getToCenterList")
    public ApiResult<List<TmTrasportRoutingResDto>> getToCenterList() {
        return ApiResult.createResult(tmTrasportRoutingService.getToCenterList());
    }
    
    /**
     * @description : 수송경로관리 노선 저장 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.15 ParkYoSep 생성
     */
    @Operation(summary = "수송경로저장", description = "수송경로저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@Valid @RequestBody TmTrasportRoutingReqDto reqDto) {
        return ApiResult.createResult(tmTrasportRoutingService.saveMasterList(reqDto));
    }
    /**
     * @description : 수송경로관리 운용단가 저장 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.03 ParkYoSep 생성
     */
    @Operation(summary = "수송경로저장", description = "수송경로저장")
    @PostMapping(value = "/v1.0/saveDetailList")
    public ApiResult<String> saveDetailList(@Valid @RequestBody TmTrasportRoutingReqDto reqDto) {
        return ApiResult.createResult(tmTrasportRoutingService.saveDetailList(reqDto));
    }

    /**
     * @description : 수송경로관리 엑셀정합성 검사 기능을 구현한 Method 
     * @issues : 
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.20 ParkYoSep 생성
     */
    @Operation(summary = "수송경로관리 엑셀검증", description = "수송경로관리 엑셀검증")
    @PostMapping(value = "/v1.0/validateExcel")
    public ApiResult<List<TmTrasportRoutingResDto>> validateExcel(@Valid @RequestBody TmTrasportRoutingReqDto reqDto) {
        return ApiResult.createResult(tmTrasportRoutingService.validateExcel(reqDto));
    }

}