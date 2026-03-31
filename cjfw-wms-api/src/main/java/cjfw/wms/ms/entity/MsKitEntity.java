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
 * @date        : 2025.07.02
 * @description : KIT상품기준정보 Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.02        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Schema(description = "KIT상품기준정보 Entity") 
public class MsKitEntity extends CommonDto {	
	@Schema(description = "시리얼키 (PK)", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal serialKey;

    @Schema(description = "고객사코드", maxLength = 20)
    private String storerKey;

    @Schema(description = "센터코드", maxLength = 14)
    private String dcCode;

    @Schema(description = "키트 상품 코드", maxLength = 20)
    private String kitSku;

    @Schema(description = "키트 상품명", maxLength = 200)
    private String kitNm;

    @Schema(description = "구성품 상품 코드", maxLength = 20)
    private String sku;

    @Schema(description = "구성품 수량", defaultValue = "0")
    private BigDecimal qty = BigDecimal.ZERO;

    @Schema(description = "삭제 여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String delYn = "N";

    @Schema(description = "유효 시작일", example = "20250701", maxLength = 8)
    private String fromDate;

    @Schema(description = "유효 종료일", example = "99991231", maxLength = 8)
    private String toDate;

    @Schema(description = "단위", defaultValue = "EA", maxLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
    private String uom = "EA";

    @Schema(description = "단위 수량", defaultValue = "0")
    private BigDecimal uomQty = BigDecimal.ZERO;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String addWho;

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", maxLength = 24, requiredMode = Schema.RequiredMode.REQUIRED)
    private String editWho;

    @Schema(description = "출력 여부", defaultValue = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
    private String prtnYn = "N";
}
