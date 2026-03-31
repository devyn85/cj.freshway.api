package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.09.02
 * @description : 비용기표 원가관리리포트 팝업 조회 결과 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "비용기표 원가관리리포트 팝업 조회 결과") 
public class IbExpenseStatusPopupResDto extends CommonProcedureDto {   
    
    /** SN */
    @Schema(description = "SN", nullable = true, example = "")
    private String sn;

    /** Status */
    @Schema(description = "Status", nullable = true, example = "")
    private String status;

    /** Shipment */
    @Schema(description = "Shipment", nullable = true, example = "")
    private String invoiceFlag;

    /** B/L No */
    @Schema(description = "B/L No", nullable = true, example = "")
    private String hblNo;

    /** Dept */
    @Schema(description = "Dept", nullable = true, example = "")
    private String dept;

    /** P/O No */
    @Schema(description = "P/O No", nullable = true, example = "")
    private String keyNo;

    /** Item */
    @Schema(description = "Item", nullable = true, example = "")
    private String lovItem;

    /** Business Type */
    @Schema(description = "Business Type", nullable = true, example = "")
    private String businessTypeName;

    /** Rev No */
    @Schema(description = "Rev No", nullable = true, example = "")
    private String revisionNo;

    /** Approval Date */
    @Schema(description = "Approval Date", nullable = true, example = "")
    private String issueDate;

    /** Shipping Type */
    @Schema(description = "Shipping Type", nullable = true, example = "")
    private String transport;

    /** ATTRIBUTES4 */
    @Schema(description = "ATTRIBUTES4", nullable = true, example = "")
    private String attributes4;

    /** Supplier */
    @Schema(description = "Supplier", nullable = true, example = "")
    private String supplierName;

    /** Delivery Type */
    @Schema(description = "Delivery Type", nullable = true, example = "")
    private String priceTermsCode;

    /** Delivery Type Name */
    @Schema(description = "Delivery Type Name", nullable = true, example = "")
    private String priceTermsName;

    /** Payment Type */
    @Schema(description = "Payment Type", nullable = true, example = "")
    private String paymentTypeCode;

    /** Payment Name */
    @Schema(description = "Payment Name", nullable = true, example = "")
    private String paymentName;

    /** Curr */
    @Schema(description = "Curr", nullable = true, example = "")
    private String totalAmountUnit;

    /** Amount */
    @Schema(description = "Amount", nullable = true, example = "")
    private BigDecimal totalAmount;

    /** Reg Name */
    @Schema(description = "Reg Name", nullable = true, example = "")
    private String regId;

    /** Reg Date */
    @Schema(description = "Reg Date", nullable = true, example = "")
    private String regDate;

    /** Mod Name */
    @Schema(description = "Mod Name", nullable = true, example = "")
    private String modId;

    /** Mod Date */
    @Schema(description = "Mod Date", nullable = true, example = "")
    private String modDate;

    /** ERP P/O No */
    @Schema(description = "ERP P/O No", nullable = true, example = "")
    private String erpPoId;

    /** Po sn */
    @Schema(description = "Po sn", nullable = true, example = "")
    private String poSn;

    /** Line No */
    @Schema(description = "Line No", nullable = true, example = "")
    private String lineNo;

    /** Item Code */
    @Schema(description = "Item Code", nullable = true, example = "")
    private String itemCode;

    /** Item Desc */
    @Schema(description = "Item Desc", nullable = true, example = "")
    private String itemDescription;

    /** HS Code */
    @Schema(description = "HS Code", nullable = true, example = "")
    private String hsCode;

    /** Quantity */
    @Schema(description = "Quantity", nullable = true, example = "")
    private BigDecimal quantity;

    /** Unit */
    @Schema(description = "Unit", nullable = true, example = "")
    private String quantityUnit;

    /** Unit Price */
    @Schema(description = "Unit Price", nullable = true, example = "")
    private BigDecimal unitPrice;

    /** Amount */
    @Schema(description = "Amount", nullable = true, example = "")
    private BigDecimal amount;

    /** Curr. */
    @Schema(description = "Curr.", nullable = true, example = "")
    private String amountUnit;

