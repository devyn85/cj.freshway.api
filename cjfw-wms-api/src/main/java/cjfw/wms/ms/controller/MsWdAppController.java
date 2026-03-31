package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ms.dto.MsWdAppReqDto;
import cjfw.wms.ms.dto.MsWdAppResDetailDto;
import cjfw.wms.ms.dto.MsWdAppResDto;
import cjfw.wms.ms.service.MsWdAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.10.24
 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 등록 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리", description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리를 조회,저장한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ms/wdApp")
public class MsWdAppController {
	private final MsWdAppService msWdAppService;
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 조회", description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsWdAppResDto>> getMasterList(@RequestBody MsWdAppReqDto dto) {
		return ApiResult.createResult(msWdAppService.getMasterList(dto));
	}
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 조회", description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<MsWdAppResDetailDto>> getDetailList(@RequestBody MsWdAppReqDto dto) {
		return ApiResult.createResult(msWdAppService.getDetailList(dto));
	}

	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 저장", description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody MsWdAppReqDto dto) throws Exception {
	    return ApiResult.createResult(msWdAppService.saveMasterList(dto));	
	}
	
	/**
	 * @description : 기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.24 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 저장", description = "기준정보 > 물류센터 정보 > 결품대응 POP그룹 관리 상세 저장")
	@PostMapping(value = "/v1.0/saveDetailList")
	public ApiResult<String> saveDetailList(@RequestBody MsWdAppReqDto dto) throws Exception {
		return ApiResult.createResult(msWdAppService.saveDetailList(dto));
	}	
}
