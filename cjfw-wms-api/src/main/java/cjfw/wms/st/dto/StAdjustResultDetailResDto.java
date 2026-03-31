package cjfw.wms.st.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** 출고진행현황 목록 결과 DTO */
@Data
@Schema(description = "출고진행현황 목록 결과")
public class StAdjustResultDetailResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

    /** 상품코드 */
    @Schema(description = "상품코드", example = "ITEM_A")
    private String sku;

    /** 상품명칭 */
    @Schema(description = "상품명칭", example = "고급 사과")
    private String skuname;

    /** 로케이션 */
    @Schema(description = "로케이션", example = "A-01-01")
    private String loc;

    /** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", example = "20250101")
    private String lottable01;

    /** 유통기간(잔여/전체) */
    @Schema(description = "유통기간(잔여/전체)", example = "100/365")
    private String durationTerm;

    /** 이력번호 */
    @Schema(description = "이력번호", example = "SN1234567890")
    private String serialno;

    /** 바코드 */
    @Schema(description = "바코드", example = "BARCODE12345")
    private String barcode;

    /** B/L번호 */
    @Schema(description = "B/L번호", example = "BL20250523001")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자", example = "20241225")
    private String butcherydt;

    /** 도축장 */
    @Schema(description = "도축장", example = "대한 도축장")
    private String factoryname;

    /** 유효일자(FROM) */
    @Schema(description = "유효일자(FROM)", example = "20250101")
    private String fromvaliddt;

    /** 유효일자(TO) */
    @Schema(description = "유효일자(TO)", example = "20251231")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형", example = "일반계약")
    private String contracttype;

    /** 계약업체 */
    @Schema(description = "계약업체", example = "CUST_ABC")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명", example = "ABC 유통")
    private String contractcompanyname;

    /** 조정수량 */
    @Schema(description = "조정수량", example = "50.0")
    private BigDecimal confirmqty;

    /** 귀속부서코드 */
    @Schema(description = "귀속부서코드", example = "DEPT001")
    private String costcd;

    /** 귀속부서명 */
    @Schema(description = "귀속부서명", example = "영업부")
    private String costname;

    /** 단위 */
    @Schema(description = "단위", example = "EA")
    private String uom;

    /** 입출고타입 */
    @Schema(description = "입출고타입", example = "입고")
    private String iotype;

    /** 등급 */
    @Schema(description = "등급", example = "A")
    private String seriallevel;

    /** 규격 */
    @Schema(description = "규격", example = "소")
    private String serialtype;

    /** 부위명 */
    @Schema(description = "부위명", example = "등심")
    private String colordescr;

    /** 원산지 */
    @Schema(description = "원산지", example = "한국")
    private String placeoforigin;

    /** 평균중량 */
    @Schema(description = "평균중량", example = "2.5")
    private Double avgweight;

    /** 환산박스 */
    @Schema(description = "환산박스", example = "20.0")
    private Double calbox;

    /** 실박스예정 */
    @Schema(description = "실박스예정", example = "10.0")
    private Double realorderbox;

    /** 실박스확정 */
    @Schema(description = "실박스확정", example = "8.0")
    private Double realcfmbox;

    /** 유통기간 */
    @Schema(description = "유통기간", example = "365")
    private Integer duration;

    /** 유통기한관리방법 */
    @Schema(description = "유통기한관리방법", example = "FIFO")
    private String durationtype;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;       
}
