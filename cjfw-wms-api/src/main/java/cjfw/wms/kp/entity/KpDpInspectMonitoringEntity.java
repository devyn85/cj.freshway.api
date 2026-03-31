package cjfw.wms.kp.entity;

import java.util.Date;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : (자동생성)
 * @date        : 2025.12.18
 * @description : 일배입고검수 row별 메세지(저장) Entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일배입고검수 row별 메세지(저장) Entity")
public class KpDpInspectMonitoringEntity extends CommonProcedureDto {
    @Schema(description = "일련번호")
    private Long serialkey;

    @Schema(description = "메세지 ROW")
    private Integer msgrow;

    @Schema(description = "센터코드")
    private String storerkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "입고처코드")
    private String custkey;

    @Schema(description = "전표일자")
    private String slipdt;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "메세지 메모")
    private String msgMemo;

    @Schema(description = "등록일자")
    private Date adddate;

    @Schema(description = "수정일자")
    private Date editdate;

    @Schema(description = "등록자")
    private String addwho;

    @Schema(description = "수정자")
    private String editwho;
    
    @Schema(description = "title")
    private String title;
    
    @Schema(description = "제목")
    private String sendtitle;
    
    @Schema(description = "내용")
    private String sendmessage;
    
    @Schema(description = "제목")
    private String sendtitle2;
    
    @Schema(description = "내용")
    private String sendmessage2;
    
    @Schema(description = "완료일자")
    private String comdt;
    
    @Schema(description = "ndate")
    private String ndate;
    
    @Schema(description = "smscnt")
    private String smscnt;
    
    @Schema(description = "custname")
    private String custname;
    

    @Schema(description = "수신자명")
    private String receivename;

    @Schema(description = "수신이메일")
    private String receiveemail;
    
    @Schema(description = "subtitle")
    private String subtitle;
    
    @Schema(description = "smssend")
    private String smssend;
    
    @Schema(description = "sms1flag")
    private String sms1flag;
    
    @Schema(description = "email1Yn")
    private String email1Yn;
    
    @Schema(description = "cnts")
    private String cnts;
    
    @Schema(description = "sendrEmailAddr")
    private String sendrEmailAddr;
    
    @Schema(description = "refEmailAdd")
    private String refEmailAdd;
    
    @Schema(description = "rcvrNm")
    private String rcvrNm;
    
    @Schema(description = "rcvrEmailAddr")
    private String rcvrEmailAddr;
    
	/** fromcustkey */
	@Schema(description = "fromcustkey", nullable = false, example = "")
	private String fromcustkey;
	
    /** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti; 
}