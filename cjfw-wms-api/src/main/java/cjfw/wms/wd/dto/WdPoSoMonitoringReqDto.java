package cjfw.wms.wd.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : YangChangHwan (iamai@cj.net)
 * @date : 2025.06.14
 * @description : WdPoSoMonitoringReqDto Request DTO
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.14 YangChangHwan (iamai@cj.net) ����
 *         </pre>
 */
@Schema(description = "WdPoSoMonitoringReqDto DTO")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WdPoSoMonitoringReqDto extends CommonDto {
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String fixdccode;		
    /** 물류센터 */
    @Schema(description = "물류센터", nullable = false, example = "")
    private String dccode;		

    /** 전표일자 */
    @Schema(description = "전표일자")
    private String slipdt;

    /** 마감코드 */
    @Schema(description = "마감코드")
    private String magamcode;

}
