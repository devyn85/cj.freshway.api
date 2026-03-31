package cjfw.wms.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.om.dto.OmCkApprovalReqDto;
import cjfw.wms.om.dto.OmCkApprovalResDto;
import cjfw.wms.om.service.OmCkApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.09.26
 * @description : 주문 > 주문요청 > CK주문결재내역 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "주문 > 주문요청 > CK주문결재내역", description = "CK주문결재내역")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/om/ckApproval")
public class OmCkApprovalController {

	private final OmCkApprovalService omCkApprovalService;

	/**
	 * @description : CK주문결재내역 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "CK주문결재내역 마스터 조회 (목록)", description = "CK주문결재내역 마스터 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<OmCkApprovalResDto>> getMasterList(@RequestBody @Valid OmCkApprovalReqDto dto) {
		return ApiResult.createResult(omCkApprovalService.getMasterList(dto));
	}
	
	
	/**
	 * @description : CK주문결재내역 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.26    JeongHyeongCheol (wjdgudcjf@cj.net)   생성
	 */
	@Operation(summary = "CK주문결재내역 저장", description = "CK주문결재내역 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody @Valid List<OmCkApprovalReqDto> dto)  {
		return ApiResult.createResult(omCkApprovalService.saveMasterList(dto));
	}
	
}