package cjfw.wms.tm.service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmDispatchListByCarResDto;
import cjfw.wms.tm.dto.tempMonitor.*;
import cjfw.wms.tm.util.PaginationUtil;
import cjfw.wms.tm.util.TmPlanCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System Generated
 * @date : 2025.01.27
 * @description : 온도 모니터링 기능을 구현한 Service Class
 * @issues :
 * -----------------------------------------------------------
 * DATE AUTHOR MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.27 System Generated 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmLocationMonitorSummaryService {

    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    public static final String SERVICEID_PREFIX = "tmLocationMonitorSummary.";

    private final CommonDao commonDao;

    private final UserContext userContext;

    /**
     * @description : 차량별 온도 모니터링 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    public Page<TmTempMonitorByCarResDto> getTempMonitorByCar(TmTempMonitorByCarReqDto reqDto) {
        Page<TmDispatchListByCarResDto> page = PaginationUtil.createPage(
                reqDto.getPageNum(),
                reqDto.getListCount()
        );
        Page<TmTempMonitorByCarResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getTempMonitorByCar", reqDto, page);

        // 기사명 마스킹 처리 (첫글자와 마지막글자 제외 중간 마스킹)
        if (result != null && result.getList() != null) {
            result.getList().forEach(dto -> dto.setDrivername(TmPlanCommon.maskDriverName(dto.getDrivername())));
        }

        return result;
    }

    /**
     * @description : 온도 그래프 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    public List<TmTempGraphResDto> getTempGraph(TmTempGraphReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getTempGraph", reqDto);
    }

    /**
     * @description : 온도 모니터링 상세 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */

    public TmTempMonitorDescWrapResDto getTempMonitorDesc(TmTempMonitorDescReqDto reqDto) {
        Page<TmTempMonitorDescResDto> page = PaginationUtil.createPage(
                reqDto.getPageNum(),
                reqDto.getListCount()
        );
        Page<TmTempMonitorDescResDto> pageRes;
        pageRes = commonDao.selectPageList(SERVICEID_PREFIX + "getTempMonitorDeparrDesc", reqDto, page);

        List<TmTempMonitorDescResDto> list = pageRes.getList();
        list.forEach(item -> {
            item.setTimeUnit(reqDto.getTimeUnit());
            item.setUserStop(false);
        });

        // 상태별 건수 조회 (매 페이지 실행)
        Map<String, Object> countMap = commonDao.selectOne(
                SERVICEID_PREFIX + "getTempMonitorDeparrDescStatusCount", reqDto);

        TmTempStatusCountDto refrigCount;
        TmTempStatusCountDto freezeCount;

        if(countMap == null) {
            refrigCount = TmTempStatusCountDto.builder()
                    .nomlCnt(0)
                    .outCnt(0)
                    .naCnt(0)
                    .build();

            freezeCount = TmTempStatusCountDto.builder()
                    .nomlCnt(0)
                    .outCnt(0)
                    .naCnt(0)
                    .build();
        } else {
            refrigCount = TmTempStatusCountDto.builder()
                    .nomlCnt(toInt(countMap.get("REFRIG_NOML_CNT")))
                    .outCnt(toInt(countMap.get("REFRIG_OUT_CNT")))
                    .naCnt(toInt(countMap.get("REFRIG_NA_CNT")))
                    .build();

            freezeCount = TmTempStatusCountDto.builder()
                    .nomlCnt(toInt(countMap.get("FREEZE_NOML_CNT")))
                    .outCnt(toInt(countMap.get("FREEZE_OUT_CNT")))
                    .naCnt(toInt(countMap.get("FREEZE_NA_CNT")))
                    .build();
        }

        return TmTempMonitorDescWrapResDto.builder()
                .totalCount(pageRes.getTotalCount())
                .list(list)
                .refrig(refrigCount)
                .freeze(freezeCount)
                .build();
    }

    private int toInt(Object value) {
        if (value == null) return 0;
        if (value instanceof BigDecimal) return ((BigDecimal) value).intValue();
        if (value instanceof Number) return ((Number) value).intValue();
        return 0;
    }

    /**
     * @description : 온도 모니터링 요약 조회 기능을 구현한 Method
     * @issues :
     * -----------------------------------------------------------
     * DATE AUTHOR MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.27 System Generated 생성
     */
    public List<TmTempMonitorSummaryResDto> getTempMonitorSummary(TmTempMonitorSummaryReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getTempMonitorSummary", reqDto);
    }

}
