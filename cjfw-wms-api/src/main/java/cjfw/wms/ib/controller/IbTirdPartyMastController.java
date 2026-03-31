package cjfw.wms.ib.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.ib.dto.IbTirdPartyMastReqDto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT1Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT2Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT3DetailDto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT3Dto;
import cjfw.wms.ib.dto.IbTirdPartyMastResT4Dto;
import cjfw.wms.ib.service.IbTirdPartyMastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : KimDongHan (liop0123@cj.net)
 * @date : 2025.09.25
 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산 단가마스터_탭, 협력사관리_탭, 검수관리_탭, 정산관리_탭을 조회,저장,수정한다.")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/ib/tirdPartyMast")
public class IbTirdPartyMastController {
	private final IbTirdPartyMastService ibTirdPartyMastService;

	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 조회", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT1List")
	public ApiResult<List<IbTirdPartyMastResT1Dto>> getMasterT1List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.getMasterT1List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 조회", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT2List")
	public ApiResult<List<IbTirdPartyMastResT2Dto>> getMasterT2List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.getMasterT2List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 조회", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT3List")
	public ApiResult<List<IbTirdPartyMastResT3Dto>> getMasterT3List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.getMasterT3List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 조회", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 상세 조회")
	@PostMapping(value = "/v1.0/getDetailT3List")
	public ApiResult<List<IbTirdPartyMastResT3DetailDto>> getDetailT3List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.getDetailT3List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 조회 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 조회", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 조회")
	@PostMapping(value = "/v1.0/getMasterT4List")
	public ApiResult<List<IbTirdPartyMastResT4Dto>> getMasterT4List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.getMasterT4List(dto));
	}
	
	///////////////////////////////////////////////////////////////////////////////// 저장 ///////////////////////////////////////////////////////////////////////////////
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 저장", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_단가마스터_탭 저장")
	@PostMapping(value = "/v1.0/saveMasterT1List")
	public ApiResult<Object> saveMasterT1List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.saveMasterT1List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 저장", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_협력사관리_탭 저장")
	@PostMapping(value = "/v1.0/saveMasterT2List")
	public ApiResult<Object> saveMasterT2List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.saveMasterT2List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 강제확정", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_검수관리_탭 강제확정")
	@PostMapping(value = "/v1.0/saveMasterT3List")
	public ApiResult<Object> saveMasterT3List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.saveMasterT3List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 저장 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 저장", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 저장")
	@PostMapping(value = "/v1.0/saveMasterT4List")
	public ApiResult<Object> saveMasterT4List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.saveMasterT4List(dto));
	}
	
	/**
	 * @description : 정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 수정 Method
	 * @issues :
	 * 
	 *         <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.09.25 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 수정", description = "정산 > 3PL 수수료 > 일배 물류대행 파트너사 수수료 정산_정산관리_탭 수정")
	@PostMapping(value = "/v1.0/updateMasterT4List")
	public ApiResult<Object> updateMasterT4List(@RequestBody IbTirdPartyMastReqDto dto) {
		return ApiResult.createResult(ibTirdPartyMastService.updateMasterT4List(dto));
	}

}
