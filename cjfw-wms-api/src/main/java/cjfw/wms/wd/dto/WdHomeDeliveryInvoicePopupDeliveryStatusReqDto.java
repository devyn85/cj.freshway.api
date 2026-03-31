package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.05.21 
 * @description : 출고진행현황 팝업 요청 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.21 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "출고진행현황 팝업 요청") 
public class WdHomeDeliveryInvoicePopupDeliveryStatusReqDto extends CommonDto {
	
	/** invoiceno */
	@Schema(description = "invoiceno", nullable = false, example = "")
	private String invoiceno;
	
	/** docno */
	@Schema(description = "docno", nullable = false, example = "")
	private String docno;
	
	/** docdt */
	@Schema(description = "docdt", nullable = false, example = "")
	private String docdt;
	
}
