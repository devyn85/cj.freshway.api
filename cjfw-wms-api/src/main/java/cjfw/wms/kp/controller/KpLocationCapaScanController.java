package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpLocationCapaScanReqDto;
import cjfw.wms.kp.dto.KpLocationCapaScanResT1Dto;
import cjfw.wms.kp.dto.KpLocationCapaScanResT2Dto;
import cjfw.wms.kp.service.KpLocationCapaScanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.09
 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황", description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황의 요약, 센터별 상세를 조회한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/locationCapaScan")
public class KpLocationCapaScanController {
	private final KpLocationCapaScanService kpLocationCapaScanService;
	
	/**
	 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 피킹존 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 피킹존 조회", description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 피킹존 조회")
	@PostMapping(value = "/v1.0/getPickingZoneList")
	public ApiResult<List<KpLocationCapaScanResT1Dto>> getPickingZoneList(@RequestBody KpLocationCapaScanReqDto dto) {
		return ApiResult.createResult(kpLocationCapaScanService.getPickingZoneList(dto));
	}	

	/**
	 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 조회", description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 요약_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<KpLocationCapaScanResT1Dto>> getMasterT1List(@RequestBody KpLocationCapaScanReqDto dto) {
		return ApiResult.createResult(kpLocationCapaScanService.getMasterT1List(dto));
	}	
	
	/**
	 * @description : 지표 > 재고 운영 > 물류센터 Capa 스캔 현황 센터별 상세_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.09 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 센터별 상세_탭 조회", description = "지표 > 재고 운영 > 물류센터 Capa 스캔 현황 센터별 상세_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<KpLocationCapaScanResT2Dto>> getMasterT2List(@RequestBody KpLocationCapaScanReqDto dto) {
		return ApiResult.createResult(kpLocationCapaScanService.getMasterT2List(dto));
	}		

}
