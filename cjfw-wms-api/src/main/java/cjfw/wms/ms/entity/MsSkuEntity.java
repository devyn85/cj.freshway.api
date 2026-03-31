package cjfw.wms.ms.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.18
 * @description : 상품기준정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.18        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "상품기준정보 Entity") 
public class MsSkuEntity extends CommonDto {	
	@Schema(description = "데이터 번호")
	private BigDecimal serialKey;

    @Schema(description = "화주 키", example = "STORER001")
    private String storerKey;

    @Schema(description = "재고 품목 코드", example = "SKU0001")
    private String sku;

    @Schema(description = "재고 품목 설명", example = "고급 노트북 15인치")
    private String description;

    @Schema(description = "송장 설명", example = "노트북 컴퓨터")
    private String invoiceDescr;

    @Schema(description = "기타 설명 1", example = "블랙 에디션")
    private String etcDescr1;

    @Schema(description = "기타 설명 2", example = "2024년 신제품")
    private String etcDescr2;

    @Schema(description = "인쇄명 타입", example = "TYPE_A")
    private String namePrintType;

    @Schema(description = "보관 유형", example = "DRY")
    private String storageType;

    @Schema(description = "재고 품목 그룹", example = "ELECTRONICS")
    private String skuGroup;

    @Schema(description = "제품 코드", example = "LAPTOP")
    private String product;

    @Schema(description = "재고 품목 유형", example = "FINISHED")
    private String skuType;

    @Schema(description = "입고 유형", example = "RANDOM")
    private String putawayType;

    @Schema(description = "재고 품목 클래스", example = "PREMIUM")
    private String skuClass;

    @Schema(description = "단위 코드", example = "BOX")
    private String unitCode;

    @Schema(description = "품목 코드", example = "ITEM-001")
    private String itemCode;

    @Schema(description = "고객 재고 품목 코드", example = "CUST-SKU-001")
    private String custSku;

    @Schema(description = "판매 재고 품목 코드", example = "SALES-SKU-001")
    private String salesSku;

    @Schema(description = "제조사 재고 품목 코드", example = "MFG-SKU-001")
    private String manufacturerSku;

    @Schema(description = "대체 재고 품목 코드", example = "ALT-SKU-XYZ")
    private String altSku;

    @Schema(description = "소매 재고 품목 코드", example = "RTSKU-001")
    private String retailSku;

    @Schema(description = "바코드 1", example = "12345678901234567890123456789012")
    private String barcode1;

    @Schema(description = "바코드 2")
    private String barcode2;

    @Schema(description = "바코드 3")
    private String barcode3;

    @Schema(description = "브랜드 코드", example = "BRANDX")
    private String brandCode;

    @Schema(description = "브랜드 설명", example = "XYZ 일렉트로닉스")
    private String brandDescr;

    @Schema(description = "시즌 연도", example = "2024")
    private String seasonYear;

    @Schema(description = "시즌 코드", example = "SPR24")
    private String seasonCode;

    @Schema(description = "시즌 설명", example = "2024년 봄 신상품")
    private String seasonDescr;

    @Schema(description = "시즌 아웃 정보", example = "OUT_SEASON")
    private String seasonOut;

    @Schema(description = "스타일 코드", example = "STYLE01")
    private String styleCode;

    @Schema(description = "스타일 설명", example = "클래식 디자인")
    private String styleDescr;

    @Schema(description = "색상 코드", example = "BLACK")
    private String colorCode;

    @Schema(description = "색상 설명", example = "미드나잇 블랙")
    private String colorDescr;

    @Schema(description = "사이즈 코드", example = "L")
    private String sizeCode;

    @Schema(description = "사이즈 설명", example = "라지")
    private String sizeDescr;

    @Schema(description = "공급업체", example = "ABC_SUPPLY")
    private String vendor;

    @Schema(description = "원산지 (장소)", example = "SEOUL")
    private String placeOfOrigin;

    @Schema(description = "원산지 (국가)", example = "KOR")
    private String countryOfOrigin;

    @Schema(description = "구매 주문 마스터 데이터 코드", example = "POMD001")
    private String pomdCode;

    @Schema(description = "구매 주문 마스터 데이터 이름", example = "구매 마스터 1")
    private String pomdName;

