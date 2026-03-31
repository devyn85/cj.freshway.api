package cjfw.wms.st.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : ParkJinWoo 
 * @date : 2025.07.29
 * @description : 외부비축센터간이동 resDto 기능을 구현한 Controller Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.29 ParkJinWoo 생성 
 */
@Data
@Schema(description = "외부비축센터간이동resDto")
public class StOutMoveMasterListResDto extends CommonProcedureDto{
	/* ────────── 기본 ────────── */
    /** 체크여부(고정 0) */
    @Schema(description = "체크여부(고정 0)")
    private String checkYn;

    /** 센터코드 */
    @Schema(description = "센터코드")
    private String dcCode;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerkey;

    /** 창고코드 */
    @Schema(description = "창고코드")
    private String organize;

    /** 창고명 */
    @Schema(description = "창고명")
    private String organizeName;

    /** 존 */
    @Schema(description = "존")
    private String area;

    /** LOT */
    @Schema(description = "LOT")
    private String lot;

    /** 재고ID/바코드키 */
    @Schema(description = "재고ID/바코드키")
    private String stockId;

    /* ────────── 재고유형/등급 ────────── */
    /** 재고등급 코드 */
    @Schema(description = "재고등급 코드")
    private String stockGrade;

    /** 재고유형 코드 */
    @Schema(description = "재고유형 코드")
    private String stockType;

    /** 재고유형명 */
    @Schema(description = "재고유형명")
    private String stockTypeName;

    /** 재고등급명 */
    @Schema(description = "재고등급명")
    private String stockGradeName;

    /* ────────── 위치/상품 ────────── */
    /** 로케이션 */
    @Schema(description = "로케이션")
    private String loc;

    /** SKU */
    @Schema(description = "SKU")
    private String sku;

    /** SKU명 */
    @Schema(description = "SKU명")
    private String skuName;

    /* ────────── 수량/단위 ────────── */
    /** 단위 */
    @Schema(description = "단위")
    private String uom;

    /** 수량 */
    @Schema(description = "수량")
    private BigDecimal qty;

    /** 할당수량 */
    @Schema(description = "할당수량")
    private BigDecimal qtyAllocated;

    /** 피킹수량 */
    @Schema(description = "피킹수량")
    private BigDecimal qtyPicked;

    /** 가용수량 */
    @Schema(description = "가용수량")
    private BigDecimal posbQty;

    /* ────────── 유통/LOT ────────── */
    /** 유통기한임박여부 */
    @Schema(description = "유통기한임박여부")
    private String nearDurationYn;

    /** LOT 유형 */
    @Schema(description = "LOT 유형")
    private String lotTable01;

    /** 유통기한(잔/총) */
    @Schema(description = "유통기한(잔/총)")
    private String durationTerm;

    /* ────────── Serial & 보관 ────────── */
    /** 시리얼번호 */
    @Schema(description = "시리얼번호")
    private String serialNo;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storageType;

    /** 유통기간 */
    @Schema(description = "유통기간")
    private Integer duration;

    /** 유통기간 TYPE */
    @Schema(description = "유통기간 TYPE")
    private String durationType;

    /* ────────── From → To 매핑 ────────── */
    /** From 센터 */
    @Schema(description = "From 센터")
    private String fromDccode;

    /** From 화주 */
    @Schema(description = "From 화주")
    private String fromStorerkey;

    /** From 창고 */
    @Schema(description = "From 창고")
    private String fromOrganize;

    /** From 존 */
    @Schema(description = "From 존")
    private String fromArea;

    /** From SKU */
    @Schema(description = "From SKU")
    private String fromSku;

    /** From LOC */
    @Schema(description = "From LOC")
    private String fromLoc;

    /** From LOT */
    @Schema(description = "From LOT")
    private String fromLot;

    /** From 재고ID */
    @Schema(description = "From 재고ID")
    private String fromStockid;

    /** From 재고등급 */
    @Schema(description = "From 재고등급")
    private String fromStockgrade;

    /** From 재고유형 */
    @Schema(description = "From 재고유형")
    private String fromStocktype;

    /** From 이동요청수량 */
    @Schema(description = "From 이동요청수량")
    private BigDecimal fromOrderqty;

    /** From 단위 */
    @Schema(description = "From 단위")
    private String fromUom;

    /** To 센터 */
    @Schema(description = "To 센터")
    private String toDccode;

    /** To 화주 */
    @Schema(description = "To 화주")
    private String toStorerkey;

    /** To 창고 */
    @Schema(description = "To 창고")
    private String toArea;

    /** To SKU */
    @Schema(description = "To SKU")
    private String toSku;

