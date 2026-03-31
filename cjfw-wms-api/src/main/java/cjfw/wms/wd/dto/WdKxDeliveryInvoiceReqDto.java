package cjfw.wms.wd.dto;

import java.math.BigDecimal;
import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JiHoPark
 * @date : 2025.12.26
 * @description : 택배송장발행(온라인) request dto
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.26 JiHoPark  생성 </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "택배송장발행(온라인) request dto")
public class WdKxDeliveryInvoiceReqDto extends CommonProcedureDto {
	
	/** 저장 리스트 */
    List<WdKxDeliveryInvoiceResDto> saveList;	
    	
	/** 레포트 리스트  */
	List<WdKxDeliveryInvoicePrintResDto> reportList;	

	/**시리얼키*/
	@Schema(description = "시리얼키")
	private String serialkey;
	
	/**시리얼키-다중OR검색*/
	@MultiSearch
	@Schema(description = "시리얼키-다중OR검색")
	private List<List<String>> serialkeyMulti;
	
	/** 모니터링 그룹 상세 update */
	@Schema(description = "택배기준 update")
	private List<WdKxDeliveryInvoiceBoxResDto> insertMaster5;

	/** 모니터링 그룹 상세 update */
	@Schema(description = "택배기준 update")
	private List<WdKxDeliveryInvoiceBoxResDto> updateMaster5;	

	/************************  주문 *********************/
	/**물류센터*/
	@Schema(description = "물류센터")
	private String dccode;

	/**요청일자*/
	@Schema(description = "요청일자")
	private String reqDate;

	/**제외사유*/
	@Schema(description = "제외사유")
	private String exceptReasonCd;
	
	/**접수일자*/
	@Schema(description = "접수일자")
	private String docdt;

	/**주문고유번호*/
	@Schema(description = "주문고유번호")
	private String docno;
	
	/**주문고유번호-다중선택*/
	@MultiSearch
	@Schema(description = "주문고유번호-다중선택")
	private List<List<String>> docnoMulti;

	/**운송장번호*/
	@Schema(description = "운송장번호")
	private String invoiceno;
	
	/**운송장번호-다중선택*/
	@MultiSearch
	@Schema(description = "운송장번호-다중선택")
	private List<String> invoicenoMulti;
	

	/**접수구분*/
	@Schema(description = "접수구분")
	private String ordertype;

	/**배송서비스구분*/
	@Schema(description = "배송서비스구분")
	private String deliverySvcType;
	
	/**배송서비스구분Tab*/
	@Schema(description = "배송서비스구분Tab")
	private String deliverySvcTypeTab;

	/**접수시간대*/
	@Schema(description = "접수시간대")
	private String rcptHourType;

	/**상품*/
	@Schema(description = "상품")
	private String sku;
	
    /** 상품(다중선택) */
    @MultiSearch
    @Schema(description = "상품(다중OR검색)")
    private List<List<String>> skuMulti;  		
	
	/**고객주문번호*/
	@Schema(description = "고객주문번호")
	private String empCustDocno;
																					
	/**고객주문번호-다중검색*/
	@MultiSearch
	@Schema(description = "고객주문번호-다중검색")
	private List<String> empCustDocnoMulti;
	
	/**판매사이트코드*/
	@Schema(description = "판매사이트코드")
	private String empCustkey;
	
	/**판매사이트코드-다중검색*/
	@MultiSearch
	@Schema(description = "판매사이트코드-다중검색")
	private List<String> empCustkeyMulti;


	/************************  택배기준 *********************/
	/**박스명*/
	@Schema(description = "박스명")
	private String boxnm;

	/**저장조건*/
	@Schema(description = "저장조건")
	private String storagetype;

	/**사용여부*/
	@Schema(description = "사용여부")
	private String useYn;

	
	/**송화인 전화번호*/
	@Schema(description = "송화인 전화번호")
	private String sendrTelNo;
	
	/**송화인 휴대폰번호*/
	@Schema(description = "송화인 휴대폰번호")
	private String sendrCellNo;
	
	/**토큰*/
	@Schema(description = "토큰")
	private String tokenNum;
	
	/**토큰만료일시*/
	@Schema(description = "토큰만료일시")
	private String tokenExprtnDtm;
	
    /** 보안토큰 */
    @Schema(description = "보안토큰")
    private String accessToken;	
    
    /** 조회결과코드 */
    @Schema(description = "조회결과코드")
    private String resultCd;	
    
    /** 조회결과 */
    @Schema(description = "조회결과")
    private String resultDetail;	

    
	/** usePgm */
	@Schema(description = "usePgm")
	private String usePgm;
	
	/* 출력명칭 */
	@Schema(description = "출력명칭")
	private String prtNm;
	
	/**시리얼키Emp*/
	@Schema(description = "시리얼키Emp")
	private BigDecimal serialkeyEmp;
	
	
    /**
     * prdOrd1
     */
    @Schema(description = "prdOrd1", example = "")
    private String prdOrd1;
    
    /**
     * prdOrd2
     */
    @Schema(description = "prdOrd2", example = "")
    private String prdOrd2;
    
    /**
     * prdOrd3
     */
    @Schema(description = "prdOrd3", example = "")
    private String prdOrd3;
    
    /**
     * prdOrd4
     */
    @Schema(description = "prdOrd4", example = "")
    private String prdOrd4;
    
    /**
     * prdOrd5
     */
    @Schema(description = "prdOrd5", example = "")
    private String prdOrd5;
    
    /**
     * prdOrd6
     */
    @Schema(description = "prdOrd6", example = "")
    private String prdOrd6;
    
    /**
     * prdOrd7
     */
    @Schema(description = "prdOrd7", example = "")
    private String prdOrd7;
    
    /**
     * prdOrd8
     */
    @Schema(description = "prdOrd8", example = "")
    private String prdOrd8;
    
	/**상태(00이면 10으로 치환)*/
	@Schema(description = "상태(00이면 10으로 치환)")
	private String status;
	
	/**상태-다중선택*/
	@MultiSearch
	@Schema(description = "상태-다중선택")
	private List<String> statusMulti;
	
	/**재고여부*/
	@Schema(description = "재고여부")
	private String stockYn;	
	
    /** 전체건수 */	
    @Schema(description = "전체건수")
    private int totalCnt;	 
	
    /** 처리건수 */	
    @Schema(description = "처리건수")
    private int processCnt;  
    
}
