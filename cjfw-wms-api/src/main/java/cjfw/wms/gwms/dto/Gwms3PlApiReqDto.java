package cjfw.wms.gwms.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.05
 * @description : 중계 API > 국가주소연계 API 마스터 물류 송신(WM001)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.05 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "우편번호POP정보") 
public class Gwms3PlApiReqDto {

	@Schema(description = "전표일자")
	private String slipdt;             
	
	@Schema(description = "거래처코드")
	private String custkey;             

	/** serialno */
	@Schema(description = "serialno")
	private String serialno;
	
	/** storagetype */
	@Schema(description = "storagetype")
	private String storagetype;

	/** channel */
	@Schema(description = "channel")
	private String channel;

	/** docno */
	@Schema(description = "docno")
	private String docno;

	/** toCustkey */
	@Schema(description = "toCustkey")
	private String toCustkey;
	
	/** closeyn */
	@Schema(description = "closeyn")
	private String closeyn;

	/** sku */
	@Schema(description = "sku")
	private String sku;
	
    /** 상품코드 */
    @MultiSearch
    @Schema(description = "상품코드")
    private List<List<String>> skuMulti;
}
