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
@Schema(description = "외부비축소비기한변경 조회 요청") 
public class StConvertLotExDCReqDto extends CommonProcedureDto {	
    /** 저장 리스트 */
    List<StConvertLotExDCResDto> saveList;
    
    /** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;
	
	/** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;
	
	/** 창고 */
	@Schema(description = "창고", nullable = false, example = "")
	private String organize;
	
	/** 작업구역 */
	@Schema(description = "작업구역", nullable = false, example = "")
	private String area;
	
	/** 상품분류 */
	@Schema(description = "상품분류", nullable = false, example = "")
	private String skugroup;
	
	/** 기준일 */
	@Schema(description = "기준일", nullable = false, example = "")
	private String lottable01;
	
	/** 이력번호 등 조건여부 */
	@Schema(description = "이력번호 등 조건여부", nullable = false, example = "")
	private String searchserial;	
	
	/** B/L 번호 */
	@Schema(description = "B/L 번호", nullable = false, example = "")
	private String blno;
	
	/** BL 번호 */
    @MultiSearch
    @Schema(description = "BL 번호(다중 – 콤마/개행 구분)", nullable = false, example = "")
    private List<String> blnoMulti;
    
	/** 이력번호 */
	@Schema(description = "이력번호", nullable = false, example = "")
	private String serialno;
	
	/** 재고속성 */
	@Schema(description = "재고속성", nullable = false, example = "")
	private String stockgrade;
	
	/** 계약업체 */
	@Schema(description = "계약업체", nullable = false, example = "")
	private String contractcompany;	
	
	/** 계약업체 */
	@MultiSearch
    @Schema(description = "계약업체", nullable = false, example = "")
    private List<String> contractcompanyMulti;
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
    @MultiSearch
    @Schema(description = "상품-다중OR검색")
	private List<List<String>> skuMulti;
	
    /** 정렬방식 */
	@Schema(description = "정렬방식", nullable = false, example = "")
	private String sortkey;
	
	/** 기준일(유통,제조) */
    @Schema(description = "기준일(유통,제조)", nullable = false, example = "")
    private String inLottable01;
    
    /** 사유코드 */
    @Schema(description = "사유코드", nullable = false, example = "")
    private String inReasoncode;
    
    /** 사유메시지 */
    @Schema(description = "사유메시지", nullable = false, example = "")
    private String inReasonmsg;
}
