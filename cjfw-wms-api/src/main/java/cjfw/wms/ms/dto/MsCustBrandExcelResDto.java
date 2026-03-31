package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "본점별브랜드 엑셀 검증 결과 DTO")
public class MsCustBrandExcelResDto {
    @Schema(description = "본점코드")
    private String custKey;

    @Schema(description = "브랜드명")
    private String brandName;

    @Schema(description = "본점코드 존재 여부(Y/N)")
    private String custKeyChk;

    @Schema(description = "브랜드명 유효 여부(Y/N)")
    private String brandNameChk;

    @Schema(description = "기존 등록 여부(Y/N)")
    private String updateYn;
}

