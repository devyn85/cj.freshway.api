package cjfw.wms.gwms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 김환수 (hwansoo.kim@cj.net)
 * @date : 2025.11.05
 * @description : 중계 API > 행정동코드기준 주 출고센터 송신(MDM0001)
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.20 김환수 (hwansoo.kim@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "행정동코드기준 주 출고센터") 
public class HjdongDccodeResDto {

	@Schema(description = "센터코드")
	private String dccode;							// DCCODE
	@Schema(description = "비고")
	private String rmk;								// RMK
/*
	@Schema(description = "행정동코드")
	private String hjdongCd;						// HJDONG_CD
	@Schema(description = "주문그룹")
	private String Ordgrp;							// ORDGRP
	@Schema(description = "데이터번호_주문그룹")
	private String serialkeyOrdgrp;					// SERIALKEY_ORDGRP
	@Schema(description = "데이터번호_행정동")
	private String serialkeyHjdong;					// SERIALKEY_HJDONG
*/	
}
