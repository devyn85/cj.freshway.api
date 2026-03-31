package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPopUploadLocationReqDto;
import cjfw.wms.ms.dto.MsPopUploadLocationResDto;
import cjfw.wms.ms.service.MsPopUploadLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net)
 * @date : 2025.09.02
 * @description : 마스터관리 > 기본정보관리 > 로케이션 일괄등록 Controller
 */
@Tag(name="마스터관리 > 기본정보관리 > 로케이션 일괄등록")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/popUploadLocation")
public class MsPopUploadLocationController {

    private final MsPopUploadLocationService msPopUploadLocationService;

    @Operation(summary = "로케이션 업로드 유효성검증", description = "엑셀 데이터를 임시테이블에 적재 후 DATACHECK를 수행합니다.")
    @PostMapping("/v1.0/validate")
    public ApiResult<List<MsPopUploadLocationResDto>> validate(@RequestBody MsPopUploadLocationReqDto dto) {
        return ApiResult.createResult(msPopUploadLocationService.validate(dto));
    }

    @Operation(summary = "로케이션 업로드 실행", description = "유효성검증 통과 데이터를 기준으로 DATAUPLOAD를 수행합니다.")
    @PostMapping("/v1.0/upload")
    public ApiResult<List<MsPopUploadLocationResDto>> upload(@RequestBody MsPopUploadLocationReqDto dto) {
        return ApiResult.createResult(msPopUploadLocationService.upload(dto));
    }
}

