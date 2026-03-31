package cjfw.wms.ms.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.08.23
 * @description : 외부창고정산 시뮬레이션 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Schema(description = "외부창고정산 시뮬레이션 조회 결과 DTO")
public class MsExDCSimulationResDto extends CommonProcedureDto {
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** 기준년월 */
    @Schema(description = "기준년월", nullable = false, example = "")
    private String stockmonth;
    
    /** 문서번호 */
    @Schema(description = "문서번호", nullable = false, example = "")
    private String docno;
    
    /** 문서라인번호 */
    @Schema(description = "문서라인번호", nullable = false, example = "")
    private String docline;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품명 */
    @Schema(description = "상품명", nullable = false, example = "")
    private String skuname;
    
    /** 기준대상여부 */
    @Schema(description = "기준대상여부", nullable = false, example = "")
    private String baseYn;
    
    /** 수량 */
    @Schema(description = "수량", nullable = false, example = "")
    private BigDecimal qty;
    
    /** 입고량 */
    @Schema(description = "입고량", nullable = false, example = "")
    private BigDecimal grQty;
    
    /** 출고량 */
    @Schema(description = "출고량", nullable = false, example = "")
    private BigDecimal giQty;

    /** 단위 */
    @Schema(description = "단위", nullable = false, example = "")
    private String uom;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = false, example = "")
    private String storagetye;
    
    /** 저장조건명 */
    @Schema(description = "저장조건명", nullable = false, example = "")
    private String storagetyename;
    
    /** 상품대분류 */
    @Schema(description = "상품대분류", nullable = false, example = "")
    private String skuLdesc;
    
    /** 박스수량 */
    @Schema(description = "박스수량", nullable = false, example = "")
    private BigDecimal boxqty;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = false, example = "")
    private String convserialno;
    
    /** 유통이력번호 */
    @Schema(description = "유통이력번호", nullable = false, example = "")
    private String serialno;
    
    /** 단가_단위 */
    @Schema(description = "단가_단위", nullable = false, example = "")
    private String priceUom;

    /** 기준 단가_입고료 */
    @Schema(description = "기준 단가_입고료", nullable = false, example = "")
    private BigDecimal baseGrprice;
    
    /** 기준 단가_출고료 */
    @Schema(description = "기준 단가_출고료", nullable = false, example = "")
    private BigDecimal baseGiprice;
    
    /** 기준 단가_창고료 */
    @Schema(description = "기준 단가_창고료", nullable = false, example = "")
    private BigDecimal baseStorageprice;
    
    /** 기준 단가_팔렛료 */
    @Schema(description = "기준 단가_팔렛료", nullable = false, example = "")
    private BigDecimal basePltprice;
    
    /** 기준 단가_계근료 */
    @Schema(description = "기준 단가_계근료", nullable = false, example = "")
    private BigDecimal baseWghprice;
    
    /** 기준 단가_작업료 */
    @Schema(description = "기준 단가_작업료", nullable = false, example = "")
    private BigDecimal baseWorkprice;  

    /** 비교 단가_입고료 */
    @Schema(description = "비교 단가_입고료", nullable = false, example = "")
    private BigDecimal cfGrprice;
    
    /** 비교 단가_출고료 */
    @Schema(description = "비교 단가_출고료", nullable = false, example = "")
    private BigDecimal cfGiprice;
    
    /** 비교 단가_창고료 */
    @Schema(description = "비교 단가_창고료", nullable = false, example = "")
    private BigDecimal cfStorageprice;
    
    /** 비교 단가_팔렛료 */
    @Schema(description = "비교 단가_팔렛료", nullable = false, example = "")
    private BigDecimal cfPltprice;
    
    /** 비교 단가_계근료 */
    @Schema(description = "비교 단가_계근료", nullable = false, example = "")
    private BigDecimal cfWghprice;
    
    /** 비교 단가_작업료 */
    @Schema(description = "비교 단가_작업료", nullable = false, example = "")
    private BigDecimal cfWorkprice;  

    /** 기준창고 M_입고료 */
    @Schema(description = "기준창고 M_입고료", nullable = false, example = "")
    private BigDecimal baseMGrAmount;
    
    /** 기준창고 M_출고료 */
    @Schema(description = "기준창고 M_출고료", nullable = false, example = "")
    private BigDecimal baseMGiAmount;
    
    /** 기준창고 M_창고료 */
    @Schema(description = "기준창고 M_창고료", nullable = false, example = "")
    private BigDecimal baseMStockAmount;
    
    /** 기준창고 M_팔렛료 */
    @Schema(description = "기준창고 M_팔렛료", nullable = false, example = "")
    private BigDecimal baseMPltAmount;
    
    /** 기준창고 M_계근료 */
    @Schema(description = "기준창고 M_계근료", nullable = false, example = "")
    private BigDecimal baseMWghAmount;
    
    /** 기준창고 M_작업료 */
    @Schema(description = "기준창고 M_작업료", nullable = false, example = "")
    private BigDecimal baseMWorkAmount;
    
    /** 기준창고 M_소계 */
    @Schema(description = "기준창고 M_소계", nullable = false, example = "")
    private BigDecimal baseMSumAmount;
    
    /** 비교창고 M_입고료 */
    @Schema(description = "비교창고 M_입고료", nullable = false, example = "")
    private BigDecimal cfMGrAmount;
    
    /** 비교창고 M_출고료 */
    @Schema(description = "비교창고 M_출고료", nullable = false, example = "")
    private BigDecimal cfMGiAmount;
    
    /** 비교창고 M_창고료 */
    @Schema(description = "비교창고 M_창고료", nullable = false, example = "")
    private BigDecimal cfMStockAmount;
    
    /** 비교창고 M_팔렛료 */
    @Schema(description = "비교창고 M_팔렛료", nullable = false, example = "")
    private BigDecimal cfMPltAmount;
    
    /** 비교창고 M_계근료 */
    @Schema(description = "비교창고 M_계근료", nullable = false, example = "")
    private BigDecimal cfMWghAmount;
    
    /** 비교창고 M_작업료 */
    @Schema(description = "비교창고 M_작업료", nullable = false, example = "")
    private BigDecimal cfMWorkAmount;
    
    /** 비교창고 M_소계 */
    @Schema(description = "비교창고 M_소계", nullable = false, example = "")
    private BigDecimal cfMSumAmount;
    
    /** 기준창고 M1_입고료 */
    @Schema(description = "기준창고 M1_입고료", nullable = false, example = "")
    private BigDecimal baseM1GrAmount;
    
    /** 기준창고 M1_출고료 */
    @Schema(description = "기준창고 M1_출고료", nullable = false, example = "")
    private BigDecimal baseM1GiAmount;
    
    /** 기준창고 M1_창고료 */
    @Schema(description = "기준창고 M1_창고료", nullable = false, example = "")
    private BigDecimal baseM1StockAmount;
    
    /** 기준창고 M1_팔렛료 */
    @Schema(description = "기준창고 M1_팔렛료", nullable = false, example = "")
    private BigDecimal baseM1PltAmount;
    
    /** 기준창고 M1_계근료 */
    @Schema(description = "기준창고 M1_계근료", nullable = false, example = "")
    private BigDecimal baseM1WghAmount;
    
    /** 기준창고 M1_작업료 */
    @Schema(description = "기준창고 M1_작업료", nullable = false, example = "")
    private BigDecimal baseM1WorkAmount;
    
    /** 기준창고 M1_소계 */
    @Schema(description = "기준창고 M1_소계", nullable = false, example = "")
    private BigDecimal baseM1SumAmount;
    
    /** 비교창고 M1_입고료 */
    @Schema(description = "비교창고 M1_입고료", nullable = false, example = "")
    private BigDecimal cfM1GrAmount;
    
    /** 비교창고 M1_출고료 */
    @Schema(description = "비교창고 M1_출고료", nullable = false, example = "")
    private BigDecimal cfM1GiAmount;
    
    /** 비교창고 M1_창고료 */
    @Schema(description = "비교창고 M1_창고료", nullable = false, example = "")
    private BigDecimal cfM1StockAmount;
    
    /** 비교창고 M1_팔렛료 */
    @Schema(description = "비교창고 M1_팔렛료", nullable = false, example = "")
    private BigDecimal cfM1PltAmount;
    
    /** 비교창고 M1_계근료 */
    @Schema(description = "비교창고 M1_계근료", nullable = false, example = "")
    private BigDecimal cfM1WghAmount;
    
    /** 비교창고 M1_작업료 */
    @Schema(description = "비교창고 M1_작업료", nullable = false, example = "")
    private BigDecimal cfM1WorkAmount;
    
    /** 비교창고 M1_소계 */
    @Schema(description = "비교창고 M1_소계", nullable = false, example = "")
    private BigDecimal cfM1SumAmount;

    /** 기준창고 M2_입고료 */
    @Schema(description = "기준창고 M2_입고료", nullable = false, example = "")
    private BigDecimal baseM2GrAmount;
    
    /** 기준창고 M2_출고료 */
    @Schema(description = "기준창고 M2_출고료", nullable = false, example = "")
    private BigDecimal baseM2GiAmount;
    
    /** 기준창고 M2_창고료 */
    @Schema(description = "기준창고 M2_창고료", nullable = false, example = "")
    private BigDecimal baseM2StockAmount;
    
    /** 기준창고 M2_팔렛료 */
    @Schema(description = "기준창고 M2_팔렛료", nullable = false, example = "")
    private BigDecimal baseM2PltAmount;
    
    /** 기준창고 M2_계근료 */
    @Schema(description = "기준창고 M2_계근료", nullable = false, example = "")
    private BigDecimal baseM2WghAmount;
    
    /** 기준창고 M2_작업료 */
    @Schema(description = "기준창고 M2_작업료", nullable = false, example = "")
    private BigDecimal baseM2WorkAmount;
    
    /** 기준창고 M2_소계 */
    @Schema(description = "기준창고 M2_소계", nullable = false, example = "")
    private BigDecimal baseM2SumAmount;
    
    /** 비교창고 M2_입고료 */
    @Schema(description = "비교창고 M2_입고료", nullable = false, example = "")
    private BigDecimal cfM2GrAmount;
    
    /** 비교창고 M2_출고료 */
    @Schema(description = "비교창고 M2_출고료", nullable = false, example = "")
    private BigDecimal cfM2GiAmount;
    
    /** 비교창고 M2_창고료 */
    @Schema(description = "비교창고 M2_창고료", nullable = false, example = "")
    private BigDecimal cfM2StockAmount;
    
    /** 비교창고 M2_팔렛료 */
    @Schema(description = "비교창고 M2_팔렛료", nullable = false, example = "")
    private BigDecimal cfM2PltAmount;
    
    /** 비교창고 M2_계근료 */
    @Schema(description = "비교창고 M2_계근료", nullable = false, example = "")
    private BigDecimal cfM2WghAmount;
    
    /** 비교창고 M2_작업료 */
    @Schema(description = "비교창고 M2_작업료", nullable = false, example = "")
    private BigDecimal cfM2WorkAmount;
    
    /** 비교창고 M2_소계 */
    @Schema(description = "비교창고 M2_소계", nullable = false, example = "")
    private BigDecimal cfM2SumAmount;

    /** 기준창고 합계금액 */
    @Schema(description = "기준창고 합계금액", nullable = false, example = "")
    private BigDecimal baseTotalAmount;
    
    /** 비교창고 합계금액 */
    @Schema(description = "비교창고 합계금액", nullable = false, example = "")
    private BigDecimal cfTotalAmount;
    
    /** 차이금액 */
    @Schema(description = "차이금액", nullable = false, example = "")
    private BigDecimal diffAmount;
    
    /** 차이율 */
    @Schema(description = "차이율", nullable = false, example = "")
    private BigDecimal diffRate;
    
    /** 기준창고 평균합계금액 */
    @Schema(description = "기준창고 평균합계금액", nullable = false, example = "")
    private BigDecimal baseAvgTotalAmount;
    
    /** 비교창고 평균합계금액 */
    @Schema(description = "비교창고 평균합계금액", nullable = false, example = "")
    private BigDecimal cfAvgTotalAmount;
    
    /** 평균차이금액 */
    @Schema(description = "평균차이금액", nullable = false, example = "")
    private BigDecimal avgDiffAmount;
    
    /** 평균차이율 */
    @Schema(description = "평균차이율", nullable = false, example = "")
    private BigDecimal avgDiffRate;
    
    /** SPID */
    @Schema(description = "SPID", nullable = false, example = "")
    private BigDecimal spid;
    
    /** 등록일시 */
    @Schema(description = "등록일시", example = "2023-01-01 10:00:00")
    private String adddate;

    /** 수정일시 */
    @Schema(description = "수정일시", example = "2024-05-20 15:30:00")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자", example = "ADMIN")
    private String addwho;

    /** 수정자 */
    @Schema(description = "수정자", example = "USER123")
    private String editwho;
    
    /** 입출고구분 */
    @Schema(description = "입출고구분", example = "USER123")
    private String ioFlag;
    
    /** 발생유형 */
    @Schema(description = "발생유형", example = "USER123")
    private String ioType;

}
