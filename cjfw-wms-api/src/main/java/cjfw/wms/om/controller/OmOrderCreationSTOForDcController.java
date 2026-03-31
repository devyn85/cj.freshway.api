package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmOrderCreationSTOForDcReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForDcResDto;
import cjfw.wms.om.service.OmOrderCreationSTOForDcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.09.17
 * @description : 저장품센터간이체 Controller
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.17    YeoSeungCheol (pw6375@cj.net)   생성
 */
@Tag(name = "주문 > 주문등록 > 저장품센터간이체", description = "저장품센터간이체 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/ordercreationstofordc", "ltx/om/ordercreationstofordc"})
public class OmOrderCreationSTOForDcController {

    private final OmOrderCreationSTOForDcService omOrderCreationSTOForDcService;

    /**
	 * @description : 저장품센터간이체 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "저장품센터간이체 목록 조회", description = "저장품센터간이체 목록 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<OmOrderCreationSTOForDcResDto>> getMasterList(@RequestBody OmOrderCreationSTOForDcReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOForDcService.getMasterList(dto));
    }

    /**
	 * @description : 저장품센터간이체 처리결과 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "저장품센터간이체 처리결과 조회", description = "저장품센터간이체 처리결과 조회")
    @PostMapping(value = "/v1.0/getResultList")
    public ApiResult<List<OmOrderCreationSTOForDcResDto>> getResultList(@RequestBody OmOrderCreationSTOForDcReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOForDcService.getResultList(dto));
    }

    /**
	 * @throws Exception 
     * @description : 저장품센터간이체 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.17 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "저장품센터간이체 저장", description = "저장품센터간이체 저장")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List<OmOrderCreationSTOForDcResDto>> saveMasterList(@RequestBody OmOrderCreationSTOForDcReqDto dto) throws Exception {
        return ApiResult.createResult(omOrderCreationSTOForDcService.saveMasterList(dto));
    }
    
    /**
	 * @throws Exception 
     * @description : 엑셀업로드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.03 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
    @Operation(summary = "엑셀업로드 유효성검사", description = "엑셀업로드 유효성검사")
    @PostMapping(value = "/v1.0/getValidateExcelList")
    public ApiResult<List<OmOrderCreationSTOForDcResDto>> getValidateExcelList(@RequestBody OmOrderCreationSTOForDcReqDto dto) throws Exception {
        return ApiResult.createResult(omOrderCreationSTOForDcService.getValidateExcelList(dto));
    }
}
