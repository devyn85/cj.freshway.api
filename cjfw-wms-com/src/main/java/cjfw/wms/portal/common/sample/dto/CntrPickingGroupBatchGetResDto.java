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
 * @author : 박진우 (jwpark1104@cj.net)
 * @date : 2025.04.21
 * @description : 피킹그룹 정보조회
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2025.04.17 박진우 (jwpark1104@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CntrPickingGroupBatchGetResDto {
//	DCCODE, PLANT, STORAGETYPE, DISTANCETYPE
	
	
	@Schema(description = "센터코드",nullable = false)
	private String	dccode;
	@Schema(description = "플랜트",nullable = false)
	private String	plant;
	@Schema(description = "저장유형",nullable = false)
	private String	storagetype;
	@Schema(description = "원거리타입",nullable = false)
	private String	distancetype;
	
	private String	serialkey;
	private String	batchgroup;
	private String	description;
	private String	status;
	private String	delyn;
	private String	trafficcop;
	private String	archivecop;
	private String	adddate;
	private String	editdate;
	private String	addwho;
	private String	editwho;
	private String	organize;
	private String	area;
	private String	ordertype;
	private String	etccode1;
	private String	etccode2;
	private String	etccode3;
	private String	etccode4;
	private String rowflag;

}
