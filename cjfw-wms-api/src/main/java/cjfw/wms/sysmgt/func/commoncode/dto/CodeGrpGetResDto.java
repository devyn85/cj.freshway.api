package cjfw.wms.sysmgt.func.commoncode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeGrpGetResDto {
	@Schema(description = "공통그룹코드", example = "TPL_TIMEZONE")
    private String comGrpCd;
	@Schema(description = "공통그룹코드(원본)", example = "TPL_TIMEZONE")
    private String comGrpCdOrig;
	@Schema(description = "공통그룹코드명", example = "타임존")
    private String grpCdNm;
	@Schema(description = "코드설명", example = "타임존")
    private String grpCdDesc;
	@Schema(description = "사용여부", example = "1")
    private String useYn;
	@Schema(description = "수정자ID", example = "devadmin01")
    private String modId;
	@Schema(description = "수정일시", example = "2022-07-26")
    private String modDt;
}
