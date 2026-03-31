package cjfw.wms.wd.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.11.13 
 * @description : 외부비축출고처리 운송비 배분 Entity
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                 MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.13    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고처리 운송비 배분 Entity") 
public class WdShipmentExDCEntity extends CommonProcedureDto {
    /** 데이터번호 */
    @Schema(description = "데이터번호", nullable = true, example = "")
    private BigDecimal serialkey;

    /** 센터 코드 */
    @Schema(description = "센터 코드", nullable = true, example = "")
    private String dccode;

    /** 문서 번호 */
    @Schema(description = "문서 번호", nullable = true, example = "")
    private String docno;
    
    /** 문서 라인 */
    @Schema(description = "문서 라인", nullable = true, example = "")
    private String docline;

    /** 보관처 코드 */
    @Schema(description = "보관처 코드", nullable = true, example = "")
    private String storerkey;

    /** 문서 타입 */
    @Schema(description = "문서 타입", nullable = true, example = "")
    private String doctype;

    /** 창고 코드 */
    @Schema(description = "창고 코드", nullable = true, example = "")
    private String organize;

    /** 창고 이름 */
    @Schema(description = "창고 이름", nullable = true, example = "")
    private String organizeName;
    
    /** AREA */
    @Schema(description = "AREA", nullable = true, example = "")
    private String area;

    /** 주문 타입 */
    @Schema(description = "주문 타입", nullable = true, example = "")
    private String ordertype;

