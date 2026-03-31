package cjfw.wms.st.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.09 
 * @description : 재고감모현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "재고감모현황 목록 요청") 
public class StAdjustResultReqDto extends CommonDto {
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";

	
	/*
	 * StAdjustResultGetDataDetailListResDto 쿼리에 사용(메인쿼리)
	 */
	
    /** 물류센터 */
    @Schema(description = "물류센터")
    private String fixdccode;		

	/** 물류센터 */
    @Schema(description = "물류센터")
    private String dccode;

    /** 고객사 */
    @Schema(description = "고객사")
    private String storerkey;

    /** 주문일자 */
    @Schema(description = "주문일자")
    private String docdt;
    
    /** 주문번호 */
    @Schema(description = "주문번호")
    private String docno;

    /** 상품코드 */
    @Schema(description = "상품코드")
    private String sku;
    
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;	    
    
    /** 발생사유 */
    @Schema(description = "발생사유")
    private String reasoncode;

	/** 귀책 */
	@Schema(description = "귀책")
	private String other01;
	
	/** 물류귀책배부 */
	@Schema(description = "물류귀책배부")
	private String other05;
	
	/** 이력번호 */
	@Schema(description = "이력번호")
	private String serialno;
	
	/** 증감여부 */
	@Schema(description = "증감여부")
	private String iotype;
	
	/** 재고위치 */
	@Schema(description = "재고위치")
	private String stocktype;
	
	/** B/L번호 */
	@Schema(description = "B/L번호")
	private String blno;
	
	/** 계약업체 */
	@Schema(description = "계약업체")
	private String contractcompany;
	
    /** 거래처(다중OR검색) */
	@MultiSearch
    @Schema(description = "거래처-다중OR검색")
    private List<List<String>> contractcompanyMulti;  	

	/** SERIAL정보조회여부 */
	@Schema(description = "SERIAL정보조회여부")
	private String searchserial;
	
	
	
	
	/*
	 * StAdjustResultGetDataHeaderListResDto 쿼리에 사용
	 */
	
	/** 조정일자FROM */
	@Schema(description = "조정일자FROM")
	private String fromTrandt;
	
	/** 조정일자TO */
	@Schema(description = "조정일자TO")
	private String toTrandt;
		
	
	
	
}
