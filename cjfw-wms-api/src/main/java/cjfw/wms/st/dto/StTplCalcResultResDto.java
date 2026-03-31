package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : ParkYoSep 
 * @date : 2025.11.12  
 * @description : 위탁정산내역현황 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 parkYosep (dytpq362@cj.net) 생성
 *         </pre>
 */
@Schema(description = "위탁입출고현황 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StTplCalcResultResDto extends CommonProcedureDto {
    /** 커스텀 엑스트라 체크박스 */
    @Schema(description = "커스텀 엑스트라 체크박스", example = "N")
    private String customRowCheckYn = "N";
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;

    /** 재고월 (YYYYMM) */
    @Schema(description = "재고월 (YYYYMM)", nullable = true, example = "")
    private String yyyymm;


    /** DP : 입고, WD : 출고 */
    @Schema(description = "DP : 입고, WD : 출고", nullable = true, example = "")
    private String ioFlag;


    /** 보관일수 */
    @Schema(description = "보관일수", nullable = true, example = "")
    private Integer storagedaycnt;

    /** 적용일자 */
    @Schema(description = "적용일자", nullable = true, example = "")
    private String deliverydate;

    /** 외부창고 최초 입고일자 */
    @Schema(description = "외부창고 최초 입고일자", nullable = true, example = "")
    private String inventoryDate;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = true, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = true, example = "")
    private String organizename;

    /** 고객코드(거래처) */
    @Schema(description = "고객코드(거래처)", nullable = true, example = "")
    private String custkey;

    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", nullable = true, example = "")
    private String skuNm;

    /** 수량 */
    @Schema(description = "수량", nullable = true, example = "")
    private BigDecimal quantity;

    /** 현재고 */
    @Schema(description = "현재고", nullable = true, example = "")
    private BigDecimal currstock;

    /** 단위 */
    @Schema(description = "단위", nullable = true, example = "")
    private String uom;

    /** 박스수량 */
    @Schema(description = "박스수량", nullable = true, example = "")
    private BigDecimal boxqty;

    /** 재고박스 */
//    @Schema(description = "재고박스", nullable = true, example = "")
//    private BigDecimal qtybox;

    /** 박스단위 */
    @Schema(description = "박스단위", nullable = true, example = "")
    private String boxuom;

    /** 박스입수 */
//    @Schema(description = "박스입수", nullable = true, example = "")
//    private BigDecimal qtyperbox;


    /** 선하증권번호 */
    @Schema(description = "선하증권번호", nullable = true, example = "")
    private String convserialno;

    /** 입고료 */
    @Schema(description = "입고료", nullable = true, example = "")
    private BigDecimal grAmount;

    /** 출고료 */
    @Schema(description = "출고료", nullable = true, example = "")
    private BigDecimal giAmount;

    /** 창고료 */
    @Schema(description = "창고료", nullable = true, example = "")
    private BigDecimal stockAmount;

    /** 보관료 합계 */
    @Schema(description = "보관료 합계", nullable = true, example = "")
    private BigDecimal sumAmount;


    /** 입고수수료 */
    @Schema(description = "입고수수료", nullable = true, example = "")
    private BigDecimal grprice;

    /** 출고수수료 */
    @Schema(description = "출고수수료", nullable = true, example = "")
    private BigDecimal giprice;

    /** 보관수수료 */
    @Schema(description = "보관수수료", nullable = true, example = "")
    private BigDecimal storageprice;

    /** 이체입고수수료 */
    @Schema(description = "이체입고수수료", nullable = true, example = "")
    private BigDecimal stogrprice;

    /** 이체출고수수료 */
    @Schema(description = "이체출고수수료", nullable = true, example = "")
    private BigDecimal stogiprice;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = true, example = "")
    private String addwho;
    
    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = true, example = "")
    private String addwhoNm;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", nullable = true, example = "")
    private String adddate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = true, example = "")
    private String editwho;
    
    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = true, example = "")
    private String editwhoNm;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = true, example = "")
    private String editdate;

    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = true, example = "")
    private String toCustkey;

    /** 관리처명 */
    @Schema(description = "관리처명", nullable = true, example = "")
    private String toCustname;
    
    /** 계근료 */
    @Schema(description = "계근료", nullable = true, example = "")
    private BigDecimal wghprice;   
    
    /** 작업료 */
    @Schema(description = "작업료", nullable = true, example = "")
    private BigDecimal workAmount;    
    
    /** 팔렛료 */
    @Schema(description = "팔렛료", nullable = true, example = "")
    private BigDecimal palletprice;        
    
    /** 계근수수료 */
    @Schema(description = "계근수수료", nullable = true, example = "")
    private BigDecimal wghFee;   
    
    /** 작업수수료 */
    @Schema(description = "작업수수료", nullable = true, example = "")
    private BigDecimal workFee;    
    
    /** 팔렛수수료 */
    @Schema(description = "팔렛수수료", nullable = true, example = "")
    private BigDecimal palletFee;
    
    /** 운송료(비용전표) */
    @Schema(description = "운송료(비용전표)", nullable = true, example = "")
    private BigDecimal transprice;
    
    /** 입고비 */
    @Schema(description = "입고비")
    private BigDecimal grPriceUpperTransbaseUom;
                      
    /** 출고비 */
    @Schema(description = "출고비")
    private BigDecimal giPriceUpperTransbaseUom;

    /** 창고료 */
    @Schema(description = "창고료")
    private BigDecimal storagePriceUpperTransbaseUom;

    /** 계근비(출고시) */
    @Schema(description = "계근비(출고시)")
    private BigDecimal wghPriceUpperTransbaseUom;

    /** 작업비(출고시) */
    @Schema(description = "작업비(출고시)")
    private BigDecimal pltPriceUpperTransbaseUom;

    /** 팔렛트단가 */
    @Schema(description = "팔렛트단가")
    private BigDecimal workPriceUpperTransbaseUom;
}
