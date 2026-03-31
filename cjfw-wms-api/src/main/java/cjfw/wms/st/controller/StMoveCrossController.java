package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StMoveCrossReqDto;
import cjfw.wms.st.dto.StMoveCrossResDetailDto;
import cjfw.wms.st.dto.StMoveCrossResDto;
import cjfw.wms.st.service.StMoveCrossService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : CROSS자동보충 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StMoveCrossController API", description = "StMoveCrossController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/moveCross")
public class StMoveCrossController {
	private final StMoveCrossService stMoveCrossService;

	/**
	 * @description : CROSS자동보충 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "CROSS자동보충 조회 List", description = "CROSS자동보충 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StMoveCrossResDto>> getMasterList(@RequestBody StMoveCrossReqDto reqDto) {
		return ApiResult.createResult(stMoveCrossService.getMasterList(reqDto));
	}

	/**
	 * @description : CROSS자동보충 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "CROSS자동보충 상세 조회 List", description = "CROSS자동보충 상세 조회 List")
	@GetMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<StMoveCrossResDetailDto>> getDetailList(@Valid StMoveCrossReqDto reqDto) {
		return ApiResult.createResult(stMoveCrossService.getDetailList(reqDto));
	} 
	
	/** @throws Exception 
	 * @description :유통기한변경 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "CROSS자동보충 목록 저장", description = "유통기한변경 목록 저장")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<StMoveCrossResDto> saveMasterList(@RequestBody StMoveCrossReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stMoveCrossService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
}
