package cjfw.wms.cm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmReceiveGroupReqDto;
import cjfw.wms.cm.dto.CmReceiveGroupResDto;
import cjfw.wms.cm.service.CmReceiveGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)  
 * @date : 2025.09.11 
 * @description : 기준정보 > 기타기준정보 > 수신그룹관리마스터 목록 조회 및 저장 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.11 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Tag(name = "기준정보 > 게시판관리 > 수신그룹관리", description = "수신그룹관리 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/receiveGroup")
public class CmReceiveGroupController {

	private final CmReceiveGroupService cmRcvGrpService;

	/**
	 * @description : 수신그룹관리 목록 검색 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "수신그룹관리 목록 검색 조회", description = "수신그룹관리 목록 검색 조회")
	@GetMapping(value = "/v1.0/getRcvGrpHeaderList")
	public ApiResult<List<CmReceiveGroupResDto>> getRcvGrpHeaderList(@Valid CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		return ApiResult.createResult(cmRcvGrpService.getRcvGrpHeaderList(cmReceiveGroupReqDto));
	}
	
	/**
	 * @description : 수신그룹관리 상세 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.11 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "수신그룹관리 상세 목록 조회", description = "수신그룹관리 상세 목록 조회")
	@GetMapping(value = "/v1.0/getRcvGrpDetailList")
	public ApiResult<List<CmReceiveGroupResDto>> getRcvGrpDetailList(@Valid CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		return ApiResult.createResult(cmRcvGrpService.getRcvGrpDetailList(cmReceiveGroupReqDto));
	}
	
	/**
	 * @description : 수신그룹관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.19 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "그룹 수신그룹관리 저장", description = "그룹 수신그룹관리 저장")
	@PostMapping(value = "/v1.0/saveRcvGrpHeader")
	public ApiResult<String> saveRcvGrpHeader(@RequestBody @Valid CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		return ApiResult.createResult(cmRcvGrpService.saveRcvGrpHeader(cmReceiveGroupReqDto));
	}
	
	/**
	 * @description : 상세 수신그룹관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.02 JiSooKim (jskim14@cj.net)  생성 </pre>
	 */
	@Operation(summary = "상세 수신그룹관리 저장", description = "상세 수신그룹관리 저장")
	@PostMapping(value = "/v1.0/saveRcvGrpDetail")
	public ApiResult<String> saveRcvGrpDetail(@RequestBody @Valid CmReceiveGroupReqDto cmReceiveGroupReqDto) {
		return ApiResult.createResult(cmRcvGrpService.saveRcvGrpDetail(cmReceiveGroupReqDto));
	}

}