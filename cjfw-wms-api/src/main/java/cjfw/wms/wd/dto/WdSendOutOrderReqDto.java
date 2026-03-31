package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
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
 * @date : 2025.06.19 
 * @description : 외부비축출고지시요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.02    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축출고지시 요청") 
public class WdSendOutOrderReqDto extends CommonProcedureDto {	
    /** 저장 리스트 */
    List<WdSendOutOrderResDto> saveList;
    
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
	
	/** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String organize;
    
    /** 창고 */
    @MultiSearch
    @Schema(description = "창고", nullable = false, example = "")
    private List<String> organizeMulti;
	
	/** area */
    @Schema(description = "area", nullable = false, example = "")
    private String area;

    /** 시작일자 */
    @Schema(description = "시작일자", nullable = false, example = "")
    private String fromSlipdt;
    
    /** 종료일자 */
    @Schema(description = "종료일자", nullable = false, example = "")
    private String toSlipdt;
    
    /** blno */
    @Schema(description = "blno", nullable = false, example = "")
    private String blno;
    
    /** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
    /** processtype */
    @Schema(description = "processtype", nullable = false, example = "")
    private String processtype;
    
    /** spid */
    @Schema(description = "spid", nullable = false, example = "")
    private String spid;
    
    /** 주문번호 */
    @Schema(description = "주문번호", nullable = false, example = "")
    private String docno;
    
    /** 주문번호 */
    @MultiSearch
    @Schema(description = "주문번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> docnoMulti;
    
    /** docline */
    @Schema(description = "docline", nullable = false, example = "")
    private String docline;

    /** 승인번호 */
    @Schema(description = "승인번호", nullable = false, example = "")
    private String mapkeyNo;
    
    /** 지시서유형 */
    @Schema(description = "지시서유형", nullable = false, example = "")
    private String exdcinstructtype;
    
    /** cfyn */
    @Schema(description = "cfyn", nullable = false, example = "")
    private Integer cfyn;
    
    /** 창고미발송 */
    @Schema(description = "창고미발송", nullable = false, example = "")
    private Integer send;
    
    /** 창고발송에러 */
    @Schema(description = "창고발송에러", nullable = false, example = "")
    private Integer errorsend;
    
    /** 취소조회 */
    @Schema(description = "취소조회", nullable = false, example = "")
    private Integer allCancelStatus;
    
    /** 정상오더없음 */
    @Schema(description = "정상오더없음", nullable = false, example = "")
    private Integer noNormal;
    
    /** fromcustkey */
    @Schema(description = "fromcustkey", nullable = false, example = "")
    private String fromcustkey;
    
    /** tocustkey */
    @Schema(description = "tocustkey", nullable = false, example = "")
    private String tocustkey; 
    
    /** tocustkey */
    @MultiSearch
    @Schema(description = "tocustkey", nullable = false, example = "")
    private List<String> tocustkeyMulti;
	
    /** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드", nullable = false, example = "")
    private List<List<String>> skuMulti;
    
    /** 지시서유형 */
    @Schema(description = "지시서유형", nullable = false, example = "")
    private String instructtype;
    
    //20251209 printType 추가 박진우
    /** 프린트타입 */
    @Schema(description = "프린트타입", nullable = true, example = "")
    private String printType;
    
    //20251227 docName 추가 박진우
    /** 문서이름 */
    private String docName;
    
}
