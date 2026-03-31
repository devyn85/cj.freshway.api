package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.11 
 * @description : 이력상품출고현황 엑셀 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.11 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품출고현황 엑셀 결과")
public class WdInplanSNResExcelDto {	

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "DC001")
    private String dccode;

    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH001")
    private String organize;

    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2025-06-11")
    private String slipdt;

    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "판매")
    private String ordertype;

    /**
     * 주문사유
     */
    @Schema(description = "주문사유", example = "정상주문")
    private String potype;

    /**
     * 주문번호
     */
    @Schema(description = "주문번호", example = "ORD001")
    private String docno;

    /**
     * 영업조직
     */
    @Schema(description = "영업조직", example = "영업1팀")
    private String saleorganize;

    /**
     * 시업장
     */
    @Schema(description = "시업장", example = "본사")
    private String saledepartment;

    /**
     * 영업그룹
     */
    @Schema(description = "영업그룹", example = "그룹A")
    private String salegroup;

    /**
     * 판매처코드
     */
    @Schema(description = "판매처코드", example = "CUST001")
    private String toVatno;

    /**
     * 판매처명
     */
    @Schema(description = "판매처명", example = "ABC상사")
    private String toVatowner;

    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "MGR001")
    private String toCustkey;

    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "ABC관리처")
    private String toCustname;

    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "완료")
    private String status;

    /**
     * POP번호
     */
    @Schema(description = "POP번호", example = "POP001")
    private String deliverygroup;

    /**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "12가3456")
    private String carno;

    /**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "ITEMLINE001")
    private String docline;

    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;

    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "프리미엄 커피")
    private String skuname;

    /**
     * 원산국
     */
    @Schema(description = "원산국", example = "[US]미국")
    private String countryoforigin;

    /**
     * 배송수단
     */
    @Schema(description = "배송수단", example = "택배")
    private String deliverytype;

    /**
     * 이력관리대상
     */
    @Schema(description = "이력관리대상", example = "Y")
    private String serialyn;

    /**
     * 비정량여부
     */
    @Schema(description = "비정량여부", example = "Y")
    private String line01;

    /**
     * 사전주문조정의뢰여부
     */
    @Schema(description = "사전주문조정의뢰여부", example = "Y")
    private String beforeshortageplanyn;

    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[P001]서울공장")
    private String plantDescr;

    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "저장")
    private String channel;

    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;

    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;

    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "90")
    private BigDecimal processqty;

    /**
     * 피킹량
     */
    @Schema(description = "피킹량", example = "85")
    private BigDecimal workqty;

    /**
     * 출고검수량
     */
    @Schema(description = "출고검수량", example = "80")
    private BigDecimal inspectqty;

    /**
     * 출고수량
     */
    @Schema(description = "출고수량", example = "80")
    private BigDecimal confirmqty;

    /**
     * 판매단위
     */
    @Schema(description = "판매단위", example = "EA")
    private String uom;

    /**
     * 출고중량
     */
    @Schema(description = "출고중량", example = "150.5")
    private BigDecimal confirmweight;

    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "2025-05-01")
    private String lottable01;

    /**
     * 소비기간(잔여/전체)
     */
    @Schema(description = "소비기간(잔여/전체)", example = "30/90")
    private String durationTerm;

    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN0012345")
    private String serialno;

    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BARC0001")
    private String barcode;

    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL0001")
    private String convserialno;

    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "2025-05-10")
    private String butcherydt;

    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "ABC도축장")
    private String factoryname;

    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "일반")
    private String contracttype;

    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CONTR001")
    private String contractcompany;

    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "XYZ파트너스")
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
    @Schema(description = "스캔예정량", example = "100")
    private BigDecimal serialorderqty;

    /**
     * 스캔량
     */
    @Schema(description = "스캔량", example = "95")
    private BigDecimal serialinspectqty;

    /**
     * 스캔중량
     */
    @Schema(description = "스캔중량", example = "5")
    private BigDecimal serialscanweight;
    
    @Schema(description = "delYn", example = "Y")
    private String delYn;
    
    
}
