package cjfw.wms.om.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : JiSooKim (jskim14@cj.net)
 * @date : 2025.08.12
 * @description : 자동발주모니터링 Request DTO Class
 * @issues :
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성
 *         </pre>
 */
@Schema(description = "자동발주모니터링 Request DTO")
//@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmAutoOrderMonitoringReqDto extends CommonDto {

	@Schema(description = "기준일자(yyyymmdd)", example = "20250303")
    private String taskDtRt;
}