    @Schema(description = "판매 주문 마스터 데이터 코드", example = "SOMD001")
    private String somdCode;

    @Schema(description = "판매 주문 마스터 데이터 이름", example = "판매 마스터 1")
    private String somdName;

    @Schema(description = "공장 고객 키", example = "PLANTCUST001")
    private String plantCustKey;

    @Schema(description = "수입 고객 키", example = "IMPORTCUST001")
    private String importCustKey;

    @Schema(description = "유통 고객 키", example = "DISTCUST001")
    private String distributionCustKey;

    @Schema(description = "반품 고객 키", example = "RETURNCUST001")
    private String returnCustKey;

    @Schema(description = "폐기 고객 키", example = "DISUSECUST001")
    private String disuseCustKey;

    @Schema(description = "재고 품목 옵션", example = "OPTION_A")
    private String skuOptions;

    @Schema(description = "유효 기간 유형", example = "DAYS")
    private String durationType;

    @Schema(description = "유효 기간", example = "365")
    private BigDecimal duration;

    @Schema(description = "기본 측정 단위", example = "EA")
    private String baseUom;

    @Schema(description = "구매 측정 단위", example = "BOX")
    private String purchaseUom;

    @Schema(description = "판매 측정 단위", example = "EA")
    private String salesUom;

    @Schema(description = "반품 측정 단위", example = "EA")
    private String returnUom;

    @Schema(description = "팔레트당 박스 수", example = "10.00000")
    private BigDecimal boxPerPlt;

    @Schema(description = "박스당 수량", example = "20.00000")
    private BigDecimal qtyPerBox;

    @Schema(description = "박스당 팩 수", example = "5.00000")
    private BigDecimal packPerBox;

    @Schema(description = "팩당 수량", example = "4.00000")
    private BigDecimal qtyPerPack;

    @Schema(description = "레이어당 박스 수", example = "2.00000")
    private BigDecimal boxPerLayer;

    @Schema(description = "팔레트당 레이어 수", example = "5.00000")
    private BigDecimal layerPerPlt;

    @Schema(description = "총 중량", example = "100.50000")
    private BigDecimal grossWeight;

    @Schema(description = "순 중량", example = "95.20000")
    private BigDecimal netWeight;

    @Schema(description = "부피 (CBM)", example = "1.25000")
    private BigDecimal cube;

    @Schema(description = "부피 설명", example = "1 CBM")
    private String cubeDescr;

    @Schema(description = "최소 온도", example = "-10.00000")
    private BigDecimal minTemperature;

    @Schema(description = "최대 온도", example = "30.00000")
    private BigDecimal maxTemperature;

    @Schema(description = "구매 유형", example = "NORMAL")
    private String purchaseType;

    @Schema(description = "구매 가격", example = "150000")
    private BigDecimal purchasePrice;

    @Schema(description = "포장 박스 유형", example = "STD")
    private String packBoxType;

    @Schema(description = "품질 관리 유형", example = "FULL_INSP")
    private String qcType;

    @Schema(description = "검사 유형", example = "PRE_SHIP")
    private String inspectType;

    @Schema(description = "BOM 유형", example = "MTL")
    private String bomType;

    @Schema(description = "시리얼 관리 유형", example = "LOT")
    private String serialType;

    @Schema(description = "시리얼 관리 여부 (Y/N)", example = "N")
    private String serialYn;

    @Schema(description = "혼합 박스 여부 (Y/N)", example = "Y")
    private String mixBoxYn;

    @Schema(description = "ABC 분류", example = "A")
    private String abc;

    @Schema(description = "라인 정보 1", example = "L001")
    private String line01;

    @Schema(description = "라인 정보 2", example = "L002")
    private String line02;

    @Schema(description = "라인 정보 3", example = "L003")
    private String line03;

    @Schema(description = "구매 주문 전략", example = "STD")
    private String poStrategy;

    @Schema(description = "판매 주문 전략", example = "OPTIMIZED")
    private String soStrategy;

    @Schema(description = "영수증 전략", example = "DIRECT")
    private String roStrategy;

    @Schema(description = "입고 전략", example = "FIFO")
    private String inStrategy;

    @Schema(description = "피킹 전략", example = "ZONE")
    private String paStrategy;

