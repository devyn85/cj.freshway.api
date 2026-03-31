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
 * @date : 2025.06.23 
 * @description : 배송라벨삭제현황 목록 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "배송라벨삭제현황 목록 요청") 
public class WdDeliveryLabelDelReqDto extends CommonDto {
	

	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** 출고일자*/
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	
	/** 관리처코드*/
	@Schema(description = "toCustkey", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	/** 주문번호 */
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
	

	/** 삭제여부 */
	@Schema(description = "삭제여부", nullable = false, example = "")
	private String deletelabelstatus;
	
}
