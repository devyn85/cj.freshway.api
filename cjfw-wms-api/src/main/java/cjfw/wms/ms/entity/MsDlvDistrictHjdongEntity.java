package cjfw.wms.ms.entity;

import cjfw.wms.common.extend.CommonDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.09.05 
 * @description : 배송권역 행정동 Entity 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.05 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Data
@Schema(description = "배송 권역 행정동 Entity") 
public class MsDlvDistrictHjdongEntity extends CommonDto {
	@Schema(description = "일련번호 (고유 키)", example = "123456789012345")
    private String serialkey;

    @Schema(description = "센터코드")
    private String dccode;

    @Schema(description = "배송권역아이디")
    private String dlvdistrictId;
   
    @Schema(description = "배송권역명")
    private String dlvdistrictNm;

    @Schema(description = "행정동코드")
    private String hjdongCd;

    @Schema(description = "삭제유무")
    private String delYn;

    @Schema(description = "유효시작일")
    private String fromDate;

    @Schema(description = "유효종료일")
    private String toDate;

    @Schema(description = "최초등록시간 (자동 생성)", accessMode = Schema.AccessMode.READ_ONLY)
    private String addDate;

    @Schema(description = "최종변경시간 (자동 갱신)", accessMode = Schema.AccessMode.READ_ONLY)
    private String editDate;

    @Schema(description = "최초등록자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String addWho = "SYSTEM";

    @Schema(description = "최종변경자", defaultValue = "SYSTEM", requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 24)
    private String editWho = "SYSTEM";
}
