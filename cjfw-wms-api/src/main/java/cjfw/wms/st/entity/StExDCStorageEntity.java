package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.21 
 * @description : 외부창고정산 Entity
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부창고정산 Entity") 
public class StExDCStorageEntity extends CommonProcedureDto {
    
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = true, example = "")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사", nullable = true, example = "")
    private String storerkey;

    /** 일련번호 */
    @Schema(description = "일련번호", nullable = false, example = "")
    private String serialkey;

    /** 1=준비,2=보관료계산,3=비용생성,4=익월기초재고등록 */
    @Schema(description = "1=준비,2=보관료계산,3=비용생성,4=익월기초재고등록", nullable = true, example = "")
    private String status;

    /** 재고월 (YYYYMM) */
    @Schema(description = "재고월 (YYYYMM)", nullable = true, example = "")
    private String yyyymm;

    /** 문서번호 */
    @Schema(description = "문서번호", nullable = true, example = "")
    private String docno;

    /** 문서라인번호 */
    @Schema(description = "문서라인번호", nullable = true, example = "")
    private String docline;

    /** 이전 문서번호 (없을 경우 'X') */
    @Schema(description = "이전 문서번호 (없을 경우 'X')", nullable = true, example = "")
    private String parentDocno;

    /** 이전 문서라인번호 */
    @Schema(description = "이전 문서라인번호", nullable = true, example = "")
    private String parentDocline;

    /** DP : 입고, WD : 출고 */
    @Schema(description = "DP : 입고, WD : 출고", nullable = true, example = "")
    private String ioFlag;

    /** 발생유형 B(Bonded WareHouse)=보세, G(General WareHouse)=일반 */
    @Schema(description = "발생유형 B(Bonded WareHouse)=보세, G(General WareHouse)=일반", nullable = true, example = "")
    private String ioType;

    /** 보관일수 */
    @Schema(description = "보관일수", nullable = true, example = "")
    private Integer storagedaycnt;

    /** 적용일자 */
    @Schema(description = "적용일자", nullable = true, example = "")
    private String deliverydate;

    /** 외부창고 최초 입고일자 */
    @Schema(description = "외부창고 최초 입고일자", nullable = true, example = "")
    private String inventoryDate;

    /** 해당창고 최초 입고일자 */
    @Schema(description = "해당창고 최초 입고일자", nullable = true, example = "")
    private String warehouseDate;

    /** 물류센터명 */
    @Schema(description = "물류센터명", nullable = true, example = "")
    private String dcname;

    /** 창고코드 */
    @Schema(description = "창고코드", nullable = true, example = "")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명", nullable = true, example = "")
    private String organizename;

    /** 고객코드(거래처) */
    @Schema(description = "고객코드(거래처)", nullable = true, example = "")
    private String custkey;

    /** 아이템의 상태 */
    @Schema(description = "아이템의 상태", nullable = true, example = "")
    private String itemStatus;

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
    @Schema(description = "재고박스", nullable = true, example = "")
    private BigDecimal qtybox;

    /** 박스단위 */
    @Schema(description = "박스단위", nullable = true, example = "")
    private String boxuom;

    /** 박스입수 */
    @Schema(description = "박스입수", nullable = true, example = "")
    private BigDecimal qtyperbox;

    /** 구매번호 */
    @Schema(description = "구매번호", nullable = true, example = "")
    private String pokey;

    /** 구매라인번호 */
    @Schema(description = "구매라인번호", nullable = true, example = "")
    private String poline;

    /** 수출입발주번호 */
    @Schema(description = "수출입발주번호", nullable = true, example = "")
    private String tcspokey;

    /** 선하증권번호 */
    @Schema(description = "선하증권번호", nullable = true, example = "")
    private String convserialno;

    /** 컨테이너 번호 */
    @Schema(description = "컨테이너 번호", nullable = true, example = "")
    private String containerno;

    /** 유통이력번호 */
    @Schema(description = "유통이력번호", nullable = true, example = "")
    private String serialno;

    /** 재고 구분 ID */
    @Schema(description = "재고 구분 ID", nullable = true, example = "")
    private String stockid;

    /** 재고계약유형 */
    @Schema(description = "재고계약유형", nullable = true, example = "")
    private String contracttype;

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

    /** 월마감비용전표번호 */
    @Schema(description = "월마감비용전표번호", nullable = true, example = "")
    private String monthExpNo;

    /** 클레임생성여부 */
    @Schema(description = "클레임생성여부", nullable = true, example = "")
    private String claimFlag;

    /** 과/면세구분 */
    @Schema(description = "과/면세구분", nullable = true, example = "")
    private String taxCls;

    /** 코스트센터코드 */
    @Schema(description = "코스트센터코드", nullable = true, example = "")
    private String costcd;

    /** 비용타입 */
    @Schema(description = "비용타입", nullable = true, example = "")
    private String expensetype;

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

    /** 운송료(비용전표) */
    @Schema(description = "운송료(비용전표)", nullable = true, example = "")
    private BigDecimal transprice;

    /** 기타비용 */
    @Schema(description = "기타비용", nullable = true, example = "")
    private BigDecimal etcprice;

    /** 최초등록자 */
    @Schema(description = "최초등록자", nullable = true, example = "")
    private String addwho;

    /** 최초등록시간 */
    @Schema(description = "최초등록시간", nullable = true, example = "")
    private String adddate;

    /** 최종변경자 */
    @Schema(description = "최종변경자", nullable = true, example = "")
    private String editwho;

    /** 최종변경시간 */
    @Schema(description = "최종변경시간", nullable = true, example = "")
    private String editdate;

    /** 저장조건 */
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storagetype;

    /** 기정산보관료 */
    @Schema(description = "기정산보관료", nullable = true, example = "")
    private BigDecimal ifStorageAmount;

    /** 기정산운송료 */
    @Schema(description = "기정산운송료", nullable = true, example = "")
    private BigDecimal ifEtcAmount;

    /** 보관료(영업공가) */
    @Schema(description = "보관료(영업공가)", nullable = true, example = "")
    private BigDecimal stockAmountSafs;

    /** 보관료(영업) */
    @Schema(description = "보관료(영업)", nullable = true, example = "")
    private BigDecimal stockAmountSafsTaxcls;

    /** */
    @Schema(description = "", nullable = true, example = "")
    private String editflag;

    /** 이체여부 */
    @Schema(description = "이체여부", nullable = true, example = "")
    private String deliveryflag;

    /** 순중량 */
    @Schema(description = "순중량", nullable = true, example = "")
    private BigDecimal netweight;

    /** MD */
    @Schema(description = "MD", nullable = true, example = "")
    private String somdname;

    /** 상품 대분류 */
    @Schema(description = "상품 대분류", nullable = true, example = "")
    private String skuDdesc;

    /** 상품 세분류 */
    @Schema(description = "상품 세분류", nullable = true, example = "")
    private String skuLdesc;

    /** 전년 입고수수료 */
    @Schema(description = "전년 입고수수료", nullable = true, example = "")
    private BigDecimal bgrprice;

    /** 전년 출고수수료 */
    @Schema(description = "전년 출고수수료", nullable = true, example = "")
    private BigDecimal bgiprice;

    /** 전년 창고수수료 */
    @Schema(description = "전년 창고수수료", nullable = true, example = "")
    private BigDecimal bstorageprice;

    /** 관리처코드 */
    @Schema(description = "관리처코드", nullable = true, example = "")
    private String toCustkey;

    /** 관리처명 */
    @Schema(description = "관리처명", nullable = true, example = "")
    private String toCustname;

    /** 입고료증감율 */
    @Schema(description = "입고료증감율", nullable = true, example = "")
    private String grrate;
    
    /** 출고료증감율 */
    @Schema(description = "출고료증감율", nullable = true, example = "")
    private String girate;
    
    /** 창고료증감율 */
    @Schema(description = "창고료증감율", nullable = true, example = "")
    private String storate;
    
    /** 입고료증감금액 */
    @Schema(description = "입고료증감금액", nullable = true, example = "")
    private BigDecimal grrateamt;
    
    /** 출고료증감금액 */
    @Schema(description = "출고료증감금액", nullable = true, example = "")
    private BigDecimal girateamt;
    
    /** 창고료증감금액 */
    @Schema(description = "창고료증감금액", nullable = true, example = "")
    private BigDecimal storateamt;    
    
    /** 일련번호 */
    @Schema(description = "일련번호", nullable = true, example = "")
    private BigDecimal expenseSn;
    
    /** 세액차액 */
    @Schema(description = "세액차액", nullable = true, example = "")
    private BigDecimal taxDiff;
    
    /** 작업일련번호 */
    @Schema(description = "작업일련번호", nullable = true, example = "")
    private BigDecimal workTransactionSn;
    
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
    
}