    /** 주문 타입명 */
    @Schema(description = "주문 타입명", nullable = true, example = "")
    private String ordertypeName;
    
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = true, example = "")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭", nullable = true, example = "")
    private String skuname;
    
    /** 단위 */
    @Schema(description = "단위", nullable = true, example = "")
    private String uom;

    /** 상태 */
    @Schema(description = "상태", nullable = true, example = "")
    private String status;

    /** 상태 이름 */
    @Schema(description = "상태 이름", nullable = true, example = "")
    private String statusname;

    /** 문서일자 */
    @Schema(description = "문서일자", nullable = true, example = "")
    private String docdt;

    /** 관리처 코드 */
    @Schema(description = "관리처 코드", nullable = true, example = "")
    private String toCustkey;

    /** 관리처 이름 */
    @Schema(description = "관리처 이름", nullable = true, example = "")
    private String toCustname;
    
    /** 계약업체 코드 */
    @Schema(description = "관리처 코드", nullable = true, example = "")
    private String contractcompany;

    /** 관리처 이름 */
    @Schema(description = "계약업체 이름", nullable = true, example = "")
    private String contractcompanyname;

    /** 계약유형 */
    @Schema(description = "계약유형", nullable = true, example = "")
    private String contracttype;
    
    /** 저장조건 */
    @Schema(description = "저장조건", nullable = true, example = "")
    private String storagetype;
    
    /** 저장조건명 */
    @Schema(description = "저장조건명", nullable = true, example = "")
    private String storagetypename;
    
    /** 도축일 */
    @Schema(description = "도축일", nullable = true, example = "")
    private String butcherydt;
    
    /** FROM */
    @Schema(description = "FROM", nullable = true, example = "")
    private String fromvaliddt;    
    
    /** TO */
    @Schema(description = "TO", nullable = true, example = "")
    private String tovaliddt;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = true, example = "")
    private String serialno;
    
    /** B/L번호 */
    @Schema(description = "B/L번호", nullable = true, example = "")
    private String convserialno;
    
    /** BARCODE */
    @Schema(description = "BARCODE", nullable = true, example = "")
    private String barcode;
    
    /** SERIALORDERQTY */
    @Schema(description = "SERIALORDERQTY", nullable = true, example = "")
    private java.math.BigDecimal serialorderqty;   
    
    /** SERIALINSPECTQTY */
    @Schema(description = "SERIALINSPECTQTY", nullable = true, example = "")
    private java.math.BigDecimal serialinspectqty;
    
    /** SERIALSCANWEIGHT */
    @Schema(description = "SERIALSCANWEIGHT", nullable = true, example = "")
    private java.math.BigDecimal serialscanweight;
    
    /** CHECKFLAG */
    @Schema(description = "CHECKFLAG", nullable = true, example = "")
    private String checkflag; 
    
    /** LINE01 */
    @Schema(description = "LINE01", nullable = true, example = "")
    private String line01;    
    
    /** FONTCOLOR */
    @Schema(description = "FONTCOLOR", nullable = true, example = "")
    private String fontcolor;
    
    /** 평균중량*/
    @Schema(description = "평균중량", nullable = true, example = "")
    private java.math.BigDecimal avgweight;
    
    /** 환산박스*/
    @Schema(description = "환산박스", nullable = true, example = "")
    private java.math.BigDecimal calbox;
    
    /** 실박스예정 */
    @Schema(description = "실박스예정", nullable = true, example = "")
    private java.math.BigDecimal realorderbox;
    
    /** 실박스확정 */
    @Schema(description = "실박스확정", nullable = true, example = "")
    private java.math.BigDecimal realcfmbox;
    
    /** 작업박스수량 */
    @Schema(description = "작업박스수량", nullable = true, example = "")
    private java.math.BigDecimal tranbox;
    
    /** 박스처리유무*/
    @Schema(description = "박스처리유무", nullable = true, example = "")
    private String boxflag;
    
    /** STO플래그*/
    @Schema(description = "STO플래그", nullable = true, example = "")
    private String stoflag; 

    /** 전표일자 */
    @Schema(description = "전표일자", nullable = true, example = "")
    private String slipdt;

    /** 전표번호 */
    @Schema(description = "전표번호", nullable = true, example = "")
    private String slipno;
    
    /** 전표라인 */
    @Schema(description = "전표라인", nullable = true, example = "")
    private String slipline;
    
    /** 전체 취소 상태 */
    @Schema(description = "전체 취소 상태", nullable = true, example = "")
    private String allCancelStatus;

    /** 가/진 여부 */
    @Schema(description = "가/진 여부", nullable = true, example = "")
    private String realYn;
    
    /** 가/진 여부명 */
    @Schema(description = "가/진 여부명", nullable = true, example = "")
    private String realYnNm;
    
    /** 과세,면세 구분(비율) */
    @Schema(description = "과세,면세 구분(비율)", nullable = true, example = "")
    private BigDecimal other05;
    
    /** 과세,면세 구분 */
    @Schema(description = "과세,면세 구분", nullable = true, example = "")
    private String taxCls;
    
    /** SERIALYN */
    @Schema(description = "SERIALYN", nullable = true, example = "")
    private String serialyn;
    
    /** PO번호 */
    @Schema(description = "PO번호", nullable = true, example = "")
    private String pokey;
    
    /** PO라인 */
    @Schema(description = "PO라인", nullable = true, example = "")
    private String poline;
    
    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = true, example = "")
    private String lottable01;
    
    /** 소비기간(잔여/전체) */
    @Schema(description = "소비기간(잔여/전체)", nullable = true, example = "")
    private String durationTerm;
    
    /** REFERENCE02 */
    @Schema(description = "REFERENCE02", nullable = true, example = "")
    private String reference02;
    
    /** 주문수량에 대한 총 보관료 */
    @Schema(description = "주문수량에 대한 총 보관료", nullable = true, example = "")
    private java.math.BigDecimal reference08;
    
    /** 계산된 보관료 */
    @Schema(description = "계산된 보관료", nullable = true, example = "")
    private java.math.BigDecimal reference09;
    
    /** 운송료 */
    @Schema(description = "운송료", nullable = true, example = "")
    private java.math.BigDecimal reference10;
    
    /** 운송료(공급가) */
    @Schema(description = "운송료(공급가)", nullable = true, example = "")
    private java.math.BigDecimal trandeliveryprice;
    
    /** 사유 */
    @Schema(description = "사유", nullable = true, example = "")
    private String reasoncode;
    
    /** 처리결과 */
    @Schema(description = "처리결과", nullable = true, example = "")
    private String reasonmsg;
    
    /** NEARDURATIONYN */
    @Schema(description = "NEARDURATIONYN", nullable = true, example = "")
    private String neardurationyn;
    
    /** 박스입수 */
    @Schema(description = "박스입수", nullable = true, example = "")
    private java.math.BigDecimal qtyperbox;
    
    /** LASTLOTTABLE01 */
    @Schema(description = "LASTLOTTABLE01", nullable = true, example = "")
    private String lastlottable01;
    
    /** 재고ID */
    @Schema(description = "재고ID", nullable = true, example = "")
    private String stockid;
    
    /** STOCKGRADE */
    @Schema(description = "STOCKGRADE", nullable = true, example = "")
    private String stockgrade;
    
    /** STORERUOM */
    @Schema(description = "STORERUOM", nullable = true, example = "")
    private String storeruom;
    
    /** 주문수량 */
    @Schema(description = "주문수량", nullable = true, example = "")
    private java.math.BigDecimal orderqty;
    
    /** INSPECTQTY */
    @Schema(description = "INSPECTQTY", nullable = true, example = "")
    private java.math.BigDecimal inspectqty;
    
    /** 출고수량*/
    @Schema(description = "출고수량", nullable = true, example = "")
    private java.math.BigDecimal confirmqty;
    
    /** 결품수량*/
    @Schema(description = "결품수량", nullable = true, example = "")
    private java.math.BigDecimal shortageqty;
    
    /** 결품작업량*/
    @Schema(description = "결품작업량", nullable = true, example = "")
    private java.math.BigDecimal shortagetranqty;
    
    /** 출고작업량*/
    @Schema(description = "출고작업량", nullable = true, example = "")
    private java.math.BigDecimal tranqty;
    
    /** TOLOC*/
    @Schema(description = "TOLOC", nullable = true, example = "")
    private String toloc;
    
    /** 중량*/
    @Schema(description = "중량", nullable = true, example = "")
    private java.math.BigDecimal weight;
    
    /** 배송채널*/
    @Schema(description = "배송채널", nullable = true, example = "")
    private String channel;    
    
    /** BUNJA*/
    @Schema(description = "BUNJA", nullable = true, example = "")
    private String bunja;
    
    /** BUNMO*/
    @Schema(description = "BUNMO", nullable = true, example = "")
    private String bunmo;
    
    /** 출고방법 */
    @Schema(description = "출고방법", nullable = false, example = "")
    private String deliverytypename;
    
    /** 창고요율등록여부*/
    @Schema(description = "창고요율등록여부", nullable = true, example = "")
    private String exdcrateYn;    
    
    /** 처리결과수 */
    @Schema(description = "처리결과수", nullable = true, example = "")
    private Integer cnt;    
    
    /**발주번호 첫번째 값 */
    @Schema(description = "발주번호 첫번째 값", nullable = true, example = "")
    private String poDivCd;    
    
    /** 결품 사유 */
    @Schema(description = "결품 사유", nullable = true, example = "")
    private String inReasoncode;
    
    /** 결품 처리결과 */
    @Schema(description = "결품 처리결과", nullable = true, example = "")
    private String inReasonmsg;
    
    /** 배부 운송비*/
    @Schema(description = "배부 운송비", nullable = true, example = "")
    private java.math.BigDecimal carrierAmount;
    
    /** 채번 번호 */
    @Schema(description = "채번 번호", nullable = true, example = "")
    private Long transactionSn;
    
    /** 등록일시 */
    @Schema(description = "등록일시", nullable = true, example = "")
    private String adddate;

    /** 수정일시 */
    @Schema(description = "수정일시", nullable = true, example = "")
    private String editdate;

    /** 등록자 */
    @Schema(description = "등록자", nullable = true, example = "")
    private String addwho;
    
    /** 등록자명 */
    @Schema(description = "등록자명", nullable = true, example = "")
    private String addwhoNm;
    
    /** 수정자 */
    @Schema(description = "수정자", nullable = true, example = "")
    private String editwho;
    
    /** 수정자명 */
    @Schema(description = "수정자명", nullable = true, example = "")
    private String editwhoNm;
    
    /** 프로시저 실행 성공여부 */
    @Schema(description = "프로시저 실행 성공여부", example = "")
    private Integer success;
    
    /** 프로시저 실행 에러코드 */
    @Schema(description = "프로시저 실행 에러코드", example = "")
    private Integer err;
    
    /** 프로시저 실행 에러메시지 */
    @Schema(description = "프로시저 실행 에러메시지", example = "")
    private String errmsg;
    
}
