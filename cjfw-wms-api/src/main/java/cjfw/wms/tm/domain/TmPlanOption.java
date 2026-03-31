package cjfw.wms.tm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.dto.TmDispatchOptionsReqDto;
import cjfw.wms.tm.entity.TmPlanOptionEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : parkeunkyung (ekmona@cj.net) 
 * @date : 2025.12.10
 * @description : 배차 옵션 설정 도메인 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.10 parkeunkyung (ekmona@cj.net) 생성
 */
@Slf4j
@Setter
@Getter
@ToString
public class TmPlanOption {
	
	public static final String IS_MULTI_TURN_CD		    = "IS_MULTI_TURN";
    public static final String MAX_CBM_CD 				= "MAX_CBM";
    public static final String MAX_POP_COUNT_CD 		= "MAX_POP_COUNT";
    public static final String MAX_TRUTH_CUST_COUNT_CD = "MAX_TRUTH_CUST_COUNT";
    public static final String MAX_WEIGHT_CD 			= "MAX_WEIGHT";
    public static final String SPECIAL_CONDITIONS_CD 	= "SPECIAL_CONDITIONS";

    // 톤급별 CBM 허용비율 옵션 코드
    public static final String MAX_CBM_1_CD 			= "MAX_CBM_1";
    public static final String MAX_CBM_1_4_CD 			= "MAX_CBM_1.4";
    public static final String MAX_CBM_2_5_CD 			= "MAX_CBM_2.5";
    public static final String MAX_CBM_3_5_CD 			= "MAX_CBM_3.5";
    public static final String MAX_CBM_5_CD 			= "MAX_CBM_5";
    public static final String MAX_CBM_11_CD 			= "MAX_CBM_11";

    public static final String USE_Y 					= "Y";
    public static final String USE_N 					= "N";
    private static final double CBM_OFFSET_HIGH         = 500;
    private static final double CBM_OFFSET_LOW          = -100;
    private static final int POP_HIGH                   = 50;
    private static final int POP_LOW                    = 1;

    /** CBM 허용비율 기본값 (%) */
    private static final int CBM_RATIO_DEFAULT          = 100;

	/** 최대 중량 사용 여부 */
    private boolean isOnMaxWeight = false;
    
    /** 스킬 사용 여부 SPECIAL_CONDITIONS*/
    private boolean isOnSkills = false;

    /** 최대 착지 사용 여부 MAX_TRUTH_CUST_COUNT*/
    private boolean isOnMaxLocation = false;

    /** 라운드 사용 여부 IS_MULTI_TURN */
    private boolean isOnMultiTurn = false;

    /** 최대 적재량 사용여부 MAX_CBM */
	private boolean isOnMaxCbm = false;

    /** CBM 증차감 조정 */
    private double cbmOffset;

    /** 최대 POP 개수 사용여부 MAX_POP_COUNT */
	private boolean isOnMaxPopCount = false;

    /** 최대 POP 사용 시, POP 수 */
    private int popCount;

    /** 톤급별 CBM 허용비율 (%) */
    private Integer cbmRatio1;      // 1톤
    private Integer cbmRatio1_4;    // 1.4톤
    private Integer cbmRatio2_5;    // 2.5톤
    private Integer cbmRatio3_5;    // 3.5톤
    private Integer cbmRatio5;      // 5톤
    private Integer cbmRatio11;     // 11톤

    /**
     * 차량 톤급에 해당하는 CBM 허용비율 반환
     * @param carCapacity 차량 톤급 (예: "1", "1.4", "2.5", "3.5", "5", "11")
     * @return CBM 허용비율 (%), 기본값 100
     */
    public int getCbmRatioByTon(String carCapacity) {
        if (carCapacity == null) {
            return CBM_RATIO_DEFAULT;
        }

        return switch (carCapacity.trim()) {
            case "1"   -> cbmRatio1 != null ? cbmRatio1 : CBM_RATIO_DEFAULT;
            case "1.4" -> cbmRatio1_4 != null ? cbmRatio1_4 : CBM_RATIO_DEFAULT;
            case "2.5" -> cbmRatio2_5 != null ? cbmRatio2_5 : CBM_RATIO_DEFAULT;
            case "3.5" -> cbmRatio3_5 != null ? cbmRatio3_5 : CBM_RATIO_DEFAULT;
            case "5"   -> cbmRatio5 != null ? cbmRatio5 : CBM_RATIO_DEFAULT;
            case "11"  -> cbmRatio11 != null ? cbmRatio11 : CBM_RATIO_DEFAULT;
            default    -> CBM_RATIO_DEFAULT;
        };
    }

