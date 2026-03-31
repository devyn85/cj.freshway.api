package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StMKitReqDto;
import cjfw.wms.st.dto.StMKitResT1Dto;
import cjfw.wms.st.service.StMKitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 고혜미 (laksjd0606@cj.net) 생성
 * @date : 2025.11.05
 * @description : KIT처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고조정 > 키트처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stMKit")
public class StMKitController {
	private final StMKitService stMKitService;

	/**
	 * @description : KIT처리 목록 조회[이체대상TAB]
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KIT처리 목록 조회[이체대상TAB]", description = "KIT처리 목록 조회[이체대상TAB]")
	@PostMapping(value = "/v1.0/getMasterList01")
	public ApiResult<List<StMKitResT1Dto>> getMasterList01(@RequestBody StMKitReqDto dto) {
		return ApiResult.createResult(stMKitService.getMasterList01(dto));
	}
	
	   /**
     * @description : KIT처리 목록 조회[이체대상TAB]
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
     */
    @Operation(summary = "KIT처리 생산저장 결과 조회[이체대상TAB]", description = "KIT처리 생산저장 결과 조회[이체대상TAB]")
    @PostMapping(value = "/v1.0/getMasterResultList01")
    public ApiResult<List<StMKitResT1Dto>> getMasterResultList01(@RequestBody StMKitReqDto dto) {
        return ApiResult.createResult(stMKitService.getMasterResultList01(dto));
    }
	
	/**
     * @description : KIT 목록 조회[이체대상TAB]
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.30 KimSunHo(sunhokim6229@cj.net) 생성 </pre>
     */
    @Operation(summary = "KIT 목록 조회[이체대상TAB]", description = "KIT처리 목록 조회[이체대상TAB]")
    @PostMapping(value = "/v1.0/getKitList01")
    public ApiResult<List<StMKitResT1Dto>> getKitList01(@RequestBody StMKitReqDto dto) {
        return ApiResult.createResult(stMKitService.getKitList01(dto));
    }
    
    /**
     * @description : 전자결재 목록 조회[전자결재TAB]
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
     */
    @Operation(summary = "전자결재 목록 조회[전자결재TAB]", description = "전자결재 목록 조회[전자결재TAB]")
    @PostMapping(value = "/v1.0/getMasterList02")
    public ApiResult<List<StMKitResT1Dto>> getMasterList02(@RequestBody StMKitReqDto dto) {
        return ApiResult.createResult(stMKitService.getMasterList02(dto));
    }
    
    /**
     * @description : 처리 목록 조회[처리TAB]
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
     */
    @Operation(summary = "처리 목록 조회[처리TAB]", description = "처리 목록 조회[처리TAB]")
    @PostMapping(value = "/v1.0/getMasterList03")
    public ApiResult<List<StMKitResT1Dto>> getMasterList03(@RequestBody StMKitReqDto dto) {
        return ApiResult.createResult(stMKitService.getMasterList03(dto));
    }
	
	/**
	 * @description :리포트 대상 조회[이체대상TAB]
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 */
	@Operation(summary = "리포트 대상 조회[이체대상TAB]", description = "리포트 대상 조회[이체대상TAB]")
	@PostMapping(value = "/v1.0/getReportList")
	public ApiResult<List<StMKitResT1Dto>> getReportList(@RequestBody StMKitReqDto dto) {
		System.out.println("dto===="+dto);
		return ApiResult.createResult(stMKitService.getReportList(dto));
	}
	
	/**
     * @description : 이체대상 저장 List Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.07 고혜미 (laksjd0606@cj.net) 생성 </pre>
     * </pre>
     */
    @Operation(summary = "생산대상 저장 List", description = "생산대상 저장 List")
    @PostMapping(value = "/v1.0/saveSubItemsList01")
    public ApiResult<String> saveSubItemsList01(@RequestBody StMKitReqDto reqDto) throws Exception {
        return ApiResult.createResult(stMKitService.saveSubItemsList01(reqDto));
    }
    
    /**
     * @description : 해제대상 저장 List Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.14 공두경  생성 </pre>
     * </pre>
     */
    @Operation(summary = "해제대상 저장 List", description = "해제대상 저장 List")
    @PostMapping(value = "/v1.0/saveSubItemsList02")
    public ApiResult<String> saveSubItemsList02(@RequestBody StMKitReqDto reqDto) throws Exception {
    	return ApiResult.createResult(stMKitService.saveSubItemsList02(reqDto));
    }
	
    
    /**
     * @description : 이체대상 저장 List Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.07 고혜미 (laksjd0606@cj.net) 생성 </pre>
     * </pre>
     */
    @Operation(summary = "처리대상 저장 List", description = "처리대상 저장 List")
    @PostMapping(value = "/v1.0/saveSubItemsList03")
    public ApiResult<String> saveSubItemsList03(@RequestBody StMKitReqDto reqDto) throws Exception {
        return ApiResult.createResult(stMKitService.saveSubItemsList03(reqDto));
    }
    
    
	/**
	 * @description : 이체대상 저장 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.07 고혜미 (laksjd0606@cj.net) 생성 </pre>
	 * </pre>
	 */
	@Operation(summary = "이체대상 저장 List", description = "이체대상 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList01")
	public ApiResult<String> saveMasterList01(@RequestBody StMKitReqDto reqDto) throws Exception {
	    return ApiResult.createResult(stMKitService.saveMasterList01(reqDto));
	}
	
	/**
	 * @description : 해체대상 저장 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 공두경 생성 </pre>
	 * </pre>
	 */
	@Operation(summary = "해체대상 저장 List", description = "해체대상 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList04")
	public ApiResult<String> saveMasterList04(@RequestBody StMKitReqDto reqDto) throws Exception {
		return ApiResult.createResult(stMKitService.saveMasterList04(reqDto));
	}
	
	/**
     * @description : 전자결재 Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.07 고혜미 (laksjd0606@cj.net) 생성 </pre>
     * </pre>
     */
    @Operation(summary = "전자결재", description = "전자결재")
    @PostMapping(value = "/v1.0/saveMasterList02")
    public ApiResult<String> saveMasterList02(@RequestBody StMKitReqDto reqDto) throws Exception {
        return ApiResult.createResult(stMKitService.saveMasterList02(reqDto));
    }
    /**
     * @description : 전자결재탭 저장 Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2026.01.21 공두경  생성 </pre>
     * </pre>
     */
    @Operation(summary = "전자결재탭 저장", description = "전자결재탭 저장")
    @PostMapping(value = "/v1.0/saveMasterList05")
    public ApiResult<String> saveMasterList05(@RequestBody StMKitReqDto reqDto) throws Exception {
    	return ApiResult.createResult(stMKitService.saveMasterList05(reqDto));
    }
    
    /**
     * @description : 처리저장 Method
     * @issues :
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.11.07 고혜미 (laksjd0606@cj.net) 생성 </pre>
     * </pre>
     */
    @Operation(summary = "처리저장", description = "처리저장")
    @PostMapping(value = "/v1.0/saveMasterList03")
    public ApiResult<String> saveMasterList03(@RequestBody StMKitReqDto reqDto) throws Exception {
        return ApiResult.createResult(stMKitService.saveMasterList03(reqDto));
    }
	
}