package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StConvertIdSNReqDto;
import cjfw.wms.st.dto.StConvertIdSNResDto;
import cjfw.wms.st.service.StConvertIdSNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.16
 * @description : 재고 > 재고조정 > 이력상품바코드변경  Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "재고 > 재고조정 > 이력상품바코드변경", description = "이력상품바코드변경를 조회, 저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/convertIdSN")
public class StConvertIdSNController {
	private final StConvertIdSNService stConvertIdSNService;

	/**
	 * @description : 재고 > 재고조정 > 이력상품바코드변경 마스터 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 이력상품바코드변경 마스터 조회", description = "재고 > 재고조정 > 이력상품바코드변경 마스터 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StConvertIdSNResDto>> getMasterList(@RequestBody StConvertIdSNReqDto dto) {
		return ApiResult.createResult(stConvertIdSNService.getMasterList(dto));
	}

	/**
	 * @description : 재고 > 재고조정 > 상품이력번호변경 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.16 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "재고 > 재고조정 > 이력상품바코드변경 저장", description = "재고 > 재고조정 > 이력상품바코드변경 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody StConvertIdSNReqDto dto) throws Exception {
	    return ApiResult.createResult(stConvertIdSNService.saveMasterList(dto));
	}	
	
	
}
