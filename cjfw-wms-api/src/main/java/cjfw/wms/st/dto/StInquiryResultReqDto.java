package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : sss (kduimux@cj.net)
 * @date : 2025.08.25
 * @description : 조사지시현황 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.25 sss (kduimux@cj.net) 생성
 *         </pre>
 */
@Data
@Schema(description = "조사지시현황 Request DTO")
public class StInquiryResultReqDto extends CommonProcedureDto { 
	/** 저장 리스트*/
    List<StInquiryResultResDto> saveList;		
	
	/** 조직 리스트 */
	@Schema(description = "조직 리스트")
	private List<String> organizeList;
	
	/** 창고구역 */ 
	@Schema(description = "창고구역")
	private String wharea;
	
	/** 존(From) */
	@Schema(description = "존(From)")
	private String fromZone;
	
	/** 존(To) */
	@Schema(description = "존(To)")
	private String toZone;
	
	/** 조사일자(From) */
	@Schema(description = "조사일자(From)")
	private String fromInquirydt;
	
	/** 조사일자(To) */
	@Schema(description = "조사일자(To)")
	private String toInquirydt;

	/** 조사일자(From) */
	@Schema(description = "조사일자(From)")
	private String dt1;

	/** 조사일자(To) */
	@Schema(description = "조사일자(To)")
	private String dt2;

	/** 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryno;
	
	/** 조사번호New */
	@Schema(description = "조사번호New")
	private String inquirynoNew;	

	/** 상태 */
	@Schema(description = "상태")
	private String status;

	/** 조사유형 */
	@Schema(description = "조사유형")
	private String inquirytype;

	/** 로케이션(From) */
	@Schema(description = "로케이션(From)")
	private String fromLoc;

	/** 로케이션(To) */
	@Schema(description = "로케이션(To)")
	private String toLoc;

    /** 조사별칭 */
    @Schema(description = "조사별칭")
    private String inquiryAlias;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품코드 */
    @Schema(description = "상품코드")
    private String fixdorganize;    
    
	/** 상품 다중OR검색 */
	@MultiSearch
	@Schema(description = "상품 다중OR검색")
	private List<List<String>> skuMulti;
	
    /** 창고(다중검색) */
    @Schema(description = "창고-다중검색")
    private List<String> organizeMulti;	
    
    /** 요청구분(1:로케이션별, 2:상품별) */
    @Schema(description = "요청구분(1:로케이션별, 2:상품별)")
    private String reqFlag;    
    
    
    /** 실사구분(0:소비기한,1:재고조사) */
    @Schema(description = "(0:소비기한,1:재고조사)")
    private String lottype;
    
    /** 조사명 */
    @Schema(description = "조사명")
    private String inquiryName;
    
    /** 금액 */
    @Schema(description = "금액")
    private String amt1;
    
    /** 금액비교 */
    @Schema(description = "금액비교")
    private String compareAmt1;
    
    /** 금액 */
    @Schema(description = "금액")
    private String amt2;
    
    /** 금액비교 */
    @Schema(description = "금액비교")
    private String compareAmt2;
    
	/** 조사번호 */
	@Schema(description = "조사번호")
	private String inquiryNameNew;

	/** 단가/금액표시 여부  */
	@Schema(description = "단가/금액표시 여부 ")
	private String viewPriceYn = "N";

}
