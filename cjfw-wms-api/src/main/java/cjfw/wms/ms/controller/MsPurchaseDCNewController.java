package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsPurchaseDCNewAvgResDto;
import cjfw.wms.ms.dto.MsPurchaseDCNewReqDto;
import cjfw.wms.ms.dto.MsPurchaseDCNewResDto;
import cjfw.wms.ms.service.MsPurchaseDCNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.24 
 * @description : 기준정보 > 상품기준정보 > 수급마스터관리(New) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 수급마스터관리(New)", description = "수급마스터관리(New)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/ms/purchaseDCNew", "ltx/ms/purchaseDCNew"})
public class MsPurchaseDCNewController {

	private final MsPurchaseDCNewService msPurchaseDCNewService;

	/**
	 * @description : 수발주마스터 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수발주마스터 조회(목록)", description = "수발주마스터 조회(목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsPurchaseDCNewResDto>> getMasterList(@RequestBody @Valid MsPurchaseDCNewReqDto dto) {
		return ApiResult.createResult(msPurchaseDCNewService.getMasterList(dto));
	}
	
	/**
	 * @description : 수발주마스터 월평균 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.24 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "수발주마스터 월평균 조회(목록)", description = "수발주마스터 월평균 조회(목록)")
	@PostMapping(value = "/v1.0/getMasterAvgList")
	public ApiResult<List<MsPurchaseDCNewAvgResDto>> getMasterAvgList(@RequestBody @Valid MsPurchaseDCNewReqDto dto) {
		return ApiResult.createResult(msPurchaseDCNewService.getMasterAvgList(dto));
	}

}