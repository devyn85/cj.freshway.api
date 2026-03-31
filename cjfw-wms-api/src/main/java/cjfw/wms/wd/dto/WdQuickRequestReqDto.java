package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵접수(VSR)및처리 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "퀵접수(VSR)및처리 Request DTO")
public class WdQuickRequestReqDto extends CommonProcedureDto {
	
	/** 저장 리스트 - 접수처리*/
    List<WdQuickRequestResDto> saveList01;	
    
	/** 저장 리스트 - 집하지*/
    List<WdQuickRequestResVOCDto> saveList02;	  
    
    /** 저장 리스트 - 도착지*/
    List<WdQuickRequestResVOCDto> saveList03;	
    
    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey; 
    
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;	    
    
    /** 물류센터 - 그리드용*/
    @Schema(description = "물류센터")
    private String dccode;    
    
    /** 시리얼키 (NEXTVAL) */
    @Schema(description = "시리얼키 (NEXTVAL)")
    private String serialkey;    
    
	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;

	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;	    
	
	/** 센터접수번호 */
	@Schema(description = "센터접수번호")
	private String rcptNo;
	
	/** 요청구분(1:집하지, 2:고객) */
	@Schema(description = "요청구분(1:집하지, 2:고객)")
	private String reqFlag;
	
    /** 귀책부서 */
    @Schema(description = "귀책부서")
    private String respDept;

    /** 귀책사유 */
    @Schema(description = "귀책사유")
    private String respReason;

    /** 귀책담당자 */
    @Schema(description = "귀책담당자")
    private String respEmp;

    /** 전달사항 */
    @Schema(description = "전달사항")
    private String memo;
	
    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String serialNumbe; 

    /** 긴급여부 */
    @Schema(description = "긴급여부")
    private String urgentYn;

    /** 진행상태 */
    @Schema(description = "진행상태")
    private String status;

    /** VSR유형 */
    @Schema(description = "VSR유형")
    private String vsrtype;

    /** VOC번호 */
    @Schema(description = "VOC번호")
    private String vocno;
    
    /** VOC번호-다중검색 */
    @MultiSearch
    @Schema(description = "VOC번호 다중검색")
    private String vocnoMulti;
    
    /** 계약처 */
    @MultiSearch
    @Schema(description = "계약처")
    private String contractcustkey;    

    /** 계약처 다중검색 */
    @MultiSearch
    @Schema(description = "계약처 다중검색")
    private List<String> contractcustkeyMulti;
    

    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;    

    /** 센터접수번호 다중검색 */
    @MultiSearch
    @Schema(description = "센터접수번호 다중검색")
    private List<String> quickDocnoMulti;

    /** vsr검색구분(1:VOC,2:수기) */
    @MultiSearch
    @Schema(description = "vsr검색구분(1:VOC,2:수기)")
    private String vsrtypeFlag;    
    
    /** 배송방법 */
    @Schema(description = "배송방법")
    private String deliverytype;

    /** 배송수단 */
    @Schema(description = "배송수단")
    private String deliveryMethod;

    /** 배송선택 */
    @Schema(description = "배송선택")
    private String deliveryOption;

    /** 물품종류 */
    @Schema(description = "물품종류")
    private String articleType;

    /** 지급구분 */
    @Schema(description = "지급구분")
    private String payType;
    
    /** 예약일시 */
    @Schema(description = "예약일시")
    private String reservedate;    

    /** 예약일 */
    @Schema(description = "예약일")
    private String reservedate1;
    
    /** 예약시분 */
    @Schema(description = "예약시분")
    private String reservedate2;   
    
    /** 비고 */
    @Schema(description = "비고")
    private String rmk;
    
	/** 테스트 PREFIX */
	@Schema(description = "테스트 PREFIX ")
	private String testPrefix;     
   
}
