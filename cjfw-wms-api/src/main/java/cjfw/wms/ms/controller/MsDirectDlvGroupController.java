package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsDirectDlvGroupReqDto;
import cjfw.wms.ms.dto.MsDirectDlvGroupResDto;
import cjfw.wms.ms.service.MsDirectDlvGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.06.23 
 * @description : 기준정보 > 상품기준정보 > 발주직송그룹관리 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "기준정보 > 상품기준정보 > 발주직송그룹관리", description = "발주직송그룹관리")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/directDlvGroup")
public class MsDirectDlvGroupController {

	private final MsDirectDlvGroupService msDirectDlvGroupService;

	/**
	 * @description : 거래처 정보 조회 (목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "거래처 정보 조회 (목록)", description = "거래처 정보 조회 (목록)")
	@GetMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsDirectDlvGroupResDto>> getMasterList(@Valid MsDirectDlvGroupReqDto dto) {
		return ApiResult.createResult(msDirectDlvGroupService.getMasterList(dto));
	}
		
	/**
	 * @description : 거래처 정보 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "거래처 정보 저장", description = "거래처 정보 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<MsDirectDlvGroupReqDto> dto) {
		return ApiResult.createResult(msDirectDlvGroupService.saveMasterList(dto));
	}

}