package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cjfw.wms.common.annotation.MaskingName;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.10
 * @description : 운송비정산서 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "운송비정산서 조회 결과")
public class TmTrxCalculationReportResDto extends CommonDto {

    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = true, example = "")
    private Integer serialkey;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = true, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = true, example = "")
    private String storerkey;
    
    /** 정산일자 */
    @Schema(description = "정산일자", nullable = true, example = "")
    private String sttlDate;
    
    /** 정산구분 (배송/수송/조달) */
    @Schema(description = "정산구분", nullable = true, example = "")
    private String sttlType;
    
    /** 정산구분명 */
    @Schema(description = "정산구분명", nullable = true, example = "")
    private String sttlTypeName;
    
    /** 차량번호 */
    @Schema(description = "차량번호", nullable = true, example = "")
    private String carno;
    
    /** 계약유형 */
    @Schema(description = "계약유형", nullable = true, example = "")
    private String contracttype;    
    
    /** 계약유형명 (지입/고정/임시/실비) */
    @Schema(description = "계약유형명", nullable = true, example = "")
    private String contracttypeName;
    
    /** 운송사 */
    @Schema(description = "운송사", nullable = true, example = "")
    private String courier;
    
    /** 운송사명 */
    @Schema(description = "운송사명", nullable = true, example = "")
    private String courierName;
    
    /** 2차운송사 */
    @Schema(description = "2차운송사", nullable = false, example = "")
    private String slaveCourier;
    
    /** 2차운송사명 */
    @Schema(description = "2차운송사명", nullable = false, example = "")
    private String slaveCourierName;
    
    /** 마감유형명 */
    @Schema(description = "마감유형명", nullable = true, example = "")
    private String closetypeName;
    
    /** 주행거리 */
    @Schema(description = "주행거리", nullable = true, example = "")
    private BigDecimal drivedistance;
    
    /** 보조원여부 */
    @Schema(description = "보조원여부", nullable = true, example = "")
    private String subdriverYn;
    
    /** 기준근무일수 */
    @Schema(description = "기준근무일수", nullable = true, example = "")
    private BigDecimal workDay;

    /** 수송노선ID */
    @Schema(description = "수송노선ID", nullable = true, example = "")
    private String carrierRouteId;

    /** 총착지수 */
    @Schema(description = "총착지수", nullable = true, example = "")
    private BigDecimal totDestCnt;    

    /** 급식착지수 */
    @Schema(description = "급식착지수", nullable = true, example = "")
    private BigDecimal ctrngDestCnt;
    
    /** 외식착지수 */
    @Schema(description = "외식착지수", nullable = true, example = "")
    private BigDecimal dinoutDestCnt;
    
    /** 권역이동수 */
    @Schema(description = "권역이동수", nullable = true, example = "")
    private BigDecimal regnVoveCnt;
    
    /** 결행대체차량번호 */
    @Schema(description = "결행대체차량번호", nullable = true, example = "")
    private String altCarNo;    

    /** 결행대체금액 */
    @Schema(description = "결행대체금액", nullable = true, example = "")
    private BigDecimal altAmount;    
    
    /** 수수료율 */
    @Schema(description = "수수료율", nullable = true, example = "")
    private BigDecimal feeRt;

    /** 마감여부 */
    @Schema(description = "마감여부", nullable = true, example = "")
    private String closeyn;
    
    /** 기사명 */
    @MaskingName
    @Schema(description = "기사명", nullable = true, example = "")
    private String driverName;
    
    /** 차량톤수 */
    @Schema(description = "차량톤수", nullable = true, example = "")
    private String tonName;
    
    /** 배송물량 */
    @Schema(description = "배송물량", nullable = true, example = "")
    private String weightKg;
    
    /** 정산금액 */
    @Schema(description = "정산금액", nullable = true, example = "")
    private BigDecimal sttlAmount;

    /** 기본급 */
    @Schema(description = "기본급", nullable = true, example = "")
    private BigDecimal baseAmount;
    
    /** 수수료 */
    @Schema(description = "수수료", nullable = true, example = "")
    private BigDecimal feeAmount;
    
    /** 패널티 */
    @Schema(description = "패널티", nullable = true, example = "")
    private BigDecimal penaltyAmount;
    
    /** 무급휴무공제 -> 확인할 사항 */
    @Schema(description = "무급휴무공제", nullable = true, example = "")
    private BigDecimal unpaidAmount;
    
    /** 무급휴무일수 -> 확인할 사항 */
    @Schema(description = "무급휴무일수", nullable = true, example = "")
    private BigDecimal unpaidDays;
    
    /** 검사일수 -> 확인할 사항 */
    @Schema(description = "검사", nullable = true, example = "")
    private BigDecimal inspectDays;
    
    /** 검사수당 -> 확인할 사항 */
    @Schema(description = "검사수당", nullable = true, example = "")
    private BigDecimal inspectAllowance;
    
    /** 교육일수 -> 확인할 사항 */
    @Schema(description = "교육", nullable = true, example = "")
    private BigDecimal educationDays;
    
    /** 교육수당 -> 확인할 사항 */
    @Schema(description = "교육수당", nullable = true, example = "")
    private BigDecimal educationAllowance;
    
    /** 휴일일수 -> 확인할 사항 */
    @Schema(description = "휴일", nullable = true, example = "")
    private BigDecimal holidayDays;
    
    /** 휴일수당 -> 확인할 사항 */
    @Schema(description = "휴일수당", nullable = true, example = "")
    private BigDecimal holidayAllowance;
    
    /** 회전수당 */
    @Schema(description = "회전수당", nullable = true, example = "")
    private BigDecimal roundAllowance;
    
    /** 보조원수당 */
    @Schema(description = "보조원수당", nullable = true, example = "")
    private BigDecimal subdriverAllowance;
    
    /** 조장수당 */
    @Schema(description = "조장수당", nullable = true, example = "")
    private BigDecimal bossAllowance;
    
    /** 격오지/장거리수당 */
    @Schema(description = "격오지/장거리수당", nullable = true, example = "")
    private BigDecimal distanceAllowance;
    
    /** 하차난이도수당 */
    @Schema(description = "하차난이도수당", nullable = true, example = "")
    private BigDecimal diffcultAllowance;
    
    /** 대면검수수당 */
    @Schema(description = "대면검수수당", nullable = true, example = "")
    private BigDecimal faceAllowance;
    
    /** 키즈분류수당 */
    @Schema(description = "키즈분류수당", nullable = true, example = "")
    private BigDecimal kidsAllowance;
    
    /** 통행료 */
    @Schema(description = "통행료", nullable = true, example = "")
    private BigDecimal tollCharge;
    
    /** 주차비 */
    @Schema(description = "주차비", nullable = true, example = "")
    private BigDecimal parkingCharge;
    
    /** 세차비 */
    @Schema(description = "세차비", nullable = true, example = "")
    private BigDecimal washCharge;
    
    /** 물량 인센티브 */
    @Schema(description = "물량 인센티브", nullable = true, example = "")
    private BigDecimal weigthIncentive;
    
    /** 착지 인센티브 */
    @Schema(description = "착지 인센티브", nullable = true, example = "")
    private BigDecimal destIncentive;
    
    /** 거리 인센티브 */
    @Schema(description = "거리 인센티브", nullable = true, example = "")
    private BigDecimal distanceIncentive;
    
    /** 권역이동 인센티브 */
    @Schema(description = "권역이동 인센티브", nullable = false, example = "")
    private BigDecimal zoneIncentive;
    
    /** 신차지원금 인센티브 */
    @Schema(description = "신차지원금 인센티브", nullable = false, example = "")
    private BigDecimal newcarIncentive;
    
    /** 통신비 */
    @Schema(description = "통신비", nullable = true, example = "")
    private BigDecimal communicationExpense;
    
    /** 폐기공제 */
    @Schema(description = "폐기공제", nullable = false, example = "")
    private BigDecimal disuseDeduction;
    
    /** 퀵공제 */
    @Schema(description = "퀵공제", nullable = false, example = "")
    private BigDecimal quickDeduction;
    
    /** 유류비 */
    @Schema(description = "유류비", nullable = true, example = "")
    private BigDecimal fuelExpense;
    
    /** 정산누락 및 소급분 */
    @Schema(description = "정산누락 및 소급분", nullable = true, example = "")
    private BigDecimal retroactiveExpense;
    
    /** 기타수당 */
    @Schema(description = "기타수당", nullable = true, example = "")
    private BigDecimal etcAllowance;
    
    /** 조달수당 */
    @Schema(description = "조달수당", nullable = false, example = "")
    private BigDecimal procureAllowance;
    
    /** 이메일 */
    @Schema(description = "이메일", nullable = true, example = "")
    private String email;
    
    /** 배송일수 */
    @Schema(description = "배송일수", nullable = false, example = "")
    private BigDecimal deliverydaycnt;
  
}
