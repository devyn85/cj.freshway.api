package cjfw.wms.wd.dto;

import java.util.List;

import cjfw.wms.common.annotation.MultiSearch;
import cjfw.wms.common.extend.CommonProcedureDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2026.02.19 
 * @description : 일배분류서출력 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.02.19 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일배분류서출력 요청") 
public class WdDeliveryLabelDailyReqDto extends CommonProcedureDto {
	/** 관리처코드 */
	@Schema(description = "관리처코드", nullable = false, example = "")
	private String toCustkey;
	
	/** 관리처코드(다중OR검색) */
	@MultiSearch
    @Schema(description = "관리처코드-다중OR검색")
    private List<List<String>> toCustkeyMulti; 
	
	/** fixdccode */
	@Schema(description = "fixdccode", nullable = false, example = "")
	private String fixdccode;
	
	/** dccode */
	@Schema(description = "dccode", nullable = false, example = "")
	private String dccode;
	
	/** 출고일자 */
	@Schema(description = "출고일자", nullable = false, example = "")
	private String slipdt;
	
	/**
     * 차량번호
     */
    @Schema(description = "차량번호", example = "PL001")
    private String carno;
    
    /** 차량번호(다중검색) */
	@MultiSearch
    @Schema(description = "차량번호-다중OR검색")
    private List<String> carnoMulti;
	
	/** deliverygroup */
	@Schema(description = "deliverygroup", nullable = false, example = "")
	private String deliverygroup;
	
	/** fromDccode */
	@Schema(description = "fromDccode", nullable = false, example = "")
	private String fromDccode;
	
	/** toDccode */
	@Schema(description = "toDccode", nullable = false, example = "")
	private String toDccode;
	

	/** 출력기준 */
	@Schema(description = "출력기준", nullable = false, example = "0")
	private String searchtype;
	
	/** 출력순서 */
	@Schema(description = "출력순서", nullable = false, example = "")
	private String orderbyPick;
	
		
}
