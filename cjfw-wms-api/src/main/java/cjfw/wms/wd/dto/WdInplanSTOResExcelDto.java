package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author 		: YeoSeungCheol (pw6375@cj.net) 
 * @date 		: 2026.02.01
 * @description : 광역출고현황 엑셀다운로드 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.01 공두경 생성 </pre>
 */
@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Schema(description = "광역출고현황 엑셀다운로드 응답 DTO")
public class WdInplanSTOResExcelDto {
	
	
	/** 체크여부 */
    @Schema(description = "체크여부", example = "0")
    private String checkyn;

    /** 출발물류센터코드 */
    @Schema(description = "출발물류센터코드", example = "DC01")
    private String fromDccode;

    /** 출발조직 */
    @Schema(description = "출발조직", example = "ORG01")
    private String fromOrganize;

    /** 전표일자 */
    @Schema(description = "전표일자", example = "20260201")
    private String slipdt;

    /** 문서일자 */
    @Schema(description = "문서일자", example = "20260201")
    private String docdt;

    /** 문서번호 */
    @Schema(description = "문서번호", example = "DOC10001")
    private String docno;

    /** 주문유형 */
    @Schema(description = "주문유형", example = "NORMAL")
    private String ordertype;

    /** 출발물류센터명 */
    @Schema(description = "출발물류센터명", example = "서울DC")
    private String fromDcname;

    /** 출발고객사코드 */
    @Schema(description = "출발고객사코드", example = "CUST01")
    private String fromCustkey;

    /** 출발고객사명 */
    @Schema(description = "출발고객사명", example = "고객사A")
    private String fromCustname;

    /** 도착물류센터코드 */
    @Schema(description = "도착물류센터코드", example = "DC02")
    private String toDccode;

    /** 도착물류센터명 */
    @Schema(description = "도착물류센터명", example = "경기DC")
    private String toDcname;

    /** 도착조직 */
    @Schema(description = "도착조직", example = "ORG02")
    private String toOrganize;

    /** 도착고객사코드 */
    @Schema(description = "도착고객사코드", example = "CUST02")
    private String toCustkey;

    /** 도착고객사명 */
    @Schema(description = "도착고객사명", example = "고객사B")
    private String toCustname;

    /** 상태 */
    @Schema(description = "상태", example = "10")
    private String status;

    /** 물류센터코드 */
    @Schema(description = "물류센터코드", example = "DC01")
    private String dccode;

    /** 화주코드 */
    @Schema(description = "화주코드", example = "STORER01")
    private String storerkey;

    /** 문서유형 */
    @Schema(description = "문서유형", example = "IN")
    private String doctype;

    /** 차량번호 */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

    /** 센터출발일시 */
    @Schema(description = "센터출발일시", example = "02/01 13:00")
    private String dcdeparturedt;

    /** 문서라인 */
    @Schema(description = "문서라인", example = "1")
    private String docline;

    /** 상품코드 */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "상품A")
    private String skuname;

    /** 채널 */
    @Schema(description = "채널", example = "B2B")
    private String channel;

    /** 보관유형 */
    @Schema(description = "보관유형", example = "COLD")
    private String storagetype;

    /** 주문수량 */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;

    /** 처리수량 */
    @Schema(description = "처리수량", example = "100")
    private BigDecimal processqty;

    /** 작업수량 */
    @Schema(description = "작업수량", example = "100")
    private BigDecimal workqty;

    /** 검수수량 */
    @Schema(description = "검수수량", example = "100")
    private BigDecimal inspectqty;

    /** 도착검수수량 */
    @Schema(description = "도착검수수량", example = "100")
    private BigDecimal tostoInspectqty;

    /** 확정수량 */
    @Schema(description = "확정수량", example = "100")
    private BigDecimal confirmqty;

    /** 도착확정수량 */
    @Schema(description = "도착확정수량", example = "100")
    private BigDecimal tostoConfirmqty;

    /** 단위 */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /** 확정중량 */
    @Schema(description = "확정중량", example = "500")
    private BigDecimal confirmweight;

    /** 도착확정중량 */
    @Schema(description = "도착확정중량", example = "500")
    private BigDecimal tostoConfirmweight;

    /** 도착상태 */
    @Schema(description = "도착상태", example = "20")
    private String tostoStatus;

    /** 플랜트 */
    @Schema(description = "플랜트", example = "P01")
    private String plant;

    /** 플랜트명 */
    @Schema(description = "플랜트명", example = "[P01]서울플랜트")
    private String plantDescr;

    /** 등록자 */
    @Schema(description = "등록자", example = "홍길동")
    private String addwho;

    /** 등록일시 */
    @Schema(description = "등록일시", example = "2026-02-01 13:00:00")
    private String adddate;

    /** 수정자 */
    @Schema(description = "수정자", example = "이몽룡")
    private String editwho;

    /** 수정일시 */
    @Schema(description = "수정일시", example = "2026-02-01 14:00:00")
    private String editdate;
}
