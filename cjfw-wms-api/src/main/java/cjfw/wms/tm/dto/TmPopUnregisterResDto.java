package cjfw.wms.tm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.02
 * @description : 거래처별 POP 미등록 현황 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "거래처별 POP 미등록 현황 조회 결과")
public class TmPopUnregisterResDto {
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = false, example = "")
    private Long serialkey;

    /** 적용시작일자 */
    @Schema(description = "적용시작일자", nullable = false, example = "")
    private String fromdate;

    /** POP번호 */
    @Schema(description = "POP번호", nullable = false, example = "")
    private String popno;
    
    /** 롤테이너 */
    @Schema(description = "롤테이너", nullable = false, example = "")
    private String rolltainerNo;

    /** row 번호 */
    @Schema(description = "row 번호", nullable = false, example = "")
    private Integer rowcnt;

    /** 센터코드 */
    @Schema(description = "센터코드", nullable = false, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = false, example = "")
    private String storerkey;

    /** 문서유형 */
    @Schema(description = "문서유형", nullable = false, example = "")
    private String doctype;
    
    /** 마감유형 */
    @Schema(description = "마감유형", nullable = false, example = "")
    private String custorderclosetype;

    /** 거래처코드 */
    @Schema(description = "거래처코드", nullable = false, example = "")
    private String custkey;

    /** 거래처명 */
    @Schema(description = "거래처명", nullable = false, example = "")
    private String custname;

    /** 거래처유형코드 */
    @Schema(description = "거래처유형코드", nullable = false, example = "")
    private String custtype;
    
    /** 거래처유형명 */
    @Schema(description = "거래처유형명", nullable = false, example = "")
    private String custtypeName;    

    /** 미등록일수 */
    @Schema(description = "미등록일수", nullable = false, example = "")
    private Integer diffDays;
    
    /** 당일주문건수 */
    @Schema(description = "주문횟수", nullable = false, example = "")
    private Integer deliverydateCnt;
    
    /** 당일주문건수 */
    @Schema(description = "당일주문건수", nullable = false, example = "")
    private Integer skuCnt;    

    /** 당일물량 */
    @Schema(description = "당일물량", nullable = false, example = "")
    private java.math.BigDecimal orderWeight;
    
    /** 당일물량_CBM */
    @Schema(description = "당일물량_CBM", nullable = false, example = "")
    private java.math.BigDecimal orderCbm;

    /** 주소지 */
    @Schema(description = "주소지", nullable = false, example = "")
    private String address;    
    
    /** GridRow 저장 구분 */
    @Schema(description = "GridRow 저장 구분", example = "U", allowableValues = {"I", "U", "D"})
    private String rowStatus;
}
