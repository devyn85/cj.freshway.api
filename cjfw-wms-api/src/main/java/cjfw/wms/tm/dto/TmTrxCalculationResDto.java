package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.02
 * @description : 운송비정산 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운송비정산 조회 결과")
public class TmTrxCalculationResDto extends CommonDto {

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private Integer serialkey;
    
    /** 상위 데이터번호 */
    @Schema(description = "상위 데이터번호", nullable = false, example = "")
    private Integer serialkeyH;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;
    
    /** 정산일자 */
    @Schema(description = "정산일자", nullable = false, example = "")
    private String sttlDate;
    
    /** 정산구분 (배송/수송/조달) */
    @Schema(description = "정산구분", nullable = false, example = "")
    private String sttlType;
    
    /** 정산구분명 */
    @Schema(description = "정산구분명", nullable = false, example = "")
    private String sttlTypeName;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = false, example = "")
    private String carno;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = false, example = "")
    private String contracttype;    
    
    /** 계약유형명 (지입/고정/임시/실비) */
    @Schema(description = "계약유형명", nullable = false, example = "")
    private String contracttypeName;
    
    /** 운송사 */
    @Schema(description = "운송사", nullable = false, example = "")
    private String courier;
    
    /** 운송사명 */
    @Schema(description = "운송사명", nullable = false, example = "")
    private String courierName;
    
    /** 2차운송사 */
    @Schema(description = "2차운송사", nullable = false, example = "")
    private String slaveCourier;
    
    /** 2차운송사명 */
    @Schema(description = "2차운송사명", nullable = false, example = "")
    private String slaveCourierName;
    
    /** 마감유형명 */
    @Schema(description = "마감유형명", nullable = false, example = "")
    private String closetypeName;
    
    /** 주행거리 */
    @Schema(description = "주행거리", nullable = false, example = "")
    private BigDecimal drivedistance;
    
    /** 보조원여부 */
    @Schema(description = "보조원여부", nullable = false, example = "")
    private String subdriverYn;
    
    /** 기준근무일수 */
    @Schema(description = "기준근무일수", nullable = false, example = "")
    private BigDecimal workDay;

    /** 수송노선ID */
    @Schema(description = "수송노선ID", nullable = false, example = "")
    private String carrierRouteId;

    /** 총착지수 */
    @Schema(description = "총착지수", nullable = false, example = "")
    private BigDecimal totDestCnt; 

    /** 급식착지수 */
    @Schema(description = "급식착지수", nullable = false, example = "")
    private BigDecimal ctrngDestCnt;
    
    /** 외식착지수 */
    @Schema(description = "외식착지수", nullable = false, example = "")
    private BigDecimal dinoutDestCnt;
    
    /** 권역이동수 */
    @Schema(description = "권역이동수", nullable = false, example = "")
    private BigDecimal regnMoveCnt;
    
    /** 결행대체차량번호 */
    @Schema(description = "결행대체차량번호", nullable = false, example = "")
    private String altCarNo;    

    /** 결행대체금액 */
    @Schema(description = "결행대체금액", nullable = false, example = "")
    private BigDecimal altAmount;    
    
    /** 수수료율 */
    @Schema(description = "수수료율", nullable = false, example = "")
    private BigDecimal feeRt;

    /** 마감여부 */
    @Schema(description = "마감여부", nullable = false, example = "")
    private String closeyn;
    
    /** 기사명 */
    @MaskingName
    @Schema(description = "기사명")
    private String driverName;
    
    /** 차량톤수 */
    @Schema(description = "차량톤수", nullable = false, example = "")
    private String tonName;
    
    /** 배송물량 */
    @Schema(description = "배송물량", nullable = false, example = "")
    private String weightKg;
    
    /** 정산금액 */
    @Schema(description = "정산금액", nullable = false, example = "")
    private BigDecimal sttlAmount;

    /** 기본급 */
    @Schema(description = "기본급", nullable = false, example = "")
    private BigDecimal baseAmount;
    
    /** 수수료 */
    @Schema(description = "수수료", nullable = false, example = "")
    private BigDecimal feeAmount;
    
    /** 패널티 */
    @Schema(description = "패널티", nullable = false, example = "")
    private BigDecimal penaltyAmount;
    
    /** 무급휴무공제 -> 확인할 사항 */
    @Schema(description = "무급휴무공제", nullable = false, example = "")
    private BigDecimal unpaidAmount;
    
    /** 무급휴무일수 -> 확인할 사항 */
    @Schema(description = "무급휴무일수", nullable = false, example = "")
    private BigDecimal unpaidDays;
    
    /** 검사일수 -> 확인할 사항 */
    @Schema(description = "검사", nullable = false, example = "")
    private BigDecimal inspectDays;
    
    /** 검사수당 -> 확인할 사항 */
    @Schema(description = "검사수당", nullable = false, example = "")
    private BigDecimal inspectAllowance;
    
    /** 교육일수 -> 확인할 사항 */
    @Schema(description = "교육", nullable = false, example = "")
    private BigDecimal educationDays;
    
    /** 교육수당 -> 확인할 사항 */
    @Schema(description = "교육수당", nullable = false, example = "")
    private BigDecimal educationAllowance;
    
    /** 휴일일수 -> 확인할 사항 */
    @Schema(description = "휴일", nullable = false, example = "")
    private BigDecimal holidayDays;
    
    /** 휴일수당 -> 확인할 사항 */
    @Schema(description = "휴일수당", nullable = false, example = "")
    private BigDecimal holidayAllowance;
    
    /** 회전수당 */
    @Schema(description = "회전수당", nullable = false, example = "")
    private BigDecimal roundAllowance;
    
    /** 보조원수당 */
    @Schema(description = "보조원수당", nullable = false, example = "")
    private BigDecimal subdriverAllowance;
    
    /** 조장수당 */
    @Schema(description = "조장수당", nullable = false, example = "")
    private BigDecimal bossAllowance;
    
    /** 격오지/장거리수당 */
    @Schema(description = "격오지/장거리수당", nullable = false, example = "")
    private BigDecimal distanceAllowance;
    
    /** 하차난이도수당 */
    @Schema(description = "하차난이도수당", nullable = false, example = "")
    private BigDecimal diffcultAllowance;
    
    /** 대면검수수당 */
    @Schema(description = "대면검수수당", nullable = false, example = "")
    private BigDecimal faceAllowance;
    
    /** 키즈분류수당 */
    @Schema(description = "키즈분류수당", nullable = false, example = "")
    private BigDecimal kidsAllowance;
    
    /** 통행료 */
    @Schema(description = "통행료", nullable = false, example = "")
    private BigDecimal tollCharge;
    
    /** 주차비 */
    @Schema(description = "주차비", nullable = false, example = "")
    private BigDecimal parkingCharge;
    
    /** 세차비 */
    @Schema(description = "세차비", nullable = false, example = "")
    private BigDecimal washCharge;
    
    /** 물량 인센티브 */
    @Schema(description = "물량 인센티브", nullable = false, example = "")
    private BigDecimal weigthIncentive;
    
    /** 착지 인센티브 */
    @Schema(description = "착지 인센티브", nullable = false, example = "")
    private BigDecimal destIncentive;
    
    /** 거리 인센티브 */
    @Schema(description = "거리 인센티브", nullable = false, example = "")
    private BigDecimal distanceIncentive;
    
    /** 권역이동 인센티브 */
    @Schema(description = "권역이동 인센티브", nullable = false, example = "")
    private BigDecimal zoneIncentive;
    
    /** 신차지원금 인센티브 */
    @Schema(description = "신차지원금 인센티브", nullable = false, example = "")
    private BigDecimal newcarIncentive;
    
    /** 통신비 */
    @Schema(description = "통신비", nullable = false, example = "")
    private BigDecimal communicationExpense;
    
    /** 유류비 */
    @Schema(description = "유류비", nullable = false, example = "")
    private BigDecimal fuelExpense;
    
    /** 폐기공제 */
    @Schema(description = "폐기공제", nullable = false, example = "")
    private BigDecimal disuseDeduction;
    
    /** 퀵공제 */
    @Schema(description = "퀵공제", nullable = false, example = "")
    private BigDecimal quickDeduction;
    
    /** 정산누락 및 소급분 */
    @Schema(description = "정산누락 및 소급분", nullable = false, example = "")
    private BigDecimal retroactiveExpense;
    
    /** 기타수당 */
    @Schema(description = "기타수당", nullable = false, example = "")
    private BigDecimal etcAllowance;
    
    /** 조달수당 */
    @Schema(description = "조달수당", nullable = false, example = "")
    private BigDecimal procureAllowance;
    
    /** 배송일수 */
    @Schema(description = "배송일수", nullable = false, example = "")
    private BigDecimal deliverydaycnt;
    
    /** 차수 */
    @Schema(description = "차수", nullable = false, example = "")
    private BigDecimal priority;
    
    /** 수당 */
    @Schema(description = "수당", nullable = false, example = "")
    private BigDecimal amount;
    
    /** 기본급 단가등록여부 */
    @Schema(description = "기본급 단가등록여부", nullable = false, example = "")
    private String baseAmountYn;
    
    /** 수수료 단가등록여부 */
    @Schema(description = "수수료 단가등록여부", nullable = false, example = "")
    private String feeAmountYn;
    
    /** 패널티 단가등록여부 */
    @Schema(description = "패널티 단가등록여부", nullable = false, example = "")
    private String penaltyAmountYn;
    
    /** 무급휴무 단가등록여부 */
    @Schema(description = "무급휴무 단가등록여부", nullable = false, example = "")
    private String unpaidAmountYn;
    
    /** 휴일 단가등록여부 */
    @Schema(description = "휴일 단가등록여부", nullable = false, example = "")
    private String holidayAllowanceYn;
    
    /** 회전수당 단가등록여부 */
    @Schema(description = "회전수당 단가등록여부", nullable = false, example = "")
    private String roundAllowanceYn;
    
    /** 보조원수당 단가등록여부 */
    @Schema(description = "보조원수당 단가등록여부", nullable = false, example = "")
    private String subdriverAllowanceYn;
    
    /** 조장수당 단가등록여부 */
    @Schema(description = "조장수당 단가등록여부", nullable = false, example = "")
    private String bossAllowanceYn;
    
    /** 격오지/장거리 단가등록여부 */
    @Schema(description = "격오지/장거리 단가등록여부", nullable = false, example = "")
    private String distanceAllowanceYn;
    
    /** 하차난이도수당 단가등록여부 */
    @Schema(description = "하차난이도수당 단가등록여부", nullable = false, example = "")
    private String diffcultAllowanceYn;
    
    /** 대면검수수당 단가등록여부 */
    @Schema(description = "대면검수수당 단가등록여부", nullable = false, example = "")
    private String faceAllowanceYn;
    
    /** 키즈분류수당 단가등록여부 */
    @Schema(description = "키즈분류수당 단가등록여부", nullable = false, example = "")
    private String kidsAllowanceYn;
    
    /** 통행료 단가등록여부 */
    @Schema(description = "통행료 단가등록여부", nullable = false, example = "")
    private String tollChargeYn;
    
    /** 주차비 단가등록여부 */
    @Schema(description = "주차비 단가등록여부", nullable = false, example = "")
    private String parkingChargeYn;
    
    /** 세차비 단가등록여부 */
    @Schema(description = "세차비 단가등록여부", nullable = false, example = "")
    private String washChargeYn;
    
    /** 물량 단가등록여부 */
    @Schema(description = "물량 단가등록여부", nullable = false, example = "")
    private String weigthIncentiveYn;
    
    /** 착지 단가등록여부 */
    @Schema(description = "착지 단가등록여부", nullable = false, example = "")
    private String destIncentiveYn;
    
    /** 거리 단가등록여부 */
    @Schema(description = "거리 단가등록여부", nullable = false, example = "")
    private String distanceIncentiveYn;
    
    /** 신차지원금 단가등록여부 */
    @Schema(description = "신차지원금 단가등록여부", nullable = false, example = "")
    private String newcarIncentiveYn;
    
    /** 권역이동 단가등록여부 */
    @Schema(description = "권역이동 단가등록여부", nullable = false, example = "")
    private String zoneIncentiveYn;
    
    /** 통신비 단가등록여부 */
    @Schema(description = "통신비 단가등록여부", nullable = false, example = "")
    private String communicationExpenseYn;
    
    /** 유류비 단가등록여부 */
    @Schema(description = "유류비 단가등록여부", nullable = false, example = "")
    private String fuelExpenseYn;
  
}
