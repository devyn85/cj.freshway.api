package cjfw.wms.mg.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.mg.dto.MgModifyLogReqDto;
import cjfw.wms.mg.dto.MgModifyLogResDto;
import cjfw.wms.mg.dto.MgModifyLogResSkuDto;
import cjfw.wms.mg.service.MgModifyLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고변경사유현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "MgModifyLogController API", description = "MgModifyLogController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/mg/mgModifyLog")
public class MgModifyLogController {
	private final MgModifyLogService mgModifyLogService;

	/**
	 * @description : 재고변경사유현황 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고변경사유현황 조회 List", description = "재고변경사유현황 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<MgModifyLogResDto>> getTab1MasterList(@RequestBody MgModifyLogReqDto reqDto) {
		return ApiResult.createResult(mgModifyLogService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 재고변경사유현황 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고변경사유현황 상세 조회 List", description = "재고변경사유현황 상세 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<MgModifyLogResSkuDto>> getTab2MasterList(@RequestBody MgModifyLogReqDto reqDto) {
		return ApiResult.createResult(mgModifyLogService.getTab2MasterList(reqDto));
	}
}
