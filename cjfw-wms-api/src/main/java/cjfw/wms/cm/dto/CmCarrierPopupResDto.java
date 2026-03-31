package cjfw.wms.cm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KimSunHo (sunhokim6229@cj.net) 
 * @date : 2025.05.09
 * @description : 운송사 검색 응답 DTO
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 KimSunHo (sunhokim6229@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "운송사 검색 응답")
public class CmCarrierPopupResDto {
	/** 운송사코드 */
	@Schema(description = "운송사코드", nullable = false, example = "")
	private String code;
	
	/** 운송사명 */
	@Schema(description = "운송사명", nullable = false, example = "")
	private String name;
	
	/** 운송사유형 */
	@Schema(description = "운송사유형", example = "LOCAL")
	private String carrierType;
	
	/** 전화번호 */
	@Schema(description = "전화번호", example = "0100000000")
	private String phone1;
}