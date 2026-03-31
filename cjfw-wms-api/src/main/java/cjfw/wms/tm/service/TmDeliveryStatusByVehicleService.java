package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleCarReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleCarResDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleMonthReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleReqDto;
import cjfw.wms.tm.dto.TmDeliveryStatusByVehicleResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : System
 * @date : 2025.01.XX
 * @description : 차량별 당일 배송현황 조회 서비스
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.XX System      생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmDeliveryStatusByVehicleService {

    private transient static final String SERVICEID_PREFIX = "tmDeliveryStatusByVehicleService.";

    private final CommonDao commonDao;

    /**
     * @description : 차량별 당일 배송현황 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.XX System      생성 </pre>
     */
    public List<TmDeliveryStatusByVehicleResDto> getDeliveryStatusByVehicle(TmDeliveryStatusByVehicleReqDto reqDto) {
        log.info("차량별 당일 배송현황 조회 요청: {}", reqDto);
        
        List<TmDeliveryStatusByVehicleResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDeliveryStatusByVehicle", reqDto);
        
        log.info("차량별 당일 배송현황 조회 결과: {} 건", result.size());
        
        return result;
    }

    /**
     * @description : 차량별 당월 배송현황 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.XX System      생성 </pre>
     */
    public List<TmDeliveryStatusByVehicleResDto> getDeliveryStatusByVehicleMonth(TmDeliveryStatusByVehicleMonthReqDto reqDto) {
        log.info("차량별 당월 배송현황 조회 요청: {}", reqDto);
        
        List<TmDeliveryStatusByVehicleResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDeliveryStatusByVehicleMonth", reqDto);
        
        log.info("차량별 당월 배송현황 조회 결과: {} 건", result.size());
        
        return result;
    }

    /**
     * @description : 계약유형별 차량 목록 조회
     * @issues :<pre>
     * -----------------------------------------------------------
     * DATE       AUTHOR                MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2025.01.XX System      생성 </pre>
     */
    public List<TmDeliveryStatusByVehicleCarResDto> getDeliveryStatusByVehicleCar(TmDeliveryStatusByVehicleCarReqDto reqDto) {
        log.info("계약유형별 차량 목록 조회 요청: {}", reqDto);
        
        List<TmDeliveryStatusByVehicleCarResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDeliveryStatusByVehicleCar", reqDto);
        
        log.info("계약유형별 차량 목록 조회 결과: {} 건", result.size());
        
        return result;
    }

}

