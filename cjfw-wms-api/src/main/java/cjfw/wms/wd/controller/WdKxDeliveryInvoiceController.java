package cjfw.wms.wd.controller;

import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.common.util.MaskingUtil;
import cjfw.wms.dev.dto.DevPilot02ReqDto;
import cjfw.wms.st.dto.StDisuseRequestCenterResDto;
import cjfw.wms.wd.dto.WdDeliveryLabelForceReqDto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI01Dto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI02Dto;
import cjfw.wms.wd.dto.WdKxDeliveryAPI03Dto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoiceReqDto;
import cjfw.wms.wd.dto.WdKxDeliveryInvoiceResDto;
import cjfw.wms.wd.dto.WdQuickResAPI01Dto;
import cjfw.wms.wd.service.WdDeliveryLabelForceService;
import cjfw.wms.wd.service.WdKxDeliveryInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : sss
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 sss  생성 </pre>
 */
@Tag(name = "WdKxDeliveryInvoice", description = "택배송장발행(온라인)(출고 > 출고작업 > 택배송장발행(온라인))")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/wd/kxdeliveryinvoice")
public class WdKxDeliveryInvoiceController {
	
	/** 택배송장발행(온라인) Service */
	private final WdKxDeliveryInvoiceService wdKxDeliveryInvoiceService;
	
	
	/** 출고 > 출고 > 배송 라벨 출력(예외 기준 적용) Service */
	private final WdDeliveryLabelForceService wdDeliveryLabelForceService;
	
	
	/////////////////////////////////tab1//////////////////////////////////////////////

