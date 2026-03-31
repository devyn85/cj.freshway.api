package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.07.04
 * @description : 센터상품속성(광역일배) Entity
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.07.04        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "센터상품속성(광역일배) Entity") 
public class MsSkuDcSetSTOEntity extends CommonDto {	
	/** 데이터번호 */
	@Schema(description = "시리얼키 (PK)")
    private String serialKey;

    @Schema(description = "센터코드", maxLength = 14)
    private String dcCode;

    @Schema(description = "고객사코드", maxLength = 20)
    private String storerKey;

    @Schema(description = "상품(SKU) 코드", maxLength = 20)
    private String sku;

    @Builder.Default
    @Schema(description = "삭제 여부", defaultValue = "N")
    private String delYn = "N";

    @Builder.Default
    @Schema(description = "SMS 전송 여부", defaultValue = "N")
    private String smsYn = "N";

    @Schema(description = "보낼 센터코드")
    private String toDcCode;

    @Schema(description = "최초등록시간 (자동 생성)")
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)")
    private String editDate;

    @Builder.Default
    @Schema(description = "최초등록자", defaultValue = "SYSTEM")
    private String addWho = "SYSTEM";

    @Builder.Default
    @Schema(description = "최종변경자", defaultValue = "SYSTEM")
    private String editWho = "SYSTEM";
}
