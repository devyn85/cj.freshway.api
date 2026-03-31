package cjfw.wms.tm.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : AI Assistant
 * @date : 2025.01.14
 * @description : TM 차량 정보 DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.01.14 AI Assistant          생성
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "차량 정보 DTO")
public class TmVehicleInfoDto {

    /** 차량번호 */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

    /** 조차코드 */
    @Schema(description = "조차 코드", example = "10")
    private String outGroupCd;

    /** 조차명 */
    @Schema(description = "조차명", example = "1조")
    private String outGroupName;

    /** 기본 중량 (kg) */
    @Schema(description = "기본 적재 중량 (kg)", example = "2000")
    private Double minWeight;

    /** 최대 중량 (kg) */
    @Schema(description = "최대 적재 중량 (kg)", example = "2000")
    private Double maxWeight;
    
    /** 최대 착지수 */
    @Schema(description = "최대 착지수", example = "10")
    private String maxLanding;
    
    /** 기본 착지수 */
    @Schema(description = "기본착지수", example = "5")
    private String minLanding;
    
    /** 기본 착지수 */
    @Schema(description = "적정팔렛수", example = "5")
    private String optPlt;

    @Schema(description = "차량 톤수", example = "3.5톤")
    private String carCapacity;

    /** 최대 부피 (CBM) */
    @Schema(description = "최대 적재 부피 (CBM)", example = "15.5")
    private Double maxCube;

    /** 계약 유형 */
    @Schema( description = "계약 유형", example = "FIXTEMPORARY")
    private String contractType;
    
    /** 계약명 */
    @Schema( description = "계약명", example = "임시용차")
    private String contractTypeNm;

    /** 위도 */
    @Schema(description = "센터 위치 위도", example = "37.4979")
    private Double latitude;

    /** 경도 */
    @Schema(description = "센터 위치 경도", example = "127.0276")
    private Double longitude;
    
    /** 차고지 위도 */
    @Schema(description = "차량 차고지 위치 위도", example = "37.4979")
    private Double carLatitude;
    
    /** 차고지 경도 */
    @Schema(description = "차량 차고지 위치 경도", example = "127.0276")
    private Double carLongitude;
    
    @Schema(description = "주행시작시간")
    private String drivingFromdate;
    
    @Schema(description = "주행 종료시간")
    private String drivingTodate;

    @Schema(description = "근무 시작시간")
    private String workFromhour;

    @Schema(description = "근무 종료시간")
    private String workTohour;

    @Schema(description = "운전자이름")
    private String drivername;

    @Schema(description = "운전자 연락처")
    private String phone1;

    private String inTime;
    private String outTime;
    private String originWorkFromhour;
    private String originWorkTohour;
    private int roundSeq;
    private String priorityYn;
    private String deliveryYn;

    @Schema(description = "tc센터코드")
    private String tcDcCode;

    private static final String MISSED = " 누락";

