package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyReqDto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab1Dto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab2Dto;
import cjfw.wms.kp.dto.KpWdRequestCancelqtyResTab3Dto;
import cjfw.wms.kp.service.KpWdRequestCancelqtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.08.07
 * @description : 결품대응현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "KpWdRequestCancelqty", description = "결품대응현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/wdRequestCancelqty")
public class KpWdRequestCancelqtyController {

	private final KpWdRequestCancelqtyService kpWdRequestCancelqtyService;

	/**
	 * @description : 결품대응현황 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "결품대응현황 목록 조회", description = "결품대응현황 목록 조회")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<KpWdRequestCancelqtyResTab1Dto>> getTab1MasterList(@RequestBody KpWdRequestCancelqtyReqDto dto) {


		return ApiResult.createResult(kpWdRequestCancelqtyService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : 분류피킹(출고센터) 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분류피킹(출고센터) 목록 조회", description = "분류피킹(출고센터) 목록 조회")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<KpWdRequestCancelqtyResTab2Dto>> getTab2MasterList(@RequestBody KpWdRequestCancelqtyReqDto dto) {
		
		
		return ApiResult.createResult(kpWdRequestCancelqtyService.getTab2MasterList(dto));
	}
	
	/**
	 * @description : 분류피킹(공급센터) 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "분류피킹(공급센터) 목록 조회", description = "분류피킹(공급센터) 목록 조회")
	@PostMapping(value = "/v1.0/getTab3MasterList")
	public ApiResult<List<KpWdRequestCancelqtyResTab3Dto>> getTab3MasterList(@RequestBody KpWdRequestCancelqtyReqDto dto) {
		
		
		return ApiResult.createResult(kpWdRequestCancelqtyService.getTab3MasterList(dto));
	}
	
	
	/**
	 * @throws Exception
	 * @description : 삭제
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "삭제", description = "삭제")
	@PostMapping(value = "/v1.0/delete")
	public ApiResult<String> delete(@RequestBody KpWdRequestCancelqtyReqDto dto) throws Exception {
		return ApiResult.createResult(kpWdRequestCancelqtyService.delete(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "저장", description = "저장")
	@PostMapping(value = "/v1.0/save")
	public ApiResult<String> save(@RequestBody KpWdRequestCancelqtyReqDto dto) throws Exception {
		return ApiResult.createResult(kpWdRequestCancelqtyService.save(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : tab2 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.18 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "tab2 저장", description = "tab2 저장")
	@PostMapping(value = "/v1.0/saveTab2")
	public ApiResult<String> saveTab2(@RequestBody KpWdRequestCancelqtyReqDto dto) throws Exception {
		return ApiResult.createResult(kpWdRequestCancelqtyService.saveTab2(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 대응불가
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "대응불가", description = "대응불가")
	@PostMapping(value = "/v1.0/statusCancel")
	public ApiResult<String> statusCancel(@RequestBody KpWdRequestCancelqtyReqDto dto) throws Exception {
		return ApiResult.createResult(kpWdRequestCancelqtyService.statusCancel(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : STO요청
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "STO요청", description = "STO요청")
	@PostMapping(value = "/v1.0/stoRequest")
	public ApiResult<String> stoRequest(@RequestBody KpWdRequestCancelqtyReqDto dto) throws Exception {
		return ApiResult.createResult(kpWdRequestCancelqtyService.stoRequest(dto));
	}


}