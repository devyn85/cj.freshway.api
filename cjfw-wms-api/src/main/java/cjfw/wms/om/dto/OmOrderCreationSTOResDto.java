package cjfw.wms.om.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : YeoSeungCheol (pw6375@cj.net) 
 * @date : 2025.09.26
 * @description : 물류센터간이체 조회 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.26 YeoSeungCheol (pw6375@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "물류센터간 이체 결과")
public class OmOrderCreationSTOResDto extends CommonProcedureDto {
    @Schema(description = "체크여부")
    private String checkyn;

    @Schema(description = "STORERKEY")
    private String storerkey;
    
    @Schema(description = "DC코드")
    private String dccode;
    
    @Schema(description = "ORGANIZE")
    private String organize;
    
    @Schema(description = "AREA")
    private String area;
    
    @Schema(description = "SKU")
    private String sku;
    
    @Schema(description = "상품명")
    private String skuName;

    @Schema(description = "단위")
    private String uom;
    
    @Schema(description = "재고등급")
    private String stockgrade;
    
    @Schema(description = "재고유형")
    private String stocktype;
    
    @Schema(description = "LOC")
    private String loc;
    
    @Schema(description = "LOT")
    private String lot;
    
    @Schema(description = "STOCKID")
    private String stockid;

    // 수량
    @Schema(description = "수량")
    private BigDecimal qty;
    
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;
    
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;
    
    @Schema(description = "박스당 낱개수")
    private String qtyperbox;
    
    @Schema(description = "가용수량")
    private BigDecimal posbqty;
    
    @Schema(description = "입고 수량(TO)")
    private BigDecimal toOrderqty;

    // from
    @Schema(description = "출고 STORERKEY")
    private String fromStorerkey;
    
    @Schema(description = "출고 DC코드")
    private String fromDccode;
    
    @Schema(description = "출고 ORGANIZE")
    private String fromOrganize;
    
    @Schema(description = "출고 AREA")
    private String fromArea;
    
    @Schema(description = "출고 SKU")
    private String fromSku;
    
    @Schema(description = "출고 UOM")
    private String fromUom;
    
    @Schema(description = "출고 LOC")
    private String fromLoc;
    
    @Schema(description = "출고 LOT")
    private String fromLot;
    
    @Schema(description = "출고 STOCKID")
    private String fromStockid;
    
    @Schema(description = "출고 재고등급")
    private String fromStockgrade;
    
    @Schema(description = "출고 재고등급명")
    private String fromStockgradeName;
    
    @Schema(description = "출고 재고유형")
    private String fromStocktype;

    // TO
    @Schema(description = "입고 DC코드")
    private String toDccode;
    
    @Schema(description = "입고 ORGANIZE")
    private String toOrganize;
    
    @Schema(description = "입고 AREA")
    private String toArea;
    
    @Schema(description = "입고 SKU")
    private String toSku;
    
    @Schema(description = "입고 UOM")
    private String toUom;
    
    @Schema(description = "입고 LOC")
    private String toLoc;
    
    @Schema(description = "입고 LOT")
    private String toLot;
    
    @Schema(description = "입고 STOCKID")
    private String toStockid;
    
    @Schema(description = "입고 재고등급")
    private String toStockgrade;

    @Schema(description = "입고 재고등급명")
    private String toStockgradeName;
    
    @Schema(description = "입고 재고유형")
    private String toStocktype;

    // 기타 표시
    @Schema(description = "유통기한 임박여부")
    private String neardurationyn;
    
    @Schema(description = "LOT1")
    private String lottable01;
    
    @Schema(description = "기간표시(유통기한/기간)")
    private String durationTerm;
    
    @Schema(description = "시리얼번호")
    private String serialno;
    
    @Schema(description = "보관유형")
    private String storagetype;
    
    @Schema(description = "기간값")
    private String duration;
    
    @Schema(description = "기간타입")
    private String durationtype;
    
    @Schema(description = "기타 수량1")
    private BigDecimal etcqty1;
    
    @Schema(description = "기타 수량2")
    private BigDecimal etcqty2;

    // 시리얼 관련
    @Schema(description = "공장명")
    private String factoryname;
    
    @Schema(description = "BL번호")
    private String convserialno;
    
    @Schema(description = "도축일")
    private String butcherydt;
    
    @Schema(description = "계약처 코드")
    private String contractcompany;
    
    @Schema(description = "계약처명")
    private String contractcompanyname;
    
    @Schema(description = "유통기한 FROM")
    private String fromvaliddt;
    
    @Schema(description = "유통기한 TO")
    private String tovaliddt;
    
    @Schema(description = "계약유형")
    private String contracttype;
    
    @Schema(description = "바코드")
    private String barcode;

    /** PROCESSFLAG */
    @Schema(description = "처리상태")
    private String processflag;
    
    /** PROCESSMSG */
    @Schema(description = "처리메시지")
    private String processmsg;
    
    /** DESCRIPTION;. */
    @Schema(description = "DESCRIPTION", example = "")
    private String description;

    // 표시용 부가
    @Schema(description = "출고 DC명")
    private String fromDcname;
    @Schema(description = "입고 DC명")
    private String toDcname;
    @Schema(description = "참조 고객문서ID")
    private String refCustDocId;
    @Schema(description = "구분(Y/N)")
    private String gubun;
    
    private String line01;
    private String line02;

    // 메모
    @Schema(description = "비고")
    private String memo1;
    
    // 유통기한 색상 표시
    @Schema(description = "유통기한 색상 표시")
    private String durationStatus;
    
    @Schema(description = "커스텀 엑스트라 체크박스")
	private String customRowCheckYn="N";
    
    @Schema(description = "식별번호유무")
    private String serialyn;
    
    @Schema(description = "출고 수량")
    private BigDecimal fromOrderqty;
    
    
}
