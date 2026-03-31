package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StAdjustmentReqDto;
import cjfw.wms.st.dto.StAdjustmentResDto;
import cjfw.wms.st.dto.StAdjustmentResSetDto;
import cjfw.wms.st.service.StAdjustmentService;
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
 * @description : 센터자체감모 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StAdjustmentController API", description = "StAdjustmentController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/adjustment")
public class StAdjustmentController {
	private final StAdjustmentService stAdjustmentService;

	/**
	 * @description : 센터자체감모 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터자체감모 조회 List", description = "센터자체감모 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StAdjustmentResDto>> getTab1MasterList(@Valid @RequestBody StAdjustmentReqDto reqDto) {
		return ApiResult.createResult(stAdjustmentService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 센터자체감모 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터자체감모 상세 조회 List", description = "센터자체감모 상세 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StAdjustmentResSetDto>> getTab2MasterList(@Valid @RequestBody StAdjustmentReqDto reqDto) {
		return ApiResult.createResult(stAdjustmentService.getTab2MasterList(reqDto));
	}
	
	/**
	 * @description : 재고일괄이동 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터자체감모 상세 저장 List", description = "센터자체감모 상세 저장 List")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StAdjustmentResDto> saveMasterList(@RequestBody StAdjustmentReqDto reqDto) throws Exception{
		return ApiResult.createResult(stAdjustmentService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	
	/**
	 * @description : 재고일괄이동 상세 조회 List Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터자체감모 ZERO 재고생성", description = "센터자체감모 ZERO 재고생성")
	@PostMapping(value = "/v1.0/saveZeroStock")
	public ApiResult<StAdjustmentResDto> saveZeroStock(@RequestBody StAdjustmentReqDto reqDto) throws Exception{
		return ApiResult.createResult(stAdjustmentService.saveZeroStock(reqDto)); 
	}		
	
	/**
	 * @description : 센터자체감모 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "센터자체감모 창고 조회 List", description = "센터자체감모  조회 List")
	@GetMapping(value = "/v1.0/getOrganizeList")
	public ApiResult<List<StAdjustmentResDto>> getOrganizeList(StAdjustmentReqDto reqDto) {
		return ApiResult.createResult(stAdjustmentService.getOrganizeList(reqDto));
	}	
}
