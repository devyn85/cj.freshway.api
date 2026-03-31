package cjfw.wms.cm.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.17
 * @description : 물류센터별창고관리 엔티티 클래스 
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
public class CmDcXOrganizeManagerEntity extends CommonDto {
    @Schema(description = "데이터번호 (DB Sequence에 의해 자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal serialKey;
    
    @Schema(description = "물류센터코드", example = "2600")
	private String dcCode;

    @Schema(description = "조직명칭", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    private String description;
    
    @Schema(description = "창고", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 200)
    private String organize;

    @Schema(description = "상태 (00~99)", defaultValue = "00", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String status = "00";

    @Schema(description = "삭제여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn = "N";

    @Schema(description = "데이터흐름제어", maxLength = 10)
    private String trafficCop;

    @Schema(description = "아카이브제어", maxLength = 1)
    private String archiveCop;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String editWho;
}