	public static TmPlanOption of(List<TmPlanOptionEntity> entityList) {
		TmPlanOption options = new TmPlanOption();

		if (entityList == null || entityList.isEmpty()) {
			return options;
		}
		
		Map<String, TmPlanOptionEntity> optionMap = entityList.stream()
			.collect(Collectors.toMap(
				TmPlanOptionEntity::getPlanOptionCd,
				entity -> entity,
				(existing, replacement) -> existing
			));
		
		for (Map.Entry<String, TmPlanOptionEntity> entry : optionMap.entrySet()) {
			String optionCd = entry.getKey();
			TmPlanOptionEntity entity = entry.getValue();
			boolean isEnabled = USE_Y.equals(entity.getUseYn());
			
			switch (optionCd) {
				case IS_MULTI_TURN_CD:
					options.setOnMultiTurn(isEnabled);
					break;
				case MAX_CBM_CD:
					options.setOnMaxCbm(isEnabled);
					if (ObjectUtils.isNotEmpty(entity.getSetValue())) {
						options.setCbmOffset(Double.parseDouble(entity.getSetValue()));
					}
					break;
				case MAX_POP_COUNT_CD:
					options.setOnMaxPopCount(isEnabled);
					if (ObjectUtils.isNotEmpty(entity.getSetValue())) {
						options.setPopCount(Integer.parseInt(entity.getSetValue()));
					}
					break;
				case MAX_TRUTH_CUST_COUNT_CD:
					options.setOnMaxLocation(isEnabled);
					break;
				case MAX_WEIGHT_CD:
					options.setOnMaxWeight(isEnabled);
					break;
				case SPECIAL_CONDITIONS_CD:
					options.setOnSkills(isEnabled);
					break;
				// 톤급별 CBM 허용비율
				case MAX_CBM_1_CD:
					options.setCbmRatio1(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				case MAX_CBM_1_4_CD:
					options.setCbmRatio1_4(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				case MAX_CBM_2_5_CD:
					options.setCbmRatio2_5(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				case MAX_CBM_3_5_CD:
					options.setCbmRatio3_5(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				case MAX_CBM_5_CD:
					options.setCbmRatio5(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				case MAX_CBM_11_CD:
					options.setCbmRatio11(parseIntOrDefault(entity.getSetValue(), CBM_RATIO_DEFAULT));
					break;
				default : {
			      log.info("알 수 없는 옵션 코드: {}", optionCd);
			    }
			}
		}

		return options;
	}

    public static List<TmPlanOptionEntity> toEntity(TmDispatchOptionsReqDto dto) {
        List<TmPlanOptionEntity> list = new ArrayList<>();

        // convert to entity
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.IS_MULTI_TURN_CD)
            .useYn(dto.getUseRounds() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue("0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.MAX_CBM_CD)
            .useYn(dto.getUseMaxCbm() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue(dto.getUseMaxCbm() ? String.valueOf(dto.getOffsetCbm()) : "0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.MAX_POP_COUNT_CD)
            .useYn(dto.getUseMaxPop() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue(dto.getUseMaxPop() ? String.valueOf(dto.getPopCount()) : "0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.MAX_TRUTH_CUST_COUNT_CD) // 최대 착지수수
            .useYn(dto.getUseMaxLocation() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue("0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.SPECIAL_CONDITIONS_CD)
            .useYn(dto.getUseSkills() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue("0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(TmPlanOption.MAX_WEIGHT_CD)
            .useYn(dto.getUseMaxWeight() ? TmPlanOption.USE_Y : TmPlanOption.USE_N)
            .setValue("0")
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());

        // 톤급별 CBM 허용비율 (값이 있는 경우에만 저장)
        addCbmRatioEntity(list, MAX_CBM_1_CD, dto.getCbmRatio1(), dto);
        addCbmRatioEntity(list, MAX_CBM_1_4_CD, dto.getCbmRatio1_4(), dto);
        addCbmRatioEntity(list, MAX_CBM_2_5_CD, dto.getCbmRatio2_5(), dto);
        addCbmRatioEntity(list, MAX_CBM_3_5_CD, dto.getCbmRatio3_5(), dto);
        addCbmRatioEntity(list, MAX_CBM_5_CD, dto.getCbmRatio5(), dto);
        addCbmRatioEntity(list, MAX_CBM_11_CD, dto.getCbmRatio11(), dto);

        return list;
    }

    /**
     * 톤급별 CBM 허용비율 Entity 추가 (값이 있는 경우에만)
     */
    private static void addCbmRatioEntity(List<TmPlanOptionEntity> list, String optionCd, Integer ratio, TmDispatchOptionsReqDto dto) {
        if (ratio == null) {
            return;
        }
        list.add(TmPlanOptionEntity.builder()
            .planOptionCd(optionCd)
            .useYn(USE_Y)
            .setValue(String.valueOf(ratio))
            .dccode(dto.getDccode())
            .planOptionType(dto.getPlanOptionType())
            .editWho(dto.getGUserId())
            .build());
    }

    public static TmDispatchOptionsReqDto toDto(List<TmPlanOptionEntity> entityList) {
        TmPlanOption option = TmPlanOption.of(entityList);
		return TmDispatchOptionsReqDto.builder()
                    .useRounds(option.isOnMultiTurn())
                    .useMaxCbm(option.isOnMaxCbm())
                    .offsetCbm(option.getCbmOffset())
                    .useMaxPop(option.isOnMaxPopCount())
                    .popCount(option.getPopCount())
                    .useMaxLocation(option.isOnMaxLocation())
                    .useMaxWeight(option.isOnMaxWeight())
                    .useSkills(option.isOnSkills())
                    .dccode(entityList.get(0).getDccode())
                    .planOptionType(entityList.get(0).getPlanOptionType())
                    // 톤급별 CBM 허용비율
                    .cbmRatio1(option.getCbmRatio1())
                    .cbmRatio1_4(option.getCbmRatio1_4())
                    .cbmRatio2_5(option.getCbmRatio2_5())
                    .cbmRatio3_5(option.getCbmRatio3_5())
                    .cbmRatio5(option.getCbmRatio5())
                    .cbmRatio11(option.getCbmRatio11())
                    .build();
    }

    /**
     * 문자열을 정수로 파싱, 실패 시 기본값 반환
     */
    private static Integer parseIntOrDefault(String value, int defaultValue) {
        if (ObjectUtils.isEmpty(value)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void validateInputValues(List<TmPlanOptionEntity> defaultOptions, List<TmPlanOptionEntity> optionList) {
        Map<String, TmPlanOptionEntity> defaultOptionMap = defaultOptions.stream()
             .collect(Collectors.toMap(TmPlanOptionEntity::getPlanOptionCd, value -> value));

        if (defaultOptionMap.containsKey(TmPlanOption.MAX_CBM_CD)) {
            TmPlanOptionEntity defaultOption = defaultOptionMap.get(TmPlanOption.MAX_CBM_CD);

            double baseValue = ObjectUtils.isEmpty(defaultOption.getLimitHigh()) ? CBM_OFFSET_HIGH : Double.parseDouble(defaultOption.getLimitHigh());
//            if (ObjectUtils.isEmpty(option.getLimitValue())) {
//                throw new UserHandleException("CBM 증감량 기준값이 존재하지 않습니다.");
//            }

            TmPlanOptionEntity newOption = optionList.stream().filter(item ->
                    TmPlanOption.MAX_CBM_CD.equals(item.getPlanOptionCd())).findFirst().orElse(null);
            if (!ObjectUtils.isEmpty(newOption) && USE_Y.equals(newOption.getUseYn())) {
                double value = ObjectUtils.isEmpty(newOption.getSetValue()) ? 0 : Double.parseDouble(newOption.getSetValue());
                if (baseValue < value) {
                    throw new UserHandleException("CBM 증감량이 기준값보다 큽니다. 기준값: " + baseValue);
                }
                baseValue = ObjectUtils.isEmpty(defaultOption.getLimitLow()) ? CBM_OFFSET_LOW : Double.parseDouble(defaultOption.getLimitLow());
                if (baseValue > value) {
                    throw new UserHandleException("CBM 증감량이 기준값보다 작습니다. 기준값: " + baseValue);
                }
            }
        }

        if (defaultOptionMap.containsKey(TmPlanOption.MAX_POP_COUNT_CD)) {
            TmPlanOptionEntity option = defaultOptionMap.get(TmPlanOption.MAX_POP_COUNT_CD);

            int baseValue = ObjectUtils.isEmpty(option.getLimitHigh()) ? POP_HIGH : Integer.parseInt(option.getLimitHigh());
//            if (ObjectUtils.isEmpty(option.getLimitValue())) {
//                throw new UserHandleException("최대 POP 기준값이 존재하지 않습니다.");
//            }

            TmPlanOptionEntity newOption = optionList.stream().filter(item ->
                    TmPlanOption.MAX_POP_COUNT_CD.equals(item.getPlanOptionCd())).findFirst().orElse(null);
            if (!ObjectUtils.isEmpty(newOption) && USE_Y.equals(newOption.getUseYn())) {
                int value = ObjectUtils.isEmpty(newOption.getSetValue()) ? POP_HIGH : Integer.parseInt(newOption.getSetValue());
                if (baseValue < value) {
                    throw new UserHandleException("최대 POP가 기준값보다 큽니다. 기준값: " + baseValue);
                }
                baseValue = ObjectUtils.isEmpty(option.getLimitLow()) ? POP_LOW : Integer.parseInt(option.getLimitLow());
                if (baseValue > value) {
                    throw new UserHandleException("최대 POP가 기준값보다 작습니다. 기준값: " + baseValue);
                }
            }
        }

    }
}
