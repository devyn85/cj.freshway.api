package cjfw.wms.tm.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.10.20
 * @description : 운송비정산 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "운송비정산 조회 결과")
public class TmTrxCalculationResultResDto extends CommonDto {
    
    /** 세로 병합 */
    @Schema(description = "세로 병합")
    private String rowDist;

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
    
    /** 기사명 */
    @MaskingName
    @Schema(description = "기사명", nullable = true, example = "")
    private String driverName;
    
    /** 차량톤수 */
    @Schema(description = "차량톤수", nullable = true, example = "")
    private String tonName;
    
    /** 기준근무일수 */
    @Schema(description = "기준근무일수", nullable = true, example = "")
    private BigDecimal workDay;
    
    /** 총착지수 */
    @Schema(description = "총착지수", nullable = true, example = "")
    private BigDecimal totDestCnt;    

    /** 급식착지수 */
    @Schema(description = "급식착지수", nullable = true, example = "")
    private BigDecimal ctrngDestCnt;
    
    /** 외식착지수 */
    @Schema(description = "외식착지수", nullable = true, example = "")
    private BigDecimal dinoutDestCnt;
    
    /** 수당코드 */
    @Schema(description = "수당코드", nullable = false, example = "")
    private String allowanceCd;
    
    /** 수당코드명 */
    @Schema(description = "수당코드명", nullable = false, example = "")
    private String allowanceName;
    
    /** 수당금액 */
    @Schema(description = "수당금액", nullable = false, example = "")
    private BigDecimal amount;
    
    /** 배송물량 */
    @Schema(description = "배송물량", nullable = false, example = "")
    private String weightKg;
    
    /** 배송물량합 */
    @Schema(description = "배송물량합", nullable = false, example = "")
    private String weightKgSum;
    
    /** 배송일수 */
    @Schema(description = "배송일수", nullable = false, example = "")
    private BigDecimal deliverydtCnt;
  
    /** 차수 */
    @Schema(description = "차수", nullable = false, example = "")
    private BigDecimal priority;
    
    /** 배송일수 */
    @Schema(description = "배송일수", nullable = false, example = "")
    private BigDecimal deliverydaycnt;
    
}
