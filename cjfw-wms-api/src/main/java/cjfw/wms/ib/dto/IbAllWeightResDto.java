package cjfw.wms.ib.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.12
 * @description : 센터별물동량 정산 Master Response DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.12 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "센터별물동량 정산 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IbAllWeightResDto {
    /**
     * FROM_STTL_BASE_DATE
     */
    @Schema(description = "FROM_STTL_BASE_DATE")
    private String fromSttlBaseDate;
    private String orgFromSttlBaseDate;

    /**
     * TO_STTL_BASE_DATE
     */
    @Schema(description = "TO_STTL_BASE_DATE")
    private String toSttlBaseDate;
    private String orgToSttlBaseDate;

    /**
     * STTL_YM
     */
    @Schema(description = "STTL_YM")
    private String sttlYm;
    private String orgSttlYm;

    /**
     * DCCODE
     */
    @Schema(description = "DCCODE")
    private String dccode;
    private String orgDccode;

    /**
     * DCNAME
     */
    @Schema(description = "DCNAME")
    private String dcname;
    private String orgDcname;

    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY")
    private String storerkey;
    private String orgStorerkey;

    /**
     * CUSTKEY
     */
    @Schema(description = "CUSTKEY")
    private String custkey;
    private String orgCustkey;

    /**
     * LCL_CD
     */
    @Schema(description = "LCL_CD")
    private String lclCd;
    private String lclCdNm;
    private String orgLclCd;

    /**
     * MCL_CD
     */
    @Schema(description = "MCL_CD")
    private String mclCd;
    private String mclCdNm;
    private String orgMclCd;

    /**
     * STORAGETYPE
     */
    @Schema(description = "STORAGETYPE")
    private String storagetype;
    private String storagetypeNm;
    private String orgStoragetype;

    /**
     * PRICE_CL
     */
    @Schema(description = "PRICE_CL")
    private String priceCl;
    private String priceClNm;
    private String orgPriceCl;

    /**
     * PRICE
     */
    @Schema(description = "PRICE")
    private String price;

    /**
     * l
     */
    @Schema(description = "QTY")
    private String qty;

    /**
     * UOM_STD
     */
    @Schema(description = "UOM_STD")
    private String uomStd;
    private String uomStdNm;

    /**
     * UOM_QTY
     */
    @Schema(description = "UOM_QTY")
    private String uomQty;

    /**
     * DC_MERGE
     */
    @Schema(description = "DC_MERGE")
    private String dcMerge;

    /**
     * RMK
     */
    @Schema(description = "RMK")
    private String rmk;

    /**
     * ADDDATE
     */
    @Schema(description = "ADDDATE")
    private String adddate;

    /**
     * EDITDATE
     */
    @Schema(description = "EDITDATE")
    private String editdate;

    /**
     * ADDWHO
     */
    @Schema(description = "ADDWHO")
    private String addwho;

    /**
     * EDITWHO
     */
    @Schema(description = "EDITWHO")
    private String editwho;

    /**
     * CUSTNAME
     */
    @Schema(description = "CUSTNAME")
    private String custname;

    /**
     * amt
     */
    @Schema(description = "amt")
    private String amt;


	@Schema(description = "행상태", example = "I")
	private String rowStatus;

	@Schema(description = "groupId", example = "I")
	private String groupId;

	@Schema(description = "groupName", example = "I")
	private String groupName;

	@Schema(description = "viewName", example = "I")
	private String viewName;

    /** * 최종변경자 이름 */
	@Schema(description = "* 최종변경자 이름")
	private String addwhoName;

    /** * 최종변경자 이름 */
	@Schema(description = "* 최종변경자 이름")
	private String editwhoName;

    /*upperId*/
	@Schema(description = "upperId")
	private String upperId;

    /*id*/
	@Schema(description = "id")
	private String id;

    /*level*/
	@Schema(description = "level")
	private String level;

    	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
	
    /**
     * 금액
     */
    @Schema(description = "amount")
    private BigDecimal amount;	
    
    /**
     * ppm
     */
    @Schema(description = "ppm")
    private BigDecimal ppm;	   
    
    /**
     * 분담율
     */
    @Schema(description = "분담율")
    private BigDecimal costAllocRate;	  
    
}
