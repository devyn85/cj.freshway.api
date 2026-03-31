package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.21 
 * @description : 출고확정처리-출고대상 목록 결과 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.21 공두경 (medstorm@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "출고확정처리-출고대상 목록 결과")
public class WdShipmentTab1DetailResDto {
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
    @Schema(description = "상품명칭", example = "쌀 10kg")
    private String skuname;
    /**
     * 원산국
     */
    @Schema(description = "원산국", example = "[KR]대한민국")
    private String countryoforigin;
    /**
     * 플랜트
     */
    @Schema(description = "플랜트", example = "[P001]메인플랜트")
    private String plantDescr;
    /**
     * 관리처코드
     */
    @Schema(description = "관리처코드", example = "CUST001")
    private String toCustkey;
    /**
     * 관리처명
     */
    @Schema(description = "관리처명", example = "관리처명칭")
    private String toCustname;
    /**
     * 분할관리처코드
     */
    @Schema(description = "분할관리처코드", example = "MNGPLC001")
    private String mngplcId;
    /**
     * 분할관리처명
     */
    @Schema(description = "분할관리처명", example = "분할관리처명칭")
    private String mngplcName;
    /**
     * 박스입수
     */
    @Schema(description = "박스입수", example = "12")
    private BigDecimal qtyperbox;
    /**
     * 단위
     */
    @Schema(description = "단위", example = "EA")
    private String uom;
    /**
     * 준일(소비,제조)
     */
    @Schema(description = "준일(소비,제조)", example = "20240101")
    private String lottable01;
    /**
     * 로케이션
     */
    @Schema(description = "로케이션", example = "A-01-01")
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
     * 상차검수량
     */
    @Schema(description = "상차검수량", example = "85")
    private BigDecimal inspectqty;
    /**
     * 하차수량
     */
    @Schema(description = "하차수량", example = "80")
    private BigDecimal unloadqty;
    /**
     * 하차검수 특이사항
     */
    @Schema(description = "하차검수 특이사항", example = "파손")
    private String rmk;
    /**
     * 검수진행상태
     */
    @Schema(description = "검수진행상태", example = "완료")
    private String inspectstatus;
    /**
     * 출고수량
     */
    @Schema(description = "출고수량", example = "70")
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
     * 결품처리결과(TXT)
     */
    @Schema(description = "결품처리결과(TXT)", example = "확정취소")
    private String other02;
    /**
     * 출고중량
     */
    @Schema(description = "출고중량", example = "100.5")
    private BigDecimal confirmweight;
    /**
     * 진행상태
     */
    @Schema(description = "진행상태", example = "진행중")
    private String status;
    /**
     * 소비기간(잔여/전체)
     */
    @Schema(description = "소비기간(잔여/전체)", example = "30/90")
    private String durationTerm;
    /**
     * 피킹작업자
     */
    @Schema(description = "피킹작업자", example = "홍길동")
    private String picker;
    /**
     * 피킹작업자명
     */
    @Schema(description = "피킹작업자명", example = "홍길동")
    private String pickernm;
    /**
     * 창고
     */
    @Schema(description = "창고", example = "WH01")
    private String organize;
    /**
     * 창고명
     */
    @Schema(description = "창고명", example = "제1창고")
    private String organizename;
    /**
     * 출고일자
     */
    @Schema(description = "출고일자", example = "2024-01-01")
    private String slipdt;
    /**
     * 주문유형
     */
    @Schema(description = "주문유형", example = "일반")
    private String ordertypeDescr;
    /**
     * 저장유무
     */
    @Schema(description = "저장유무", example = "Y")
    private String channeldescr;
    /**
     * 저장조건
     */
    @Schema(description = "저장조건", example = "상온")
    private String storagetype;
    /**
     * 이력번호
     */
    @Schema(description = "이력번호", example = "S001")
    private String serialno;
    /**
     * 바코드
     */
    @Schema(description = "바코드", example = "1234567890")
    private String barcode;
    /**
     * B/L번호
     */
    @Schema(description = "B/L번호", example = "BL001")
    private String convserialno;
    /**
     * 도축일자
     */
    @Schema(description = "도축일자", example = "2024-01-01")
    private String butcherydt;
    /**
     * 도축장
     */
    @Schema(description = "도축장", example = "강원도축장")
    private String factoryname;
    /**
     * 계약유형
     */
    @Schema(description = "계약유형", example = "정상")
    private String contracttype;
    /**
     * 계약업체
     */
    @Schema(description = "계약업체", example = "업체A")
    private String contractcompany;
    /**
     * 계약업체명
     */
    @Schema(description = "계약업체명", example = "주식회사 업체A")
    private String contractcompanyname;
    /**
     * 유효일자(FROM)
     */
    @Schema(description = "유효일자(FROM)", example = "2024-01-01")
    private String fromvaliddt;
    /**
     * 유효일자(TO)
     */
    @Schema(description = "유효일자(TO)", example = "2024-12-31")
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
    @Schema(description = "스캔중량", example = "500.5")
    private BigDecimal serialscanweight;
    /**
     * 이체유형
     */
    @Schema(description = "이체유형", example = "일반")
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
    @Schema(description = "", example = "2024-01-01")
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
    @Schema(description = "", example = "ONLINE")
    private String channel;
    /**
     *
     */
    @Schema(description = "", example = "NORMAL")
    private String sliptype;
    /**
     *
     */
    @Schema(description = "", example = "SALES")
    private String ordertype;
    /**
     *
     */
    @Schema(description = "", example = "123-45-67890")
    private String toVatno;
    /**
     *
     */
    @Schema(description = "", example = "김사업")
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
    private String confirmweight2; // 변수명이 중복되어 뒤에 '2'를 붙였습니다.
    /**
     *
     */
    @Schema(description = "", example = "LOT001")
    private String lot;
    /**
     *
     */
    @Schema(description = "", example = "STOCK001")
    private String stockid;
    /**
     *
     */
    @Schema(description = "", example = "A")
    private String stockgrade;
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
    @Schema(description = "", example = "20240101")
    private String other03;
    /**
     *
     */
    @Schema(description = "", example = "ONLINE")
    private String other04;
    /**
     *
     */
    @Schema(description = "", example = "MONTH")
    private String durationtype;
    /**
     *
     */
    @Schema(description = "", example = "3")
    private String duration;
    /**
     *
     */
    @Schema(description = "", example = "2024-01-02")
    private String deliverydt;
    /**
     *
     */
    @Schema(description = "", example = "Y")
    private String ifSendType;
    /**
     *
     */
    @Schema(description = "", example = "PLANT01")
    private String plant;
    /**
     *
     */
    @Schema(description = "", example = "KOR")
    private String placeoforigin;
    /**
     *
     */
    @Schema(description = "", example = "Y")
    private String other05;
    /**
     *
     */
    @Schema(description = "", example = "5")
    private String serialcancelqty;
    /**
     *
     */
    @Schema(description = "", example = "100")
    private String sumprocessqty;
    /**
     *
     */
    @Schema(description = "", example = "COMPLETED")
    private String statusCode;
    /**
     *
     */
    @Schema(description = "", example = "SHORTAGEKEY001")
    private String shortageserialkey;
    /**
     *확정가능여부
     */
    @Schema(description = "확정가능여부", example = "Y")
    private String confirmyn;
    /**
     *
     */
    @Schema(description = "", example = "Y")
    private String workprocesscode;
    /**
     * 작업유형
     * */
    @Schema(description = "작업유형", example = "AL")
    private String tasktype;
}
