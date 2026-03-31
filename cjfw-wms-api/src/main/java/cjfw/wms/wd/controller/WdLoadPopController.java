package cjfw.wms.wd.controller;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdLoadPopReqDto;
import cjfw.wms.wd.service.WdLoadPopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "WdLoadController", description = "상차지시 설정 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/loadPop")
public class WdLoadPopController {
    private final WdLoadPopService wdLoadPopService;

    @Operation(summary = "상차지시 설정 팝업", description = "상차지시 설정 조회")
    @PostMapping(value = "/v1.0/getLoadDirectionsStatus")
    public ApiResult<WdLoadPopReqDto> getLoadDirectionsStatus(@Valid @RequestBody WdLoadPopReqDto dto) {
        return ApiResult.createResult(wdLoadPopService.getLoadDirectionsStatus(dto));
    }

    @Operation(summary = "상차지시 설정 팝업", description = "상차지시 설정 적재")
    @PostMapping(value = "/v1.0/updateLoadDirectionsStatus")
    public ApiResult<String> updateLoadDirectionsStatus(@Valid @RequestBody WdLoadPopReqDto dto) {
        return ApiResult.createResult(wdLoadPopService.updateLoadDirectionsStatus(dto));
    }
}
