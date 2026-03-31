package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.21 
 * @description : 출고확정처리-상차검수취소 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고확정처리-상차검수취소 목록 결과")
public class WdShipmentTab3DetailResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	    /**
	     * 인보이스번호
	     */
	    @Schema(description = "인보이스번호", example = "INV001")
	    private String invoiceno;
	    /**
	     * 상품코드
	     */
	    @Schema(description = "상품코드", example = "SKU001")
	    private String sku;
	    /**
	     * 상품명칭
	     */
	    @Schema(description = "상품명칭", example = "상품명칭 예시")
	    private String skuname;
	    /**
	     * 원산국
	     */
	    @Schema(description = "원산국", example = "[US]미국")
	    private String countryoforigin;
	    /**
	     * 플랜트
	     */
	    @Schema(description = "플랜트", example = "[P001]플랜트명")
	    private String plantDescr;
	    /**
	     * 저장유무
	     */
	    @Schema(description = "저장유무", example = "Y")
	    private String channeldescr;
	    /**
	     * 관리처코드
	     */
	    @Schema(description = "관리처코드", example = "CUST001")
	    private String toCustKey;
	    /**
	     * 관리처명
	     */
	    @Schema(description = "관리처명", example = "관리처명 예시")
	    private String toCustName;
	    /**
	     * 분할관리처코드
	     */
	    @Schema(description = "분할관리처코드", example = "MNGPLC01")
	    private String mngplcId;
	    /**
	     * 분할관리처명
	     */
	    @Schema(description = "분할관리처명", example = "분할관리처명 예시")
	    private String mngplcName;
	    /**
	     * 단위
	     */
	    @Schema(description = "단위", example = "EA")
	    private String uom;
	    /**
	     * 출고검수량
	     */
	    @Schema(description = "출고검수량", example = "100")
	    private BigDecimal inspectqty;
	    /**
	     * 문서번호
	     */
	    @Schema(description = "문서번호", example = "DOC001")
	    private String docno;
	    /**
	     * 품목번호
	     */
	    @Schema(description = "품목번호", example = "1")
	    private String docline;
	    /**
	     * 생성인
	     */
	    @Schema(description = "생성인", example = "admin")
	    private String addwho;
	    /**
	     * 등록일자
	     */
	    @Schema(description = "등록일자", example = "20250721100000")
	    private String adddate;
	    /**
	     * 이력번호
	     */
	    @Schema(description = "이력번호", example = "SER001")
	    private String serialno;
	    /**
	     * 바코드
	     */
	    @Schema(description = "바코드", example = "BAR001")
	    private String barcode;
	    /**
	     * B/L번호
	     */
	    @Schema(description = "B/L번호", example = "BL001")
	    private String convserialno;
	    /**
	     * 도축일자
	     */
	    @Schema(description = "도축일자", example = "2025-07-20")
	    private String butcherydt;
	    /**
	     * 도축장
	     */
	    @Schema(description = "도축장", example = "도축장명")
	    private String factoryname;
	    /**
	     * 계약유형
	     */
	    @Schema(description = "계약유형", example = "유형1")
	    private String contracttype;
	    /**
	     * 계약업체
	     */
	    @Schema(description = "계약업체", example = "COMP001")
	    private String contractcompany;
	    /**
	     * 계약업체명
	     */
	    @Schema(description = "계약업체명", example = "계약업체명칭")
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
	     * 스캔예정량
	     */
	    @Schema(description = "스캔예정량", example = "50")
	    private BigDecimal serialorderqty;
	    /**
	     * 스캔량
	     */
	    @Schema(description = "스캔량", example = "45")
	    private BigDecimal serialinspectqty;
	    /**
	     * 스캔중량
	     */
	    @Schema(description = "스캔중량", example = "50.5")
	    private BigDecimal serialscanweight;
	    /**
	     *
	     */
	    @Schema(description = "", example = "SERKEY001")
	    private String serialkey;
	    /**
	     *
	     */
	    @Schema(description = "", example = "CHANNEL01")
	    private String channel;
	    /**
	     *
	     */
	    @Schema(description = "", example = "DC01")
	    private String dccode;
	    /**
	     *
	     */
	    @Schema(description = "", example = "STORER01")
	    private String storerkey;
	    /**
	     *
	     */
	    @Schema(description = "", example = "DOCTYPE01")
	    private String doctype;
	    /**
	     *
	     */
	    @Schema(description = "", example = "2025-07-22")
	    private String deliverydt;
	    /**
	     *
	     */
	    @Schema(description = "", example = "HIGH")
	    private String priority;
	    /**
	     *
	     */
	    @Schema(description = "", example = "2025-07-21")
	    private String slipdt;
	    /**
	     *
	     */
	    @Schema(description = "", example = "GROUP01")
	    private String deliverygroup;
	    /**
	     *
	     */
	    @Schema(description = "", example = "1234")
	    private String carno;
	    /**
	     *
	     */
	    @Schema(description = "", example = "0")
	    private String checkyn;
	    /**
	     *
	     */
	    @Schema(description = "", example = "PLANT01")
	    private String plant;
	    /**
	     *
	     */
	    @Schema(description = "", example = "ORIGIN01")
	    private String placeoforigin;
}
