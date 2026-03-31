package cjfw.wms.portal.common.sample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : KimSunHo (sunhokim6229@cj.net)
 * @date        : 2025.04.21
 * @description : 존 정보 조회 Request DTO Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.04.21        KimSunHo (sunhokim6229@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "존 정보 조회 Request DTO")
public class ZoneManagerGetReqDto {

	@Schema(description = "피킹존", nullable = true, example = "2600")
	private String schZone;
	
	@Schema(description = "내역", nullable = true, example = "1층 피킹장")
	private String schDescription;
	
	@Schema(description = "센터", nullable = true, example = "")
	private String schDccode;
	
	@Schema(description = "기본센터", nullable = true, example = "")
	private String gDccode;
	
	@Schema(description = "사용자아이디", nullable = true, example = "")
	private String regId;
}
