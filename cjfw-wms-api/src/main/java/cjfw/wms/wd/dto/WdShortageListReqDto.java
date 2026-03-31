package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.03.05 
 * @description : 출고결품현황 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.05 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고결품현황 요청") 
public class WdShortageListReqDto extends CommonDto {
	
	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    
	/** organize */
	@Schema(description = "organize", nullable = false, example = "")
	private String organize;			

    /** organize(다중검색) */
	@MultiSearch
    @Schema(description = "organize-다중OR검색")
    private List<String> organizeMulti;
    
	/** fromSlipdt */
	@Schema(description = "fromSlipdt", nullable = false, example = "")
	private String fromSlipdt;
	
	/** toSlipdt */
	@Schema(description = "toSlipdt", nullable = false, example = "")
	private String toSlipdt;

	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
    /** 주문번호(다중검색) */
	@MultiSearch
    @Schema(description = "주문번호-다중OR검색")
    private List<String> docnoMulti;

	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
    /** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** 저장유무 */
	@Schema(description = "저장유무", nullable = false, example = "")
	private String channel;
	
	/** 담당MD명(SO) */
	@Schema(description = "담당MD명(SO)", nullable = false, example = "")
	private String somdname;
	
	/** 조정사유 */
	@Schema(description = "조정사유", nullable = false, example = "")
	private String reason;
	
		
}
