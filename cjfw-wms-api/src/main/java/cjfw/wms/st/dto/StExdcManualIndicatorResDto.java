package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2026.02.03 
 * @description : 수기출고 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.03 ParkJinWoo 생성
 */
@Data
@Schema(description = "수기출고현황 결과 DTO")
public class StExdcManualIndicatorResDto extends CommonDto {
    /** 구분코드(TOTAL/NORMAL/MANUAL_TOTAL/MANUAL) */
    @Schema(description = "구분코드(TOTAL/NORMAL/MANUAL_TOTAL/MANUAL)")
    private String cat;

    /** 순번(전체-1, 정상0, 수기사유1~6, 수기소계7) */
    @Schema(description = "순번(전체-1, 정상0, 수기사유1~6, 수기소계7)")
    private Integer seq;

    /** 사유코드(수기사유일 때만) */
    @Schema(description = "사유코드(수기사유일 때만)")
    private String reasoncode;

    /** 사유명(수기사유명/정상출고확정) */
    @Schema(description = "사유명(수기사유명/정상출고확정)")
    private String reasonnm;

    /** 전년 월평균(전년도 12개월 합 / 12) */
    @Schema(description = "전년 월평균(전년도 12개월 합 / 12)")
    private String prevYearAvg;

    /** 금년 누계건수(1월~기준월) */
    @Schema(description = "금년 누계건수(1월~기준월)")
    private String ytdSum;

    /** D-2 건수(기준월-2) */
    @Schema(description = "D-2 건수(기준월-2)")
    private String dMinus2Cnt;

    /** D-1 건수(기준월-1) */
    @Schema(description = "D-1 건수(기준월-1)")
    private String dMinus1Cnt;

    /** D 건수(기준월) */
    @Schema(description = "D 건수(기준월)")
    private String dCnt;

    /** 전년비(D - 전년동월) */
    @Schema(description = "전년비(D - 전년동월)")
    private String yoyDiff;
    
    /** 전년비(D - 전년동월) */
    @Schema(description = "전년비(D - 전년동월)")
    private String yoyRate;


    /** 전년평균비(D - 전년월평균) */
    @Schema(description = "전년평균비(D - 전년월평균)")
    private String vsPrevAvgDiff;

    /** 전년평균비(D - 전년월평균) */
    @Schema(description = "전년평균비(D - 전년월평균)")
    private String vsPrevAvgRate;
    
    /** 전년평균비(D - 전년월평균) */
    @Schema(description = "전년평균비(D - 전년월평균)")
    private BigDecimal ym;
    
    /** 전년평균비(D - 전년월평균) */
    @Schema(description = "전년평균비(D - 전년월평균)")
    private BigDecimal cnt;
    
    /** 전년평균비(D - 전년월평균) */
    @Schema(description = "전년평균비(D - 전년월평균)")
    private String rate;
    

    /** 문서번호 */
    @Schema(description = "문서번호")
    private String docNo;
    
    /** 상품번호 */
    @Schema(description = "상품번호")
    private String sku;
    /** 상품번호 */
    @Schema(description = "상품번호")
    private String skuName;
    /** 담당자 */
    @Schema(description = "담당자")
    private String mdName;
    /** 담당부서 */
    @Schema(description = "담당부서")
    private String depthrNm;
//    /** 사유 */
//    @Schema(description = "사유")
//    private String reasonNm;
    
}
