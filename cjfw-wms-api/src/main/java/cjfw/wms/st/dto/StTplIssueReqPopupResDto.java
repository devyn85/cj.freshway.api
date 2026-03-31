package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.10.31 
 * @description : 위탁물류입고요청 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 ParkJinWoo 생성
 */
@Data
@Schema(description = "위탁물류입고요청 결과 DTO")
public class StTplIssueReqPopupResDto extends CommonProcedureDto {

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String dcCode;

    /** 창고 */
    @Schema(description = "창고")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;

    /** 재고유형 */
    @Schema(description = "재고유형")
    private String stockType;

    /** 재고등급 */
    @Schema(description = "재고등급")
    private String stockGrade;

    /** 존 */
    @Schema(description = "존")
    private String zone;

    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal openQty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyAllocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtyPicked;

    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String nearDurationYn;

    /** LOT번호 */
    @Schema(description = "LOT번호")
    private String lottable01;

    /** 유통기한_표기 (잔여/총일수 형식) */
    @Schema(description = "유통기한_표기")
    private String durationTerm;

    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storageType;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /** 유통기간(일) */
    @Schema(description = "유통기간(일)")
    private Integer duration;

    /** 유통기간유형 */
    @Schema(description = "유통기간유형")
    private String durationType;

    /** 상품그룹 */
    @Schema(description = "상품그룹")
    private String skuGroup;

    /** 재고상태 */
    @Schema(description = "재고상태")
    private String stockStatus;

    /** SAP가격 */
    @Schema(description = "SAP가격")
    private String sapPrice;

    /** 수량(BOX)_정수 */
    @Schema(description = "수량(BOX)_정수")
    private BigDecimal qty1;

    /** BOX수량_나머지 */
    @Schema(description = "BOX수량_나머지")
    private BigDecimal qty2;

    /** 단위1 */
    @Schema(description = "단위1")
    private String uom1;

    /** 단위2 */
    @Schema(description = "단위2")
    private String uom2;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private BigDecimal avgWeight;

    /** 환산박스 */
    @Schema(description = "환산박스")
    private BigDecimal calBox;

    /** 실제박스수 */
    @Schema(description = "실제박스수")
    private BigDecimal realBox;

    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryName;

    /** 변환시리얼 */
    @Schema(description = "변환시리얼")
    private String convSerialNo;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcheryDt;

    /** 계약사코드 */
    @Schema(description = "계약사코드")
    private String contractCompany;

    /** 계약사명 */
    @Schema(description = "계약사명")
    private String contractCompanyName;

    /** 계약사담당자명 */
    @Schema(description = "계약사담당자명")
    private String contractCompanyEmpName1;

    /** 유효시작일 */
    @Schema(description = "유효시작일")
    private String fromValidDt;

    /** 유효종료일 */
    @Schema(description = "유효종료일")
    private String toValidDt;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;
    
    /** 바코드 */
    @Schema(description = "단위")
    private String baseUom;
    
//    /** 바코드 */
//    @Schema(description = "상품명")
//    private String skuName;

    /** 발주KEY */
    @Schema(description = "발주KEY")
    private String poKey;

    /** 발주LINE */
    @Schema(description = "발주LINE")
    private String poLine;

    /** 평균중량_2170 */
    @Schema(description = "평균중량_2170")
    private BigDecimal avgWeight2170;

    /** 환산박스_2170 */
    @Schema(description = "환산박스_2170")
    private BigDecimal calBox2170;

    /** 실박스예정 */
    @Schema(description = "실박스예정")
    private BigDecimal realOrderBox;

    /** 실박스확정 */
    @Schema(description = "실박스확정")
    private BigDecimal realCfmBox;

    /** BOX플래그 */
    @Schema(description = "BOX플래그")
    private String boxFlag;

    /** 실적여부 */
    @Schema(description = "실적여부")
    private String realYn;

    /** 박스당수량 */
    @Schema(description = "박스당수량")
    private BigDecimal qtyPerBox;

    /** 소비기한잔여율(%) */
    @Schema(description = "소비기한잔여율(%)")
    private String durationRate;
    /** 소비기한잔여율(%) */
    @Schema(description = "소비기한잔여율(%)")
    private String durationTo;
    /** 소비기한잔여율(%) */
    @Schema(description = "소비기한잔여율(%)")
    private String durationFrom;
    /** 저장위치(SAP Storage Location) */
    @Schema(description = "저장위치")
    private String storageLoc;

    /** 시리얼타입 */
    @Schema(description = "시리얼타입")
    private String serialType;

    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private Long serialKey;

    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String custKey;

    /** 입고예정일 */
    @Schema(description = "입고예정일")
    private String deliveryDate;
    
    private String attachment;
		/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
