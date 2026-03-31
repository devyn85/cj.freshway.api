package cjfw.wms.st.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.04 
 * @description : 상품 정보 조회 (단건) 응답 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.04 ParkJinWoo 생성
 */
@Data
@Schema(description = "상품 정보 조회 (단건) 응답 DTO" )
public class StTplIssueReqPopupResDpDto {
    /** 시리얼키 */
    @Schema(description = "시리얼키")
    private String serialKey;

    /** 화주코드 */
    @Schema(description = "화주코드")
    private String storerKey;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String description;

    /** 인보이스표기명 */
    @Schema(description = "인보이스표기명")
    private String invoiceDescr;

    /** 기타설명1 */
    @Schema(description = "기타설명1")
    private String etcDescr1;

    /** 기타설명2 */
    @Schema(description = "기타설명2")
    private String etcDescr2;

    /** 명칭인쇄유형 */
    @Schema(description = "명칭인쇄유형")
    private String namePrintType;

    /** 보관유형 */
    @Schema(description = "보관유형")
    private String storageType;

    /** 상품그룹 */
    @Schema(description = "상품그룹")
    private String skuGroup;

    /** 제품구분 */
    @Schema(description = "제품구분")
    private String product;

    /** 상품유형 */
    @Schema(description = "상품유형")
    private String skuType;

    /** 적치유형 */
    @Schema(description = "적치유형")
    private String putawayType;

    /** 상품분류 */
    @Schema(description = "상품분류")
    private String skuClass;

    /** 단위코드 */
    @Schema(description = "단위코드")
    private String unitCode;

    /** 품목코드 */
    @Schema(description = "품목코드")
    private String itemCode;

    /** 거래처상품코드 */
    @Schema(description = "거래처상품코드")
    private String custSku;

    /** 판매상품코드 */
    @Schema(description = "판매상품코드")
    private String salesSku;

    /** 제조사상품코드 */
    @Schema(description = "제조사상품코드")
    private String manufacturerSku;

    /** 대체상품코드 */
    @Schema(description = "대체상품코드")
    private String altSku;

    /** 소매상품코드 */
    @Schema(description = "소매상품코드")
    private String retailSku;

    /** 바코드1 */
    @Schema(description = "바코드1")
    private String barcode1;

    /** 바코드2 */
    @Schema(description = "바코드2")
    private String barcode2;

    /** 바코드3 */
    @Schema(description = "바코드3")
    private String barcode3;

    /** 브랜드코드 */
    @Schema(description = "브랜드코드")
    private String brandCode;

    /** 브랜드명 */
    @Schema(description = "브랜드명")
    private String brandDescr;

    /** 시즌년도 */
    @Schema(description = "시즌년도")
    private String seasonYear;

    /** 시즌코드 */
    @Schema(description = "시즌코드")
    private String seasonCode;

    /** 시즌명 */
    @Schema(description = "시즌명")
    private String seasonDescr;

    /** 시즌종료구분 */
    @Schema(description = "시즌종료구분")
    private String seasonOut;

    /** 스타일코드 */
    @Schema(description = "스타일코드")
    private String styleCode;

    /** 스타일명 */
    @Schema(description = "스타일명")
    private String styleDescr;

    /** 색상코드 */
    @Schema(description = "색상코드")
    private String colorCode;

    /** 색상명 */
    @Schema(description = "색상명")
    private String colorDescr;

    /** 쌀상품여부 */
    @Schema(description = "쌀상품여부")
    private String riceYn;

    /** 사이즈명 */
    @Schema(description = "사이즈명")
    private String sizeDescr;

    /** 공급업체코드 */
    @Schema(description = "공급업체코드")
    private String vendor;

    /** 원산지 */
    @Schema(description = "원산지")
    private String placeOfOrigin;

    /** 원산지국가 */
    @Schema(description = "원산지국가")
    private String countryOfOrigin;

    /** 대분류코드 */
    @Schema(description = "대분류코드")
    private String pomdCode;

    /** 대분류명 */
    @Schema(description = "대분류명")
    private String pomdName;

    /** 소분류코드 */
    @Schema(description = "소분류코드")
    private String somdCode;

