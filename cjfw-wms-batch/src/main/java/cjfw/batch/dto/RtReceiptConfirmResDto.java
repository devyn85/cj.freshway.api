package cjfw.batch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.11
 * @description : MS_CUST(거래처마스터) 주소정제 DTO (배치용)  
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.11 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */

@Data
@Schema(description = "반품확정처리")
public class RtReceiptConfirmResDto {

	@Schema(description = "serialkey")
	private String serialkey;
	@Schema(description = "chgReqDeptCd")
	private String chgReqDeptCd;
	/** 커스텀 엑스트라 체크박스 */
	@Schema(description = "커스텀 엑스트라 체크박스", example = "N")
	private String customRowCheckYn = "N";
}
