package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpKxCloseDtlResT1Dto;
import cjfw.wms.kp.dto.KpKxCloseReqDto;
import cjfw.wms.kp.dto.KpKxCloseResT10Dto;
import cjfw.wms.kp.dto.KpKxCloseResT11Dto;
import cjfw.wms.kp.dto.KpKxCloseResT13Dto;
import cjfw.wms.kp.dto.KpKxCloseResT14Dto;
import cjfw.wms.kp.dto.KpKxCloseResT1Dto;
import cjfw.wms.kp.dto.KpKxCloseResT2Dto;
import cjfw.wms.kp.dto.KpKxCloseResT4Dto;
import cjfw.wms.kp.dto.KpKxCloseResT5Dto;
import cjfw.wms.kp.dto.KpKxCloseResT6Dto;
import cjfw.wms.kp.dto.KpKxCloseResT7Dto;
import cjfw.wms.kp.dto.KpKxCloseResT8Dto;
import cjfw.wms.kp.dto.KpKxCloseResT9Dto;
import cjfw.wms.kp.service.KpKxCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.09.24 
 * @description : ADMIN > 모니터링 > KX마감진행현황 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
 */
@Tag(name = "ADMIN > 모니터링 > KX마감진행현황", description = "KX마감진행현황 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/kxClose")
public class KpKxCloseController {

	private final KpKxCloseService kpKxCloseService;

	/**
	 * @description : 마감현황 - 문서 기준 처리현황 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX마감진행현황 조회", description = "KX마감진행현황 조회")
	@GetMapping(value = "/v1.0/getKxCloseList")
	public ApiResult<List<KpKxCloseResT1Dto>> getKxCloseList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCloseList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 마감현황 - I/F 기준 처리현황 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX마감진행현황 조회 (I/F)", description = "KX마감진행현황 조회 (I/F)")
	@GetMapping(value = "/v1.0/getKxCloseListIF")
	public ApiResult<List<KpKxCloseResT1Dto>> getListIF(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCloseListIF(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 마감현황 - 문서 기준 처리현황 상세 조회 (문서 기준 미처리 내역)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX마감진행현황 상세조회", description = "KX마감진행현황 상세조회")
	@GetMapping(value = "/v1.0/getKxCloseDetail")
	public ApiResult<List<KpKxCloseDtlResT1Dto>> getDetail(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCloseDetailList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 마감현황 - I/F 기준 처리현황 상세 조회 (I/F 기준 미처리 내역)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX마감진행현황 상세조회 (I/F)", description = "KX마감진행현황 상세조회 (I/F)")
	@GetMapping(value = "/v1.0/getKxCloseDetailIF")
	public ApiResult<List<KpKxCloseDtlResT1Dto>> getIFDetail(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCloseDetailListIF(KpKxCloseReqDto));
	}
	
	/**
	 * @description : KX검증 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX검증 조회", description = "KX검증 조회")
	@GetMapping(value = "/v1.0/getKxCheckList")
	public ApiResult<List<KpKxCloseResT2Dto>> getKxCheckList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCheckList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : KX검증 저장 - IF상태 변경
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.23 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX검증 저장", description = "KX검증 저장 - IF상태 변경")
	@PostMapping(value = "/v1.0/saveKxCheck")
	public ApiResult<String> saveKxCheck(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveKxCheck(dto));
	}	
	
	/**
	 * @description : 문서내역 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "문서내역 조회", description = "문서내역 조회")
	@GetMapping(value = "/v1.0/getKxDocList")
	public ApiResult<List<KpKxCloseResT4Dto>> getKxDocList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxDocList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 문서내역 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "문서내역 상세 조회", description = "문서내역 상세 조회")
	@GetMapping(value = "/v1.0/getKxDocDetailList")
	public ApiResult<List<KpKxCloseResT4Dto>> getKxDocDetailList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxDocDetailList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 문서내역 저장 - 삭제오더 초기화 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */	
	@Operation(summary = "문서내역 저장", description = "문서내역 저장 - 삭제오더 초기화 ")
	@PostMapping(value = "/v1.0/saveDelOrderReset")
	public ApiResult<String> saveDelOrderReset(@RequestBody @Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.saveDelOrderReset(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 문서내역 저장 - STO예외처리, 오더초기화, 강제결품
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */	
	@Operation(summary = "문서내역 저장", description = "문서내역 저장 - STO예외처리, 오더초기화, 강제결품 ")
	@PostMapping(value = "/v1.0/saveKxDoc")
	public ApiResult<String> saveKxDoc(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveT3Pr(dto));
	}	
	
	
	/**
	 * @description : 인수거절확인 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "인수거절확인 조회", description = "인수거절확인 조회")
	@GetMapping(value = "/v1.0/getKxCdiiList")
	public ApiResult<List<KpKxCloseResT4Dto>> getDataKxCdiiList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxCdiiList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 수불확인 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.24 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수불확인 조회", description = "수불확인 조회")
	@GetMapping(value = "/v1.0/getKxInoutListForSku")
	public ApiResult<List<KpKxCloseResT5Dto>> getKxInoutListForSku(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxInoutListForSku(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 조정내역반영 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.27 YoSepPark (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "조정요청내역 조회", description = "조정요청내역 조회")
	@GetMapping(value = "/v1.0/getKxAjRequestList")
	public ApiResult<List<KpKxCloseResT6Dto>> getKxAjRequestList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxAjRequestList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 조정내역반영 저장 및 결과 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.01 YoSepPark (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "조정내역반영", description = "조정내역반영 저장")
	@PostMapping(value = "/v1.0/saveKxAj")
	public ApiResult<List<KpKxCloseResT6Dto>> saveKxAj(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveKxAj(dto));
	}	
	
	/**
	 * @description : 협력사반출처리상태 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.05 YoSepPark (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "협력사반출 조회", description = "협력사반출 조회")
	@GetMapping(value = "/v1.0/getKxRPList")
	public ApiResult<List<KpKxCloseResT7Dto>> getKxRPList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxRPList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 협력사반출 저장 및 결과 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.08 YoSepPark (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "협력사반출 저장", description = "협력사반출 저장")
	@PostMapping(value = "/v1.0/saveKxRP")
	public ApiResult<List<KpKxCloseResT7Dto>> saveKxRP(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveKxRP(dto));
	}	
	
	/**
	 * @description : 협력사반출 예외처리 및 결과 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.08 YoSepPark (dytpq362@cj.net) 생성 </pre>
	 */
	@Operation(summary = "협력사반출 예외처리", description = "협력사반출 예외처리")
	@PostMapping(value = "/v1.0/saveKxRPEx")
	public ApiResult<List<KpKxCloseResT7Dto>> saveKxRPEx(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveKxRPEx(dto));
	}	
	
