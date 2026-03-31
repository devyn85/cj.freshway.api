package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.12.09
 * @description : 퀵배송상세 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.09 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Schema(description = "퀵배송상세 Request DTO")
@Data
public class WdQuickMonCloseReqDto extends CommonDto {
	/** API 조회 결과 */
	List<WdQuickResAPI02Dto> resultList;
	
	/** 저장 리스트 - 그리드*/
    List<WdQuickMonCloseResDto> saveList;	
	
	/** 저장 리스트 - api */
    List<WdQuickResAPI02Dto> saveList01;	
    
    

    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;
    
    /** 기간구분(1:정상월,2:접수일) */
    @Schema(description = "기간구분")
    private String dateFlag;    
    
    /** 정산월 */
    @Schema(description = "정산월")
    private String sttlYm;    
    
	/** 일자(From) */
	@Schema(description = "일자(From)")
	private String dt1;

	/** 일자(To) */
	@Schema(description = "일자(To)")
	private String dt2;
	

    /** 진행상태 */
    @Schema(description = "진행상태")
    private String status;	
    
    /** 퀵주문번호 */
    @Schema(description = "퀵주문번호")
    private String quickDocno;    

    /** 센터접수번호 다중검색 */
    @MultiSearch
    @Schema(description = "센터접수번호 다중검색")
    private List<String> quickDocnoMulti;
    
	/** 센터접수번호 */
	@Schema(description = "센터접수번호")
	private String rcptNo;    
	
    /** 총건수 */
    @Schema(description = "총건수")
    private BigDecimal totalRecord;
    
    /** 총페이지 */
    @Schema(description = "총페이지")
    private BigDecimal totalPage;    
    
    /** 현재페이지 */
    @Schema(description = "현재페이지")
    private BigDecimal currentPage;
    
    /** 페이지당 건수 */
    @Schema(description = "페이지당 건수")
    private BigDecimal limit;	
    
	/** 퀵사용자id */
	@Schema(description = "퀵사용자id")
	private String quickUserId; 
	
    /** 처리건수 */	
    @Schema(description = "처리건수")
    private int processCnt; 
    
	/** 마감여부 */
	@Schema(description = "마감여부")
	private String isClosedYn; 
	
	/** 테스트여부 */
	@Schema(description = "테스트여부")
	private String testYn; 

}
