package cjfw.wms.kp.dto;

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
 * @date : 2025.08.07 
 * @description : 결품대응현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.07 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "결품대응현황 목록 요청") 
public class KpWdRequestCancelqtyReqDto extends CommonDto {
	/** 저장/삭제 리스트 */
	List<KpWdRequestCancelqtyResTab1Dto> saveDataList;
	/** 저장/삭제 리스트 */
	List<KpWdRequestCancelqtyResTab2Dto> saveDataTab2List;
	
	
	/** 출고일자FROM */
	@Schema(description = "출고일자FROM", nullable = false, example = "")
	private String fromDeliverydt;
	
	/** 출고일자TO */
	@Schema(description = "출고일자TO", nullable = false, example = "")
	private String toDeliverydt;
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** 창고-다중선택 */
	@Schema(description = "창고-다중선택", nullable = false, example = "4900,8774")
	private String organize;
	
    /** 창고(다중검색) */
	@MultiSearch
    @Schema(description = "창고-다중OR검색")
    private List<String> organizeMulti; 
	
	/** carno */
	@Schema(description = "carno", nullable = false, example = "")
	private String carno;
	
	/** carno(다중검색) */
	@MultiSearch
    @Schema(description = "carno-다중OR검색")
    private List<String> carnoMulti; 
	
	/** popno */
	@Schema(description = "popno", nullable = false, example = "Y")
	private String popno;
	
	/** popno(다중검색) */
	@MultiSearch
    @Schema(description = "popno-다중OR검색")
    private List<String> popnoMulti;
	
	/** status */
	@Schema(description = "status", nullable = false, example = "")
	private String status;
	
	/** storagetype */
	@Schema(description = "storagetype", nullable = false, example = "")
	private String storagetype;
	
	/** channel */
	@Schema(description = "channel", nullable = false, example = "Y")
	private String channel;
	
	
	/** 상품코드 */
	@Schema(description = "상품코드", nullable = false, example = "")
	private String sku;
	
	/** 상품(다중OR검색) */
	@MultiSearch
    @Schema(description = "상품-다중OR검색")
    private List<List<String>> skuMulti;    	
	
	/** toCustkey */
	@Schema(description = "toCustkey", nullable = false, example = "")
	private String toCustkey;
		
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti;
	
	
	
}
