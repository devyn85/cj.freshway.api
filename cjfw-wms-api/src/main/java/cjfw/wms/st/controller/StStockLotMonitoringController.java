package cjfw.wms.st.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.st.dto.StStockLotMonitoringDurationResDto;
import cjfw.wms.st.dto.StStockLotMonitoringReqDto;
import cjfw.wms.st.dto.StStockLotMonitoringResDto;
import cjfw.wms.st.dto.StStockLotMonitoringStoragetypeResDto;
import cjfw.wms.st.service.StStockLotMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net) 
 * @date : 2025.10.14
 * @description : 재고 > 재고현황 > 유통기한점검 Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Tag(name = "재고 > 재고현황 > 유통기한점검", description = "유통기한점검")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/st/stockLotMonitoring", "ltx/st/stockLotMonitoring"} )
public class StStockLotMonitoringController {

	private final StStockLotMonitoringService stStockLotMonitoringService;
	private final UserContext userContext;

	/**
	 * @description : 유통기한 점검 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "유통기한 점검 조회 (목록)", description = "유통기한 점검 조회 (목록)")
	@PostMapping(value = "/v1.0/getMasterList")
	public ApiResult<List<StStockLotMonitoringResDto>> getMasterList(@RequestBody @Valid StStockLotMonitoringReqDto dto) {
		return ApiResult.createResult(stStockLotMonitoringService.getMasterList(dto));
	}
	
	/**
	 * @description : 유통기한 점검 요약장표 소비기한 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "유통기한 점검 조회 (목록)", description = "유통기한 점검 조회 (목록)")
	@PostMapping(value = "/v1.0/getDurationList")
	public ApiResult<List<StStockLotMonitoringDurationResDto>> getDurationList(@RequestBody @Valid StStockLotMonitoringReqDto dto) {
		return ApiResult.createResult(stStockLotMonitoringService.getDurationList(dto));
	}
	
	/**
	 * @description : 유통기한 점검 요약장표 저장조건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.02.06 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	@Operation(summary = "유통기한 점검 조회 (목록)", description = "유통기한 점검 조회 (목록)")
	@PostMapping(value = "/v1.0/getStoragetypeList")
	public ApiResult<List<StStockLotMonitoringStoragetypeResDto>> getStoragetypeList(@RequestBody @Valid StStockLotMonitoringReqDto dto) {
		return ApiResult.createResult(stStockLotMonitoringService.getStoragetypeList(dto));
	}
	
	/**
	 * 
	 * @description : 유통기한 점검 엑셀 다운로드
	 * @issues      :
	 */
	@PostMapping(value = "/v1.0/saveLargeDataExcel")
	public void saveLargeDataExcel(@RequestBody StStockLotMonitoringReqDto dto, HttpServletRequest request, HttpServletResponse response) {
		String[] headerColumns = new String[]{
		    "물류센터", "창고", "재고위치", "재고속성", "피킹존", "로케이션", "상품코드", "상품명칭", 
		    "저장조건", "박스입수", "재고정보(단위)", "재고정보(현재고수량)", "소비기한임박여부", "기준일(소비,제조)", 
		    "기준일(구분)", "제조일자", "소비일자", "소비기한(잔여/전체)", "소비기한 잔여(%)",
		    "소비기한 잔여(구분)", "수급담당",
		    "주문량(D-1월)", "주문량(D-2월)", "주문량(D-3월)", 
		    "주문건수(D-1월)", "주문건수(D-2월)", "주문건수(D-3월)", 
		    "출고량(D-1월)", "출고량(D-2월)", "출고량(D-3월)",
		    "일평균", "소진예상시점", "소진가능여부"
		};

		String[] dataColumns = new String[]{
		    "dccode", "organize", "stocktype", "stockgrade", "zone", "loc", "sku", "skuname", 
		    "storagetype", "qtyperbox", "uom", "qty", "neardurationyn", "lottable01", 
		    "durationtypeName", "manufacturedt", "expiredt", "durationTerm", "persent",
		    "percentDiv", "buyername",
		    "shipday1w", "shipday2w", "shipday3w", 
		    "ordcnt1w", "ordcnt2w", "ordcnt3w", 
		    "shipqty1w", "shipqty2w", "shipqty3w",
		    "avg30", "exhaustiondt", "exhaustionchk"		    
		};
		
	    LargeExcel largeExcel = new LargeExcel();
	    largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
	    largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
	    largeExcel.setExcelFileName("상품소비기한현황"); // 엑셀 파일명
	    LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
	    stStockLotMonitoringService.getMasterListExcel(dto, largeExcel);
	    largeExcel.setUserContext(userContext);
	    excelUtil.makeExcelDownload(request, response, largeExcel);
	}
}