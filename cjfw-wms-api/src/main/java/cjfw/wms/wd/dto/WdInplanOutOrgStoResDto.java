package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.07.08 
 * @description : 출고재고분배 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고재고분배 요청") 
public class WdInplanOutOrgStoResDto extends CommonDto {

    /** 오더번호 */
    @Schema(description = "오더번호")
    private String slipNo;

    /** 출고일자 */
    @Schema(description = "출고일자")
    private String deliveryDate;

    /** 조직(ORGANIZE) */
    @Schema(description = "조직(ORGANIZE)")
    private String organize;

    /** 플랜트 */
    @Schema(description = "플랜트")
    private String plant;

    /** 창고코드(저장위치코드) */
    @Schema(description = "창고코드(저장위치코드)")
    private String storageLoc;

    /** 창고명 */
    @Schema(description = "창고명")
    private String warehouseName;

    /** 창고권역 */
    @Schema(description = "창고권역")
    private String warehouseZone;

    /** 창고지역 */
    @Schema(description = "창고지역")
    private String warehouseArea;

    /** 창고주소 */
    @Schema(description = "창고주소")
    private String warehouseAddress;

    /** 관리처코드 */
    @Schema(description = "관리처코드")
    private String manageCustKey;

    /** 관리처명 */
    @Schema(description = "관리처명")
    private String manageCustName;

    /** 관리처우편번호 */
    @Schema(description = "관리처우편번호")
    private String manageCustZipcode;

    /** 관리처권역 */
    @Schema(description = "관리처권역")
    private String manageCustZone;

    /** 관리처지역 */
    @Schema(description = "관리처지역")
    private String manageCustArea;

    /** 관리처주소 */
    @Schema(description = "관리처주소")
    private String manageCustAddress;

    /** 택배사(COURIER) */
    @Schema(description = "택배사(COURIER)")
    private String courier;

    /** 운송료(REFERENCE10 원본) */
    @Schema(description = "운송료(REFERENCE10 원본)")
    private BigDecimal reference10;

    /** 운송비(부가세 반영 금액) */
    @Schema(description = "운송비(부가세 반영 금액)")
    private BigDecimal tranDeliveryPrice;

    /** 보관유형(STORAGETYPE) */
    @Schema(description = "보관유형(STORAGETYPE)")
    private String storageType;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;

    /** 상품명 */
    @Schema(description = "상품명")
    private String skuName;

    /** 박스당수량 */
    @Schema(description = "박스당수량")
    private BigDecimal qtyPerBox;

    /** 단위중량(kg) */
    @Schema(description = "단위중량(kg)")
    private BigDecimal netWeight;

    /** 기본단위 */
    @Schema(description = "기본단위")
    private String baseUom;

    /** 확정수량 */
    @Schema(description = "확정수량")
    private BigDecimal confirmQty;

    /** 확정중량(kg) */
    @Schema(description = "확정중량(kg)")
    private BigDecimal confirmNetWeight;

    /** 이력번호(SERIALNO) */
    @Schema(description = "이력번호(SERIALNO)")
    private String serialNo;

    /** 바코드 */
    @Schema(description = "바코드")
    private String barcode;

    /** BL번호(CONVSERIALNO) */
    @Schema(description = "BL번호(CONVSERIALNO)")
    private String convSerialNo;

    /** 계약유형(CONTRACTTYPE) */
    @Schema(description = "계약유형(CONTRACTTYPE)")
    private String contractType;

    /** 계약업체코드 */
    @Schema(description = "계약업체코드")
    private String contractCompany;

    /** 계약업체명 */
    @Schema(description = "계약업체명")
    private String contractCompanyName;

    /** PO키 */
    @Schema(description = "PO키")
    private String poKey;

    /** PO라인 */
    @Schema(description = "PO라인")
    private String poLine;

    /** 주문유형 */
    @Schema(description = "주문유형")
    private String storageTypeCd;

    /** 주문사유 */
    @Schema(description = "주문사유")
    private String contractTypeCd;
    
    private String tonCd;
    private String carCnt;
    private String serialKeyGroup;
   
	
}
