package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.10 
 * @description : 외부비축상품별수불현황 기능 reqDTO
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.10 ParkJinWoo 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StExdcTransIndicatorOListResDto {
	 /** 항목 */
    @Schema(description = "항목")
    private String item;

    /** 구분(전년도대비/STO/SO) */
    @Schema(description = "구분(전년도대비/STO/SO)")
    private String gubun;

    /** 평균 */
    @Schema(description = "평균")
    private BigDecimal avg;

    /** 누계 */
    @Schema(description = "누계")
    private BigDecimal acc;

    /** 1월 */
    @Schema(description = "1월")
    private BigDecimal m01;

    /** 2월 */
    @Schema(description = "2월")
    private BigDecimal m02;

    /** 3월 */
    @Schema(description = "3월")
    private BigDecimal m03;

    /** 4월 */
    @Schema(description = "4월")
    private BigDecimal m04;

    /** 5월 */
    @Schema(description = "5월")
    private BigDecimal m05;

    /** 6월 */
    @Schema(description = "6월")
    private BigDecimal m06;

    /** 7월 */
    @Schema(description = "7월")
    private BigDecimal m07;

    /** 8월 */
    @Schema(description = "8월")
    private BigDecimal m08;

    /** 9월 */
    @Schema(description = "9월")
    private BigDecimal m09;

    /** 10월 */
    @Schema(description = "10월")
    private BigDecimal m10;

    /** 11월 */
    @Schema(description = "11월")
    private BigDecimal m11;

    /** 12월 */
    @Schema(description = "12월")
    private BigDecimal m12;
    
}
