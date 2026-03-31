package cjfw.wms.tm.dto;

import java.util.Arrays;
import java.util.List;

import cjfw.core.exception.UserHandleException;
import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.24 
 * @description : 차량 배차 요청 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "차량 배차 요청 DTO")
public class TmPlanEtaVehicleReqDto {
	
    /** 차량번호 */
    @Schema(description = "차량번호", example = "경기105바1234", nullable = false)
    private String carno;

    /** 차량 유형 */
    @Schema(description = "차량 유형 (고정, 1조, 2조 등)", example = "고정", nullable = true)
    private String vehicleType;
    
    /** 조차 코드 */
    @Schema(description = "조차 코드", example = "10", nullable = true)
    private String outGroupCd;
    
    @Schema(description = "최대CBM", example = "1000", nullable = true)
    private String maxCbm;
    
    @Schema(description = "착대중량", example = "1000", nullable = true)
    private String maxWeight;
    
    @Schema(description = "착지수", example = "10", nullable = true)
    private String maxLanding;
    
    @Schema(description = "계약 유형 코드", example = "TEMPORARY", nullable = true)
    private String contractType;

    /** 경로 단계들 */
    @Schema(description = "경로 단계 목록")
    private List<TmPlanEtaStepReqDto> steps;

    /** 우선순위 */
    @Schema(description = "우선순위", example = "1")
    private Integer priority;

    /** 회차 */
    @Schema(description = "회차", example = "1")
    private Integer roundSeq;

    @Schema(description = "2회전 배차 완료 여부")
    private boolean isAlreadyRounded;

    @Schema(description = "회전 수, 다회전 배차된 차량에 대해 이후 다회전 처리 가능 여부 판단. 1: 다회전 가능, 2: 다회전 불가능")
    private int roundCount;

    /** 2회전 운행 여부 */
    private String priorityYn;

    /** m³ → cm³ 변환 상수 */
    private static final double M3_TO_CM3 = 1000000.0;

    /**
     * 실비차 배차 시 옵션에 따른 capacity 생성
     *  - 배차옵션 최대 적재량 OFF(제한없음) 시 엔진 전송하지 않음
     *  - 중량, 착지수는 옵션에 영향 받지 않고 입력된 정보 그대로 설정
     *  - 실비차는 톤급별 비율 미적용, 입력값(m³) → cm³ 변환만 수행
     * @param planOption
     * @return
     */
    public List<Integer> getCapacity(TmPlanOption planOption){

        // 최대 착지수 설정
        int vehicleLanding = Integer.parseInt(maxLanding == null ? "10" : maxLanding.trim());
        double weight = Double.parseDouble(maxWeight == null ? "1000" : maxWeight.trim());
        if (planOption.isOnMaxCbm()) {
            // 실비차: 사용자 입력값(m³) → cm³ 변환
            double cbm = Double.parseDouble(maxCbm == null ? "10" : maxCbm.trim()) * M3_TO_CM3;

            return Arrays.asList(
                    TmPlanCommon.toIntMinCbm(cbm),
                    TmPlanCommon.toIntMin1(weight),
                    vehicleLanding
            );
        } else {
            return Arrays.asList(
                    10,
                    TmPlanCommon.toIntMin1(weight),
                    vehicleLanding
            );
        }
    }

    public boolean isPriorityYn() {
        return "Y".equals(priorityYn);
    }

    public void isAvailableMultiRound() {
        if (this.isAlreadyRounded() && this.getRoundCount() >= 2) {
            throw new UserHandleException("엔진 최적화 적용되어 다회전 배차된 차량입니다. "+ this.getCarno());
        }
    }

    /**
     * [배차옵션 다회전 ON, 차량정보 2회전 ON]
     * 이중 다회전 제한 처리
     *  - 다회전 배차 처리된 차량 재계산 시 불가
     *  - 다회전 배차 처리 후 차량1 삭제 하여 재계산 시 가능
     *  클라이언트에서 동일 차량 추가 시 다회전 적용 보완 - 향후 업무 프로세스 확정 및 조차 추가시 재 확인 필요
     * @param vehicleInfo   등록 차량 정보
     * @param isOnMultiTurn 배차옵션의 다회전 여부
     * @return
     */
    public int getMaxTrips(TmVehicleInfoDto vehicleInfo, boolean isOnMultiTurn) {
        if (isOnMultiTurn && vehicleInfo.isPriorityYn()) {
            if (this.isAlreadyRounded()) {
                return checkRoundCount();
            }
            return checkRoundCount();
        }
        return 1;
    }

    private int checkRoundCount() {
        if (this.getRoundCount() <= 1)
            return 2;
        else isAvailableMultiRound();
        return 1;
    }

    public String getId() {
        return carno + "-" + (ObjectUtils.isEmpty(roundSeq) ? 1 : roundSeq);
    }
}
