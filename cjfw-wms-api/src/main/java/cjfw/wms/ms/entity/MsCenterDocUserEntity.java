package cjfw.wms.ms.entity;

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
 * @description : 센터서류 담당자 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.17        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "센터서류 담당자 Entity") 
public class MsCenterDocUserEntity extends CommonDto {	
	@Schema(description = "데이터번호 (PK, DB Sequence에 의해 자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal serialKey;

    @Schema(description = "사용자아이디", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 50)
    private String userNm;

    @Schema(description = "센터코드", defaultValue = "STD", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 14)
    private String dcCode;

    @Schema(description = "고객사코드", defaultValue = "FW00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 20)
    private String storerKey;

    @Schema(description = "이메일", maxLength = 50)
    private String email;

    @Schema(description = "상태", defaultValue = "00", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 12)
    private String status = "00";

    @Schema(description = "삭제여부", defaultValue = "N", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1)
    private String delYn = "N";

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String editWho;
}
