package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.16 
 * @description : 자동창고보충 이동결과 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "자동창고보충 이동결과 조회 결과")
public class StLocMoveAsrsTab2ResDto {
	
	    /**
	     * 물류센터
	     */
	    @Schema(description = "물류센터", example = "DC01")
	    private String dccode;

	    /**
	     * 창고
	     */
	    @Schema(description = "창고", example = "W01")
	    private String organize;

	    /**
	     * 재고위치
	     */
	    @Schema(description = "재고위치", example = "Stage")
	    private String stocktype;

	    /**
	     * 재고속성
	     */
	    @Schema(description = "재고속성", example = "A")
	    private String stockgrade;

	    /**
	     * 로케이션
	     */
	    @Schema(description = "로케이션", example = "L01-A-01-01")
	    private String loc;

	    /**
	     * 상품코드
	     */
	    @Schema(description = "상품코드", example = "SKU001")
	    private String sku;

	    /**
	     * 상품명칭
	     */
	    @Schema(description = "상품명칭", example = "샘플상품")
	    private String skuname;

	    /**
	     * 저장조건
	     */
	    @Schema(description = "저장조건", example = "냉장")
	    private String storagetype;

	    /**
	     * 고정로케이션
	     */
	    @Schema(description = "고정로케이션", example = "L01-A-01-02")
	    private String fixloc;

	    /**
	     * BOX
	     */
	    @Schema(description = "BOX", example = "10")
	    private BigDecimal posbqtyBox;

	    /**
	     * EA
	     */
	    @Schema(description = "EA", example = "5")
	    private BigDecimal posbqtyEa;

	    /**
	     * 이동로케이션
	     */
	    @Schema(description = "이동로케이션", example = "L02-B-02-02")
	    private String toLoc;

	    /**
	     * BOX
	     */
	    @Schema(description = "BOX", example = "10")
	    private BigDecimal toOrderqtyBox;

	    /**
	     * EA
	     */
	    @Schema(description = "EA", example = "5")
	    private BigDecimal toOrderqtyEa;

	    /**
	     * 소비기한임박여부
	     */
	    @Schema(description = "소비기한임박여부", example = "Y")
	    private String neardurationyn;

	    /**
	     * 기준일(소비,제조)
	     */
	    @Schema(description = "기준일(소비,제조)", example = "2025-10-24")
	    private String lottable01;

	    /**
	     * 소비기간(잔여/전체)
	     */
	    @Schema(description = "소비기간(잔여/전체)", example = "30/60")
	    private String durationTerm;

	    /**
	     * 단위
	     */
	    @Schema(description = "단위", example = "BOX")
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
	    @Schema(description = "바코드", example = "8801234567890")
	    private String barcode;

	    /**
	     * B/L번호
	     */
	    @Schema(description = "B/L번호", example = "BL123456")
	    private String convserialno;

	    /**
	     * 도축일자
	     */
	    @Schema(description = "도축일자", example = "2025-09-01")
	    private String butcherydt;

	    /**
	     * 도축장
	     */
	    @Schema(description = "도축장", example = "행복도축장")
	    private String factoryname;

	    /**
	     * 계약유형
	     */
	    @Schema(description = "계약유형", example = "A")
	    private String contracttype;

	    /**
	     * 계약업체
	     */
	    @Schema(description = "계약업체", example = "CUST001")
	    private String contractcompany;

	    /**
	     * 계약업체명
	     */
	    @Schema(description = "계약업체명", example = "(주)좋은회사")
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
	     * PROCESSFLAG
	     */
	    @Schema(description = "PROCESSFLAG", example = "Y")
	    private String processflag;

	    /**
	     * PROCESSMSG
	     */
	    @Schema(description = "PROCESSMSG", example = "Success")
	    private String processmsg;

	    /**
	     * QTYPERBOX
	     */
	    @Schema(description = "QTYPERBOX", example = "12")
	    private BigDecimal qtyperbox;

	    /**
	     * TO_ORDERQTY
	     */
	    @Schema(description = "TO_ORDERQTY", example = "120")
	    private BigDecimal toOrderqty;

	    /**
	     * DURATION
	     */
	    @Schema(description = "DURATION", example = "60")
	    private String duration;

	    /**
	     * DURATIONTYPE
	     */
	    @Schema(description = "DURATIONTYPE", example = "D")
	    private String durationtype;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
