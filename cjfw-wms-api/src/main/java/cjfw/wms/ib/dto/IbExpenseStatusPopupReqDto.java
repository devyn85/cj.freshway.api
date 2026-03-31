package cjfw.wms.ib.dto;

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
 * @description : 외부창고 원가관리리포트 팝업 처리 요청 DTO 
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
@Schema(description = "외부창고 원가관리리포트 팝업 처리 요청") 
public class IbExpenseStatusPopupReqDto extends CommonProcedureDto {	

    /** ISSUE_DATE from */
	@Schema(description = "ISSUE_DATE from", nullable = true, example = "")
	private String issuedateFrom;

	/** ISSUE_DATE to*/
    @Schema(description = "ISSUE_DATE to", nullable = true, example = "")
    private String issuedateTo;

    /** SHIPPING_DATE from */
    @Schema(description = "SHIPPING_DATE from", nullable = true, example = "")
    private String shippingdateFrom;
	
	/** SHIPPING_DATE to */
    @Schema(description = "SHIPPING_DATE to", nullable = true, example = "")
    private String shippingdateTo;	

    /** EXPIRY_DATE from */
    @Schema(description = "EXPIRY_DATE from", nullable = true, example = "")
    private String expirydateFrom;
    
    /** EXPIRY_DATE to */
    @Schema(description = "EXPIRY_DATE to", nullable = true, example = "")
    private String expirydateTo;
    
    /** ARRIVAL_DATE from */
    @Schema(description = "ARRIVAL_DATE from", nullable = true, example = "")
    private String arrivaldateFrom;
    
    /** ARRIVAL_DATE to */
    @Schema(description = "ARRIVAL_DATE to", nullable = true, example = "")
    private String arrivaldateTo;
    
    /** REG_DATE from */
    @Schema(description = "REG_DATE from", nullable = true, example = "")
    private String regdateFrom;
    
    /** REG_DATE to */
    @Schema(description = "REG_DATE to", nullable = true, example = "")
    private String regdateTo;
    
    /** MOD_DATE from */
    @Schema(description = "MOD_DATE from", nullable = true, example = "")
    private String moddateFrom;
    
    /** MOD_DATE to */
    @Schema(description = "MOD_DATE to", nullable = true, example = "")
    private String moddateTo;
    
    /** KEY_NO */
    @Schema(description = "KEY_NO", nullable = true, example = "")
    private String keyno;
    
    /** SUPPLIER_NAME */
    @Schema(description = "SUPPLIER_NAME", nullable = true, example = "")
    private String suppliername;
    
    /** BUSINESS_TYPE_NAME */
    @Schema(description = "BUSINESS_TYPE_NAME", nullable = true, example = "")
    private String businesstypename;
    
    /** ERP_PO_ID */
    @Schema(description = "ERP_PO_ID", nullable = true, example = "")
    private String erppoid;
    
    /** HBL_NO */
    @Schema(description = "HBL_NO", nullable = true, example = "")
    private String hblno;
    
    /** Item Code */
    @Schema(description = "Item Code", nullable = true, example = "")
    private String itemcode;
    
    /** Item Description */
    @Schema(description = "Item Description", nullable = true, example = "")
    private String itemdescription;
    
    /** ERP_PO_NO*/
    @Schema(description = "ERP_PO_NO", nullable = true, example = "")
    private String erppono;
    
    /** HOUSE_BL_NO */
    @Schema(description = "HOUSE_BL_NO", nullable = true, example = "")
    private String houseblno;
    
    /** Posting Date */
    @Schema(description = "Posting Date", nullable = true, example = "")
    private String postingdate;
    
    /** 조회기간 유형 */
    @Schema(description = "조회기간 유형", nullable = true, example = "")
    private String searchDateCategory;
    
}
