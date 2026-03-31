package cjfw.wms.tm.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.TmEngineRouteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;

@Slf4j
public class TmPlanCommon {

    public static String convertEta(String eta, String timeZoneString) {
        if (eta == null) return null;

        // 과학적 표기법(예: "1.768431571E09") 처리를 위해 Double.parseDouble 사용
        long epochSeconds = (long) Double.parseDouble(eta);
        return Instant.ofEpochSecond(epochSeconds)
                .atZone(ZoneId.of(timeZoneString))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 배차에 필요한 차량 기본 정보 확인
     * @param vehicles
     * @param planOption
     * @return
     */
    public static String validateVehicleInfo(List<TmVehicleInfoDto> vehicles,
                                             TmPlanOption planOption) {
        String message = null;
        for (TmVehicleInfoDto v : vehicles) {
            message = v.checkRequiredDataForDispatch(planOption);
            if (message != null) return v.getCarno() + " : " + message;
        }
        return null;
    }

    /**
     * 배차에 필요한 차량 용적량 확인
     * @param vehicles
     * @return
     */
    public static String validateVehicleWeight(List<TmVehicleInfoDto> vehicles) {
        String message = null;
        int count = 0;
        for (TmVehicleInfoDto v : vehicles) {
            Double maxWeight = Objects.requireNonNullElse(v.getMaxWeight(), 0.0);
            Double minWeight = Objects.requireNonNullElse(v.getMinWeight(), 0.0);
            if (!Double.isFinite(maxWeight) || !Double.isFinite(minWeight) || maxWeight <= 0.0 || minWeight <= 0.0) {
                if(message == null){
                    message =  v.getCarno() + " " ;
                }else{
                    count ++;
                    message =  v.getCarno() + " 외 "+ count + "건";
                }

            }
        }
        return message;
    }

    /**
     * 배차 요청 정보 차량의 상태 (다회전, 운행상태) 확인
     * @param vehicleMap
     * @param dto
     * @return
     */
    public static void validateVehicleStatus(Map<String, TmVehicleInfoDto> vehicleMap,
                                            TmPlanEtaOptimizeAutoReqDto dto,
                                            TmPlanOption planOption) {
        TmVehicleInfoDto vehicleInfo;
        for (TmPlanEtaVehicleReqDto vehicle : dto.getVehicles()) {
            if (CONTRACT_TYPE_TEMPORARY.equals(vehicle.getContractType())) continue;
            vehicleInfo = vehicleMap.get(vehicle.getCarno());
            if (vehicleInfo == null) {
                throw new UserHandleException("운행 상태가 아니거나 등록된 차량이 아닙니다. 차량 상태 확인 후 다시 시도해 주세요." + vehicle.getCarno());
            }

            if (ObjectUtils.isEmpty(planOption)) {
                if (vehicle.getRoundSeq() > 1 && !vehicleInfo.isPriorityYn()) {
                    throw new UserHandleException("2회전 설정되지 않은 차량입니다. 차량 2회전 설정 후 다시 시도해 주세요." + vehicle.getCarno());
                }
            }
            else if (!planOption.isOnMultiTurn() && vehicle.getRoundSeq() > 1) {
                throw new UserHandleException("단회전 배차 옵션이 설정되어 있어 차량 2회전 처리가 불가합니다. 다회전 옵션 설정 후 다시 시도해 주세요." + vehicle.getCarno());
            }
        }
    }

    public static int toInt(String value, int defaultValue) {
        if (ObjectUtils.isEmpty(value)) {
            return defaultValue;
        }
        try {
            return (int) Math.round(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            log.warn("숫자 변환 실패: '{}'. 기본값 {}을 사용합니다.", value, defaultValue);
            return defaultValue;
        }
    }

    /**
     * 문자열을 정수로 변환하며 최소값 1을 보장합니다.
     * - 반올림 적용
     * - 결과가 1 미만이면 1 반환
     *
     * @param value 변환할 문자열
     * @param defaultValue 변환 실패 시 기본값
     * @return 최소 1 이상의 정수값
     */
    public static int toIntMin1(String value, int defaultValue) {
        int result = toInt(value, defaultValue);
        return Math.max(result, 1);
    }

    /**
     * double 값을 정수로 변환하며 최소값 1을 보장합니다.
     * - 반올림 적용
     * - 결과가 1 미만이면 1 반환
     *
     * @param value 변환할 double 값
     * @return 최소 1 이상의 정수값
     */
    public static int toIntMin1(double value) {
        int result = (int) Math.round(value);
        return Math.max(result, 1);
    }

    /**
     * double 값을 정수로 변환하며 최소값 100백만을 보장합니다.
     * - 반올림 적용
     * - 결과가 0 미만이면 100만 반환
     *
     * @param value 변환할 double 값
     * @return 백만 정수값
     */
    public static int toIntMinCbm(double value) {
        int result = (int) Math.round(value);
        return (result == 0) ? 1000000 : result;
    }

    public static void validateReturnOrderInfo(List<TmSetDispatchReturnOrderResDto> returnOrders) {
        returnOrders.forEach(TmSetDispatchReturnOrderResDto::getLocation);
    }

    /**
     * 기사명 마스킹 처리
     * - null 또는 빈 문자열: 빈 문자열("") 반환
     * - 양쪽 공백 제거 후 처리
     * - 1글자: 마스킹 없이 그대로 반환
     * - 2글자: 뒷글자 마스킹 (예: "홍길" → "홍*")
     * - 3글자 이상: 첫글자와 마지막글자를 제외한 중간 마스킹 (예: "홍길동" → "홍*동")
     *
     * @param name 기사명
     * @return 마스킹된 기사명 (기사명이 없으면 빈 문자열)
     */
    public static String maskDriverName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return "";
        }

        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        int length = trimmed.length();

        if (length == 1) {
            return trimmed;
        }

        if (length == 2) {
            // 2글자: 뒷글자 마스킹 (예: "홍길" → "홍*")
            return trimmed.charAt(0) + "*";
        }

        // 3글자 이상: 첫글자와 마지막글자 제외 중간 마스킹 (예: "홍길동" → "홍*동")
        String middle = "*".repeat(length - 2);
        return trimmed.charAt(0) + middle + trimmed.charAt(length - 1);
    }