    public String checkRequiredDataForDispatch(TmPlanOption planOption) {
        if (ObjectUtils.isEmpty(inTime)) return "센터 입차시간" + MISSED;
        if (ObjectUtils.isEmpty(outTime)) return "센터 출차시간" + MISSED;
        if (!isValidInOutTime()) {
            return "센터 입/출차 시간 비정상";
        }
        if (ObjectUtils.isEmpty(originWorkFromhour)) return "근무 시작시간" + MISSED;
        if (ObjectUtils.isEmpty(originWorkTohour)) return "근무 종료시간" + MISSED;
        if (isInvalidDrivingTime()) {
            return "주행 시작/종료시간 비정상";
        }
        if (planOption == null) return null;
        if (planOption.isOnMaxWeight() && ObjectUtils.isEmpty(maxWeight)) return "최대 적재 중량" + MISSED;
        if (!planOption.isOnMaxWeight() && ObjectUtils.isEmpty(minWeight)) return "기본 적재 중량" + MISSED;
        if (planOption.isOnMaxLocation() && ObjectUtils.isEmpty(maxLanding)) return "최대 착지수" + MISSED;
        if (!planOption.isOnMaxLocation() && ObjectUtils.isEmpty(minLanding)) return "기본착지수" + MISSED;
        if (planOption.isOnMaxCbm() && ObjectUtils.isEmpty(maxCube)) return "최대 적재 부피 (CBM)" + MISSED;

        if (ObjectUtils.isEmpty(latitude) || ObjectUtils.isEmpty(longitude)) {
            return "센터 좌표" + MISSED;
        }
        return null;
    }

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter yyyyMMddHHmm_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final DateTimeFormatter CENTER_INOUT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private boolean isValidInOutTime() {
        try {
            LocalTime.parse(inTime, CENTER_INOUT_FORMATTER);
            LocalTime.parse(outTime, CENTER_INOUT_FORMATTER);
        } catch (Exception e) {
            log.warn("[{}] Wrong CENTER IN_TIME or OUT_TIME, inTime={}, outTime={} : {}", carno, inTime, outTime, e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isInvalidDrivingTime() {
        return !getDrivingEndDateTime().isAfter(getDrivingStartDateTime());
    }

    /**
     * 주행 시작 시간 = 운전자 근무 시작 시간 이후  조차 출차 시간이 주행 시간
     *    근무시간   |---------------------------------------------------------|
     *                       |          |
     *        1조차         입차시간      출자시간     |          |
     *        2조차                               입차시간      출자시간
     * @return
     */
    public LocalDateTime getDrivingStartDateTime() {
        if (ObjectUtils.isEmpty(drivingFromdate)) {
            return null;
        }

        LocalDateTime startTime = convertFormat(drivingFromdate);

        // 야간 근무 체크: 종료시간이 시작시간보다 빠르면 전날로 보정
        // 예: 출차 23:00, 근무종료 06:00 → 출차를 전날 23:00로 처리
        if (!ObjectUtils.isEmpty(drivingTodate)) {
            LocalDateTime endTime = convertFormat(drivingTodate);
            if (!endTime.isAfter(startTime)) {
                log.info("[{}] 야간 근무 보정: {} → {}", carno, drivingFromdate, startTime.minusDays(1));
                return startTime.minusDays(1);
            }
        }

        return startTime;
    }

    /**
     * 주행 종료시간 = 다음 조차 입차 시간 또는 근무 종료시간
     * @return
     */
    public LocalDateTime getDrivingEndDateTime() {
        if (ObjectUtils.isEmpty(drivingTodate)) {
            return null;
        }
        return convertFormat(drivingTodate);
    }

    private LocalDateTime convertFormat(String workhour) {
        return LocalDateTime.parse(workhour, DATETIME_FORMATTER);
    }

    public List<Integer> getCapacity(TmPlanOption planOption){

        List<Integer> capacity = new ArrayList<>();

        // 배차옵션 최대적재CBM (톤급별 비율 적용)
        if (planOption.isOnMaxCbm()) {
            double cbmValue = maxCube != null ? maxCube : 0;
            int ratio = planOption.getCbmRatioByTon(this.carCapacity);
            double cbm = cbmValue * ratio / 100.0;
            capacity.add(TmPlanCommon.toIntMinCbm(cbm));
        }
        // 배차옵션 최대적재중량
        if (planOption.isOnMaxWeight()) {
            capacity.add(TmPlanCommon.toIntMin1(maxWeight));
        }else{
            capacity.add(TmPlanCommon.toIntMin1(minWeight));
        }

        // 배차 옵션 최대 착지수 설정시 차량에 설정된 최대착지수 설정하고 OFF 인경우 제한 없음
        if (planOption.isOnMaxLocation())
            capacity.add(Integer.parseInt(maxLanding == null ? "10" : maxLanding.trim()));

        return capacity;
    }

    public boolean isPriorityYn() {
        return "Y".equals(priorityYn);
    }

    public int getMaxTrips(boolean isOnMultiTurn) {
        if (isOnMultiTurn) {
            return isPriorityYn() ? 2 : 1;
        }
        return 1;
    }
}
