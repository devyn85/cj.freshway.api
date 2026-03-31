package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cjfw.core.file.FileUpload;
import cjfw.core.model.ApiResult;
import cjfw.wms.ms.service.MsCenterDocService;
import cjfw.wms.tm.dto.tempMonitor.TmTempDocReqDto;
import cjfw.wms.tm.dto.tempMonitor.TmTempMonitorDescReqDto;
import cjfw.wms.tm.service.TmLocationMonitorPreviewPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27
 * @description : 온도 모니터링 기능을 구현한 Controller Class
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.27 System Generated 생성
 */

@Tag(name = "배차 > 온도기록지 미리보기 팝업", description = "온도기록지 미리보기 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tm/tmLocationMonitorPreviewPopup")
public class TmLocationMonitorPreviewPopupController {

    private final TmLocationMonitorPreviewPopupService service;
    private final MsCenterDocService centerDocService;

    /**
     * @description : 온도기록지 미리보기 팝업 요약 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    @Operation(summary = "온도기록지 미리보기 팝업", description = "온도기록지 미리보기 팝업 조회")
    @PostMapping(value = "/v1.0/getTempPreviewPopup")
    public ApiResult<Object> getTempPreviewPopup(@Valid @RequestBody TmTempMonitorDescReqDto reqDto) {
        return ApiResult.createResult(service.getTempPreviewPopup(reqDto));
    }

    @Operation(summary = "온도기록지 UPLOAD", description = "온도기록지 UPLOAD")
    @PostMapping(value = "/v1.0/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResult<String> upload(
            @RequestPart(value="files",required = false) List<MultipartFile> files,
            @RequestPart(value="fileInfoList",required = false) List<FileUpload> fileInfoList,
            @RequestPart(value = "docReqDto", required = false) TmTempDocReqDto docReqDto) {
        return ApiResult.createResult(service.saveUpload(files, fileInfoList, docReqDto));
    }
}
