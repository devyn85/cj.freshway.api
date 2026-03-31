package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmOrderCreationSTOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOResDto;
import cjfw.wms.om.service.OmOrderCreationSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author      : YeoSeungCheol (pw6375@cj.net)
 * @date        : 2025.09.26
 * @description : 물류센터간 이체 Controller
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.26    YeoSeungCheol (pw6375@cj.net)	생성
 */
@Tag(name = "주문 > 주문등록 > 물류센터간 이체", description = "물류센터간 이체")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/ordercreationsto", "ltx/om/ordercreationsto"})
public class OmOrderCreationSTOController {

    private final OmOrderCreationSTOService omOrderCreationSTOService;

    /**
	 * @description : 물류센터간 이체 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "물류센터간 이체 목록 조회", description = "이동대상 목록 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<OmOrderCreationSTOResDto>> getMasterList(@RequestBody OmOrderCreationSTOReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOService.getMasterList(dto));
    }

    /**
	 * @description : 물류센터간 이체 결과 조회 (임시테이블)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "물류센터간 이체 결과 조회", description = "임시테이블 결과")
    @PostMapping(value = "/v1.0/getResultList")
    public ApiResult<List<OmOrderCreationSTOResDto>> getResultList(@RequestBody OmOrderCreationSTOReqDto dto) {
        return ApiResult.createResult(omOrderCreationSTOService.getResultList(dto));
    }

    /**
	 * @throws Exception 
     * @description : 물류센터간 이체 저장 (임시테이블 적재 후 패키지 호출)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
	 */
    @Operation(summary = "물류센터간 이체 저장", description = "임시테이블 적재 후 패키지 호출")
    @PostMapping(value = "/v1.0/saveMasterList")
    public ApiResult<List<OmOrderCreationSTOResDto>> saveMasterList(@RequestBody OmOrderCreationSTOReqDto dto) throws Exception {
        return ApiResult.createResult(omOrderCreationSTOService.saveMasterList(dto));
    }
}
