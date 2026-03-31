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
 * @description : 피킹그룹 정보조회 reqDto
 * @issues : ----------------------------------------------------------- DATE
 *         AUTHOR MAJOR_ISSUE
 *         -----------------------------------------------------------
 *         2025.04.17 박진우 (jwpark1104@cj.net) 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CntrPickingGroupBatchGetReqDto {
	@Schema(description = "피킹배치그룹", nullable = true)
	private String batchgroup;
	@Schema(description = "피킹배치그룹명", nullable = true)
	private String description;
	@Schema(description = "시리얼키", nullable = true)
	private String serialkey;
}
