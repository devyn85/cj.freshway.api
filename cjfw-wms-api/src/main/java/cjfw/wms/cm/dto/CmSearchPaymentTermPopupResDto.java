package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.08.09
 * @description : PAYMENT TERM 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "PAYMENT TERM 검색 응답")
public class CmSearchPaymentTermPopupResDto {
	/** Supplier 코드 */
	@Schema(description = "PAYMENT TERM 코드", nullable = false, example = "")
	private String code;
	
	/** Supplier명 */
	@Schema(description = "PAYMENT TERM명", nullable = false, example = "")
	private String name;

}