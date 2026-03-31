package cjfw.wms.tm.dto;

import cjfw.wms.tm.domain.TmPlanOption;
import cjfw.wms.tm.util.TmPlanCommon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.11.17 
 * @description : 실비차 배차 차량 정보 응답 DTO 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.17 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "실비차 배차 차량 정보 응답 DTO")
public class TmSetDispatchTemporaryCarResDto {
	
	@Schema(description = "실비차ID")
	private String id;
	
	@Schema(description = "조차코드")
	private String outGroupCd;
	
	@Schema(description = "용적량")
	private String maxWeight;
	
	@Schema(description = "체적")
	private String maxCbm;
	
	@Schema(description = "수량 아니고 최대 착지수")
	private String maxLoadQty;
	
	@Schema(description = "근무 시작시간")
	private String startTime;
	
	@Schema(description = "근무 종료시간")
	private String endTime;
	
	@Schema(description = "위도")
	private String latitude;
	
	@Schema(description = "경도")
	private String longitude;
	
	@Schema(description = "주행 시작시간")
	private String drivingFromdate;
	
	@Schema(description = "주행 종료시간")
	private String drivingTodate;

    /**
     * 실비차 배차 시 옵션에 따른 capacity 생성
     *  - 배차옵션 최대 적재량 OFF(제한없음) 시 엔진 전송하지 않음
     *  - 중량, 착지수는 옵션에 영향 받지 않고 입력된 정보 그대로 설정
     * @param planOption
     * @return
     */
    public List<Integer> getCapacity(TmPlanOption planOption){

        // 최대 착지수 설정
        int vehicleLanding = Integer.parseInt(maxLoadQty == null ? "10" : maxLoadQty.trim());
        double weight = Double.parseDouble(maxWeight == null ? "1000" : maxWeight.trim());
        if (planOption.isOnMaxCbm()) {
            double cbm = Double.parseDouble(maxCbm == null ? "10" : maxCbm.trim()) + planOption.getCbmOffset();

            return Arrays.asList(
                    TmPlanCommon.toIntMinCbm(cbm), // CBM 옵션 존재 시 증감 처리
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
}
