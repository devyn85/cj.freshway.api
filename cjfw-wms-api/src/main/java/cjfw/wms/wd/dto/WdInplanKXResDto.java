package cjfw.wms.wd.dto;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JiSooKim (jskim14@cj.net) 
 * @date        : 2025.11.28
 * @description : KX출고진행현황 통합 정보 조회 결과 DTO
 */
@Data
@Schema(description = "KX출고진행현황 통합 정보 조회 결과 DTO")
public class WdInplanKXResDto extends CommonDto {

    @Schema(description = "CHECKYN")
    private String checkyn;

    @Schema(description = "DCCODE")
    private String dccode;

    @Schema(description = "STORERKEY")
    private String storerkey;

    @Schema(description = "ORGANIZE")
    private String organize;

    @Schema(description = "SLIPDT")
    private String slipdt;

    @Schema(description = "SLIPNO")
    private String slipno;

    @Schema(description = "SLIPLINE")
    private String slipline;

    @Schema(description = "DOCDT")
    private String docdt;

    @Schema(description = "DOCNO")
    private String docno;

    @Schema(description = "DOCTYPE")
    private String doctype;

    @Schema(description = "DOCLINE")
    private String docline;

    @Schema(description = "ORDERTYPE")
    private String ordertype;

    @Schema(description = "SOTYPE")
    private String sotype;

    @Schema(description = "SALEGROUP")
    private String salegroup;

    @Schema(description = "SALEDEPARTMENT")
    private String saledepartment;

    @Schema(description = "SALEORGANIZE")
    private String saleorganize;

    @Schema(description = "CUSTGROUP")
    private String custgroup;

    @Schema(description = "BILLTOCUSTKEY")
    private String billtocustkey;

    @Schema(description = "BILLTOCUSTNAME")
    private String billtocustname;

    @Schema(description = "TO_CUSTKEY")
    private String toCustkey;

    @Schema(description = "TO_CUSTNAME")
    private String toCustname;

    @Schema(description = "MNGPLC_ID")
    private String mngplcId;

    @Schema(description = "MNGPLC_NAME")
    private String mngplcName;

    @Schema(description = "STATUS")
    private String status;

    @Schema(description = "DELIVERYGROUP")
    private String deliverygroup;

    @Schema(description = "CARNO")
    private String carno;

    @Schema(description = "SKU")
    private String sku;

    @Schema(description = "SKUNAME")
    private String skuname;

    @Schema(description = "CHANNEL")
    private String channel;

    @Schema(description = "STORAGETYPE")
    private String storagetype;

    @Schema(description = "ORDERQTY")
    private BigDecimal orderqty;

    @Schema(description = "ORDERWEIGHT")
    private BigDecimal orderweight;

    @Schema(description = "CANCELQTY")
    private BigDecimal cancelqty;

    @Schema(description = "CONFIRMWEIGHT")
    private BigDecimal confirmweight;

    @Schema(description = "UOM")
    private String uom;

    @Schema(description = "PROCESSQTY")
    private BigDecimal processqty;

    @Schema(description = "WORKQTY")
    private BigDecimal workqty;

    @Schema(description = "INSPECTQTY")
    private BigDecimal inspectqty;

    @Schema(description = "CONFIRMQTY")
    private BigDecimal confirmqty;

    @Schema(description = "CLOSEYN")
    private String closeyn;

    @Schema(description = "DEL_YN")
    private String delYn;

    @Schema(description = "IF_AUDIT_FILE")
    private String ifAuditFile;

    @Schema(description = "IF_SEND_FILE")
    private String ifSendFile;

    @Schema(description = "ADDDATE")
    private String adddate;

    @Schema(description = "PLANT")
    private String plant;

    @Schema(description = "PLANT_DESCR")
    private String plantDescr;

    @Schema(description = "LOADPLACE")
    private String loadplace;

    @Schema(description = "ROUTE")
    private String route;

    @Schema(description = "ROUTE_DESCR")
    private String routeDescr;

    @Schema(description = "DELIVERYTYPE")
    private String deliverytype;

    @Schema(description = "SERIALYN")
    private String serialyn;

    @Schema(description = "LINE01")
    private String line01;

    @Schema(description = "BEFORESHORTAGEPLANYN")
    private String beforeshortageplanyn;

    @Schema(description = "SALECUSHRC1")
    private String salecushrc1;

    @Schema(description = "SALECUSHRC2")
    private String salecushrc2;

    @Schema(description = "SALECUSHRC3")
    private String salecushrc3;

    @Schema(description = "QTYPERBOX")
    private BigDecimal qtyperbox;

    @Schema(description = "EXCEPT_YN")
    private String exceptYn;

    @Schema(description = "TO_VATADDRESS1")
    private String toVataddress1;

    @Schema(description = "TO_ZIPCODE")
    private String toZipcode;

}