    /**
     * 차량 변경시 실비차 이외 센터 체크
     * @param vehiclesMap
     * @param changeCarNoList
     * @return
     */
    public static String validateCenterVehicleInfo(Map<String, TmVehicleInfoDto> vehiclesMap, List<TmChangeCarNoReqDto> changeCarNoList){
        String message = null;
        int count = 0;
        Set<String> checkedCarNo = new HashSet<>();

        for (TmChangeCarNoReqDto v : changeCarNoList) {
            TmVehicleInfoDto  vehicleInfo = vehiclesMap.get(v.getCarno());
            if(!("TEMPORARY").equals(v.getContractType())){
                if (vehicleInfo == null && !checkedCarNo.contains(v.getCarno())) {
                    checkedCarNo.add(v.getCarno());
                    if(message == null){
                        message =  v.getCarno() + " " ;
                    }else{
                        count ++;
                        message =  v.getCarno() + " 외 "+ count + "건";
                    }

                }
            }
        }
        return message;
    }

    /**
     * 차량 변경시 중복체크
     * @param dupCarDto
     * @param changeCarNoList
     * @return
     */
    public static String validateDupChangeInfo(Map<String, TmEngineRouteDto> dupCarDto, List<TmChangeCarNoReqDto> changeCarNoList){
        String message = null;
        int count = 0;
        Set<String> checkedCarNo = new HashSet<>();

        for (TmChangeCarNoReqDto v : changeCarNoList) {
            // oldCarNo와 CarNo와 같으면 로직 패스
            if(!v.getCarno().equals(v.getOldCarno())){
                String key = v.getCarno() + "|" + v.getPriority();
                TmEngineRouteDto  vehicleInfo = dupCarDto.get(key);

                if (vehicleInfo != null && !checkedCarNo.contains(key)) {
                    checkedCarNo.add(key);
                    if(message == null){
                        message =  v.getCarno() + " " ;
                    }else{
                        count ++;
                        message =  v.getCarno() + " 외 "+ count + "건";
                    }
                }
            }
        }
        return message;
    }

    /**
     * newCarNo에 oldCarNo가 중복복사금지
     * @param pasteDto
     * @return
     */
    public static String validateDupChangeInfo(Map<String, Integer> pasteDto){
        String message = null;
        int count = 0;

        for (Map.Entry<String, Integer> entry : pasteDto.entrySet()) {
            String carNo = entry.getKey();
            Integer cnt = entry.getValue();
            if(cnt > 1){
                if(message == null){
                    message =  carNo + " " ;
                }else{
                    count ++;
                    message =  carNo + " 외 "+ count + "건";
                }
            }
        }
        return message;
    }

    /**
     * otdTime 체크
     * @param orders
     * @return
     */
    public static String validateOtdTime(List<TmSetDispatchTruthCustResDto> orders, String deliveryDate){
        String message = null;
        int count = 0;

        for (TmSetDispatchTruthCustResDto v : orders) {
            // oldCarNo와 CarNo와 같으면 로직 패스
            if(!v.checkOtdTime()){
                if(count == 0){
                    count ++;
                    message =  v.getTruthCustKey() + " " ;
                }
                else if(count < 5 && count > 0){
                    count ++;
                    message =  message + v.getTruthCustKey() + " " ;
                }else{
                    count ++;
                }
            }
        }
        if(message != null){
            if(count > 4){
                message =  message +  " 외 "+ count + "건";
            }
        }
        return message;
    }

    /**
     * 차량 변경시 모든 행을 선택했는지 확인
     * @param requestList
     * @param dispatchList
     * @return
     */
    public static String validateCheckAllCarNo(List<TmChangeCarNoReqDto> requestList, List<TmDispatchInfoListResDto> dispatchList){
        String message = null;
        int count = 0;
        // 요청한 변경할 차량의 oldCar 갯수 확인
        Map<String, Long> distinctOldCarNoCountByCarNo =
                requestList.stream()
                        .collect(Collectors.groupingBy(
                                TmChangeCarNoReqDto::getOldCarno,
                                Collectors.counting()
                        ));

        Map<String, Long> distinctDispatchCountByCarNo =
                dispatchList.stream()
                        .collect(Collectors.groupingBy(
                                TmDispatchInfoListResDto::getCarno,
                                Collectors.counting()
                        ));

        // oldCarNo별로 개수 비교
        for (Map.Entry<String, Long> entry : distinctOldCarNoCountByCarNo.entrySet()) {
            String oldCarNo = entry.getKey();
            Long oldCarCount = entry.getValue();
            Long dispatchCount = distinctDispatchCountByCarNo.getOrDefault(oldCarNo, 0L);
            
            // oldCar 개수와 dispatchList의 carno 개수가 다른 경우
            if (!oldCarCount.equals(dispatchCount)) {
                if(message == null){
                    message = oldCarNo + " ";
                }else{
                    count++;
                    message = oldCarNo + " 외 " + count + "건";
                }
            }
        }

        return message;
    }
}
