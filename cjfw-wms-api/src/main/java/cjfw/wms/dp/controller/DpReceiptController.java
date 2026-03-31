package cjfw.wms.dp.controller;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.dp.dto.DpReceiptReqDto;
import cjfw.wms.dp.service.DpReceiptService;
import cjfw.wms.wd.dto.WdShipmentReqDto;
import cjfw.wms.wd.dto.WdShipmentResBillYnDto;
import com.sap.conn.jco.JCoException;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.08.22
 * @description : 입고확정처리 Controller Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Tag(name = "DpReceiptController API", description = "DpReceiptController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"api/dp/receipt", "ltx/dp/receipt"})
public class DpReceiptController {
    private final UserContext userContext;
    private final DpReceiptService dpReceiptService;

    /**
     * @description : 입고확정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 조회 List", description = "입고확정처리 조회 List")
    @PostMapping(value = "/v1.0/getMasterList")
    public ApiResult<List> getMasterList(@RequestBody DpReceiptReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpReceiptService.getMasterList(reqDto));
    }

    /**
     * @description : 입고확정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 상세 조회 List", description = "입고확정처리 상세 조회 List")
    @PostMapping(value = "/v1.0/getDetailList")
    public ApiResult<List> getDetailList(@RequestBody DpReceiptReqDto reqDto) {
        return ApiResult.createResult(dpReceiptService.getDetailList(reqDto));
    }

    /**
     * @description : 입고확정처리 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리2 조회 List", description = "입고확정처리2 조회 List")
    @PostMapping(value = "/v1.0/getMasterList2")
    public ApiResult<List> getMasterList2(@RequestBody DpReceiptReqDto reqDto) {
        return ApiResult.createResult(dpReceiptService.getMasterList2(reqDto));
    }

    /**
     * @description : 입고확정처리 상세 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 상세2 조회 List", description = "입고확정처리 상세2 조회 List")
    @PostMapping(value = "/v1.0/getDetailList2")
    public ApiResult<List> getDetailList2(@RequestBody DpReceiptReqDto reqDto) {
        return ApiResult.createResult(dpReceiptService.getDetailList2(reqDto));
    }

    /**
     * @description : 입고확정처리 저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "저장", description = "저장")
    @PostMapping(value = "/v1.0/saveMaster")
    public ApiResult<String> saveMaster(@RequestBody DpReceiptReqDto dto) throws Exception {
        return ApiResult.createResult(dpReceiptService.saveMaster(dto));
    }

    /**
     * @description : 입고확정처리 대상확정
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "대상확정", description = "대상확정")
    @PostMapping(value = "/v1.0/saveDetail")
    public ApiResult<String> saveDetail(@RequestBody DpReceiptReqDto dto) throws Exception {
        return ApiResult.createResult(dpReceiptService.saveDetail(dto));
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 조회 List", description = "입고확정처리 조회 List")
    @PostMapping(value = "/v1.0/getExcelList")
    public void getExcelList(@RequestBody DpReceiptReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        // 헤더 칼럼 설정 (주석 기반)
        String[] headerColumns = new String[]{
            "입고전표번호", "입고일자", "구매번호", "구매문서생성일자", "구매유형", "협력사코드", "협력사명", "물류센터", "창고", "창고명", "품목번호", "상품코드", "상품명칭", "원산국", "플랜트", "저장유무", "저장조건", "진행상태", "박스입수", "예정수량(EA)", "예정수량(BOX)", "PLT환산", "구매단위", "구매수량", "구매중량", "입고검수량", "입고수량", "결품수량", "로케이션", "입고로케이션", "제조일자", "소비일자", "소비기간(잔여/전체)", "소비기한임박여부", "기존소비기한"
        };

        // Data 매핑 DTO 칼럼 설정 (dataField 기반)
        // Data 매핑 DTO 칼럼 설정 (헤더 순서에 맞게)
        String[] dataColumns = new String[]{
            "slipno", "slipdt", "docno", "docdt", "ordertypeName", "fromCustkey", "fromCustname", "dccode", "organize", "organizename", "docline", "sku", "skuname", "countryoforigin", "plantDescr", "channelName", "storagetypename", "statusname", "qtyperbox", "openqtyEa", "openqtyBox", "pltqty", "uom", "orderqty", "orderweight", "inspectqty", "confirmqty", "shortageqty", "loc", "toloc", "manufacturedt", "expiredt", "durationTerm", "neardurationyn", "lastlottable01", "lottable01", "duration", "durationtype",};

        LargeExcel largeExcel = new LargeExcel();
        largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
        largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
        largeExcel.setExcelFileName("입고확정처리_일반상품"); // 엑셀 파일명
        LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
        dpReceiptService.getExcelList(reqDto, largeExcel);
        largeExcel.setUserContext(userContext);
        excelUtil.makeExcelDownload(request, response, largeExcel);
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 조회 List", description = "입고확정처리 조회 List")
    @PostMapping(value = "/v1.0/getExcelList2")
    public void getExcelList2(@RequestBody DpReceiptReqDto reqDto, HttpServletRequest request, HttpServletResponse response) {
        // 헤더 칼럼 설정 (주석 기반)
        String[] headerColumns = new String[]{
            "입고전표번호", "입고일자", "구매번호", "구매문서생성일자", "구매유형", "협력사코드", "협력사명", "물류센터", "창고", "창고명", "품목번호", "상품코드", "상품명칭", "원산국", "플랜트", "저장유무", "저장조건", "진행상태", "박스입수", "예정수량(EA)", "예정수량(BOX)", "PLT환산", "구매단위", "구매수량", "구매중량", "입고검수량", "입고수량", "결품수량", "로케이션", "입고로케이션", "제조일자", "소비일자", "소비기간(잔여/전체)", "소비기한임박여부", "기존소비기한"
        };

        // Data 매핑 DTO 칼럼 설정 (dataField 기반)
        // Data 매핑 DTO 칼럼 설정 (헤더 순서에 맞게)
        String[] dataColumns = new String[]{
            "slipno", "slipdt", "docno", "docdt", "ordertypeName", "fromCustkey", "fromCustname", "dccode", "organize", "organizename", "docline", "sku", "skuname", "countryoforigin", "plantDescr", "channelName", "storagetypename", "statusname", "qtyperbox", "openqtyEa", "openqtyBox", "pltqty", "uom", "orderqty", "orderweight", "inspectqty", "confirmqty", "shortageqty", "loc", "toloc", "manufacturedt", "expiredt", "durationTerm", "neardurationyn", "lastlottable01"
        };

        LargeExcel largeExcel = new LargeExcel();
        largeExcel.setHeaderColumns(headerColumns); // 헤더 컬럼
        largeExcel.setDataColumns(dataColumns); // 컬럼 매핑 키
        largeExcel.setExcelFileName("입고확정처리_이력상품"); // 엑셀 파일명
        LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);
        dpReceiptService.getExcelList(reqDto, largeExcel);
        largeExcel.setUserContext(userContext);
        excelUtil.makeExcelDownload(request, response, largeExcel);
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 조회 List", description = "입고확정처리 조회 List")
    @PostMapping(value = "/v1.0/getPrintList")
    public ApiResult<List> getPrintList(@RequestBody DpReceiptReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpReceiptService.getPrintList(reqDto));
    }

    /**
     * @description : 입고확정처리 엑셀 조회 List Method
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "입고확정처리 조회 List", description = "입고확정처리 조회 List")
    @PostMapping(value = "/v1.0/getPrintList2")
    public ApiResult<List> getPrintList2(@RequestBody DpReceiptReqDto reqDto, HttpServletRequest req) throws IOException {
        return ApiResult.createResult(dpReceiptService.getPrintList2(reqDto));
    }


    /**
     * @description : 입고확정처리 방단저장
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "방단저장", description = "방단저장")
    @PostMapping(value = "/v1.0/saveBoxPlt")
    public ApiResult<String> saveBoxPlt(@RequestBody DpReceiptReqDto dto) throws Exception {
        return ApiResult.createResult(dpReceiptService.saveBoxPlt(dto));
    }

    /**
     * @description : 입고확정처리 세금계산서발행여부
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */
    @Operation(summary = "세금계산서 발행여부 조회", description = "세금계산서 발행여부 조회")
    @PostMapping(value = "/v1.0/getBillYn")
    public ApiResult<WdShipmentResBillYnDto> getBillYn(@RequestBody WdShipmentReqDto dto) throws JCoException {
        return ApiResult.createResult(dpReceiptService.getBillYn(dto));
    }

    /**
     * @description : 입고확정처리 역STO
     * @issues :
     *
     * <pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.08.22 KimDongHyeon (tirran123@cj.net) 생성
     *         </pre>
     */

    @Operation(summary = "입고확정처리 역STO", description = "입고확정처리 역STO")
    @PostMapping(value = "/v1.0/reverseSto")
    public ApiResult<Map> reverseSto(@RequestBody DpReceiptReqDto dto) throws Exception {
        return ApiResult.createResult(dpReceiptService.reverseSto(dto));
    }
}
