package cjfw.wms.dp.controller;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.ApiResult;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.dp.dto.DpInplanDetailResDto;
import cjfw.wms.dp.dto.DpInplanReqDto;
import cjfw.wms.dp.dto.DpInplanResDto;
import cjfw.wms.dp.service.DpInplanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : 공두경 (medstorm@cj.net)
 * @date : 2025.06.16
 * @description : 입고진행현황 Controller
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Tag(name = "DpInplan", description = "입고진행현황")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/dp/dpInplan", "ltx/dp/dpInplan"})
public class DpInplanController {
    private final UserContext userContext;
    private final DpInplanService dpInplanService;

    /**
     * @description : 입고진행현황 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
     */
    @Operation(summary = "입고진행현황 목록 조회", description = "입고진행현황 목록 조회")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List<DpInplanResDto>> getMasterList(@RequestBody DpInplanReqDto dpInplanReqDto, Page page) {


        return ApiResult.createResult(dpInplanService.getMasterList(dpInplanReqDto, page));
    }

    /**
     * @description : 입고진행현황 상세 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
     */
    @Operation(summary = "입고진행현황 상세 조회", description = "입고진행현황 상세 조회")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List<DpInplanDetailResDto>> getDetailList(@RequestBody DpInplanReqDto dpInplanReqDto, Page page) {


        return ApiResult.createResult(dpInplanService.getDetailList(dpInplanReqDto));
    }

    /**
     * @description : 입고진행현황 엑셀 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.06.16 공두경 (medstorm@cj.net) 생성 </pre>
     */
    @Operation(summary = "입고진행현황 엑셀 조회", description = "입고진행현황 엑셀 조회")
    @PostMapping(value = "/v1.0/getDataExcelList")
    public void getDataExcelList(@RequestBody DpInplanReqDto dpInplanReqDto, HttpServletRequest request, HttpServletResponse response) {
        // 헤더 칼럼 설정 (주석 기반)
        String[] headerColumns = new String[]{
            "입고전표번호", "구매유형", "입고일자", "협력사코드", "협력사명", "진행상태", "품목번호", "상품코드", "상품명칭", "플랜트", "저장유무", "저장조건", "이력관리기관", "박스입수", "구매단위", "구매수량", "발주수량", "결품수량", "입고예정량", "입고예정확정량", "입고검수량", "입고확정량", "입고중량"
        };

        // Data 매핑 DTO 칼럼 설정 (dataField 기반)
        // Data 매핑 DTO 칼럼 설정 (헤더 순서에 맞게)
        String[] dataColumns = new String[]{
            "slipno", "ordertypeName", "slipdt", "fromCustkey", "fromCustname", "statusname", "docline", "sku", "skuname", "plantDescr", "channelName", "storagetypeName", "serialtypeName", "qtyperbox", "uom", "orderqty", "purchaseQty", "shortageqty", "orderadjustqty", "openqty", "inspectqty", "confirmqty", "confirmweight"
        };

        LargeExcel largeExcel = new LargeExcel();
        largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
        largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
        largeExcel.setExcelFileName("입고진행현황"); // 엑셀 파일명
        LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
        dpInplanService.getDataExcelList(dpInplanReqDto, largeExcel);
        largeExcel.setUserContext(userContext);
        excelUtil.makeExcelDownload(request, response, largeExcel);
    }


}