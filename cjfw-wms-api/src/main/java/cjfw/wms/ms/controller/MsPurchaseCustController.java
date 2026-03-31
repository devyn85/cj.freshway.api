package cjfw.wms.ms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.ApiResult;
import cjfw.wms.cm.dto.CmCodeReqDto;
import cjfw.wms.ms.dto.MsPurchaseCustDetailResDto;
import cjfw.wms.ms.dto.MsPurchaseCustReqDto;
import cjfw.wms.ms.dto.MsPurchaseCustResDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetSTOResDto;
import cjfw.wms.ms.service.MsPurchaseCustService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import cjfw.core.model.UserContext;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.24
 * @description : 기준정보 > 상품기준정보 > 수발주정보 목록 조회 및 저장 Controller
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Tag(name = "기준정보 > 상품기준정보 > 수발주정보", description = "수발주정보 목록 조회 및 저장")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/ms/purchaseCust", "ltx/ms/purchaseCust"})
public class MsPurchaseCustController {

	private final MsPurchaseCustService msPurchaseCustService;
	private final UserContext userContext;
	
	/**
	 * 
	 * @description : 수발주정보 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주정보 목록 조회", description = "수발주정보 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<MsPurchaseCustResDto>> getMasterList(@RequestBody MsPurchaseCustReqDto dto) {
		log.info("Received dto: {}", dto);
		return ApiResult.createResult(msPurchaseCustService.getMasterList(dto));
	}
	
	/**
	 * 
	 * @description : 수발주정보 상세 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주정보 상세 목록 조회", description = "수발주정보 상세 목록 조회")
	@PostMapping(value = "/v1.0/getDetailList")
	public ApiResult<List<MsPurchaseCustDetailResDto>> getDetailList(@RequestBody MsPurchaseCustReqDto dto) {
		log.info("Received dto: {}", dto);
		return ApiResult.createResult(msPurchaseCustService.getDetailList(dto));
	}
		
	/**
	 * @description : 수발주정보 목록 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주정보 목록 저장", description = "수발주정보 목록 저장")
	@PostMapping(value = "/v1.0/saveMasterList")
	public ApiResult<Object> saveMasterList(@RequestBody List<MsPurchaseCustReqDto> dto) {
		return ApiResult.createResult(msPurchaseCustService.saveMasterList(dto));
	}
	
	/**
	 * @description : 수발주정보 목록 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.19        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주정보 목록 저장", description = "수발주정보 목록 저장")
	@PostMapping(value = "/v1.0/deleteMasterList")
	public ApiResult<Object> deleteMasterList(@RequestBody List<MsPurchaseCustReqDto> dto) {
		return ApiResult.createResult(msPurchaseCustService.deleteMasterList(dto));
	}
	
	/**
	 * 
	 * @description : 엑셀업로드 저장
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.10.01        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀업로드 저장", description = "엑셀업로드 저장")
	@PostMapping(value = "/v1.0/saveExcelList")
	public ApiResult<Object> saveExcelList(@RequestBody List<MsPurchaseCustReqDto> dto) {
		return ApiResult.createResult(msPurchaseCustService.saveExcelList(dto));
	}
	
	/**
	 * @description : 엑셀 업로드 유효성 검사(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.02        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "엑셀 업로드 유효성 검사(목록)", description = "엑셀 업로드 유효성 검사(목록)")
	@PostMapping(value = "/v1.0/getValidateExcelList")
	public ApiResult<List<MsPurchaseCustResDto>> getValidateExcelList(@RequestBody @Valid List<MsPurchaseCustReqDto> dtoList) {
		return ApiResult.createResult(msPurchaseCustService.getValidateExcelList(dtoList));
	}
	
	/**
	 * 
	 * @description : 수발주정보 상세 목록 검색 조회
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.07.24        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@Operation(summary = "수발주정보 엑셀 업로드 목록 조회", description = "수발주정보 엑셀 업로드 목록 조회")
	@PostMapping(value = "/v1.0/getExcelUploadList")
	public ApiResult<List<MsPurchaseCustResDto>> getExcelUploadList(@RequestBody MsPurchaseCustReqDto dto) {
		log.info("Received dto: {}", dto);
		return ApiResult.createResult(msPurchaseCustService.getExcelUploadList(dto));
	}
	
	/**
	 * 
	 * @description : 수발주정보 대용량 엑셀 다운로드
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.12.29        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	@PostMapping(value = "/v1.0/saveLargeDataExcel")
	public void saveLargeDataExcel(@RequestBody MsPurchaseCustReqDto dto, HttpServletRequest request, HttpServletResponse response) {
		String[] headerColumns = new String[]{
			    "발주구분", "자동생성유형", "직송그룹", "수급센터", "상품코드", "상품명칭", "플랜트", "수발주연결여부", 
			    "저장유무", "식별번호유무", "비정량여부", "사용여부", "시작일자", "종료일자", "저장조건", "구매처유형", 
			    "구매처", "구매처명", "실공급 협력사코드", "실공급 협력사명", "실공급센터", "경유지조직", "경유지조직명", 
			    "수급담당ID", "수급담당", "구매그룹", "영업조직", "발주수량(BOX)","발주수량(EA)", "박스입수",
			    "단위", "PLT단수", "PLT변환값(환산값)", "PLT1단박스수", "안전계수", 
			    "리드타임", "MOQ(상품/BOX)", "MOQ(상품/PLT)", "MOQ(협력사/BOX)", 
			    "MOQ(협력사/PLT)", "주문간격", "수기재발주점", "수기목표재고수량", "토요일입고가능여부", 
			    "발주요일전체","월", "화", "수", "목", "금", "토", "일", 
			    "반품여부", "마감기일", "마감시간",
			    "대체상품", "특이사항", "예외입고품의", "참조정보1", "참조정보2", "참조정보3", "참조정보4", "할당정보", "할당내용", 
			    "산정기간", "월", "화", "수", "목", "금", "토", "일",
			    "1차", "2차", "3차",
			    "엑셀업로드 결과","양산 마감기일","양산 마감시간","PLT변환값","리드타임2",
			    "소진후중단", "CJ코드", "키맨번호", "등록자", "등록일시", "수정자", "수정일시"
			};

			String[] dataColumns = new String[]{
			    "purchaseType", "reference05", "deliveryType", "dcCode", "sku", "skuName", "plantDescr", "qtyYn", 
			    "channel", "serialYn", "line01", "controlType", "fromDate", "toDate", "storageType", "custType", 
			    "custKey", "custName", "slaveCustKey", "slaveCustName", "route", "routeOrganize", "routeOrganizeNm", 
			    "buyerKey", "buyerKeyNm", "purchaseGroup", "saleGroupCd", "orderQtyUnit","orderQtyUnitEA","qtyPerBox",
			    "uom", "layerPerPlt", "pltChange", "boxPerLayer", "coefficientSafety", 
			    "leadTime", "moqSku", "moqSkuPlt", "moqVender", 
			    "moqVenderPlt", "purInterval", "editReorderPoint", "editStockGoal", "reference04", 
			    "allYn", "monYn", "tueYn", "wedYn", "thuYn", "friYn", "satYn", "sunYn", 
			    "returnType", "closeDay", "closeHour",
			    "alterSku", "spclNoteYn", "excptDpApprYn", "reference01", "reference02", "reference03", "reference06", "stopStatus", "stopInfo", 
			    "autoExecDay", "autoMonYn", "autoTueYn", "autoWedYn", "autoThuYn", "autoFriYn", "autoSatYn", "autoSunYn",
			    "batchTimePeriod1", "batchTimePeriod2", "batchTimePeriod3",
			    "errMsg", "tempCloseDay", "tempCloseTime", "boxPerPlt", "leadTime2",
			    "exhaustionStopYn", "custSku", "reference07", "addWhoNm", "addDate", "editWhoNm", "editDate"
			};
	    LargeExcel largeExcel = new LargeExcel();
	    largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
	    largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
	    largeExcel.setExcelFileName("수발주정보"); // 엑셀 파일명
	    LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
	    msPurchaseCustService.getMasterListExcel(dto, largeExcel);
	    largeExcel.setUserContext(userContext);
	    excelUtil.makeExcelDownload(request, response, largeExcel);
	}
	
	/**
	 * @description : 상세 코드 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06  (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "상세 코드 저장", description = "상세 코드 저장")
	@PostMapping(value = "/v1.0/saveCmDtlCode")
	public ApiResult<String> saveCmDtlCode(@RequestBody @Valid CmCodeReqDto cmCodeReqDto) {
		return ApiResult.createResult(msPurchaseCustService.saveCmDtlCode(cmCodeReqDto));
	}
}