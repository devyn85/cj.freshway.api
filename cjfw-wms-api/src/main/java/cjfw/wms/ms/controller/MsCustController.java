package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.ms.dto.MsCustDetailResDto;
import cjfw.wms.ms.dto.MsCustHeaderResDto;
import cjfw.wms.ms.dto.MsCustReqDto;
import cjfw.wms.ms.dto.MsCustResDto;
import cjfw.wms.ms.service.MsCustService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.09 
 * @description : 기준정보 > 거래처기준정보 > 고객정보(New) Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 거래처기준정보 > 고객정보(New)", description = "고객정보(New)")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/cust")
public class MsCustController {

	private final MsCustService msCustService;

	/**
	 * @description : 주출고센터리스트 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "주출고센터 코드리스트 조회", description = "주출고센터 코드리스트 조회")
	@GetMapping(value = "/v1.0/getSelectDccodeList")
	public ApiResult<List<MsCustHeaderResDto>> getSelectDccodeList() {
		return ApiResult.createResult(msCustService.getSelectDccodeList());
	}
	
	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (목록)", description = "거래처 정보 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<Page<MsCustResDto>> getMasterList(@RequestBody @Valid MsCustReqDto dto) {
		return ApiResult.createResult(msCustService.getMasterList(dto));
	}
	
		
	/**
	 * @description : 거래처 정보 조회 (단건)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (단건)", description = "거래처 정보 조회 (단건)")
	@GetMapping(value="/v1.0/getMaster")
	public ApiResult<MsCustDetailResDto> getMaster(MsCustReqDto dto) {
		return ApiResult.createResult(msCustService.getMaster(dto));
	}
	/**
	 * @description : 거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.09    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처 정보 저장", description = "거래처 정보 저장")
	@PostMapping(value = "/v1.0/saveMaster")
	public ApiResult<String> saveCustInfo(@RequestBody @Valid MsCustReqDto dto) {
		return ApiResult.createResult(msCustService.saveMaster(dto));
	}

}