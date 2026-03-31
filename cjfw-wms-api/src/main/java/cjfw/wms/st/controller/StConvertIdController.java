package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertIdReqDto;
import cjfw.wms.st.dto.StConvertIdResDto;
import cjfw.wms.st.service.StConvertIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : PLT-ID변경 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StConvertIdController API", description = "StConvertIdController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertId")
public class StConvertIdController {
	private final StConvertIdService stConvertIdService;

	/**
	 * @description : PLT-ID변경 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "PLT-ID변경 조회 List", description = "PLT-ID변경 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StConvertIdResDto>> getMasterList(@RequestBody StConvertIdReqDto reqDto) {
		return ApiResult.createResult(stConvertIdService.getMasterList(reqDto));
	}

	/**
	 * @description : PLT-ID변경 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
//	@Operation(summary = "PLT-ID변경 상세 조회 List", description = "PLT-ID변경 상세 조회 List")
//	@GetMapping(value = "/v1.0/getDetailList")
//	public ApiResult<List<StConvertIdResDetailDto>> getDetailList(@Valid StConvertIdReqDto reqDto) {
//		return ApiResult.createResult(stConvertIdService.getDetailList(reqDto));
//	}
	
	/** @throws Exception 
	 * @description :유통기한변경 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "PLT-ID변경 목록 저장", description = "유통기한변경 목록 저장")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StConvertIdReqDto reqDto)  {
		return ApiResult.createResult(stConvertIdService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
}
