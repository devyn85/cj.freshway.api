package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsTplUserReqDto;
import cjfw.wms.ms.dto.MsTplUserResDto;
import cjfw.wms.ms.service.MsTplUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : ParkYoSep (dytpq362@cj.net)   
 * @date        : 2025.10.24
 * @description : 정산 > 위탁물류 > 화주정보관리 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24      ParkYoSep (dytpq362@cj.net)       생성
 */
@Tag(name = "정산 > 위탁물류 > 화주정보관리", description = "화주정보 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/tplUser")
public class MsTplUserController {

    private final MsTplUserService msTplUserService;

    /**
     * 
     * @description : 화주 목록 조회
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.10.24      ParkYoSep (dytpq362@cj.net)  
     */
    @Operation(summary = "화주정보관리 조회", description = "화주정보 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<MsTplUserResDto>> getMasterList( @RequestBody MsTplUserReqDto dto) {
        return ApiResult.createResult(msTplUserService.getMasterList(dto));
    }

    /**
     * 
     * @description : 화주 목록 사용자 리스트 조회
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     *  2025.10.24      ParkYoSep (dytpq362@cj.net)  
     */
    @Operation(summary = "화주정보관리 사용자 조회", description = "화주정보 사용자 조회")
    @PostMapping(value = "/v1.0/getUserList")
    public ApiResult<List<MsTplUserResDto>> getUserlList( ) {
        return ApiResult.createResult(msTplUserService.getUserList());
    }
    /**
     * @description : 화주관리 목록 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.24      ParkYoSep (dytpq362@cj.net)  
     */
    @Operation(summary = "화주정보관리 저장", description = "화주정보 목록 저장")
    @PostMapping(value = "/v1.0/saveConfirm")
    public ApiResult<String> saveTplUser(@Valid @RequestBody MsTplUserReqDto dto) {
        return ApiResult.createResult(msTplUserService.saveConfirm(dto));
    }

}