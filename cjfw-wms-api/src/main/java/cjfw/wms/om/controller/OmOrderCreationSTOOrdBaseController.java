package cjfw.wms.om.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmOrderCreationSTOBatchResultDetailDto;
import cjfw.wms.om.dto.OmOrderCreationSTOBatchResultHeadDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseResultResDto;
import cjfw.wms.om.service.OmOrderCreationSTOOrdBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.31
 * @description : 주문 > 주믄등록 > 당일광역보충발주 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주믄등록 > 당일광역보충발주", description = "당일광역보충발주")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/om/orderCreationSTOOrdBase", "ltx/om/orderCreationSTOOrdBase"})
public class OmOrderCreationSTOOrdBaseController {

	private final OmOrderCreationSTOOrdBaseService omOrderCreationSTOOrdBaseService;

	/**
	 * @description : 당일광역보충발주 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 마스터 조회 (목록)", description = "당일광역보충발주 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmOrderCreationSTOOrdBaseResDto>> getMasterList(@RequestBody @Valid OmOrderCreationSTOOrdBaseReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseService.getMasterList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 마감유형 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 마감유형 조회 (목록)", description = "당일광역보충발주 마감유형 조회 (목록)")
	@GetMapping(value = "/v1.0/getDailyDeadlineSto")
	public ApiResult<List<OmOrderCreationSTOOrdBaseResDto>> getDailyDeadlineSto(@Valid OmOrderCreationSTOOrdBaseReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseService.getDailyDeadlineSto(dto));
	}
	
	
	/**
	 * @throws RemoteException 
	 * @description : 당일광역보충발주 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "당일광역보충발주 저장", description = "당일광역보충발주 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<List<OmOrderCreationSTOOrdBaseResultResDto>> saveMasterList(@RequestBody @Valid OmOrderCreationSTOOrdBaseReqDto dto) throws RemoteException {
		
		List<OmOrderCreationSTOOrdBaseResultResDto> resultList = omOrderCreationSTOOrdBaseService.saveMasterList(dto);
		
		if("OSTO".equals(dto.getStoStoragetype())) {	//주문 STO인 경우 출고 재고 분배 STO로직을 태운다.   20260305 공두경
			omOrderCreationSTOOrdBaseService.saveSTOAllocationBatch(dto);
		}
		
		return ApiResult.createResult(resultList);
	}
	
	/**
	 * @description : 당일광역보충발주 배치결과 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 배치결과 조회 (목록)", description = "당일광역보충발주 배치결과 조회 (목록)")
	@PostMapping(value = "/v1.0/getBatchResultList")
	public ApiResult<List<OmOrderCreationSTOOrdBaseResDto>> getBatchResultList(@RequestBody @Valid OmOrderCreationSTOOrdBaseReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseService.getBatchResultList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 배치결과 헤더 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.12 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 배치결과 헤더 조회 (목록)", description = "당일광역보충발주 배치결과 헤더 조회 (목록)")
	@PostMapping(value = "/v1.0/getBatchResultHeadList")
	public ApiResult<List<OmOrderCreationSTOBatchResultHeadDto>> getBatchResultHeadList(@RequestBody @Valid OmOrderCreationSTOOrdBaseReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseService.getBatchResultHeadList(dto));
	}
	
	/**
	 * @description : 당일광역보충발주 배치결과 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.12 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	@Operation(summary = "당일광역보충발주 배치결과 상세 조회 (목록)", description = "당일광역보충발주 배치결과 상세 조회 (목록)")
	@PostMapping(value = "/v1.0/getBatchResultDetailList")
	public ApiResult<List<OmOrderCreationSTOBatchResultDetailDto>> getBatchResultDetailList(@RequestBody @Valid OmOrderCreationSTOOrdBaseReqDto dto) {
		return ApiResult.createResult(omOrderCreationSTOOrdBaseService.getBatchResultDetailList(dto));
	}
	
}