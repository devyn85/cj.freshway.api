package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.wms.st.dto.StTplUserReqDto;
import cjfw.wms.st.dto.StTplUserResDto;
import cjfw.wms.st.service.StTplUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : ys.park(dytpq362@cj.com) 
 * @date : 2025.11.04 
 * @description : 화주 조회 기능을 구현한 Controller Class 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.04 ys.park 생성
 */
@Tag(name = "화주 검색", description = "화주 검색")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/search")
public class StTplUsesrController {

	private final StTplUserService stTplUserService;

	/**
	 * @description : 화주 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.04 ys.park 생성
	 */
	@Operation(summary = "화주 목록 조회", description = "화주 목록 조회")
	@PostMapping(value = "/v1.0/getTplUserPopupList")
	public ApiResult<Page<StTplUserResDto>> getTplUserPopupList(@RequestBody StTplUserReqDto dto, Page<?> page) {
		return ApiResult.createResult(stTplUserService.getTplUserPopupList(dto,page));
	}
	
	/** @description : 화주 조회 
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.19 ys.park 생성 </pre>
	*/
	@Operation(summary = "화주 검색", description = "화주 검색")
	@PostMapping(value="/v1.0/getMasterList")
	public ApiResult<List<StTplUserResDto>> getMasterList(@RequestBody StTplUserReqDto req) {
		return ApiResult.createResult(stTplUserService.getMasterList(req));
	}

}