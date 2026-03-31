package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveRPAllReqDto;
import cjfw.wms.st.dto.StLocMoveRPAllResDto;
import cjfw.wms.st.dto.StLocMoveRPAllResPrintDto;
import cjfw.wms.st.service.StLocMoveRPAllService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.09.28
 * @description : 출고재고보충(전센터) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "StLocMoveRPAll", description = "출고재고보충(전센터)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMoveRPAll")
public class StLocMoveRPAllController {

	private final StLocMoveRPAllService stLocMoveRPAllService;

	/**
	 * @description : 자동창고보충 이동대상 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고보충(전센터) 조회", description = "출고재고보충(전센터) 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StLocMoveRPAllResDto>> getMasterList(@RequestBody StLocMoveRPAllReqDto dto) {


		return ApiResult.createResult(stLocMoveRPAllService.getMasterList(dto));
	}
	
	/**
	 * @description : 출고재고보충(전센터) 출력 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고보충(전센터) 출력 조회", description = "출고재고보충(전센터) 출력 조회")
	@PostMapping(value = "/v1.0/getPrintList")
	public ApiResult<List<StLocMoveRPAllResPrintDto>> getPrintList(@RequestBody StLocMoveRPAllReqDto dto) {
		
		
		return ApiResult.createResult(stLocMoveRPAllService.getPrintList(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 출고재고보충(전센터) 버튼 기능 처리
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고보충(전센터) 버튼 기능 처리", description = "출고재고보충(전센터) 버튼 기능 처리")
	@PostMapping(value = "/v1.0/save")
	public ApiResult<String> save(@RequestBody StLocMoveRPAllReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPAllService.save(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 출고재고보충(전센터) 출력 처리
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.28 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고재고보충(전센터) 출력 처리", description = "출고재고보충(전센터) 출력 처리")
	@PostMapping(value = "/v1.0/savePrint")
	public ApiResult<List<StLocMoveRPAllResPrintDto>> savePrint(@RequestBody StLocMoveRPAllReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPAllService.savePrint(dto));
	}

}