	/**
	 * @description : 소유권확인 탭 저장 및 결과 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "소유권확인 탭 저장 및 결과 조회", description = "소유권확인 탭 저장 및 결과 조회")
	@PostMapping(value = "/v1.0/saveKxTrOrderCheck")
	public ApiResult<List<KpKxCloseResT9Dto>> saveKxTrOrderCheck(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveKxTrOrderCheck(dto));
	}	
	
	
	/**
	 * @description : 수불비교 - 월간수불비교 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수불비교 조회", description = "수불비교 - 월간수불비교 조회")
	@GetMapping(value = "/v1.0/getKxSubulMonthList")
	public ApiResult<List<KpKxCloseResT11Dto>> getKxSubulMonthList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		// return ApiResult.createResult(kpKxCloseService.getKxSubulMonthList(KpKxCloseReqDto));
		return ApiResult.createResult(kpKxCloseService.getKxSubulMonthList2(KpKxCloseReqDto));
	}

	/**
	 * @description : 수불비교 - 일별수불비교 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수불비교 조회", description = "수불비교 - 일별 조회")
	@GetMapping(value = "/v1.0/getKxSubulDayList")
	public ApiResult<List<KpKxCloseResT11Dto>> getKxSubulDayList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		//return ApiResult.createResult(kpKxCloseService.getKxSubulDayList(KpKxCloseReqDto));
		return ApiResult.createResult(kpKxCloseService.getKxSubulDayList2(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 수불비교 - 일별수불비교(상세) 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.13 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수불비교(상세) 조회", description = "수불비교 - 일별 상세 조회")
	@GetMapping(value = "/v1.0/getKxSubulDayDtlList")
	public ApiResult<List<KpKxCloseResT11Dto>> getKxSubulDayDetailList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		// return ApiResult.createResult(kpKxCloseService.getKxSubulDayDetailList(KpKxCloseReqDto));
		return ApiResult.createResult(kpKxCloseService.getKxSubulDayDetailList2(KpKxCloseReqDto));
	}
	
	/**
	 * @description : 재고비교 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 JiSooKim (jskim14@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고비교 조회", description = "재고비교 조회")
	@GetMapping(value = "/v1.0/getKxStDiffList")
	public ApiResult<List<KpKxCloseResT13Dto>> getKxStDiffList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		// return ApiResult.createResult(kpKxCloseService.getKxStDiffList(KpKxCloseReqDto));
		return ApiResult.createResult(kpKxCloseService.getKxStDiffList2(KpKxCloseReqDto));
	}
	
	/**
	 * @description : I/F관리 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "I/F관리 조회", description = "I/F관리 조회")
	@GetMapping(value = "/v1.0/getDataIfStatusList")
	public ApiResult<List<KpKxCloseResT14Dto>> getDataIfStatusList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getDataIfStatusList(KpKxCloseReqDto));
	}	
	
	/**
	 * @description : 코스트센터 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "코스트센터 저장", description = "코스트센터 저장")
	@PostMapping(value = "/v1.0/saveDuplicateCostCd")
	public ApiResult<KpKxCloseReqDto> saveDuplicateCostCd(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveDuplicateCostCd(dto));
	}	
	
	/**
	 * @description : SRM1300 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "SRM1300 저장", description = "SRM1300 저장")
	@PostMapping(value = "/v1.0/saveDuplicateSRM1300")
	public ApiResult<KpKxCloseReqDto> saveDuplicateSRM1300(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveDuplicateSRM1300(dto));
	}	
	
	/**
	 * @description : I/F관리 상세 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "I/F관리 상세 조회", description = "I/F관리 상세 조회")
	@PostMapping(value = "/v1.0/getDataIfStatusDetailList")
	public ApiResult<List<KpKxCloseResT14Dto>> getDataIfStatusDetailList(@RequestBody KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getDataIfStatusDetailList(KpKxCloseReqDto));
	}		
	
	
	/**
	 * @description : 오류 재처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "오류 재처리", description = "오류 재처리")
	@PostMapping(value = "/v1.0/saveIFTables")
	public ApiResult<KpKxCloseReqDto> saveIFTables(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveIFTables(dto));
	}		
	
	
	/**
	 * @description : 실적미수신 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "실적미수신 조회", description = "실적미수신 조회")
	@PostMapping(value = "/v1.0/getDataNotRcvList")
	public ApiResult<List<KpKxCloseResT8Dto>> getDataNotRcvList(@RequestBody KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getDataNotRcvList(KpKxCloseReqDto));
	}

	/**
	 * @description : 실적미수신 KX접수여부
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX접수여부 조회", description = "KX접수여부 조회")
	@PostMapping(value = "/v1.0/getKxAcceptYn")
	public ApiResult<String> getKxAcceptYn(@RequestBody KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxAcceptYn(KpKxCloseReqDto));
	}
		
	
	/**
	 * @description : 오더강제결품 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "오더강제결품", description = "오더강제결품")
	@PostMapping(value = "/v1.0/saveOrderClear")
	public ApiResult<KpKxCloseReqDto> saveOrderClear(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveOrderClear(dto));
	}		
	
	/**
	 * @description : KX실적미수신 이메일 전송
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.01.15 sss (kduimux@cj.net) 생성 </pre>
	 */
	@Operation(summary = "KX실적미수신 이메일 전송", description = "KX실적미수신 이메일 전송")
	@PostMapping(value = "/v1.0/saveEmail")
	public ApiResult<KpKxCloseReqDto> saveEmail(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveEmail(dto));
	}	
	
	/**
	 * @description : 재고 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.11 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "재고 조회", description = "재고 조회")
	@GetMapping(value = "/v1.0/getKxStockList")
	public ApiResult<List<KpKxCloseResT10Dto>> getKxStockList(@Valid KpKxCloseReqDto KpKxCloseReqDto) {
		return ApiResult.createResult(kpKxCloseService.getKxStockList(KpKxCloseReqDto));
	}
	
	/**
	 * @description : STOCKID(개체식별/유통이력) 초기화 처리
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.12 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "STOCKID(개체식별/유통이력) 초기화 처리", description = "STOCKID(개체식별/유통이력) 초기화 처리")
	@PostMapping(value = "/v1.0/saveStockIdInit")
	public ApiResult<String> saveStockIdInit(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveStockIdInit(dto));
	}
	
	/**
	 * @description : 전표 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.13 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "전표 수정", description = "전표 수정")
	@PostMapping(value = "/v1.0/saveInplanZero")
	public ApiResult<String> saveInplanZero(@RequestBody @Valid KpKxCloseReqDto dto) throws Exception {
		return ApiResult.createResult(kpKxCloseService.saveInplanZero(dto));
	}

}