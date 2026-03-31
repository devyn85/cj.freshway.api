package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.st.dto.StInquiryResultReqDto;
import cjfw.wms.st.dto.StInquiryResultResDto;
import cjfw.wms.st.dto.StInquiryResultResSkuDto;
import cjfw.wms.st.service.StInquiryResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 조사지시현황 Controller Class
 * @issues :
 * 
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Tag(name = "StInquiryResultController API", description = "StInquiryResultController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/st/stInquiryResult")
public class StInquiryResultController {
	private final StInquiryResultService stStInquiryResultService;

	/**
	 * @description : 조사지시현황 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "조사지시현황 조회 List", description = "조사지시현황 로케이션별 조회 List")
	@PostMapping(value = "/v1.0/getTab1MasterList")
	public ApiResult<List<StInquiryResultResDto>> getTab1MasterList(@RequestBody StInquiryResultReqDto reqDto) {
		return ApiResult.createResult(stStInquiryResultService.getTab1MasterList(reqDto));
	}

	/**
	 * @description : 조사지시현황 상세 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre> 2번과 1번 공통사용 서비스 2번 삭제
	 */
	@Operation(summary = "조사지시현황 상세 조회 List", description = "조사지시현황 상품별 조회 List")
	@PostMapping(value = "/v1.0/getTab2MasterList")
	public ApiResult<List<StInquiryResultResDto>> getTab2MasterList(@RequestBody StInquiryResultReqDto reqDto) {
		return ApiResult.createResult(stStInquiryResultService.getTab1MasterList(reqDto)); 
	}
	
	/**
	 * @description : 조사지시현황 seq 조회 List Method
	 * @issues :
	 * 
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.08.25 sss (kduimux@cj.net) 생성
	 * </pre>
	 */
	@Operation(summary = "조사지시현황 seq 조회", description = "조사지시현황 seq 조회")
	@PostMapping(value = "/v1.0/getSequenceNumber")
	public ApiResult<List<StInquiryResultResSkuDto>> getSequenceNumber(@RequestBody StInquiryResultReqDto reqDto) {
		return ApiResult.createResult(stStInquiryResultService.getSequenceNumber(reqDto));
	}
	
	
	/** @throws Exception 
	 * @description :조사지시현황 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "조사지시현황 목록 재지시", description = "조사지시현황 목록 재지시")
	@PostMapping(value="/v1.0/saveMasterList")
	public ApiResult<StInquiryResultReqDto> saveMasterList(@RequestBody StInquiryResultReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stStInquiryResultService.saveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
	
	
	/** @throws Exception 
	 * @description :조사지시현황 화면 조회 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "조사지시현황 예약재고조사(일일) 저장", description = "조사지시현황 예약재고조사(일일) 저장")
	@PostMapping(value="/v1.0/saveReserveMasterList")
	public ApiResult<StInquiryResultReqDto> saveReserveMasterList(@RequestBody StInquiryResultReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stStInquiryResultService.saveReserveMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}
	
	
	
	/** @throws Exception 
	 * @description :조사지시현황 화면 종료 Method
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.01 sss 생성 </pre>
	*/
	@Operation(summary = "조사지시현황 예약재고조사(일일) 종료", description = "조사지시현황 예약재고조사(일일) 종료")
	@PostMapping(value="/v1.0/saveCloseMasterList")
	public ApiResult<StInquiryResultReqDto> saveCloseMasterList(@RequestBody StInquiryResultReqDto reqDto) throws Exception  {
		return ApiResult.createResult(stStInquiryResultService.saveCloseMasterList(reqDto),"MSG.COM.SUC.003"); // 저장되었습니다. => UI에서 CmLoopTranPopup 사용 시 처리메세지 표시
	}		
}
