package cjfw.wms.ms.dto;

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
public class MsExDcRateGetSkuDataSelectResDto {
    @Schema(description = "")
    private String serialKey;

    @Schema(description = "")
    private String storerKey;

    @Schema(description = "")
    private String sku;

    @Schema(description = "")
    private String description;

    @Schema(description = "")
    private String invoiceDescr;

    @Schema(description = "")
    private String etcDescr1;

    @Schema(description = "")
    private String etcDescr2;

    @Schema(description = "")
    private String namePrintType;

    @Schema(description = "")
    private String storageType;

    @Schema(description = "")
    private String skuGroup;

    @Schema(description = "")
    private String product;

    @Schema(description = "")
    private String skuType;

    @Schema(description = "")
    private String putawayType;

    @Schema(description = "")
    private String skuClass;

    @Schema(description = "")
    private String unitCode;

    @Schema(description = "")
    private String itemCode;

    @Schema(description = "")
    private String custSku;

    @Schema(description = "")
    private String salesSku;

    @Schema(description = "")
    private String manufacturerSku;

    @Schema(description = "")
    private String altSku;

    @Schema(description = "")
    private String retailSku;

    @Schema(description = "")
    private String barcode1;

    @Schema(description = "")
    private String barcode2;

    @Schema(description = "")
    private String barcode3;

    @Schema(description = "")
    private String brandCode;

    @Schema(description = "")
    private String brandDescr;

    @Schema(description = "")
    private String seasonYear;

    @Schema(description = "")
    private String seasonCode;

    @Schema(description = "")
    private String seasonDescr;

    @Schema(description = "")
    private String seasonOut;

    @Schema(description = "")
    private String styleCode;

    @Schema(description = "")
    private String styleDescr;

    @Schema(description = "")
    private String colorCode;

    @Schema(description = "")
    private String colorDescr;

    @Schema(description = "")
    private String riceYn;

    @Schema(description = "")
    private String sizeDescr;

    @Schema(description = "")
    private String vendor;

    @Schema(description = "")
    private String placeOfOrigin;

    @Schema(description = "")
    private String countryOfOrigin;

    @Schema(description = "")
    private String pomdCode;

    @Schema(description = "")
    private String pomdName;

    @Schema(description = "")
    private String somdCode;

    @Schema(description = "")
    private String somdName;

    @Schema(description = "")
    private String plantCustKey;

    @Schema(description = "")
    private String importCustKey;

    @Schema(description = "")
    private String distributionCustKey;

    @Schema(description = "")
    private String returnCustKey;

    @Schema(description = "")
    private String disuseCustKey;

    @Schema(description = "")
    private String skuOptions;

    @Schema(description = "")
    private String duration;

    @Schema(description = "")
    private String baseUom;

    @Schema(description = "")
    private String purchaseUom;

    @Schema(description = "")
    private String salesUom;

    @Schema(description = "")
    private String returnUom;

    @Schema(description = "")
    private String boxPerPlt;

    @Schema(description = "")
    private String qtyPerBox;

    @Schema(description = "")
    private String packPerBox;

    @Schema(description = "")
    private String qtyPerPack;

    @Schema(description = "")
    private String boxPerLayer;

    @Schema(description = "")
    private String layerPerPlt;

    @Schema(description = "")
    private String grossWeight;

    @Schema(description = "")
    private String netWeight;

    @Schema(description = "")
    private String cube;

    @Schema(description = "")
    private String cubeDescr;

    @Schema(description = "")
    private String cubeDescr1;

    @Schema(description = "")
    private String cubeDescr2;

    @Schema(description = "")
    private String cubeDescr3;

    @Schema(description = "")
    private String minTemperature;

    @Schema(description = "")
    private String maxTemperature;

    @Schema(description = "")
    private String purchaseType;

    @Schema(description = "")
    private String purchasePrice;

    @Schema(description = "")
    private String packBoxType;

    @Schema(description = "")
    private String qcType;

    @Schema(description = "")
    private String inspectType;

    @Schema(description = "")
    private String bomType;

    @Schema(description = "")
    private String serialType;

    @Schema(description = "")
    private String serialYn;

    @Schema(description = "")
    private String mixBoxYn;

    @Schema(description = "")
    private String abc;

    @Schema(description = "")
    private String line01;

    @Schema(description = "")
    private String line02;

    @Schema(description = "")
    private String line03;

    @Schema(description = "")
    private String poStrategy;

    @Schema(description = "")
    private String soStrategy;

    @Schema(description = "")
    private String roStrategy;

    @Schema(description = "")
    private String inStrategy;

    @Schema(description = "")
    private String paStrategy;

    @Schema(description = "")
    private String keepStrategy;

    @Schema(description = "")
    private String fifoStrategy;

    @Schema(description = "")
    private String assemStrategy;

    @Schema(description = "")
    private String disassemStrategy;

    @Schema(description = "")
    private String outStrategy;

    @Schema(description = "")
    private String returnStrategy;

    @Schema(description = "")
    private String packStrategy;

    @Schema(description = "")
    private String stockStrategy;

    @Schema(description = "")
    private String adjustStrategy;

    @Schema(description = "")
    private String holdStrategy;

    @Schema(description = "")
    private String disuseStrategy;

    @Schema(description = "")
    private String handleStrategy;

    @Schema(description = "")
    private String other01;

    @Schema(description = "")
    private String other02;

    @Schema(description = "")
    private String other03;

    @Schema(description = "")
    private String other04;

    @Schema(description = "")
    private String other05;

    @Schema(description = "")
    private String reference01;

    @Schema(description = "")
    private String reference02;

    @Schema(description = "")
    private String reference03;

    @Schema(description = "")
    private String reference04;

    @Schema(description = "")
    private String reference05;

    @Schema(description = "")
    private String imageUrl1;

    @Schema(description = "")
    private String imageUrl2;

    @Schema(description = "")
    private String imageUrl3;

    @Schema(description = "")
    private String imageUrl4;

    @Schema(description = "")
    private String imageUrl5;

    @Schema(description = "")
    private String iffilename;

    @Schema(description = "")
    private String status;

    @Schema(description = "")
    private String delYn;

    @Schema(description = "")
    private String trafficCop;

    @Schema(description = "")
    private String archiveCop;

    @Schema(description = "")
    private String addWho;

    @Schema(description = "")
    private String editWho;

    @Schema(description = "")
    private String durationType;

    @Schema(description = "")
    private String addDate;

    @Schema(description = "")
    private String editDate;

    @Schema(description = "")
    private String labelType;

    @Schema(description = "")
    private String skuDdesc;

    @Schema(description = "")
    private String skuSdesc;

    @Schema(description = "")
    private String skuMdesc;

    @Schema(description = "")
    private String skuLdesc;

}