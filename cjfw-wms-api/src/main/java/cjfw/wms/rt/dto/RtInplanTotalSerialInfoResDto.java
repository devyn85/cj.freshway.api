package cjfw.wms.rt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.05 
 * @description : 반품진행현황 이력정보 조회 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.05 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "반품진행현황 이력정보 조회 결과")
public class RtInplanTotalSerialInfoResDto {
	/**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "ITEM001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "새우깡")
    private String skuname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[P1]플랜트1")
    private String plantDescr;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "BOX")
    private String uom;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "저장")
    private String channelname;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetypename;
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
    @Schema(description = "고객반품주문수량", example = "10")
    private String orderqty;
    /**
     * 반품입고수량
     */
    @Schema(description = "반품입고수량", example = "5")
    private String confirmqty;
    /**
     * 기준일(유통,제조)
     */
    @Schema(description = "기준일(유통,제조)", example = "20241231")
    private String lottable01;
    /**
     * 유통기간(잔여/전체)
     */
    @Schema(description = "유통기간(잔여/전체)", example = "100/365")
    private String durationTerm;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN001")
    private String serialno;
    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BARCODE123")
    private String stockid;
    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL12345")
    private String convserialno;
    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "20250101")
    private String butcherydt;
    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "서울도축장")
    private String factoryname;
    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "일반")
    private String contracttype;
    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "CUST001")
    private String contractcompany;
    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "ABC상사")
    private String contractcompanyname;
    /**
     * FROM
     */
    @Schema(description = "FROM", example = "20250101")
    private String fromvaliddt;
    /**
     * TO
     */
    @Schema(description = "TO", example = "20251231")
    private String tovaliddt;
    /**
     * 스캔예정량
     */
    @Schema(description = "스캔예정량", example = "10")
    private String serialorderwty;
    /**
     * 스캔량
     */
    @Schema(description = "스캔량", example = "5")
    private String serialinspectqty;
    /**
     * 스캔중량
     */
    @Schema(description = "스캔중량", example = "50.5")
    private String serialscanweight;
    /**
     *
     */
    @Schema(description = "", example = "CHANNEL01")
    private String channel;
    /**
     *
     */
    @Schema(description = "", example = "COLD")
    private String storagetype;
    /**
     *
     */
    @Schema(description = "", example = "LEVEL1")
    private String seriallevel;
    /**
     *
     */
    @Schema(description = "", example = "TYPEA")
    private String serialtype;
    /**
     *
     */
    @Schema(description = "", example = "RED")
    private String colordescr;
    /**
     *
     */
    @Schema(description = "", example = "한국")
    private String placeoforigin;
    /**
     *
     */
    @Schema(description = "", example = "365")
    private String duration;
    /**
     *
     */
    @Schema(description = "", example = "DAYS")
    private String durationtype;
    /**
     *
     */
    @Schema(description = "", example = "PLANT01")
    private String plant;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
