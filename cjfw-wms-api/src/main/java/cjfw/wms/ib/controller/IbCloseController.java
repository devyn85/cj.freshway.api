package cjfw.wms.ib.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbCloseReqDto;
import cjfw.wms.ib.dto.IbCloseResDto;
import cjfw.wms.ib.service.IbCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.08.21
 * @description : Admin > 모니터링 > 마감상태 관리 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "Admin > 모니터링 > 마감상태 관리", description = "Admin > 모니터링 > 마감상태 관리를 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/close")
public class IbCloseController {
	private final IbCloseService ibCloseService;

	/**
	 * @description : Admin > 모니터링 > 마감상태 관리 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "Admin > 모니터링 > 마감상태 관리 조회", description = "Admin > 모니터링 > 마감상태 관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<IbCloseResDto>> getMasterList(@RequestBody IbCloseReqDto dto) {
		return ApiResult.createResult(ibCloseService.getMasterList(dto));
	}	


	/**
	 * @description : Admin > 모니터링 > 마감상태 관리 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.21 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "Admin > 모니터링 > 마감상태 관리 저장", description = "Admin > 모니터링 > 마감상태 관리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<IbCloseResDto> saveMasterList(@RequestBody IbCloseReqDto dto) throws Exception {
	    return ApiResult.createResult(ibCloseService.saveMasterList(dto));
	}
	
}
