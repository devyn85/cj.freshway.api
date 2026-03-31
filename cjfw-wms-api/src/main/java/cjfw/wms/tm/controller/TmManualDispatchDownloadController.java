package cjfw.wms.tm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.dataaccess.largedata.LargeExcelUtil;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.tm.dto.CoordinateFailInfo;
import cjfw.wms.tm.dto.CoordinateUpdateResult;
import cjfw.wms.tm.dto.TmDispatchExcelColumn;
import cjfw.wms.tm.dto.TmDispatchListDownloadDto;
import cjfw.wms.tm.dto.TmManualDispatchDownloadReqDto;
import cjfw.wms.tm.dto.TmOrderListReqDto;
import cjfw.wms.tm.service.TmDispatchDownloadService;
import cjfw.wms.tm.service.TmOrderListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 수동배차 다운로드 Controller (거래처 필터링 지원)
 */
@Tag(name = "TM > 배차관리", description = "TM 수동배차 다운로드 및 좌표 검증")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping({"/api/tm/dispatch/manual", "/ltx/tm/dispatch/manual"})
public class TmManualDispatchDownloadController {

    private final UserContext userContext;
    private final TmDispatchDownloadService tmDispatchDownloadService;
    private static final String PAKAGE_NAME = "SPTM_INPLAN";
    private final CmCommonService cmCommonService;
    private final TmOrderListService tmOrderListService;
    /**
     * 수동배차 좌표 검증
     */
    @Operation(summary = "수동배차 좌표 검증")
    @PostMapping(value = "/validation/geo")
    public ApiResult<CoordinateUpdateResult> updateManualDispatchCoordinates(
        @Valid @RequestBody TmManualDispatchDownloadReqDto reqDto
    ) {
        log.info("배차목록 좌표 갱신 요청 - deliveryDate={}, deliveryTypeCode={}, 거래처수={}",
                reqDto.getDeliveryDate(),
                reqDto.getDeliveryTypeCode(),
                reqDto.getTruthcustkeyList() != null ? reqDto.getTruthcustkeyList().size() : 0);

        // UserContext에서 storerkey 가져오기
        String storerkey = userContext.getStorerkey();
        String dccode = reqDto.getGDccode();
        String deliveryDate = reqDto.getDeliveryDate();

        log.info("프로시저 호출 파라미터 - dccode: {}, deliveryDate: {}, storerkey: {}",
                dccode, deliveryDate, storerkey);

        // 1. WD_PLAN → TM_INPLAN 프로시저 호출
        tmDispatchDownloadService.orderListToDispatchList(deliveryDate, dccode, storerkey);

        // 2. TM_INPLAN에서 배차 목록 조회
        List<TmDispatchListDownloadDto> dispatchList = tmDispatchDownloadService.getListFromTmInPlan(reqDto);

        log.info("배차 목록 조회 완료 - 조회 건수: {}건", dispatchList != null ? dispatchList.size() : 0);

        // 3. 좌표 갱신
        TmOrderListReqDto dto = new TmOrderListReqDto();    
        dto.setDeliveryDate(reqDto.getDeliveryDate());
        dto.setDeliveryType(reqDto.getDeliveryTypeCode());
        dto.setGDccode(reqDto.getGDccode());
        
        //CoordinateUpdateResult result = tmDispatchDownloadService.updateCoordinates(dispatchList);   기존 좌표 업드이트   로직  주석
        final int failCount = tmOrderListService.updateBulkCustDlvInfoPoint(dto);    //   공통좌표 업데이트 로직직으로 
        CoordinateUpdateResult result = new CoordinateUpdateResult(10,10,failCount, new ArrayList<CoordinateFailInfo>());
        

        return ApiResult.createResult(result);
    }

    /**
     * 수동배차 데이터 다운로드 (거래처 필터링)
     */
    @Operation(summary = "수동배차 데이터 다운로드 (Excel)")
    @PostMapping(value = "/download/excel")
    public void downloadManualDispatchExcel(
        @Valid @RequestBody TmManualDispatchDownloadReqDto reqDto,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        log.info("배차목록 엑셀 다운로드 요청 - deliveryDate={}, deliveryTypeCode={}, 거래처수={}",
                reqDto.getDeliveryDate(),
                reqDto.getDeliveryTypeCode(),
                reqDto.getTruthcustkeyList() != null ? reqDto.getTruthcustkeyList().size() : 0);

        // 다운로드 전 CREATION_WD 프로시저 호출 (WD_PLAN → TM_INPLAN 생성)
        String storerkey = userContext.getStorerkey();
        String dccode = reqDto.getGDccode();
        tmDispatchDownloadService.orderListToDispatchList(reqDto.getDeliveryDate(), dccode, storerkey);

        // 1. 헤더 정의 (enum에서 자동 생성)
        String[] headerColumns = Arrays.stream(TmDispatchExcelColumn.values())
            .map(TmDispatchExcelColumn::getKoreanName)
            .toArray(String[]::new);

        // 2. 데이터 컬럼 정의 (enum에서 자동 생성)
        String[] dataColumns = Arrays.stream(TmDispatchExcelColumn.values())
            .map(TmDispatchExcelColumn::getColumnName)
            .toArray(String[]::new);

        // 3. 파일명 생성
        String fileName = String.format("배차목록_%s_%s",
                reqDto.getDeliveryDate(), reqDto.getDeliveryType());

        // 4. LargeExcel 객체 생성 및 설정
        LargeExcel largeExcel = new LargeExcel();
        largeExcel.setHeaderColumns(headerColumns);
        largeExcel.setDataColumns(dataColumns);
        largeExcel.setExcelFileName(fileName);

        // 5. LargeExcelUtil 생성 (헤더 자동 생성)
        LargeExcelUtil excelUtil = new LargeExcelUtil(largeExcel);

 
        // 6. Service 호출 (데이터 채우기 - 커스텀 Handler 사용)
        tmDispatchDownloadService.createDispatchExcelData(reqDto, largeExcel);

        // 7. UserContext 설정 (DRM 처리용)
        largeExcel.setUserContext(userContext);

        // 8. Excel 다운로드
        excelUtil.makeExcelDownload(request, response, largeExcel);

        log.info("배차목록 엑셀 다운로드 완료 - 파일명: {}", fileName);
    }
}