    /** Net Wt. */
    @Schema(description = "Net Wt.", nullable = true, example = "")
    private BigDecimal netWeight;

    /** Unit */
    @Schema(description = "Unit", nullable = true, example = "")
    private String netWeightUnit;

    /** Gross Wt. */
    @Schema(description = "Gross Wt.", nullable = true, example = "")
    private BigDecimal grossWeight;

    /** Unit */
    @Schema(description = "Unit", nullable = true, example = "")
    private String grossWeightUnit;

    /** Volumne */
    @Schema(description = "Volumne", nullable = true, example = "")
    private String volume;

    /** Sales Customer Code */
    @Schema(description = "Sales Customer Code", nullable = true, example = "")
    private String saleCustomerCode;

    /** Sales Customer Name */
    @Schema(description = "Sales Customer Name", nullable = true, example = "")
    private String saleCustomerName;

    /** House B/L No. */
    @Schema(description = "House B/L No.", nullable = true, example = "")
    private String houseBlNo;

    /** LG Flag */
    @Schema(description = "LG Flag", nullable = true, example = "")
    private String lgFlag;

    /** ATTRIBUTES1 */
    @Schema(description = "ATTRIBUTES1", nullable = true, example = "")
    private String attributes1;

    /** Place of Receipt Code */
    @Schema(description = "Place of Receipt Code", nullable = true, example = "")
    private String placeOfReceiptCode;

    /** Master B/LNO */
    @Schema(description = "Master B/LNO", nullable = true, example = "")
    private String masterBlNo;

    /** P/O No */
    @Schema(description = "P/O No", nullable = true, example = "")
    private String poNo;

    /** Pur.Person */
    @Schema(description = "Pur.Person", nullable = true, example = "")
    private String purPerson;

    /** Payment Type */
    @Schema(description = "Payment Type", nullable = true, example = "")
    private String paymentType;

    /** Vendor Code */
    @Schema(description = "Vendor Code", nullable = true, example = "")
    private String shipperCode;

    /** Vendor Name */
    @Schema(description = "Vendor Name", nullable = true, example = "")
    private String shipperName;

    /** B/L Date */
    @Schema(description = "B/L Date", nullable = true, example = "")
    private String onboardDate;

    /** ETA */
    @Schema(description = "ETA", nullable = true, example = "")
    private String arrivalDate;

    /** C/L Rest */
    @Schema(description = "C/L Rest", nullable = true, example = "")
    private String customsFlag;

    /** Carrier */
    @Schema(description = "Carrier", nullable = true, example = "")
    private String carrierName;

    /** Shipping port Name */
    @Schema(description = "Shipping port Name", nullable = true, example = "")
    private String shippingPortName;

    /** Arrival port Name */
    @Schema(description = "Arrival port Name", nullable = true, example = "")
    private String arrivalPortName;

    /** TransPort Kind */
    @Schema(description = "TransPort Kind", nullable = true, example = "")
    private String transportKind;

    /** TOTAL Packing */
    @Schema(description = "TOTAL Packing", nullable = true, example = "")
    private String totalPacking;

    /** TOTAL Gross WT. */
    @Schema(description = "TOTAL Gross WT.", nullable = true, example = "")
    private BigDecimal totalGrossWeight;

    /** Mmeasurement */
    @Schema(description = "Mmeasurement", nullable = true, example = "")
    private String measurement;

    /** Receipt Date */
    @Schema(description = "Receipt Date", nullable = true, example = "")
    private String receiptDate;

    /** Place of Receipt Name */
    @Schema(description = "Place of Receipt Name", nullable = true, example = "")
    private String placeOfReceiptName;

    /** Quantity */
    @Schema(description = "Quantity", nullable = true, example = "")
    private BigDecimal totalQuantity;

    /** Unit. */
    @Schema(description = "Unit.", nullable = true, example = "")
    private String totalQuantityUnit;
    
    /** SHIPPING_DATE */
    @Schema(description = "SHIPPING_DATE", nullable = true, example = "")
    private String shippingDate;
    
    /** EXPIRY_DATE */
    @Schema(description = "EXPIRY_DATE", nullable = true, example = "")
    private String expiryDate;
    

}
