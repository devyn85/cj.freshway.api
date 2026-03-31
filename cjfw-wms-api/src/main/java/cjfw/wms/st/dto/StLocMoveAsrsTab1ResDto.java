package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.16 
 * @description : 자동창고보충 이동대상 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동창고보충 이동대상 조회 결과")
public class StLocMoveAsrsTab1ResDto {
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC01")
    private String dccode;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "A01")
    private String organize;

    /**
     * 재고위치코드
     */
    @Schema(description = "재고위치코드", example = "S01")
    private String fromStocktype;

    /**
     * 재고위치명칭
     */
    @Schema(description = "재고위치명칭", example = "일반재고")
    private String stocktype;

    /**
     * 재고속성코드
     */
    @Schema(description = "재고속성코드", example = "G01")
    private String fromStockgrade;

    /**
     * 재고속성명칭
     */
    @Schema(description = "재고속성명칭", example = "양품")
    private String stockgrade;

    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "A-01-01-01")
    private String loc;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU00123")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "샘플상품")
    private String skuname;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetype;

    /**
     * 고정로케이션
     */
    @Schema(description = "고정로케이션", example = "B-01-01-01")
    private String fixloc;

    /**
     * 박스입수
     */
    @Schema(description = "박스입수", example = "20")
    private BigDecimal qtyperbox;

    /**
     * BOX
     */
    @Schema(description = "BOX", example = "5")
    private BigDecimal posbqtyBox;

    /**
     * EA
     */
    @Schema(description = "EA", example = "10")
    private BigDecimal posbqtyEa;

    /**
     * 이동로케이션
     */
    @Schema(description = "이동로케이션", example = "C-01-01-01")
    private String toLoc;

    /**
     * BOX
     */
    @Schema(description = "BOX", example = "5")
    private BigDecimal toOrderqtyBox;

    /**
     * EA
     */
    @Schema(description = "EA", example = "10")
    private BigDecimal toOrderqtyEa;

    /**
     * 소비기한임박여부
     */
    @Schema(description = "소비기한임박여부", example = "Y")
    private String neardurationyn;

    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "2025-12-31")
    private String lottable01;

    /**
     * 소비기간(잔여/전체)
     */
    @Schema(description = "소비기간(잔여/전체)", example = "30/90")
    private String durationTerm;

    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /**
     * 현재고수량
     */
    @Schema(description = "현재고수량", example = "100")
    private BigDecimal qty;

    /**
     * 재고할당수량
     */
    @Schema(description = "재고할당수량", example = "10")
    private BigDecimal qtyallocated;

    /**
     * 피킹재고
     */
    @Schema(description = "피킹재고", example = "5")
    private BigDecimal qtypicked;

    /**
     * 이동가능수량
     */
    @Schema(description = "이동가능수량", example = "85")
    private BigDecimal posbqty;

    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "S123456789")
    private String serialno;

    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "B123456789")
    private String barcode;

    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL12345")
    private String convserialno;

    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "2025-07-01")
    private String butcherydt;

    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "행복도축장")
    private String factoryname;

    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "장기계약")
    private String contracttype;

    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CUST001")
    private String contractcompany;

    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "(주)행복유통")
    private String contractcompanyname;

    /**
     * 유효일자(FROM)
     */
    @Schema(description = "유효일자(FROM)", example = "2025-01-01")
    private String fromvaliddt;

    /**
     * 유효일자(TO)
     */
    @Schema(description = "유효일자(TO)", example = "2025-12-31")
    private String tovaliddt;

    /**
     * 로트
     */
    @Schema(description = "로트", example = "LOT001")
    private String fromLot;

    /**
     * 개체식별/유통이력
     */
    @Schema(description = "개체식별/유통이력", example = "ID12345")
    private String fromStockid;

    /**
     * 작업구역
     */
    @Schema(description = "작업구역", example = "AREA01")
    private String fromArea;

    /**
     *
     */
    @Schema(description = "", example = "50")
    private BigDecimal toOrderqty;

    /**
     *
     */
    @Schema(description = "", example = "90")
    private String duration;

    /**
     *
     */
    @Schema(description = "", example = "DAY")
    private String durationtype;

    /**
     *
     */
    @Schema(description = "", example = "DC01")
    private String fromDccode;

    /**
     *
     */
    @Schema(description = "", example = "STR001")
    private String fromStorerkey;

    /**
     *
     */
    @Schema(description = "", example = "A01")
    private String fromOrganize;

    /**
     *
     */
    @Schema(description = "", example = "SKU00123")
    private String fromSku;

    /**
     *
     */
    @Schema(description = "", example = "A-01-01-01")
    private String fromLoc;

    /**
     *
     */
    @Schema(description = "", example = "100")
    private BigDecimal fromOrderqty;

    /**
     *
     */
    @Schema(description = "", example = "EA")
    private String fromUom;

    /**
     *
     */
    @Schema(description = "", example = "DC01")
    private String toDccode;

    /**
     *
     */
    @Schema(description = "", example = "STR001")
    private String toStorerkey;

    /**
     *
     */
    @Schema(description = "", example = "A01")
    private String toOrganize;

    /**
     *
     */
    @Schema(description = "", example = "AREA01")
    private String toArea;

    /**
     *
     */
    @Schema(description = "", example = "SKU00123")
    private String toSku;

    /**
     *
     */
    @Schema(description = "", example = "LOT001")
    private String toLot;

    /**
     *
     */
    @Schema(description = "", example = "ID12345")
    private String toStockid;

    /**
     *
     */
    @Schema(description = "", example = "G01")
    private String toStockgrade;

    /**
     *
     */
    @Schema(description = "", example = "S01")
    private String toStocktype;

    /**
     *
     */
    @Schema(description = "", example = "EA")
    private String toUom;

    /**
     *
     */
    @Schema(description = "", example = "10")
    private BigDecimal etcqty1;

    /**
     *
     */
    @Schema(description = "", example = "5")
    private BigDecimal etcqty2;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
