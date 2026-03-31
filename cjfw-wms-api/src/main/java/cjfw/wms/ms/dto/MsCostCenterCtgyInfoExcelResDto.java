package cjfw.wms.ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "업무부세조직분류 엑셀 유효성 결과 DTO")
public class MsCostCenterCtgyInfoExcelResDto {
    @Schema(description = "고객코드")
    private String custkey;

    @Schema(description = "적용월")
    private String applyYm;

    @Schema(description = "업무부코드")
    private String deptCd;

    @Schema(description = "고객코드 유효 여부(Y/N)")
    private String custkeyChk;

    @Schema(description = "적용월 형식 유효 여부(Y/N)")
    private String applyYmChk;

    @Schema(description = "기존 등록건 여부(Y/N) - 존재 시 U, 미존재 시 I")
    private String updateYn;
}

