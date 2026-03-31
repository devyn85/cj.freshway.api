package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.dataaccess.largedata.LargeExcel;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.tm.dto.TmDispatchListDownloadDto;
import cjfw.wms.tm.dto.TmManualDispatchDownloadReqDto;
import cjfw.wms.tm.dto.TmSetDispatchReqDto;
import cjfw.wms.tm.handler.TmDispatchExcelDataRowHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.27
 * @description : TM 수동배차 다운로드 Service (거래처 필터링 지원)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDispatchDownloadService {

    private final CmCommonService cmCommonService;
    private final CommonDao commonDao;

    private static final String SERVICEID_PREFIX = "tmDispatchDownloadService.";

    // TODO: 운영환경에서는 SPTM_INPLAN 사용, 테스트환경에서는 SPTM_INPLAN_WM 사용
    // private static final String PACKAGE_NAME = "SPTM_INPLAN";
    private static final String PACKAGE_NAME = "SPTM_INPLAN";  // 테스트용WM
    private static final String PROCEDURE_COMMAND = "CREATION_WD";

    /**
     * WD_PLAN → TM_INPLAN 프로시저 호출
     * WD_INPLAN/DP_INPLAN/RT_INPLAN → TM_INPLAN 복사/갱신
     *
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @param dccode 물류센터 코드
     * @param storerkey 고객사 코드
     */
    public void orderListToDispatchList(
        String deliveryDate,
        String dccode,
        String storerkey
    ) {
        try {
            log.info("TM_INPLAN 생성 프로시저 호출 시작 - dccode: {}, deliveryDate: {}, storerkey: {}",
                dccode, deliveryDate, storerkey);

            // 프로시저 호출용 DTO 생성
            TmSetDispatchReqDto procRequest = new TmSetDispatchReqDto();

            // 프로시저 파라미터 설정
            ProcedureParametersFactory.initParamDto(procRequest, procRequest, PACKAGE_NAME);
            procRequest.setAvc_COMMAND(PROCEDURE_COMMAND);
            procRequest.setAvc_DCCODE(dccode);
            procRequest.setPackagename(PACKAGE_NAME);
            procRequest.setDccode(dccode);
            procRequest.setDeliveryDate(deliveryDate);
            procRequest.setStorerkey(storerkey);

            String[] keyList = { "DELIVERYDT", "DCCODE", "STORERKEY" };
            Object[] valueList = { deliveryDate, dccode, storerkey };

            procRequest.setAvc_REQUESTMSG(
                ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList)
            );

            // 프로시저 호출
            int rv = cmCommonService.saveProcedure(procRequest);

            // 결과 확인
            String resultCode = StringUtil.nvl(procRequest.getResultCode());
            String resultMessage = StringUtil.nvl(procRequest.getResultMessage());
            String returnMessage = StringUtil.nvl(procRequest.getReturnMessage());

            log.info("TM_INPLAN 생성 프로시저 호출 완료 - rv: {}, resultCode: {}, resultMessage: {}, returnMessage: {}",
                rv, resultCode, resultMessage, returnMessage);

            // 프로시저 실패 시 예외 발생 (resultCode "0" = 성공)
            if (!"0".equals(resultCode)) {
                throw new RuntimeException("TM_INPLAN 생성 실패 (resultCode: " + resultCode + "): " + resultMessage);
            }

        } catch (Exception e) {
            log.error("TM_INPLAN 생성 프로시저 호출 실패", e);
            throw new RuntimeException("TM_INPLAN 생성 실패: " + e.getMessage(), e);
        }
    }

    /**
     * TM_INPLAN에서 배차 목록 조회 (좌표 갱신 및 다운로드용)
     *
     * @param reqDto 요청 DTO (배송일자, 배송유형, 거래처 필터링)
     * @return 배차 목록 (TM_INPLAN 조회 결과)
     */
    public List<TmDispatchListDownloadDto> getListFromTmInPlan(TmManualDispatchDownloadReqDto reqDto) {
        log.info("TM_INPLAN 배차 목록 조회 시작 - deliveryDate: {}, deliveryType: {}, 거래처수: {}",
            reqDto.getDeliveryDate(),
            reqDto.getDeliveryTypeCode(),
            reqDto.getTruthcustkeyList() != null ? reqDto.getTruthcustkeyList().size() : 0);

        // TM_INPLAN 조회
        List<TmDispatchListDownloadDto> dispatchList =
            commonDao.selectList(SERVICEID_PREFIX + "getDispatchListForDownload", reqDto);

        log.info("TM_INPLAN 배차 목록 조회 완료 - 조회 건수: {}건",
            dispatchList != null ? dispatchList.size() : 0);

        return dispatchList;
    }

    /**
     * 배차 목록 Excel 데이터 채우기 (LargeExcel + 커스텀 Handler 사용)
     *
     * @param reqDto 요청 DTO (배송일자, 배송유형, 거래처 필터링)
     * @param largeExcel LargeExcel 객체
     */
    public void createDispatchExcelData(
        TmManualDispatchDownloadReqDto reqDto,
        LargeExcel largeExcel
    ) {
        log.info("배차 목록 Excel 데이터 채우기 시작 - deliveryDate: {}, deliveryType: {}, 거래처수: {}",
            reqDto.getDeliveryDate(),
            reqDto.getDeliveryTypeCode(),
            reqDto.getTruthcustkeyList() != null ? reqDto.getTruthcustkeyList().size() : 0);

        // 1. 커스텀 Handler 생성 (TmDispatchListDownloadDto 직접 사용)
        TmDispatchExcelDataRowHandler<TmDispatchListDownloadDto> handler =
            new TmDispatchExcelDataRowHandler<>(largeExcel);

        // 2. MyBatis 스트리밍 조회 (커스텀 Handler 사용)
        commonDao.selectWithRowHandler(
            SERVICEID_PREFIX + "getDispatchListForDownload",
            reqDto,
            handler
        );

        log.info("배차 목록 Excel 데이터 채우기 완료 - {}건", handler.getResultCnt());
    }

}
