package cjfw.wms.tm.service;

import static cjfw.wms.tm.constant.TmConstant.CONTRACT_TYPE_TEMPORARY;
import static cjfw.wms.tm.constant.TmConstant.KST;
import static cjfw.wms.tm.constant.TmConstant.TM_DELIVERY_TYPE_RETURN;
import static cjfw.wms.tm.constant.TmConstant.VEHICLE_TYPE_FIXED;
import static cjfw.wms.tm.constant.TmConstant.VEHICLE_TYPE_YONGCHA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.client.TmEngineOptimizeConfig;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.dto.*;
import cjfw.wms.tm.dto.engine.TmEngineJobDto;
import cjfw.wms.tm.dto.engine.TmEngineRequestDto;
import cjfw.wms.tm.dto.engine.TmEngineVehicleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2026. CJ Freshway Co. all rights reserved.
 * @author : ChoiHwaRang (az86067@cj.net)
 * @date : 2026.01.22
 * @description : PlanEta Auto API 전처리 Service (Jobs 방식)
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2026.01.22 ChoiHwaRang (az86067@cj.net) 생성 - TmNewEngineDataMapper에서 이관 </pre>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TmPlanAutoRequestService {

    private final TmEngineOptimizeConfig tmEngineOptimizeConfig;

    @Value("${tm.test-mode:false}")
    private boolean testMode;

    private static final double DEFAULT_LONGITUDE = 126.9780;
    private static final double DEFAULT_LATITUDE = 37.5665;
    private static final int DEFAULT_DELIVERY_DURATION_SECONDS = 600;
    private static final int DEFAULT_PRIORITY = 5;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** 전용차량 스킬 KEY 접두사 */
    private static final String DEDICATED_VEHICLE_SKILL_PREFIX = "DEDICATED_";

    /**
     * Auto API용 엔진 요청 생성
     *
     * 다회전 처리:
     * - 동일 carno의 여러 회차(roundSeq)를 하나의 vehicle로 합침
     * - max_trips = 회차 수
     * - 모든 회차의 steps를 jobs로 변환
     *
     * @param request 재계산 요청 DTO
     * @param centerInfo 센터 정보
     * @param orderMap 주문 Map (truthCustKey 기준)
     * @param vehicleMap 차량 Map (carno 기준)
     * @param planOption 배차 옵션
     * @return 엔진 요청 DTO
     */
    public TmEngineRequestDto createAutoEngineRequest(
            TmPlanEtaOptimizeAutoReqDto request,
            TmDispatchCenterInfoResDto centerInfo,
            Map<String, TmSetDispatchTruthCustResDto> orderMap,
            Map<String, TmVehicleInfoDto> vehicleMap,
            TmPlanOption planOption) {

        // 0. 전용차량 Map 생성: 차량번호 → 전용차량 스킬 KEY 목록
        Map<String, Set<String>> dedicatedVehicleSkillMap = buildDedicatedVehicleSkillMap(orderMap.values());

        // 1. carno별로 그룹화 (다회전 합침)
        Map<String, List<TmPlanEtaVehicleReqDto>> vehiclesByCarno = request.getVehicles().stream()
                .collect(Collectors.groupingBy(TmPlanEtaVehicleReqDto::getCarno));

        // 2. 그룹별로 하나의 vehicle 생성 (max_trips = 회차 수)
        List<TmEngineVehicleDto> vehicleDtoList = vehiclesByCarno.entrySet().stream()
                .map(entry -> createMergedEngineVehicle(entry.getValue(), vehicleMap.get(entry.getKey()), planOption, dedicatedVehicleSkillMap))
                .filter(Objects::nonNull)
                .toList();

        // 3. 모든 회차의 steps를 jobs로 변환
        List<TmEngineJobDto> jobsDtoList = request.getVehicles().stream()
                .flatMap(vehicle -> vehicle.getSteps().stream()
                        .map(step -> createEngineJob(
                                step,
                                vehicle.getCarno(),  // carno를 vehicleId로 사용 (합쳐진 vehicle의 id)
                                centerInfo,
                                orderMap.get(step.getId()),
                                planOption,
                                request.getDeliveryDate()))
                )
                .filter(Objects::nonNull)
                .toList();

        return TmEngineRequestDto.builder()
                .vehicles(vehicleDtoList)
                .jobs(jobsDtoList)
                .options(tmEngineOptimizeConfig.getOptimizeOption(planOption))
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
    private Map<String, Set<String>> buildDedicatedVehicleSkillMap(Collection<TmSetDispatchTruthCustResDto> orders) {
        Map<String, Set<String>> result = new HashMap<>();

        for (TmSetDispatchTruthCustResDto order : orders) {
            if (order == null) continue;

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

    /**
     * 동일 carno의 여러 회차를 하나의 엔진 Vehicle로 합침
     *
     * @param rounds 동일 carno의 회차 목록
     * @param vehicleInfo DB 차량 정보
     * @param planOption 배차 옵션
     * @param dedicatedVehicleSkillMap 전용차량 스킬 Map
     * @return 합쳐진 엔진 Vehicle DTO
     */
    private TmEngineVehicleDto createMergedEngineVehicle(List<TmPlanEtaVehicleReqDto> rounds,
                                                          TmVehicleInfoDto vehicleInfo,
                                                          TmPlanOption planOption,
                                                          Map<String, Set<String>> dedicatedVehicleSkillMap) {
        if (rounds == null || rounds.isEmpty()) {
            return null;
        }

        // roundSeq 정렬 후 대표 vehicle 선택 (1회전)
        rounds.sort(Comparator.comparing(v -> v.getRoundSeq() == null ? 1 : v.getRoundSeq()));
        TmPlanEtaVehicleReqDto primaryVehicle = rounds.get(0);

        if (vehicleInfo == null) {
            log.warn("차량 정보를 찾을 수 없습니다: {}", primaryVehicle.getCarno());
            return null;
        }

        List<Integer> capacity;
        String vehicleType = VEHICLE_TYPE_FIXED;

        // 실비차일 경우 요청 시 들어온 입력값 데이터 사용
        if (CONTRACT_TYPE_TEMPORARY.equals(primaryVehicle.getContractType())) {
            capacity = primaryVehicle.getCapacity(planOption);
            vehicleType = VEHICLE_TYPE_YONGCHA;
        } else {
            if (testMode) {
                capacity = getCapacityForTestMode(planOption);
            } else {
                capacity = vehicleInfo.getCapacity(planOption);
            }
        }

        List<Double> startLocation = Arrays.asList(
                Optional.ofNullable(vehicleInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                Optional.ofNullable(vehicleInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        // 배차 옵션에 다회전 여부 ON인 경우만 endLocation 설정
        List<Double> endLocation = null;
        if (planOption.isOnMultiTurn()) {
            endLocation = startLocation;
        }

        LocalDateTime workStartTime = vehicleInfo.getDrivingStartDateTime();
        LocalDateTime workEndTime = vehicleInfo.getDrivingEndDateTime();

        // 차량 병합 방식에서는 setDispatch와 동일하게 처리 (roundCount 체크 불필요)
        int maxTrips = vehicleInfo.getMaxTrips(planOption.isOnMultiTurn());

        if (testMode) {
            if (vehicleInfo.isInvalidDrivingTime()) {
                workEndTime = workStartTime.plusHours(5);
            }
        } else {
            if (vehicleInfo.isInvalidDrivingTime()) {
                throw new UserHandleException("주행 종료시간은 시작시간보다 커야합니다. " + vehicleInfo.getCarno());
            }
        }

        // 차량 스킬 설정: 차량번호 + 전용차량 스킬 KEY
        String carno = primaryVehicle.getCarno();
        List<String> skills = new ArrayList<>();
        skills.add(carno);

        // 전용차량 스킬 KEY 추가
        Set<String> dedicatedSkills = dedicatedVehicleSkillMap.get(carno);
        if (!ObjectUtils.isEmpty(dedicatedSkills)) {
            skills.addAll(dedicatedSkills);
        }

        // 최소 착지수 설정 (기본착지수 = 최소착지수), null인 경우 0
        int minStops = 0;
        if (!ObjectUtils.isEmpty(vehicleInfo.getMinLanding())) {
            try {
                minStops = Integer.parseInt(vehicleInfo.getMinLanding().trim());
            } catch (NumberFormatException e) {
                log.warn("최소 착지수 변환 실패 - 차량: {}, 값: {}", vehicleInfo.getCarno(), vehicleInfo.getMinLanding());
            }
        }

        // 최대 착지수 설정 (최대착지수 = 최대착지수), null인 경우 0
        int maxStops = 0;
        if (!ObjectUtils.isEmpty(vehicleInfo.getMaxLanding())) {
            try {
                maxStops = Integer.parseInt(vehicleInfo.getMaxLanding().trim());
            } catch (NumberFormatException e) {
                log.warn("최대 착지수 변환 실패 - 차량: {}, 값: {}", vehicleInfo.getCarno(), vehicleInfo.getMaxLanding());
            }
        }

        return TmEngineVehicleDto.builder()
                .id(carno)  // carno를 id로 사용 (합쳐진 vehicle)
                .start_location(startLocation)
                .end_location(null)
                .capacity(capacity)
                .skills(skills)
                .preferred_skills(Collections.singletonList(carno))
                .vehicle_type(vehicleType)
                .work_start_time(workStartTime.format(DATETIME_FORMATTER))
                .work_end_time(workEndTime.format(DATETIME_FORMATTER))
                .max_trips(maxTrips)
                .car_number(carno)
                .max_visit_pops((planOption.getPopCount() == 0) ? null : planOption.getPopCount())
                .min_stops(minStops)
                .build();
    }

    /**
     * Step 정보를 엔진 Job DTO로 변환
     */
    private TmEngineJobDto createEngineJob(
            TmPlanEtaStepReqDto step,
            String vehicleId,
            TmDispatchCenterInfoResDto centerInfo,
            TmSetDispatchTruthCustResDto orderInfo,
            TmPlanOption planOption,
            String deliveryDate) {

        if (step.getId() == null || orderInfo == null) return null;

        // reload step은 Job으로 변환하지 않음
        if ("1".equals(step.getTmDeliveryType()) && "reload".equals(step.getType())) {
            return null;
        }

        // 분할배송 처리
        String requestId = step.getId();
        if ("Y".equals(step.getSplitDeliveryYn())) {
            int splitSeq = Integer.parseInt(step.getSplitDeliverySeq()) + 1;
            requestId = step.getId() + "-" + splitSeq;
        }

        // 반품/배송 분기
        if (TM_DELIVERY_TYPE_RETURN.equals(orderInfo.getTmDeliveryType())) {
            return createJobForReturn(requestId, step, orderInfo, planOption, deliveryDate);
        } else {
            return createJobForDelivery(requestId, step, orderInfo, planOption, deliveryDate);
        }
    }

    /**
     * 반품 Job 생성
     */
    private TmEngineJobDto createJobForReturn(String requestId,
                                               TmPlanEtaStepReqDto step,
                                               TmSetDispatchTruthCustResDto orderInfo,
                                               TmPlanOption planOption,
                                               String deliveryDate) {

        List<Double> location = Arrays.asList(
                parseCoordinate(orderInfo.getLongitude()).orElse(DEFAULT_LONGITUDE),
                parseCoordinate(orderInfo.getLatitude()).orElse(DEFAULT_LATITUDE)
        );

        // 반품의 경우 중량, 체적 = 0 설정해서 전송
        List<Integer> amount = step.getAmount(planOption);

        // 거래처명 또는 주소
        String description = orderInfo.getCustName() != null ? orderInfo.getCustName() : orderInfo.getAddress();

        return TmEngineJobDto.builder()
                .id(requestId)
                .location(location)
                .delivery(amount)
                .service(DEFAULT_DELIVERY_DURATION_SECONDS)
                .description(description)
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deliveryDate))
                .pop(orderInfo.getPop())
                .dedicated_vehicle(orderInfo.getDedicatedCars())
                .build();
    }

    /**
     * 배송 Job 생성
     */
    private TmEngineJobDto createJobForDelivery(String requestId,
                                                 TmPlanEtaStepReqDto step,
                                                 TmSetDispatchTruthCustResDto orderInfo,
                                                 TmPlanOption planOption,
                                                 String deliveryDate) {

        List<Integer> amount = step.getAmount(planOption);

        // 제외차량 목록 조회
        List<String> blockedVehicleIds = orderInfo.getBlockedCars();

        // 스킬 목록 생성 후 제외차량 필터링
        List<String> rawSkills = buildJobSkills(orderInfo, planOption);
        List<String> skills = filterBlockedVehicles(rawSkills, blockedVehicleIds);

        // 고정차량이 제외차량에 포함된 경우 미배차 처리
        if (!ObjectUtils.isEmpty(rawSkills) && ObjectUtils.isEmpty(skills)) {
            skills = Collections.singletonList("UNASSIGNED_BLOCKED_CONFLICT");
            log.warn("고정/전용차량이 제외차량과 충돌 - 주문: {}, 원본 skills: {}, blocked: {}",
                    orderInfo.getTruthCustKey(), rawSkills, blockedVehicleIds);
        }

        // 거래처명 또는 주소
        String description = orderInfo.getCustName() != null ? orderInfo.getCustName() : orderInfo.getAddress();

        TmEngineJobDto engineJobDto = TmEngineJobDto.builder()
                .id(requestId)
                .location(step.getLocation())
                .delivery(amount)
                .service(DEFAULT_DELIVERY_DURATION_SECONDS)
                .description(description)
                .skills(skills)
                .blocked_vehicle_ids(blockedVehicleIds)
                .priority(DEFAULT_PRIORITY)
                .otd_time(orderInfo.getOtdTime(deliveryDate))
                .pop(orderInfo.getPop())
                .dedicated_vehicle(orderInfo.getDedicatedCars())
                .build();

        // 선호차량 설정 (제외차량 필터링 적용)
        if (!ObjectUtils.isEmpty(orderInfo.getDefCarno())) {
            List<String> rawPreferred = Collections.singletonList(orderInfo.getDefCarno());
            List<String> preferredSkills = filterBlockedVehicles(rawPreferred, blockedVehicleIds);
            if (!ObjectUtils.isEmpty(preferredSkills)) {
                engineJobDto.setPreferred_skills(preferredSkills);
            }
            if (skills != null && !skills.isEmpty()) {
                engineJobDto.setPriority(100);
            }
        }
        return engineJobDto;
    }

    /**
     * Job 스킬 목록 생성
     * - 전용차량과 특수조건/출입KEY를 고려하여 스킬 목록 생성
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
     * 테스트 모드용 용량 설정
     */
    private List<Integer> getCapacityForTestMode(TmPlanOption planOption) {
        List<Integer> capacity = new ArrayList<>();
        if (planOption.isOnMaxCbm()) {
            capacity.add(10);
        }
        capacity.add(1000);
        if (planOption.isOnMaxLocation()) {
            capacity.add(10);
        }
        return capacity;
    }

    /**
     * 좌표 문자열을 Double로 변환
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
