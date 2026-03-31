package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmTnsfMissingSTOReqDto;
import cjfw.wms.om.dto.OmTnsfMissingSTOResDto;
import cjfw.wms.om.service.OmTnsfMissingSTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.11.12
 * @description : 주문 > 주믄등록 > 누락분 STO 이체 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 누락분 STO 이체", description = "누락분 STO 이체")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/tnsfMissingSTO")
public class OmTnsfMissingSTOController {

	private final OmTnsfMissingSTOService omTnsfMissingSTOService;

	/**
	 * @description : 누락분 STO 이체 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "누락분 STO 이체 마스터 조회 (목록)", description = "누락분 STO 이체 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmTnsfMissingSTOResDto>> getMasterList(@RequestBody @Valid OmTnsfMissingSTOReqDto dto) {
		return ApiResult.createResult(omTnsfMissingSTOService.getMasterList(dto));
	}
	
	/**
	 * @description : 누락분 STO이체 마스터 조회 (목록) 프린트 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.11 ParkJinWoo 생성
	 */
	@Operation(summary = "누락분 STO이체 마스터 조회 (목록) 프린트", description = "누락분 STO 이체 마스터 조회 (목록) 프린트")
	@PostMapping(value = "/v1.0/getMasterPrintList")
	public ApiResult<List<OmTnsfMissingSTOResDto>> getMasterPrintList(@RequestBody @Valid OmTnsfMissingSTOReqDto dto) {
		return ApiResult.createResult(omTnsfMissingSTOService.getMasterPrintList(dto));
	}
	/**
	 * @description : 누락분 STO 공급받는업체 이체 마스터 조회 (목록) 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.11 ParkJinWoo 생성
	 */
	@Operation(summary = "누락분 STO 공급받는업체 이체 마스터 조회 (목록)", description = "누락분 STO 이체 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList1")
	public ApiResult<List<OmTnsfMissingSTOResDto>> getMasterList1(@RequestBody @Valid OmTnsfMissingSTOReqDto dto) {
		return ApiResult.createResult(omTnsfMissingSTOService.getMasterList1(dto));
	}
	
	/**
	 * @description : 누락분 STO 공급받는업체 이체 마스터 조회 (목록) 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.11 ParkJinWoo 생성
	 */
	@Operation(summary = "누락분 STO 공급받는업체 이체 마스터 조회 (목록)", description = "누락분 STO 이체 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMaster1PrintList")
	public ApiResult<List<OmTnsfMissingSTOResDto>> getMaster1PrintList(@RequestBody @Valid OmTnsfMissingSTOReqDto dto) {
		return ApiResult.createResult(omTnsfMissingSTOService.getMaster1PrintList(dto));
	}
	/**
	 * @description : 누락분 STO 이체 마스터 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "누락분 STO 이체 마스터 저장", description = "누락분 STO 이체 마스터 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<OmTnsfMissingSTOReqDto> dtoList) {
		return ApiResult.createResult(omTnsfMissingSTOService.saveMasterList(dtoList));
	}
	

	
	/**
	 * @description : 누락분 STO 이체 마스터 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "누락분 STO 이체 마스터 저장", description = "누락분 STO 이체 마스터 저장")
	@PostMapping(value = "/v1.0/saveMasterList1")
	public ApiResult<String> saveMasterList1(@RequestBody @Valid List<OmTnsfMissingSTOReqDto> dtoList) {
		return ApiResult.createResult(omTnsfMissingSTOService.saveMasterList1(dtoList));
	}
	/**
	 * @description : 누락분 STO 이체 마스터 확정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "누락분 STO 이체 마스터 확정", description = "누락분 STO 이체 마스터 확정")
	@PostMapping(value = "/v1.0/confirmMasterList")
	public ApiResult<String> confirmMasterList(@RequestBody @Valid List<OmTnsfMissingSTOReqDto> dtoList) {
		return ApiResult.createResult(omTnsfMissingSTOService.confirmMasterList(dtoList));
	}
	
	/**
	 * @description : 누락분 STO 이체 대응완료(요청받는)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.12 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "누락분 STO 이체 대응완료(요청받는)", description = "누락분 STO 이체 대응완료(요청받는)")
	@PostMapping(value = "/v1.0/supplyConfirmList")
	public ApiResult<String> supplyConfirmList(@RequestBody @Valid List<OmTnsfMissingSTOReqDto> dtoList) {
		return ApiResult.createResult(omTnsfMissingSTOService.supplyConfirmList(dtoList));
	}
	
}