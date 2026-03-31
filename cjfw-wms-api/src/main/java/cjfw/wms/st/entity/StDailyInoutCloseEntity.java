package cjfw.wms.st.entity;

import java.math.BigDecimal;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.22
 * @description : 수불마감정보 기능을 구현한 Entity Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.22        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "수불마감정보 Entity") 
public class StDailyInoutCloseEntity extends CommonDto {

    @Schema(description = "데이터번호 (DB Sequence에 의해 자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal serialKey;
    
    @Schema(description = "센터 코드", example = "DC001")
    private String dcCode;
    
    @Schema(description = "고객사 코드")
    private String storerKey;

    @Schema(description = "상태 (00~99)", defaultValue = "00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 10)
    private String status;

    @Schema(description = "삭제여부", defaultValue = "N", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1)
    private String delYn;

    @Schema(description = "수불 일자", example = "20240731")
    private String inoutDt;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String editWho;
    
}
