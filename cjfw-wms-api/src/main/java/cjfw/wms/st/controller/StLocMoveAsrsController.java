package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveAsrsReqDto;
import cjfw.wms.st.dto.StLocMoveAsrsTab1ResDto;
import cjfw.wms.st.dto.StLocMoveAsrsTab2ResDto;
import cjfw.wms.st.service.StLocMoveAsrsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.09.16
 * @description : 자동창고보충 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "StLocMoveAsrs", description = "자동창고보충")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMoveAsrs")
public class StLocMoveAsrsController {

	private final StLocMoveAsrsService stLocMoveAsrsService;

	/**
	 * @description : 자동창고보충 이동대상 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동창고보충 이동대상 조회", description = "자동창고보충 이동대상 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StLocMoveAsrsTab1ResDto>> getMasterList(@RequestBody StLocMoveAsrsReqDto dto) {


		return ApiResult.createResult(stLocMoveAsrsService.getMasterList(dto));
	}
	
	/**
	 * @description : 자동창고보충 이동결과 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동창고보충 이동결과 조회", description = "자동창고보충 이동결과 조회")
	@PostMapping(value = "/v1.0/getResultList")
	public ApiResult<List<StLocMoveAsrsTab2ResDto>> getResultList(@RequestBody StLocMoveAsrsReqDto dto) {
		
		
		return ApiResult.createResult(stLocMoveAsrsService.getResultList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 자동창고보충 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "자동창고보충 저장", description = "자동창고보충 저장")
	@PostMapping(value = "/v1.0/saveBatch")
	public ApiResult<List<StLocMoveAsrsTab2ResDto>> saveBatch(@RequestBody StLocMoveAsrsReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveAsrsService.saveBatch(dto));
	}
	
	

}