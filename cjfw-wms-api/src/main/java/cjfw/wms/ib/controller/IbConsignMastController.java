package cjfw.wms.ib.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbConsignMastReqDto;
import cjfw.wms.ib.dto.IbConsignMastResT1Dto;
import cjfw.wms.ib.service.IbConsignMastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.   777
 * 
 * @author : 고혜미 (laksjd0606@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
 *         </pre>
 */
@Tag(name = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리를 조회, 저장 한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/consignMast")
public class IbConsignMastController {
	private final IbConsignMastService ibConsignMastService;
	
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[품목별정산료TAB] 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB]", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB]")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<Object> getTab1MasterList(@RequestBody IbConsignMastReqDto dto) {
		return ApiResult.createResult(ibConsignMastService.getTab1MasterList(dto));
	}	
	
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[품목별정산료TAB] 생성자료조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.14 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[품목별정산료TAB_자료생성]", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[품목별정산료TAB_쟈료생성]")
	@PostMapping(value = "/v1.0/getTab1CreatDataList")
	public ApiResult<Object> getTab1CreatDataList(@RequestBody IbConsignMastReqDto dto) {
		return ApiResult.createResult(ibConsignMastService.getTab1CreatDataList(dto));
	}
	
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산[품목별정산료TAB] 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.12 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[품목별정산료TAB] 저장", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[품목별정산료TAB] 저장")
	@PostMapping(value = "/v1.0/saveTab1MasterList")
	public ApiResult<Object> saveTab1MasterList(@RequestBody List<IbConsignMastResT1Dto> dto) throws Exception {
	    return ApiResult.createResult(ibConsignMastService.saveTab1MasterList(dto));
	}

	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리[기준정보TAB] 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB]", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB]")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<Object> getTab2MasterList(@RequestBody IbConsignMastReqDto dto) {
		return ApiResult.createResult(ibConsignMastService.getTab2MasterList(dto));
	}	
	
	/**
	 * @description : 정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산[기준정보TAB] 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 고혜미 (laksjd0606@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB] 저장", description = "정산 > 3PL물류대행수수료정산 > 위탁 물류 처리 - 수수료 정산 관리 조회[기준정보TAB] 저장")
	@PostMapping(value = "/v1.0/saveTab2MasterList")
	public ApiResult<Object> saveTab2MasterList(@RequestBody List<IbConsignMastReqDto> dto) throws Exception {
	    return ApiResult.createResult(ibConsignMastService.saveTab2MasterList(dto));
	}	

}
