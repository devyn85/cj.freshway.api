package cjfw.wms.ms.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.06.11
 * @description : 상품정보 조회 조건 DTO
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.11        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품정보 조회 조건 DTO")
public class MsSkuReqDto extends CommonDto {
	
	@Schema(description = "물류센터", example = "2600")
	private String dccode;

    @Schema(description = "협력사 코드", example = "CUST001")
    private String partnerCode;
    
    @Schema(description = "상품 코드", example = "CUST001")
    private String skuCode;
    
    @Schema(description = "저장조건")
    private String storageType;
    
    @Schema(description = "상품유형-1")
    private String skuType;
    
    @Schema(description = "담당MD명")
    private String somdCode;
    
    @Schema(description = "진행상태")
    private String statusSku;
    
    @Schema(description = "식별번호유무")
    private String serialYn;
    
    @Schema(description = "데이터 번호")
    private String serialKey;

    @Schema(description = "고객사 코드", example = "FW00")
    private String storerKey;

    @Schema(description = "상품 코드", example = "103055")
    private String sku;

    @Schema(description = "기본 제품명 (없을 경우 SKU 코드)")
    private String description;

    @Schema(description = "명세서 및 운송장 표기용 상품명")
    private String invoiceDescr;

    @Schema(description = "기타 제품명1")
    private String etcDescr1;

    @Schema(description = "기타 제품명2")
    private String etcDescr2;

    @Schema(description = "명세서에 찍히는 상품명 출력 방법")
    private String namePrintType;

    @Schema(description = "상품 그룹")
    private String skuGroup;

    @Schema(description = "상품 종류")
    private String product;

    @Schema(description = "적치 유형")
    private String putawayType;

    @Schema(description = "상품 클래스")
    private String skuClass;

    @Schema(description = "단품 코드")
    private String unitCode;

    @Schema(description = "아이템 코드")
    private String itemCode;

    @Schema(description = "거래처 상품 코드")
    private String custSku;

    @Schema(description = "판매 상품 코드")
    private String salesSku;

    @Schema(description = "생산 코드")
    private String manufacturerSku;

    @Schema(description = "대체 상품 코드")
    private String altSku;

    @Schema(description = "소매 상품 코드")
    private String retailSku;

    @Schema(description = "표준 바코드")
    private String barcode1;

    @Schema(description = "EAN13 바코드")
    private String barcode2;

    @Schema(description = "EAN14 바코드")
    private String barcode3;

    @Schema(description = "제품 브랜드 (없을 경우 고객사 코드)")
    private String brandCode;

    @Schema(description = "브랜드명")
    private String brandDescr;

    @Schema(description = "시즌 년도")
    private String seasonYear;

    @Schema(description = "제품 시즌 코드")
    private String seasonCode;

    @Schema(description = "시즌명")
    private String seasonDescr;

    @Schema(description = "시즌 아웃 정보")
    private String seasonOut;

    @Schema(description = "스타일 코드 (없을 경우 SKU 코드)")
    private String styleCode;

    @Schema(description = "스타일명")
    private String styleDescr;

    @Schema(description = "컬러 코드 (없을 경우 SKU 코드)")
    private String colorCode;

    @Schema(description = "컬러명")
    private String colorDescr;

    @Schema(description = "미곡 여부 (원래 SIZECODE)", example = "Y/N")
    private String riceYn; // 원래 SIZECODE 였으나 미곡여부로 변경

    @Schema(description = "사이즈명")
    private String sizeDescr;

    @Schema(description = "판매 업체 코드 (없을 경우 고객사 코드)")
    private String vendor;

    @Schema(description = "원산지명")
    private String placeOfOrigin;

    @Schema(description = "원산국명")
    private String countryOfOrigin;

    @Schema(description = "구매 담당 코드")
    private String pomdCode;

    @Schema(description = "구매 담당명")
    private String pomdName;

    @Schema(description = "판매 담당명")
    private String somdName;

    @Schema(description = "생산 업체 코드 (없을 경우 고객사 코드)")
    private String plantCustKey;

    @Schema(description = "수입 업체 코드 (없을 경우 고객사 코드)")
    private String importCustKey;

    @Schema(description = "유통 업체 코드 (없을 경우 고객사 코드)")
    private String distributionCustKey;

    @Schema(description = "반품 업체 코드 (없을 경우 고객사 코드)")
    private String returnCustKey;

    @Schema(description = "폐기 업체 코드 (없을 경우 고객사 코드)")
    private String disuseCustKey;

    @Schema(description = "상품 옵션 (비표 등)")
    private String skuOptions;

    @Schema(description = "유통 기간")
    private String duration;

    @Schema(description = "기본 단위")
    private String baseUom;

    @Schema(description = "구매 단위")
    private String purchaseUom;

    @Schema(description = "판매 단위")
    private String salesUom;

    @Schema(description = "회수 단위")
    private String returnUom;

    @Schema(description = "팔레트당 박스 수")
    private BigDecimal boxPerPlt;

    @Schema(description = "박스당 낱개 수")
    private BigDecimal qtyPerBox;

    @Schema(description = "박스당 팩 수")
    private BigDecimal packPerBox;

    @Schema(description = "팩당 낱개 수")
    private BigDecimal qtyPerPack;

    @Schema(description = "적재 단당 박스 수")
    private BigDecimal boxPerLayer;

    @Schema(description = "팔레트당 적재 단수")
    private BigDecimal layerPerPlt;

    @Schema(description = "개당 총중량 (Kg)")
    private BigDecimal grossWeight;