    /** 소분류명 */
    @Schema(description = "소분류명")
    private String somdName;

    /** 공장거래처코드 */
    @Schema(description = "공장거래처코드")
    private String plantCustKey;

    /** 수입거래처코드 */
    @Schema(description = "수입거래처코드")
    private String importCustKey;

    /** 유통거래처코드 */
    @Schema(description = "유통거래처코드")
    private String distributionCustKey;

    /** 반품거래처코드 */
    @Schema(description = "반품거래처코드")
    private String returnCustKey;

    /** 폐기거래처코드 */
    @Schema(description = "폐기거래처코드")
    private String disuseCustKey;

    /** 상품옵션 */
    @Schema(description = "상품옵션")
    private String skuOptions;

    /** 유통기간 */
    @Schema(description = "유통기간")
    private String duration;

    /** 기준단위 */
    @Schema(description = "기준단위")
    private String baseUom;

    /** 매입단위 */
    @Schema(description = "매입단위")
    private String purchaseUom;

    /** 판매단위 */
    @Schema(description = "판매단위")
    private String salesUom;

    /** 반품단위 */
    @Schema(description = "반품단위")
    private String returnUom;

    /** PLT당박스수 */
    @Schema(description = "PLT당박스수")
    private String boxPerPlt;

    /** BOX당수량 */
    @Schema(description = "BOX당수량")
    private String qtyPerBox;

    /** BOX당PACK수 */
    @Schema(description = "BOX당PACK수")
    private String packPerBox;

    /** PACK당수량 */
    @Schema(description = "PACK당수량")
    private String qtyPerPack;

    /** 적층당박스수 */
    @Schema(description = "적층당박스수")
    private String boxPerLayer;

    /** PLT당적층수 */
    @Schema(description = "PLT당적층수")
    private String layerPerPlt;

    /** 총중량 */
    @Schema(description = "총중량")
    private String grossWeight;

    /** 순중량 */
    @Schema(description = "순중량")
    private String netWeight;

    /** 체적 */
    @Schema(description = "체적")
    private String cube;

    /** 체적설명 */
    @Schema(description = "체적설명")
    private String cubeDescr;

    /** 체적추가정보1 */
    @Schema(description = "체적추가정보1")
    private String cubeDescr1;

    /** 체적추가정보2 */
    @Schema(description = "체적추가정보2")
    private String cubeDescr2;

    /** 체적추가정보3 */
    @Schema(description = "체적추가정보3")
    private String cubeDescr3;

    /** 최저보관온도 */
    @Schema(description = "최저보관온도")
    private String minTemperature;

    /** 최고보관온도 */
    @Schema(description = "최고보관온도")
    private String maxTemperature;

    /** 매입구분 */
    @Schema(description = "매입구분")
    private String purchaseType;

    /** 매입단가 */
    @Schema(description = "매입단가")
    private String purchasePrice;

    /** 포장박스타입 */
    @Schema(description = "포장박스타입")
    private String packBoxType;

    /** 검수유형 */
    @Schema(description = "검수유형")
    private String qcType;

    /** 검사유형 */
    @Schema(description = "검사유형")
    private String inspectType;

    /** BOM유형 */
    @Schema(description = "BOM유형")
    private String bomType;

    /** 시리얼유형 */
    @Schema(description = "시리얼유형")
    private String serialType;

    /** 시리얼관리여부 */
    @Schema(description = "시리얼관리여부")
    private String serialYn;

    /** 혼적박스허용여부 */
    @Schema(description = "혼적박스허용여부")
    private String mixBoxYn;

    /** ABC코드 */
    @Schema(description = "ABC코드")
    private String abc;

    /** 추가속성1 */
    @Schema(description = "추가속성1")
    private String line01;

    /** 추가속성2 */
    @Schema(description = "추가속성2")
    private String line02;

    /** 추가속성3 */
    @Schema(description = "추가속성3")
    private String line03;

    /** 발주전략 */
    @Schema(description = "발주전략")
    private String poStrategy;

    /** 판매전략 */
    @Schema(description = "판매전략")
    private String soStrategy;

    /** 반품전략 */
    @Schema(description = "반품전략")
    private String roStrategy;

