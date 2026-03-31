package cjfw.wms.om.dto;

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
 * @description : 자동발주모니터링 Master Response DTO Class
 * @issues :
 *
 *         <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.08.12 JiSooKim (jskim14@cj.net) 생성
 *         </pre>
 */
@Schema(description = "자동발주모니터링 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmAutoOrderMonitoringResDto {

	@Schema(description = "발주유형")
    private String purchaseType;

    @Schema(description = "발주유형명")
    private String purchaseTypeNm;

    @Schema(description = "자동발주타임")
    private String purchaseAutoTime;

    @Schema(description = "자동발주타임명")
    private String purchaseAutoTimeNm;

    @Schema(description = "타겟일자")
    private String targetDate;

    @Schema(description = "문서번호")
    private String docNo;

    @Schema(description = "출발센터")
    private String frDcCode;

    @Schema(description = "출발조직")
    private String frOrganize;

    @Schema(description = "도착센터")
    private String toDcCode;

    @Schema(description = "도착조직")
    private String toOrganize;

    @Schema(description = "등록일시")
    private String addDate;

    @Schema(description = "등록자")
    private String addWho;
}
