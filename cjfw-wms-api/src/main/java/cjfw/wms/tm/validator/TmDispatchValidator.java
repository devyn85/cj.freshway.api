package cjfw.wms.tm.validator;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmDispatchExcelColumn;
import cjfw.wms.tm.dto.TmDispatchExcelDto;
import cjfw.wms.tm.dto.TmDispatchRowDto;
import cjfw.wms.tm.dto.VehicleInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : han@wemeetmobility.com
 * @date : 2025.11.28
 * @description : TM 배차 업로드 검증 클래스
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TmDispatchValidator {

    private static final String SERVICEID_PREFIX = "tmDispatchUploadMapper.";

    private static final int IN_CLAUSE_CHUNK_SIZE = 1000;

    private final CommonDao commonDao;

    /**
     * 차량번호 검증
     *
     * @param excelDto Excel DTO
     * @param dccode 물류센터 코드
     * @return 검증 결과가 반영된 Excel DTO
     */
    public TmDispatchExcelDto validateVehicleInfo(TmDispatchExcelDto excelDto, String dccode) {
        // 1. 입력된 차량번호 목록 추출 (중복 제거)
        List<String> carnoList = excelDto.getRows().stream()
            .map(row -> {
                // CARNO 컬럼의 셀 값 찾기
                return row.getCells().stream()
                    .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.CARNO)
                    .findFirst()
                    .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                    .orElse(null);
            })
            .filter(carno -> carno != null && !carno.trim().isEmpty())  // null, 빈 값 제거
            .distinct()  // 중복 제거
            .toList();

        log.info("차량번호 목록 추출 완료 - 총 {}대", carnoList.size());

        // 2. DB에서 차량 정보 조회 (selectVehicleInfoByCarnoList)
        Map<String, Object> vehicleParams = new HashMap<>();
        vehicleParams.put("carnoList", carnoList);
        vehicleParams.put("dccode", dccode);

        @SuppressWarnings("unchecked")
        List<VehicleInfoDto> vehicleInfoList = (List<VehicleInfoDto>) (List<?>) commonDao.selectList(
            SERVICEID_PREFIX + "selectVehicleInfoByCarnoList",
            vehicleParams
        );

        log.info("차량 정보 조회 완료 - 조회된 차량: {}대 / 입력된 차량: {}대",
            vehicleInfoList.size(), carnoList.size());

        // 3. 존재하지 않는 차량번호 검증
        // 조회된 차량번호 Set 생성 (빠른 검색을 위해)
        Set<String> validCarnoSet = vehicleInfoList.stream()
            .map(VehicleInfoDto::getCarno)
            .collect(Collectors.toSet());

        // 각 행의 차량번호를 검증하여 존재하지 않으면 오류 추가
        int invalidVehicleCount = 0;
        for (TmDispatchRowDto row : excelDto.getRows()) {
            // 해당 행의 차량번호 추출
            String carno = row.getCells().stream()
                .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.CARNO)
                .findFirst()
                .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                .orElse(null);

            // 차량번호가 비어있지 않고, DB에 존재하지 않으면 오류 추가
            if (carno != null && !carno.trim().isEmpty() && !validCarnoSet.contains(carno)) {
                excelDto.addValidationError(row.getRowNumber(), "차량번호", "존재하지 않는 차량번호입니다");
                invalidVehicleCount++;
            }
        }

        if (invalidVehicleCount > 0) {
            log.warn("존재하지 않는 차량번호 {}건 발견", invalidVehicleCount);
        }

        return excelDto;
    }
    
    
    /**
     * 차량번호 검증
     *
     * @param excelDto Excel DTO
     * @param dccode 물류센터 코드
     * @return 검증 결과가 반영된 Excel DTO
     */
    public TmDispatchExcelDto validateHolidayInfo(TmDispatchExcelDto excelDto, String dccode) {
        // 1. 입력된 차량번호 목록 추출 (중복 제거)
        List<String> carnoList = excelDto.getRows().stream()
            .map(row -> {
                // CARNO 컬럼의 셀 값 찾기
                return row.getCells().stream()
                    .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.CARNO)
                    .findFirst()
                    .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                    .orElse(null);
            })
            .filter(carno -> carno != null && !carno.trim().isEmpty())  // null, 빈 값 제거
            .distinct()  // 중복 제거
            .toList();

        log.info("차량번호 목록 추출 완료 - 총 {}대", carnoList.size());

        // 2. DB에서 차량 정보 조회 (selectVehicleInfoByCarnoList)
        Map<String, Object> vehicleParams = new HashMap<>();
        vehicleParams.put("carnoList", carnoList);
        vehicleParams.put("dccode", dccode);

        @SuppressWarnings("unchecked")
        List<VehicleInfoDto> vehicleInfoList = (List<VehicleInfoDto>) (List<?>) commonDao.selectList(
            SERVICEID_PREFIX + "selectHolidayInfo",
            vehicleParams
        );

        log.info("차량 정보 조회 완료 - 조회된 차량: {}대 / 입력된 차량: {}대",
            vehicleInfoList.size(), carnoList.size());

        // 조회된 차량번호 Set 생성 (빠른 검색을 위해)
        Set<String> validCarnoSet = vehicleInfoList.stream()
            .map(VehicleInfoDto::getCarno)
            .collect(Collectors.toSet());

        // 각 행의 차량번호를 검증하여 존재하지 않으면 오류 추가
        int invalidVehicleCount = 0;
        for (TmDispatchRowDto row : excelDto.getRows()) {
            // 해당 행의 차량번호 추출
            String carno = row.getCells().stream()
                .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.CARNO)
                .findFirst()
                .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                .orElse(null);
            // 차량번호가 비어있지 않으면  휴무차량   ATTEND_CD 10 =  휴무
            if (carno != null && !carno.trim().isEmpty() && validCarnoSet.contains(carno)) {
                excelDto.addValidationError(row.getRowNumber(), "차량번호", "휴무차량입니다");
                invalidVehicleCount++;
            }
        }

        if (invalidVehicleCount > 0) {
            log.warn("휴무 차량번호 {}건 발견", invalidVehicleCount);
        }

        return excelDto;
    }
    
    
    

    /**
     * TM_INPLAN 존재 여부 검증
     *
     * @param excelDto Excel DTO
     * @param dccode 물류센터 코드
     * @param deliveryDate 배송일자 (YYYYMMDD)
     * @param storerkey 고객사 코드
     * @return 검증 결과가 반영된 Excel DTO
     */
    public TmDispatchExcelDto validateTmInplan(
        TmDispatchExcelDto excelDto,
        String dccode,
        String deliveryDate,
        String storerkey
    ) {
        // 1. truthCustKey 목록 추출 (중복 제거)
        List<String> truthCustKeyList = excelDto.getRows().stream()
            .map(row -> {
                // TRUTH_CUST_KEY 컬럼의 셀 값 찾기
                return row.getCells().stream()
                    .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.TRUTH_CUST_KEY)
                    .findFirst()
                    .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                    .orElse(null);
            })
            .filter(truthCustKey -> truthCustKey != null && !truthCustKey.trim().isEmpty())  // null, 빈 값 제거
            .distinct()  // 중복 제거
            .toList();

        log.info("실착지코드 목록 추출 완료 - 총 {}건", truthCustKeyList.size());

        // 2. DB에서 TM_INPLAN 조회 (IN 절 1000개 제한 대응: 청크 단위 조회 후 결과 합침)
        List<Map<String, Object>> inplanList = new ArrayList<>();
        for (int i = 0; i < truthCustKeyList.size(); i += IN_CLAUSE_CHUNK_SIZE) {
            List<String> chunk = truthCustKeyList.subList(
                    i, Math.min(i + IN_CLAUSE_CHUNK_SIZE, truthCustKeyList.size()));
            Map<String, Object> inplanParams = new HashMap<>();
            inplanParams.put("truthCustKeyList", chunk);
            inplanParams.put("dccode", dccode);
            inplanParams.put("deliveryDate", deliveryDate);
            inplanParams.put("storerkey", storerkey);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> chunkResult = (List<Map<String, Object>>) (List<?>) commonDao.selectList(
                    SERVICEID_PREFIX + "selectTmInplanByTruthCustKeyList",
                    inplanParams
            );
            inplanList.addAll(chunkResult);
        }

        log.info("TM_INPLAN 조회 완료 - 조회된 레코드: {}건 / 입력된 실착지: {}건",
            inplanList.size(), truthCustKeyList.size());

        // 3. 존재하는 truthCustKey Set 생성 (빠른 검색을 위해)
        // Oracle은 alias를 대문자로 변환하므로 TRUTHCUSTKEY로 읽어야 함
        Set<String> validTruthCustKeySet = inplanList.stream()
            .map(record -> (String) record.get("TRUTHCUSTKEY"))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

        // 4. 각 행의 truthCustKey를 검증하여 존재하지 않으면 오류 추가
        int invalidInplanCount = 0;
        for (TmDispatchRowDto row : excelDto.getRows()) {
            // 해당 행의 truthCustKey 추출
            String truthCustKey = row.getCells().stream()
                .filter(cell -> cell.getColumn() == TmDispatchExcelColumn.TRUTH_CUST_KEY)
                .findFirst()
                .map(cell -> cell.getValue() != null ? cell.getValue().toString() : null)
                .orElse(null);

            // truthCustKey가 비어있지 않고, DB에 존재하지 않으면 오류 추가
            if (truthCustKey != null && !truthCustKey.trim().isEmpty() && !validTruthCustKeySet.contains(truthCustKey)) {
                excelDto.addValidationError(row.getRowNumber(), "실착지코드", "존재하지 않는 배차 데이터입니다");
                invalidInplanCount++;
            }
        }

        if (invalidInplanCount > 0) {
            log.warn("존재하지 않는 TM_INPLAN 레코드 {}건 발견", invalidInplanCount);
        }

        return excelDto;
    }
}
