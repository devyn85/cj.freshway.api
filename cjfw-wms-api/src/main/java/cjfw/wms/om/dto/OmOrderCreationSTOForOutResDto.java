package cjfw.wms.om.dto;

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
 * @date : 2025.07.08
 * @description : 외부센터 보충발주 조회 목록 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부센터 보충발주 조회 목록") 
public class OmOrderCreationSTOForOutResDto extends CommonProcedureDto {	    
    /** 물류센터 코드 */
    @Schema(description = "물류센터 코드", example = "")
    private String dccode;

    /** 고객사 코드 */
    @Schema(description = "고객사 코드", example = "")
    private String storerkey;

    /** 조직 코드 */
    @Schema(description = "조직 코드", example = "")
    private String organize;

    /** 조직명 */
    @Schema(description = "조직명", example = "")
    private String organizeName;

    /** 구역 */
    @Schema(description = "구역", example = "")
    private String area;

    /** 로트 */
    @Schema(description = "로트", example = "")
    private String lot;

    /** 재고 ID */
    @Schema(description = "재고 ID", example = "")
    private String stockid;

    /** 재고속성 */
    @Schema(description = "재고속성", example = "")
    private String stockgrade;

    /** 재고위치*/
    @Schema(description = "재고위치", example = "")
    private String stocktype;

    /** ㅍ명 */
    @Schema(description = "재고위치명", example = "")
    private String stocktypename;

    /** 재고속성 */
    @Schema(description = "재고속성명", example = "")
    private String stockgradename;

    /** 위치 */
    @Schema(description = "위치", example = "")
    private String loc;

