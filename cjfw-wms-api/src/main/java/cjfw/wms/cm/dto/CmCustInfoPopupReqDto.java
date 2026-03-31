package cjfw.wms.cm.dto;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : KwonJungYun (jungyun8667@cj.net)
 * @date : 2025.06.23
 * @description : 거래처 정보(단건) 팝업조회 Request DTO
 * @issues :<pre>
 * -----------------------------------------------------------
 * DATE       AUTHOR                MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.06.23 KwonJungYun (jungyun8667@cj.net) 생성 </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "거래처 정보(단건) 팝업조회 Request DTO")
public class CmCustInfoPopupReqDto extends CommonDto {
    /** 고객사코드 */
    @Schema(description = "고객사코드")
    private String storerkey;

    /** 거래처코드 */
    @Schema(description = "거래처코드")
    private String custkey;

    /** 거래처 타입 */
    @Schema(description = "거래처 타입")
    private String custtype;
}