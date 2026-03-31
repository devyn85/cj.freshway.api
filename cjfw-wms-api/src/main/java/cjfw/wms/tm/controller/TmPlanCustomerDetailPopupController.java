package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cjfw.core.model.ApiResult;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupMemoReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupResDto;
import cjfw.wms.tm.service.TmPlanCustomerDetailPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.29 
 * @description : 거래처 상세팝업 Controller 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Tag(name = "배송 > 배차계획 > 거래처 상세 팝업", description = "거래서 상세 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/tm/customerDetailPopup")
public class TmPlanCustomerDetailPopupController {
  
	
	private final TmPlanCustomerDetailPopupService tmPlanCustomerDetailPopupService;
    
    /**
     * @description : 실착지기준 거래처 상세팝업 목록 조회 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    @GetMapping("/v1.0/getMasterList")
    @Operation(summary = "배차 실착지기준 거래처 상세팝업 목록 조회", description = "배차 실착지기준 거래처 상세팝업 목록 조회")
    public ApiResult<List<TmPlanCustomerDetailPopupResDto>> getMasterList(TmPlanCustomerDetailPopupReqDto dto) {
        return ApiResult.createResult(tmPlanCustomerDetailPopupService.getMasterList(dto));
    }

	/**
	 * @description : 거래처 상세팝업 일별 메모 업데이트 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
    @PostMapping("/v1.0/saveMemo")
    @Operation(summary = "거래처 상세팝업 메모 저장", description = "거래처 상세팝업 메모 저장")
    public ApiResult<String> saveMemo(@RequestBody TmPlanCustomerDetailPopupMemoReqDto dto) {
        return ApiResult.createResult(tmPlanCustomerDetailPopupService.saveMemo(dto));
    }

}
