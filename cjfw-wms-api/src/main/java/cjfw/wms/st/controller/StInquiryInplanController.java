package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StInquiryInplanReqDto;
import cjfw.wms.st.dto.StInquiryInplanResDto;
import cjfw.wms.st.service.StInquiryInplanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.28
 * @description : 재고 > 재고조사 > 재고 실사 지시 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 재고조사 > 재고 실사 지시", description = "재고 > 재고조사 > 재고 실사 지시를 조회, 저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/inquiryInplan")
public class StInquiryInplanController {
	private final StInquiryInplanService stInquiryInplanService;

	/**
	 * @description : 재고 > 재고조사 > 재고 실사 지시 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고 실사 지시 조회", description = "재고 > 재고조사 > 재고 실사 지시 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StInquiryInplanResDto>> getMasterList(@RequestBody StInquiryInplanReqDto dto) {
		return ApiResult.createResult(stInquiryInplanService.getMasterList(dto));
	}
	
	/**
	 * @description : 재고 > 재고조사 > 재고 실사 지시 조사명칭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고 실사 지시 조사명칭 조회", description = "재고 > 재고조사 > 재고 실사 지시 조사명칭 조회")
	@PostMapping(value = "/v1.0/getInquiryName")
	public ApiResult<List<StInquiryInplanResDto>> getInquiryName(@RequestBody StInquiryInplanReqDto dto) {
		return ApiResult.createResult(stInquiryInplanService.getInquiryName(dto));
	}
	
	/**
	 * @description : 재고 > 재고조사 > 재고 실사 지시 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.28 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조사 > 재고 실사 지시 저장", description = "재고 > 재고조사 > 재고 실사 지시 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<StInquiryInplanResDto> saveMasterList(@RequestBody StInquiryInplanReqDto dto) throws Exception {
		return ApiResult.createResult(stInquiryInplanService.saveMasterList(dto));
	}
}
