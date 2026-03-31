package cjfw.wms.wd.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.conn.jco.JCoException;

import cjfw.core.model.ApiResult;
import cjfw.wms.wd.dto.WdShipmentReqDto;
import cjfw.wms.wd.dto.WdShipmentResBillYnDto;
import cjfw.wms.wd.dto.WdShipmentResDto;
import cjfw.wms.wd.dto.WdShipmentTab1DetailResDto;
import cjfw.wms.wd.dto.WdShipmentTab2DetailResDto;
import cjfw.wms.wd.dto.WdShipmentTab3DetailResDto;
import cjfw.wms.wd.service.WdShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.07.22
 * @description : 출고확정처리 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdShipment", description = "출고확정처리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/wd/shipment", "ltx/wd/shipment"})
public class WdShipmentController {

	private final WdShipmentService wdShipmentService;

	/**
	 * @description : 출고확정처리 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고확정처리 목록 조회", description = "출고확정처리 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdShipmentResDto>> getMasterList(@RequestBody WdShipmentReqDto dto) {


		return ApiResult.createResult(wdShipmentService.getMasterList(dto));
	}
	
	/**
	 * @description : 출고대상 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고대상 목록 조회", description = "출고대상 목록 조회")
	@PostMapping(value = "/v1.0/getTab1DetailList")
	public ApiResult<List<WdShipmentTab1DetailResDto>> getTab1DetailList(@RequestBody WdShipmentReqDto dto) throws RemoteException {
		
		
		return ApiResult.createResult(wdShipmentService.getTab1DetailList(dto));
	}
	
	/**
	 * @description : 결품대상 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "결품대상 목록 조회", description = "결품대상 목록 조회")
	@PostMapping(value = "/v1.0/getTab2DetailList")
	public ApiResult<List<WdShipmentTab2DetailResDto>> getTab2DetailList(@RequestBody WdShipmentReqDto dto) {
		
		
		return ApiResult.createResult(wdShipmentService.getTab2DetailList(dto));
	}
	
	/**
	 * @description : 상차검수취소 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상차검수취소 목록 조회", description = "상차검수취소 목록 조회")
	@PostMapping(value = "/v1.0/getTab3DetailList")
	public ApiResult<List<WdShipmentTab3DetailResDto>> getTab3DetailList(@RequestBody WdShipmentReqDto dto) {
		
		
		return ApiResult.createResult(wdShipmentService.getTab3DetailList(dto));
	}
	
	/**
	 * @description : 세금계산서 발행여부 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "세금계산서 발행여부 조회", description = "세금계산서 발행여부 조회")
	@PostMapping(value = "/v1.0/getBillYn")
	public ApiResult<WdShipmentResBillYnDto> getBillYn(@RequestBody WdShipmentReqDto dto)  throws JCoException {
		
		
		return ApiResult.createResult(wdShipmentService.getBillYn(dto));
	}
	

	/**
	 * @throws Exception
	 * @description : 출고확정 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고확정 저장", description = "출고확정 저장")
	@PostMapping(value = "/v1.0/saveConfirm")
	public ApiResult<String> saveConfirm(@RequestBody WdShipmentReqDto dto) throws Exception {
		return ApiResult.createResult(wdShipmentService.saveConfirm(dto));
	}
	

	/**
	 * @throws Exception
	 * @description : 출고대상확정
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고대상확정", description = "출고대상확정")
	@PostMapping(value = "/v1.0/saveWD")
	public ApiResult<String> saveWD(@RequestBody WdShipmentReqDto dto) throws Exception {
		return ApiResult.createResult(wdShipmentService.saveWD(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 결품대상확정
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "결품대상확정", description = "결품대상확정")
	@PostMapping(value = "/v1.0/saveShortage")
	public ApiResult<String> saveShortage(@RequestBody WdShipmentReqDto dto) throws Exception {
		return ApiResult.createResult(wdShipmentService.saveShortage(dto));
	}
	
	/**
	 * @throws Exception
	 * @description : 상차검수취소 저장
	 * @issues :<pre>
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상차검수취소 저장", description = "상차검수취소 저장")
	@PostMapping(value = "/v1.0/saveWdInspect")
	public ApiResult<String> saveWdInspect(@RequestBody WdShipmentReqDto dto) throws Exception {
		return ApiResult.createResult(wdShipmentService.saveWdInspect(dto));
	}
	


}