    /** 입고전략 */
    @Schema(description = "입고전략")
    private String inStrategy;

    /** 적치전략 */
    @Schema(description = "적치전략")
    private String paStrategy;

    /** 보관전략 */
    @Schema(description = "보관전략")
    private String keepStrategy;

    /** 선입선출전략 */
    @Schema(description = "선입선출전략")
    private String fifoStrategy;

    /** 조립전략 */
    @Schema(description = "조립전략")
    private String assemStrategy;

    /** 분해전략 */
    @Schema(description = "분해전략")
    private String disassemStrategy;

    /** 출고전략 */
    @Schema(description = "출고전략")
    private String outStrategy;

    /** 반품처리전략 */
    @Schema(description = "반품처리전략")
    private String returnStrategy;

    /** 포장전략 */
    @Schema(description = "포장전략")
    private String packStrategy;

    /** 재고전략 */
    @Schema(description = "재고전략")
    private String stockStrategy;

    /** 재고조정전략 */
    @Schema(description = "재고조정전략")
    private String adjustStrategy;

    /** 보류전략 */
    @Schema(description = "보류전략")
    private String holdStrategy;

    /** 폐기전략 */
    @Schema(description = "폐기전략")
    private String disuseStrategy;

    /** 취급전략 */
    @Schema(description = "취급전략")
    private String handleStrategy;

    /** 기타항목01 */
    @Schema(description = "기타항목01")
    private String other01;

    /** 기타항목02 */
    @Schema(description = "기타항목02")
    private String other02;

    /** 기타항목03 */
    @Schema(description = "기타항목03")
    private String other03;

    /** 기타항목04 */
    @Schema(description = "기타항목04")
    private String other04;

    /** 기타항목05 */
    @Schema(description = "기타항목05")
    private String other05;

    /** 참조항목01 */
    @Schema(description = "참조항목01")
    private String reference01;

    /** 참조항목02 */
    @Schema(description = "참조항목02")
    private String reference02;

    /** 참조항목03 */
    @Schema(description = "참조항목03")
    private String reference03;

    /** 참조항목04 */
    @Schema(description = "참조항목04")
    private String reference04;

    /** 참조항목05 */
    @Schema(description = "참조항목05")
    private String reference05;

    /** 이미지URL1 */
    @Schema(description = "이미지URL1")
    private String imageUrl1;

    /** 이미지URL2 */
    @Schema(description = "이미지URL2")
    private String imageUrl2;

    /** 이미지URL3 */
    @Schema(description = "이미지URL3")
    private String imageUrl3;

    /** 이미지URL4 */
    @Schema(description = "이미지URL4")
    private String imageUrl4;

    /** 이미지URL5 */
    @Schema(description = "이미지URL5")
    private String imageUrl5;

    /** 인터페이스파일명 */
    @Schema(description = "인터페이스파일명")
    private String iffilename;

    /** 상태 */
    @Schema(description = "상태")
    private String status;

    /** 삭제여부 */
    @Schema(description = "삭제여부")
    private String delYn;

    /** 트래픽제어값 */
    @Schema(description = "트래픽제어값")
    private String trafficCop;

    /** 아카이브제어값 */
    @Schema(description = "아카이브제어값")
    private String archiveCop;

    /** 등록자 */
    @Schema(description = "등록자")
    private String addWho;

    /** 수정자 */
    @Schema(description = "수정자")
    private String editWho;

    /** 유통기간유형 */
    @Schema(description = "유통기간유형")
    private String durationType;

    /** 등록일시 */
    @Schema(description = "등록일시")
    private String addDate;

    /** 수정일시 */
    @Schema(description = "수정일시")
    private String editDate;

    /** 라벨유형 */
    @Schema(description = "라벨유형")
    private String labelType;

    /** 상품상세설명 */
    @Schema(description = "상품상세설명")
    private String skuDdesc;

    /** 상품간략설명 */
    @Schema(description = "상품간략설명")
    private String skuSdesc;

    /** 상품중간설명 */
    @Schema(description = "상품중간설명")
    private String skuMdesc;

    /** 상품상세설명(장문) */
    @Schema(description = "상품상세설명(장문)")
    private String skuLdesc;

}