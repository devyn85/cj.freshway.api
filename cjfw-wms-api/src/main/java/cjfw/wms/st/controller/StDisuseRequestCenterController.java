package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StDisuseRequestCenterReqDto;
import cjfw.wms.st.dto.StDisuseRequestCenterResApprovalDto;
import cjfw.wms.st.dto.StDisuseRequestCenterResDto;
import cjfw.wms.st.dto.StDisuseRequestCenterResProcessDto;
import cjfw.wms.st.dto.StDisuseRequestCenterResResultDto;
import cjfw.wms.st.service.StDisuseRequestCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 재고폐기요청/처리 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StDisuseRequestCenterController API", description = "StDisuseRequestCenterController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/disuseRequestCenter")
public class StDisuseRequestCenterController {
	private final StDisuseRequestCenterService stDisuseRequestCenterService;

	/**
	 * @description : 재고폐기요청/처리 등록 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리-등록 Tab 조회 List", description = "재고폐기요청/처리-등록 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StDisuseRequestCenterResDto>> getTab1MasterList(@Valid @RequestBody StDisuseRequestCenterReqDto reqDto) {
		return ApiResult.createResult(stDisuseRequestCenterService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 재고폐기요청/처리 요청 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리-요청 조회 List", description = "재고폐기요청/처리-요청 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StDisuseRequestCenterResResultDto>> getTab2MasterList(@Valid @RequestBody StDisuseRequestCenterReqDto reqDto) {
		return ApiResult.createResult(stDisuseRequestCenterService.getTab2MasterList(reqDto));
	}
	
	/**
	 * @description : 재고폐기요청/처리 처리결과 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리-처리결과 조회 List", description = "재고폐기요청/처리-처리결과 조회 List")
	@PostMapping(value = "/v1.0/getTab3MasterResultList")
	public ApiResult<List<StDisuseRequestCenterResResultDto>> getTab3MasterResultList(@Valid @RequestBody StDisuseRequestCenterReqDto reqDto) {
		return ApiResult.createResult(stDisuseRequestCenterService.getTab3MasterResultList(reqDto));
	}	
	
	/**
	 * @description : 재고폐기요청/처리 처리결과 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리-전자결재 조회 List", description = "재고폐기요청/처리-처리결과 조회 List")
	@PostMapping(value = "/v1.0/getTab4MasterApprovalList")
	public ApiResult<List<StDisuseRequestCenterResApprovalDto>> getTab4MasterApprovalList(@Valid @RequestBody StDisuseRequestCenterReqDto reqDto) {
		return ApiResult.createResult(stDisuseRequestCenterService.getTab4MasterApprovalList(reqDto));
	}		
	
	/**
	 * @description : 재고폐기요청/처리 저장 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리 저장", description = "재고폐기요청/처리 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public Object saveMasterList(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 제일제당(STO) 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.27 공두경 (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "제일제당(STO) 처리", description = "제일제당(STO) 처리")
	@PostMapping(value = "/v1.0/saveCJSTO")
	public Object saveCJSTO(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.saveCJSTO(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 재고폐기요청/처리 확정 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리 확정", description = "재고폐기요청/처리 확정")
	@PostMapping(value = "/v1.0/confirmMasterList")
	public Object confirmMasterList(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.confirmMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 재고폐기요청/처리 삭제 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리 삭제", description = "재고폐기요청/처리 삭제")
	@PostMapping(value = "/v1.0/deleteMasterList")
	public Object deleteMasterList(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.deleteMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 재고폐기요청/처리 전자결재 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리 전자결재", description = "재고폐기요청/처리 전자결재")
	@PostMapping(value = "/v1.0/saveElectApproval")
	public Object saveElectApproval(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.saveElectApproval(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	/**
	 * @description : 재고폐기요청/처리 처리결과 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리-폐기처리 조회 List", description = "재고폐기요청/처리-폐기처리 조회 List")
	@PostMapping(value = "/v1.0/getTab5MasterProcessList")
	public ApiResult<List<StDisuseRequestCenterResProcessDto>> getTab5MasterProcessList(@Valid @RequestBody StDisuseRequestCenterReqDto reqDto) {
		return ApiResult.createResult(stDisuseRequestCenterService.getTab5MasterProcessList(reqDto));
	}		
	
	/**
	 * @description : 재고폐기요청/처리 폐기처리 처리 Method
	 * @issues :
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.31 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고폐기요청/처리 폐기처리", description = "재고폐기요청/처리 폐기처리")
	@PostMapping(value = "/v1.0/processMasterList")
	public Object processMasterList(@RequestBody StDisuseRequestCenterReqDto reqDto) throws Exception{
		return ApiResult.createResult(stDisuseRequestCenterService.processMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
}
