package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.28 
 * @description : 이력상품반품현황 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.28 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "이력상품반품현황 목록 결과")
public class RtInplanSNDetailResDto {
	/**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "프리미엄 한우 등심")
    private String skuname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[PLANT01]서울공장")
    private String plantDescr;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "KG")
    private String uom;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "냉장")
    private String storagetype;
    /**
     * 평균중량
     */
    @Schema(description = "평균중량", example = "0")
    private String avgweight;
    /**
     * 환산주문박스
     */
    @Schema(description = "환산주문박스", example = "0")
    private String calorderbox;
    /**
     * 환산확정박스
     */
    @Schema(description = "환산확정박스", example = "0")
    private String calconfirmbox;
    /**
     * 실박스
     */
    @Schema(description = "실박스", example = "0")
    private String realbox;
    /**
     * 고객반품주문수량
     */
    @Schema(description = "고객반품주문수량", example = "10.0")
    private Double orderqty;
    /**
     * 반품입고수량
     */
    @Schema(description = "반품입고수량", example = "8.5")
    private Double confirmqty;
    /**
     * 기준일(유통,제조)
     */
    @Schema(description = "기준일(유통,제조)", example = "20250528")
    private String lottable01;
    /**
     * 유통기간(잔여/전체)
     */
    @Schema(description = "유통기간(잔여/전체)", example = "7/30")
    private String durationTerm;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN1234567890")
    private String serialno;
    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BC9876543210")
    private String barcodeSn;
    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL-001-2025")
    private String convserialno;
    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "20250520")
    private String butcherydt;
    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "ABC 도축장")
    private String factoryname;
    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "위탁")
    private String contracttype;
    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CUST001")
    private String wdCustkey;
    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "으뜸유통")
    private String wdCustName;
    /**
     * FROM
     */
    @Schema(description = "FROM", example = "20250520")
    private String fromvaliddt;
    /**
     * TO
     */
    @Schema(description = "TO", example = "20250620")
    private String tovaliddt;
    /**
     * 스캔예정량
     */
    @Schema(description = "스캔예정량", example = "10.0")
    private Double serialorderqty;
    /**
     * 스캔량
     */
    @Schema(description = "스캔량", example = "8.5")
    private Double serialinspectqty;
    /**
     * 스캔중량
     */
    @Schema(description = "스캔중량", example = "8.5")
    private Double serialscanweight;
    /**
     * SERIALLEVEL
     */
    @Schema(description = "SERIALLEVEL", example = "L1")
    private String seriallevel;
    /**
     * SERIALTYPE
     */
    @Schema(description = "SERIALTYPE", example = "TYPE_A")
    private String serialtype;
    /**
     * COLORDESCR
     */
    @Schema(description = "COLORDESCR", example = "신선육")
    private String colordescr;
    /**
     * PLACEOFORIGIN
     */
    @Schema(description = "PLACEOFORIGIN", example = "국내산 한우")
    private String placeoforigin;
    /**
     * DURATION
     */
    @Schema(description = "DURATION", example = "30")
    private String duration;
    /**
     * DURATIONTYPE
     */
    @Schema(description = "DURATIONTYPE", example = "일")
    private String durationtype;
    /**
     * PLANT
     */
    @Schema(description = "PLANT", example = "PLANT01")
    private String plant;
    /**
     * 제조
     */
    @Schema(description = "제조", example = "PLANT01")
    private String lotManufacture;
    /**
     * 유통
     */
    @Schema(description = "유통", example = "PLANT01")
    private String lotExpire;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}