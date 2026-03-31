package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StStockForCJLReqDto;
import cjfw.wms.st.dto.StStockForCJLResDto;
import cjfw.wms.st.service.StStockForCJLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.12 
 * @description : 저장품재고조회(CJ대한통운제공)  Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
 */
@Tag(name = "재고 > 재고현황 > 저장품재고조회(CJ대한통운제공)", description = "저장품재고조회(CJ대한통운제공) 조회")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stockForCJL")
public class StStockForCJLController {

	private final StStockForCJLService stStockForCJLService;

	/**
	 * @description : 브랜드별재고추이 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "저장품재고조회(CJ대한통운제공) 목록 조회", description = "저장품재고조회(CJ대한통운제공) 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockForCJLResDto>> getMasterList(@RequestBody @Valid StStockForCJLReqDto dto) {
		return ApiResult.createResult(stStockForCJLService.getMasterList(dto));
	}
	
}