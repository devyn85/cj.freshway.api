package cjfw.wms.tm.dto;

import cjfw.wms.common.annotation.MaskingName;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.09.16
 * @description : 근태관리 기능을 구현한 RES DTO Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "근태관리 조회 resDto")
public class TmAttendanceListResDto extends CommonDto {

	private String rowStatus;
	/** 기본센터코드 */
    @Schema(description = "기본센터코드")
    private String defDccode;

    /** 운송사명 */
    @Schema(description = "운송사명")
    private String description;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 차량번호 */
    @Schema(description = "차량번호")
    private String carNo;

    /** 차량유형 */
    @Schema(description = "차량유형")
    private String carType;

    /** 기사명 */
    @MaskingName
    @Schema(description = "기사명")
    private String driverName;
    
    private String carCapacity;

    /** 근무일수 */
    @Schema(description = "근무일수")
    private Integer workDays;
    
    /**운행일수 */
    @Schema(description = "운행일수")
    private Integer workCnt;

    /** 연도 */
    @Schema(description = "연도")
    private String yy;

    /** 월 */
    @Schema(description = "월")
    private String mm;
    
    /** 2차운송사 */
    @Schema(description = "2차운송사")
    private String caragentname;
    

    /** ATTEND_CD=10 건수 */
    @Schema(description = "ATTEND_CD=10 건수")
    private Integer cnt10;

    /** ATTEND_CD=11 건수 */
    @Schema(description = "ATTEND_CD=11 건수")
    private Integer cnt11;

    /** ATTEND_CD=12 건수 */
    @Schema(description = "ATTEND_CD=12 건수")
    private Integer cnt12;

    /** ATTEND_CD=13 건수 */
    @Schema(description = "ATTEND_CD=13 건수")
    private Integer cnt13;

    /** ATTEND_CD=14 건수 */
    @Schema(description = "ATTEND_CD=14 건수")
    private Integer cnt14;

    /** ATTEND_CD=15 건수 */
    @Schema(description = "ATTEND_CD=15 건수")
    private Integer cnt15;

    /** ATTEND_CD=16 건수 */
    @Schema(description = "ATTEND_CD=16 건수")
    private Integer cnt16;

    /** ATTEND_CD=17 건수 */
    @Schema(description = "ATTEND_CD=17 건수")
    private Integer cnt17;

    /** 01일 근태코드 */
    @Schema(description = "01일 근태코드")
    private String d01;
    /** 02일 근태코드 */
    @Schema(description = "02일 근태코드")
    private String d02;
    /** 03일 근태코드 */
    @Schema(description = "03일 근태코드")
    private String d03;
    /** 04일 근태코드 */
    @Schema(description = "04일 근태코드")
    private String d04;
    /** 05일 근태코드 */
    @Schema(description = "05일 근태코드")
    private String d05;
    /** 06일 근태코드 */
    @Schema(description = "06일 근태코드")
    private String d06;
    /** 07일 근태코드 */
    @Schema(description = "07일 근태코드")
    private String d07;
    /** 08일 근태코드 */
    @Schema(description = "08일 근태코드")
    private String d08;
    /** 09일 근태코드 */
    @Schema(description = "09일 근태코드")
    private String d09;
    /** 10일 근태코드 */
    @Schema(description = "10일 근태코드")
    private String d10;
    /** 11일 근태코드 */
    @Schema(description = "11일 근태코드")
    private String d11;
    /** 12일 근태코드 */
    @Schema(description = "12일 근태코드")
    private String d12;
    /** 13일 근태코드 */
    @Schema(description = "13일 근태코드")
    private String d13;
    /** 14일 근태코드 */
    @Schema(description = "14일 근태코드")
    private String d14;
    /** 15일 근태코드 */
    @Schema(description = "15일 근태코드")
    private String d15;
    /** 16일 근태코드 */
    @Schema(description = "16일 근태코드")
    private String d16;
    /** 17일 근태코드 */
    @Schema(description = "17일 근태코드")
    private String d17;
    /** 18일 근태코드 */
    @Schema(description = "18일 근태코드")
    private String d18;
    /** 19일 근태코드 */
    @Schema(description = "19일 근태코드")
    private String d19;
    /** 20일 근태코드 */
    @Schema(description = "20일 근태코드")
    private String d20;
    /** 21일 근태코드 */
    @Schema(description = "21일 근태코드")
    private String d21;
    /** 22일 근태코드 */
    @Schema(description = "22일 근태코드")
    private String d22;
    /** 23일 근태코드 */
    @Schema(description = "23일 근태코드")
    private String d23;
    /** 24일 근태코드 */
    @Schema(description = "24일 근태코드")
    private String d24;
    /** 25일 근태코드 */
    @Schema(description = "25일 근태코드")
    private String d25;
    /** 26일 근태코드 */
    @Schema(description = "26일 근태코드")
    private String d26;
    /** 27일 근태코드 */
    @Schema(description = "27일 근태코드")
    private String d27;
    /** 28일 근태코드 */
    @Schema(description = "28일 근태코드")
    private String d28;
    /** 29일 근태코드 */
    @Schema(description = "29일 근태코드")
    private String d29;
    /** 30일 근태코드 */
    @Schema(description = "30일 근태코드")
    private String d30;
    /** 31일 근태코드 */
    @Schema(description = "31일 근태코드")
    private String d31;
    
}
