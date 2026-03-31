package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsSkuDcLabelReqDto;
import cjfw.wms.ms.dto.MsSkuDcLabelResDto;
import cjfw.wms.ms.service.MsSkuDcLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.07.15 
 * @description : 센터상품라벨속성 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 센터상품라벨속성", description="센터상품라벨속성 조회 및 저장")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ms/skuDcLabel")
public class MsSkuDcLabelController {
    private final MsSkuDcLabelService msSkuDcLabelService;

    /**
	 * @description : 센터상품라벨속성 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "센터상품라벨속성 조회", description = "센터상품라벨속성 조회")
    @GetMapping("/v1.0/getMasterList")
    public ApiResult<List<MsSkuDcLabelResDto>> getMasterList(MsSkuDcLabelReqDto dto) {
    	return ApiResult.createResult(msSkuDcLabelService.getMasterList(dto));
    }

    /**
	 * @description : 센터상품라벨속성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.15 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "센터상품라벨속성 저장", description = "센터상품라벨속성 저장")
    @PostMapping("/v1.0/saveMasterList")
    public ApiResult<String> saveMasterList(@RequestBody List<MsSkuDcLabelReqDto> dto) {
    	return ApiResult.createResult(msSkuDcLabelService.saveMasterList(dto));
    }
}
