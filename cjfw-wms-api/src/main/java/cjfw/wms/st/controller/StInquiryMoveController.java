package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StInquiryMoveReqDto;
import cjfw.wms.st.dto.StInquiryMoveResDto;
import cjfw.wms.st.dto.StInquiryMoveResSkuDto;
import cjfw.wms.st.service.StInquiryMoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.11.24
 * @description : 재고조사결과처리 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.24 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StInquiryMoveController API", description = "StInquiryMoveController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stInquiryMove")
public class StInquiryMoveController {
	private final StInquiryMoveService stStInquiryMoveService;
	
	/**
	 * @description : 재고조사결과처리 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고조사결과처리 조회 List", description = "재고조사결과처리 조회 List")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StInquiryMoveResDto>> getMasterList(@RequestBody StInquiryMoveReqDto reqDto) {
		return ApiResult.createResult(stStInquiryMoveService.getMasterList(reqDto));
	}	

	/**
	 * @description : 재고조사결과처리 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고조사결과처리 상세1 조회 List", description = "재고조사결과처리 상세1 조회 List")
	@PostMapping(value = "/v1.0/getTab1DetailList")
	public ApiResult<List<StInquiryMoveResSkuDto>> getTab1DetailList(@RequestBody StInquiryMoveReqDto reqDto) {
		return ApiResult.createResult(stStInquiryMoveService.getTab1DetailList(reqDto));
	}

	/**
	 * @description : 재고조사결과처리 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.11.24 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "재고조사결과처리 상세2 조회 List", description = "재고조사결과처리 상세2 조회 List")
	@PostMapping(value = "/v1.0/getTab2DetailList")
	public ApiResult<List<StInquiryMoveResSkuDto>> getTab2DetailList(@RequestBody StInquiryMoveReqDto reqDto) {
		return ApiResult.createResult(stStInquiryMoveService.getTab2DetailList(reqDto));
	}
	
	/** @throws Exception 
	 * @description :재고조사결과처리 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "재고조사결과처리 저장", description = "재고조사결과처리 저장")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<StInquiryMoveReqDto> saveMasterList(@RequestBody StInquiryMoveReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stStInquiryMoveService.saveMasterList(reqDto));
	}
	
	
//	/** @throws Exception 
//	 * @description :재고조사결과처리 소비기한변경 처리 Method
//	 * @issues :<pre>
//	 * -----------------------------------------------------------
//	 * DATE       AUTHOR                MAJOR_ISSUE
//	 * -----------------------------------------------------------
//	 * 2025.07.01 sss 생성 </pre>
//	*/
//	@Operation(summary = "재고조사결과처리 소비기한변경 처리", description = "재고조사결과처리 소비기한변경 처리")
//	@PostMapping(value="/v1.0/saveMasterList01")
//	public ApiResult<StInquiryMoveReqDto> saveMasterList01(@RequestBody StInquiryMoveReqDto reqDto) throws Exception  {
//		return ApiResult.createResult(stStInquiryMoveService.saveMasterList01(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
//	}	
	
	/** @throws Exception 
	 * @description :재고조사결과처리 소비기한변경 처리 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "재고조사결과처리 소비기한변경/재고이동 처리", description = "재고조사결과처리 소비기한변경/재고이동 처리")
	@PostMapping(value="/v1.0/saveMasterListAll")
	public ApiResult<StInquiryMoveReqDto> saveMasterListAll(@RequestBody StInquiryMoveReqDto reqDto) throws Exception  {
		StInquiryMoveReqDto returnDto = stStInquiryMoveService.saveMasterListAll(reqDto);
		return ApiResult.createResult(returnDto,returnDto.getResultMessage()); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	
	/** @throws Exception 
	 * @description :재고조사결과처리 저장 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "재고조사결과처리 이동 처리", description = "재고조사결과처리 이동 처리")
	@PostMapping(value="/v1.0/saveMasterList02")
	public ApiResult<StInquiryMoveReqDto> saveMasterList02(@RequestBody StInquiryMoveReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stStInquiryMoveService.saveMasterList02(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}
	
	
}
