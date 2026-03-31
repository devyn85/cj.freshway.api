package cjfw.wms.kp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.wms.kp.dto.KpKxCloseDocPopupReqDto;
import cjfw.wms.kp.dto.KpKxCloseDocPopupResDto;
import cjfw.wms.kp.dto.KpKxCloseDocPopupTransactionResDto;
import cjfw.wms.kp.service.KpKxCloseDocPopupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net) 
 * @date : 2025.10.24 
 * @description : KX문서정보 팝업 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.12  JiSooKim (jskim14@cj.net) 생성 </pre>
 */

@Tag(name = "ADMIN > 모니터링 > KX문서정보 팝업")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/kp/kxCloseDocPopup")
public class KpKxCloseDocPopupController {
	private final KpKxCloseDocPopupService cmLoginHistoryPopupService;
	
	
	/** 
	 * @description : KX문서정보 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation (summary = "KX문서정보 조회", description = "KX문서정보 조회")
	@GetMapping(value = "/v1.0/getDocinfo")
	public ApiResult <KpKxCloseDocPopupResDto> getDocinfo(KpKxCloseDocPopupReqDto cmPopupReqDto) {
		return ApiResult.createResult(cmLoginHistoryPopupService.getDocinfo(cmPopupReqDto));
	}
	
	   /** 
     * @description : 재고처리현황 조회
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
     */
    @Operation (summary = "재고처리현황 조회", description = "재고처리현황 조회")
    @GetMapping(value = "/v1.0/getTransactionList")
    public ApiResult <List<KpKxCloseDocPopupTransactionResDto>> getTransactionList(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return ApiResult.createResult(cmLoginHistoryPopupService.getTransactionList(cmPopupReqDto));
    }
    
    
    @Operation (summary = "재고처리현황 조회", description = "재고처리현황 조회")
    @GetMapping(value = "/v1.0/getDocumentDetailForDocno")
    public ApiResult <List<KpKxCloseDocPopupTransactionResDto>> getDocumentDetailForDocno(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return ApiResult.createResult(cmLoginHistoryPopupService.getDocumentDetailForDocno(cmPopupReqDto));
    }
    
    @Operation (summary = "재고처리현황 조회", description = "재고처리현황 조회")
    @GetMapping(value = "/v1.0/getDocumentModifyDetailForDocno")
    public ApiResult <List<KpKxCloseDocPopupTransactionResDto>> getDocumentModifyDetailForDocno(KpKxCloseDocPopupReqDto cmPopupReqDto) {
        return ApiResult.createResult(cmLoginHistoryPopupService.getDocumentModifyDetailForDocno(cmPopupReqDto));
    }
	/** 
	 * @description : KX실적현황 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation (summary = "KX실적현황", description = "KX실적현황")
	@GetMapping(value = "/v1.0/getDocumentKx")
	public ApiResult<List<KpKxCloseDocPopupResDto>> getDocumentKx(KpKxCloseDocPopupReqDto cmPopupReqDto) {
		// return ApiResult.createResult(cmLoginHistoryPopupService.getDocumentKx(cmPopupReqDto));
		return ApiResult.createResult(cmLoginHistoryPopupService.getDocumentKx2(cmPopupReqDto));
	}
	
	/** 
	 * @description : KX실적현황 조회 (TCS 소유권처리상태)
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.12  JiSooKim (jskim14@cj.net)       생성
	 */
	@Operation (summary = "KX실적현황", description = "TCS 소유권처리상태")
	@GetMapping(value = "/v1.0/getKxCloseDocPopupTCS")
	public ApiResult<List<KpKxCloseDocPopupResDto>> getKxCloseDocPopupTCS(KpKxCloseDocPopupReqDto cmPopupReqDto) {
		// return ApiResult.createResult(cmLoginHistoryPopupService.getKxCloseDocPopupTCS(cmPopupReqDto));
		return ApiResult.createResult(cmLoginHistoryPopupService.getKxCloseDocPopupTCS2(cmPopupReqDto));
	}
}