    @Schema(description = "개당 실중량 (Kg)")
    private BigDecimal netWeight;

    @Schema(description = "상품 체적 (cm^3)")
    private BigDecimal cube;

    @Schema(description = "상품 체적 설명 (길이-너비-높이, 단위 mm)")
    private String cubeDescr;

    @Schema(description = "상품 체적 설명 (장)")
    private BigDecimal cubeDescr1;

    @Schema(description = "상품 체적 설명 (폭)")
    private BigDecimal cubeDescr2;

    @Schema(description = "상품 체적 설명 (고)")
    private BigDecimal cubeDescr3;

    @Schema(description = "관리 온도 최저값 (섭씨)")
    private BigDecimal minTemperature;

    @Schema(description = "관리 온도 최대값 (섭씨)")
    private BigDecimal maxTemperature;

    @Schema(description = "구매 유형")
    private String purchaseType;

    @Schema(description = "구매 단가")
    private BigDecimal purchasePrice;

    @Schema(description = "포장 박스 유형")
    private String packBoxType;

    @Schema(description = "QC 유형")
    private String qcType;

    @Schema(description = "검수 유형")
    private String inspectType;

    @Schema(description = "BOM 유형 (FG, MTL, PKG 등)")
    private String bomType;

    @Schema(description = "시리얼 타입")
    private String serialType;

    @Schema(description = "합포장 가능 여부")
    private String mixboxYn;

    @Schema(description = "ABC 분류")
    private String abc;

    @Schema(description = "제품 생산 라인1 (중복)")
    private String line01; // 중복 필드이지만 쿼리에서 다시 선택됨

    @Schema(description = "제품 생산 라인2")
    private String line02;

    @Schema(description = "제품 생산 라인3")
    private String line03;

    @Schema(description = "구매 전략")
    private String postrategy;

    @Schema(description = "판매 전략")
    private String sostrategy;

    @Schema(description = "반품 전략")
    private String rostrategy;

    @Schema(description = "입고 전략")
    private String instrategy;

    @Schema(description = "적치 전략")
    private String pastrategy;

    @Schema(description = "보관 전략")
    private String keepstrategy;

    @Schema(description = "FIFO 전략")
    private String fifostrategy;

    @Schema(description = "해체 전략")
    private String disassemstrategy;

    @Schema(description = "출고 전략")
    private String outstrategy;

    @Schema(description = "회수 전략")
    private String returnstrategy;

    @Schema(description = "패킹 전략")
    private String packstrategy;

    @Schema(description = "재고 전략")
    private String stockstrategy;

    @Schema(description = "조정 전략")
    private String adjuststrategy;

    @Schema(description = "보류 전략")
    private String holdstrategy;

    @Schema(description = "폐기 전략")
    private String disusestrategy;

    @Schema(description = "취급 전략")
    private String handlestrategy;

    @Schema(description = "기타 정보1")
    private String other01;

    @Schema(description = "기타 정보2")
    private String other02;

    @Schema(description = "기타 정보3")
    private String other03;

    @Schema(description = "기타 정보4")
    private String other04;

    @Schema(description = "기타 정보5")
    private String other05;

    @Schema(description = "참조 정보1")
    private String reference01;

    @Schema(description = "참조 정보2")
    private String reference02;

    @Schema(description = "참조 정보3")
    private String reference03;

    @Schema(description = "참조 정보4")
    private String reference04;

    @Schema(description = "참조 정보5")
    private String reference05;

    @Schema(description = "관련 이미지 URL1")
    private String imageUrl1;

    @Schema(description = "관련 이미지 URL2")
    private String imageUrl2;

    @Schema(description = "관련 이미지 URL3")
    private String imageUrl3;

    @Schema(description = "관련 이미지 URL4")
    private String imageUrl4;

    @Schema(description = "관련 이미지 URL5")
    private String imageUrl5;

    @Schema(description = "인터페이스 파일명")
    private String ifFileName;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "삭제 여부")
    private String delYn;

    @Schema(description = "데이터 흐름 제어")
    private String trafficCop;

    @Schema(description = "아카이브 제어")
    private String archiveCop;

    @Schema(description = "최초 등록자")
    private String addWho;

    @Schema(description = "최종 변경자")
    private String editWho;

    @Schema(description = "최초 등록 시간", example = "YYYYMMDDHH24MISS")
    private String addDate;

    @Schema(description = "최종 변경 시간", example = "YYYYMMDDHH24MISS")
    private String editDate;

    @Schema(description = "라벨 타입")
    private String labelType;

    @Schema(description = "상품 대분류 설명")
    private String skuDdesc;

    @Schema(description = "상품 중분류 설명")
    private String skuSdesc;

    @Schema(description = "상품 소분류 설명")
    private String skuMdesc;

    @Schema(description = "상품 그룹 상위 분류 설명")
    private String skuLdesc;
    
    @Schema(description = "SIZECODE", example = "Y/N")
    private String sizeCode;
    
    @Schema(description = "상품 체적 설명 (장)")
    private BigDecimal length;
    
    @Schema(description = "상품 체적 설명 (폭)")
    private BigDecimal width;
    
    @Schema(description = "상품 체적 설명 (고)")
    private BigDecimal height;
    
    /** 저장 목록 */
	@Schema(description = "저장 목록", example = "")
	private List<MsSkuReqDto> skuList;
	
	@Schema(description = "상품 코드 리스트", example = "CUST001")
    private String[][] skuCodeList;
}
