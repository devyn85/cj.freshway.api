package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdPickingCancelDetailResDto;
import cjfw.wms.wd.dto.WdPickingCancelReqDto;
import cjfw.wms.wd.dto.WdPickingCancelResDto;
import cjfw.wms.wd.service.WdPickingCancelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.06.30
 * @description : 출고진행현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.30 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdPickingCancel", description = "피킹취소처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/pickingCancel")
public class WdPickingCancelController {

	private final WdPickingCancelService wdPickingCancelService;

	/**
	 * @description : 피킹취소처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹취소처리 목록 조회", description = "피킹취소처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdPickingCancelResDto>> getMasterList(@RequestBody WdPickingCancelReqDto wdPickingCancelReqDto) {


		return ApiResult.createResult(wdPickingCancelService.getMasterList(wdPickingCancelReqDto));
	}
	
	/**
	 * @description : 피킹취소처리 상세 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.06.10 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "피킹취소처리 상세 조회", description = "피킹취소처리 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<WdPickingCancelDetailResDto>> getDetailList(@RequestBody WdPickingCancelReqDto wdPickingCancelReqDto) {


		return ApiResult.createResult(wdPickingCancelService.getDetailList(wdPickingCancelReqDto));
	}
	

	/**
	 * @throws Exception
	 * @description : 지정취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "지정취소 저장", description = "지정취소 저장")
	@PostMapping(value = "/v1.0/savePicking")
	public ApiResult<String> savePicking(@RequestBody WdPickingCancelReqDto dto) throws Exception {
		return ApiResult.createResult(wdPickingCancelService.savePicking(dto));
	}
	
	
	/**
	 * @throws Exception
	 * @description : 일괄취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일괄취소 저장", description = "일괄취소 저장")
	@PostMapping(value = "/v1.0/saveBatch")
	public ApiResult<String> saveBatch(@RequestBody WdPickingCancelReqDto dto) throws Exception {
		return ApiResult.createResult(wdPickingCancelService.saveBatch(dto));
	}
	

}