    /** 상품 코드 */
    @Schema(description = "상품 코드", example = "")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명", example = "")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위", example = "")
    private String uom;

    /** 수량 */
    @Schema(description = "수량", example = "")
    private BigDecimal qty;

    /** 박스 수량 */
    @Schema(description = "박스 수량", example = "")
    private BigDecimal boxqty;

    /** 할당 수량 */
    @Schema(description = "할당 수량", example = "")
    private BigDecimal qtyallocated;

    /** 피킹 수량 */
    @Schema(description = "피킹 수량", example = "")
    private BigDecimal qtypicked;

    /** 가용 수량 */
    @Schema(description = "가용 수량", example = "")
    private BigDecimal posbqty;

    /** 유통기한 임박 여부 */
    @Schema(description = "유통기한 임박 여부", example = "")
    private String neardurationyn;

    /** 제조일자 */
    @Schema(description = "제조일자", example = "")
    private String lottable01;

    /** 유통기한/기준일 */
    @Schema(description = "유통기한/기준일", example = "")
    private String durationTerm;
    
    /** 소비기한잔여율 */
    @Schema(description = "소비기한잔여율", example = "")
    private BigDecimal usebydateFreeRt;

    /** 이력번호 */
    @Schema(description = "이력번호", example = "")
    private String serialno;

    /** 저장유형 */
    @Schema(description = "저장유형", example = "")
    private String storagetype;

    /** 유통기한 */
    @Schema(description = "유통기한", example = "")
    private String duration;

    /** 유통기한 타입 */
    @Schema(description = "유통기한 타입", example = "")
    private String durationtype;

    /** 출고 DCCODE */
    @Schema(description = "출고 DCCODE", example = "")
    private String fromDccode;    
    
    /** 출고 DCNAME */
    @Schema(description = "출고 DCNAME", example = "")
    private String fromDcname;

    /** 출고 STORERKEY */
    @Schema(description = "출고 STORERKEY", example = "")
    private String fromStorerkey;

    /** 출고 ORGANIZE */
    @Schema(description = "출고 ORGANIZE", example = "")
    private String fromOrganize;

    /** 출고 AREA */
    @Schema(description = "출고 AREA", example = "")
    private String fromArea;

    /** 출고 SKU */
    @Schema(description = "출고 SKU", example = "")
    private String fromSku;

    /** 출고 LOC */
    @Schema(description = "출고 LOC", example = "")
    private String fromLoc;

    /** 출고 LOT */
    @Schema(description = "출고 LOT", example = "")
    private String fromLot;

    /** 출고 STOCKID */
    @Schema(description = "출고 STOCKID", example = "")
    private String fromStockid;

    /** 출고 STOCKGRADE */
    @Schema(description = "출고 STOCKGRADE", example = "")
    private String fromStockgrade;

    /** 출고 STOCKTYPE */
    @Schema(description = "출고 STOCKTYPE", example = "")
    private String fromStocktype;

    /** 출고 수량 */
    @Schema(description = "출고 수량", example = "")
    private java.math.BigDecimal fromOrderqty;

    /** 출고 UOM */
    @Schema(description = "출고 UOM", example = "")
    private String fromUom;

    /** 입고 DCCODE */
    @Schema(description = "입고 DCCODE", example = "")
    private String toDccode;
    
    /** 입고 DCNAME */
    @Schema(description = "입고 DCNAME", example = "")
    private String toDcname;

    /** 입고 STORERKEY */
    @Schema(description = "입고 STORERKEY", example = "")
    private String toStorerkey;

    /** 입고 AREA */
    @Schema(description = "입고 AREA", example = "")
    private String toArea;
    
    /** 입고 ORGANIZE */
    @Schema(description = "입고 ORGANIZE", example = "")
    private String toOrganize;

    /** 입고 SKU */
    @Schema(description = "입고 SKU", example = "")
    private String toSku;

    /** 입고 LOC */
    @Schema(description = "입고 LOC", example = "")
    private String toLoc;

    /** 입고 LOT */
    @Schema(description = "입고 LOT", example = "")
    private String toLot;

    /** 입고 STOCKID */
    @Schema(description = "입고 STOCKID", example = "")
    private String toStockid;

    /** 입고 STOCKGRADE */
    @Schema(description = "입고 STOCKGRADE", example = "")
    private String toStockgrade;

    /** 입고 STOCKTYPE */
    @Schema(description = "입고 STOCKTYPE", example = "")
    private String toStocktype;

    /** 입고 수량 */
    @Schema(description = "입고 수량", example = "")
    private BigDecimal toOrderqty;

    /** 입고 UOM */
    @Schema(description = "입고 UOM", example = "")
    private String toUom;

    /** 기타 수량1 */
    @Schema(description = "기타 수량1", example = "")
    private BigDecimal etcqty1;

    /** 기타 수량2 */
    @Schema(description = "기타 수량2", example = "")
    private BigDecimal etcqty2;

    /** 도축장 */
    @Schema(description = "도축장", example = "")
    private String factoryname;

    /** B/L번호 */
    @Schema(description = "B/L번호", example = "")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자", example = "")
    private String butcherydt;

    /** 계약업체 */
    @Schema(description = "계약업체", example = "")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", example = "")
    private String contractcompanyname;

    /** 유효 시작일자 */
    @Schema(description = "유효 시작일자", example = "")
    private String fromvaliddt;

    /** 유효 종료일자 */
    @Schema(description = "유효 종료일자", example = "")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형", example = "")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드", example = "")
    private String barcode;

    /** 구매단위 */
    @Schema(description = "구매단위", example = "")
    private String purchaseuom;

    /** 박스단위환산 */
    @Schema(description = "박스단위환산", example = "")
    private BigDecimal boxCal;

    /** 비정량 여부 */
    @Schema(description = "비정량 여부", example = "")
    private String line01;

    /** 표준중량 */
    @Schema(description = "표준중량", example = "")
    private BigDecimal line02;

    /** 박스입수량 */
    @Schema(description = "박스입수량", example = "")
    private BigDecimal qtyperbox;

    /** 메모 */
    @Schema(description = "메모", example = "")
    private String memo1;

    /** 평균중량 */
    @Schema(description = "평균중량", example = "")
    private BigDecimal avgweight;

    /** 환산 박스 수 */
    @Schema(description = "환산 박스 수", example = "")
    private BigDecimal calbox;

    /** 실박스 예정 */
    @Schema(description = "실박스 예정", example = "")
    private BigDecimal realorderbox;

    /** 실박스 확정 */
    @Schema(description = "실박스 확정", example = "")
    private BigDecimal realcfmbox;

    /** 작업박스 수량 */
    @Schema(description = "작업박스 수량", example = "")
    private BigDecimal tranbox;

    /** 박스처리유무 */
    @Schema(description = "박스처리유무", example = "")
    private String boxflag;

    /** 재고 박스 수량 */
    @Schema(description = "재고 박스 수량", example = "")
    private BigDecimal stbox;
    
    /** 삭제 여부 */
    @Schema(description = "삭제 여부", example = "")
    private String delYn;
    
    /** PROCESSFLAG */
    @Schema(description = "PROCESSFLAG", example = "")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "PROCESSMSG", example = "")
    private String processmsg;
    
    /** DESCRIPTION;. */
    @Schema(description = "DESCRIPTION", example = "")
    private String description;
    
}
