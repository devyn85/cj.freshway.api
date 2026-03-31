package cjfw.wms.kp.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author      : JiSooKim (jskim14@cj.net)
 * @date        : 2025.12.23
 * @description : 일자별 검수현황(모니터링팝업_sum) 응답 DTO
 * @issues      :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.12.23 JiSooKim (jskim14@cj.net)  생성 </pre>
 */
@Data
@Schema(description = "일자별 검수현황(모니터링팝업_sum) 응답")
public class KpDpInspectMonitoringSumResDto extends CommonDto {
    @Schema(description = "출고처코드")
    private String fromcustkey;

    @Schema(description = "출고처명")
    private String fromcustname;

    @Schema(description = "입고예정중량(KG)")
    private BigDecimal storeropenkg;

    @Schema(description = "검수중량(KG)")
    private BigDecimal dpinspectkg;

    @Schema(description = "검수율(%)")
    private BigDecimal dpinsprate;

    @Schema(description = "전체검수율(%)")
    private BigDecimal totalrate;
}
