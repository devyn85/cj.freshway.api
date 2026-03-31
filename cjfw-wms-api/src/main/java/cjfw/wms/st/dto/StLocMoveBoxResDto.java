package cjfw.wms.st.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.07.31
 * @description : 재고일괄이동(수원3층) Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.31 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "재고일괄이동(수원3층) Master Response DTO")
@Data
public class StLocMoveBoxResDto {
	/** 메인그리드 리스트 */
	List<StLocMoveBoxResDto> resultList;	
	
    /** 고정센터코드 */
    @Schema(description = "고정센터코드")
    private String fixdccode;	
	
    /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkyn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dccode;

    /** 조직 */
    @Schema(description = "조직")
    private String organize;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stocktype;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockgrade;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;        

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuname;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyallocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtypicked;

    /** 이동가능수량 */
    @Schema(description = "이동가능수량")
    private BigDecimal posbqty;

    /** 박스당수량 */
    @Schema(description = "박스당수량")
    private BigDecimal qtyperbox;

    /** 이동가능박스수량 */
    @Schema(description = "이동가능박스수량")
    private BigDecimal posbqtyBox;

    /** 이동가능낱개수량 */
    @Schema(description = "이동가능낱개수량")
    private BigDecimal posbqtyEa;

    /** 도착로케이션 */
    @Schema(description = "도착로케이션")
    private String toLoc;

    /** 도착수량 */
    @Schema(description = "도착수량")
    private BigDecimal toOrderqty;

    /** 도착박스수량 */
    @Schema(description = "도착박스수량")
    private BigDecimal toOrderqtyBox;

    /** 도착낱개수량 */
    @Schema(description = "도착낱개수량")
    private BigDecimal toOrderqtyEa;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String neardurationyn;

    /** 유통기한 */
    @Schema(description = "유통기한")
    private String lottable01;

    /** 유통기한/기간 */
    @Schema(description = "유통기한/기간")
    private String durationTerm;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialno;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storagetype;

    /** 유통기한기간 */
    @Schema(description = "유통기한기간")
    private String duration;

    /** 유통기한구분 */
    @Schema(description = "유통기한구분")
    private String durationtype;

    /** 출발센터코드 */
    @Schema(description = "출발센터코드")
    private String fromDccode;

    /** 출발화주코드 */
    @Schema(description = "출발화주코드")
    private String fromStorerkey;

    /** 출발조직 */
    @Schema(description = "출발조직")
    private String fromOrganize;

    /** 출발구역 */
    @Schema(description = "출발구역")
    private String fromArea;

    /** 출발상품코드 */
    @Schema(description = "출발상품코드")
    private String fromSku;

    /** 출발로케이션 */
    @Schema(description = "출발로케이션")
    private String fromLoc;

    /** 출발로트 */
    @Schema(description = "출발로트")
    private String fromLot;

    /** 출발재고ID */
    @Schema(description = "출발재고ID")
    private String fromStockid;

    /** 출발재고등급 */
    @Schema(description = "출발재고등급")
    private String fromStockgrade;

    /** 출발재고유형 */
    @Schema(description = "출발재고유형")
    private String fromStocktype;

    /** 출발수량 */
    @Schema(description = "출발수량")
    private BigDecimal fromOrderqty;

    /** 출발단위 */
    @Schema(description = "출발단위")
    private String fromUom;

    /** 도착센터코드 */
    @Schema(description = "도착센터코드")
    private String toDccode;

    /** 도착화주코드 */
    @Schema(description = "도착화주코드")
    private String toStorerkey;

    /** 도착조직 */
    @Schema(description = "도착조직")
    private String toOrganize;

    /** 도착구역 */
    @Schema(description = "도착구역")
    private String toArea;

    /** 도착상품코드 */
    @Schema(description = "도착상품코드")
    private String toSku;

    /** 도착로트 */
    @Schema(description = "도착로트")
    private String toLot;

    /** 도착재고ID */
    @Schema(description = "도착재고ID")
    private String toStockid;

    /** 도착재고등급 */
    @Schema(description = "도착재고등급")
    private String toStockgrade;

    /** 도착재고유형 */
    @Schema(description = "도착재고유형")
    private String toStocktype;

    /** 도착단위 */
    @Schema(description = "도착단위")
    private String toUom;

    /** 기타수량1(할당) */
    @Schema(description = "기타수량1(할당)")
    private BigDecimal etcqty1;

    /** 기타수량2(피킹) */
    @Schema(description = "기타수량2(피킹)")
    private BigDecimal etcqty2;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryname;

    /** 변환시리얼번호 */
    @Schema(description = "변환시리얼번호")
    private String convserialno;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcherydt;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractcompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명")
    private String contractcompanyname;
    
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;      

    /** 출발유효일자 */
    @Schema(description = "출발유효일자")
    private String fromvaliddt;

    /** 도착유효일자 */
    @Schema(description = "도착유효일자")
    private String tovaliddt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contracttype;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 고정로케이션 */
    @Schema(description = "고정로케이션")
    private String fixloc;
    
    /** 제조일자 */
    @Schema(description = "제조일자")
    private String manufacturedt;    
    
    /** 소비일자 */
    @Schema(description = "소비일자")
    private String expiredt;

		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /** 입고예정수량 */
    @Schema(description = "입고예정수량")
    private BigDecimal qtyexpected;
    
    /** 사유코드 */
    @Schema(description = "사유코드")
    private String reasoncode;

    /** 사유메시지 */
    @Schema(description = "사유메시지")
    private String reasonmsg;
  

    /** 처리상태 */
    @Schema(description = "처리상태")
    private String processflag;

    /** 처리메시지 */
    @Schema(description = "처리메시지")
    private String processmsg;

}
