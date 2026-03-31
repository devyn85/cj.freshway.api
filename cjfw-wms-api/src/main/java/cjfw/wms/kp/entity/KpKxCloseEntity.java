package cjfw.wms.kp.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.12.03
 * @description : DM_DOCUMENT_D Entity for delOrderReset update
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpKxCloseEntity extends CommonDto {
	
	/** 전표번호 */
	@Schema
    private String docno;
	
	/** 전표순번 */
	@Schema
    private String docline;
	
	/** 물류센터 */
	@Schema
    private String dccode;
	
	/** 삭제여부 */
	@Schema
	private String delYn;
	
	/** 창고조정수량 */
	@Schema
    private String storeradjustqty;
	
	/** 주문조정수량 */
	@Schema
    private String orderadjustqty;
	
    /** 송수신 구분 (DM_DOCUMENT_D) */
    @Schema(description = "송수신 구분 (DM_DOCUMENT_D)")
    private String dmD;

    /** 송수신 구분 (DM_SENDDATA) */
    @Schema(description = "송수신 구분 (DM_SENDDATA)")
    private String dmSndd;

    /** 송수신 구분 (DM_DOCUMENT_H) */
    @Schema(description = "송수신 구분 (DM_DOCUMENT_H)")
    private String dmH;

    /** 송수신 구분 (IF_ST_STOCK_SERIALINFO_R) */
    @Schema(description = "송수신 구분 (IF_ST_STOCK_SERIALINFO_R)")
    private String ifStR;

    /** 송수신 구분 (IF_ST_STOCK_SERIALINFO_S) */
    @Schema(description = "송수신 구분 (IF_ST_STOCK_SERIALINFO_S)")
    private String ifStS;

    /** 송수신 구분 (IF_SENDRESULT) */
    @Schema(description = "송수신 구분 (IF_SENDRESULT)")
    private String ifSndRst;

    /** IF 상태 */
    @Schema(description = "IF 상태")
    private String ifFlag;
    
    /** 시리얼키 (NEXTVAL) */
    @Schema(description = "시리얼키 (NEXTVAL)")
    private BigDecimal serialkey;
    
    /** 사유내용 */
	@Schema(description = "사유내용")
	private String memo;    
    
}
