package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.21 
 * @description : 출고확정처리-결품대상 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고확정처리-결품대상 목록 결과")
public class WdShipmentTab2DetailResDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
	/**
     * 타스크유형
     */
    @Schema(description = "타스크유형", example = "출고확정대상")
    private String taskdescr;
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
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "관리처명 예시")
    private String toCustname;
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
     * 박스입수
     */
    @Schema(description = "박스입수", example = "10")
    private BigDecimal qtyperbox;
    /**
     * 판매단위
     */
    @Schema(description = "판매단위", example = "EA")
    private String uom;
    /**
     * 기준일(소비,제조)
     */
    @Schema(description = "기준일(소비,제조)", example = "")
    private String lottable01;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "A01-01")
    private String loc;
    /**
     * 분배량
     */
    @Schema(description = "분배량", example = "100")
    private BigDecimal processqty;
    /**
     * 피킹량
     */
    @Schema(description = "피킹량", example = "90")
    private BigDecimal workqty;
    /**
     * 출고검수량
     */
    @Schema(description = "출고검수량", example = "85")
    private BigDecimal inspectqty;
    /**
     * 검수진행상태
     */
    @Schema(description = "검수진행상태", example = "진행중")
    private String inspectstatus;
    /**
     * 출고수량
     */
    @Schema(description = "출고수량", example = "80")
    private BigDecimal confirmedqty;
    /**
     * 출고작업량
     */
    @Schema(description = "출고작업량", example = "0")
    private BigDecimal confirmqty;
    /**
     * 전표번호
     */
    @Schema(description = "전표번호", example = "SLIP001")
    private String slipno;
    /**
     * 전표라인번호
     */
    @Schema(description = "전표라인번호", example = "1")
    private String slipline;
    /**
     * 주문수량
     */
    @Schema(description = "주문수량", example = "100")
    private BigDecimal orderqty;
    /**
     * 결품수량
     */
    @Schema(description = "결품수량", example = "5")
    private BigDecimal cancelqty;
    /**
     * 결품작업량
     */
    @Schema(description = "결품작업량", example = "0")
    private BigDecimal etcqty1;
    /**
     * 결품사유
     */
    @Schema(description = "결품사유", example = "재고부족")
    private String other01;
    /**
     * 결품처리결과(TEXT)
     */
    @Schema(description = "결품처리결과(TEXT)", example = "처리완료")
    private String other02;
    /**
     * 사전결품대응처리
     */
    @Schema(description = "사전결품대응처리", example = "Y")
    private String other04;
    /**
     * 출고중량
     */
    @Schema(description = "출고중량", example = "")
    private BigDecimal confirmweight;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "진행중")
    private String status;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "30")
    private String statusCode;
    /**
     * 피킹작업자
     */
    @Schema(description = "피킹작업자", example = "홍길동")
    private String picker;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01")
    private String organize;
    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "메인창고")
    private String organizename;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2024-07-21")
    private String slipdt;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "일반주문")
    private String ordertype;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channel;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetype;
    /**
     * 이체유형
     */
    @Schema(description = "이체유형", example = "판매이체")
    private String stotype;
    /**
     *
     */
    @Schema(description = "", example = "SHIPMENT")
    private String tasksystem;
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
    @Schema(description = "", example = "AREA01")
    private String area;
    /**
     *
     */
    @Schema(description = "", example = "DO")
    private String doctype;
    /**
     *
     */
    @Schema(description = "", example = "2024-07-21")
    private String docdt;
    /**
     *
     */
    @Schema(description = "", example = "DOC001")
    private String docno;
    /**
     *
     */
    @Schema(description = "", example = "1")
    private String docline;
    /**
     *
     */
    @Schema(description = "", example = "SLIPTYPE01")
    private String sliptype;
    /**
     *
     */
    @Schema(description = "", example = "123-45-67890")
    private String toVatno;
    /**
     *
     */
    @Schema(description = "", example = "김세금")
    private String toVatowner;
    /**
     *
     */
    @Schema(description = "", example = "BOX")
    private String storeruom;
    /**
     *
     */
    @Schema(description = "", example = "1")
    private String bunja;
    /**
     *
     */
    @Schema(description = "", example = "1")
    private String bunmo;
    /**
     *
     */
    @Schema(description = "", example = "100.0")
    private String confirmweight2; // 변수명이 중복되어 '2'를 추가했습니다.
    /**
     *
     */
    @Schema(description = "", example = "LOT001")
    private String lot;
    /**
     *
     */
    @Schema(description = "", example = "SERIALKEY001")
    private String srcserialkey1;
    /**
     *
     */
    @Schema(description = "", example = "TASKKEY001")
    private String taskkey;
    /**
     *
     */
    @Schema(description = "", example = "PLANT01")
    private String plant;
}
