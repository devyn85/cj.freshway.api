package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpCloseReqDto;
import cjfw.wms.kp.dto.KpCloseResDetailDto;
import cjfw.wms.kp.dto.KpCloseResDto;
import cjfw.wms.kp.service.KpCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.22
 * @description : 모니터링 > 물동 > 물동마감 진행 현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "모니터링 > 물동 > 물동마감 진행 현황", description = "모니터링 > 물동 > 물동마감 진행 현황을 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/kp/close", "ltx/kp/close"})
public class KpCloseController {
	private final KpCloseService kpCloseService;

	/**
	 * @description : 모니터링 > 물동 > 물동마감 진행 현황 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "모니터링 > 물동 > 물동마감 진행 현황 조회", description = "모니터링 > 물동 > 물동마감 진행 현황 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<KpCloseResDto>> getMasterList(@RequestBody KpCloseReqDto dto) {
		return ApiResult.createResult(kpCloseService.getMasterList(dto));
	}	
	
	/**
	 * @description : 모니터링 > 물동 > 물동마감 진행 현황 상세 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.22 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "모니터링 > 물동 > 물동마감 진행 현황 상세 조회", description = "모니터링 > 물동 > 물동마감 진행 현황 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<KpCloseResDetailDto>> getDetailList(@RequestBody KpCloseReqDto dto) {
		return ApiResult.createResult(kpCloseService.getDetailList(dto));
	}	
	
	/**
	 * @description : 모니터링 > 물동 > 물동마감 진행 현황 저장(월마감처리) Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.29 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "모니터링 > 물동 > 물동마감 진행 현황 저장(월마감처리)", description = "모니터링 > 물동 > 물동마감 진행 현황 저장(월마감처리)")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody KpCloseReqDto dto) throws Exception {
	    return ApiResult.createResult(kpCloseService.saveMasterList(dto));
	}	

}
