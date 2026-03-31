package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 *
 * @author : 고혜미(laksjd0606@cj.net)
 * @date : 2025.10.21
 * @description : KIT상품 계획등록 결과 DTO
 * @issues :
 * -----------------------------------------------------------
 * DATE          AUTHOR                  MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.10.21   고혜미(laksjd0606@cj.net)  생성
 */
@Data
@Schema(description = "KIT상품 계획등록 결과 DTO")
public class StKitPlanResT1Dto extends CommonProcedureDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";


    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String kitSku;
    
    /** 상품명 */
    @Schema(description = "상품명")
    private String kitNm;
    
    /** 저장조건 */
    @Schema(description = "저장조건")
    private String storagetype;
    
    /** 단위중량 */
    @Schema(description = "단위중량")
    private BigDecimal grossweight;
    
    /** 박스입수 */
    @Schema(description = "박스입수")
    private BigDecimal qtyperbox;
    
    /** PLT입수 */
    @Schema(description = "PLT입수")
    private BigDecimal boxperplt;
    
    /** 수급담당 */
    @Schema(description = "수급담당")
    private String buyerkey;
    
    /** 수급담당명 */
    @Schema(description = "수급담당명")
    private String buyerkeynm;
    
    /** 센터담당 */
    @Schema(description = "센터담당")
    private String centerManager;
    
    /** 전년동월 제작수량 */
    @Schema(description = "전년동월 제작수량")
    private BigDecimal totalConfirmqtyLastYear;
    
    /** 센터보유량(인천제외) */
    @Schema(description = "센터보유량(인천제외)")
    private BigDecimal centerQty1;
    
    /** 센터보유량(인천) */
    @Schema(description = "센터보유량(인천)")
    private BigDecimal centerQty2;
    
    /** 센터보유량(인천) */
    @Schema(description = "센터보유량(합)")
    private BigDecimal sumCenterQty;
    
    /** 안전재고 */
    @Schema(description = "안전재고")
    private BigDecimal stocksafety;
    
    /** 재발주점 */
    @Schema(description = "재발주점")
    private BigDecimal reorderpoint;
    
    /** MAX */
    @Schema(description = "MAX")
    private BigDecimal maxstock;
    
    /** D+7 기주문량 */
    @Schema(description = "D+7 기주문량")
    private BigDecimal quantityOrder;
    
    /** 반영수량 (재고 -7일 치 주문량) */
    @Schema(description = "반영수량 (재고 -7일 치 주문량)")
    private BigDecimal reflectedQty;
    
    /** 총주문 */
    @Schema(description = "총주문")
    private BigDecimal totalOrderqty;
    
    /** 당일 전 주문량 */
    @Schema(description = "당일 전 주문량")
    private BigDecimal qtyOrderBefore;
    
    /** 당일 후 주문량 */
    @Schema(description = "당일 후 주문량")
    private BigDecimal qtyOrderAfter;
    
    /** DP대비 */
    @Schema(description = "DP대비")
    private String dpRate;
    
    /** 출고수량 D-1월 */
    @Schema(description = "출고수량 D-1월")
    private BigDecimal confirmqtyM1;
    
    /** 출고수량 D-2월 */
    @Schema(description = "출고수량 D-2월")
    private BigDecimal confirmqtyM2;
    
    /** 출고수량 D-3월 */
    @Schema(description = "출고수량 D-3월")
    private BigDecimal confirmqtyM3;
    
    /** DP(영업요청수량) */
    @Schema(description = "DP(영업요청수량)")
    private BigDecimal dpQty;
    
    /** 기 작업수량 */
    @Schema(description = "기 작업수량")
    private BigDecimal totalConfirmqty;
    
    /** 작업잔량 */
    @Schema(description = "작업잔량")
    private BigDecimal workRemainingAmt;
    
    /** 작업율 */
    @Schema(description = "작업율")
    private String workRate;
    
    /** 계획일 */
    @Schema(description = "계획일")
    private String planDt;
    
    /** 01일 요청 */
    @Schema(description = "01일 요청")
    private BigDecimal d01Req;

    /** 01일 계획 */
    @Schema(description = "01일 계획")
    private BigDecimal d01Plan;

    /** 01일 생산 */
    @Schema(description = "01일 생산")
    private BigDecimal d01Prod;

    /** 02일 요청 */
    @Schema(description = "02일 요청")
    private BigDecimal d02Req;

    /** 02일 계획 */
    @Schema(description = "02일 계획")
    private BigDecimal d02Plan;

    /** 02일 생산 */
    @Schema(description = "02일 생산")
    private BigDecimal d02Prod;

    /** 03일 요청 */
    @Schema(description = "03일 요청")
    private BigDecimal d03Req;

    /** 03일 계획 */
    @Schema(description = "03일 계획")
    private BigDecimal d03Plan;

    /** 03일 생산 */
    @Schema(description = "03일 생산")
    private BigDecimal d03Prod;

    /** 04일 요청 */
    @Schema(description = "04일 요청")
    private BigDecimal d04Req;

    /** 04일 계획 */
    @Schema(description = "04일 계획")
    private BigDecimal d04Plan;

    /** 04일 생산 */
    @Schema(description = "04일 생산")
    private BigDecimal d04Prod;

    /** 05일 요청 */
    @Schema(description = "05일 요청")
    private BigDecimal d05Req;

    /** 05일 계획 */
    @Schema(description = "05일 계획")
    private BigDecimal d05Plan;

    /** 05일 생산 */
    @Schema(description = "05일 생산")
    private BigDecimal d05Prod;

    /** 06일 요청 */
    @Schema(description = "06일 요청")
    private BigDecimal d06Req;

    /** 06일 계획 */
    @Schema(description = "06일 계획")
    private BigDecimal d06Plan;

    /** 06일 생산 */
    @Schema(description = "06일 생산")
    private BigDecimal d06Prod;

    /** 07일 요청 */
    @Schema(description = "07일 요청")
    private BigDecimal d07Req;

    /** 07일 계획 */
    @Schema(description = "07일 계획")
    private BigDecimal d07Plan;

    /** 07일 생산 */
    @Schema(description = "07일 생산")
    private BigDecimal d07Prod;

    /** 08일 요청 */
    @Schema(description = "08일 요청")
    private BigDecimal d08Req;

    /** 08일 계획 */
    @Schema(description = "08일 계획")
    private BigDecimal d08Plan;

    /** 08일 생산 */
    @Schema(description = "08일 생산")
    private BigDecimal d08Prod;

    /** 09일 요청 */
    @Schema(description = "09일 요청")
    private BigDecimal d09Req;

    /** 09일 계획 */
    @Schema(description = "09일 계획")
    private BigDecimal d09Plan;

    /** 09일 생산 */
    @Schema(description = "09일 생산")
    private BigDecimal d09Prod;

    /** 10일 요청 */
    @Schema(description = "10일 요청")
    private BigDecimal d10Req;

    /** 10일 계획 */
    @Schema(description = "10일 계획")
    private BigDecimal d10Plan;

    /** 10일 생산 */
    @Schema(description = "10일 생산")
    private BigDecimal d10Prod;

    /** 11일 요청 */
    @Schema(description = "11일 요청")
    private BigDecimal d11Req;

    /** 11일 계획 */
    @Schema(description = "11일 계획")
    private BigDecimal d11Plan;

    /** 11일 생산 */
    @Schema(description = "11일 생산")
    private BigDecimal d11Prod;

    /** 12일 요청 */
    @Schema(description = "12일 요청")
    private BigDecimal d12Req;

    /** 12일 계획 */
    @Schema(description = "12일 계획")
    private BigDecimal d12Plan;

    /** 12일 생산 */
    @Schema(description = "12일 생산")
    private BigDecimal d12Prod;

    /** 13일 요청 */
    @Schema(description = "13일 요청")
    private BigDecimal d13Req;

    /** 13일 계획 */
    @Schema(description = "13일 계획")
    private BigDecimal d13Plan;

    /** 13일 생산 */
    @Schema(description = "13일 생산")
    private BigDecimal d13Prod;

    /** 14일 요청 */
    @Schema(description = "14일 요청")
    private BigDecimal d14Req;

    /** 14일 계획 */
    @Schema(description = "14일 계획")
    private BigDecimal d14Plan;

    /** 14일 생산 */
    @Schema(description = "14일 생산")
    private BigDecimal d14Prod;

    /** 15일 요청 */
    @Schema(description = "15일 요청")
    private BigDecimal d15Req;

    /** 15일 계획 */
    @Schema(description = "15일 계획")
    private BigDecimal d15Plan;

    /** 15일 생산 */
    @Schema(description = "15일 생산")
    private BigDecimal d15Prod;

    /** 16일 요청 */
    @Schema(description = "16일 요청")
    private BigDecimal d16Req;

    /** 16일 계획 */
    @Schema(description = "16일 계획")
    private BigDecimal d16Plan;

    /** 16일 생산 */
    @Schema(description = "16일 생산")
    private BigDecimal d16Prod;

    /** 17일 요청 */
    @Schema(description = "17일 요청")
    private BigDecimal d17Req;

    /** 17일 계획 */
    @Schema(description = "17일 계획")
    private BigDecimal d17Plan;

    /** 17일 생산 */
    @Schema(description = "17일 생산")
    private BigDecimal d17Prod;

    /** 18일 요청 */
    @Schema(description = "18일 요청")
    private BigDecimal d18Req;

    /** 18일 계획 */
    @Schema(description = "18일 계획")
    private BigDecimal d18Plan;

    /** 18일 생산 */
    @Schema(description = "18일 생산")
    private BigDecimal d18Prod;

    /** 19일 요청 */
    @Schema(description = "19일 요청")
    private BigDecimal d19Req;

    /** 19일 계획 */
    @Schema(description = "19일 계획")
    private BigDecimal d19Plan;

    /** 19일 생산 */
    @Schema(description = "19일 생산")
    private BigDecimal d19Prod;

    /** 20일 요청 */
    @Schema(description = "20일 요청")
    private BigDecimal d20Req;

    /** 20일 계획 */
    @Schema(description = "20일 계획")
    private BigDecimal d20Plan;

    /** 20일 생산 */
    @Schema(description = "20일 생산")
    private BigDecimal d20Prod;

    /** 21일 요청 */
    @Schema(description = "21일 요청")
    private BigDecimal d21Req;

    /** 21일 계획 */
    @Schema(description = "21일 계획")
    private BigDecimal d21Plan;

    /** 21일 생산 */
    @Schema(description = "21일 생산")
    private BigDecimal d21Prod;

    /** 22일 요청 */
    @Schema(description = "22일 요청")
    private BigDecimal d22Req;

    /** 22일 계획 */
    @Schema(description = "22일 계획")
    private BigDecimal d22Plan;

    /** 22일 생산 */
    @Schema(description = "22일 생산")
    private BigDecimal d22Prod;

    /** 23일 요청 */
    @Schema(description = "23일 요청")
    private BigDecimal d23Req;

    /** 23일 계획 */
    @Schema(description = "23일 계획")
    private BigDecimal d23Plan;

    /** 23일 생산 */
    @Schema(description = "23일 생산")
    private BigDecimal d23Prod;

    /** 24일 요청 */
    @Schema(description = "24일 요청")
    private BigDecimal d24Req;

    /** 24일 계획 */
    @Schema(description = "24일 계획")
    private BigDecimal d24Plan;

    /** 24일 생산 */
    @Schema(description = "24일 생산")
    private BigDecimal d24Prod;

    /** 25일 요청 */
    @Schema(description = "25일 요청")
    private BigDecimal d25Req;

    /** 25일 계획 */
    @Schema(description = "25일 계획")
    private BigDecimal d25Plan;

    /** 25일 생산 */
    @Schema(description = "25일 생산")
    private BigDecimal d25Prod;

    /** 26일 요청 */
    @Schema(description = "26일 요청")
    private BigDecimal d26Req;

    /** 26일 계획 */
    @Schema(description = "26일 계획")
    private BigDecimal d26Plan;

    /** 26일 생산 */
    @Schema(description = "26일 생산")
    private BigDecimal d26Prod;

    /** 27일 요청 */
    @Schema(description = "27일 요청")
    private BigDecimal d27Req;

    /** 27일 계획 */
    @Schema(description = "27일 계획")
    private BigDecimal d27Plan;

    /** 27일 생산 */
    @Schema(description = "27일 생산")
    private BigDecimal d27Prod;

    /** 28일 요청 */
    @Schema(description = "28일 요청")
    private BigDecimal d28Req;

    /** 28일 계획 */
    @Schema(description = "28일 계획")
    private BigDecimal d28Plan;

    /** 28일 생산 */
    @Schema(description = "28일 생산")
    private BigDecimal d28Prod;

    /** 29일 요청 */
    @Schema(description = "29일 요청")
    private BigDecimal d29Req;

    /** 29일 계획 */
    @Schema(description = "29일 계획")
    private BigDecimal d29Plan;

    /** 29일 생산 */
    @Schema(description = "29일 생산")
    private BigDecimal d29Prod;

    /** 30일 요청 */
    @Schema(description = "30일 요청")
    private BigDecimal d30Req;

    /** 30일 계획 */
    @Schema(description = "30일 계획")
    private BigDecimal d30Plan;

    /** 30일 생산 */
    @Schema(description = "30일 생산")
    private BigDecimal d30Prod;

    /** 31일 요청 */
    @Schema(description = "31일 요청")
    private BigDecimal d31Req;

    /** 31일 계획 */
    @Schema(description = "31일 계획")
    private BigDecimal d31Plan;

    /** 31일 생산 */
    @Schema(description = "31일 생산")
    private BigDecimal d31Prod;
    
    /** 요청수량 */
    @Schema(description = "요청수량")
    private BigDecimal orderqty;
    
    /** 계획수량 */
    @Schema(description = "계획수량")
    private BigDecimal openqty;
    
    /** 생산수량 */
    @Schema(description = "생산수량")
    private BigDecimal confirmqty;


    
}