    /** To LOC */
    @Schema(description = "To LOC")
    private String toLoc;

    /** To LOT */
    @Schema(description = "To LOT")
    private String toLot;

    /** To 재고ID */
    @Schema(description = "To 재고ID")
    private String toStockid;

    /** To 재고등급 */
    @Schema(description = "To 재고등급")
    private String toStockgrade;

    /** To 재고유형 */
    @Schema(description = "To 재고유형")
    private String toStocktype;

    /** To 이동수량 */
    @Schema(description = "To 이동수량")
//    private BigDecimal toOrderQty;\
    private BigDecimal toOrderqty;

    /** To 단위 */
    @Schema(description = "To 단위")
    private String toUom;

    /* ────────── 기타 수량/BOX/중량 ────────── */
    /** 기타수량1 */
    @Schema(description = "기타수량1")
    private BigDecimal etcqty1;

    /** 기타수량2(TRANBOXQTY) */
    @Schema(description = "기타수량2(TRANBOXQTY)")
    private BigDecimal etcqty2;

    /** BOX 환산값 */
    @Schema(description = "BOX 환산값")
    private BigDecimal boxCal;

    /** 평균중량 */
    @Schema(description = "평균중량")
    private BigDecimal avgweight;

    /** 환산BOX */
    @Schema(description = "환산BOX")
    private BigDecimal calbox;

    /** 실BOX */
    @Schema(description = "실BOX")
    private BigDecimal realBox;

    /** 실OPENBOX */
    @Schema(description = "실OPENBOX")
    private BigDecimal realOpenBox;

    /** 실CFMBOX */
    @Schema(description = "실CFMBOX")
    private BigDecimal realcfmbox;

    /** 작업BOX */
    @Schema(description = "작업BOX")
    private BigDecimal tranbox;

    /** BOX 처리유무 */
    @Schema(description = "BOX 처리유무")
    private String boxflag;

    /* ────────── 공장/계약/BL ────────── */
    /** 공장명 */
    @Schema(description = "공장명")
    private String factoryName;

    /** B/L 번호 */
    @Schema(description = "B/L 번호")
    private String convSerialNo;

    /** 도축일자 */
    @Schema(description = "도축일자")
    private String butcheryDt;

    /** 계약사 코드 */
    @Schema(description = "계약사 코드")
    private String contractCompany;

    /** 계약사명 */
    @Schema(description = "계약사명")
    private String contractCompanyName;

    /** 계약유형 */
    @Schema(description = "계약유형")
    private String contractType;

    /* ────────── 유효기간 ────────── */
    /** 유효시작일 */
    @Schema(description = "유효시작일")
    private String fromValidDt;

    /** 유효종료일 */
    @Schema(description = "유효종료일")
    private String toValidDt;

    /* ────────── 과세/운임/보관료 ────────── */
    /** 운송료(공급가) */
    @Schema(description = "운송료(공급가)")
    private BigDecimal tranDeliveryPrice;

    /** 과세/면세 구분 */
    @Schema(description = "과세/면세 구분")
    private String taxCls;

    /** 과세/면세 비율 */
    @Schema(description = "과세/면세 비율")
    private BigDecimal other05;

    /** 보관료 */
    @Schema(description = "보관료")
    private BigDecimal stockPrice;

    /* ────────── 작업/사유 ────────── */
    /** 처리사유코드 */
    @Schema(description = "처리사유코드")
    private String processmsg;

    /** 사유내용 */
    @Schema(description = "사유내용")
    private String memo01;
    private String memo1;

    /* ────────── 이동창고 ────────── */
    /** 이동창고 */
    @Schema(description = "이동창고")
    private String toOrganize;

    /** 이동창고명 */
    @Schema(description = "이동창고명")
    private String toOrganizeName;

    /* ────────── 비정량/표준중량 ────────── */
    /** 비정량 여부 */
    @Schema(description = "비정량 여부")
    private String line01;

    /** 표준중량 */
    @Schema(description = "표준중량")
    private BigDecimal line02;

    /* ────────── 가시화용 색상 ────────── */
    /** 글자색 */
    @Schema(description = "글자색")
    private String fontColor;

    /** 배경색 */
    @Schema(description = "배경색")
    private String bgColor;

    /* ────────── 바코드 & 기타 ────────── */
    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** 박스당 수량 */
    @Schema(description = "박스당 수량")
    private String qtyperbox;

    /** 면세운임(공급가) */
    @Schema(description = "면세운임(공급가)")
    private String deliveryFeeTaxExdc;

    /** 작업번호 */
    @Schema(description = "작업번호")
    private String workNo;
    
    private String errCode;
    private String errMsg;
    
    private String uid1;
}
