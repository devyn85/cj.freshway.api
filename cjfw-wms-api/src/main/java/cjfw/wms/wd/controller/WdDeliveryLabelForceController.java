package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdDeliveryLabelForceReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceResT1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceResT2Dto;
import cjfw.wms.wd.service.WdDeliveryLabelForceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.17
 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)을 조회, 엑셀업로드, STO등록, 인쇄한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/deliveryLabelForce")
public class WdDeliveryLabelForceController {
	
	private final WdDeliveryLabelForceService wdDeliveryLabelForceService;

	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 조회", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_분류표출력_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<WdDeliveryLabelForceResT1Dto>> getMasterT1List(@RequestBody WdDeliveryLabelForceReqDto dto) {
		return ApiResult.createResult(wdDeliveryLabelForceService.getMasterT1List(dto));
	}
	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 인쇄시 저장", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 인쇄시 저장")
	@PostMapping(value = "/v1.0/savePrintList")
	public ApiResult<String> savePrintList(@RequestBody WdDeliveryLabelForceReqDto dto) throws Exception {
	    return ApiResult.createResult(wdDeliveryLabelForceService.savePrintList(dto));
	}
	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 조회", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<WdDeliveryLabelForceResT2Dto>> getMasterT2List(@RequestBody WdDeliveryLabelForceReqDto dto) {
		return ApiResult.createResult(wdDeliveryLabelForceService.getMasterT2List(dto));
	}
	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장 Method
	 * @issues :      ->택배송장발행(온라인)에서 운송장출력 관련 service를 사용중에 있음
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody WdDeliveryLabelForceReqDto dto) throws Exception {
	    return ApiResult.createResult(wdDeliveryLabelForceService.saveMasterList(dto));
	}
	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 조회", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 조회")
	@PostMapping(value = "/v1.0/getPopMasterList")
	public ApiResult<List<WdDeliveryLabelForceResT1Dto>> getPopMasterList(@RequestBody WdDeliveryLabelForceReqDto dto) {
		return ApiResult.createResult(wdDeliveryLabelForceService.getPopMasterList(dto));
	}
	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 저장", description = "출고 > 출고 > 배송 라벨 출력(예외 기준 적용) STO 등록 팝업 저장")
	@PostMapping(value = "/v1.0/savePopMasterList")
	public ApiResult<Object> savePopMasterList(@RequestBody WdDeliveryLabelForceReqDto dto) {
		return ApiResult.createResult(wdDeliveryLabelForceService.savePopMasterList(dto));
	}
	
	

}
