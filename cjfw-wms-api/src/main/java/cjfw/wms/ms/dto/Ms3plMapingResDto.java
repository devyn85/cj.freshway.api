package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : KimDongHyeon (tirran123@cj.net)
 * @date : 2025.11.18
 * @description : 3PL전산기준목록 Master Response DTO Class
 * @issues :
 *
 * <pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.11.18 KimDongHyeon (tirran123@cj.net) 생성
 *         </pre>
 */
@Schema(description = "3PL전산기준목록 Master Response DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ms3plMapingResDto {
    /**
     * STORERKEY
     */
    @Schema(description = "STORERKEY")
    private String storerkey;

    /**
     * COURIER
     */
    @Schema(description = "COURIER")
    private String courier;

    /**
     * CUSTKEY
     */
    @Schema(description = "CUSTKEY")
    private String custkey;

    /**
     * COURIERNM
     */
    @Schema(description = "COURIERNM")
    private String couriernm;

    /**
     * CUSTNM
     */
    @Schema(description = "CUSTNM")
    private String custnm;

    /**
     * START_DATE
     */
    @Schema(description = "START_DATE")
    private String startDate;

    /**
     * CONTRACTYN
     */
    @Schema(description = "CONTRACTYN")
    private String contractyn;

    /**
     * USE_YN
     */
    @Schema(description = "USE_YN")
    private String useYn;

    /**
     * ADDDATE
     */
    @Schema(description = "ADDDATE")
    private String adddate;

    /**
     * EDITDATE
     */
    @Schema(description = "EDITDATE")
    private String editdate;

    /**
     * ADDWHO
     */
    @Schema(description = "ADDWHO")
    private String addwho;

    /**
     * EDITWHO
     */
    @Schema(description = "EDITWHO")
    private String editwho;
}
