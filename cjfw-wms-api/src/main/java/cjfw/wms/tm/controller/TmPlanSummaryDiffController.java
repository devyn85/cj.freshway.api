package cjfw.wms.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.wms.tm.dto.TmPlanSummaryDiffCarResDto;
import cjfw.wms.tm.dto.TmPlanSummaryDiffReqDto;
import cjfw.wms.tm.dto.TmPlanSummaryDiffResDto;
import cjfw.wms.tm.service.TmPlanSummaryDiffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.20 
 * @description : 배차 결과 비교 컨트롤러 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Slf4j
@RestController
@RequestMapping("/api/tm/planSummaryDiff")
@RequiredArgsConstructor
@Tag(name = "배송 > 배차 결과 비교", description = "배차 결과 비교 컨트롤러")
public class TmPlanSummaryDiffController {
	
	private final TmPlanSummaryDiffService tmPlanSummaryDiffService;
	
	/**
	 * @description : 배차 요약정보 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배차 결과 비교 조회", description = "배차 결과 비교 조회")
	@GetMapping(value = "/v1.0/getMasterList")
	public List<TmPlanSummaryDiffResDto> getMasterList(TmPlanSummaryDiffReqDto dto) {
		return tmPlanSummaryDiffService.getMasterList(dto);
	}
	
	/**
	 * @description : 배차 차량 요약정보 조회 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.21 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
	 */
	@Operation(summary = "배차 차량 요약 조회", description = "배차 차량 요약 조회")
	@GetMapping(value = "/v1.0/getMasterCarList")
	public TmPlanSummaryDiffCarResDto getMasterCarList(TmPlanSummaryDiffReqDto dto) {
		return tmPlanSummaryDiffService.getMasterCarList(dto);
	}
	
}
