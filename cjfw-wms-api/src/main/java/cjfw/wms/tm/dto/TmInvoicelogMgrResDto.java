package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.06.30 
 * @description : 납품서출력로그(관리자) 조회 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.30 ParkJinWoo 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "납품서출력로그(관리자) 조회 결과 DTO")
public class TmInvoicelogMgrResDto {

    @Schema(description = "물류센터 코드")
    private String dcCode;
    
    @Schema(description = "물류센터 이름")
    private String dcName;
    @Schema(description = "위탁사(화주) 코드")
    private String storerKey;

    @Schema(description = "전표일자(SLIP_DT)")
    private String slipDt;

    @Schema(description = "전표 번호")
    private String docNo;

    @Schema(description = "고객사 코드")
    private String custKey;

    @Schema(description = "출력 데이터 01")
    private String prtData01;

    @Schema(description = "출력 데이터 02")
    private String prtData02;

    @Schema(description = "출력 데이터 03")
    private String prtData03;

    @Schema(description = "출력 데이터 04")
    private String prtData04;

    @Schema(description = "출력 데이터 05")
    private String prtData05;

    @Schema(description = "출력자(ADDWHO)")
    private String prtWho;

    @Schema(description = "출력 일시(YYYYMMDD HH24:MI:SS)")
    private String prtDate;
    
    private String prtUserNm;
    
    private String description;
    private String addWho;
}
