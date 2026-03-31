package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StLocMoveRPReqDto;
import cjfw.wms.st.dto.StLocMoveRPResPrintDto;
import cjfw.wms.st.dto.StLocMoveRPResTab1Dto;
import cjfw.wms.st.dto.StLocMoveRPResTab2Dto;
import cjfw.wms.st.service.StLocMoveRPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.16
 * @description : 출고재고보충(수원3층) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "StLocMoveRP", description = "출고재고보충(수원3층)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/locMoveRP")
public class StLocMoveRPController {

	private final StLocMoveRPService stLocMoveRPService;

	/**
	 * @description : 보충생성 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "보충생성 목록 조회", description = "보충생성 목록 조회")
	@GetMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StLocMoveRPResTab1Dto>> getTab1MasterList(@Valid StLocMoveRPReqDto dto) {


		return ApiResult.createResult(stLocMoveRPService.getTab1MasterList(dto));
	}
	
	/**
	 * @description : ASRS결과 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "ASRS결과 목록 조회", description = "ASRS결과 목록 조회")
	@GetMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StLocMoveRPResTab2Dto>> getTab2MasterList(@Valid StLocMoveRPReqDto dto) {
		
		
		return ApiResult.createResult(stLocMoveRPService.getTab2MasterList(dto));
	}
	

	/**
	 * @throws Exception
	 * @description : 보충생성 호출
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "보충생성 호출", description = "보충생성 호출")
	@PostMapping(value = "/v1.0/saveCreate")
	public ApiResult<String> saveCreate(@RequestBody StLocMoveRPReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPService.saveCreate(dto));
	}
	
	
	/**
	 * @throws Exception
	 * @description : ASRS지시
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "ASRS지시", description = "ASRS지시")
	@PostMapping(value = "/v1.0/saveDiraction")
	public ApiResult<String> saveDiraction(@RequestBody StLocMoveRPReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPService.saveDiraction(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 일반보충이동
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "일반보충이동", description = "일반보충이동")
	@PostMapping(value = "/v1.0/saveMove")
	public ApiResult<String> saveMove(@RequestBody StLocMoveRPReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPService.saveMove(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 리스트 조회
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.16 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "리스트 조회", description = "리스트 조회")
	@PostMapping(value = "/v1.0/getPrint")
	public ApiResult<List<StLocMoveRPResPrintDto>> getPrint(@RequestBody StLocMoveRPReqDto dto) throws Exception {
		return ApiResult.createResult(stLocMoveRPService.getPrint(dto));
	}
	
	

}