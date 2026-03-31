package cjfw.wms.st.dto;

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
 * @date : 2025.06.16 
 * @description : 외부비축소비기한변경 조회 요청 DTO 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.16    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "외부비축BL내재고이관 조회 요청") 
public class StAdjustmentTRBLExDCReqDto extends CommonProcedureDto {	
    /** 저장 리스트 */
    List<StAdjustmentTRBLExDCResDto> saveList;
    
    /** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
	
	/** 창고 */
	@Schema(description = "창고", nullable = false, example = "")
	private String organize;
	
	/** 창고-다중검색 */
    @MultiSearch
    @Schema(description = "창고-다중검색", nullable = false, example = "")
    private List<String> organizeMulti;
	
	/** 작업구역 */
	@Schema(description = "작업구역", nullable = false, example = "")
	private String area;
	
	/** 상품코드 */
    @Schema(description = "상품코드", nullable = false, example = "")
    private String sku;
    
    /** 재고재고유형속성 */
    @Schema(description = "재고유형", nullable = false, example = "")
    private String stocktype;
    
    /** 재고속성 */
    @Schema(description = "재고속성", nullable = false, example = "")
    private String stockgrade;
    
    /** 이력번호 */
    @Schema(description = "이력번호", nullable = false, example = "")
    private String serialno;
    
    /** B/L 번호 */
    @Schema(description = "B/L 번호", nullable = false, example = "")
    private String blno;
    
    /** 계약업체 */
    @Schema(description = "계약업체", nullable = false, example = "")
    private String contractcompany; 
    
	/** 상품분류 */
	@Schema(description = "상품분류", nullable = false, example = "")
	private String skugroup;
	
	/** 기준일 */
	@Schema(description = "기준일", nullable = false, example = "")
	private String lottable01;
	
	/** 이력번호 등 조건여부 */
	@Schema(description = "이력번호 등 조건여부", nullable = false, example = "")
	private String searchserial;	
	
    /** 조정일자 */
	@Schema(description = "조정일자", nullable = false, example = "")
	private String taskdtAj;
	    
    /** 귀속부서 */
    @Schema(description = "귀속부서", nullable = false, example = "")
    private String costCd;
    
    /** 거래처 */
    @Schema(description = "거래처", nullable = false, example = "")
    private String formCustkey;
    
    /** 조정일자 */
    @Schema(description = "조정일자", nullable = false, example = "")
    private String docdt;
    
    /** 창고 */
    @Schema(description = "창고", nullable = false, example = "")
    private String[] organizeList;

}
