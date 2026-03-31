package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.29
 * @description : 외부비축센터간이동 저장 결과 resDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성 
 */
@Data
@Schema(description = "외부비축센터간이동 저장 결과 resDto")
public class StOutMoveResultListResDto {
	  /** 체크여부 */
    @Schema(description = "체크여부")
    private String checkYn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;

    /** 재고타입명 */
    @Schema(description = "재고타입명")
    private String stockType;

    /** 재고속성명 */
    @Schema(description = "재고속성명")
    private String stockGrade;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 재고수량 */
    @Schema(description = "재고수량")
    private Double qty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private Double qtyAllocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private Double qtyPicked;

    /** 가용재고 */
    @Schema(description = "가용재고")
    private Double posbQty;

    /** TO 창고 */
    @Schema(description = "TO 창고")
    private String toOrganize;

    /** TO 수량 */
    @Schema(description = "TO 수량")
    private Double toOrderQty;

    /** 임박여부(Y/N) */
    @Schema(description = "임박여부(Y/N)")
    private String nearDurationYn;

    /** 기준일(유통/제조) */
    @Schema(description = "기준일(유통/제조)")
    private String lottable01;

    /** 소비기한(잔여/전체) */
    @Schema(description = "소비기한(잔여/전체)")
    private String durationTerm;

    /** 이력번호 */
    @Schema(description = "이력번호")
    private String serialNo;

    /** 보관유형명 */
    @Schema(description = "보관유형명")
    private String storageType;

    /** 기준기간 */
    @Schema(description = "기준기간")
    private Integer duration;

    /** 기간타입 */
    @Schema(description = "기간타입")
    private String durationType;

    /** 처리플래그 */
    @Schema(description = "처리플래그")
    private String processFlag;

    /** 처리메시지 */
    @Schema(description = "처리메시지")
    private String processMsg;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryName;

    /** B/L 번호 */
    @Schema(description = "B/L 번호")
    private String convSerialNo;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcheryDt;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractCompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명")
    private String contractCompanyName;

    /** 유효기간 FROM */
    @Schema(description = "유효기간 FROM")
    private String fromValidDt;

    /** 유효기간 TO */
    @Schema(description = "유효기간 TO")
    private String toValidDt;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;
    
    private String tranDeliveryPrice;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
