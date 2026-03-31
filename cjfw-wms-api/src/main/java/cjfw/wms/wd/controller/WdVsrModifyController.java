package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdVsrModifyReqDto;
import cjfw.wms.wd.dto.WdVsrModifyResDto;
import cjfw.wms.wd.service.WdVsrModifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.21
 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "출고 > 출고 > CS 출고 정정 요청 대응", description = "출고 > 출고 > CS 출고 정정 요청 대응을 조회, 저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/vsrModify")
public class WdVsrModifyController {
	
	private final WdVsrModifyService wdVsrModifyService;

	/**
	 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > CS 출고 정정 요청 대응 조회", description = "출고 > 출고 > CS 출고 정정 요청 대응 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdVsrModifyResDto>> getMasterList(@RequestBody WdVsrModifyReqDto dto) {
		return ApiResult.createResult(wdVsrModifyService.getMasterList(dto));
	}


	/**
	 * @description : 출고 > 출고 > CS 출고 정정 요청 대응 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.21 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > CS 출고 정정 요청 대응 저장", description = "출고 > 출고 > CS 출고 정정 요청 대응 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody WdVsrModifyReqDto dto) {
		return ApiResult.createResult(wdVsrModifyService.saveMasterList(dto));
	}
}
