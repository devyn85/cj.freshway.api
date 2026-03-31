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
 * @date : 2025.07.03 
 * @description : 이력피킹현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.03 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력피킹현황 목록 요청") 
public class WdPickingListSNReqDto extends CommonDto {
	

	/** 주문사유 */
	@Schema(description = "주문사유", nullable = false, example = "")
	private String ordertype;

	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 

	/** 저장조건 */
	@Schema(description = "저장조건", nullable = false, example = "")
	private String storagetype;
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromSlipdt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toSlipdt;
	
	/** skugroup */
	@Schema(description = "skugroup", nullable = false, example = "")
	private String skugroup;
	
	/** 물류센터 */
	@Schema(description = "물류센터", nullable = false, example = "")
	private String dccode;

	/**
     * 물류센터
     */
    @Schema(description = "물류센터", example = "C100")
    private String fixdccode;
    

    /** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 
	
	/** workdccode */
	@Schema(description = "workdccode", nullable = false, example = "")
	private String workdccode;
	
	/** plant */
	@Schema(description = "plant", nullable = false, example = "Y")
	private String plant;
	
	/** 주문번호-다중선택 */
	@Schema(description = "주문번호-다중선택", nullable = false, example = "4900,8774")
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
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
		
	
}
