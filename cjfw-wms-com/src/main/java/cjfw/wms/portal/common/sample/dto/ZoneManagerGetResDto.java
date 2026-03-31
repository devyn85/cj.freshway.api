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
 * @description : 존 정보 조회 Response DTO Class
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
@Schema(description = "존 정보 조회 Response DTO")
public class ZoneManagerGetResDto {
	@Schema(description = "센터")
	String dccode;
	
	@Schema(description = "피킹존")
	String zone;
	
	@Schema(description = "존 설명")
	String description;
	
	@Schema(description = "입고 전략")
	String instrategy;
	
	@Schema(description = "출고 전략")
	String outstrategy;
	
	@Schema(description = "상태")
	String status; 
	
	@Schema(description = "삭제여부")
	String delYn;
	
	@Schema(description = "저장유형")
	String storagetype;
	
	@Schema(description = "구간(방)")
	String zoneGugan;
	
	@Schema(description = "데이터번호")
	String serialkey;
	
	@Schema(description = "등록일시")
	String adddate;
	
	@Schema(description = "수정일시")
	String editdate;
	
	@Schema(description = "등록자")
	String addwho;
	
	@Schema(description = "수정자")
	String editwho;
}
