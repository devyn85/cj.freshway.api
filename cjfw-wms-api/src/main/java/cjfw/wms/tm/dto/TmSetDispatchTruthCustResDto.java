package cjfw.wms.tm.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import static cjfw.wms.tm.constant.TmConstant.KST;
import static cjfw.wms.tm.controller.TmNewEngineDataMapper.SEOUL_ZONE_ID;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.30 
 * @description : 착지 기준 거래처 목록 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.30 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "착지 기준 거래처 목록 응답 DTO")
public class TmSetDispatchTruthCustResDto {
	
	@Schema(description = "실착지 거리처 코드")
	private String truthCustKey;
	
	@Schema(description = "주소")
	private String address;
	
	@Schema(description = "담당 차량")
	private String defCarno;

    @Schema(description = "위치 좌표 [경도, 위도]")
    private List<Double> location;

	@Schema(description = "위도")
	private String latitude;
	
	@Schema(description = "경도")
	private String longitude;
	
	@Schema(description = "고객명")
	private String custName;
	
	@Schema(description = "주문수량")
	private String orderQty;
	
	@Schema(description = "체적")
	private String cube;
	
	@Schema(description = "중량")
	private String weight;
	
	@Schema(description = "비밀번호유형")
	private String passwordType;

    @Schema(description = "비밀번호유형 코드")
    private String passwordTypeCd;

	@Schema(description = "OTD FROM")
	private String reqdlvtime1From;
	
	@Schema(description = "대면검수 Yn")
	private String faceInspect;
	
	@Schema(description = "OTD TO")
	private String reqdlvtime1To;
	
	@Schema(description = "납품대기여부 YN")
	private String dlvWaitYn;
	
	@Schema(description = "격오지 YN")
	private String distantYn;
	
	@Schema(description = "장거리 YN")
	private String lngDistantYn;
	
	@Schema(description = "하차난이도 YN")
	private String unloadLvlYn;
	
	@Schema(description = "도착요구시간")
	private String deliveryReqDt;
	
	@Schema(description = "키즈분류여부")
	private String kidsClYn;
	
	@Schema(description = "고객사코드")
	private String storerkey;

	@Schema(description = "거래처유형")
	private String custType;
	
	@Schema(description = "클레임 YN")
	private String claimYn;
	
	@Schema(description = "작업시간")
	private String workTime;
	
	@Schema(description = "지정차량")
	private String carno;
	
	@Schema(description = "현재 배차된 POP")
	private String pop;

	@Schema(description = "고정 POP")
	private String defPop;

	@Schema(description = "대표 POP (고객 마스터 기본 POP)")
	private String basePop;

	@Schema(description = "반품주문 포함여부")
	private String returnYn;
	
	@Schema(description = "배송유형")
	private String tmDeliveryType;
	
	@Schema(description = "분할배송 Y/N")
	private String splitDeliveryYn;
	
	@Schema(description = "분할배송 순번")
	private String splitDeliverySeq;
	
	@Schema(description = "전표일자")
	private String slipdt;
	
	@Schema(description = "전표라인")
	private String slipline;
	
	@Schema(description = "전표번호")
	private String slipno;

    @Schema(description = "전용차량 목록")
    private String dedicatedCarnoList;
	
	@Schema(description = "전용차량 차량유형, 10:전용차량,")
	private String dedicatedCustCarType;

    @Schema(description = "강성차량 목록")
    private String blockedCarnoList;

    @Schema(description = "전용차량 차량유형, 99:강성차량")
    private String blockedCustCarType;

    @Schema(description = "요청otdFrom시간")
    private String otdTimeFrom;

    @Schema(description = "요청otdTo시간")
    private String otdTimeTo;

    @Schema(description = "분할배송 상품 목록")
    private List<TmSplitItemDto> splitItems;

    /** 서비스 시간 */
    @Schema(description = "서비스 시간 (초)")
    private Integer service;

    public List<String> getBlockedCars() {
        if (ObjectUtils.isEmpty(blockedCustCarType)) return null;

        if ("99".equals(blockedCustCarType) && !ObjectUtils.isEmpty(blockedCarnoList)) {
			return new ArrayList<>(Arrays.asList(blockedCarnoList.split(",")));
        }

        return null;
    }

    public List<String> getDedicatedCars() {
        if (ObjectUtils.isEmpty(dedicatedCustCarType)) {
        	return null;
        }

        if (!ObjectUtils.isEmpty(dedicatedCustCarType) &&
                "10".equals(dedicatedCustCarType) &&
                !ObjectUtils.isEmpty(dedicatedCarnoList)) {
			return new ArrayList<>(Arrays.asList(dedicatedCarnoList.split(",")));
        }

        return null;
    }

    public boolean isSpecial() {
        return "Y".equals(this.getKidsClYn()) || "Y".equals(this.getDlvWaitYn())
                || "Y".equals(this.getDistantYn()) || "Y".equals(this.getLngDistantYn())
                || "Y".equals(this.getUnloadLvlYn());
    }