    @Schema(description = "보관 전략", example = "BIN")
    private String keepStrategy;

    @Schema(description = "FIFO 전략", example = "LOCATION")
    private String fifoStrategy;

    @Schema(description = "조립 전략", example = "KIT")
    private String assemStrategy;

    @Schema(description = "분해 전략", example = "UNIT")
    private String disassemStrategy;

    @Schema(description = "출고 전략", example = "LIFO")
    private String outStrategy;

    @Schema(description = "반품 전략", example = "INSPECT")
    private String returnStrategy;

    @Schema(description = "포장 전략", example = "WEIGHT")
    private String packStrategy;

    @Schema(description = "재고 전략", example = "CYCLIC")
    private String stockStrategy;

    @Schema(description = "조정 전략", example = "MANUAL")
    private String adjustStrategy;

    @Schema(description = "보류 전략", example = "QUARANTINE")
    private String holdStrategy;

    @Schema(description = "폐기 전략", example = "SCRAP")
    private String disuseStrategy;

    @Schema(description = "처리 전략", example = "BATCH")
    private String handleStrategy;

    @Schema(description = "기타 필드 1", example = "추가 정보 A")
    private String other01;

    @Schema(description = "기타 필드 2", example = "추가 정보 B")
    private String other02;

    @Schema(description = "기타 필드 3", example = "추가 정보 C")
    private String other03;

    @Schema(description = "기타 필드 4", example = "추가 정보 D")
    private String other04;

    @Schema(description = "기타 필드 5", example = "추가 정보 E")
    private String other05;

    @Schema(description = "참조 필드 1", example = "참조 데이터 1")
    private String reference01;

    @Schema(description = "참조 필드 2", example = "참조 데이터 2")
    private String reference02;

    @Schema(description = "참조 필드 3", example = "참조 데이터 3")
    private String reference03;

    @Schema(description = "참조 필드 4", example = "참조 데이터 4")
    private String reference04;

    @Schema(description = "참조 필드 5", example = "참조 데이터 5")
    private String reference05;

    @Schema(description = "이미지 URL 1", example = "http://example.com/image1.jpg")
    private String imageUrl1;

    @Schema(description = "이미지 URL 2")
    private String imageUrl2;

    @Schema(description = "이미지 URL 3")
    private String imageUrl3;

    @Schema(description = "이미지 URL 4")
    private String imageUrl4;

    @Schema(description = "이미지 URL 5")
    private String imageUrl5;

    @Schema(description = "인터페이스 파일 이름", example = "sku_data_2024.xml")
    private String ifFilename;

    @Schema(description = "상태 코드", example = "00")
    private String status;

    @Schema(description = "삭제 여부 (Y/N)", example = "N")
    private String delYn;

    @Schema(description = "트래픽 코프", example = "NORMAL")
    private String trafficcop;

    @Schema(description = "아카이브 코프", example = "N")
    private String archivecop;

    @Schema(description = "라벨 유형", example = "STANDARD")
    private String labelType;

    @Schema(description = "참조 필드 15", example = "REF12345")
    private String reference15;

    @Schema(description = "추가 일시", example = "2023-01-01T10:00:00")
    private String adddate;

    @Schema(description = "수정 일시", example = "2023-01-01T10:00:00")
    private String editdate;

    @Schema(description = "추가자", example = "SYSTEM")
    private String addwho;

    @Schema(description = "수정자", example = "SYSTEM")
    private String editwho;

    @Schema(description = "길이", example = "10.00000")
    private BigDecimal length;

    @Schema(description = "너비", example = "5.00000")
    private BigDecimal width;

    @Schema(description = "높이", example = "2.00000")
    private BigDecimal height;
    
    @Schema(description = "미곡 여부 (원래 SIZECODE)", example = "Y/N")
    private String riceYn; // 원래 SIZECODE 였으나 미곡여부로 변경
    
    /** 프로시저 실행 성공여부 */
	@Schema(description = "프로시저 실행 성공여부", example = "")
	private Integer success;
	
	/** 프로시저 실행 에러코드 */
	@Schema(description = "프로시저 실행 에러코드", example = "")
	private Integer err;
	
	/** 프로시저 실행 에러메시지 */
	@Schema(description = "프로시저 실행 에러메시지", example = "")
	private String errmsg;
}