	/**
	 * @description : 택배송장발행(온라인) - 주문 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.29 sss  생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 주문 목록 조회", description = "택배송장발행(온라인) - 주문 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdKxDeliveryInvoiceResDto>> getMasterList(@Valid @RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.getMasterList(dto));
	}
	
	/**
	 * @description : 택배송장발행(온라인) - 주문내역 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 주문내역 저장", description = "택배송장발행(온라인) - 주문내역 저장")
	@PostMapping(value = "/v1.0/saveMasterList01")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterList01(@RequestBody WdKxDeliveryInvoiceReqDto reqDto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterList01(reqDto));
	}	
	
	
	/**
	 * @description : 택배송장발행(온라인) - N배송 분리 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - N배송 분리 처리", description = "택배송장발행(온라인) - N배송 분리 처리")
	@PostMapping(value = "/v1.0/saveMasterNDeliveryDivide")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterNDeliveryDivide(@RequestBody WdKxDeliveryInvoiceReqDto reqDto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterNDeliveryDivide(reqDto));
	}	
		
	/**
	 * @description : 택배송장발행(온라인) - 접수 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 접수 처리", description = "택배송장발행(온라인) - 접수")
	@PostMapping(value = "/v1.0/saveMasterReceipt")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterReceipt(@RequestBody WdKxDeliveryInvoiceReqDto reqDto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterReceipt(reqDto));
	}	
		
	
	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////tab2//////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	/**
	 * @description : 택배송장발행(온라인) - N배송 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *   생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - N배송 목록 조회", description = "택배송장발행(온라인) - N배송 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList2")
	public ApiResult<List<WdKxDeliveryInvoiceResDto>> getMasterList2(@Valid @RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.getMasterList2(dto));
	}
	
	/**
	 * @description : 택배송장발행(온라인) - 송장분리 처리	
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 송장분리 처리", description = "택배송장발행(온라인) - 송장분리 처리")
	@PostMapping(value = "/v1.0/saveMasterInvoiceDivide")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterInvoiceDivide(@RequestBody WdKxDeliveryInvoiceReqDto reqDto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterInvoiceDivide(reqDto));
	}		
	
	/**
	 * @description : 택배송장발행(온라인) - N배송 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - N배송 저장", description = "택배송장발행(온라인) - N배송 저장")
	@PostMapping(value = "/v1.0/saveMasterList02")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterList02(@RequestBody WdKxDeliveryInvoiceReqDto reqDto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterList02(reqDto));
	}	
		
	
	/**
	 * @description : 택배송장발행(온라인) - 택배 접수 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 택배 접수 처리", description = "택배송장발행(온라인) - 택배 접수 처리")
	@PostMapping(value = "/v1.0/saveMasterInvoiceReceipt")
	public ApiResult<WdKxDeliveryInvoiceReqDto> saveMasterInvoiceReceipt(@RequestBody WdKxDeliveryInvoiceReqDto paramDto) {
		WdKxDeliveryInvoiceReqDto resultDto = new WdKxDeliveryInvoiceReqDto();
        String tokenNum = "";
        int processCnt = 0; // 처리 건수
        // 파라미터 위변조 적용(paramDto->reqDto)
		WdKxDeliveryInvoiceReqDto reqDto = ModelMapperUtil.map(paramDto, WdKxDeliveryInvoiceReqDto.class);		
		
	    /*********************************************************
	     * 2.택배	주문접수 API 호출 
	     ********************************************************/ 	
		WdKxDeliveryAPI01Dto wdKxDeliveryAPI01Dto = null;
		List<WdKxDeliveryInvoiceResDto> saveList = reqDto.getSaveList();
		for (WdKxDeliveryInvoiceResDto dto : saveList) {
			// 토큰조회 API 호출
			wdKxDeliveryAPI01Dto = new WdKxDeliveryAPI01Dto();
			wdKxDeliveryAPI01Dto.setKxCustId(dto.getKxCustkey()); // 고객ID
			tokenNum = wdKxDeliveryInvoiceService.getAccessToken(wdKxDeliveryAPI01Dto); // 일반/N배송/반품 등 ID별 토큰이므로 매번 조회해야 함
			
			if(tokenNum == null || tokenNum.equals("")) {
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"퀵주문접수 API[토큰발급에 실패]"}) ); // {0} 처리 시 문제가 발생했습니다.
			}
			
			// 배송정보 API 호출
			dto.setTokenNum(tokenNum);			
			WdKxDeliveryAPI02Dto rowDto = (WdKxDeliveryAPI02Dto)wdKxDeliveryInvoiceService.getOrderRequest01(dto).get(0);
			try {
				log.info("▶상품정보 조회 파라미터1: {}", MaskingUtil.maskLog(rowDto.toString()));
				WdKxDeliveryAPI02Dto apiDto = wdKxDeliveryInvoiceService.regBook(rowDto); 
				
				if("S".equals(apiDto.getResultCd())) {
					processCnt++;
				} 			
				
			} catch (Exception e) { 
				log.error("{}", e);
				throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_055", new String[]{"퀵주문접수 API"}));
			} 			
		}
		resultDto.setProcessCnt(processCnt); // 처리 건수
		return ApiResult.createResult(resultDto); 
	}	
	
	
	
	/**
	 * @description : 택배송장발행(온라인) -운송장출력	
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 운송장출력", description = "택배송장발행(온라인) -운송장출력")
	@PostMapping(value = "/v1.0/printMasterList")
	public ApiResult<WdKxDeliveryInvoiceReqDto> printMasterList(@RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.printMasterList(dto));
	}			
	

	/**
	 * @description : 택배송장발행(온라인) - 일반택배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *  sss  생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 일반택배 목록 조회", description = "택배송장발행(온라인) - 일반택배 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList3")
	public ApiResult<List<WdKxDeliveryInvoiceResDto>> getMasterList3(@Valid @RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.getMasterList3(dto));
	}

	/**
	 * @description : 택배송장발행(온라인) - 반품택배 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 *  sss  생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 반품택배 목록 조회", description = "택배송장발행(온라인) - 반품택배 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList4")
	public ApiResult<List<WdKxDeliveryInvoiceResDto>> getMasterList4(@Valid @RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.getMasterList4(dto));
	}
	/**
	 * @description : 택배송장발행(온라인) - 택배기준 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss  생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 택배기준 목록 조회", description = "택배송장발행(온라인) - 택배기준 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList5")
	public ApiResult<List<WdKxDeliveryInvoiceResDto>> getMasterList5(@Valid @RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.getMasterList5(dto));
	}

	/**
	 * @description : 택배송장발행(온라인) - 택배기준 저장
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.26 sss 생성 </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 택배기준 저장", description = "택배송장발행(온라인) - 택배기준 저장")
	@PostMapping(value = "/v1.0/saveMasterList5")
	public ApiResult<String> saveMasterList(@RequestBody WdKxDeliveryInvoiceReqDto dto) {
		return ApiResult.createResult(wdKxDeliveryInvoiceService.saveMasterList5(dto));
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////tab5//////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////	
	/**
	 * @description : 출고 > 출고 > 배송 라벨 출력(예외 기준 적용)_기준정보_탭 저장 Method
	 * @issues :
	 *
	 * <pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.17 KimDongHan (liop0123@cj.net) 생성
	 *         </pre>
	 */
	@Operation(summary = "택배송장발행(온라인) - 정열기준정보 저장", description = "택배송장발행(온라인) - 정열기준정보 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<String> saveMasterList(@RequestBody WdDeliveryLabelForceReqDto dto) throws Exception {
	    return ApiResult.createResult(wdDeliveryLabelForceService.saveMasterList(dto));
	}
}
