package cjfw.wms.tm.service;

import static cjfw.wms.tm.constant.TmConstant.KST;
import static cjfw.wms.tm.constant.TmConstant.VEHICLE_TYPE_FIXED;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cjfw.wms.tm.client.TmEngineOptimizeConfig;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.*;
import cjfw.wms.tm.util.TmPlanCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.domain.TmPlanOption;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwaRang (az86067@cj.net)
 * @date : 2026.01.20
 * @description : TM Engine 요청 데이터 전처리 Service (Jobs 방식)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.20 ChoiHwaRang (az86067@cj.net) 생성 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanEngineRequestService {

    private final TmEngineOptimizeConfig tmEngineOptimizeConfig;

    @Value("${tm.test-mode:false}")
    private boolean testMode;

    // DEFAULT VALUE는 DB 데이터 미존재로 임의 설정 추후 제거 필요
    private static final double DEFAULT_LONGITUDE = 126.9780; // 서울시청 경도
    private static final double DEFAULT_LATITUDE = 37.5665;   // 서울시청 위도
    private static final int DEFAULT_SERVICE_SECONDS = 600; // 10분 (거래처 작업시간)
    private static final int DEFAULT_PRIORITY = 5;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** 전용차량 스킬 KEY 접두사 */
    private static final String DEDICATED_VEHICLE_SKILL_PREFIX = "DEDICATED_";

    public TmEngineRequestDto createEngineRequest(List<TmSetDispatchTruthCustResDto> orders,
                                                 List<TmVehicleInfoDto> vehicles,
                                                 TmDispatchCenterInfoResDto centerInfo,
                                                 TmPlanOption dispatchOptionDto,
                                                 TmSetDispatchReqDto request) {

        // 전용차량 Map 생성: 차량번호 → 전용차량 스킬 KEY 목록
        Map<String, Set<String>> dedicatedVehicleSkillMap = buildDedicatedVehicleSkillMap(orders);

        // 주문을 Jobs로 변환
        List<TmEngineJobDto> jobs = orders.stream()
                .map(item -> createEngineJob(item, centerInfo, dispatchOptionDto, request.getDeliveryDate()))
                .filter(Objects::nonNull)
                .toList();

        // 차량을 Vehicle로 변환 (전용차량 스킬 Map 전달)
        List<TmEngineVehicleDto> engineVehicles = vehicles.stream()
            .map(item -> createEngineVehicle(item, dispatchOptionDto, dedicatedVehicleSkillMap))
            .filter(Objects::nonNull)
            .toList();

        return TmEngineRequestDto.builder()
            .jobs(jobs)
            .vehicles(engineVehicles)
            .options(tmEngineOptimizeConfig.getOptimizeOption(dispatchOptionDto))
            .timezone(KST)
            .description(request.getDescription())
            .build();
    }

    /**
     * 전용차량 스킬 Map 생성
     * - 주문의 전용차량 목록을 순회하여 차량번호 → 전용차량 스킬 KEY 매핑
     *
     * @param orders 주문 목록
     * @return Map<차량번호, Set<전용차량 스킬 KEY>>
     */
    private Map<String, Set<String>> buildDedicatedVehicleSkillMap(List<TmSetDispatchTruthCustResDto> orders) {
        Map<String, Set<String>> result = new HashMap<>();

        for (TmSetDispatchTruthCustResDto order : orders) {
            List<String> dedicatedCars = order.getDedicatedCars();
            if (ObjectUtils.isEmpty(dedicatedCars)) {
                continue;
            }

            String dedicatedSkillKey = DEDICATED_VEHICLE_SKILL_PREFIX + order.getTruthCustKey();

            for (String carno : dedicatedCars) {
                result.computeIfAbsent(carno, k -> new HashSet<>()).add(dedicatedSkillKey);
            }
        }

        return result;
    }

    public TmEngineJobDto createEngineJob(TmSetDispatchTruthCustResDto order,
                                      TmDispatchCenterInfoResDto centerInfo,
                                      TmPlanOption planOption,
                                      String deliveryDate) {
        if (order == null) {
            log.warn("주문 정보가 null입니다.");
            return null;
        }

        // 좌표 없는 주문은 미배차 처리
        if (order.getLatitude() == null || order.getLongitude() == null) {
            return null;
        }

        try {
            List<Double> custLocation = Arrays.asList(
                    parseCoordinate(order.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    parseCoordinate(order.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            // 제외차량 목록 조회
            List<String> blockedVehicleIds = order.getBlockedCars();

            // 스킬 목록 생성 후 제외차량 필터링
            List<String> rawSkills = buildJobSkills(order, planOption);
            List<String> skills = filterBlockedVehicles(rawSkills, blockedVehicleIds);

            // 고정차량이 제외차량에 포함된 경우 미배차 처리
            if (!ObjectUtils.isEmpty(rawSkills) && ObjectUtils.isEmpty(skills)) {
                skills = Collections.singletonList("UNASSIGNED_BLOCKED_CONFLICT");
                log.warn("고정/전용차량이 제외차량과 충돌 - 주문: {}, 원본 skills: {}, blocked: {}",
                        order.getTruthCustKey(), rawSkills, blockedVehicleIds);
            }

            // 서비스 시간 계산 (workTime: 분 단위 -> 초 단위 변환)
            int serviceSeconds = Optional.ofNullable(order.getWorkTime())
                    .map(minutes -> TmPlanCommon.toInt(minutes, 10) * 60)
                    .orElse(DEFAULT_SERVICE_SECONDS);

            // 거래처 주소 (description)
            String description = order.getCustName() != null ? order.getCustName() : order.getAddress();

            TmEngineJobDto engineJobDto = TmEngineJobDto.builder()
                    .id(Optional.ofNullable(order.getTruthCustKey()).orElse("UNKNOWN"))
                    .location(custLocation)
                    .delivery(order.getAmount(planOption))
                    .service(serviceSeconds)
                    .description(description)
                    .priority(DEFAULT_PRIORITY)
                    .blocked_vehicle_ids(blockedVehicleIds)
                    .skills(skills)
                    .otd_time(order.getOtdTime(deliveryDate))
                    .pop(order.getPop())
                    .dedicated_vehicle(order.getDedicatedCars())
                    .build();

            // 선호차량 설정 (제외차량 필터링 적용)
            if (!ObjectUtils.isEmpty(order.getDefCarno())) {
                List<String> rawPreferred = Collections.singletonList(order.getDefCarno());
                List<String> preferredSkills = filterBlockedVehicles(rawPreferred, blockedVehicleIds);
                if (!ObjectUtils.isEmpty(preferredSkills)) {
                    engineJobDto.setPreferred_skills(preferredSkills);
                }
                if (skills != null && !skills.isEmpty()) {
                    engineJobDto.setPriority(100);
                }
            }
            return engineJobDto;
        } catch (Exception e) {
            log.error("주문 정보를 Engine Job로 변환 중 오류 발생 - 주문: {}, 오류: {}", order.getTruthCustKey(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * 제외차량(blocked_vehicle_ids)에 포함된 차량을 목록에서 필터링
     *
     * @param vehicles 차량 목록 (skills 또는 preferred_skills)
     * @param blockedVehicleIds 제외차량 목록
     * @return 제외차량이 필터링된 차량 목록
     */
    private List<String> filterBlockedVehicles(List<String> vehicles, List<String> blockedVehicleIds) {
        if (ObjectUtils.isEmpty(vehicles) || ObjectUtils.isEmpty(blockedVehicleIds)) {
            return vehicles;
        }
        Set<String> blockedSet = new HashSet<>(blockedVehicleIds);
        List<String> filtered = vehicles.stream()
                .filter(v -> !blockedSet.contains(v))
                .collect(Collectors.toList());
        return filtered.isEmpty() ? Collections.emptyList() : filtered;
    }

    /**
     * Job 스킬 목록 생성
     * - 전용차량과 특수조건/출입KEY를 고려하여 스킬 목록 생성
     * - 엔진의 skills 매칭은 AND 조건이므로, 필요한 스킬을 모두 추가하면 엔진이 교집합 처리
     *
     * @param order 주문 정보
     * @param planOption 배차 옵션
     * @return 스킬 목록
     */
    private List<String> buildJobSkills(TmSetDispatchTruthCustResDto order, TmPlanOption planOption) {
        List<String> dedicatedCars = order.getDedicatedCars();
        boolean hasDedicatedCars = !ObjectUtils.isEmpty(dedicatedCars);

        // 1. 전용차량 목록이 존재할 경우, 전용차량만 skill로 적용
        if (hasDedicatedCars) {
            String dedicatedSkillKey = DEDICATED_VEHICLE_SKILL_PREFIX + order.getTruthCustKey();
            return Collections.singletonList(dedicatedSkillKey);
        }

        // 2. 전용차량 목록이 존재하지 않는 경우, 고정차량 조건 적용
        boolean needsFixedVehicle = order.needsDedicatedVehicle(planOption.isOnSkills());
        if (needsFixedVehicle) {
            String fixedCarno = order.getDefCarno();
            if (ObjectUtils.isEmpty(fixedCarno)) {
                // 담당차량 없음 → 미배차
                return Collections.singletonList("UNASSIGNED_SPECIAL_CONDITION");
            }
            return Collections.singletonList(fixedCarno);
        }

        // 3. 조건 없음 → 모든 차량 배차 가능
        return Collections.emptyList();
    }

    public TmEngineVehicleDto createEngineVehicle(TmVehicleInfoDto vehicle,
                                              TmPlanOption planOption,
                                              Map<String, Set<String>> dedicatedVehicleSkillMap) {
        if (vehicle == null) {
            log.warn("차량 정보가 null입니다.");
            return null;
        }

        try {
            List<Double> startLocation = Arrays.asList(
                    Optional.ofNullable(vehicle.getLongitude()).orElse(DEFAULT_LONGITUDE),
                    Optional.ofNullable(vehicle.getLatitude()).orElse(DEFAULT_LATITUDE)
            );

            // 차량별 종착지 확인 후 endLocation 지정 필요.
            List<Double> endLocation = null;
            // 배차 옵션에 다회전 여부 ON, 차량 설정에 ON(적용필요)
            if (planOption.isOnMultiTurn()) {
                 endLocation = startLocation;
            }

            List<Integer> capacity = vehicle.getCapacity(planOption);

            /* 테스트모드 사용안함
            if (testMode) {
                capacity = getCapacityForTestMode(planOption);
            } else {
                capacity = vehicle.getCapacity(planOption);
            }
            */

            LocalDateTime workStartTime = vehicle.getDrivingStartDateTime();
            LocalDateTime workEndTime = vehicle.getDrivingEndDateTime();

            if (testMode) {
                if (vehicle.isInvalidDrivingTime()) {
                    workEndTime = workStartTime.plusHours(5); // [TODO] 임시 로직, 제거 필요
                }
            } else {
                if (vehicle.isInvalidDrivingTime()) {
                    throw new UserHandleException("주행 종료시간은 시작시간보다 커야합니다. " + vehicle.getCarno());
                }
            }

            // 차량 스킬 설정: 차량번호 + 전용차량 스킬 KEY
            List<String> skills = new ArrayList<>();
            if (vehicle.getCarno() != null) {
                skills.add(vehicle.getCarno());

                // 전용차량 스킬 KEY 추가
                Set<String> dedicatedSkills = dedicatedVehicleSkillMap.get(vehicle.getCarno());
                if (!ObjectUtils.isEmpty(dedicatedSkills)) {
                    skills.addAll(dedicatedSkills);
                }
            }

            // 회전수
            int maxTrips= vehicle.getMaxTrips(planOption.isOnMultiTurn());

            // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 0
            int minStops = 0;
            if (!ObjectUtils.isEmpty(vehicle.getMinLanding())) {
                try {
                    minStops = Integer.parseInt(vehicle.getMinLanding().trim());
                } catch (NumberFormatException e) {
                    log.warn("최소 착지수 변환 실패 - 차량: {}, 값: {}", vehicle.getCarno(), vehicle.getMinLanding());
                }
            }

            // 최대 착지수 설정 (최대착지수 = 최대착지수), null인 경우 0
            Integer maxStops = null;
            if (planOption.isOnMaxLocation()) {
                if (!ObjectUtils.isEmpty(vehicle.getMaxLanding())) {
                    try {
                        maxStops = Integer.parseInt(vehicle.getMaxLanding().trim());
                    } catch (NumberFormatException e) {
                        log.warn("최대 착지수 변환 실패 - 차량: {}, 값: {}", vehicle.getCarno(), vehicle.getMaxLanding());
                    }
                }
            }

            return TmEngineVehicleDto.builder()
                    .id(vehicle.getCarno())
                    .start_location(startLocation)
                    .end_location(null)
                    .capacity(capacity)
                    .skills(skills)
                    .preferred_skills(Collections.singletonList(vehicle.getCarno()))
                    .vehicle_type(VEHICLE_TYPE_FIXED)
                    .work_start_time(workStartTime.format(DATETIME_FORMATTER))
                    .work_end_time(workEndTime.format(DATETIME_FORMATTER))
                    .max_trips(maxTrips)
                    .car_number(vehicle.getCarno())
                    .min_stops(minStops)
                    .max_visit_pops((planOption.getPopCount() == 0) ? null : planOption.getPopCount())
                    .build();

        } catch (Exception e) {
            log.error("차량 정보를 Engine Vehicle로 변환 중 오류 발생 - 차량: {}, 오류: {}", vehicle.getCarno(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * 테스트 모드용 용량 설정
     *
     * @param planOption 배차 옵션
     * @return 테스트용 capacity 목록
     */
    private List<Integer> getCapacityForTestMode(TmPlanOption planOption) {
        List<Integer> capacity = new ArrayList<>();
        if (planOption.isOnMaxCbm())
            capacity.add(10);
        capacity.add(1000);
        if (planOption.isOnMaxLocation())
            capacity.add(10);
        return capacity;
    }

    /**
     * 좌표 문자열을 Double로 변환
     *
     * @param coordinateStr 좌표 문자열
     * @return 변환된 좌표값 (Optional)
     */
    private Optional<Double> parseCoordinate(String coordinateStr) {
        if (coordinateStr == null || coordinateStr.trim().isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Double.parseDouble(coordinateStr.trim()));
        } catch (NumberFormatException e) {
            log.warn("좌표 변환 실패: {}", coordinateStr);
            return Optional.empty();
        }
    }
}
