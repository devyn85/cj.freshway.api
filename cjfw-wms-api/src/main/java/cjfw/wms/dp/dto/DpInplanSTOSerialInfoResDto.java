package cjfw.wms.dp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.06.18 
 * @description : 광역입고현황 이력현황 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "광역입고현황 이력현황 결과")
public class DpInplanSTOSerialInfoResDto {
	/**
     * 품목번호
     */
    @Schema(description = "품목번호", example = "1")
    private String docline;
    /**
     * 상품코드
     */
    @Schema(description = "상품코드", example = "SKU001")
    private String sku;
    /**
     * 상품명칭
     */
    @Schema(description = "상품명칭", example = "오렌지주스")
    private String skuname;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[P001]서울공장")
    private String plantDescr;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channelname;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetypeName;
    /**
     * 박스입수
     */
    @Schema(description = "박스입수", example = "10")
    private String qtyperbox;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
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
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private String orderqty;
    /**
     * 입고검수량
     */
    @Schema(description = "입고검수량", example = "95")
    private String inspectqty;
    /**
     * 입고확정량
     */
    @Schema(description = "입고확정량", example = "90")
    private String confirmqty;
    /**
     * 입고중량
     */
    @Schema(description = "입고중량", example = "150.5")
    private String weight;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "완료")
    private String status;
    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "20240101")
    private String lottable01;
    /**
     * 소비기간(잔여/전체)
     */
    @Schema(description = "소비기간(잔여/전체)", example = "180/365")
    private String durationTerm;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "SN12345")
    private String serialno;
    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "BC7890")
    private String stockid;
    /**
     * B/L 번호
     */
    @Schema(description = "B/L 번호", example = "BL-001")
    private String convserialno;
    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "20231225")
    private String butcherydt;
    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "하림")
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
    @Schema(description = "계약업체명", example = "대한식품")
    private String contractcompanyname;
    /**
     * FROM
     */
    @Schema(description = "FROM", example = "20240101")
    private String fromvaliddt;
    /**
     * TO
     */
    @Schema(description = "TO", example = "20241231")
    private String tovaliddt;
    /**
     * 스캔예정량
     */
    @Schema(description = "스캔예정량", example = "100")
    private String serialorderqty;
    /**
     * 스캔량
     */
    @Schema(description = "스캔량", example = "95")
    private String serialinspectqty;
    /**
     * 스캔중량
     */
    @Schema(description = "스캔중량", example = "150.5")
    private String serialscanweight;
    /**
     * QTY
     */
    @Schema(description = "QTY", example = "5")
    private String qty;
    /**
     * SERIALLEVEL
     */
    @Schema(description = "SERIALLEVEL", example = "1")
    private String seriallevel;
    /**
     * SERIALTYPE
     */
    @Schema(description = "SERIALTYPE", example = "BOX")
    private String serialtype;
    /**
     * FACTORYKEY
     */
    @Schema(description = "FACTORYKEY", example = "F001")
    private String factorykey;
    /**
     * COLORDESCR
     */
    @Schema(description = "COLORDESCR", example = "RED")
    private String colordescr;
    /**
     * PLACEOFORIGIN
     */
    @Schema(description = "PLACEOFORIGIN", example = "KOREA")
    private String placeoforigin;
    /**
     * CHANNEL
     */
    @Schema(description = "CHANNEL", example = "Offline")
    private String channel;
    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE", example = "Frozen")
    private String storagetype;
    /**
     * DURATION
     */
    @Schema(description = "DURATION", example = "365")
    private String duration;
    /**
     * DURATIONTYPE
     */
    @Schema(description = "DURATIONTYPE", example = "DAY")
    private String durationtype;
    /**
     * PLANT
     */
    @Schema(description = "PLANT", example = "P001")
    private String plant;

    /** * 제조 */
    @Schema(description = "* 제조")
    private String lotManufacture;

    /** * 유통 */
    @Schema(description = "* 유통")
    private String lotExpire;
    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