    /** 특수조건인데 지정차량이 없는 경우 미배차 처리용 스킬 값 */
    private static final String UNASSIGNED_SPECIAL_CONDITION_SKILL = "UNASSIGNED_SPECIAL_CONDITION";

    /** 고정차량 배차가 필요한 출입KEY 코드 목록 (배차 옵션과 무관하게 항상 적용) */
    private static final Set<String> DEDICATED_VEHICLE_KEY_TYPES = Set.of("01", "02", "04");

    /**
     * 출입KEY가 고정차량 배차를 필요로 하는지 확인
     * - 배차 옵션과 무관하게 항상 적용
     * - 코드: 01, 02, 04
     */
    public boolean isKeyTypeRequiresDedicatedVehicle() {
        return DEDICATED_VEHICLE_KEY_TYPES.contains(Optional.ofNullable(this.passwordTypeCd)
                .orElse("05"));
    }

    /**
     * 특수조건/출입KEY에 대한 스킬 목록 반환
     * - 특수조건 (배차 옵션 의존) 또는 출입KEY (배차 옵션 무관)인 경우
     * - 담당차량(carno)이 있음 → 해당 차량번호를 스킬로 추가 (hard constraint)
     * - 담당차량이 없음 → 미배차 스킬 추가
     */
    public List<String> getSkillsForSpecialCondition(boolean isOnSkills) {
        List<String> result = new ArrayList<>();

        boolean needsDedicatedVehicle = (isOnSkills && isSpecial()) || isKeyTypeRequiresDedicatedVehicle();

        if (needsDedicatedVehicle) {
            if (!ObjectUtils.isEmpty(carno)) {
                // 담당차량이 있으면 해당 차량번호를 스킬로 추가 (hard constraint)
                result.add(carno);
            } else {
                logUnassignedReason(isOnSkills);
                result.add(UNASSIGNED_SPECIAL_CONDITION_SKILL);
            }
        }

        return result;
    }

    /**
     * 특수조건/출입KEY 여부 확인
     */
    public boolean needsDedicatedVehicle(boolean isOnSkills) {
        return (isOnSkills && isSpecial()) || isKeyTypeRequiresDedicatedVehicle();
    }

    /**
     * 미배차 사유 로깅
     */
    private void logUnassignedReason(boolean isOnSkills) {
        if (isOnSkills && isSpecial()) {
            log.info("특수조건이 설정되었으나 담당차량이 없어 미배차 처리됩니다. 실착지: {}({})",
                    this.truthCustKey, this.custName);
        }
        if (isKeyTypeRequiresDedicatedVehicle()) {
            log.info("출입KEY({})가 고정차량 배차를 필요로 하나 담당차량이 없어 미배차 처리됩니다. 실착지: {}({})",
                    this.passwordTypeCd, this.truthCustKey, this.custName);
        }
    }

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH:mm");
    public List<Long> getOtdTime(String deliveryDate) {
        long min = 0L;
        long max = Long.MAX_VALUE;
        LocalDateTime from = null, to = null;
        if (!ObjectUtils.isEmpty(otdTimeFrom)) {
            from = convertFormat(deliveryDate + otdTimeFrom);
            min = from.atZone(SEOUL_ZONE_ID).toEpochSecond();
        }
        if (!ObjectUtils.isEmpty(otdTimeTo)) {
            to = convertFormat(deliveryDate + otdTimeTo);
            max = to.atZone(SEOUL_ZONE_ID).toEpochSecond();
        }

        if (from != null && to != null) {
            // to가 00:00 이 아닌 경우에만 비교
            // boolean isToMidnight = to.toLocalTime().equals(LocalTime.MIDNIGHT);

            if (to.isBefore(from)) {
                // from은 전일
                from = from.minusDays(1);
                min = from.atZone(SEOUL_ZONE_ID).toEpochSecond();
            }
        }

        return Arrays.asList(min, max);
    }

    private LocalDateTime convertFormat(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    public List<Integer> getAmount(TmPlanOption planOption) {

        List<Integer> capacity = new ArrayList<>();

        // 배차옵션 최대적재중량
        if (planOption.isOnMaxCbm()) {
            capacity.add(TmPlanCommon.toIntMin1(cube, 1));
        }
        // 배차옵션 최대중량
        capacity.add(TmPlanCommon.toIntMin1(weight, 1));

        // 배차 옵션 최대 착지수 설정시 차량에 설정된 최대착지수 설정하고 OFF 인경우 제한 없음
        if (planOption.isOnMaxLocation()) {
            capacity.add(1);
        }

        return capacity;
    }

    /**
     * Otd시간 체크
     */
    public boolean checkOtdTime() {
        if (ObjectUtils.isEmpty(otdTimeFrom)) {
            return true;
        }

        if (ObjectUtils.isEmpty(otdTimeTo)) {
            return true;
        }

        return !otdTimeFrom.equals(otdTimeTo);
    }

}
