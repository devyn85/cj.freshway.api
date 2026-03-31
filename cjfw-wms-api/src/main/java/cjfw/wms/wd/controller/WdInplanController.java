package cjfw.wms.wd.controller;

import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.wd.dto.WdInplanReqDto;
import cjfw.wms.wd.dto.WdInplanResDto;
import cjfw.wms.wd.service.WdInplanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.05.16
 * @description : 출고진행현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.05.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "WdInplan", description = "출고진행현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/wd/inplan", "ltx/wd/inplan"})
public class WdInplanController {

	private final WdInplanService wdInplanService;

	private final UserContext userContext;

	/**
	 * @description : 차량 목록 조회
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.12 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	@Operation(summary = "출고진행현황 목록 조회", description = "출고진행현황 목록 조회")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<WdInplanResDto>> getMasterList(@RequestBody WdInplanReqDto wdInplanReqDto, Page page) {
		return ApiResult.createResult(wdInplanService.getMasterList(wdInplanReqDto, page));
	}

	/**
	 * @throws Exception
	 * @description : 출고진행현황 상품제외 처리
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2025.05.09 SangSuSung(kduimux@cj.com) 생성 </pre>
	 */
	@Operation(summary = "센터상품속성 저장", description = "저장위치정보 저장")
	@PostMapping(value = "/v1.0/saveInquiry")
	public ApiResult<String> saveInquiry(@RequestBody WdInplanReqDto dto) throws Exception {
		return ApiResult.createResult(wdInplanService.saveInquiry(dto));
	}
	
	@PostMapping(value = "/v1.0/saveLargeDataExcel")
	public void saveLargeDataExcel(@RequestBody WdInplanReqDto dto, HttpServletRequest request, HttpServletResponse response) {
	 // 헤더 칼럼 설정 (주석 기반)
	    String[] headerColumns = new String[]{
	        "출고일자", "마감여부", "주문유형", "주문사유", "통합배송 주문유형", 
	        "주문번호", "물류센터", "창고", "영업조직", "사업장", 
	        "영업그룹", "영업경로(대)", "영업경로(중)", "영업경로(소)", "판매처코드", 
	        "판매처명", "관리처코드", "관리처명", "분할관리처코드", "분할관리처명", 
	        "진행상태", "POP번호", "회수위치", "차량번호", "품목번호", 
	        "상품코드", "상품명칭", "배송수단", "이력관리대상", "비정량여부", 
	        "플랜트", "경유지", "저장유무", "저장조건", "유통이력", 
	        "박스입수", "판매단위", "주문수량", "주문중량", "분배량", 
	        "피킹량", "출고검수량", "출고수량", "취소량", "출고중량", 
	        "사전주문조정의료여부", "우편번호", "실배송지", "등록자", "등록일자"
	    };

	    // Data 매핑 DTO 칼럼 설정 (dataField 기반)
	    // Data 매핑 DTO 칼럼 설정 (헤더 순서에 맞게)
	    String[] dataColumns = new String[]{
	        "slipdt", "closeyn", "ordertype", "sotype", "tplTypeNm", 
	        "docno", "dccode", "organize", "saleorganize", "saledepartment", 
	        "salegroup", "salecushrc1", "salecushrc2", "salecushrc3", "billtocustkey", 
	        "billtocustname", "toCustkey", "toCustname", "mngplcId", "mngplcName", 
	        "status", "deliverygroup", "loadplace", "carno", "docline", 
	        "sku", "skuname", "deliverytype", "serialyn", "line01", 
	        "plantDescr", "routeDescr", "channel", "storagetype", "serialtypename", 
	        "qtyperbox", "uom", "orderqty", "orderweight", "processqty", 
	        "workqty", "inspectqty", "confirmqty", "cancelqty", "confirmweight", 
	        "beforeShortageplanyn", "toZipcode", "toVataddress1", "addwho", "adddate"
	    };
	    
	    LargeExcel largeExcel = new LargeExcel();
	    largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
	    largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
	    largeExcel.setExcelFileName("출고진행현황"); // 엑셀 파일명
	    LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
	    wdInplanService.getCodeHeaderList(dto, largeExcel);
	    largeExcel.setUserContext(userContext);
	    excelUtil.makeExcelDownload(request, response, largeExcel);
	}
	
	// @RequestParam

	public static boolean isNull(String str) {
		return str == null || str.trim().isEmpty();
	}

		/**
	 * @description : 메인 물동량 및 라벨건수
	 * @issues :<pre>
	 * -----------------------------------------------------------
	 * DATE       AUTHOR                MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2026.02.20 KimDongHyeon (tirran123@cj.net) 생성
	 */
	@Operation(summary = "메인 물동량 및 라벨건수", description = "메인 물동량 및 라벨건수")
	@PostMapping(value = "/v1.0/getMainMasterList")
	public ApiResult<List<WdInplanResDto>> getMainMasterList(@RequestBody WdInplanReqDto wdInplanReqDto) {
		return ApiResult.createResult(wdInplanService.getMainMasterList(wdInplanReqDto));
	}
}